package defpackage;

/* renamed from: HN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HN extends AbstractC3142j30 {
    public HN(SA sa, AbstractC4224pN pNVar) {
        super(sa, pNVar);
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
                return AbstractC3484l30.b(IN.f8221a, a2);
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
                return AbstractC3484l30.a(this.F, IN.f8221a, a2, jk0);
            }
            if (i != 0) {
                return false;
            }
            ((AbstractC4224pN) this.G).u(CN.d(a2.b()).d, new FN(this.F, jk0, pj0.f));
            return true;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
