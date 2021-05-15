package dataLayer.dataAccessors;
import redis.clients.jedis.Jedis;


public class RedisAccessor {
    Jedis jedis;
    // docker run --name redistwo -v redis-data:/data -p 6379:6379 -d redis:alpine (porten er vigtig! uden -p "bindes" den ikke til localhost og kan ikke findes fra windows)
    public RedisAccessor(){
        jedis = new Jedis("0.0.0.0", 6379);
    }
    
}
