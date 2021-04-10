package com.facebook.common.logging;

public interface LoggingDelegate {
    void e(String str, String str2);

    boolean isLoggable(int i);

    void setMinimumLoggingLevel(int i);

    void w(String str, String str2);
}
