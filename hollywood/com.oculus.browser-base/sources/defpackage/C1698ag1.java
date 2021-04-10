package defpackage;

/* renamed from: ag1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1698ag1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0509Ih d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1698ag1() {
        super(16, 0);
    }

    public static C1698ag1 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1698ag1 ag1 = new C1698ag1(sDVar.c(b).b);
            ag1.d = C0509Ih.d(sDVar.s(8, false));
            return ag1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C1698ag1(int i) {
        super(16, i);
    }
}
