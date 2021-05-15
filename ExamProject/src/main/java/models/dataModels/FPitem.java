package models.dataModels;

public class FPitem {
    String postTitle;
    String subRedditName;
    String userName;
    String timestamp;
    int PostKarma;
    int commentNum;

    public FPitem(String postTitle, String subRedditName, String userName, String timestamp, int postKarma, int commentNum) {
        this.postTitle = postTitle;
        this.subRedditName = subRedditName;
        this.userName = userName;
        this.timestamp = timestamp;
        PostKarma = postKarma;
        this.commentNum = commentNum;
    }

    public FPitem(Post post, SubReddit subReddit, User user) {
        this.postTitle = post.getPostTitle();
        this.subRedditName = subReddit.getSubRedditName();
        this.userName = user.getUserName();
        this.timestamp = post.getTimestamp();
        this.PostKarma = post.getPostKarmaCount();
        this.commentNum = post.getCommentsSize();
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getSubRedditName() {
        return subRedditName;
    }

    public String getUserName() {
        return userName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getPostKarma() {
        return PostKarma;
    }

    public int getCommentNum() {
        return commentNum;
    }
}
