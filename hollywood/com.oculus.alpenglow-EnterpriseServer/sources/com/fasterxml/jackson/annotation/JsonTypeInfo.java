package com.fasterxml.jackson.annotation;

import X.AbstractC05790lK;
import X.EnumC05770lI;
import X.EnumC05780lJ;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {
    Class<?> defaultImpl() default AbstractC05790lK.class;

    EnumC05770lI include() default EnumC05770lI.PROPERTY;

    String property() default "";

    EnumC05780lJ use();

    boolean visible() default false;
}
