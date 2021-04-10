package defpackage;

/* renamed from: hz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2959hz0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C1035Qz0 d;
    public C1035Qz0[] e;
    public C1401Wz0 f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2959hz0(int i) {
        super(32, i);
    }

    public static C2959hz0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C2959hz0 hz0 = new C2959hz0(sDVar.c(b).b);
            hz0.d = C1035Qz0.d(sDVar.s(8, true));
            C4709sD s = sDVar.s(16, false);
            CC i = s.i(-1);
            hz0.e = new C1035Qz0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                hz0.e[i2] = C1035Qz0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            hz0.f = C1401Wz0.d(sDVar.s(24, false));
            return hz0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, true);
        C1035Qz0[] qz0Arr = this.e;
        if (qz0Arr != null) {
            C1648aL t = x.t(qz0Arr.length, 16, -1);
            int i = 0;
            while (true) {
                C1035Qz0[] qz0Arr2 = this.e;
                if (i >= qz0Arr2.length) {
                    break;
                }
                t.i(qz0Arr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            x.s(16, false);
        }
        x.i(this.f, 24, false);
    }
}
