package defpackage;

/* renamed from: Kf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0625Kf extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public C4048oK0 e;
    public int f;
    public C4883tE0[] g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0625Kf() {
        super(40, 0);
    }

    public static C0625Kf d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0625Kf kf = new C0625Kf(sDVar.c(b).b);
            kf.d = sDVar.v(8, false);
            kf.e = C4048oK0.d(sDVar.s(16, false));
            int n = sDVar.n(24);
            kf.f = n;
            AbstractC1295Vf.a(n);
            C4709sD s = sDVar.s(32, false);
            CC i = s.i(-1);
            kf.g = new C4883tE0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                kf.g[i2] = C4883tE0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return kf;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.i(this.e, 16, false);
        x.c(this.f, 24);
        C4883tE0[] te0Arr = this.g;
        if (te0Arr == null) {
            x.s(32, false);
            return;
        }
        C1648aL t = x.t(te0Arr.length, 32, -1);
        int i = 0;
        while (true) {
            C4883tE0[] te0Arr2 = this.g;
            if (i < te0Arr2.length) {
                t.i(te0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C0625Kf(int i) {
        super(40, i);
    }
}
