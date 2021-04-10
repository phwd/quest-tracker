package com.facebook.mobileconfig.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface MobileConfigs {
    public static final String NOOP = "<NOOP>";
    public static final String SERVICE_NOOP = "<SERVICE_NOOP>";

    String[] value();
}
