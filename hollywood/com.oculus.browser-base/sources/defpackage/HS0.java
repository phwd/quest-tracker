package defpackage;

/* renamed from: HS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HS0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public long f;
    public AbstractC0874Oh g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public HS0(int i) {
        super(40, i);
    }

    public static HS0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            HS0 hs0 = new HS0(sDVar.c(b).b);
            hs0.d = sDVar.v(8, false);
            hs0.e = sDVar.v(16, false);
            hs0.f = sDVar.q(24);
            int i = AbstractC0874Oh.j;
            hs0.g = (AbstractC0874Oh) sDVar.t(32, false, AbstractC4445qi.f11156a);
            return hs0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.d(this.f, 24);
        AbstractC0874Oh oh = this.g;
        int i = AbstractC0874Oh.j;
        x.g(oh, 32, false, AbstractC4445qi.f11156a);
    }
}
