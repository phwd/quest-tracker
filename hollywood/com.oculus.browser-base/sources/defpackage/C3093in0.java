package defpackage;

/* renamed from: in0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3093in0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public String e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3093in0() {
        super(24, 0);
    }

    public static C3093in0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3093in0 in0 = new C3093in0(sDVar.c(b).b);
            int n = sDVar.n(8);
            in0.d = n;
            if (n >= 0 && n <= 5) {
                in0.e = sDVar.v(16, false);
                return in0;
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
    }

    public C3093in0(int i) {
        super(24, i);
    }
}
