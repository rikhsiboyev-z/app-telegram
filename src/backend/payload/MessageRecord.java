package backend.payload;

public record MessageRecord (String id, String chatId, String messageBody, String senderId) {
}
