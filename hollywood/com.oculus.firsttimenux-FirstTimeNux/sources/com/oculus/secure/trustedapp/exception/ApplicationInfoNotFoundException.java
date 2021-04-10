package com.oculus.secure.trustedapp.exception;

public class ApplicationInfoNotFoundException extends SecurityException {
    public ApplicationInfoNotFoundException() {
    }

    public ApplicationInfoNotFoundException(String s) {
        super(s);
    }

    public ApplicationInfoNotFoundException(Exception cause) {
        super(cause);
    }
}
