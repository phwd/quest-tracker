package defpackage;

/* renamed from: mr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3790mr1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public long d;
    public long e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3790mr1() {
        super(24, 0);
    }

    public static C3790mr1 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3790mr1 mr1 = new C3790mr1(sDVar.c(b).b);
            mr1.d = sDVar.q(8);
            mr1.e = sDVar.q(16);
            return mr1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.d(this.d, 8);
        x.d(this.e, 16);
    }

    public C3790mr1(int i) {
        super(24, i);
    }
}
