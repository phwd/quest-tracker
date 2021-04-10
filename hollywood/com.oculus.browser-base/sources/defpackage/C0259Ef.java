package defpackage;

/* renamed from: Ef  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0259Ef extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0259Ef() {
        super(16, 0);
    }

    public static C0259Ef d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0259Ef ef = new C0259Ef(sDVar.c(b).b);
            int i = 0;
            ef.d = sDVar.p(8, 0, -1);
            while (true) {
                int[] iArr = ef.d;
                if (i >= iArr.length) {
                    return ef;
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

    public C0259Ef(int i) {
        super(16, i);
    }
}
