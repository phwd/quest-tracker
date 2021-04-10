package com.facebook.dracula.api;

import X.AnonymousClass0Nw;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Instantiate {
    Class<?> implementation() default AnonymousClass0Nw.class;

    Class<?> wrapper() default AnonymousClass0Nw.class;
}
