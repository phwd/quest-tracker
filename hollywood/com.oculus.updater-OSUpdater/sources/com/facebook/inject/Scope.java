package com.facebook.inject;

import com.google.inject.Key;
import javax.inject.Provider;

public interface Scope {
    <T> Provider<T> scope(Key<T> key, Provider<T> provider);
}
