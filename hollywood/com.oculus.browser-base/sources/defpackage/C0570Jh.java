package defpackage;

/* renamed from: Jh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0570Jh extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public int e;
    public int f;
    public float[] g;
    public float[] h;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0570Jh(int i) {
        super(40, i);
    }

    public static C0570Jh d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0570Jh jh = new C0570Jh(sDVar.c(b).b);
            int n = sDVar.n(8);
            jh.d = n;
            if (n >= 0 && n <= 3) {
                jh.e = sDVar.n(12);
                jh.f = sDVar.n(16);
                jh.g = sDVar.m(24, 1, 7);
                jh.h = sDVar.m(32, 1, 9);
                return jh;
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
        x.c(this.e, 12);
        x.c(this.f, 16);
        x.o(this.g, 24, 1, 7);
        x.o(this.h, 32, 1, 9);
    }
}
