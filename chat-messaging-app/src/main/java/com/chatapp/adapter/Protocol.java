package com.chatapp.adapter;

public interface Protocol {
    void connect();
    String send(String payload);
    void disconnect();
}