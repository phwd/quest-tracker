package defpackage;

import com.oculus.os.Version;

/* renamed from: pA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4189pA0 extends AbstractC3142j30 {
    public C4189pA0(SA sa, AbstractC1797bA0 ba0) {
        super(sa, ba0);
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
                    return AbstractC3484l30.b(AbstractC4360qA0.f11121a, a2);
                case -1:
                default:
                    return false;
                case 0:
                    C3163jA0 d = C3163jA0.d(a2.b());
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).l0(d.d, d.e);
                    return true;
                case 1:
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).n0(C3505lA0.d(a2.b()).d);
                    return true;
                case 2:
                    C2740gj0 b = a2.b();
                    CC[] ccArr = C3676mA0.b;
                    C4709sD sDVar = new C4709sD(b);
                    sDVar.b();
                    try {
                        C3676mA0 ma0 = new C3676mA0(sDVar.c(C3676mA0.b).b);
                        ma0.d = sDVar.v(8, false);
                        sDVar.a();
                        ((C4018oA0) ((AbstractC1797bA0) this.G)).o0(ma0.d);
                        return true;
                    } catch (Throwable th) {
                        sDVar.a();
                        throw th;
                    }
                case 3:
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).k0(C2993iA0.d(a2.b()).d);
                    return true;
                case 4:
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).m0(C3334kA0.d(a2.b()).d);
                    return true;
                case 5:
                    C2651gA0 d2 = C2651gA0.d(a2.b());
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).i0(d2.d, d2.e);
                    return true;
                case 6:
                    C2740gj0 b2 = a2.b();
                    CC[] ccArr2 = C2480fA0.b;
                    C4709sD sDVar2 = new C4709sD(b2);
                    sDVar2.b();
                    try {
                        sDVar2.c(C2480fA0.b);
                        sDVar2.a();
                        ((C4018oA0) ((AbstractC1797bA0) this.G)).h0();
                        return true;
                    } catch (Throwable th2) {
                        sDVar2.a();
                        throw th2;
                    }
                case Version.VERSION_7:
                    C2740gj0 b3 = a2.b();
                    CC[] ccArr3 = C2139dA0.b;
                    C4709sD sDVar3 = new C4709sD(b3);
                    sDVar3.b();
                    try {
                        C2139dA0 da0 = new C2139dA0(sDVar3.c(C2139dA0.b).b);
                        da0.d = sDVar3.d(8, 0);
                        sDVar3.a();
                        ((C4018oA0) ((AbstractC1797bA0) this.G)).f0(da0.d);
                        return true;
                    } catch (Throwable th3) {
                        sDVar3.a();
                        throw th3;
                    }
                case Version.VERSION_8:
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).g0(C2309eA0.d(a2.b()).d);
                    return true;
                case Version.VERSION_9:
                    ((C4018oA0) ((AbstractC1797bA0) this.G)).j0(C2822hA0.d(a2.b()).d);
                    return true;
                case Version.VERSION_10:
                    C2740gj0 b4 = a2.b();
                    CC[] ccArr4 = C3847nA0.b;
                    C4709sD sDVar4 = new C4709sD(b4);
                    sDVar4.b();
                    try {
                        sDVar4.c(C3847nA0.b);
                        sDVar4.a();
                        ((C4018oA0) ((AbstractC1797bA0) this.G)).p0();
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
                return AbstractC3484l30.a(this.F, AbstractC4360qA0.f11121a, a2, jk0);
            }
            return false;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
