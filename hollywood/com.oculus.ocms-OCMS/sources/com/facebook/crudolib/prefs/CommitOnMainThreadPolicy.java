package com.facebook.crudolib.prefs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CommitOnMainThreadPolicy {
    public static final int ALLOW = 0;
    public static final int BLOG = 1;
    public static final int THROW = 2;
}
