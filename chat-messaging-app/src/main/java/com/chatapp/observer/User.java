package com.chatapp.observer;

import com.chatapp.model.Message;

public class User implements Observer {
    private final String name;
    private String lastReceivedMessage;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(Message message) {
        this.lastReceivedMessage = message.preview();
    }

    @Override
    public String getLastReceivedMessage() {
        return lastReceivedMessage;
    }

}