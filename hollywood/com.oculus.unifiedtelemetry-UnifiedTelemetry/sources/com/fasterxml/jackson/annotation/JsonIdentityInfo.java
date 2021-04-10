package com.fasterxml.jackson.annotation;

import X.pp;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonIdentityInfo {
    Class<? extends pp<?>> generator();

    String property() default "@id";

    Class<?> scope() default Object.class;
}
