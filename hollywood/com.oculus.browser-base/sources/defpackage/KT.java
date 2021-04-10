package defpackage;

/* renamed from: KT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KT extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4479qt0 d;
    public String e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public KT(int i) {
        super(24, i);
    }

    public static KT d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            KT kt = new KT(sDVar.c(b).b);
            kt.d = C4479qt0.d(sDVar.s(8, false));
            kt.e = sDVar.v(16, false);
            return kt;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.k(this.e, 16, false);
    }
}
