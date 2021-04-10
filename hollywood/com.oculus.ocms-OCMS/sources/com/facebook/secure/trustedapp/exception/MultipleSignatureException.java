package com.facebook.secure.trustedapp.exception;

public class MultipleSignatureException extends SecurityException {
    public MultipleSignatureException() {
    }

    public MultipleSignatureException(String str) {
        super(str);
    }

    public MultipleSignatureException(Exception exc) {
        super(exc);
    }
}
