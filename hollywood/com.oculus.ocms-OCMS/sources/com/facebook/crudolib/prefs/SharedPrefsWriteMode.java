package com.facebook.crudolib.prefs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SharedPrefsWriteMode {
    public static final int WRITE_BACK = 0;
    public static final int WRITE_THROUGH = 1;
}
