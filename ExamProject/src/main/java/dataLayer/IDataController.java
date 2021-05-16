package dataLayer;

import models.dataModels.FPitem;
import models.dataModels.SubReddit;
import models.dataModels.User;

import java.util.List;

public interface IDataController {
    public List<FPitem> getFrontPageItems(String userID);
    public List<SubReddit> getSubRedditsByUser(String userID);
    public User getUserInfo(String userID);
    public void getUserMessages(String userID);
    public void createMessage(String userIDsender, String userIDreciever);
    public void authenticateUser(String userID);
    // and more...
}
