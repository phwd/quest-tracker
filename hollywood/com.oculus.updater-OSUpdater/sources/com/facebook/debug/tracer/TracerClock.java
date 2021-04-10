package com.facebook.debug.tracer;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TracerClock {
    private static volatile long sTestTimeNanos = -1;

    public static long nanoTime() {
        return sTestTimeNanos != -1 ? sTestTimeNanos : System.nanoTime();
    }

    public static long realNanoTime() {
        return System.nanoTime();
    }
}
