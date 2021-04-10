package defpackage;

import java.util.Collection;

/* renamed from: Qg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0994Qg0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1543Zg0 f8777a;

    public C0994Qg0(C1543Zg0 zg0) {
        this.f8777a = zg0;
    }

    public void a(AbstractC0202Dg0 dg0, C0869Of0 of0, Collection collection) {
        C1543Zg0 zg0 = this.f8777a;
        if (dg0 == zg0.u && of0 != null) {
            C2051ch0 ch0 = zg0.t.f9872a;
            String i = of0.i();
            C2392eh0 eh0 = new C2392eh0(ch0, i, this.f8777a.b(ch0, i));
            eh0.j(of0);
            C1543Zg0 zg02 = this.f8777a;
            C2392eh0 eh02 = zg02.t;
            if (zg02.r != eh0) {
                zg02.j(eh0, 3);
                zg02.r = eh0;
                zg02.s = zg02.u;
                zg02.t = null;
                zg02.u = null;
                zg02.k.c(264, new C1754aw0(eh02, eh0), 3);
                zg02.v.clear();
                zg02.r.o(collection);
                zg02.i();
                zg02.n();
            }
        } else if (dg0 == zg0.s) {
            if (of0 != null) {
                zg0.p(zg0.r, of0);
            }
            this.f8777a.r.o(collection);
        }
    }
}
