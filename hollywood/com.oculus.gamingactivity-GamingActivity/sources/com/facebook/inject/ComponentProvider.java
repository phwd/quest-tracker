package com.facebook.inject;

public interface ComponentProvider<T> {
    void inject(T t);
}
