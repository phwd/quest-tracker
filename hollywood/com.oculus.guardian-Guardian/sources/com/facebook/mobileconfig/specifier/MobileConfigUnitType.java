package com.facebook.mobileconfig.specifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface MobileConfigUnitType {
    public static final int PAGEID = 2;
    public static final int SESSIONBASED = 0;
    public static final int SESSIONLESS = 1;
}
