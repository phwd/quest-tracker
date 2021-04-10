package com.oculus.secure.trustedapp.exception;

public class PackageInfoMisMatchException extends SecurityException {
    public PackageInfoMisMatchException() {
    }

    public PackageInfoMisMatchException(String s) {
        super(s);
    }

    public PackageInfoMisMatchException(String packageName1, String packageName2) {
        this("Package name mismatch: expected=" + packageName1 + ", was=" + packageName2);
    }

    public PackageInfoMisMatchException(Exception cause) {
        super(cause);
    }
}
