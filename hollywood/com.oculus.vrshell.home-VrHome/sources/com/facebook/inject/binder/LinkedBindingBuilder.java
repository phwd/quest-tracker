package com.facebook.inject.binder;

import javax.inject.Provider;

public interface LinkedBindingBuilder<T> extends ScopedBindingBuilder {
    ScopedBindingBuilder toProvider(Provider<? extends T> provider);
}
