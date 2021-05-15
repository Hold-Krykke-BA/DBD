package dataLayer.dataAccessors;
import models.dataModels.User;
import redis.clients.jedis.Jedis;
import java.util.UUID;


public class RedisAccessor {
    Jedis jedis;
    // docker run --name redistwo -v redis-data:/data -p 6379:6379 -d redis:alpine (porten er vigtig! uden -p "bindes" den ikke til localhost og kan ikke findes fra windows)
    public RedisAccessor(){
        jedis = new Jedis("0.0.0.0", 6379);
    }

    public void saveCacheID (User user){
        UUID cacheID = UUID.randomUUID();
        //System.out.println(cacheID);
        jedis.set(user.getUserID(), cacheID.toString());
    }

    public String getCacheID(User user){
        return jedis.get(user.getUserID());
    }

    public static void main(String[] args) {
        User user = new User("arne", "s@g.dk", "172893");
        RedisAccessor rDBD = new RedisAccessor();
        rDBD.saveCacheID(user);
        System.out.println(rDBD.getCacheID(user));
    }


}
