package com.google.gson.interceptors;

public interface JsonPostDeserializer<T> {
    void postDeserialize(T t);
}
