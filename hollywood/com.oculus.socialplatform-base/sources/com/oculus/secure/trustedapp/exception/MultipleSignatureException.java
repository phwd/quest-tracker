package com.oculus.secure.trustedapp.exception;

public class MultipleSignatureException extends SecurityException {
    public MultipleSignatureException() {
    }

    public MultipleSignatureException(Exception exc) {
        super(exc);
    }

    public MultipleSignatureException(String str) {
        super(str);
    }
}
