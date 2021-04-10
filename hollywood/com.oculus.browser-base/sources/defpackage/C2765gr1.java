package defpackage;

/* renamed from: gr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2765gr1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4644rr1 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2765gr1() {
        super(16, 0);
    }

    /* JADX INFO: finally extract failed */
    public static C2765gr1 d(C2740gj0 gj0) {
        C4644rr1 rr1;
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2765gr1 gr1 = new C2765gr1(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC[] ccArr = C4644rr1.b;
            if (s == null) {
                rr1 = null;
            } else {
                s.b();
                try {
                    C4644rr1 rr12 = new C4644rr1(s.c(C4644rr1.b).b);
                    s.a();
                    rr1 = rr12;
                } catch (Throwable th) {
                    s.a();
                    throw th;
                }
            }
            gr1.d = rr1;
            return gr1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C2765gr1(int i) {
        super(16, i);
    }
}
