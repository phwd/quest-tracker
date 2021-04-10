package com.oculus.secure.trustedapp.exception;

public class SignatureNullException extends SecurityException {
    public SignatureNullException() {
    }

    public SignatureNullException(Exception exc) {
        super(exc);
    }

    public SignatureNullException(String str) {
        super(str);
    }
}
