package defpackage;

import org.chromium.gfx.mojom.Rect;

/* renamed from: J5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class J5 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public Rect d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public J5() {
        super(16, 0);
    }

    public static J5 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            J5 j5 = new J5(sDVar.c(b).b);
            j5.d = Rect.d(sDVar.s(8, false));
            return j5;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public J5(int i) {
        super(16, i);
    }
}
