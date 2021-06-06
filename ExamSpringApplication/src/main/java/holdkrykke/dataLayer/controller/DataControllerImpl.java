package holdkrykke.dataLayer.controller;

import holdkrykke.dataLayer.IDataController;

import holdkrykke.dataLayer.dataAccessors.PostgresAccessor;
import holdkrykke.dataLayer.dataAccessors.RedisAccessor;
import holdkrykke.dataLayer.dataAccessors.Neo4jAccessor;
import holdkrykke.models.dataModels.*;
import holdkrykke.models.viewModels.CommentUpdater;
import holdkrykke.models.viewModels.PostUpdater;
import holdkrykke.models.viewModels.PostWithCommentsContainer;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataControllerImpl implements IDataController {
    private static DataControllerImpl singleton = null;
    private RedisAccessor redDBD;
    private PostgresAccessor pgrDBD;
    private Neo4jAccessor neoDBD;
    private int minFrontpageItems = 25;

    private DataControllerImpl() {
        this.redDBD = new RedisAccessor();
        this.pgrDBD = new PostgresAccessor();
        this.neoDBD = new Neo4jAccessor();
    }

    public static DataControllerImpl getInstance() {
        if (singleton == null) {
            singleton = new DataControllerImpl();
        }
        return singleton;
    }

    @Override
    public List<FPitem> getFrontPageItems(String userID, String subredditID) {
        List<FPitem> result = redDBD.getFPitems(userID, subredditID);

        if (result.size() < minFrontpageItems) {
            List<FPitem> uncached = new ArrayList<>();

            List<Map<String, Object>> FPmapList = pgrDBD.getFrontPageItemsBySubRedditID(subredditID);
            for (Map<String, Object> map : FPmapList) {
                String userName = neoDBD.getUserByUserID(map.get("post_user_id").toString()).getUserName();
                uncached.add(new FPitem((String) map.get("post_title"), (String) map.get("post_url_identifier"),
                        (String) map.get("subreddit_name"), userName, (LocalDateTime) map.get("post_timestamp"),
                        (int) map.get("post_karma"), (int) map.get("comments"), (String) map.get("post_user_id")));
            }
            redDBD.createFrontpageCacheID(userID, subredditID);
            redDBD.createMultiplePostCache(uncached, redDBD.getFrontpageCacheID(userID, subredditID));
            result = uncached;
        }
        return result;
    }

    @Override
    public List<SubReddit> getSubRedditsByUser(String userID) {
        List<SubReddit> subreddits = redDBD.getFollowedSubreddits(userID);
        if (subreddits.isEmpty()) {
            subreddits = pgrDBD.getFollowedSubreddits(userID);
            for (SubReddit subreddit : subreddits) {
                redDBD.createUserSubredditCache(userID, subreddit);
            }
        }
        return subreddits;
    }


    @Override
    public UserContainer getUserInfo(String userID) {
        return new UserContainer(neoDBD.getUserByUserID(userID),
                pgrDBD.getUserKarma(userID), pgrDBD.getFollowedSubreddits(userID));
    }

    @Override
    public Message deleteMessage(String messageID) {
        return neoDBD.deleteMessage(messageID);
    }

    @Override
    public List<Chat> getUserChats(String userName) {
        return neoDBD.getUserChats(userName);
    }

    @Override
    public List<Message> getChatMessages(String chatID) {
        return neoDBD.getChatMessages(chatID);
    }


    @Override
    public Message createMessage(Message message, String userIDreciever) {
        var chat = neoDBD.getOrCreateChat(message.getSenderUserID(), userIDreciever);
        return neoDBD.createMessage(chat.getChatID(), message);
    }

    @Override
    public List<UserSession> authenticateUser(String userID) {
        List<UserSession> sessions = neoDBD.getUserSessions(userID);

        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        if (sessions.isEmpty()) {
            sessions.add(neoDBD.createSession(userID));
        } else {
            for (int i = 0; i < sessions.size(); i++) {
                UserSession ses = sessions.get(i);
                UserSession newSes = neoDBD.updateSession(ses.getSessionID());
                sessions.set(i, newSes);
            }
        }
        return sessions;
    }

    @Override
    public void createComment(Comment comment) {
        pgrDBD.insertComment(comment);
    }

    @Override
    public void updateComment(CommentUpdater commentUpdater) {
        pgrDBD.updateComment(commentUpdater.getCommentID(), commentUpdater.getContent());
    }

    @Override
    public void deleteComment(String commentID) {
        pgrDBD.deleteComment(commentID);
    }

    @Override
    public void createPost(Post post) {
        pgrDBD.insertPost(post);
    }

    @Override
    public void updatePost(PostUpdater postupdater) {
        pgrDBD.updatePost(postupdater.getPostID(), postupdater.getContent());
    }

    @Override
    public void deletePost(String postID) {
        pgrDBD.deletePost(postID);
    }

    @Override
    public void createSubreddit(SubReddit subreddit) {
        pgrDBD.insertSubreddit(subreddit);
    }

    @Override
    public void followSubreddit(SubReddit subreddit, String userID) {
        pgrDBD.insert_User_Follow_Subreddit(subreddit, userID);
        redDBD.createUserSubredditCache(userID, subreddit);
    }

    @Override
    public User createUser(User user) {
        pgrDBD.insertUserId(user);
        return neoDBD.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return neoDBD.updateUser(user);
    }

    @Override
    public Boolean deleteUserByUserID(String userID) {
        return neoDBD.deleteUserByUserID(userID);
    }

    @Override
    public Boolean getOrCreateUserFollowing(String userID, String followerID) {
        return neoDBD.getOrCreateUserFollowing(userID, followerID);
    }

    @Override
    public List<String> getUserIDs() {
        return pgrDBD.getAllUserID();
    }

    @Override
    public PostWithCommentsContainer getPostWithComments(String urlIdentifier, String subredditName, String postID) {
        return new PostWithCommentsContainer(pgrDBD.getPost(subredditName, urlIdentifier), convertToTree(pgrDBD.getComments(postID)));
    }

    @Override
    public PostWithCommentsContainer getPostWithCommentsSorted(String urlIdentifier, String subredditName, String postID) {
        return new PostWithCommentsContainer(pgrDBD.getPost(subredditName, urlIdentifier), convertToTree(pgrDBD.getCommentsSorted(postID)));
    }

    private List<Comment> convertToTree(List<Comment> comments) {
        List<Comment> commentTree = new ArrayList<>();
        List<String> parentIDs = new ArrayList<>();

        Map<String, Comment> map = new HashMap<>();

        for (Comment comment : comments) {
            if (map.get(comment.getCommentID()) == null) {
                map.put(comment.getCommentID(), comment);

                if (comment.getCommentParentID() == null) {
                    parentIDs.add(comment.getCommentID());
                }
            }
            if (comment.getCommentParentID() != null) {
                map.get(comment.getCommentParentID()).addComment(comment);
            }
        }
        for (String parent : parentIDs) {
            commentTree.add(map.get(parent));
        }
        return commentTree;
    }

    @Override
    public void unfollowSubreddit(String userID, String subredditID) {
        pgrDBD.unfollow_user_subreddit(userID, subredditID);
        redDBD.removeUserSubredditCache(userID, subredditID);
    }

    @Override
    public void upvotePost(String postID) {
        pgrDBD.upvotePost(postID);
    }

    @Override
    public void downvotePost(String postID) {
        pgrDBD.downvotePost(postID);
    }

    @Override
    public void upvoteComment(String commentId) {
        pgrDBD.upvoteComment(commentId);
    }

    @Override
    public void downvoteComment(String commentID) {
        pgrDBD.downvoteComment(commentID);
    }

}
