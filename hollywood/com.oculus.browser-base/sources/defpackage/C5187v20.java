package defpackage;

/* renamed from: v20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5187v20 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C5922zL0[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5187v20() {
        super(16, 0);
    }

    public static C5187v20 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C5187v20 v20 = new C5187v20(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            v20.d = new C5922zL0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                v20.d[i2] = C5922zL0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return v20;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C5922zL0[] zl0Arr = this.d;
        if (zl0Arr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(zl0Arr.length, 8, -1);
        int i = 0;
        while (true) {
            C5922zL0[] zl0Arr2 = this.d;
            if (i < zl0Arr2.length) {
                t.i(zl0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C5187v20(int i) {
        super(16, i);
    }
}
