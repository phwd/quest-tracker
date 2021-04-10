package defpackage;

/* renamed from: AN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AN extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4048oK0 d;
    public C4689s60[] e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public AN() {
        super(24, 0);
    }

    public static AN d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            AN an = new AN(sDVar.c(b).b);
            an.d = C4048oK0.d(sDVar.s(8, false));
            C4709sD s = sDVar.s(16, false);
            CC i = s.i(-1);
            an.e = new C4689s60[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                an.e[i2] = C4689s60.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return an;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        C4689s60[] s60Arr = this.e;
        if (s60Arr == null) {
            x.s(16, false);
            return;
        }
        C1648aL t = x.t(s60Arr.length, 16, -1);
        int i = 0;
        while (true) {
            C4689s60[] s60Arr2 = this.e;
            if (i < s60Arr2.length) {
                t.i(s60Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public AN(int i) {
        super(24, i);
    }
}
