package defpackage;

/* renamed from: gF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2665gF extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public YE e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2665gF() {
        super(24, 0);
    }

    public static C2665gF d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2665gF gFVar = new C2665gF(sDVar.c(b).b);
            gFVar.d = sDVar.n(8);
            int i = YE.l;
            gFVar.e = (YE) sDVar.t(12, true, BF.f7726a);
            return gFVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        YE ye = this.e;
        int i = YE.l;
        x.g(ye, 12, true, BF.f7726a);
    }

    public C2665gF(int i) {
        super(24, i);
    }
}
