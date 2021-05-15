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
        this.commentKarmaCount = 0;
        this.childCommentsID = new ArrayList<>();
    }

    public String getCommentID() {
        return commentID;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public int getCommentKarmaCount() {
        return commentKarmaCount;
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
