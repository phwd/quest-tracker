package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(@NullableDecl String message) {
        super(message);
    }

    public VerifyException(@NullableDecl Throwable cause) {
        super(cause);
    }

    public VerifyException(@NullableDecl String message, @NullableDecl Throwable cause) {
        super(message, cause);
    }
}
