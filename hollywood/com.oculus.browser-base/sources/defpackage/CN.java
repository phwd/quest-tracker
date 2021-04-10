package defpackage;

/* renamed from: CN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CN extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0509Ih d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public CN() {
        super(16, 0);
    }

    public static CN d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            CN cn = new CN(sDVar.c(b).b);
            cn.d = C0509Ih.d(sDVar.s(8, false));
            return cn;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public CN(int i) {
        super(16, i);
    }
}
