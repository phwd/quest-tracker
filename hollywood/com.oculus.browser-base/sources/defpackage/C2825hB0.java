package defpackage;

/* renamed from: hB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2825hB0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public C1033Qy0 f;
    public String g;
    public C0911Oy0 h;

    static {
        CC[] ccArr = {new CC(48, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2825hB0() {
        super(48, 0);
    }

    public static C2825hB0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C2825hB0 hb0 = new C2825hB0(sDVar.c(b).b);
            hb0.d = sDVar.v(8, false);
            hb0.e = sDVar.v(16, false);
            hb0.f = C1033Qy0.d(sDVar.s(24, true));
            hb0.g = sDVar.v(32, true);
            hb0.h = C0911Oy0.d(sDVar.s(40, false));
            return hb0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.i(this.f, 24, true);
        x.k(this.g, 32, true);
        x.i(this.h, 40, false);
    }

    public C2825hB0(int i) {
        super(48, i);
    }
}
