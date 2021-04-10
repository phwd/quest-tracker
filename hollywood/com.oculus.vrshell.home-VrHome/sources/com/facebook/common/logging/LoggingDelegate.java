package com.facebook.common.logging;

public interface LoggingDelegate {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    int getMinimumLoggingLevel();

    void i(String str, String str2);

    void i(String str, String str2, Throwable th);

    boolean isLoggable(int i);

    void setMinimumLoggingLevel(int i);

    void v(String str, String str2);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);
}
