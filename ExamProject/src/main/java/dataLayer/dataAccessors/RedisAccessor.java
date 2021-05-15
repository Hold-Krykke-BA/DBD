package dataLayer.dataAccessors;
import models.dataModels.FPitem;
import models.dataModels.Post;
import models.dataModels.SubReddit;
import models.dataModels.User;
import redis.clients.jedis.Jedis;

import java.util.*;


public class RedisAccessor {
    Jedis jedis;
    // docker run --name redistwo -v redis-data:/data -p 6379:6379 -d redis:alpine (porten er vigtig! uden -p "bindes" den ikke til localhost og kan ikke findes fra windows)
    public RedisAccessor(){
        jedis = new Jedis("0.0.0.0", 6379);
    }

    public void createCacheID (String userID){
        UUID cacheID = UUID.randomUUID();
        //System.out.println(cacheID);
        jedis.set(userID, cacheID.toString());
    }

    public String getCacheID(String userID){
        return jedis.get(userID);
    }

    // should be either transaction or collect all fpItems and set all of them in same db-call
    public void createPostUUIDs(FPitem fpItem){
        UUID postUUID = UUID.randomUUID();
        if(getCacheID(fpItem.getUserID()) == null){
            createCacheID(fpItem.getUserID());
        }
        String cacheID = getCacheID(fpItem.getUserID());
        jedis.rpush(cacheID, postUUID.toString());
        // call create FPitem
        createFPitem(fpItem, postUUID.toString());
    }

    public List<String> getPostUUIDs(String cacheID){
        return jedis.lrange(cacheID, 0, -1);
    }

    public void createFPitem(FPitem fpItem, String postuuid){
        Map<String, String> map = new HashMap<>();
        map.put("subreddit",fpItem.getSubRedditName());
        map.put("comments",String.valueOf(fpItem.getCommentNum()));
        map.put("created",fpItem.getTimestamp());
        map.put("karma",String.valueOf(fpItem.getPostKarma()));
        map.put("title",fpItem.getPostTitle());
        map.put("createdby",fpItem.getUserName());
        jedis.hset(postuuid, map);
    }

    public List<FPitem> getFPitems(String userID){
        List<String> postuuids = getPostUUIDs(getCacheID(userID));
        Map<String, String> map;
        List<FPitem> fpitems = new ArrayList<>();

        for(String item : postuuids){
           map = jedis.hgetAll(item);
           fpitems.add(new FPitem(map.get("title"), map.get("subreddit"), map.get("createdby"), map.get("created"),
                   Integer.parseInt(map.get("karma")), Integer.parseInt(map.get("comments")), userID));
        }
        return fpitems;
    }

    public static void main(String[] args) {
        User user = new User("arne", "s@g.dk", "172893");
        User user3 = new User("arne", "s@g.dk", "1111111");
        RedisAccessor rDBD = new RedisAccessor();
        rDBD.createCacheID(user.getUserID());

        //System.out.println(rDBD.getCacheID(user.getUserID()));
        //System.out.println(rDBD.getCacheID(user3.getUserID()) == null);

        // String postID, String timestamp, String postTitle, String subredditID, String userID, int postKarmaCount
        Post post1 = new Post("123456789", "2015468795", "TestPost1", "363636", "172893", 0 );
        Post post2 = new Post("987654321", "2017414795", "TestPost2", "363636", "172893", 0 );
        //Post post3 = new Post("987654321", "2017414795", "TestPost2", "363636", "1111111", 0 );

        // String subRedditID, String subRedditName
        SubReddit subreddit = new SubReddit("363636", "WSB");
        FPitem fPitem1 = new FPitem(post1, subreddit, user);
        FPitem fPitem2 = new FPitem(post2, subreddit, user);

        rDBD.createPostUUIDs(fPitem1);
        rDBD.createPostUUIDs(fPitem2);
        List<String> res = rDBD.getPostUUIDs(rDBD.getCacheID(user.getUserID()));
        for (Object item : res) {
            System.out.println(item);
        }

        List<FPitem> fpitems = rDBD.getFPitems(user.getUserID());
        System.out.println("************************************************");
        for (Object item : fpitems) {
            System.out.println(item);
        }
    }


}
