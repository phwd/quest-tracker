package com.facebook.common.time;

import X.AbstractC0106Kc;
import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class RealtimeSinceBootClock implements AbstractC0106Kc {
    public static final RealtimeSinceBootClock A00 = new RealtimeSinceBootClock();

    @Override // X.AbstractC0106Kc
    public final long now() {
        return SystemClock.elapsedRealtime();
    }

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return A00;
    }
}
