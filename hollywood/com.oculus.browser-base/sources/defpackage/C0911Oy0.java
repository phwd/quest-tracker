package defpackage;

/* renamed from: Oy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0911Oy0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public String f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0911Oy0() {
        super(32, 0);
    }

    public static C0911Oy0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0911Oy0 oy0 = new C0911Oy0(sDVar.c(b).b);
            oy0.d = sDVar.v(8, true);
            oy0.e = sDVar.v(16, true);
            oy0.f = sDVar.v(24, true);
            return oy0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, true);
        x.k(this.e, 16, true);
        x.k(this.f, 24, true);
    }

    public C0911Oy0(int i) {
        super(32, i);
    }
}
