package defpackage;

/* renamed from: Yo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1501Yo0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C3093in0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1501Yo0() {
        super(16, 0);
    }

    public static C1501Yo0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1501Yo0 yo0 = new C1501Yo0(sDVar.c(b).b);
            yo0.d = C3093in0.d(sDVar.s(8, true));
            return yo0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, true);
    }

    public C1501Yo0(int i) {
        super(16, i);
    }
}
