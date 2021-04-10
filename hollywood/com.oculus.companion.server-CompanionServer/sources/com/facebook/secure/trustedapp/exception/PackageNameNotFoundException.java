package com.facebook.secure.trustedapp.exception;

public class PackageNameNotFoundException extends SecurityException {
    public PackageNameNotFoundException() {
    }

    public PackageNameNotFoundException(String str) {
        super(str);
    }
}
