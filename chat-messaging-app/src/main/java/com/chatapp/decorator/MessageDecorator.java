package com.chatapp.decorator;

import com.chatapp.model.Message;

public abstract class MessageDecorator implements Message {
    protected final Message wrapped;

    public MessageDecorator(Message wrapped) {
        this.wrapped = wrapped;
    }

    @Override public String getSender() { return wrapped.getSender(); }
    @Override public String getContent() { return wrapped.getContent(); }
    @Override public void setContent(String content) { wrapped.setContent(content); }
    @Override public String getType() { return wrapped.getType(); }

    @Override
    public abstract String preview();
}