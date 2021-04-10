package com.facebook.inject;

public interface MultiBindIndexedProvider<T> {
    T provide(Injector injector, int i);

    int size();
}
