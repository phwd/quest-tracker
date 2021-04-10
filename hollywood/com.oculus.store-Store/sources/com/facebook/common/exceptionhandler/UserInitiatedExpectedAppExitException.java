package com.facebook.common.exceptionhandler;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UserInitiatedExpectedAppExitException extends RuntimeException {
    public UserInitiatedExpectedAppExitException(String message) {
        super(message);
    }
}
