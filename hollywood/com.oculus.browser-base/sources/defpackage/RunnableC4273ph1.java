package defpackage;

import J.N;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: ph1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4273ph1 implements Runnable {
    public final /* synthetic */ C5464wh1 F;

    public RunnableC4273ph1(C5464wh1 wh1) {
        this.F = wh1;
    }

    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.F.f;
        boolean z = false;
        if (imeAdapterImpl.v0() && imeAdapterImpl.H != null) {
            z = N.M7o5Xhhi(imeAdapterImpl.F, imeAdapterImpl);
        }
        if (!z) {
            this.F.h();
        }
    }
}
