package defpackage;

import J.N;
import org.chromium.components.media_router.BrowserMediaRouter;

/* renamed from: El  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0275El extends AbstractC5474wl {
    public final QL0 h = new QL0(this);

    public C0275El(C3246jh0 jh0, AbstractC5800yg0 yg0) {
        super(jh0, yg0);
    }

    @Override // defpackage.AbstractC5970zg0
    public void d(String str, String str2, String str3, int i, int i2) {
        BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) this.c;
        long j = browserMediaRouter.b;
        if (j != 0) {
            N.M9VY0XZb(j, browserMediaRouter, "Remote playback doesn't support joining routes", i2);
        }
    }

    @Override // defpackage.AbstractC5970zg0
    public void f(String str, String str2) {
        AbstractC1220Ua0.a("RmtMRP", "Remote playback does not support sending messages", new Object[0]);
    }

    @Override // defpackage.AbstractC5970zg0, defpackage.AbstractC5474wl
    public AbstractC2352eR h(String str) {
        if (this.h.h() && this.e.containsKey(str)) {
            return this.h.f;
        }
        return null;
    }

    @Override // defpackage.AbstractC5474wl
    public AbstractC1424Xh0 p(String str) {
        return NL0.d(str);
    }

    @Override // defpackage.AbstractC5474wl
    public AbstractC0018Ag v() {
        return this.h;
    }
}
