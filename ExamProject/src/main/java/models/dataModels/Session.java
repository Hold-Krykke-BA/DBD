package models.dataModels;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private String sessionID, UserID;
    private LocalDateTime Timestamp;

    public Session(String sessionID, String userID, LocalDateTime timestamp) {
        this.sessionID = sessionID;
        UserID = userID;
        Timestamp = timestamp;
    }

    public Session(String userID, LocalDateTime timestamp) {
        UserID = userID;
        Timestamp = timestamp;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public LocalDateTime getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        Timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(getSessionID(), session.getSessionID()) && Objects.equals(getUserID(), session.getUserID()) && Objects.equals(getTimestamp(), session.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionID(), getUserID(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionID='" + sessionID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", Timestamp=" + Timestamp +
                '}';
    }
}
