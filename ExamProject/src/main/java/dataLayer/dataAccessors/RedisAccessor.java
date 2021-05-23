package dataLayer.dataAccessors;
import models.dataModels.FPitem;
import models.dataModels.Post;
import models.dataModels.SubReddit;
import models.dataModels.User;
import redis.clients.jedis.Jedis;
import util.DateConverter;
import util.StringManipulation;

import java.time.LocalDateTime;
import java.util.*;


public class RedisAccessor {
    Jedis jedis;
    int cacheTimeout = 15000000;

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

    public void createPostCache(FPitem fpItem , String cacheID){
        UUID postUUID = UUID.randomUUID();
        jedis.rpush(cacheID, postUUID.toString());
        jedis.pexpire(cacheID,cacheTimeout);

        createFPitem(fpItem, postUUID.toString());
    }

    public void createMultiplePostCache(List<FPitem> fpitems, String cacheID){
        for(FPitem item : fpitems){
            createPostCache(item, cacheID);
        }
    }

    public List<String> getPostUUIDs(String cacheID){
        if(cacheID == null) return new ArrayList<>();
        return jedis.lrange(cacheID, 0, -1);
    }

    private void createFPitem(FPitem fpItem, String postuuid){
        Map<String, String> map = new HashMap<>();
        map.put("subreddit",fpItem.getSubRedditName());
        map.put("comments",String.valueOf(fpItem.getCommentNum()));
        map.put("created",DateConverter.getStringFromDate(fpItem.getTimestamp()));
        map.put("karma",String.valueOf(fpItem.getPostKarma()));
        map.put("title",fpItem.getPostTitle());
        map.put("createdby",fpItem.getUserName());
        map.put("postidentifier",fpItem.getPostUrlIdentifier());
        jedis.hset(postuuid, map);
        jedis.pexpire(postuuid,cacheTimeout);
    }


    public List<FPitem> getFPitems(String userID){
        List<String> postuuids = getPostUUIDs(getCacheID(userID));
        Map<String, String> map;
        List<FPitem> fpitems = new ArrayList<>();

        for(String item : postuuids){
           map = jedis.hgetAll(item);
            fpitems.add(new FPitem(map.get("postidentifier"), map.get("title"), map.get("subreddit"), map.get("createdby"), DateConverter.getDateFromString(map.get("created")),
                    Integer.parseInt(map.get("karma")), Integer.parseInt(map.get("comments")), userID));

        }
        return fpitems;
    }


    // all below is just testing and are to-be-deleted whenever we don't need the testing anymore
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime date = LocalDateTime.now();
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
        Post post1 = new Post("222222", StringManipulation.generateRandomString(5), date, "TestPost3", "363636", "172893", 0, "sdfsdf");
        Post post2 = new Post("333333",StringManipulation.generateRandomString(5), date, "TestPost4", "363636", "172893", 0, "sfsdf" );
        //Post post3 = new Post("987654321", "2017414795", "TestPost2", "363636", "1111111", 0 );

        // String subRedditID, String subRedditName
        SubReddit subreddit = new SubReddit("363636", "WSB");
        List<FPitem> fplist = new ArrayList<>();
//        fplist.add(new FPitem(post1, subreddit, user));
//        fplist.add(new FPitem(post2, subreddit, user));

//        rDBD.createMultiplePostCache(fplist);

//        List<String> res = rDBD.getPostUUIDs(rDBD.getCacheID(user.getUserID()));
//        for (Object item : res) {
//            System.out.println(item);
//        }

        System.out.println("USER CACHE ID " + rDBD.getCacheID("3f"));
        System.out.println("USER POSTUUIDS " + rDBD.getPostUUIDs(rDBD.getCacheID("3f")));


        List<FPitem> fpitems = rDBD.getFPitems("3f");
        System.out.println("FPITEMS " + fpitems.toString());
        System.out.println("************************************************");
        for (Object item : fpitems) {
            System.out.println("Am here");
            System.out.println(item);
        }

//        System.out.println("CacheID : " + rDBD.getCacheID(user.getUserID()));
        System.out.println("All keys:");
        System.out.println(rDBD.jedis.keys("*"));
//        Thread.sleep(15000);
//        System.out.println("CacheID : " + rDBD.getCacheID(user.getUserID()));

//        System.out.println("All keys:");
//        System.out.println(rDBD.jedis.keys("*"));
    }


}
