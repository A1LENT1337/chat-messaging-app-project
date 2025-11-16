package com.chatapp.model;

public class ImageMessage implements Message {
    private final String sender;
    private String content;

    public ImageMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    @Override public String getSender() { return sender; }
    @Override public String getContent() { return content; }
    @Override public void setContent(String content) { this.content = content; }

    @Override
    public String preview() {
        return "Image from " + sender + ": [" + content + "]";
    }

    @Override
    public String getType() {
        return "image";
    }
}