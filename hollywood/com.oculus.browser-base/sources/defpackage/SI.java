package defpackage;

import java.util.Objects;

/* renamed from: SI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class SI implements Runnable {
    public final TI F;
    public final XK0 G;
    public final boolean H;

    public SI(TI ti, XK0 xk0, boolean z) {
        this.F = ti;
        this.G = xk0;
        this.H = z;
    }

    public void run() {
        TI ti = this.F;
        XK0 xk0 = this.G;
        boolean z = this.H;
        Objects.requireNonNull(ti);
        xk0.G.setBackgroundColor(z ? ti.e.N : 0);
    }
}
