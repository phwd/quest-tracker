package defpackage;

/* renamed from: ji  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3248ji extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public long d;
    public long e;
    public EC f = H30.F;
    public AbstractC0935Ph g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3248ji() {
        super(40, 0);
    }

    public static C3248ji d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3248ji jiVar = new C3248ji(sDVar.c(b).b);
            jiVar.d = sDVar.q(8);
            jiVar.e = sDVar.q(16);
            jiVar.f = sDVar.w(24, false).D();
            int i = AbstractC0935Ph.k;
            jiVar.g = (AbstractC0935Ph) sDVar.t(28, true, AbstractC1301Vh.f9099a);
            return jiVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.d(this.d, 8);
        x.d(this.e, 16);
        x.f(this.f, 24, false);
        AbstractC0935Ph ph = this.g;
        int i = AbstractC0935Ph.k;
        x.g(ph, 28, true, AbstractC1301Vh.f9099a);
    }

    public C3248ji(int i) {
        super(40, i);
    }
}
