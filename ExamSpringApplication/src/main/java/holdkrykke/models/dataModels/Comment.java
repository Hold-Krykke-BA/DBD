package holdkrykke.models.dataModels;

import holdkrykke.util.CreateUUID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    String commentID;
    String parentID;
    String content;
    LocalDateTime timestamp;
    int commentKarmaCount;
    String postID;
    String userID;
    List<Comment> children;



    public Comment(String commentID, LocalDateTime timestamp, int commentKarmaCount, String content, String parentid, String postid, String userid) {
        this.parentID = parentid;
        this.content = content;
        this.commentID = commentID;
        this.timestamp = timestamp;
        this.commentKarmaCount = commentKarmaCount;
        this.postID = postid;
        this.userID = userid;
        this.children = new ArrayList<>();
    }

    public Comment() {
        this.commentID = CreateUUID.getID();
        this.timestamp = LocalDateTime.now();
        this.commentKarmaCount = 0;
        this.children = new ArrayList<>();
    }

    public Comment(String parentID, String content, String postID, String userID) {
        this.commentID = CreateUUID.getID();
        this.timestamp = LocalDateTime.now();
        this.commentKarmaCount = 0;
        this.children = new ArrayList<>();
        this.parentID = parentID;
        this.content = content;
        this.postID = postID;
        this.userID = userID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void addComment(Comment comment){
        children.add(comment);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getCommentKarmaCount() {
        return commentKarmaCount;
    }

    public String getCommentContent() {
        return content;
    }

    public String getCommentParentID() {
        return parentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setCommentKarmaCount(int commentKarmaCount) {
        this.commentKarmaCount = commentKarmaCount;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getPostID() {
        return postID;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID='" + commentID + '\'' +
                ", parentID='" + parentID + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", commentKarmaCount=" + commentKarmaCount +
                ", postID='" + postID + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }

}
