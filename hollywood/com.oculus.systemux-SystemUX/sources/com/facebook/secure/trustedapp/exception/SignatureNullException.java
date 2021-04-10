package com.facebook.secure.trustedapp.exception;

public class SignatureNullException extends SecurityException {
    public SignatureNullException() {
    }

    public SignatureNullException(String str) {
        super(str);
    }

    public SignatureNullException(Exception exc) {
        super(exc);
    }
}
