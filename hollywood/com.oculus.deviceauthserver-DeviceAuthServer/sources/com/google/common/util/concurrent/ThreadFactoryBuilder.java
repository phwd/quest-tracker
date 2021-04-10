package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadFactoryBuilder {
    private ThreadFactory backingThreadFactory = null;
    private Boolean daemon = null;
    private String nameFormat = null;
    private Integer priority = null;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;

    public ThreadFactoryBuilder setNameFormat(String nameFormat2) {
        String.format(nameFormat2, 0);
        this.nameFormat = nameFormat2;
        return this;
    }

    public ThreadFactoryBuilder setDaemon(boolean daemon2) {
        this.daemon = Boolean.valueOf(daemon2);
        return this;
    }

    public ThreadFactoryBuilder setPriority(int priority2) {
        Preconditions.checkArgument(priority2 >= 1, "Thread priority (%s) must be >= %s", Integer.valueOf(priority2), 1);
        Preconditions.checkArgument(priority2 <= 10, "Thread priority (%s) must be <= %s", Integer.valueOf(priority2), 10);
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

    public ThreadFactory build() {
        return build(this);
    }

    private static ThreadFactory build(ThreadFactoryBuilder builder) {
        final ThreadFactory backingThreadFactory2;
        final String nameFormat2 = builder.nameFormat;
        final Boolean daemon2 = builder.daemon;
        final Integer priority2 = builder.priority;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = builder.uncaughtExceptionHandler;
        ThreadFactory threadFactory = builder.backingThreadFactory;
        if (threadFactory != null) {
            backingThreadFactory2 = threadFactory;
        } else {
            backingThreadFactory2 = Executors.defaultThreadFactory();
        }
        final AtomicLong count = nameFormat2 != null ? new AtomicLong(0) : null;
        return new ThreadFactory() {
            /* class com.google.common.util.concurrent.ThreadFactoryBuilder.AnonymousClass1 */

            public Thread newThread(Runnable runnable) {
                Thread thread = backingThreadFactory2.newThread(runnable);
                String str = nameFormat2;
                if (str != null) {
                    thread.setName(String.format(str, Long.valueOf(count.getAndIncrement())));
                }
                Boolean bool = daemon2;
                if (bool != null) {
                    thread.setDaemon(bool.booleanValue());
                }
                Integer num = priority2;
                if (num != null) {
                    thread.setPriority(num.intValue());
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = uncaughtExceptionHandler2;
                if (uncaughtExceptionHandler != null) {
                    thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
                }
                return thread;
            }
        };
    }
}
