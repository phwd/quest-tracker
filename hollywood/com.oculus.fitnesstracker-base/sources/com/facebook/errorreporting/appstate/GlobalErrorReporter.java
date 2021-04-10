package com.facebook.errorreporting.appstate;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class GlobalErrorReporter {
    private static IReporter sInstance;

    public interface IReporter {
    }

    public static synchronized void setInstance(IReporter iReporter) {
        synchronized (GlobalErrorReporter.class) {
            sInstance = iReporter;
        }
    }
}
