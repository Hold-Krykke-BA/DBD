package models.dataModels;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    String postID;
    String postTitle;
    LocalDateTime timestamp;
    String subredditID;
    String userID;
    String content;
    int postKarmaCount;
    List<String> commentsID;


    public Post(String postID, LocalDateTime timestamp, String postTitle, String subredditID, String userID, int postKarmaCount, String postcontent) {
        this.postID = postID;
        this.timestamp = timestamp;
        this.postTitle = postTitle;
        this.subredditID = subredditID;
        this.userID = userID;
        this.postKarmaCount = 0;
        commentsID = new ArrayList<>();
        this.content = postcontent;
    }

    public int getCommentsSize(){
        // temp. solution - should be a recursive call I think since comments can have children
        return commentsID.size();
    }

    public String getPostID() {
        return postID;
    }

    public String getPostContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSubredditID() {
        return subredditID;
    }

    public String getPostTitle(){
        return postTitle;
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
