package defpackage;

/* renamed from: sI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4725sI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public byte[] e;
    public int[] f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4725sI0(int i) {
        super(32, i);
    }

    public static C4725sI0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4725sI0 si0 = new C4725sI0(sDVar.c(b).b);
            int n = sDVar.n(8);
            si0.d = n;
            AbstractC5575xI0.a(n);
            int i = 0;
            si0.e = sDVar.e(16, 0, -1);
            si0.f = sDVar.p(24, 0, -1);
            while (true) {
                int[] iArr = si0.f;
                if (i >= iArr.length) {
                    return si0;
                }
                AbstractC0186Db.a(iArr[i]);
                i++;
            }
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.n(this.e, 16, 0, -1);
        x.p(this.f, 24, 0, -1);
    }
}
