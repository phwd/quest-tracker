package com.fasterxml.jackson.annotation;

import X.AnonymousClass0ns;
import X.EnumC03570nq;
import X.EnumC03580nr;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {
    Class<?> defaultImpl() default AnonymousClass0ns.class;

    EnumC03570nq include() default EnumC03570nq.PROPERTY;

    String property() default "";

    EnumC03580nr use();

    boolean visible() default false;
}
