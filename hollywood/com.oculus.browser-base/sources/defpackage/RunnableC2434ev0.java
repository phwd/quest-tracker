package defpackage;

import J.N;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: ev0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2434ev0 implements Runnable {
    public final C3117iv0 F;

    public RunnableC2434ev0(C3117iv0 iv0) {
        this.F = iv0;
    }

    public void run() {
        C3117iv0 iv0 = this.F;
        ((PageInfoController) iv0.F).k(13);
        if (iv0.Q != null) {
            BrowserContextHandle g = ((PageInfoController) iv0.F).g();
            C3469ky1 ky1 = iv0.Q;
            AbstractC5675xv0 xv0 = iv0.F;
            xv0.getClass();
            RunnableC2947hv0 hv0 = new RunnableC2947hv0(xv0);
            String d = ky1.F.d();
            N.Mks53EZS(g, d);
            N.MyQGLOqU(g, d);
            N.MSoF8bn2(g, d);
            ky1.a(g, new NX0(hv0));
        }
    }
}
