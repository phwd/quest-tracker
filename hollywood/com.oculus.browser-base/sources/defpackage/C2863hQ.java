package defpackage;

import java.util.Objects;

/* renamed from: hQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2863hQ extends AbstractC3142j30 {
    public C2863hQ(SA sa, ZP zp) {
        super(sa, zp);
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
                return AbstractC3484l30.b(AbstractC3034iQ.f10136a, a2);
            }
            if (i2 != 1) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C1838bQ.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                C1838bQ bQVar = new C1838bQ(sDVar.c(C1838bQ.b).b);
                bQVar.d = sDVar.o(8, false);
                sDVar.a();
                B30 b30 = bQVar.d;
                C2692gQ gQVar = (C2692gQ) ((ZP) this.G);
                Objects.requireNonNull(gQVar);
                C1838bQ bQVar2 = new C1838bQ();
                bQVar2.d = b30;
                C2288e30 e30 = gQVar.F;
                e30.G.b(bQVar2.c(e30.F, new C0942Pj0(1)));
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
                return AbstractC3484l30.a(this.F, AbstractC3034iQ.f10136a, a2, jk0);
            }
            if (i != 0) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C2009cQ.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                sDVar.c(C2009cQ.b);
                sDVar.a();
                C2521fQ fQVar = new C2521fQ(this.F, jk0, pj0.f);
                C2692gQ gQVar = (C2692gQ) ((ZP) this.G);
                Objects.requireNonNull(gQVar);
                C2009cQ cQVar = new C2009cQ();
                C2288e30 e30 = gQVar.F;
                e30.G.g(cQVar.c(e30.F, new C0942Pj0(0, 1, 0)), new C2350eQ(fQVar));
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
