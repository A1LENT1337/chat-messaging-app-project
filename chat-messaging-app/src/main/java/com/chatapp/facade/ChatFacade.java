package com.chatapp.facade;

import com.chatapp.adapter.*;
import com.chatapp.decorator.*;
import com.chatapp.factory.MessageFactory;
import com.chatapp.model.Message;
import com.chatapp.observer.*;
import com.chatapp.strategy.*;
import com.chatapp.storage.MessageRepository;

public class ChatFacade {
    private final ChatRoom room = new ChatRoom();
    private Protocol protocol = new WebSocketAdapter();
    private MessageStrategy strategy = new GroupStrategy();
    private final MessageRepository repo = new MessageRepository();
    private boolean encryptEnabled = false;
    private String lastOperation;

    public void registerUser(User user) {
        room.join(user);
        lastOperation = "User registered: " + user.getName();
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
        lastOperation = "Protocol set to: " + protocol.getClass().getSimpleName();
    }

    public void setStrategy(MessageStrategy strategy) {
        this.strategy = strategy;
        lastOperation = "Strategy set to: " + strategy.getClass().getSimpleName();
    }

    public void enableEncryption(boolean enabled) {
        this.encryptEnabled = enabled;
        lastOperation = "Encryption " + (enabled ? "enabled" : "disabled");
    }

    public String send(String type, String payload, String target) {
        Message message = createAndDecorateMessage(type, payload);
        return saveAndSendMessage(message, target);
    }

    private Message createAndDecorateMessage(String type, String payload) {
        Message message = MessageFactory.create(type, "System", payload);

        if ("image".equalsIgnoreCase(type)) {
            message = new EmojiDecorator(message);
        }

        if (encryptEnabled) {
            message = new EncryptedMessageDecorator(message);
        }

        return message;
    }

    private String saveAndSendMessage(Message message, String target) {
        repo.save(message);

        String sendResult;
        try {
            protocol.connect();
            try {
                sendResult = protocol.send(message.preview());
            } finally {
                protocol.disconnect();
            }
        } catch (Exception ex) {
            lastOperation = "Send failed: " + ex.getMessage();
            return "Send operation failed: " + ex.getMessage();
        }

        String strategyResult;
        try {
            strategyResult = strategy.send(message, room, target);
        } catch (Exception ex) {
            lastOperation = "Dispatch failed: " + ex.getMessage();
            return "Dispatch operation failed: " + ex.getMessage();
        }

        lastOperation = "Message sent successfully: " + message.preview();
        return "Send operation completed:\n- " + sendResult + "\n- " + strategyResult;
    }

    public MessageRepository getRepository() {
        return repo;
    }

    public String getLastOperation() {
        return lastOperation;
    }
}
