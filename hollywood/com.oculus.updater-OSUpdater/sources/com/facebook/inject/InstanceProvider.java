package com.facebook.inject;

import javax.inject.Provider;

public class InstanceProvider<T> implements Provider<T> {
    private final T mInstance;

    public InstanceProvider(T t) {
        this.mInstance = t;
    }

    @Override // javax.inject.Provider
    public T get() {
        return this.mInstance;
    }
}
