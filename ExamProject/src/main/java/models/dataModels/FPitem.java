package models.dataModels;
import java.util.Date;

public class FPitem {
    String postTitle;
    String subRedditName;
    String userName;
    String userID;
    Date timestamp;
    int PostKarma;
    int commentNum;

    public FPitem(String postTitle, String subRedditName, String userName, Date timestamp, int postKarma, int commentNum, String userID) {
        this.postTitle = postTitle;
        this.subRedditName = subRedditName;
        this.userName = userName;
        this.timestamp = timestamp;
        this.PostKarma = postKarma;
        this.commentNum = commentNum;
        this.userID = userID;
    }

    public FPitem(Post post, SubReddit subReddit, User user) {
        this.postTitle = post.getPostTitle();
        this.subRedditName = subReddit.getSubRedditName();
        this.userName = user.getUserName();
        this.timestamp = post.getTimestamp();
        this.PostKarma = post.getPostKarmaCount();
        this.commentNum = post.getCommentsSize();
        this.userID = user.getUserID();
    }

    public String getPostTitle() { return postTitle; }

    public String getSubRedditName() {return subRedditName; }

    public String getUserID(){ return userID; }

    public String getUserName() { return userName; }

    public Date getTimestamp() {
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
