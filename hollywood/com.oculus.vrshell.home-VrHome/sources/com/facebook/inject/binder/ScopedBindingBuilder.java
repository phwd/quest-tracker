package com.facebook.inject.binder;

import java.lang.annotation.Annotation;

public interface ScopedBindingBuilder {
    void in(Class<? extends Annotation> cls);
}
