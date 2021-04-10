package com.facebook.mobileconfig.specifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface MobileConfigParamType {
    public static final int BOOLEAN = 1;
    public static final int DOUBLE = 4;
    public static final int LONG = 2;
    public static final int NULL = 0;
    public static final int STRING = 3;
}
