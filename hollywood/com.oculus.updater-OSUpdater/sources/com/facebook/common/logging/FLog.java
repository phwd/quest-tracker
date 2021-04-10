package com.facebook.common.logging;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FLog {
    private static LoggingDelegate sHandler = FLogDefaultLoggingDelegate.getInstance();

    public static void setLoggingDelegate(LoggingDelegate loggingDelegate) {
        if (loggingDelegate != null) {
            sHandler = loggingDelegate;
            return;
        }
        throw new IllegalArgumentException();
    }
}
