package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: AB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AB {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0956Pq0 f7658a;
    public final AbstractC1404Xa1 b;
    public final Callback c;
    public C1128Sl d;
    public Tab e;

    public AB(AbstractC0956Pq0 pq0, AbstractC1404Xa1 xa1) {
        this.f7658a = pq0;
        this.b = xa1;
        C1128Sl sl = new C1128Sl();
        this.d = sl;
        Callback b2 = sl.b(new C5893zB(this));
        this.c = b2;
        ((C1078Rq0) pq0).l(b2);
    }

    public void a() {
        Tab tab = this.e;
        if (tab != null) {
            tab.I(this.b);
        }
        AbstractC0956Pq0 pq0 = this.f7658a;
        ((C1078Rq0) pq0).I.c(this.c);
        this.d.a();
        this.d = null;
    }
}
