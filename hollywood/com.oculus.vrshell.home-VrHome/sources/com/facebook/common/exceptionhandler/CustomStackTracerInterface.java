package com.facebook.common.exceptionhandler;

import javax.annotation.Nullable;

public interface CustomStackTracerInterface {

    public interface CustomStackTrace {
    }

    @Nullable
    CustomStackTrace collectStackTraceForException(Throwable th);
}
