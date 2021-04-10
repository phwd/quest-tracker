package com.fasterxml.jackson.annotation;

import X.Cm;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {
    Cm creatorVisibility() default Cm.DEFAULT;

    Cm fieldVisibility() default Cm.DEFAULT;

    Cm getterVisibility() default Cm.DEFAULT;

    Cm isGetterVisibility() default Cm.DEFAULT;

    Cm setterVisibility() default Cm.DEFAULT;
}
