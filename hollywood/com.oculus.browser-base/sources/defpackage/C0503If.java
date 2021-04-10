package defpackage;

/* renamed from: If  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0503If extends AbstractC3142j30 {
    public C0503If(SA sa, AbstractC5966zf zfVar) {
        super(sa, zfVar);
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
                return AbstractC3484l30.b(AbstractC0564Jf.f8304a, a2);
            }
            if (i2 != 0) {
                return false;
            }
            C0137Cf d = C0137Cf.d(a2.b());
            ((AbstractC5966zf) this.G).A(d.d, d.e);
            return true;
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
                return AbstractC3484l30.a(this.F, AbstractC0564Jf.f8304a, a2, jk0);
            }
            if (i != 1) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C0198Df.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                sDVar.c(C0198Df.b);
                sDVar.a();
                ((AbstractC5966zf) this.G).f(new C0381Gf(this.F, jk0, pj0.f));
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
}
