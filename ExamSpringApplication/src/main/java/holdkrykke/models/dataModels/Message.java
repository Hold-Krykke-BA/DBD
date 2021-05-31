package holdkrykke.models.dataModels;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private String messageID, senderUserID, receiverUserID, content;
    private LocalDateTime timestamp;

    public Message(String senderUserID, String receiverUserID, String content, LocalDateTime timestamp) {
        this.senderUserID = senderUserID;
        this.receiverUserID = receiverUserID;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(String messageID, String senderUserID, String receiverUserID, String content, LocalDateTime timestamp) {
        this.messageID = messageID;
        this.senderUserID = senderUserID;
        this.receiverUserID = receiverUserID;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getSenderUserID() {
        return senderUserID;
    }

    public void setSenderUserID(String senderUserID) {
        this.senderUserID = senderUserID;
    }

    public String getReceiverUserID() {
        return receiverUserID;
    }

    public void setReceiverUserID(String receiverUserID) {
        this.receiverUserID = receiverUserID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getMessageID(), message.getMessageID()) && Objects.equals(getSenderUserID(), message.getSenderUserID()) && Objects.equals(getReceiverUserID(), message.getReceiverUserID()) && Objects.equals(getContent(), message.getContent()) && Objects.equals(getTimestamp(), message.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessageID(), getSenderUserID(), getReceiverUserID(), getContent(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID='" + messageID + '\'' +
                ", senderUserID='" + senderUserID + '\'' +
                ", receiverUserID='" + receiverUserID + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
