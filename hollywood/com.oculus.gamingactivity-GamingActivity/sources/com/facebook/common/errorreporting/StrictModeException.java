package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StrictModeException extends Exception implements NonCrashException {
    public StrictModeException() {
    }

    public StrictModeException(String detailMessage) {
        super(detailMessage);
    }

    public StrictModeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "strict mode violation";
    }
}
