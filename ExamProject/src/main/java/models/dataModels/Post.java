package models.dataModels;

import java.util.ArrayList;
import java.util.List;

public class Post {
    String postID;
    String timestamp;
    String subredditID;
    String userID;
    int postKarmaCount;
    List<String> commentsID;


    public Post(String postID, String timestamp, String subredditID, String userID, int postKarmaCount) {
        this.postID = postID;
        this.timestamp = timestamp;
        this.subredditID = subredditID;
        this.userID = userID;
        this.postKarmaCount = 0;
        commentsID = new ArrayList<>();
    }

    public String getPostID() {
        return postID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSubredditID() {
        return subredditID;
    }

    public String getUserID() {
        return userID;
    }

    public int getPostKarmaCount() {
        return postKarmaCount;
    }

    public void addToPostKarmaCount() {
        postKarmaCount ++;
    }

    public void detractFromPostKarmaCount() {
        postKarmaCount --;
    }

    public List<String> getCommentsID() {
        return commentsID;
    }

    public void addToChildCommentsID(Comment comment) {
        commentsID.add(comment.commentID);
    }

    public void removeChildComment(Comment comment){
        commentsID.remove(comment.commentID);
    }
}
