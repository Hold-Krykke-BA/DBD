package holdkrykke.models.dataModels;


import holdkrykke.util.CreateUUID;

public class SubReddit {
    String subRedditID;
    String subRedditName;

    public SubReddit(String subRedditID, String subRedditName) {
        this.subRedditID = subRedditID;
        this.subRedditName = subRedditName;
    }

    public SubReddit(String subRedditName) {
        this.subRedditName = subRedditName;
        this.subRedditID = CreateUUID.getID();
    }

    public SubReddit() {
        this.subRedditID = CreateUUID.getID();
    }

    public String getSubRedditID() {
        return subRedditID;
    }

    public String getSubRedditName() {
        return subRedditName;
    }

    public void setSubRedditID(String subRedditID) {
        this.subRedditID = subRedditID;
    }

    public void setSubRedditName(String subRedditName) {
        this.subRedditName = subRedditName;
    }

    @Override
    public String toString() {
        return "SubReddit{" +
                "subRedditID='" + subRedditID + '\'' +
                ", subRedditName='" + subRedditName + '\'' +
                '}';
    }
}
