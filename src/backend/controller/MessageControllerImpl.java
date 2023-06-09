package backend.controller;

import backend.database.DataBase;
import backend.model.Chat;
import backend.model.Message;
import backend.payload.MessageRecord;

import java.util.ArrayList;
import java.util.List;

public class MessageControllerImpl implements MessageController {
    @Override
    public List<MessageRecord> getAllMessagesByChatId(String id) {
        ArrayList<MessageRecord> messageRecords = new ArrayList<>();
        for (Message message : DataBase.messageList) {
            MessageRecord messageRecord = new MessageRecord(message.getId(),
                    message.getChat().getId(),
                    message.getMessageBody(),
                    message.getSenderId());
            messageRecords.add(messageRecord);
        }
        return messageRecords;
    }

    @Override
    public void add(MessageRecord messageRecord) {
        Chat chat = DataBase.getChatById(messageRecord.chatId());
        Message message = new Message(messageRecord.id(), chat,
                messageRecord.messageBody(),
                messageRecord.senderId());
        DataBase.messageList.add(message);
    }

    @Override
    public void edit(String id, String nexText) {
        Message message = DataBase.findMessageById(id);
        message.setMessageBody(nexText);
        System.out.println("Successfully edited");
    }

    @Override
    public void delet(String id) {

        Message message = DataBase.findMessageById(id);
        DataBase.messageList.remove(message);


    }
}
