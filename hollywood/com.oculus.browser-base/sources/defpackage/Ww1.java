package defpackage;

import org.chromium.base.Callback;

/* renamed from: Ww1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ww1 implements Runnable {
    public final Zw1 F;
    public final Callback G;
    public final Boolean H;

    public Ww1(Zw1 zw1, Callback callback, Boolean bool) {
        this.F = zw1;
        this.G = callback;
        this.H = bool;
    }

    public void run() {
        Zw1 zw1 = this.F;
        zw1.e--;
        this.G.onResult(this.H);
    }
}
