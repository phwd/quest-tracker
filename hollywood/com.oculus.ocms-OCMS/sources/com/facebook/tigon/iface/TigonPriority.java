package com.facebook.tigon.iface;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TigonPriority {
    public static final int IMMEDIATE = 0;
    public static final int LOW = 2;
    public static final int NORMAL = 1;
}
