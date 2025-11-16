package com.chatapp.observer;

import com.chatapp.model.Message;
import java.util.*;

public class ChatRoom {
    private final List<Observer> users = new ArrayList<>();

    public void join(Observer user) {
        users.add(user);
    }

    public void notifyAllUsers(Message message) {
        for (Observer user : new ArrayList<>(users)) {
            user.update(message);
        }
    }

    public void notifyUser(String userName, Message message) {
        users.stream()
                .filter(user -> user.getName().equals(userName))
                .findFirst()
                .ifPresent(user -> user.update(message));
    }
}