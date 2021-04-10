package defpackage;

/* renamed from: Uh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1240Uh extends AbstractC3142j30 {
    public C1240Uh(SA sa, AbstractC0935Ph ph) {
        super(sa, ph);
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            C0942Pj0 pj0 = a2.d;
            int i = 4;
            if (!pj0.a(4)) {
                i = 0;
            }
            if (!pj0.c(i)) {
                return false;
            }
            int i2 = pj0.d;
            if (i2 == -2) {
                return AbstractC3484l30.b(AbstractC1301Vh.f9099a, a2);
            }
            if (i2 == 0) {
                C1057Rh d = C1057Rh.d(a2.b());
                ((AbstractC0935Ph) this.G).K(d.d, d.e);
                return true;
            } else if (i2 != 1) {
                return false;
            } else {
                C1118Sh d2 = C1118Sh.d(a2.b());
                ((AbstractC0935Ph) this.G).F(d2.d, d2.e);
                return true;
            }
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @Override // defpackage.AbstractC3426kk0
    public boolean g(C2740gj0 gj0, AbstractC3255jk0 jk0) {
        try {
            IS0 a2 = gj0.a();
            C0942Pj0 pj0 = a2.d;
            int i = 1;
            if (pj0.a(4)) {
                i = 5;
            }
            if (pj0.c(i) && pj0.d == -1) {
                return AbstractC3484l30.a(this.F, AbstractC1301Vh.f9099a, a2, jk0);
            }
            return false;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
