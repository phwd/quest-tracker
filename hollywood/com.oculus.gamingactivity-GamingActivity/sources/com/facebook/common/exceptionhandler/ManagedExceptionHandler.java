package com.facebook.common.exceptionhandler;

import com.facebook.common.exceptionhandler.CustomStackTracerInterface;
import javax.annotation.Nullable;

public interface ManagedExceptionHandler {
    void handleUncaughtException(Thread thread, Throwable th, @Nullable CustomStackTracerInterface.CustomStackTrace customStackTrace);
}
