package com.chatapp;

import com.chatapp.adapter.*;
import com.chatapp.facade.ChatFacade;
import com.chatapp.observer.User;
import com.chatapp.strategy.*;

public class Main {
    public static void main(String[] args) {
        ChatFacade facade = new ChatFacade();

        User anna = new User("Anna");
        User ivan = new User("Ivan");
        facade.registerUser(anna);
        facade.registerUser(ivan);

        System.out.println("=== Demo 1: Group text message via WebSocket ===");
        String result1 = facade.send("text", "Hello everyone!", null);
        System.out.println(result1);
        printUserMessages(anna, ivan);

        System.out.println("\n=== Demo 2: Direct encrypted message ===");
        facade.setStrategy(new DirectStrategy());
        facade.enableEncryption(true);
        String result2 = facade.send("text", "Secret to Ivan", "Ivan");
        System.out.println(result2);
        printUserMessages(anna, ivan);

        System.out.println("\n=== Demo 3: Image with emoji decorator via REST ===");
        facade.setProtocol(new RestAdapter());
        facade.enableEncryption(false);
        String result3 = facade.send("image", "photo1.jpg", null);
        System.out.println(result3);
        printUserMessages(anna, ivan);

        System.out.println("\n=== Statistics ===");
        System.out.println("Total messages: " + facade.getRepository().getMessageCount());
        System.out.println("Last operation: " + facade.getLastOperation());
    }

    private static void printUserMessages(User... users) {
        System.out.println("--- User Messages ---");
        for (User user : users) {
            String message = user.getLastReceivedMessage();
            if (message != null) {
                System.out.println("[" + user.getName() + "] received: " + message);
            }
        }
    }
}