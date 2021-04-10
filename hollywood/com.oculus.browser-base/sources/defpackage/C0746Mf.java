package defpackage;

/* renamed from: Mf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0746Mf extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0509Ih d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0746Mf() {
        super(16, 0);
    }

    public static C0746Mf d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0746Mf mf = new C0746Mf(sDVar.c(b).b);
            mf.d = C0509Ih.d(sDVar.s(8, false));
            return mf;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C0746Mf(int i) {
        super(16, i);
    }
}
