package holdkrykke.models.viewModels;

public class CommentUpdater {
    String commentID;
    String content;

    public CommentUpdater(String commentID, String content) {
        this.commentID = commentID;
        this.content = content;
    }

    public CommentUpdater() {
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
