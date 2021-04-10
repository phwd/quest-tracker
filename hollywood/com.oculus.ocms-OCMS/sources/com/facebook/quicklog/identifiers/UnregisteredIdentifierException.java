package com.facebook.quicklog.identifiers;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UnregisteredIdentifierException extends RuntimeException {
    public UnregisteredIdentifierException(String str, int i) {
        super("Tried to use unregistered " + str + ": " + String.valueOf(i));
    }
}
