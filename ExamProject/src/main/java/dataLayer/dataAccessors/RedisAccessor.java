package dataLayer.dataAccessors;
import models.dataModels.FPitem;
import models.dataModels.Post;
import models.dataModels.SubReddit;
import models.dataModels.User;
import redis.clients.jedis.Jedis;

import java.util.*;


public class RedisAccessor {
    Jedis jedis;
    int cacheTimeout = 15000;

    public RedisAccessor(){
        jedis = new Jedis("0.0.0.0", 6379);
    }

    public void createCacheID (String userID){
        UUID cacheID = UUID.randomUUID();
        jedis.set(userID, cacheID.toString());
        jedis.pexpire(userID,cacheTimeout);
    }

    public String getCacheID(String userID){
        return jedis.get(userID);
    }

    public void createPostCache(FPitem fpItem){
        UUID postUUID = UUID.randomUUID();
        if(getCacheID(fpItem.getUserID()) == null){
            createCacheID(fpItem.getUserID());
        }
        String cacheID = getCacheID(fpItem.getUserID());
        jedis.rpush(cacheID, postUUID.toString());
        jedis.pexpire(cacheID,cacheTimeout);

        createFPitem(fpItem, postUUID.toString());
    }

    public void createMultiplePostCache(List<FPitem> fpitems){
        for(FPitem item : fpitems){
            createPostCache(item);
        }
    }

    public List<String> getPostUUIDs(String cacheID){
        return jedis.lrange(cacheID, 0, -1);
    }

    private void createFPitem(FPitem fpItem, String postuuid){
        Map<String, String> map = new HashMap<>();
        map.put("subreddit",fpItem.getSubRedditName());
        map.put("comments",String.valueOf(fpItem.getCommentNum()));
        map.put("created",fpItem.getTimestamp());
        map.put("karma",String.valueOf(fpItem.getPostKarma()));
        map.put("title",fpItem.getPostTitle());
        map.put("createdby",fpItem.getUserName());
        jedis.hset(postuuid, map);
        jedis.pexpire(postuuid,cacheTimeout);
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



    // all below is just testing and are to-be-deleted whenever we don't need the testing anymore
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            UUID cacheID = UUID.randomUUID();
            System.out.println(cacheID);
        }

        User user = new User("arne", "s@g.dk", "172893");
        User user3 = new User("arne", "s@g.dk", "1111111");
        RedisAccessor rDBD = new RedisAccessor();
        rDBD.createCacheID(user.getUserID());

        //System.out.println(rDBD.getCacheID(user.getUserID()));
        //System.out.println(rDBD.getCacheID(user3.getUserID()) == null);

        // String postID, String timestamp, String postTitle, String subredditID, String userID, int postKarmaCount
        Post post1 = new Post("222222", "301468795", "TestPost3", "363636", "172893", 0, "sdfsdf");
        Post post2 = new Post("333333", "4017414795", "TestPost4", "363636", "172893", 0, "sfsdf" );
        //Post post3 = new Post("987654321", "2017414795", "TestPost2", "363636", "1111111", 0 );

        // String subRedditID, String subRedditName
        SubReddit subreddit = new SubReddit("363636", "WSB");
        List<FPitem> fplist = new ArrayList<>();
        fplist.add(new FPitem(post1, subreddit, user));
        fplist.add(new FPitem(post2, subreddit, user));

        rDBD.createMultiplePostCache(fplist);

        List<String> res = rDBD.getPostUUIDs(rDBD.getCacheID(user.getUserID()));
        for (Object item : res) {
            System.out.println(item);
        }

        List<FPitem> fpitems = rDBD.getFPitems(user.getUserID());
        System.out.println("************************************************");
        for (Object item : fpitems) {
            System.out.println(item);
        }

        System.out.println("CacheID : " + rDBD.getCacheID(user.getUserID()));
        System.out.println("All keys:");
        System.out.println(rDBD.jedis.keys("*"));
        Thread.sleep(15000);
        System.out.println("CacheID : " + rDBD.getCacheID(user.getUserID()));

        System.out.println("All keys:");
        System.out.println(rDBD.jedis.keys("*"));
    }


}
