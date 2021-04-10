package defpackage;

/* renamed from: kU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3384kU0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public Cq1 f;
    public KU0[] g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3384kU0() {
        super(40, 0);
    }

    public static C3384kU0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3384kU0 ku0 = new C3384kU0(sDVar.c(b).b);
            ku0.d = sDVar.v(8, false);
            ku0.e = sDVar.v(16, false);
            ku0.f = Cq1.d(sDVar.s(24, false));
            C4709sD s = sDVar.s(32, false);
            CC i = s.i(-1);
            ku0.g = new KU0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                ku0.g[i2] = KU0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return ku0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.i(this.f, 24, false);
        KU0[] ku0Arr = this.g;
        if (ku0Arr == null) {
            x.s(32, false);
            return;
        }
        C1648aL t = x.t(ku0Arr.length, 32, -1);
        int i = 0;
        while (true) {
            KU0[] ku0Arr2 = this.g;
            if (i < ku0Arr2.length) {
                t.i(ku0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C3384kU0(int i) {
        super(40, i);
    }
}
