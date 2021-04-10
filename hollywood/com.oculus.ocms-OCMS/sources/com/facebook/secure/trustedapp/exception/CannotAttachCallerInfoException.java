package com.facebook.secure.trustedapp.exception;

public class CannotAttachCallerInfoException extends Exception {
    public CannotAttachCallerInfoException() {
    }

    public CannotAttachCallerInfoException(String str) {
        super(str);
    }

    public CannotAttachCallerInfoException(Exception exc) {
        super(exc);
    }
}
