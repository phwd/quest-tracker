package defpackage;

/* renamed from: Wf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1358Wf1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public C4048oK0 e;
    public C4883tE0[] f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1358Wf1() {
        super(32, 0);
    }

    public static C1358Wf1 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1358Wf1 wf1 = new C1358Wf1(sDVar.c(b).b);
            wf1.d = sDVar.v(8, false);
            wf1.e = C4048oK0.d(sDVar.s(16, false));
            C4709sD s = sDVar.s(24, false);
            CC i = s.i(-1);
            wf1.f = new C4883tE0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                wf1.f[i2] = C4883tE0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return wf1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.i(this.e, 16, false);
        C4883tE0[] te0Arr = this.f;
        if (te0Arr == null) {
            x.s(24, false);
            return;
        }
        C1648aL t = x.t(te0Arr.length, 24, -1);
        int i = 0;
        while (true) {
            C4883tE0[] te0Arr2 = this.f;
            if (i < te0Arr2.length) {
                t.i(te0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C1358Wf1(int i) {
        super(32, i);
    }
}
