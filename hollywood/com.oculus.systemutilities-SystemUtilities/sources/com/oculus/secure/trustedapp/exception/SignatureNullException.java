package com.oculus.secure.trustedapp.exception;

public class SignatureNullException extends SecurityException {
    public SignatureNullException() {
    }

    public SignatureNullException(String s) {
        super(s);
    }
}
