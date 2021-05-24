package dataLayer.controller;

import dataLayer.IDataController;
import dataLayer.dataAccessors.Neo4jAccessor;
import dataLayer.dataAccessors.PostgresAccessor;
import dataLayer.dataAccessors.RedisAccessor;
import models.dataModels.*;
import models.viewModels.PostWithCommentsContainer;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataControllerImpl implements IDataController {
    private static DataControllerImpl singleton = null;
    private RedisAccessor redDBD;
    private PostgresAccessor pgrDBD;
    private Neo4jAccessor neoDBD;
    private int minFrontpageItems = 25;

    private DataControllerImpl(){
        this.redDBD = new RedisAccessor();
        this.pgrDBD = new PostgresAccessor();
        this.neoDBD = new Neo4jAccessor();
    }

    public static DataControllerImpl getInstance(){
        if (singleton == null){
            singleton = new DataControllerImpl();
        }
        return singleton;
    }

    @Override
    public List<FPitem> getFrontPageItems(String userID) {
        List<FPitem> result = redDBD.getFPitems(userID);

        if (result.size() < minFrontpageItems){
            List<FPitem> uncached = new ArrayList<>();

            // FOR RÚNI: call neo4j with post_user_id from postgres to get post_username
            List<Map<String, Object>> FPmapList = pgrDBD.getFrontPageItemsBySubRedditID("609f1f9f-dba7-44c8-838b-c00bb5d3e7ac");
            for(Map<String, Object> map : FPmapList){
                uncached.add(new FPitem((String)map.get("post_title"), (String)map.get("post_url_identifier"),
                        (String)map.get("subreddit_name"), "post_username", (LocalDateTime) map.get("post_timestamp"),
                        (int) map.get("post_karma"), (int)map.get("comments"), (String)map.get("post_user_id")));
            }
            redDBD.createFrontpageCacheID(userID);
            redDBD.createMultiplePostCache(uncached, redDBD.getFrontpageCacheID(userID));
            result = uncached;
        }
        return result;
    }

    public static void main(String[] args) {
        DataControllerImpl dc = new DataControllerImpl();
        List<FPitem> fp = dc.getFrontPageItems("3ff"); //cache-owner user id, not post-owner
        for(FPitem item : fp){
            System.out.println(item.toString());
        }
        User user = new User("", "", "a643196f-6a35-496e-a206-774c4bdc1d7c");
        SubReddit subReddit1 = new SubReddit("280c2631-bed6-4500-9fc0-abe386d2eea0", "photography");
        SubReddit subReddit2 = new SubReddit("f1843571-aa55-418d-9a43-9bc2054452fa", "legaladvice");
        System.out.println(dc.getSubRedditsByUser("a643196f-6a35-496e-a206-774c4bdc1d7c"));
        dc.unfollowSubreddit("a643196f-6a35-496e-a206-774c4bdc1d7c", "f1843571-aa55-418d-9a43-9bc2054452fa");
        System.out.println(dc.getSubRedditsByUser("a643196f-6a35-496e-a206-774c4bdc1d7c"));
//        dc.followSubreddit(subReddit1, user);
//        dc.followSubreddit(subReddit2, user);
        System.out.println(dc.getSubRedditsByUser("a643196f-6a35-496e-a206-774c4bdc1d7c"));
    }

    @Override
    public List<SubReddit> getSubRedditsByUser(String userID) {
        List<SubReddit> subreddits = redDBD.getFollowedSubreddits(userID);
        if(subreddits.isEmpty()){
            System.out.println("IN IF");
            subreddits = pgrDBD.getFollowedSubreddits(userID);
            for(SubReddit subreddit : subreddits){
                redDBD.createUserSubredditCache(userID, subreddit);
            }
        }
        return subreddits;
    }


    @Override
    public User getUserInfo(String userID) {
        return null;
    }

    @Override
    public void getUserMessages(String userID) {

    }

    @Override
    public void createMessage(String userIDsender, String userIDreciever) {

    }

    @Override
    public void authenticateUser(String userID) {

    }

    @Override
    public void createComment(Post post, User user, Comment comment) {
        pgrDBD.insertComment(post, user, comment);
    }

    @Override
    public void createPost(Post post, User user, SubReddit subreddit) {
        pgrDBD.insertPost(post, user,subreddit);
    }

    @Override
    public void createSubreddit(SubReddit subreddit) {
        pgrDBD.insertSubreddit(subreddit);
    }

    @Override
    public void followSubreddit(SubReddit subreddit, User user) {
        pgrDBD.insert_User_Follow_Subreddit(subreddit, user);
        redDBD.createUserSubredditCache(user.getUserID(), subreddit);
    }

    @Override
    public void createUser(User user) {
        // Insert in neo4j also
        pgrDBD.insertUserId(user);
    }

    @Override
    public PostWithCommentsContainer getPostWithComments(String urlIdentifier, String subredditName, String postID) {
        PostWithCommentsContainer pwcContainer = new PostWithCommentsContainer(pgrDBD.getPost(subredditName, urlIdentifier), pgrDBD.getComments(postID));
        // comments needs to be ordered/sorted by parentID and timestamp before returning
        return null;
    }

    @Override
    public void unfollowSubreddit(String userID, String subredditID) {
        pgrDBD.unfollow_user_subreddit(userID, subredditID);
        redDBD.removeUserSubredditCache(userID, subredditID);

    }
}
