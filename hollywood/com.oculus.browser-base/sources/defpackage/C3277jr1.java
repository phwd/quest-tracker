package defpackage;

/* renamed from: jr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3277jr1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4984tr1 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3277jr1() {
        super(16, 0);
    }

    public static C3277jr1 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3277jr1 jr1 = new C3277jr1(sDVar.c(b).b);
            C4984tr1.d(sDVar.s(8, false));
            jr1.d = null;
            return jr1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C3277jr1(int i) {
        super(16, i);
    }
}
