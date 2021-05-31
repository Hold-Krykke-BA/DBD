package holdkrykke.models.dataModels;

import holdkrykke.util.CreateUUID;
import holdkrykke.util.StringManipulation;

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

    // for creation
    public Post(String postTitle, String subredditID, String userID, String postcontent) {
        this.postUrlIdentifier = StringManipulation.generateRandomString(5);
        this.postID = CreateUUID.getID();
        this.timestamp = LocalDateTime.now();
        this.postTitle = postTitle;
        this.subredditID = subredditID;
        this.userID = userID;
        this.content = postcontent;
    }

    public Post(){
        this.postUrlIdentifier = StringManipulation.generateRandomString(5);
        this.postID = CreateUUID.getID();
        this.timestamp = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setSubredditID(String subredditID) {
        this.subredditID = subredditID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
