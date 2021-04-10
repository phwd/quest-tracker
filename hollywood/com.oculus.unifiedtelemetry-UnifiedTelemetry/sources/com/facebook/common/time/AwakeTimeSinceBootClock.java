package com.facebook.common.time;

import X.AbstractC0106Kc;
import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AwakeTimeSinceBootClock implements AbstractC0106Kc {
    @DoNotStrip
    public static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    @Override // X.AbstractC0106Kc
    @DoNotStrip
    public long now() {
        return SystemClock.uptimeMillis();
    }

    @DoNotStrip
    public long nowNanos() {
        return System.nanoTime();
    }

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }
}
