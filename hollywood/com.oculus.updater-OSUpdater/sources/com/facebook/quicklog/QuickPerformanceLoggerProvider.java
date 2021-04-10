package com.facebook.quicklog;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.quicklog.QuickPerformanceLogger;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class QuickPerformanceLoggerProvider {
    private static QuickPerformanceLogger mQuickPerformanceLogger;
    private static QuickPerformanceLogger.Builder mQuickPerformanceLoggerBuilder;

    @Nullable
    public static QuickPerformanceLogger getQPLInstance() {
        QuickPerformanceLogger quickPerformanceLogger = mQuickPerformanceLogger;
        if (quickPerformanceLogger != null) {
            return quickPerformanceLogger;
        }
        QuickPerformanceLogger.Builder builder = mQuickPerformanceLoggerBuilder;
        if (builder == null) {
            return null;
        }
        mQuickPerformanceLogger = builder.build();
        return mQuickPerformanceLogger;
    }
}
