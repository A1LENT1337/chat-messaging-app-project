package com.chatapp.strategy;

import com.chatapp.model.Message;
import com.chatapp.observer.ChatRoom;

public class DirectStrategy implements MessageStrategy {
    @Override
    public String send(Message message, ChatRoom room, String target) {
        if (target != null && !target.isEmpty()) {
            room.notifyUser(target, message);
            return "Direct message sent to " + target + ": " + message.preview();
        } else {
            room.notifyAllUsers(message);
            return "Direct message sent to all (no target): " + message.preview();
        }
    }
}