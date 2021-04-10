package com.oculus.errorreporting;

import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.common.time.MonotonicClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ErrorReportingExecutorFactory {
    private static ExecutorService sSingleThreadExecutorService;

    /* access modifiers changed from: private */
    public static class NamedThreadFactory implements ThreadFactory {
        private final String mNamePrefix;
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);

        public NamedThreadFactory(String str) {
            this.mNamePrefix = str;
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.mNamePrefix + this.mThreadNumber.getAndIncrement());
        }
    }

    public static ExecutorService getSingleThreadExecutorService() {
        synchronized (ErrorReportingExecutorFactory.class) {
            if (sSingleThreadExecutorService == null) {
                sSingleThreadExecutorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("ErrorReportingThread-"));
            }
        }
        return sSingleThreadExecutorService;
    }

    public static MonotonicClock getMonotonicClock() {
        return AwakeTimeSinceBootClock.get();
    }
}
