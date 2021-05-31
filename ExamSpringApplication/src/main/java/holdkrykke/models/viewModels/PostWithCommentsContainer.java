package holdkrykke.models.viewModels;

import holdkrykke.models.dataModels.Comment;
import holdkrykke.models.dataModels.Post;

import java.util.ArrayList;
import java.util.List;

public class PostWithCommentsContainer {
    Post post;
    List<Comment> comments = new ArrayList();

    public PostWithCommentsContainer(Post _post, List<Comment> _comments){
        this.post = _post;
        this.comments = _comments;
    }

    public Post getPost() {
        return post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
