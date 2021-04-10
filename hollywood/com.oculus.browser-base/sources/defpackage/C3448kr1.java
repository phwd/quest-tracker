package defpackage;

/* renamed from: kr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3448kr1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public DC d = H30.F;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3448kr1() {
        super(16, 0);
    }

    public static C3448kr1 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3448kr1 kr1 = new C3448kr1(sDVar.c(b).b);
            kr1.d = sDVar.w(8, false).X();
            return kr1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).f(this.d, 8, false);
    }

    public C3448kr1(int i) {
        super(16, i);
    }
}
