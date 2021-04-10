package defpackage;

/* renamed from: Ai1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0026Ai1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public long d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0026Ai1(int i) {
        super(16, i);
    }

    public static C0026Ai1 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0026Ai1 ai1 = new C0026Ai1(sDVar.c(b).b);
            ai1.d = sDVar.q(8);
            return ai1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).d(this.d, 8);
    }
}
