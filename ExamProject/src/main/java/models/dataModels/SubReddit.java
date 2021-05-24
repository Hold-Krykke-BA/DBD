package models.dataModels;

import java.util.ArrayList;
import java.util.List;

public class SubReddit {
    String subRedditID;
    String subRedditName;
    List<String> modUsersID;
    List<String> subbedUsersID;

    public SubReddit(String subRedditID, String subRedditName) {
        this.subRedditID = subRedditID;
        this.subRedditName = subRedditName;
        this.modUsersID = new ArrayList<>();
        this.subbedUsersID = new ArrayList<>();
    }

    public String getSubRedditID() {
        return subRedditID;
    }

    public String getSubRedditName() {
        return subRedditName;
    }

    public List<String> getModUsersID() {
        return modUsersID;
    }

    public void addToModUsersID(User user) {
        modUsersID.add(user.getUserID());
    }

    public void removeFromModUsersID(User user){
        modUsersID.remove(user.getUserID());
    }

    public List<String> getSubbedUsersID() {
        return subbedUsersID;
    }

    public void addToSubbedUsersID(User user) {
        subbedUsersID.add(user.getUserID());
    }

    public void removeFromSubbedUsersID(User user){
        subbedUsersID.remove(user.getUserID());
    }
}
