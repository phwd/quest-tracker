package defpackage;

/* renamed from: Tb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1161Tb extends AbstractC3142j30 {
    public C1161Tb(SA sa, AbstractC5274vb vbVar) {
        super(sa, vbVar);
    }

    /* JADX INFO: finally extract failed */
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
                return AbstractC3484l30.b(AbstractC1222Ub.f9034a, a2);
            }
            if (i2 != 3) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C0308Fb.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                sDVar.c(C0308Fb.b);
                sDVar.a();
                ((AbstractC5274vb) this.G).cancel();
                return true;
            } catch (Throwable th) {
                sDVar.a();
                throw th;
            }
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
                return AbstractC3484l30.a(this.F, AbstractC1222Ub.f9034a, a2, jk0);
            }
            if (i == 0) {
                ((AbstractC5274vb) this.G).j(C0856Ob.d(a2.b()).d, new C1039Rb(this.F, jk0, pj0.f));
                return true;
            } else if (i == 1) {
                ((AbstractC5274vb) this.G).Z(C0369Gb.d(a2.b()).d, new C0552Jb(this.F, jk0, pj0.f));
                return true;
            } else if (i != 2) {
                return false;
            } else {
                C2740gj0 b = a2.b();
                CC[] ccArr = C0613Kb.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    sDVar.c(C0613Kb.b);
                    sDVar.a();
                    ((AbstractC5274vb) this.G).c0(new C0795Nb(this.F, jk0, pj0.f));
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            }
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
