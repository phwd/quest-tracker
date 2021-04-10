package defpackage;

/* renamed from: Jo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0587Jo0 extends AbstractC3142j30 {
    public C0587Jo0(SA sa, AbstractC0099Bo0 bo0) {
        super(sa, bo0);
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
                return AbstractC3484l30.b(AbstractC0648Ko0.f8389a, a2);
            }
            if (i2 == 0) {
                C0343Fo0 d = C0343Fo0.d(a2.b());
                ((AbstractC0099Bo0) this.G).s(d.d, d.e);
                return true;
            } else if (i2 == 1) {
                C2740gj0 b = a2.b();
                CC[] ccArr = C0465Ho0.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    sDVar.c(C0465Ho0.b);
                    sDVar.a();
                    ((AbstractC0099Bo0) this.G).d0();
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            } else if (i2 != 2) {
                return false;
            } else {
                C2740gj0 b2 = a2.b();
                CC[] ccArr2 = C0404Go0.b;
                C4709sD sDVar2 = new C4709sD(b2);
                sDVar2.b();
                try {
                    sDVar2.c(C0404Go0.b);
                    sDVar2.a();
                    ((AbstractC0099Bo0) this.G).v();
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
                return AbstractC3484l30.a(this.F, AbstractC0648Ko0.f8389a, a2, jk0);
            }
            return false;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
