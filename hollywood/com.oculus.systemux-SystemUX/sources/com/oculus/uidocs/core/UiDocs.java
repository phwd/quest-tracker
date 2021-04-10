package com.oculus.uidocs.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface UiDocs {
    String componentOverride() default "";

    String description() default "";

    String design() default "";

    String version() default "";
}
