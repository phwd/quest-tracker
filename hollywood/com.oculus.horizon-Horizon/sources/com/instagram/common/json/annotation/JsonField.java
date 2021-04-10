package com.instagram.common.json.annotation;

import X.EnumC11521zf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface JsonField {
    String fieldAssignmentFormatter() default "";

    String fieldName();

    EnumC11521zf mapping() default EnumC11521zf.COERCED;

    String serializeCodeFormatter() default "";

    String valueExtractFormatter() default "";
}
