package defpackage;

import J.N;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.content_public.browser.RenderFrameHost;

/* renamed from: dF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2153dF implements AbstractC1631aF {
    public final RenderFrameHost F;
    public final C1982cF G;
    public final ZE H = new ZE(AbstractApplicationC3785mq.g().e());

    public C2153dF(RenderFrameHost renderFrameHost) {
        this.F = renderFrameHost;
        renderFrameHost.getClass();
        this.G = new C1982cF(renderFrameHost);
    }

    @Override // defpackage.AbstractC1631aF
    public void Q(String str, C3007iF iFVar) {
        if (N.M1X7xdZV("AppStoreBilling")) {
            ChromeActivity.J0(Bx1.a(this.F));
        }
        iFVar.a(3, null);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }
}
