package defpackage;

/* renamed from: TN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TN0 extends AbstractC5658xp1 {
    public C2343eM0 b;
    public XK c;
    public C2911hj0 d;
    public C3957nq0 e;

    public static final TN0 b(C4709sD sDVar, int i) {
        CC j = sDVar.j(i);
        C2343eM0 em0 = null;
        C3957nq0 nq0 = null;
        C2911hj0 hj0 = null;
        XK xk = null;
        if (j.f7794a == 0) {
            return null;
        }
        TN0 tn0 = new TN0();
        int i2 = j.b;
        if (i2 == 0) {
            C4709sD s = sDVar.s(i + 8, false);
            CC[] ccArr = C2343eM0.b;
            if (s != null) {
                s.b();
                try {
                    em0 = new C2343eM0(s.c(C2343eM0.b).b);
                    em0.d = s.n(8);
                } finally {
                    s.a();
                }
            }
            tn0.b = em0;
            tn0.f11638a = 0;
        } else if (i2 == 1) {
            C4709sD s2 = sDVar.s(i + 8, false);
            CC[] ccArr2 = XK.b;
            if (s2 != null) {
                s2.b();
                try {
                    xk = new XK(s2.c(XK.b).b);
                    xk.d = s2.q(8);
                } finally {
                    s2.a();
                }
            }
            tn0.c = xk;
            tn0.f11638a = 1;
        } else if (i2 == 2) {
            C4709sD s3 = sDVar.s(i + 8, false);
            CC[] ccArr3 = C2911hj0.b;
            if (s3 != null) {
                s3.b();
                try {
                    hj0 = new C2911hj0(s3.c(C2911hj0.b).b);
                } finally {
                    s3.a();
                }
            }
            tn0.d = hj0;
            tn0.f11638a = 2;
        } else if (i2 == 3) {
            C4709sD s4 = sDVar.s(i + 8, false);
            CC[] ccArr4 = C3957nq0.b;
            if (s4 != null) {
                s4.b();
                try {
                    nq0 = new C3957nq0(s4.c(C3957nq0.b).b);
                } finally {
                    s4.a();
                }
            }
            tn0.e = nq0;
            tn0.f11638a = 3;
        }
        return tn0;
    }

    @Override // defpackage.AbstractC5658xp1
    public final void a(C1648aL aLVar, int i) {
        aLVar.c(16, i);
        aLVar.c(this.f11638a, i + 4);
        int i2 = this.f11638a;
        if (i2 == 0) {
            aLVar.i(this.b, i + 8, false);
        } else if (i2 == 1) {
            aLVar.i(this.c, i + 8, false);
        } else if (i2 == 2) {
            aLVar.i(this.d, i + 8, false);
        } else if (i2 == 3) {
            aLVar.i(this.e, i + 8, false);
        }
    }
}
