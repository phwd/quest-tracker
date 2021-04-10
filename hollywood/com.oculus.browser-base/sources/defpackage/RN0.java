package defpackage;

/* renamed from: RN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RN0 extends AbstractC5658xp1 {
    public C5748yJ0 b;
    public C4743sR c;

    public static final RN0 b(C4709sD sDVar, int i) {
        CC j = sDVar.j(i);
        C5748yJ0 yj0 = null;
        C4743sR sRVar = null;
        if (j.f7794a == 0) {
            return null;
        }
        RN0 rn0 = new RN0();
        int i2 = j.b;
        if (i2 == 0) {
            C4709sD s = sDVar.s(i + 8, false);
            CC[] ccArr = C5748yJ0.b;
            if (s != null) {
                s.b();
                try {
                    yj0 = new C5748yJ0(s.c(C5748yJ0.b).b);
                } finally {
                    s.a();
                }
            }
            rn0.b = yj0;
            rn0.f11638a = 0;
        } else if (i2 == 1) {
            C4709sD s2 = sDVar.s(i + 8, false);
            CC[] ccArr2 = C4743sR.b;
            if (s2 != null) {
                s2.b();
                try {
                    sRVar = new C4743sR(s2.c(C4743sR.b).b);
                } finally {
                    s2.a();
                }
            }
            rn0.c = sRVar;
            rn0.f11638a = 1;
        }
        return rn0;
    }

    @Override // defpackage.AbstractC5658xp1
    public final void a(C1648aL aLVar, int i) {
        aLVar.c(16, i);
        aLVar.c(this.f11638a, i + 4);
        int i2 = this.f11638a;
        if (i2 == 0) {
            aLVar.i(this.b, i + 8, false);
        } else if (i2 == 1) {
            aLVar.i(this.c, i + 8, false);
        }
    }
}
