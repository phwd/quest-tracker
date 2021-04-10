package defpackage;

/* renamed from: Zh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1545Zh extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public long d;
    public C0026Ai1 e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1545Zh() {
        super(24, 0);
    }

    public static C1545Zh d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1545Zh zh = new C1545Zh(sDVar.c(b).b);
            zh.d = sDVar.q(8);
            zh.e = C0026Ai1.d(sDVar.s(16, true));
            return zh;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.d(this.d, 8);
        x.i(this.e, 16, true);
    }

    public C1545Zh(int i) {
        super(24, i);
    }
}
