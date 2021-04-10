package com.instagram.common.json.annotation;

import X.AnonymousClass1kV;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface JsonType {
    AnonymousClass1kV generateSerializer() default AnonymousClass1kV.DEFAULT;

    String serializeCodeFormatter() default "";

    String valueExtractFormatter() default "${subobject_helper_class}.parseFromJson(${parser_object})";
}
