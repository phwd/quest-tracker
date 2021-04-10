package defpackage;

/* renamed from: wN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5415wN extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public B30 d;
    public KN e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5415wN() {
        super(24, 0);
    }

    /* JADX INFO: finally extract failed */
    public static C5415wN d(C2740gj0 gj0) {
        KN kn;
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C5415wN wNVar = new C5415wN(sDVar.c(b).b);
            wNVar.d = sDVar.o(8, false);
            C4709sD s = sDVar.s(16, false);
            CC[] ccArr = KN.b;
            if (s == null) {
                kn = null;
            } else {
                s.b();
                try {
                    KN kn2 = new KN(s.c(KN.b).b);
                    kn2.d = s.n(8);
                    kn2.e = s.d(12, 0);
                    s.a();
                    kn = kn2;
                } catch (Throwable th) {
                    s.a();
                    throw th;
                }
            }
            wNVar.e = kn;
            return wNVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.h(this.d, 8, false);
        x.i(this.e, 16, false);
    }

    public C5415wN(int i) {
        super(24, i);
    }
}
