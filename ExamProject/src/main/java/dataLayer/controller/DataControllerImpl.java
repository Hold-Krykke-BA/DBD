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
            redDBD.createCacheID(userID);
            redDBD.createMultiplePostCache(uncached, redDBD.getCacheID(userID));
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
    }

    @Override
    public List<SubReddit> getSubRedditsByUser(String userID) {
        return null;
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
    public void subscribeUserToSubreddit(SubReddit subreddit, User user) {
        pgrDBD.insertUser_Subreddit(subreddit, user);
    }

    @Override
    public void createUser(User user) {
        // Insert in neo4j also
        pgrDBD.insertUserId(user);
    }

    @Override
    public PostWithCommentsContainer getPostCommentContainer(String urlIdentifier, String subredditName, String postID) {
        PostWithCommentsContainer pwcContainer = new PostWithCommentsContainer(pgrDBD.getPost(subredditName, urlIdentifier), pgrDBD.getComments(postID));
        // comments needs to be ordered/sorted by parentID and timestamp before returning
        return null;
    }
}
