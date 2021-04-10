package defpackage;

/* renamed from: oU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4068oU0 extends AbstractC3142j30 {
    public C4068oU0(SA sa, AbstractC2359eU0 eu0) {
        super(sa, eu0);
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
            if (pj0.c(i) && pj0.d == -2) {
                return AbstractC3484l30.b(AbstractC4239pU0.f11068a, a2);
            }
            return false;
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
            if (!pj0.c(pj0.a(4) ? 5 : 1)) {
                return false;
            }
            int i = pj0.d;
            if (i == -1) {
                return AbstractC3484l30.a(this.F, AbstractC4239pU0.f11068a, a2, jk0);
            }
            if (i != 0) {
                return false;
            }
            C3384kU0 d = C3384kU0.d(a2.b());
            ((AbstractC2359eU0) this.G).k(d.d, d.e, d.f, d.g, new C3897nU0(this.F, jk0, pj0.f));
            return true;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
