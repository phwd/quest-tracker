package com.facebook.inject.binder;

import java.lang.annotation.Annotation;

public interface ScopedBindingBuilder {
    void asContextLocal();

    void asSingleton();

    void in(Class<? extends Annotation> cls);
}
