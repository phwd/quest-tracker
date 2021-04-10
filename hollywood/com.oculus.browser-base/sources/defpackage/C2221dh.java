package defpackage;

/* renamed from: dh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2221dh extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C3245jh d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2221dh() {
        super(16, 0);
    }

    public static C2221dh d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2221dh dhVar = new C2221dh(sDVar.c(b).b);
            dhVar.d = C3245jh.d(sDVar.s(8, false));
            return dhVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C2221dh(int i) {
        super(16, i);
    }
}
