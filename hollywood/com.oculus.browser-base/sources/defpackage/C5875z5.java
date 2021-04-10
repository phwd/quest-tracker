package defpackage;

import org.chromium.gfx.mojom.Rect;

/* renamed from: z5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5875z5 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C5318vp1 d;
    public Rect e;
    public boolean f;
    public boolean g;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5875z5(int i) {
        super(32, i);
    }

    public static C5875z5 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5875z5 z5Var = new C5875z5(sDVar.c(b).b);
            z5Var.d = C5318vp1.d(sDVar.s(8, false));
            z5Var.e = Rect.d(sDVar.s(16, false));
            z5Var.f = sDVar.d(24, 0);
            z5Var.g = sDVar.d(24, 1);
            return z5Var;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.i(this.e, 16, false);
        x.m(this.f, 24, 0);
        x.m(this.g, 24, 1);
    }
}
