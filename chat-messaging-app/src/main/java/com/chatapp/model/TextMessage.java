package com.chatapp.model;

public class TextMessage implements Message {
    private final String sender;
    private String content;

    public TextMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    @Override public String getSender() { return sender; }
    @Override public String getContent() { return content; }
    @Override public void setContent(String content) { this.content = content; }

    @Override
    public String preview() {
        return "Text from " + sender + ": " + content;
    }

    @Override
    public String getType() {
        return "text";
    }
}