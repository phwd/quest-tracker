package com.fasterxml.jackson.annotation;

import X.pH;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {
    pH creatorVisibility() default pH.DEFAULT;

    pH fieldVisibility() default pH.DEFAULT;

    pH getterVisibility() default pH.DEFAULT;

    pH isGetterVisibility() default pH.DEFAULT;

    pH setterVisibility() default pH.DEFAULT;
}
