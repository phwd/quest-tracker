package com.facebook.secure.trustedapp.exception;

public class ApplicationInfoNotFoundException extends SecurityException {
    public ApplicationInfoNotFoundException() {
    }

    public ApplicationInfoNotFoundException(String str) {
        super(str);
    }
}
