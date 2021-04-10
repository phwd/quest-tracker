package com.facebook.common.time;

import X.AnonymousClass0LE;
import X.AnonymousClass0LF;
import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AwakeTimeSinceBootClock implements AnonymousClass0LE, AnonymousClass0LF {
    @DoNotStrip
    public static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // X.AnonymousClass0LE
    @DoNotStrip
    public long now() {
        return SystemClock.uptimeMillis();
    }

    @Override // X.AnonymousClass0LF
    @DoNotStrip
    public long nowNanos() {
        return System.nanoTime();
    }
}
