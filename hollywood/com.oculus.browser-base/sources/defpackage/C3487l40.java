package defpackage;

/* renamed from: l40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3487l40 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public String f;
    public C2617fz0 g;
    public String h;
    public String i;
    public C2617fz0 j;
    public String k;

    static {
        CC[] ccArr = {new CC(72, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3487l40(int i2) {
        super(72, i2);
    }

    public static C3487l40 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3487l40 l40 = new C3487l40(sDVar.c(b).b);
            l40.d = sDVar.v(8, false);
            l40.e = sDVar.v(16, false);
            l40.f = sDVar.v(24, false);
            l40.g = C2617fz0.d(sDVar.s(32, false));
            l40.h = sDVar.v(40, true);
            l40.i = sDVar.v(48, true);
            l40.j = C2617fz0.d(sDVar.s(56, true));
            l40.k = sDVar.v(64, true);
            return l40;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.k(this.f, 24, false);
        x.i(this.g, 32, false);
        x.k(this.h, 40, true);
        x.k(this.i, 48, true);
        x.i(this.j, 56, true);
        x.k(this.k, 64, true);
    }
}
