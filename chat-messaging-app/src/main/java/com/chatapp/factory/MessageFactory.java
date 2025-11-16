package com.chatapp.factory;

import com.chatapp.model.*;

public class MessageFactory {
    public static Message create(String type, String sender, String payload) {
        if ("image".equalsIgnoreCase(type)) {
            return new ImageMessage(sender, payload);
        } else {
            return new TextMessage(sender, payload);
        }
    }
}