package com.facebook.common.time;

import X.Ca;
import android.os.SystemClock;

public class RealtimeSinceBootClock implements Ca {
    public static final RealtimeSinceBootClock A00 = new RealtimeSinceBootClock();

    public static RealtimeSinceBootClock get() {
        return A00;
    }

    @Override // X.Ca
    public final long now() {
        return SystemClock.elapsedRealtime();
    }
}
