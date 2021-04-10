package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

public class SoftErrorException extends Exception implements NonCrashException {
    public SoftErrorException(String detailMessage, @Nullable Throwable throwable) {
        super(detailMessage, throwable);
    }

    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "soft error";
    }
}
