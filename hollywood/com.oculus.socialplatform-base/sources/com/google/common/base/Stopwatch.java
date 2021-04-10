package com.google.common.base;

import X.AnonymousClass006;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.cli.HelpFormatter;

@GwtCompatible
public final class Stopwatch {
    public long elapsedNanos;
    public boolean isRunning;
    public long startTick;
    public final Ticker ticker = Ticker.SYSTEM_TICKER;

    /* renamed from: com.google.common.base.Stopwatch$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                java.util.concurrent.TimeUnit[] r0 = java.util.concurrent.TimeUnit.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.google.common.base.Stopwatch.AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit = r2
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MINUTES     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.HOURS     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Stopwatch.AnonymousClass1.<clinit>():void");
        }
    }

    public static Stopwatch createStarted() {
        Stopwatch stopwatch = new Stopwatch();
        Preconditions.checkState(!stopwatch.isRunning, "This stopwatch is already running.");
        stopwatch.isRunning = true;
        stopwatch.startTick = stopwatch.ticker.read();
        return stopwatch;
    }

    public long elapsed(TimeUnit timeUnit) {
        long j;
        if (this.isRunning) {
            j = (this.ticker.read() - this.startTick) + this.elapsedNanos;
        } else {
            j = this.elapsedNanos;
        }
        return timeUnit.convert(j, TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    public Stopwatch stop() {
        long read = this.ticker.read();
        Preconditions.checkState(this.isRunning, "This stopwatch is already stopped.");
        this.isRunning = false;
        this.elapsedNanos += read - this.startTick;
        return this;
    }

    public String toString() {
        long j;
        TimeUnit timeUnit;
        String str;
        if (this.isRunning) {
            j = (this.ticker.read() - this.startTick) + this.elapsedNanos;
        } else {
            j = this.elapsedNanos;
        }
        if (TimeUnit.DAYS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.DAYS;
        } else if (TimeUnit.HOURS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.HOURS;
        } else if (TimeUnit.MINUTES.convert(j, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MINUTES;
        } else if (TimeUnit.SECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.SECONDS;
        } else if (TimeUnit.MILLISECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else if (TimeUnit.MICROSECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MICROSECONDS;
        } else {
            timeUnit = TimeUnit.NANOSECONDS;
        }
        String format = String.format(Locale.ROOT, "%.4g", Double.valueOf(((double) j) / ((double) TimeUnit.NANOSECONDS.convert(1, timeUnit))));
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = "s";
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = "h";
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        return AnonymousClass006.A09(format, HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, str);
    }
}
