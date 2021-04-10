package com.instagram.common.json.annotation;

import X.AnonymousClass0Jh;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface JsonType {
    AnonymousClass0Jh generateSerializer() default AnonymousClass0Jh.DEFAULT;

    String serializeCodeFormatter() default "";

    String valueExtractFormatter() default "${subobject_helper_class}.parseFromJson(${parser_object})";
}
