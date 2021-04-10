package defpackage;

/* renamed from: i5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2977i5 extends AbstractC3142j30 {
    public C2977i5(SA sa, S4 s4) {
        super(sa, s4);
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
                return AbstractC3484l30.b(AbstractC3147j5.f10186a, a2);
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
                return AbstractC3484l30.a(this.F, AbstractC3147j5.f10186a, a2, jk0);
            }
            if (i == 0) {
                C2740gj0 b = a2.b();
                CC[] ccArr = Z4.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    sDVar.c(Z4.b);
                    sDVar.a();
                    ((S4) this.G).b0(new C1952c5(this.F, jk0, pj0.f));
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            } else if (i != 1) {
                return false;
            } else {
                C2740gj0 b2 = a2.b();
                CC[] ccArr2 = C2123d5.b;
                C4709sD sDVar2 = new C4709sD(b2);
                sDVar2.b();
                try {
                    C2123d5 d5Var = new C2123d5(sDVar2.c(C2123d5.b).b);
                    d5Var.d = sDVar2.v(8, false);
                    sDVar2.a();
                    ((S4) this.G).P(d5Var.d, new C2635g5(this.F, jk0, pj0.f));
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
