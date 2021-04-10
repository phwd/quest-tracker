package com.facebook.inject;

import javax.inject.Provider;

public interface Lazy<T> extends Provider<T> {
    @Override // javax.inject.Provider
    T get();
}
