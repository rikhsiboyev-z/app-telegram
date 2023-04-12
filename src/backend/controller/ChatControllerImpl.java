package backend.controller;

import backend.database.DataBase;
import backend.model.Chat;
import backend.model.Message;
import backend.model.User;
import backend.payload.ChatRecord;

import java.util.ArrayList;
import java.util.List;

public class ChatControllerImpl implements ChatController{
    @Override
    public List<ChatRecord> getAllChatsByUserId(String userId) {
        ArrayList<ChatRecord> chatRecords = new ArrayList<>();
        for (Chat chat : DataBase.chatList) {
            if (chat.getSecondSide().getId().equals(userId) ||
                    chat.getFirstSide().getId().equals(userId) ) {
                ChatRecord chatRecord = new ChatRecord(chat.getId(),
                        DataBase.mapUserToRecord(chat.getFirstSide()),
                        DataBase.mapUserToRecord(chat.getSecondSide()));
                chatRecords.add(chatRecord);
            }
        }
        return chatRecords;
    }

    @Override
    public void add(ChatRecord chatRecord) {
        User firstSide = DataBase.findUserById(chatRecord.firstSide().id());
        User secondSide = DataBase.findUserById(chatRecord.secondSide().id());
        Chat chat = new Chat(chatRecord.id(), firstSide, secondSide);
        DataBase.chatList.add(chat);
    }

    @Override
    public void deleteChat(String id) {
            Chat chat = DataBase.findChatById(id);
            Message message = DataBase.findMessageByChatId(id);
            DataBase.messageList.remove(message);
            DataBase.chatList.remove(chat);

    }


}
