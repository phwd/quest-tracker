package defpackage;

import org.chromium.base.Callback;

/* renamed from: V0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V0 implements Runnable {
    public final C1769b1 F;
    public final Callback G;

    public V0(C1769b1 b1Var, Callback callback) {
        this.F = b1Var;
        this.G = callback;
    }

    public void run() {
        this.G.onResult((C2111d1) this.F.e.get());
    }
}
