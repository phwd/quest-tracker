package defpackage;

import java.util.Objects;

/* renamed from: x5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5535x5 extends AbstractC3142j30 {
    public C5535x5(SA sa, AbstractC3831n5 n5Var) {
        super(sa, n5Var);
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
                return AbstractC3484l30.b(AbstractC5705y5.f11661a, a2);
            }
            if (i2 == 3) {
                C2740gj0 b = a2.b();
                CC[] ccArr = C4344q5.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    C4344q5 q5Var = new C4344q5(sDVar.c(C4344q5.b).b);
                    q5Var.d = sDVar.d(8, 0);
                    sDVar.a();
                    ((C5365w5) ((AbstractC3831n5) this.G)).g0(q5Var.d);
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            } else if (i2 == 0) {
                C2740gj0 b2 = a2.b();
                CC[] ccArr2 = C4514r5.b;
                C4709sD sDVar2 = new C4709sD(b2);
                sDVar2.b();
                try {
                    C4514r5 r5Var = new C4514r5(sDVar2.c(C4514r5.b).b);
                    r5Var.d = sDVar2.q(8);
                    sDVar2.a();
                    ((C5365w5) ((AbstractC3831n5) this.G)).h0(r5Var.d);
                    return true;
                } catch (Throwable th2) {
                    sDVar2.a();
                    throw th2;
                }
            } else if (i2 != 1) {
                return false;
            } else {
                C2740gj0 b3 = a2.b();
                CC[] ccArr3 = C4173p5.b;
                C4709sD sDVar3 = new C4709sD(b3);
                sDVar3.b();
                try {
                    sDVar3.c(C4173p5.b);
                    sDVar3.a();
                    ((C5365w5) ((AbstractC3831n5) this.G)).f0();
                    return true;
                } catch (Throwable th3) {
                    sDVar3.a();
                    throw th3;
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
                return AbstractC3484l30.a(this.F, AbstractC5705y5.f11661a, a2, jk0);
            }
            if (i != 2) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C4685s5.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                sDVar.c(C4685s5.b);
                sDVar.a();
                C5195v5 v5Var = new C5195v5(this.F, jk0, pj0.f);
                C5365w5 w5Var = (C5365w5) ((AbstractC3831n5) this.G);
                Objects.requireNonNull(w5Var);
                C4685s5 s5Var = new C4685s5();
                C2288e30 e30 = w5Var.F;
                e30.G.g(s5Var.c(e30.F, new C0942Pj0(2, 1, 0)), new C5025u5(v5Var));
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
