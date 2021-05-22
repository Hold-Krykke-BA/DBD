package models.dataModels;


import java.time.LocalDateTime;

public class FPitem {
    String postTitle;
    String postUrlIdentifier;
    String subRedditName;
    String userName;
    String userID;
    LocalDateTime timestamp;
    int PostKarma;
    int commentNum;

    public FPitem(String postTitle, String postidentifier, String subRedditName, String userName, LocalDateTime timestamp, int postKarma, int commentNum, String userID) {
        this.postTitle = postTitle;
        this.postUrlIdentifier = postidentifier;
        this.subRedditName = subRedditName;
        this.userName = userName;
        this.timestamp = timestamp;
        this.PostKarma = postKarma;
        this.commentNum = commentNum;
        this.userID = userID;
    }

    public FPitem(Post post, SubReddit subReddit, User user) {
        this.postTitle = post.getPostTitle();
        this.postUrlIdentifier = post.getPostUrlIdentifier();
        this.subRedditName = subReddit.getSubRedditName();
        this.userName = user.getUserName();
        this.timestamp = post.getTimestamp();
        this.PostKarma = post.getPostKarmaCount();
        this.userID = user.getUserID();
    }

    public String getPostTitle() { return postTitle; }

    public String getPostUrlIdentifier() { return postUrlIdentifier; }

    public String getSubRedditName() {return subRedditName; }

    public String getUserID(){ return userID; }

    public String getUserName() { return userName; }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getPostKarma() {
        return PostKarma;
    }

    public int getCommentNum() {
        return commentNum;
    }

    @Override
    public String toString() {
        return "FPitem{" +
                "postTitle='" + postTitle + '\'' +
                ", subRedditName='" + subRedditName + '\'' +
                ", userName='" + userName + '\'' +
                ", userID='" + userID + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", PostKarma=" + PostKarma +
                ", commentNum=" + commentNum +
                '}';
    }
}
