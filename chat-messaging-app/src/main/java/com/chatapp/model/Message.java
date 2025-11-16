package com.chatapp.model;

public interface Message {
    String getSender();
    String getContent();
    void setContent(String content);
    String preview();
    String getType();
}