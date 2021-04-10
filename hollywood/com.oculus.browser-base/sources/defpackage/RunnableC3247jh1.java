package defpackage;

import J.N;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: jh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3247jh1 implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C5464wh1 H;

    public RunnableC3247jh1(C5464wh1 wh1, int i, int i2) {
        this.H = wh1;
        this.F = i;
        this.G = i2;
    }

    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.H.f;
        int i = this.F;
        int i2 = this.G;
        if (imeAdapterImpl.v0()) {
            N.MmtjCblb(imeAdapterImpl.F, imeAdapterImpl, i, i2);
        }
    }
}
