package defpackage;

/* renamed from: Zf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1541Zf1 extends AbstractC3142j30 {
    public C1541Zf1(SA sa, AbstractC1236Uf1 uf1) {
        super(sa, uf1);
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
                return AbstractC3484l30.b(AbstractC2390eg1.f9871a, a2);
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
                return AbstractC3484l30.a(this.F, AbstractC2390eg1.f9871a, a2, jk0);
            }
            if (i != 0) {
                return false;
            }
            ((AbstractC1236Uf1) this.G).R(C1698ag1.d(a2.b()).d, new C2220dg1(this.F, jk0, pj0.f));
            return true;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
