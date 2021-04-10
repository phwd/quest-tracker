package defpackage;

/* renamed from: Y90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Y90 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public KT d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public Y90() {
        super(16, 0);
    }

    public static Y90 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            Y90 y90 = new Y90(sDVar.c(b).b);
            y90.d = KT.d(sDVar.s(8, false));
            return y90;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public Y90(int i) {
        super(16, i);
    }
}
