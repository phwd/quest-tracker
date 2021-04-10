package defpackage;

import J.N;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* renamed from: xs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5665xs implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public final RenderFrameHost f11643a;

    public C5665xs(RenderFrameHost renderFrameHost) {
        this.f11643a = renderFrameHost;
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        RenderFrameHost renderFrameHost = this.f11643a;
        if (renderFrameHost == null) {
            return new J30();
        }
        C5815yl0 yl0 = null;
        if (!renderFrameHost.f(8)) {
            C3826n30 h = this.f11643a.h();
            new C5475wl0(7);
            h.G.close();
        } else if (!N.M1X7xdZV("WebPayments")) {
            return new J30();
        } else {
            C5495ws wsVar = new C5495ws(this.f11643a, null);
            WebContents a2 = Bx1.a(this.f11643a);
            if (a2 == null || a2.g()) {
                return new J30();
            }
            yl0 = new C5815yl0(new C5155us(this, wsVar));
        }
        return yl0;
    }
}
