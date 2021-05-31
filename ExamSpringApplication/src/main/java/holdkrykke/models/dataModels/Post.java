package holdkrykke.models.dataModels;

import java.time.LocalDateTime;

public class Post {
    String postID;
    String postUrlIdentifier;
    String postTitle;
    LocalDateTime timestamp;
    String subredditID;
    String userID;
    String content;
    int postKarmaCount = 0;


    public Post(String postID, String postidentifier, LocalDateTime timestamp, String postTitle, String subredditID, String userID, int postKarmaCount, String postcontent) {
        this.postUrlIdentifier = postidentifier;
        this.postID = postID;
        this.timestamp = timestamp;
        this.postTitle = postTitle;
        this.subredditID = subredditID;
        this.userID = userID;
        this.postKarmaCount = postKarmaCount;
        this.content = postcontent;
    }

    public Post(String postID, String postidentifier, LocalDateTime timestamp, String postTitle, String subredditID, String userID, String postcontent) {
        this.postUrlIdentifier = postidentifier;
        this.postID = postID;
        this.timestamp = timestamp;
        this.postTitle = postTitle;
        this.subredditID = subredditID;
        this.userID = userID;
        this.content = postcontent;
    }

    public String getPostID() {
        return postID;
    }

    public String getPostUrlIdentifier() {
        return postUrlIdentifier;
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

    @Override
    public String toString() {
        return "Post{" +
                "postID='" + postID + '\'' +
                ", postUrlIdentifier='" + postUrlIdentifier + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", timestamp=" + timestamp +
                ", subredditID='" + subredditID + '\'' +
                ", userID='" + userID + '\'' +
                ", content='" + content + '\'' +
                ", postKarmaCount=" + postKarmaCount +
                '}';
    }
}
