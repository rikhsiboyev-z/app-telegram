package backend.controller;

import backend.model.Message;
import backend.payload.MessageRecord;

import java.util.List;

public interface MessageController {

    List<MessageRecord> getAllMessagesByChatId(String id);

    void add(MessageRecord messageRecord);
}
