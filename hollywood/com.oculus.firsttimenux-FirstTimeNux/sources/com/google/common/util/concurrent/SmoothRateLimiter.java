package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    /* access modifiers changed from: package-private */
    public abstract double coolDownIntervalMicros();

    /* access modifiers changed from: package-private */
    public abstract void doSetRate(double d, double d2);

    /* access modifiers changed from: package-private */
    public abstract long storedPermitsToWaitTime(double d, double d2);

    static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        SmoothWarmingUp(RateLimiter.SleepingStopwatch stopwatch, long warmupPeriod, TimeUnit timeUnit, double coldFactor2) {
            super(stopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(warmupPeriod);
            this.coldFactor = coldFactor2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
            double d;
            double oldMaxPermits = this.maxPermits;
            double coldIntervalMicros = stableIntervalMicros * this.coldFactor;
            this.thresholdPermits = (0.5d * ((double) this.warmupPeriodMicros)) / stableIntervalMicros;
            this.maxPermits = this.thresholdPermits + ((2.0d * ((double) this.warmupPeriodMicros)) / (stableIntervalMicros + coldIntervalMicros));
            this.slope = (coldIntervalMicros - stableIntervalMicros) / (this.maxPermits - this.thresholdPermits);
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
            double availablePermitsAboveThreshold = storedPermits - this.thresholdPermits;
            long micros = 0;
            if (availablePermitsAboveThreshold > 0.0d) {
                double permitsAboveThresholdToTake = Math.min(availablePermitsAboveThreshold, permitsToTake);
                micros = (long) ((permitsAboveThresholdToTake * (permitsToTime(availablePermitsAboveThreshold) + permitsToTime(availablePermitsAboveThreshold - permitsAboveThresholdToTake))) / 2.0d);
                permitsToTake -= permitsAboveThresholdToTake;
            }
            return micros + ((long) (this.stableIntervalMicros * permitsToTake));
        }

        private double permitsToTime(double permits) {
            return this.stableIntervalMicros + (this.slope * permits);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return ((double) this.warmupPeriodMicros) / this.maxPermits;
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
            double d = 0.0d;
            double oldMaxPermits = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * permitsPerSecond;
            if (oldMaxPermits == Double.POSITIVE_INFINITY) {
                this.storedPermits = this.maxPermits;
                return;
            }
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

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
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
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend) + ((long) (this.stableIntervalMicros * (((double) requiredPermits) - storedPermitsToSpend))));
        this.storedPermits -= storedPermitsToSpend;
        return returnValue;
    }

    /* access modifiers changed from: package-private */
    public void resync(long nowMicros) {
        if (nowMicros > this.nextFreeTicketMicros) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + (((double) (nowMicros - this.nextFreeTicketMicros)) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = nowMicros;
        }
    }
}
