package com.oculus.secure.trustedapp.exception;

public class SignatureNotFoundException extends SecurityException {
    public SignatureNotFoundException() {
    }

    public SignatureNotFoundException(String s) {
        super(s);
    }
}
