package com.facebook.common.errorreporting;

import com.facebook.errorreporting.common.exception.NonCrashException;

public final class SoftErrorException extends Exception implements NonCrashException {
    @Override // com.facebook.errorreporting.common.exception.NonCrashException
    public final String getExceptionFriendlyName() {
        return "soft error";
    }

    public SoftErrorException(String str, Throwable th) {
        super(str, th);
    }
}
