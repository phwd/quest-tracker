package com.facebook.common.time;

import X.AnonymousClass0LK;
import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AwakeTimeSinceBootClock implements AnonymousClass0LK {
    @DoNotStrip
    public static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // X.AnonymousClass0LK
    @DoNotStrip
    public long now() {
        return SystemClock.uptimeMillis();
    }

    @DoNotStrip
    public long nowNanos() {
        return System.nanoTime();
    }
}
