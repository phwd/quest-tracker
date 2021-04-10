package defpackage;

import com.oculus.os.Version;

/* renamed from: fB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2483fB0 extends AbstractC3142j30 {
    public C2483fB0(SA sa, AbstractC1617aA0 aa0) {
        super(sa, aa0);
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
            switch (pj0.d) {
                case -2:
                    return AbstractC3484l30.b(AbstractC2654gB0.f9984a, a2);
                case -1:
                default:
                    return false;
                case 0:
                    ZA0 d = ZA0.d(a2.b());
                    ((AbstractC1617aA0) this.G).n(d.d, d.e, d.f, d.g, d.h);
                    return true;
                case 1:
                    C1971cB0 d2 = C1971cB0.d(a2.b());
                    ((AbstractC1617aA0) this.G).z(d2.d, d2.e);
                    return true;
                case 2:
                    ((AbstractC1617aA0) this.G).q(C2142dB0.d(a2.b()).d);
                    return true;
                case 3:
                    C2740gj0 b = a2.b();
                    CC[] ccArr = C1620aB0.b;
                    C4709sD sDVar = new C4709sD(b);
                    sDVar.b();
                    try {
                        sDVar.c(C1620aB0.b);
                        sDVar.a();
                        ((AbstractC1617aA0) this.G).p();
                        return true;
                    } catch (Throwable th) {
                        sDVar.a();
                        throw th;
                    }
                case 4:
                    C2740gj0 b2 = a2.b();
                    CC[] ccArr2 = VA0.b;
                    C4709sD sDVar2 = new C4709sD(b2);
                    sDVar2.b();
                    try {
                        sDVar2.c(VA0.b);
                        sDVar2.a();
                        ((AbstractC1617aA0) this.G).y();
                        return true;
                    } catch (Throwable th2) {
                        sDVar2.a();
                        throw th2;
                    }
                case 5:
                    ((AbstractC1617aA0) this.G).G(XA0.d(a2.b()).d);
                    return true;
                case 6:
                    ((AbstractC1617aA0) this.G).a0(C1800bB0.d(a2.b()).d);
                    return true;
                case Version.VERSION_7:
                    C2740gj0 b3 = a2.b();
                    CC[] ccArr3 = WA0.b;
                    C4709sD sDVar3 = new C4709sD(b3);
                    sDVar3.b();
                    try {
                        sDVar3.c(WA0.b);
                        sDVar3.a();
                        ((AbstractC1617aA0) this.G).o();
                        return true;
                    } catch (Throwable th3) {
                        sDVar3.a();
                        throw th3;
                    }
                case Version.VERSION_8:
                    C2740gj0 b4 = a2.b();
                    CC[] ccArr4 = YA0.b;
                    C4709sD sDVar4 = new C4709sD(b4);
                    sDVar4.b();
                    try {
                        sDVar4.c(YA0.b);
                        sDVar4.a();
                        ((AbstractC1617aA0) this.G).U();
                        return true;
                    } catch (Throwable th4) {
                        sDVar4.a();
                        throw th4;
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
                return AbstractC3484l30.a(this.F, AbstractC2654gB0.f9984a, a2, jk0);
            }
            return false;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
