package com.oculus.errorreporting;

import X.AnonymousClass006;
import X.AnonymousClass0K8;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ErrorReportingExecutorFactory {
    public static ExecutorService sSingleThreadExecutorService;

    public static class NamedThreadFactory implements ThreadFactory {
        public final String mNamePrefix;
        public final AtomicInteger mThreadNumber = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, AnonymousClass006.A03(this.mNamePrefix, this.mThreadNumber.getAndIncrement()));
        }

        public NamedThreadFactory(String str) {
            this.mNamePrefix = str;
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

    public static AnonymousClass0K8 getMonotonicClock() {
        return AwakeTimeSinceBootClock.INSTANCE;
    }
}
