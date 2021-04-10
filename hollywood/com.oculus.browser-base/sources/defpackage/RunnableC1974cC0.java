package defpackage;

import org.chromium.base.Callback;

/* renamed from: cC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1974cC0 implements Runnable {
    public final Callback F;
    public final AbstractC2145dC0 G;

    public RunnableC1974cC0(Callback callback, AbstractC2145dC0 dc0) {
        this.F = callback;
        this.G = dc0;
    }

    public void run() {
        this.F.onResult(this.G);
    }
}
