package backend.controller;

import backend.payload.ChatRecord;

import java.util.List;

public interface ChatController {

    List<ChatRecord> getAllChatsByUserId(String userId);

    void add(ChatRecord chatRecord);
}
