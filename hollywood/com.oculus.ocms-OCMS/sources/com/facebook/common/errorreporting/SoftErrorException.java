package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

public class SoftErrorException extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "soft error";
    }

    public SoftErrorException(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
