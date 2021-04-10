package defpackage;

/* renamed from: ln0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3606ln0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public byte[] j;
    public C3264jn0 k;

    static {
        CC[] ccArr = {new CC(72, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3606ln0() {
        super(72, 0);
    }

    public static C3606ln0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3606ln0 ln0 = new C3606ln0(sDVar.c(b).b);
            int n = sDVar.n(8);
            ln0.d = n;
            if (n >= 0 && n <= 2) {
                ln0.e = sDVar.v(16, false);
                ln0.f = sDVar.v(24, true);
                ln0.g = sDVar.v(32, true);
                ln0.h = sDVar.v(40, true);
                ln0.i = sDVar.v(48, true);
                ln0.j = sDVar.e(56, 0, -1);
                ln0.k = C3264jn0.d(sDVar.s(64, true));
                return ln0;
            }
            throw new C4200pE("Invalid enum value.");
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.k(this.e, 16, false);
        x.k(this.f, 24, true);
        x.k(this.g, 32, true);
        x.k(this.h, 40, true);
        x.k(this.i, 48, true);
        x.n(this.j, 56, 0, -1);
        x.i(this.k, 64, true);
    }

    public C3606ln0(int i2) {
        super(72, i2);
    }
}
