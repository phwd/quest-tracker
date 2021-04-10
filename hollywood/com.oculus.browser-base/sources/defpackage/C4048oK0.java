package defpackage;

/* renamed from: oK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4048oK0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public float d;
    public float e;
    public float f;
    public float g;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4048oK0() {
        super(24, 0);
    }

    public static C4048oK0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4048oK0 ok0 = new C4048oK0(sDVar.c(b).b);
            ok0.d = sDVar.l(8);
            ok0.e = sDVar.l(12);
            ok0.f = sDVar.l(16);
            ok0.g = sDVar.l(20);
            return ok0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.b(this.d, 8);
        x.b(this.e, 12);
        x.b(this.f, 16);
        x.b(this.g, 20);
    }

    public C4048oK0(int i) {
        super(24, i);
    }
}
