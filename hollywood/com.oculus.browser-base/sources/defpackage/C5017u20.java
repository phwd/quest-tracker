package defpackage;

/* renamed from: u20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5017u20 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C5922zL0[] d;
    public Cq1 e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5017u20() {
        super(24, 0);
    }

    public static C5017u20 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C5017u20 u20 = new C5017u20(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            u20.d = new C5922zL0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                u20.d[i2] = C5922zL0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            u20.e = Cq1.d(sDVar.s(16, false));
            return u20;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C5922zL0[] zl0Arr = this.d;
        if (zl0Arr != null) {
            C1648aL t = x.t(zl0Arr.length, 8, -1);
            int i = 0;
            while (true) {
                C5922zL0[] zl0Arr2 = this.d;
                if (i >= zl0Arr2.length) {
                    break;
                }
                t.i(zl0Arr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            x.s(8, false);
        }
        x.i(this.e, 16, false);
    }

    public C5017u20(int i) {
        super(24, i);
    }
}
