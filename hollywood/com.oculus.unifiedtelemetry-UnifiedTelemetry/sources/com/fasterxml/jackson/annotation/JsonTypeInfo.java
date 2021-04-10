package com.fasterxml.jackson.annotation;

import X.EnumC0463pg;
import X.EnumC0464ph;
import X.pi;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {
    Class<?> defaultImpl() default pi.class;

    EnumC0463pg include() default EnumC0463pg.PROPERTY;

    String property() default "";

    EnumC0464ph use();

    boolean visible() default false;
}
