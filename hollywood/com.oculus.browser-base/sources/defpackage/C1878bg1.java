package defpackage;

/* renamed from: bg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1878bg1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C1358Wf1[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1878bg1() {
        super(16, 0);
    }

    public static C1878bg1 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1878bg1 bg1 = new C1878bg1(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            bg1.d = new C1358Wf1[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                bg1.d[i2] = C1358Wf1.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return bg1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C1358Wf1[] wf1Arr = this.d;
        if (wf1Arr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(wf1Arr.length, 8, -1);
        int i = 0;
        while (true) {
            C1358Wf1[] wf1Arr2 = this.d;
            if (i < wf1Arr2.length) {
                t.i(wf1Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C1878bg1(int i) {
        super(16, i);
    }
}
