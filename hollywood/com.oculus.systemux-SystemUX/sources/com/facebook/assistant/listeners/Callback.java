package com.facebook.assistant.listeners;

public interface Callback<T> {
    boolean run(T t);
}
