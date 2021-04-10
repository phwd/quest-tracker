package com.oculus.secure.trustedapp.exception;

public class PackageInfoNullException extends SecurityException {
    public PackageInfoNullException() {
    }

    public PackageInfoNullException(Exception exc) {
        super(exc);
    }

    public PackageInfoNullException(String str) {
        super(str);
    }
}
