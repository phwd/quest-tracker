package com.facebook.secure.trustedapp.exception;

public class MultipleSignatureException extends SecurityException {
    public MultipleSignatureException() {
    }

    public MultipleSignatureException(String s) {
        super(s);
    }
}
