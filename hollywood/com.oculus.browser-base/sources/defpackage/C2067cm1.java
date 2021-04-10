package defpackage;

import android.content.Context;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: cm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2067cm1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9631a;
    public final AbstractC2642g70 b;
    public final I70 c;
    public final Callback d;
    public final AbstractC0956Pq0 e;
    public final AB f;
    public final Callback g;
    public final AbstractC2400ek h;
    public final AbstractC2230dk i;
    public final C2921hm1 j;
    public final UH0 k;
    public final C1128Sl l;
    public boolean m;
    public boolean n;

    public C2067cm1(UH0 uh0, Context context, AbstractC2642g70 g70, Callback callback, AbstractC0956Pq0 pq0, AbstractC2400ek ekVar, C2921hm1 hm1) {
        this.f9631a = context;
        this.b = g70;
        this.d = callback;
        this.e = pq0;
        this.h = ekVar;
        this.j = hm1;
        this.k = uh0;
        C1128Sl sl = new C1128Sl();
        this.l = sl;
        Zl1 zl1 = new Zl1(this);
        this.c = zl1;
        ((D70) g70).Q.b(zl1);
        AB ab = new AB(pq0, new C1716am1(this));
        this.f = ab;
        Callback b2 = sl.b(new Yl1(this));
        this.g = b2;
        C1078Rq0 rq0 = (C1078Rq0) pq0;
        rq0.l(b2);
        ((C1067Rl) b2).onResult((Tab) rq0.H);
        ab.c.onResult((Tab) rq0.H);
        C1896bm1 bm1 = new C1896bm1(this);
        this.i = bm1;
        ((C1551Zj) ekVar).Y.b(bm1);
    }

    public final void a() {
        if (!DeviceFormFactor.a(this.f9631a)) {
            UH0 uh0 = this.k;
            TH0 th0 = AbstractC2238dm1.h;
            if (uh0.g(th0) == null) {
                this.k.m(th0, new C3971nv());
            }
            this.d.onResult((C3971nv) this.k.g(th0));
            UH0 uh02 = this.k;
            uh02.m(th0, (C3971nv) uh02.g(th0));
        }
    }

    public final void b() {
        boolean z = true;
        if (!(((C1551Zj) this.h).W > 0.0f) && this.n) {
            z = false;
        }
        this.k.j(AbstractC2238dm1.f, z);
    }

    public final void c(Tab tab) {
        int f2 = this.j.f(tab);
        this.k.l(AbstractC2238dm1.c, f2);
        this.k.l(AbstractC2238dm1.d, Pj1.b(this.f9631a.getResources(), tab, f2));
    }

    public final void d() {
        UH0 uh0 = this.k;
        QH0 qh0 = AbstractC2238dm1.g;
        boolean z = true;
        if ((((C1551Zj) this.h).W == 1.0f) || this.m) {
            z = false;
        }
        uh0.j(qh0, z);
    }
}
