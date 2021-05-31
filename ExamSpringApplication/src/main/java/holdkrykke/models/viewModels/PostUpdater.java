package holdkrykke.models.viewModels;

import java.time.LocalDateTime;

public class PostUpdater {
    String postID;
    String content;

    public PostUpdater(String postID, String content) {
        this.postID = postID;
        this.content = content;
    }

    public PostUpdater() {
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
