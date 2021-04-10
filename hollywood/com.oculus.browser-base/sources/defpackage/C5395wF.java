package defpackage;

/* renamed from: wF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5395wF extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public JI0[] e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5395wF() {
        super(24, 0);
    }

    public static C5395wF d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C5395wF wFVar = new C5395wF(sDVar.c(b).b);
            wFVar.d = sDVar.n(8);
            C4709sD s = sDVar.s(16, false);
            CC i = s.i(-1);
            wFVar.e = new JI0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                wFVar.e[i2] = JI0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return wFVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        JI0[] ji0Arr = this.e;
        if (ji0Arr == null) {
            x.s(16, false);
            return;
        }
        C1648aL t = x.t(ji0Arr.length, 16, -1);
        int i = 0;
        while (true) {
            JI0[] ji0Arr2 = this.e;
            if (i < ji0Arr2.length) {
                t.i(ji0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C5395wF(int i) {
        super(24, i);
    }
}
