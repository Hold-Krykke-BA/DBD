package dataLayer.controller;

import dataLayer.IDataController;
import dataLayer.dataAccessors.Neo4jAccessor;
import dataLayer.dataAccessors.PostgresAccessor;
import dataLayer.dataAccessors.RedisAccessor;
import models.dataModels.FPitem;
import models.dataModels.SubReddit;
import models.dataModels.User;


import java.util.ArrayList;
import java.util.List;

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
            // call postgress and add new FPitems to uncached list
            redDBD.createCacheID(userID);
            redDBD.createMultiplePostCache(uncached);
        }
        return result;
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
}
