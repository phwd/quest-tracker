package defpackage;

/* renamed from: Ex1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Ex1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public Cq1 d;
    public String e;
    public C4901tL[] f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public Ex1(int i) {
        super(32, i);
    }

    public static Ex1 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            Ex1 ex1 = new Ex1(sDVar.c(b).b);
            ex1.d = Cq1.d(sDVar.s(8, false));
            ex1.e = sDVar.v(16, false);
            C4709sD s = sDVar.s(24, false);
            CC i = s.i(-1);
            ex1.f = new C4901tL[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                ex1.f[i2] = C4901tL.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return ex1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.k(this.e, 16, false);
        C4901tL[] tLVarArr = this.f;
        if (tLVarArr == null) {
            x.s(24, false);
            return;
        }
        C1648aL t = x.t(tLVarArr.length, 24, -1);
        int i = 0;
        while (true) {
            C4901tL[] tLVarArr2 = this.f;
            if (i < tLVarArr2.length) {
                t.i(tLVarArr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }
}
