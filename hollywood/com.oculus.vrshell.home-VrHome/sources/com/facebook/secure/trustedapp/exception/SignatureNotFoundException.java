package com.facebook.secure.trustedapp.exception;

public class SignatureNotFoundException extends SecurityException {
    public SignatureNotFoundException() {
    }

    public SignatureNotFoundException(String s) {
        super(s);
    }
}
