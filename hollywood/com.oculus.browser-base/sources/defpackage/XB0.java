package defpackage;

import org.chromium.base.Callback;

/* renamed from: XB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XB0 implements Runnable {
    public final Callback F;
    public final AbstractC2145dC0 G;

    public XB0(Callback callback, AbstractC2145dC0 dc0) {
        this.F = callback;
        this.G = dc0;
    }

    public void run() {
        this.F.onResult(this.G);
    }
}
