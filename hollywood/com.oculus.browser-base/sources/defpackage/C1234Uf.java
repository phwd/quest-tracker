package defpackage;

/* renamed from: Uf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1234Uf extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1234Uf(int i) {
        super(16, i);
    }

    public static C1234Uf d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1234Uf uf = new C1234Uf(sDVar.c(b).b);
            int i = 0;
            uf.d = sDVar.p(8, 0, -1);
            while (true) {
                int[] iArr = uf.d;
                if (i >= iArr.length) {
                    return uf;
                }
                AbstractC1295Vf.a(iArr[i]);
                i++;
            }
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).p(this.d, 8, 0, -1);
    }
}
