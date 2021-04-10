package defpackage;

/* renamed from: bp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1904bp0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public AbstractC4464qo0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1904bp0() {
        super(16, 0);
    }

    public static C1904bp0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1904bp0 bp0 = new C1904bp0(sDVar.c(b).b);
            int i = AbstractC4464qo0.v;
            bp0.d = (AbstractC4464qo0) sDVar.t(8, false, AbstractC5484wo0.f11570a);
            return bp0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        AbstractC4464qo0 qo0 = this.d;
        int i = AbstractC4464qo0.v;
        x.g(qo0, 8, false, AbstractC5484wo0.f11570a);
    }

    public C1904bp0(int i) {
        super(16, i);
    }
}
