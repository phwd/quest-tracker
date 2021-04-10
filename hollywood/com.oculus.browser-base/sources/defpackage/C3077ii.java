package defpackage;

/* renamed from: ii  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3077ii extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public EC d = H30.F;
    public AbstractC0935Ph e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3077ii() {
        super(24, 0);
    }

    public static C3077ii d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3077ii iiVar = new C3077ii(sDVar.c(b).b);
            iiVar.d = sDVar.w(8, false).D();
            int i = AbstractC0935Ph.k;
            iiVar.e = (AbstractC0935Ph) sDVar.t(12, true, AbstractC1301Vh.f9099a);
            return iiVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.f(this.d, 8, false);
        AbstractC0935Ph ph = this.e;
        int i = AbstractC0935Ph.k;
        x.g(ph, 12, true, AbstractC1301Vh.f9099a);
    }

    public C3077ii(int i) {
        super(24, i);
    }
}
