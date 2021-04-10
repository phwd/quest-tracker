package com.facebook.common.logging;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FLog {
    public static LoggingDelegate sHandler = FLogDefaultLoggingDelegate.getInstance();
}
