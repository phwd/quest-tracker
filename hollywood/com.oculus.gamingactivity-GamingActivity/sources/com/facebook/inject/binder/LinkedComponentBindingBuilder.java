package com.facebook.inject.binder;

import com.facebook.inject.ComponentProvider;

public interface LinkedComponentBindingBuilder<T> {
    void toProvider(ComponentProvider<T> componentProvider);
}
