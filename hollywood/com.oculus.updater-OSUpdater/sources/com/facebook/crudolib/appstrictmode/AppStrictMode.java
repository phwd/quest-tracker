package com.facebook.crudolib.appstrictmode;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AppStrictMode {

    @Retention(RetentionPolicy.SOURCE)
    @interface Penalty {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Violation {
    }
}
