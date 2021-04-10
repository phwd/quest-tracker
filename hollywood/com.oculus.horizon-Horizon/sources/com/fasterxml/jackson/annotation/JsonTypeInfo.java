package com.fasterxml.jackson.annotation;

import X.AbstractC04730jM;
import X.EnumC04710jK;
import X.EnumC04720jL;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {
    Class<?> defaultImpl() default AbstractC04730jM.class;

    EnumC04710jK include() default EnumC04710jK.PROPERTY;

    String property() default "";

    EnumC04720jL use();

    boolean visible() default false;
}
