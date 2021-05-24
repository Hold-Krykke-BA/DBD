package dataLayer;

import models.dataModels.*;
import models.viewModels.PostWithCommentsContainer;

import java.util.List;

public interface IDataController {
    public List<FPitem> getFrontPageItems(String userID);
    public List<SubReddit> getSubRedditsByUser(String userID);
    public User getUserInfo(String userID);
    public void getUserMessages(String userID);
    public void createMessage(String userIDsender, String userIDreciever);
    public void authenticateUser(String userID);
    public void createComment(Post post, User user, Comment comment);
    public void createPost(Post post, User user, SubReddit subreddit);
    public void createSubreddit(SubReddit subreddit);
    public void followSubreddit(SubReddit subreddit, User user);
    public void createUser(User user);
    public PostWithCommentsContainer getPostWithComments(String urlIdentifier, String subredditName, String postID);
    public void unfollowSubreddit(String userID, String subredditID);
}
