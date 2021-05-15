package models.dataModels;

import java.util.Objects;

public class User {
    String userName;
    String userID;
    String userMail;

    public User(String name, String mail, String id){
        this.userID = id;
        this.userMail = mail;
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(userID, user.userID) && Objects.equals(userMail, user.userMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userID, userMail);
    }
}
