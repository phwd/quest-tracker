package com.facebook.crudolib.prefs;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UnknownVersionException extends Exception {
    public UnknownVersionException(String str) {
        super(str);
    }
}
