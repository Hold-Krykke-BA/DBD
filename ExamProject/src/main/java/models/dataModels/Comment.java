package models.dataModels;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    String commentID;
    String timestamp;
    int commentKarmaCount;
    List<String> childCommentsID;

    public Comment(String commentID, String timestamp, int commentKarmaCount) {
        this.commentID = commentID;
        this.timestamp = timestamp;
        this.commentKarmaCount = commentKarmaCount;
        this.childCommentsID = new ArrayList<>();
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentKarmaCount() {
        return commentKarmaCount;
    }

    public void setCommentKarmaCount(int commentKarmaCount) {
        this.commentKarmaCount = commentKarmaCount;
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
