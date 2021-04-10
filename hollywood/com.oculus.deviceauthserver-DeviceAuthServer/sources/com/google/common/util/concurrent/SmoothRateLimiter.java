package com.google.common.util.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    /* access modifiers changed from: package-private */
    public abstract void doSetRate(double d, double d2);

    /* access modifiers changed from: package-private */
    public abstract long storedPermitsToWaitTime(double d, double d2);

    static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double halfPermits;
        private double slope;
        private final long warmupPeriodMicros;

        SmoothWarmingUp(RateLimiter.SleepingStopwatch stopwatch, long warmupPeriod, TimeUnit timeUnit) {
            super(stopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(warmupPeriod);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
            double d;
            double oldMaxPermits = this.maxPermits;
            this.maxPermits = ((double) this.warmupPeriodMicros) / stableIntervalMicros;
            this.halfPermits = this.maxPermits / 2.0d;
            this.slope = ((3.0d * stableIntervalMicros) - stableIntervalMicros) / this.halfPermits;
            if (oldMaxPermits == Double.POSITIVE_INFINITY) {
                this.storedPermits = 0.0d;
                return;
            }
            if (oldMaxPermits == 0.0d) {
                d = this.maxPermits;
            } else {
                d = (this.storedPermits * this.maxPermits) / oldMaxPermits;
            }
            this.storedPermits = d;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
            double availablePermitsAboveHalf = storedPermits - this.halfPermits;
            long micros = 0;
            if (availablePermitsAboveHalf > 0.0d) {
                double permitsAboveHalfToTake = Math.min(availablePermitsAboveHalf, permitsToTake);
                micros = (long) (((permitsToTime(availablePermitsAboveHalf) + permitsToTime(availablePermitsAboveHalf - permitsAboveHalfToTake)) * permitsAboveHalfToTake) / 2.0d);
                permitsToTake -= permitsAboveHalfToTake;
            }
            return (long) (((double) micros) + (this.stableIntervalMicros * permitsToTake));
        }

        private double permitsToTime(double permits) {
            return this.stableIntervalMicros + (this.slope * permits);
        }
    }

    static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        SmoothBursty(RateLimiter.SleepingStopwatch stopwatch, double maxBurstSeconds2) {
            super(stopwatch);
            this.maxBurstSeconds = maxBurstSeconds2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
            double oldMaxPermits = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * permitsPerSecond;
            if (oldMaxPermits == Double.POSITIVE_INFINITY) {
                this.storedPermits = this.maxPermits;
                return;
            }
            double d = 0.0d;
            if (oldMaxPermits != 0.0d) {
                d = (this.storedPermits * this.maxPermits) / oldMaxPermits;
            }
            this.storedPermits = d;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
            return 0;
        }
    }

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch stopwatch) {
        super(stopwatch);
        this.nextFreeTicketMicros = 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.util.concurrent.RateLimiter
    public final void doSetRate(double permitsPerSecond, long nowMicros) {
        resync(nowMicros);
        double stableIntervalMicros2 = ((double) TimeUnit.SECONDS.toMicros(1)) / permitsPerSecond;
        this.stableIntervalMicros = stableIntervalMicros2;
        doSetRate(permitsPerSecond, stableIntervalMicros2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.util.concurrent.RateLimiter
    public final double doGetRate() {
        return ((double) TimeUnit.SECONDS.toMicros(1)) / this.stableIntervalMicros;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.util.concurrent.RateLimiter
    public final long queryEarliestAvailable(long nowMicros) {
        return this.nextFreeTicketMicros;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.util.concurrent.RateLimiter
    public final long reserveEarliestAvailable(int requiredPermits, long nowMicros) {
        resync(nowMicros);
        long returnValue = this.nextFreeTicketMicros;
        double storedPermitsToSpend = Math.min((double) requiredPermits, this.storedPermits);
        this.nextFreeTicketMicros += storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend) + ((long) (this.stableIntervalMicros * (((double) requiredPermits) - storedPermitsToSpend)));
        this.storedPermits -= storedPermitsToSpend;
        return returnValue;
    }

    private void resync(long nowMicros) {
        long j = this.nextFreeTicketMicros;
        if (nowMicros > j) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + (((double) (nowMicros - j)) / this.stableIntervalMicros));
            this.nextFreeTicketMicros = nowMicros;
        }
    }
}
