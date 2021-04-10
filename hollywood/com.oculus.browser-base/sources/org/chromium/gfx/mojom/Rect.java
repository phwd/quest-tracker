package org.chromium.gfx.mojom;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Rect extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public int e;
    public int f;
    public int g;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public Rect(int i) {
        super(24, i);
    }

    public static Rect d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            Rect rect = new Rect(sDVar.c(b).b);
            rect.d = sDVar.n(8);
            rect.e = sDVar.n(12);
            rect.f = sDVar.n(16);
            rect.g = sDVar.n(20);
            return rect;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.c(this.e, 12);
        x.c(this.f, 16);
        x.c(this.g, 20);
    }
}
