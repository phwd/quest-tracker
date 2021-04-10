package defpackage;

/* renamed from: JI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public boolean f;
    public int g;
    public C0087Bi1 h;
    public boolean i;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public JI0(int i2) {
        super(40, i2);
    }

    public static JI0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            JI0 ji0 = new JI0(sDVar.c(b).b);
            ji0.d = sDVar.v(8, false);
            ji0.e = sDVar.v(16, false);
            ji0.f = sDVar.d(24, 0);
            ji0.i = sDVar.d(24, 1);
            ji0.g = sDVar.n(28);
            ji0.h = C0087Bi1.d(sDVar.s(32, false));
            return ji0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.m(this.f, 24, 0);
        x.m(this.i, 24, 1);
        x.c(this.g, 28);
        x.i(this.h, 32, false);
    }
}
