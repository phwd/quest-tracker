package defpackage;

/* renamed from: dB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2142dB0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C2788gz0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2142dB0() {
        super(16, 0);
    }

    public static C2142dB0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2142dB0 db0 = new C2142dB0(sDVar.c(b).b);
            db0.d = C2788gz0.d(sDVar.s(8, false));
            return db0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C2142dB0(int i) {
        super(16, i);
    }
}
