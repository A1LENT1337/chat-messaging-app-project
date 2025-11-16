package com.chatapp.observer;

import com.chatapp.model.Message;

public interface Observer {
    void update(Message message);
    String getName();
    String getLastReceivedMessage();
}