package com.facebook.common.errorreporting;

import com.facebook.acra.NonCrashException;
import javax.annotation.Nullable;

public class ReactNativeException extends Exception implements NonCrashException {
    public ReactNativeException(String detailMessage, @Nullable Throwable throwable) {
        super(detailMessage, throwable);
    }

    @Override // com.facebook.acra.NonCrashException
    public String getExceptionFriendlyName() {
        return "react native exception";
    }
}
