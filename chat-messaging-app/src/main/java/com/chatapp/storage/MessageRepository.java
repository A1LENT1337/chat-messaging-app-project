package com.chatapp.storage;

import com.chatapp.model.Message;
import java.util.*;

public class MessageRepository {
    private final List<Message> messages = new ArrayList<>();

    public void save(Message message) {
        messages.add(message);
    }

    public int getMessageCount() {
        return messages.size();
    }
}