package com.facebook.inject;

import X.AnonymousClass0RM;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface InjectStatic {
    Class<?> inModule() default AnonymousClass0RM.class;
}
