package defpackage;

/* renamed from: zF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5906zF0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public byte[] d;
    public byte[] e;
    public byte[] f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5906zF0(int i) {
        super(32, i);
    }

    public static C5906zF0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5906zF0 zf0 = new C5906zF0(sDVar.c(b).b);
            zf0.d = sDVar.e(8, 1, -1);
            zf0.e = sDVar.e(16, 0, 32);
            zf0.f = sDVar.e(24, 1, 32);
            return zf0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.n(this.d, 8, 1, -1);
        x.n(this.e, 16, 0, 32);
        x.n(this.f, 24, 1, 32);
    }
}
