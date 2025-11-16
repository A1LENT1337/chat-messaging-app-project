package com.chatapp.decorator;

import com.chatapp.model.Message;

public class EncryptedMessageDecorator extends MessageDecorator {
    public EncryptedMessageDecorator(Message wrapped) {
        super(wrapped);
    }

    private String encrypt(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    @Override
    public String preview() {
        String basePreview = wrapped.preview();
        String encryptedPreview = encrypt(basePreview);
        return "[ENCRYPTED] " + encryptedPreview;
    }

    @Override
    public String getContent() {
        return encrypt(wrapped.getContent());
    }
}
