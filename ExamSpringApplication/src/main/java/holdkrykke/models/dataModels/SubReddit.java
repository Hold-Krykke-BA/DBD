package holdkrykke.models.dataModels;


public class SubReddit {
    String subRedditID;
    String subRedditName;

    public SubReddit(String subRedditID, String subRedditName) {
        this.subRedditID = subRedditID;
        this.subRedditName = subRedditName;
    }

    public String getSubRedditID() {
        return subRedditID;
    }

    public String getSubRedditName() {
        return subRedditName;
    }


    @Override
    public String toString() {
        return "SubReddit{" +
                "subRedditID='" + subRedditID + '\'' +
                ", subRedditName='" + subRedditName + '\'' +
                '}';
    }
}
