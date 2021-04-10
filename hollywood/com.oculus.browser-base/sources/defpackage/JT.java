package defpackage;

/* renamed from: JT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JT extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public String e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public JT(int i) {
        super(24, i);
    }

    public static JT d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            JT jt = new JT(sDVar.c(b).b);
            int n = sDVar.n(8);
            jt.d = n;
            if (n >= 0 && n <= 2) {
                jt.e = sDVar.v(16, false);
                return jt;
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
}
