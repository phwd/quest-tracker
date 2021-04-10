package com.oculus.uidocs.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface UiDocsSample {
    String component();

    boolean darkBackground() default false;

    String description() default "";

    String design() default "";

    boolean hideCode() default false;

    int index() default -1;

    String layoutFile() default "";

    String title();
}
