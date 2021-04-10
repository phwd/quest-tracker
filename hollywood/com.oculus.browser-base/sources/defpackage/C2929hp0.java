package defpackage;

/* renamed from: hp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2929hp0 extends AbstractC3142j30 {
    public C2929hp0(SA sa, AbstractC3951no0 no0) {
        super(sa, no0);
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            C0942Pj0 pj0 = a2.d;
            if (!pj0.c(pj0.a(4) ? 4 : 0)) {
                return false;
            }
            int i = pj0.d;
            if (i == -2) {
                return AbstractC3484l30.b(AbstractC3099ip0.f10164a, a2);
            }
            if (i == 0) {
                ((AbstractC3951no0) this.G).E(C1904bp0.d(a2.b()).d);
                return true;
            } else if (i == 2) {
                C2740gj0 b = a2.b();
                CC[] ccArr = C1318Vo0.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    sDVar.c(C1318Vo0.b);
                    sDVar.a();
                    ((AbstractC3951no0) this.G).M();
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            } else if (i != 4) {
                return false;
            } else {
                C2740gj0 b2 = a2.b();
                CC[] ccArr2 = C1379Wo0.b;
                C4709sD sDVar2 = new C4709sD(b2);
                sDVar2.b();
                try {
                    C1379Wo0 wo0 = new C1379Wo0(sDVar2.c(C1379Wo0.b).b);
                    wo0.d = sDVar2.n(8);
                    sDVar2.a();
                    ((AbstractC3951no0) this.G).I(wo0.d);
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
                return AbstractC3484l30.a(this.F, AbstractC3099ip0.f10164a, a2, jk0);
            }
            if (i == 1) {
                C1440Xo0 d = C1440Xo0.d(a2.b());
                ((AbstractC3951no0) this.G).S(d.d, d.e, new C1733ap0(this.F, jk0, pj0.f));
                return true;
            } else if (i != 3) {
                return false;
            } else {
                C2740gj0 b = a2.b();
                CC[] ccArr = C2075cp0.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    C2075cp0 cp0 = new C2075cp0(sDVar.c(C2075cp0.b).b);
                    cp0.d = sDVar.n(8);
                    sDVar.a();
                    ((AbstractC3951no0) this.G).J(cp0.d, new C2587fp0(this.F, jk0, pj0.f));
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
