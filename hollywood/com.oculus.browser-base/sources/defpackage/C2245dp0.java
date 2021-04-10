package defpackage;

/* renamed from: dp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2245dp0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C3093in0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2245dp0() {
        super(16, 0);
    }

    public static C2245dp0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2245dp0 dp0 = new C2245dp0(sDVar.c(b).b);
            dp0.d = C3093in0.d(sDVar.s(8, true));
            return dp0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, true);
    }

    public C2245dp0(int i) {
        super(16, i);
    }
}
