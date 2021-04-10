package defpackage;

import android.os.SystemClock;
import org.chromium.base.TraceEvent;

/* renamed from: Ka  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0610Ka implements Runnable {
    public final AbstractActivityC0731Ma F;

    public RunnableC0610Ka(AbstractActivityC0731Ma ma) {
        this.F = ma;
    }

    public void run() {
        AbstractActivityC0731Ma ma = this.F;
        ma.m0 = true;
        AbstractC2793h01.e("FirstDrawCompletedTime", SystemClock.elapsedRealtime() - ma.m0(), AbstractC4772sd1.k(ma.h0));
        if (!ma.l0) {
            TraceEvent.h0("onFirstDrawComplete");
            C5478wm0 wm0 = ma.X;
            wm0.g = true;
            wm0.a();
        }
    }
}
