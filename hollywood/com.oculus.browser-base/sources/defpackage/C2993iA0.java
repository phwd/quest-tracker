package defpackage;

/* renamed from: iA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2993iA0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0911Oy0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2993iA0() {
        super(16, 0);
    }

    public static C2993iA0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2993iA0 ia0 = new C2993iA0(sDVar.c(b).b);
            ia0.d = C0911Oy0.d(sDVar.s(8, false));
            return ia0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C2993iA0(int i) {
        super(16, i);
    }
}
