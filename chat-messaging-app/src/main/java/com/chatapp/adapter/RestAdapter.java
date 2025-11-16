package com.chatapp.adapter;

public class RestAdapter implements Protocol {
    private String lastAction;

    @Override
    public void connect() {
        lastAction = "[REST] ready";
    }

    @Override
    public String send(String payload) {
        lastAction = "[REST] POST: " + payload;
        return lastAction;
    }

    @Override
    public void disconnect() {
        lastAction = "[REST] done";
    }

}