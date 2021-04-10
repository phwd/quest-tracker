package defpackage;

/* renamed from: jh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3245jh extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public boolean d = true;
    public double e = 0.0d;
    public double f = Double.POSITIVE_INFINITY;
    public double g = 1.0d;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3245jh(int i) {
        super(40, i);
    }

    public static C3245jh d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3245jh jhVar = new C3245jh(sDVar.c(b).b);
            jhVar.d = sDVar.d(8, 0);
            jhVar.e = sDVar.k(16);
            jhVar.f = sDVar.k(24);
            jhVar.g = sDVar.k(32);
            return jhVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.m(this.d, 8, 0);
        x.a(this.e, 16);
        x.a(this.f, 24);
        x.a(this.g, 32);
    }
}
