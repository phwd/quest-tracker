package com.facebook.infer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Nullsafe {

    public enum Mode {
        LOCAL,
        STRICT
    }

    public @interface TrustList {
        boolean trustAll() default false;

        Class[] value();
    }

    TrustList trustOnly() default @TrustList(trustAll = true, value = {});

    Mode value();
}
