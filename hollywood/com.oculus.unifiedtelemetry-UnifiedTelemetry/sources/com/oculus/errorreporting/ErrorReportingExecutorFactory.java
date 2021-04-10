package com.oculus.errorreporting;

import X.AnonymousClass06;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ErrorReportingExecutorFactory {
    public static ExecutorService sSingleThreadExecutorService;

    public static class NamedThreadFactory implements ThreadFactory {
        public final String mNamePrefix = "ErrorReportingThread-";
        public final AtomicInteger mThreadNumber = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, AnonymousClass06.A01(this.mNamePrefix, this.mThreadNumber.getAndIncrement()));
        }
    }
}
