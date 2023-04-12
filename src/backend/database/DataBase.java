package backend.database;

import backend.model.Chat;
import backend.model.Message;
import backend.model.User;
import backend.payload.UserRecord;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<User> userList = new ArrayList<>();
    public static List<Chat> chatList = new ArrayList<>();
    public static List<Message> messageList = new ArrayList<>();

    public static User findUserById(String id) {
        for (User user : userList) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    public static UserRecord mapUserToRecord(User user) {
        return new UserRecord(user.getId(), user.getFirsname(), user.getUsername());
    }

    public static Chat getChatById(String chatId) {
        for (Chat chat : chatList) {
            if (chat.getId().equals(chatId))
                return chat;
        }
        return null;
    }

    public static Message findMessageById(String id) {
        for (Message message : messageList) {
            if (message.getId().equals(id))
                return message;
        }
        return null;
    }

    public static Chat findChatById(String id) {
        for (Chat chat : chatList) {
            if (chat.getId().equals(id))
                return chat;
        }
        return null;
    }

    public static Message findMessageByChatId(String id) {
        for (Message message : messageList) {
            if (message.getChat().equals(id))
                return message;
        }
        return null;
    }
}
