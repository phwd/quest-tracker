package defpackage;

/* renamed from: KU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KU0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public HS0 e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public KU0(int i) {
        super(24, i);
    }

    public static KU0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            KU0 ku0 = new KU0(sDVar.c(b).b);
            ku0.d = sDVar.v(8, false);
            ku0.e = HS0.d(sDVar.s(16, false));
            return ku0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.i(this.e, 16, false);
    }
}
