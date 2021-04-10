package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ANRException extends Exception {
    public ANRException() {
    }

    public ANRException(String detailMessage) {
        super(detailMessage);
    }
}
