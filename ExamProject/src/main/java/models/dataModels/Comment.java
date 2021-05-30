package models.dataModels;

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

//    public Comment(String commentID, LocalDateTime timestamp, String content) {
//        this.content = content;
//        this.commentID = commentID;
//        this.timestamp = timestamp;
//        this.commentKarmaCount = 0;
//    }


    public Comment(String commentID, LocalDateTime timestamp, int commentKarmaCount, String content, String parentid, String postid, String userid) {
        this.parentID = parentid;
        this.content = content;
        this.commentID = commentID;
        this.timestamp = timestamp;
        this.commentKarmaCount = commentKarmaCount;
        this.postID = postid;
        this.userID = userid;
    }

    public String getCommentID() {
        return commentID;
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
