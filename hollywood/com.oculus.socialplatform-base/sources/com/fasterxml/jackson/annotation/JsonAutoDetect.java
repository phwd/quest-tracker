package com.fasterxml.jackson.annotation;

import X.AnonymousClass0nR;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {
    AnonymousClass0nR creatorVisibility() default AnonymousClass0nR.DEFAULT;

    AnonymousClass0nR fieldVisibility() default AnonymousClass0nR.DEFAULT;

    AnonymousClass0nR getterVisibility() default AnonymousClass0nR.DEFAULT;

    AnonymousClass0nR isGetterVisibility() default AnonymousClass0nR.DEFAULT;

    AnonymousClass0nR setterVisibility() default AnonymousClass0nR.DEFAULT;
}
