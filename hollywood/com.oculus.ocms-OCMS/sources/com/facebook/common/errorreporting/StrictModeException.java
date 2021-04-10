package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StrictModeException extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "strict mode violation";
    }

    public StrictModeException() {
    }

    public StrictModeException(String str) {
        super(str);
    }

    public StrictModeException(String str, Throwable th) {
        super(str, th);
    }
}
