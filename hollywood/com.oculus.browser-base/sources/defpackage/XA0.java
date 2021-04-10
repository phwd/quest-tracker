package defpackage;

/* renamed from: XA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class XA0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public XA0() {
        super(16, 0);
    }

    public static XA0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            XA0 xa0 = new XA0(sDVar.c(b).b);
            int n = sDVar.n(8);
            xa0.d = n;
            if (n >= 0 && n <= 2) {
                return xa0;
            }
            throw new C4200pE("Invalid enum value.");
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).c(this.d, 8);
    }

    public XA0(int i) {
        super(16, i);
    }
}
