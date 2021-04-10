package defpackage;

/* renamed from: yI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5745yI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public byte[] d;
    public String e;
    public Cq1 f;
    public String g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5745yI0(int i) {
        super(40, i);
    }

    public static C5745yI0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5745yI0 yi0 = new C5745yI0(sDVar.c(b).b);
            yi0.d = sDVar.e(8, 0, -1);
            yi0.e = sDVar.v(16, false);
            yi0.f = Cq1.d(sDVar.s(24, true));
            yi0.g = sDVar.v(32, false);
            return yi0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.n(this.d, 8, 0, -1);
        x.k(this.e, 16, false);
        x.i(this.f, 24, true);
        x.k(this.g, 32, false);
    }
}
