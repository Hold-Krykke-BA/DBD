package impl;

import contract.PostManagement;
import dto.Post;
import util.Time;
import redis.clients.jedis.Jedis;

import java.util.List;

public class PostManagementImpl implements PostManagement {
    private Jedis jedis;
    private Time time;

    public PostManagementImpl(Jedis jedis, Time time) {
        this.jedis = jedis;
        this.time = time;
    }

    @Override
    public boolean createPost(String username, String message) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<Post> getPosts(String username) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<Post> getPostsBetween(String username, long timeFrom, long timeTo) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
