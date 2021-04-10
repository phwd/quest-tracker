package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;

@Beta
@ThreadSafe
public abstract class RateLimiter {
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    /* access modifiers changed from: package-private */
    public abstract double doGetRate();

    /* access modifiers changed from: package-private */
    public abstract void doSetRate(double d, long j);

    /* access modifiers changed from: package-private */
    public abstract long queryEarliestAvailable(long j);

    /* access modifiers changed from: package-private */
    public abstract long reserveEarliestAvailable(int i, long j);

    public static RateLimiter create(double permitsPerSecond) {
        return create(SleepingStopwatch.createFromSystemTimer(), permitsPerSecond);
    }

    @VisibleForTesting
    static RateLimiter create(SleepingStopwatch stopwatch2, double permitsPerSecond) {
        RateLimiter rateLimiter = new SmoothRateLimiter.SmoothBursty(stopwatch2, 1.0d);
        rateLimiter.setRate(permitsPerSecond);
        return rateLimiter;
    }

    public static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit) {
        Preconditions.checkArgument(warmupPeriod >= 0, "warmupPeriod must not be negative: %s", Long.valueOf(warmupPeriod));
        return create(SleepingStopwatch.createFromSystemTimer(), permitsPerSecond, warmupPeriod, unit);
    }

    @VisibleForTesting
    static RateLimiter create(SleepingStopwatch stopwatch2, double permitsPerSecond, long warmupPeriod, TimeUnit unit) {
        RateLimiter rateLimiter = new SmoothRateLimiter.SmoothWarmingUp(stopwatch2, warmupPeriod, unit);
        rateLimiter.setRate(permitsPerSecond);
        return rateLimiter;
    }

    private Object mutex() {
        Object mutex = this.mutexDoNotUseDirectly;
        if (mutex == null) {
            synchronized (this) {
                mutex = this.mutexDoNotUseDirectly;
                if (mutex == null) {
                    Object obj = new Object();
                    mutex = obj;
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return mutex;
    }

    RateLimiter(SleepingStopwatch stopwatch2) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(stopwatch2);
    }

    public final void setRate(double permitsPerSecond) {
        Preconditions.checkArgument(permitsPerSecond > 0.0d && !Double.isNaN(permitsPerSecond), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(permitsPerSecond, this.stopwatch.readMicros());
        }
    }

    public final double getRate() {
        double doGetRate;
        synchronized (mutex()) {
            doGetRate = doGetRate();
        }
        return doGetRate;
    }

    public double acquire() {
        return acquire(1);
    }

    public double acquire(int permits) {
        long microsToWait = reserve(permits);
        this.stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return (((double) microsToWait) * 1.0d) / ((double) TimeUnit.SECONDS.toMicros(1));
    }

    /* access modifiers changed from: package-private */
    public final long reserve(int permits) {
        long reserveAndGetWaitLength;
        checkPermits(permits);
        synchronized (mutex()) {
            reserveAndGetWaitLength = reserveAndGetWaitLength(permits, this.stopwatch.readMicros());
        }
        return reserveAndGetWaitLength;
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) {
        return tryAcquire(1, timeout, unit);
    }

    public boolean tryAcquire(int permits) {
        return tryAcquire(permits, 0, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) {
        long timeoutMicros = Math.max(unit.toMicros(timeout), 0L);
        checkPermits(permits);
        synchronized (mutex()) {
            long nowMicros = this.stopwatch.readMicros();
            if (!canAcquire(nowMicros, timeoutMicros)) {
                return false;
            }
            this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength(permits, nowMicros));
            return true;
        }
    }

    private boolean canAcquire(long nowMicros, long timeoutMicros) {
        return queryEarliestAvailable(nowMicros) - timeoutMicros <= nowMicros;
    }

    /* access modifiers changed from: package-private */
    public final long reserveAndGetWaitLength(int permits, long nowMicros) {
        return Math.max(reserveEarliestAvailable(permits, nowMicros) - nowMicros, 0L);
    }

    public String toString() {
        return String.format("RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static abstract class SleepingStopwatch {
        /* access modifiers changed from: package-private */
        public abstract long readMicros();

        /* access modifiers changed from: package-private */
        public abstract void sleepMicrosUninterruptibly(long j);

        SleepingStopwatch() {
        }

        static final SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() {
                /* class com.google.common.util.concurrent.RateLimiter.SleepingStopwatch.AnonymousClass1 */
                final Stopwatch stopwatch = Stopwatch.createStarted();

                /* access modifiers changed from: package-private */
                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                public long readMicros() {
                    return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                public void sleepMicrosUninterruptibly(long micros) {
                    if (micros > 0) {
                        Uninterruptibles.sleepUninterruptibly(micros, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }
    }

    private static int checkPermits(int permits) {
        Preconditions.checkArgument(permits > 0, "Requested permits (%s) must be positive", Integer.valueOf(permits));
        return permits;
    }
}
