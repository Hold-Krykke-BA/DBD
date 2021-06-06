package holdkrykke.models.dataModels;

import java.time.LocalDateTime;
import java.util.Objects;

public class Chat {
    private String chatID;
    private LocalDateTime timestamp;

    public Chat(String chatID, LocalDateTime timestamp) {
        this.chatID = chatID;
        this.timestamp = timestamp;
    }

    public Chat() {
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat)) return false;
        Chat chat = (Chat) o;
        return Objects.equals(getChatID(), chat.getChatID()) && Objects.equals(getTimestamp(), chat.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChatID(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatID='" + chatID + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
