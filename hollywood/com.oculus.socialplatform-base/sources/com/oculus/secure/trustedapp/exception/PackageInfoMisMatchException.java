package com.oculus.secure.trustedapp.exception;

import X.AnonymousClass006;

public class PackageInfoMisMatchException extends SecurityException {
    public PackageInfoMisMatchException() {
    }

    public PackageInfoMisMatchException(Exception exc) {
        super(exc);
    }

    public PackageInfoMisMatchException(String str) {
        super(str);
    }

    public PackageInfoMisMatchException(String str, String str2) {
        super(AnonymousClass006.A0B("Package name mismatch: expected=", str, ", was=", str2));
    }
}
