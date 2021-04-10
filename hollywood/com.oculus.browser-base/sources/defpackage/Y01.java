package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Y01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y01 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f9248a;
    public final Callback b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final Q31 f;
    public AbstractC0124Ca1 g;
    public AbstractC0383Gf1 h;
    public AbstractC0612Ka1 i;
    public AbstractC2642g70 j;
    public I70 k;
    public C5976zi0 l;
    public int m;
    public boolean n;
    public C1128Sl o = new C1128Sl();
    public float p;

    public Y01(UH0 uh0, Callback callback, boolean z, boolean z2, boolean z3, C5976zi0 zi0, AbstractC0956Pq0 pq0, Q31 q31) {
        this.f9248a = uh0;
        this.m = 0;
        this.b = callback;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.l = zi0;
        this.f = q31;
        ((C1078Rq0) pq0).l(new U01(this));
    }

    public void a(C0517Ik ik) {
        if (ik.f8247a && !((AbstractC0246Ea1) this.g).r()) {
            this.f9248a.m(Z01.c, ik.c);
            this.f9248a.m(Z01.d, ik.b.getConstantState().newDrawable());
            this.f9248a.l(Z01.e, ik.d);
            this.f9248a.j(Z01.f, true);
            this.b.onResult(ik.g);
            return;
        }
        this.f9248a.j(Z01.f, false);
    }

    public final void b() {
        boolean z = true;
        if ((this.m == 1 && this.d) || this.e) {
            this.f9248a.m(Z01.q, Boolean.FALSE);
        } else if (this.c) {
            UH0 uh0 = this.f9248a;
            TH0 th0 = Z01.q;
            AbstractC0124Ca1 ca1 = this.g;
            if (ca1 != null) {
                TabModel l2 = ((AbstractC0246Ea1) ca1).l(true);
                int i2 = 0;
                while (true) {
                    if (i2 >= l2.getCount()) {
                        break;
                    } else if (!l2.getTabAt(i2).x()) {
                        break;
                    } else {
                        i2++;
                    }
                }
                uh0.m(th0, Boolean.valueOf(z));
            }
            z = false;
            uh0.m(th0, Boolean.valueOf(z));
        } else {
            this.f9248a.m(Z01.q, Boolean.TRUE);
        }
    }

    public final void c(boolean z) {
        this.n = z;
        int i2 = this.m;
        boolean z2 = true;
        if (!(i2 == 1 || i2 == 4 || i2 == 5 || i2 == 6) || !z) {
            z2 = false;
        }
        this.f9248a.j(Z01.i, z2);
    }

    public final void d() {
        int i2 = this.m;
        this.f9248a.j(Z01.m, i2 == 2 || i2 == 4 || i2 == 5 || i2 == 6 || C0283Ep.h().d());
    }

    public final void e(float f2) {
        if (this.m != 1 || this.f9248a.h(Z01.j)) {
            this.f9248a.k(Z01.s, 0.0f);
            return;
        }
        this.p = f2;
        this.f9248a.k(Z01.s, f2);
    }
}
