package holdkrykke.models.viewModels;

import holdkrykke.models.dataModels.Comment;
import holdkrykke.models.dataModels.Post;

import java.util.ArrayList;
import java.util.List;

public class PostWithCommentsContainer {
    Post post;
    List<Comment> parents;

    public PostWithCommentsContainer(Post _post, List<Comment> _comments){
        this.post = _post;
        this.parents = _comments;
    }

    public Post getPost() {
        return post;
    }

    public List<Comment> getComments() {
        return parents;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setComments(List<Comment> comments) {
        this.parents = comments;
    }
}
