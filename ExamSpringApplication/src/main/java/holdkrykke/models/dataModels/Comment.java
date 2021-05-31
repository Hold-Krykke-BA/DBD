package holdkrykke.models.dataModels;

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
