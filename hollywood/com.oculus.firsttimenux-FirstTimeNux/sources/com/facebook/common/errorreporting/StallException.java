package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StallException extends Exception implements NonCrashException {
    public StallException() {
    }

    public StallException(String detailMessage) {
        super(detailMessage);
    }

    public StallException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "stall";
    }
}
