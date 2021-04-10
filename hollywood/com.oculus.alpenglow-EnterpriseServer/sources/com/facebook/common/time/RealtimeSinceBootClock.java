package com.facebook.common.time;

import X.AnonymousClass0LK;
import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class RealtimeSinceBootClock implements AnonymousClass0LK {
    public static final RealtimeSinceBootClock A00 = new RealtimeSinceBootClock();

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return A00;
    }

    @Override // X.AnonymousClass0LK
    public final long now() {
        return SystemClock.elapsedRealtime();
    }
}
