package defpackage;

/* renamed from: gz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2788gz0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C1035Qz0 d;
    public C1035Qz0[] e;
    public C3337kB0[] f;
    public C2959hz0[] g;
    public String h = "";
    public T3 i;
    public String j;
    public String k;

    static {
        CC[] ccArr = {new CC(72, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2788gz0(int i2) {
        super(72, i2);
    }

    public static C2788gz0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C2788gz0 gz0 = new C2788gz0(sDVar.c(b).b);
            gz0.d = C1035Qz0.d(sDVar.s(8, true));
            C4709sD s = sDVar.s(16, true);
            if (s == null) {
                gz0.e = null;
            } else {
                CC i2 = s.i(-1);
                gz0.e = new C1035Qz0[i2.b];
                for (int i3 = 0; i3 < i2.b; i3++) {
                    gz0.e[i3] = C1035Qz0.d(AbstractC2531fV.n(i3, 8, 8, s, false));
                }
            }
            C4709sD s2 = sDVar.s(24, true);
            if (s2 == null) {
                gz0.f = null;
            } else {
                CC i4 = s2.i(-1);
                gz0.f = new C3337kB0[i4.b];
                for (int i5 = 0; i5 < i4.b; i5++) {
                    gz0.f[i5] = C3337kB0.d(AbstractC2531fV.n(i5, 8, 8, s2, false));
                }
            }
            C4709sD s3 = sDVar.s(32, true);
            if (s3 == null) {
                gz0.g = null;
            } else {
                CC i6 = s3.i(-1);
                gz0.g = new C2959hz0[i6.b];
                for (int i7 = 0; i7 < i6.b; i7++) {
                    gz0.g[i7] = C2959hz0.d(AbstractC2531fV.n(i7, 8, 8, s3, false));
                }
            }
            gz0.h = sDVar.v(40, false);
            gz0.i = T3.d(sDVar.s(48, true));
            gz0.j = sDVar.v(56, true);
            gz0.k = sDVar.v(64, true);
            return gz0;
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
            int i2 = 0;
            while (true) {
                C1035Qz0[] qz0Arr2 = this.e;
                if (i2 >= qz0Arr2.length) {
                    break;
                }
                t.i(qz0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(16, true);
        }
        C3337kB0[] kb0Arr = this.f;
        if (kb0Arr != null) {
            C1648aL t2 = x.t(kb0Arr.length, 24, -1);
            int i3 = 0;
            while (true) {
                C3337kB0[] kb0Arr2 = this.f;
                if (i3 >= kb0Arr2.length) {
                    break;
                }
                t2.i(kb0Arr2[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            x.s(24, true);
        }
        C2959hz0[] hz0Arr = this.g;
        if (hz0Arr != null) {
            C1648aL t3 = x.t(hz0Arr.length, 32, -1);
            int i4 = 0;
            while (true) {
                C2959hz0[] hz0Arr2 = this.g;
                if (i4 >= hz0Arr2.length) {
                    break;
                }
                t3.i(hz0Arr2[i4], (i4 * 8) + 8, false);
                i4++;
            }
        } else {
            x.s(32, true);
        }
        x.k(this.h, 40, false);
        x.i(this.i, 48, true);
        x.k(this.j, 56, true);
        x.k(this.k, 64, true);
    }
}
