package com.chatapp.adapter;

public class WebSocketAdapter implements Protocol {
    private String lastAction;

    @Override
    public void connect() {
        lastAction = "[WebSocket] connected";
    }

    @Override
    public String send(String payload) {
        lastAction = "[WebSocket] send: " + payload;
        return lastAction;
    }

    @Override
    public void disconnect() {
        lastAction = "[WebSocket] disconnected";
    }
}