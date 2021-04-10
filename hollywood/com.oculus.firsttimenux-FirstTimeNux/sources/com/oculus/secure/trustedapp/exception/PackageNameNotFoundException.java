package com.oculus.secure.trustedapp.exception;

public class PackageNameNotFoundException extends SecurityException {
    public PackageNameNotFoundException() {
    }

    public PackageNameNotFoundException(Exception cause) {
        super(cause);
    }

    public PackageNameNotFoundException(String s) {
        super(s);
    }
}
