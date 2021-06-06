package holdkrykke.models.dataModels;

import java.time.LocalDateTime;

public class MessageDTO extends Message {
    private String userIDReceiver;


    @Override
    public String getMessageID() {
        return super.getMessageID();
    }

    @Override
    public void setMessageID(String messageID) {
        super.setMessageID(messageID);
    }

    @Override
    public String getSenderUserID() {
        return super.getSenderUserID();
    }

    @Override
    public void setSenderUserID(String senderUserID) {
        super.setSenderUserID(senderUserID);
    }

    @Override
    public String getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Override
    public LocalDateTime getTimestamp() {
        return super.getTimestamp();
    }

    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        super.setTimestamp(timestamp);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public MessageDTO() {
    }

    public MessageDTO(String senderUserID, String content, LocalDateTime timestamp, String userIDReceiver) {
        super(senderUserID, content, timestamp);
        this.userIDReceiver = userIDReceiver;
    }

    public MessageDTO(String messageID, String senderUserID, String content, LocalDateTime timestamp, String userIDReceiver) {
        super(messageID, senderUserID, content, timestamp);
        this.userIDReceiver = userIDReceiver;
    }

    public MessageDTO(String senderUserID, String content, String userIDReceiver) {
        super(senderUserID, content);
        this.userIDReceiver = userIDReceiver;
    }

    public MessageDTO(String userIDReceiver) {
        this.userIDReceiver = userIDReceiver;
    }

    public String getUserIDReceiver() {
        return userIDReceiver;
    }

    public void setUserIDReceiver(String userIDReceiver) {
        this.userIDReceiver = userIDReceiver;
    }

    public String toString() {
        return "MessageDTO{" +
                "userIDReceiver='" + userIDReceiver + '\'' +
                "messageID='" + super.getMessageID() + '\'' +
                ", senderUserID='" + super.getSenderUserID() + '\'' +
                ", content='" + super.getContent() + '\'' +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }
}
