package defpackage;

/* renamed from: vo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5314vo0 extends AbstractC3142j30 {
    public C5314vo0(SA sa, AbstractC4464qo0 qo0) {
        super(sa, qo0);
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
                return AbstractC3484l30.b(AbstractC5484wo0.f11570a, a2);
            }
            if (i2 == 0) {
                C4974to0 d = C4974to0.d(a2.b());
                ((C5144uo0) ((AbstractC4464qo0) this.G)).g0(d.d, d.e, d.f);
                return true;
            } else if (i2 != 1) {
                return false;
            } else {
                ((C5144uo0) ((AbstractC4464qo0) this.G)).f0(C4804so0.d(a2.b()).d);
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
                return AbstractC3484l30.a(this.F, AbstractC5484wo0.f11570a, a2, jk0);
            }
            return false;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
