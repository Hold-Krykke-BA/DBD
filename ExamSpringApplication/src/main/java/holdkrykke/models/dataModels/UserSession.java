package holdkrykke.models.dataModels;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserSession {
    private String sessionID, userID;
    private LocalDateTime timestamp, ttl;

    public UserSession(String sessionID, String userID, LocalDateTime timestamp) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.timestamp = timestamp;
    }

    public UserSession(String sessionID, String userID, LocalDateTime timestamp, LocalDateTime ttl) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.ttl = ttl;
    }

    public UserSession(String userID, LocalDateTime timestamp) {
        this.userID = userID;
        this.timestamp = timestamp;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTtl() {
        return ttl;
    }

    public void setTtl(LocalDateTime ttl) {
        this.ttl = ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSession)) return false;
        UserSession userSession = (UserSession) o;
        return Objects.equals(getSessionID(), userSession.getSessionID()) && Objects.equals(getUserID(), userSession.getUserID()) && Objects.equals(getTimestamp(), userSession.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionID(), getUserID(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionID='" + sessionID + '\'' +
                ", userID='" + userID + '\'' +
                ", timestamp=" + timestamp +
                ", ttl=" + ttl +
                '}';
    }
}
