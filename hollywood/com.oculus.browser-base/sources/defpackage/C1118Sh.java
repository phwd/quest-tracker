package defpackage;

/* renamed from: Sh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1118Sh extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public long e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1118Sh() {
        super(24, 0);
    }

    public static C1118Sh d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1118Sh sh = new C1118Sh(sDVar.c(b).b);
            sh.d = sDVar.n(8);
            sh.e = sDVar.q(16);
            return sh;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.d(this.e, 16);
    }

    public C1118Sh(int i) {
        super(24, i);
    }
}
