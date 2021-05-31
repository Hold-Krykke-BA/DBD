package holdkrykke.dataLayer;

import holdkrykke.models.dataModels.*;
import holdkrykke.models.viewModels.PostWithCommentsContainer;

import java.util.List;

public interface IDataController {
    public List<FPitem> getFrontPageItems(String userID);
    public List<SubReddit> getSubRedditsByUser(String userID);
    public User getUserInfo(String userID);
    public UserKarma getUserKarma(String userID);
    public void getUserMessages(String userID);
    public void createMessage(String userIDsender, String userIDreciever);
    public void authenticateUser(String userID);
    public void createComment(Post post, User user, Comment comment);
    public void createPost(Post post, User user, SubReddit subreddit);
    public void createSubreddit(SubReddit subreddit);
    public void followSubreddit(SubReddit subreddit, User user);
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
