package com.fasterxml.jackson.annotation;

import X.EnumC04680iv;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {
    EnumC04680iv creatorVisibility() default EnumC04680iv.DEFAULT;

    EnumC04680iv fieldVisibility() default EnumC04680iv.DEFAULT;

    EnumC04680iv getterVisibility() default EnumC04680iv.DEFAULT;

    EnumC04680iv isGetterVisibility() default EnumC04680iv.DEFAULT;

    EnumC04680iv setterVisibility() default EnumC04680iv.DEFAULT;
}
