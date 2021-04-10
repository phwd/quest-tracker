package com.fasterxml.jackson.annotation;

import X.AnonymousClass0j0;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {
    String locale() default "##default";

    String pattern() default "";

    AnonymousClass0j0 shape() default AnonymousClass0j0.ANY;

    String timezone() default "##default";
}
