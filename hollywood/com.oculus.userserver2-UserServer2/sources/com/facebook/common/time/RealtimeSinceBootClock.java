package com.facebook.common.time;

import X.Kt;
import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class RealtimeSinceBootClock implements Kt {
    public static final RealtimeSinceBootClock A00 = new RealtimeSinceBootClock();

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return A00;
    }

    @Override // X.Kt
    public final long now() {
        return SystemClock.elapsedRealtime();
    }
}
