package models.dataModels;

public class UserKarma {
    private String userID;
    private int commentKarma;
    private int postKarma;
    private int sumKarma;

    public UserKarma(String userID, int commentKarma, int postKarma) {
        this.userID = userID;
        this.commentKarma = commentKarma;
        this.postKarma = postKarma;
        this.sumKarma = commentKarma + postKarma;
    }

    public String getUserID() {
        return userID;
    }

    public int getCommentKarma() {
        return commentKarma;
    }

    public int getPostKarma() {
        return postKarma;
    }

    public int getSumKarma() {
        return sumKarma;
    }

    @Override
    public String toString() {
        return "UserKarma{" +
                "userID='" + userID + '\'' +
                ", commentKarma=" + commentKarma +
                ", postKarma=" + postKarma +
                ", sumKarma=" + sumKarma +
                '}';
    }
}
