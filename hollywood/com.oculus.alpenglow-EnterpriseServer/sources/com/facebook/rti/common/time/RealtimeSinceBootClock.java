package com.facebook.rti.common.time;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class RealtimeSinceBootClock {
    @DoNotStrip
    public static final RealtimeSinceBootClock INSTANCE = new RealtimeSinceBootClock();

    @DoNotStrip
    public long now() {
        return SystemClock.elapsedRealtime();
    }

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return INSTANCE;
    }
}
