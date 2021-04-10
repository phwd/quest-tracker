package defpackage;

/* renamed from: cB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1971cB0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public boolean d;
    public boolean e;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1971cB0() {
        super(16, 0);
    }

    public static C1971cB0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1971cB0 cb0 = new C1971cB0(sDVar.c(b).b);
            cb0.d = sDVar.d(8, 0);
            cb0.e = sDVar.d(8, 1);
            return cb0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.m(this.d, 8, 0);
        x.m(this.e, 8, 1);
    }

    public C1971cB0(int i) {
        super(16, i);
    }
}
