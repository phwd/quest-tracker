package defpackage;

import java.util.Objects;

/* renamed from: da0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2201da0 extends AbstractC3142j30 {
    public C2201da0(SA sa, W90 w90) {
        super(sa, w90);
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
                return AbstractC3484l30.b(AbstractC2371ea0.f9861a, a2);
            }
            return false;
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
            if (!pj0.c(pj0.a(4) ? 5 : 1)) {
                return false;
            }
            int i = pj0.d;
            if (i == -1) {
                return AbstractC3484l30.a(this.F, AbstractC2371ea0.f9861a, a2, jk0);
            }
            if (i != 0) {
                return false;
            }
            KT kt = Y90.d(a2.b()).d;
            C1859ba0 ba0 = new C1859ba0(this.F, jk0, pj0.f);
            C2030ca0 ca0 = (C2030ca0) ((W90) this.G);
            Objects.requireNonNull(ca0);
            Y90 y90 = new Y90();
            y90.d = kt;
            C2288e30 e30 = ca0.F;
            e30.G.g(y90.c(e30.F, new C0942Pj0(0, 1, 0)), new C1679aa0(ba0));
            return true;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
