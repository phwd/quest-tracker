package com.facebook.assistant.listeners;

public interface Post<T> {
    void run(T t);
}
