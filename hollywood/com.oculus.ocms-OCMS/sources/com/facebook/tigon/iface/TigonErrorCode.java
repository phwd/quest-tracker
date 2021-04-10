package com.facebook.tigon.iface;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TigonErrorCode {
    public static final int CANCEL = 1;
    public static final int FATAL_ERROR = 3;
    public static final int INVALID_REQUEST = 4;
    public static final int NONE = 0;
    public static final int REQUEST_NOT_SUPPORTED = 5;
    public static final int TRANSIENT_ERROR = 2;
}
