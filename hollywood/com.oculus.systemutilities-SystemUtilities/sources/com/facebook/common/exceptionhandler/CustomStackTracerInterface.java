package com.facebook.common.exceptionhandler;

public interface CustomStackTracerInterface {

    public interface CustomStackTrace {
    }

    CustomStackTrace collectStackTraceForException(Throwable th);
}
