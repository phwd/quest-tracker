package com.facebook.inject.binder;

import java.lang.annotation.Annotation;

public interface AnnotatedBindingBuilder<T> extends LinkedBindingBuilder<T> {
    LinkedBindingBuilder<T> annotatedWith(Class<? extends Annotation> cls);

    LinkedBindingBuilder<T> annotatedWith(Annotation annotation);
}
