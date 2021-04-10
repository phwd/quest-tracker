package defpackage;

/* renamed from: zL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5922zL0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public String f;
    public String g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5922zL0(int i) {
        super(40, i);
    }

    public static C5922zL0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5922zL0 zl0 = new C5922zL0(sDVar.c(b).b);
            zl0.d = sDVar.v(8, false);
            zl0.e = sDVar.v(16, true);
            zl0.f = sDVar.v(24, true);
            zl0.g = sDVar.v(32, true);
            return zl0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, true);
        x.k(this.f, 24, true);
        x.k(this.g, 32, true);
    }
}
