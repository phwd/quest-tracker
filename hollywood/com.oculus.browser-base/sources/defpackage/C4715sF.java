package defpackage;

/* renamed from: sF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4715sF extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public C3487l40[] e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4715sF() {
        super(24, 0);
    }

    public static C4715sF d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C4715sF sFVar = new C4715sF(sDVar.c(b).b);
            sFVar.d = sDVar.n(8);
            C4709sD s = sDVar.s(16, false);
            CC i = s.i(-1);
            sFVar.e = new C3487l40[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                sFVar.e[i2] = C3487l40.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return sFVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        C3487l40[] l40Arr = this.e;
        if (l40Arr == null) {
            x.s(16, false);
            return;
        }
        C1648aL t = x.t(l40Arr.length, 16, -1);
        int i = 0;
        while (true) {
            C3487l40[] l40Arr2 = this.e;
            if (i < l40Arr2.length) {
                t.i(l40Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C4715sF(int i) {
        super(24, i);
    }
}
