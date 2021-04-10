package com.facebook.inject.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface InjectedClass {
    Class bindingAnnotation() default Void.class;

    Class value();
}
