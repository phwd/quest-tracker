package defpackage;

/* renamed from: kB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3337kB0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public C2617fz0 f;
    public boolean g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3337kB0() {
        super(40, 0);
    }

    public static C3337kB0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3337kB0 kb0 = new C3337kB0(sDVar.c(b).b);
            kb0.d = sDVar.v(8, false);
            kb0.e = sDVar.v(16, false);
            kb0.f = C2617fz0.d(sDVar.s(24, false));
            kb0.g = sDVar.d(32, 0);
            return kb0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.i(this.f, 24, false);
        x.m(this.g, 32, 0);
    }

    public C3337kB0(int i) {
        super(40, i);
    }
}
