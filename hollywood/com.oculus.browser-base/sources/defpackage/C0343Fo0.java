package defpackage;

/* renamed from: Fo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0343Fo0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public B30 e;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0343Fo0() {
        super(16, 0);
    }

    public static C0343Fo0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0343Fo0 fo0 = new C0343Fo0(sDVar.c(b).b);
            fo0.d = sDVar.n(8);
            fo0.e = sDVar.o(12, false);
            return fo0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.h(this.e, 12, false);
    }

    public C0343Fo0(int i) {
        super(16, i);
    }
}
