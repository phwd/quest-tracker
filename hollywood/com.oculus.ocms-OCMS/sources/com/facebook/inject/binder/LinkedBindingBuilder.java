package com.facebook.inject.binder;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

public interface LinkedBindingBuilder<T> extends ScopedBindingBuilder {
    ScopedBindingBuilder to(Key<? extends T> key);

    ScopedBindingBuilder to(TypeLiteral<? extends T> typeLiteral);

    ScopedBindingBuilder to(Class<? extends T> cls);

    ScopedBindingBuilder to(Class<? extends T> cls, Class<? extends Annotation> cls2);

    void toInstance(T t);

    ScopedBindingBuilder toProvider(Provider<? extends T> provider);
}
