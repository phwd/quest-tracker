package defpackage;

import android.os.SystemClock;
import java.util.Objects;

/* renamed from: CI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CI0 implements Runnable {
    public final /* synthetic */ HI0 F;

    public CI0(HI0 hi0) {
        this.F = hi0;
    }

    public void run() {
        HI0 hi0 = this.F;
        Objects.requireNonNull(hi0);
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = hi0.N;
        long j2 = hi0.K.c;
        if ((j - j2) / 2500 != (uptimeMillis - j2) / 2500) {
            Objects.requireNonNull(hi0.O);
        }
        GI0 gi0 = hi0.K;
        gi0.d = gi0.e.getInterpolation(((float) ((uptimeMillis - gi0.c) % 2500)) / 2500.0f);
        GI0 gi02 = hi0.K;
        gi02.f.a(hi0, gi02.d);
        hi0.N = uptimeMillis;
        HI0 hi02 = this.F;
        if (hi02.M) {
            hi02.scheduleSelf(hi02.F, SystemClock.uptimeMillis() + 16);
        }
    }
}
