package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ANRException extends Exception {
    public ANRException() {
    }

    public ANRException(String str) {
        super(str);
    }
}
