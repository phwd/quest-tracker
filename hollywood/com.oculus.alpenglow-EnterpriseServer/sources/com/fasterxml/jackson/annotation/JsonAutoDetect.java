package com.fasterxml.jackson.annotation;

import X.EnumC05730kt;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {
    EnumC05730kt creatorVisibility() default EnumC05730kt.DEFAULT;

    EnumC05730kt fieldVisibility() default EnumC05730kt.DEFAULT;

    EnumC05730kt getterVisibility() default EnumC05730kt.DEFAULT;

    EnumC05730kt isGetterVisibility() default EnumC05730kt.DEFAULT;

    EnumC05730kt setterVisibility() default EnumC05730kt.DEFAULT;
}
