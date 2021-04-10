package com.facebook.quicklog;

import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@DoNotStrip
@Retention(RetentionPolicy.CLASS)
public @interface EventLevel {
    public static final int DEBUG = 9;
    public static final int ERROR = 3;
    public static final int FATAL = 1;
    public static final int INFO = 7;
    public static final int NONE = 0;
    public static final int WARN = 5;
}
