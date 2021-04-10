package com.facebook.quicklog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface AnnotationType {
    public static final int TYPE_BOOL = 8;
    public static final int TYPE_BOOL_ARRAY = 9;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_DOUBLE_ARRAY = 7;
    public static final int TYPE_INT = 2;
    public static final int TYPE_INT_ARRAY = 5;
    public static final int TYPE_LONG = 3;
    public static final int TYPE_LONG_ARRAY = 10;
    public static final int TYPE_STRING = 1;
    public static final int TYPE_STRING_ARRAY = 4;
}
