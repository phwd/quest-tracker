package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

public class ReactNativeException extends Exception implements NonCrashException {
    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "react native exception";
    }

    public ReactNativeException(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
