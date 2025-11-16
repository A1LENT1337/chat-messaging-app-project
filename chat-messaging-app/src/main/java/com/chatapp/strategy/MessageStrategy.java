package com.chatapp.strategy;

import com.chatapp.model.Message;
import com.chatapp.observer.ChatRoom;

public interface MessageStrategy {
    String send(Message message, ChatRoom room, String target);
}