package defpackage;

/* renamed from: bB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1800bB0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public BB0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1800bB0() {
        super(16, 0);
    }

    public static C1800bB0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1800bB0 bb0 = new C1800bB0(sDVar.c(b).b);
            bb0.d = BB0.d(sDVar.s(8, false));
            return bb0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C1800bB0(int i) {
        super(16, i);
    }
}
