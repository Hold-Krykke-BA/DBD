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
    List<String> childCommentsID;

    public Comment(String commentID, LocalDateTime timestamp, int commentKarmaCount, String content) {
        this.content = content;
        this.commentID = commentID;
        this.timestamp = timestamp;
        this.commentKarmaCount = 0;
        this.childCommentsID = new ArrayList<>();
    }

    public Comment(String commentID, LocalDateTime timestamp, int commentKarmaCount, String content, String parentid) {
        this.parentID = parentid;
        this.content = content;
        this.commentID = commentID;
        this.timestamp = timestamp;
        this.commentKarmaCount = 0;
        this.childCommentsID = new ArrayList<>();
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

    public void addToCommentKarmaCount() {
        commentKarmaCount ++;
    }

    public void detractFromCommentKarmaCount() {
        commentKarmaCount --;
    }

    public List<String> getChildCommentsID() {
        return childCommentsID;
    }

    public void addToChildCommentsID(Comment comment) {
        childCommentsID.add(comment.commentID);
    }

    public void removeChildComment(Comment comment){
        childCommentsID.remove(comment.commentID);
    }


}
