package backend.model;

public class Message {
    private String id;
    private Chat chat;
    private String messageBody;
    private String senderId;

    public Message(String id, Chat chat, String messageBody, String senderId) {
        this.id = id;
        this.chat = chat;
        this.messageBody = messageBody;
        this.senderId = senderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }


}
