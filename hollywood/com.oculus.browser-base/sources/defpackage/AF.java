package defpackage;

import java.util.Objects;

/* renamed from: AF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AF extends AbstractC3142j30 {
    public AF(SA sa, YE ye) {
        super(sa, ye);
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
                return AbstractC3484l30.b(BF.f7726a, a2);
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
                return AbstractC3484l30.a(this.F, BF.f7726a, a2, jk0);
            }
            if (i == 0) {
                String[] strArr = C4544rF.d(a2.b()).d;
                C5055uF uFVar = new C5055uF(this.F, jk0, pj0.f);
                C5905zF zFVar = (C5905zF) ((YE) this.G);
                Objects.requireNonNull(zFVar);
                C4544rF rFVar = new C4544rF();
                rFVar.d = strArr;
                C2288e30 e30 = zFVar.F;
                e30.G.g(rFVar.c(e30.F, new C0942Pj0(0, 1, 0)), new C4885tF(uFVar));
                return true;
            } else if (i == 1) {
                C3861nF d = C3861nF.d(a2.b());
                String str = d.d;
                boolean z = d.e;
                C4374qF qFVar = new C4374qF(this.F, jk0, pj0.f);
                C5905zF zFVar2 = (C5905zF) ((YE) this.G);
                Objects.requireNonNull(zFVar2);
                C3861nF nFVar = new C3861nF();
                nFVar.d = str;
                nFVar.e = z;
                C2288e30 e302 = zFVar2.F;
                e302.G.g(nFVar.c(e302.F, new C0942Pj0(1, 1, 0)), new C4203pF(qFVar));
                return true;
            } else if (i != 2) {
                return false;
            } else {
                C2740gj0 b = a2.b();
                CC[] ccArr = C5225vF.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    sDVar.c(C5225vF.b);
                    sDVar.a();
                    C5735yF yFVar = new C5735yF(this.F, jk0, pj0.f);
                    C5905zF zFVar3 = (C5905zF) ((YE) this.G);
                    Objects.requireNonNull(zFVar3);
                    C5225vF vFVar = new C5225vF();
                    C2288e30 e303 = zFVar3.F;
                    e303.G.g(vFVar.c(e303.F, new C0942Pj0(2, 1, 0)), new C5565xF(yFVar));
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
