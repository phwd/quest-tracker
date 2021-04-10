package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StallException extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "stall";
    }

    public StallException() {
    }

    public StallException(String str) {
        super(str);
    }

    public StallException(String str, Throwable th) {
        super(str, th);
    }
}
