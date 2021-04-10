package com.oculus.secure.trustedapp.exception;

public class ApplicationInfoNotFoundException extends SecurityException {
    public ApplicationInfoNotFoundException() {
    }

    public ApplicationInfoNotFoundException(Exception exc) {
        super(exc);
    }

    public ApplicationInfoNotFoundException(String str) {
        super(str);
    }
}
