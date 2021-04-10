package defpackage;

import J.N;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: mh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3760mh1 implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ C5464wh1 G;

    public RunnableC3760mh1(C5464wh1 wh1, int i) {
        this.G = wh1;
        this.F = i;
    }

    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.G.f;
        int i = this.F;
        boolean z = false;
        boolean z2 = (i & 1) != 0;
        if ((i & 2) != 0) {
            z = true;
        }
        if (imeAdapterImpl.v0()) {
            N.MdwW1P2L(imeAdapterImpl.F, imeAdapterImpl, z2, z);
        }
        CB cb = imeAdapterImpl.M;
        if (cb != null) {
            cb.a(z2, z, imeAdapterImpl.t0());
        }
    }
}
