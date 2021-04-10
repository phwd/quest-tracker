package defpackage;

/* renamed from: jn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3264jn0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C3606ln0[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3264jn0() {
        super(16, 0);
    }

    public static C3264jn0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3264jn0 jn0 = new C3264jn0(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            jn0.d = new C3606ln0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                jn0.d[i2] = C3606ln0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return jn0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C3606ln0[] ln0Arr = this.d;
        if (ln0Arr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(ln0Arr.length, 8, -1);
        int i = 0;
        while (true) {
            C3606ln0[] ln0Arr2 = this.d;
            if (i < ln0Arr2.length) {
                t.i(ln0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C3264jn0(int i) {
        super(16, i);
    }
}
