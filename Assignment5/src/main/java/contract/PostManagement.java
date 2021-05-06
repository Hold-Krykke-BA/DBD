package contract;

import dto.Post;

import java.util.List;

public interface PostManagement {
    boolean createPost(String username, String message);
    List<Post> getPosts(String username);
    List<Post> getPostsBetween(String username, long timeFrom, long timeTo);
}
