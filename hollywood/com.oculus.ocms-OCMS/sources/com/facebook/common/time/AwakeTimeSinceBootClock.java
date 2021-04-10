package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AwakeTimeSinceBootClock implements MonotonicClock, MonotonicNanoClock {
    @DoNotStrip
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    private AwakeTimeSinceBootClock() {
    }

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // com.facebook.common.time.MonotonicClock
    @DoNotStrip
    public long now() {
        return SystemClock.uptimeMillis();
    }

    @Override // com.facebook.common.time.MonotonicNanoClock
    @DoNotStrip
    public long nowNanos() {
        return System.nanoTime();
    }
}
