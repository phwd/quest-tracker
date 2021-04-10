package com.instagram.common.json.annotation;

import X.EnumC01560Jf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface JsonField {
    String fieldAssignmentFormatter() default "";

    String fieldName();

    EnumC01560Jf mapping() default EnumC01560Jf.COERCED;

    String serializeCodeFormatter() default "";

    String valueExtractFormatter() default "";
}
