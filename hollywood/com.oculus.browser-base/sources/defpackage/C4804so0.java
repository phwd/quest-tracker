package defpackage;

/* renamed from: so0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4804so0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C3093in0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4804so0() {
        super(16, 0);
    }

    public static C4804so0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C4804so0 so0 = new C4804so0(sDVar.c(b).b);
            so0.d = C3093in0.d(sDVar.s(8, false));
            return so0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C4804so0(int i) {
        super(16, i);
    }
}
