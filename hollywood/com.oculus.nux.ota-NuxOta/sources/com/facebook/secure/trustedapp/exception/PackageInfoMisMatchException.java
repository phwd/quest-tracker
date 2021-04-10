package com.facebook.secure.trustedapp.exception;

public class PackageInfoMisMatchException extends SecurityException {
    public PackageInfoMisMatchException() {
    }

    public PackageInfoMisMatchException(String str) {
        super(str);
    }

    public PackageInfoMisMatchException(String str, String str2) {
        this("Package name mismatch: expected=" + str + ", was=" + str2);
    }
}
