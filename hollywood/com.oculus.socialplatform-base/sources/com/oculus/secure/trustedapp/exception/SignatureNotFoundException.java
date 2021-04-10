package com.oculus.secure.trustedapp.exception;

public class SignatureNotFoundException extends SecurityException {
    public SignatureNotFoundException() {
    }

    public SignatureNotFoundException(Exception exc) {
        super(exc);
    }

    public SignatureNotFoundException(String str) {
        super(str);
    }
}
