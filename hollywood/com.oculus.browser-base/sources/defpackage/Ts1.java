package defpackage;

/* renamed from: Ts1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Ts1 extends AbstractC3142j30 {
    public Ts1(SA sa, Ps1 ps1) {
        super(sa, ps1);
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
                return AbstractC3484l30.b(AbstractC2088ct1.f9727a, a2);
            }
            return false;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }

    /* JADX INFO: finally extract failed */
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
                return AbstractC3484l30.a(this.F, AbstractC2088ct1.f9727a, a2, jk0);
            }
            if (i == 0) {
                C2740gj0 b = a2.b();
                CC[] ccArr = Ys1.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    Ys1 ys1 = new Ys1(sDVar.c(Ys1.b).b);
                    ys1.d = sDVar.q(8);
                    sDVar.a();
                    ((Ps1) this.G).L(ys1.d, new C1917bt1(this.F, jk0, pj0.f));
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            } else if (i != 1) {
                return false;
            } else {
                C2740gj0 b2 = a2.b();
                CC[] ccArr2 = Us1.b;
                C4709sD sDVar2 = new C4709sD(b2);
                sDVar2.b();
                try {
                    sDVar2.c(Us1.b);
                    sDVar2.a();
                    ((Ps1) this.G).m(new Xs1(this.F, jk0, pj0.f));
                    return true;
                } catch (Throwable th2) {
                    sDVar2.a();
                    throw th2;
                }
            }
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
