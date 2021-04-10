package com.facebook.inject;

import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

public interface Scope {
    Class<? extends Annotation> annotationType();

    <T> Provider<T> scope(Key<T> key, Provider<T> provider);
}
