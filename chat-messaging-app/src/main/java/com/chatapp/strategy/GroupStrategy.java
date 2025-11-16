package com.chatapp.strategy;

import com.chatapp.model.Message;
import com.chatapp.observer.ChatRoom;

public class GroupStrategy implements MessageStrategy {
    @Override
    public String send(Message message, ChatRoom room, String target) {
        room.notifyAllUsers(message);
        return "Group message sent to all users: " + message.preview();
    }
}