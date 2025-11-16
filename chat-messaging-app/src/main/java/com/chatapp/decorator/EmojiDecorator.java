package com.chatapp.decorator;

import com.chatapp.model.Message;

public class EmojiDecorator extends MessageDecorator {
    public EmojiDecorator(Message wrapped) {
        super(wrapped);
    }

    @Override
    public String preview() {
        return "[Emoji] " + wrapped.preview() + " [Decorated]";
    }
}