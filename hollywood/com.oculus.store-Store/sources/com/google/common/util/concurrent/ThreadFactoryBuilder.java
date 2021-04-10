package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.lang.Thread;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@CanIgnoreReturnValue
@GwtIncompatible
public final class ThreadFactoryBuilder {
    private ThreadFactory backingThreadFactory = null;
    private Boolean daemon = null;
    private String nameFormat = null;
    private Integer priority = null;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;

    public ThreadFactoryBuilder setNameFormat(String nameFormat2) {
        format(nameFormat2, 0);
        this.nameFormat = nameFormat2;
        return this;
    }

    public ThreadFactoryBuilder setDaemon(boolean daemon2) {
        this.daemon = Boolean.valueOf(daemon2);
        return this;
    }

    public ThreadFactoryBuilder setPriority(int priority2) {
        boolean z = true;
        Preconditions.checkArgument(priority2 >= 1, "Thread priority (%s) must be >= %s", priority2, 1);
        if (priority2 > 10) {
            z = false;
        }
        Preconditions.checkArgument(z, "Thread priority (%s) must be <= %s", priority2, 10);
        this.priority = Integer.valueOf(priority2);
        return this;
    }

    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler2);
        return this;
    }

    public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory2) {
        this.backingThreadFactory = (ThreadFactory) Preconditions.checkNotNull(backingThreadFactory2);
        return this;
    }

    @CheckReturnValue
    public ThreadFactory build() {
        return doBuild(this);
    }

    private static ThreadFactory doBuild(ThreadFactoryBuilder builder) {
        final ThreadFactory backingThreadFactory2;
        final String nameFormat2 = builder.nameFormat;
        final Boolean daemon2 = builder.daemon;
        final Integer priority2 = builder.priority;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = builder.uncaughtExceptionHandler;
        if (builder.backingThreadFactory != null) {
            backingThreadFactory2 = builder.backingThreadFactory;
        } else {
            backingThreadFactory2 = Executors.defaultThreadFactory();
        }
        final AtomicLong count = nameFormat2 != null ? new AtomicLong(0) : null;
        return new ThreadFactory() {
            /* class com.google.common.util.concurrent.ThreadFactoryBuilder.AnonymousClass1 */

            public Thread newThread(Runnable runnable) {
                Thread thread = backingThreadFactory2.newThread(runnable);
                if (nameFormat2 != null) {
                    thread.setName(ThreadFactoryBuilder.format(nameFormat2, new Object[]{Long.valueOf(count.getAndIncrement())}));
                }
                if (daemon2 != null) {
                    thread.setDaemon(daemon2.booleanValue());
                }
                if (priority2 != null) {
                    thread.setPriority(priority2.intValue());
                }
                if (uncaughtExceptionHandler2 != null) {
                    thread.setUncaughtExceptionHandler(uncaughtExceptionHandler2);
                }
                return thread;
            }
        };
    }

    /* access modifiers changed from: private */
    public static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }
}
