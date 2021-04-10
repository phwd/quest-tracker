package defpackage;

/* renamed from: Z90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Z90 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public JT d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public Z90() {
        super(16, 0);
    }

    public static Z90 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            Z90 z90 = new Z90(sDVar.c(b).b);
            z90.d = JT.d(sDVar.s(8, false));
            return z90;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public Z90(int i) {
        super(16, i);
    }
}
