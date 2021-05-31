package holdkrykke.dataLayer;

import holdkrykke.models.dataModels.*;
import holdkrykke.models.viewModels.PostUpdater;
import holdkrykke.models.viewModels.PostWithCommentsContainer;

import java.util.List;

public interface IDataController {
    public List<FPitem> getFrontPageItems(String userID, String subredditID);
    public List<SubReddit> getSubRedditsByUser(String userID);
    public UserContainer getUserInfo(String userID);
    public void getUserMessages(String userID);
    public void createMessage(String userIDsender, String userIDreciever);
    public void authenticateUser(String userID);
    public void createComment(Post post, User user, Comment comment);
    public void createPost(Post post);
    public void updatePost(PostUpdater postupdater);
    public void deletePost(String postID);
    public void createSubreddit(SubReddit subreddit);
    public void followSubreddit(SubReddit subreddit, String userID);
    public void createUser(User user);
    public List<String> getUserIDs();
    public PostWithCommentsContainer getPostWithComments(String urlIdentifier, String subredditName, String postID);
    public PostWithCommentsContainer getPostWithCommentsSorted(String urlIdentifier, String subredditName, String postID);
    public void unfollowSubreddit(String userID, String subredditID);
    public void upvotePost(String postID);
    public void downvotePost(String postID);
    public void upvoteComment(String commentId);
    public void downvoteComment(String commentID);
}
