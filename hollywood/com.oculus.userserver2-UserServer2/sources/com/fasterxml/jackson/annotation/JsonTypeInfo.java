package com.fasterxml.jackson.annotation;

import X.Ac;
import X.Ad;
import X.Ae;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {
    Class<?> defaultImpl() default Ac.class;

    Ae include() default Ae.PROPERTY;

    String property() default "";

    Ad use();
}
