package com.facebook.common.time;

import X.Ca;
import X.Cb;
import android.os.SystemClock;

public class AwakeTimeSinceBootClock implements Cb, Ca {
    public static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // X.Ca
    public long now() {
        return SystemClock.uptimeMillis();
    }

    @Override // X.Cb
    public long nowNanos() {
        return System.nanoTime();
    }
}
