package defpackage;

/* renamed from: tL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4901tL extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public EH0[] e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4901tL(int i) {
        super(24, i);
    }

    public static C4901tL d(C4709sD sDVar) {
        EH0 eh0;
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4901tL tLVar = new C4901tL(sDVar.c(b).b);
            tLVar.d = sDVar.v(8, false);
            C4709sD s = sDVar.s(16, false);
            CC i = s.i(-1);
            tLVar.e = new EH0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                sDVar = AbstractC2531fV.n(i2, 8, 8, s, false);
                EH0[] eh0Arr = tLVar.e;
                CC[] ccArr = EH0.b;
                if (sDVar == null) {
                    eh0 = null;
                } else {
                    sDVar.b();
                    try {
                        eh0 = new EH0(sDVar.c(EH0.b).b);
                        eh0.d = sDVar.v(8, false);
                        eh0.e = C4306ps1.b(sDVar, 16);
                    } finally {
                        sDVar.a();
                    }
                }
                eh0Arr[i2] = eh0;
            }
            sDVar.a();
            return tLVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        EH0[] eh0Arr = this.e;
        if (eh0Arr == null) {
            x.s(16, false);
            return;
        }
        C1648aL t = x.t(eh0Arr.length, 16, -1);
        int i = 0;
        while (true) {
            EH0[] eh0Arr2 = this.e;
            if (i < eh0Arr2.length) {
                t.i(eh0Arr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }
}
