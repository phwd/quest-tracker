package com.facebook.common.exceptionhandler;

import com.facebook.common.exceptionhandler.CustomStackTracerInterface;

public interface ManagedExceptionHandler {
    void handleUncaughtException(Thread thread, Throwable th, CustomStackTracerInterface.CustomStackTrace customStackTrace);
}
