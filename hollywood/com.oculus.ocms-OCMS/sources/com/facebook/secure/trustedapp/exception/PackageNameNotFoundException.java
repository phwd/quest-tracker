package com.facebook.secure.trustedapp.exception;

public class PackageNameNotFoundException extends SecurityException {
    public PackageNameNotFoundException() {
    }

    public PackageNameNotFoundException(Exception exc) {
        super(exc);
    }

    public PackageNameNotFoundException(String str) {
        super(str);
    }
}
