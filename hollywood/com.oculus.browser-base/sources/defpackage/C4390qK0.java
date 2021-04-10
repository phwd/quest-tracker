package defpackage;

import android.graphics.Rect;

/* renamed from: qK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4390qK0 {
    public final Rect F;
    public AbstractC4219pK0 G;

    public C4390qK0() {
        this.F = new Rect();
    }

    public void a() {
        AbstractC4219pK0 pk0 = this.G;
        if (pk0 != null) {
            pk0.b();
        }
    }

    public void b(AbstractC4219pK0 pk0) {
        this.G = pk0;
    }

    public void c() {
        this.G = null;
    }

    public C4390qK0(Rect rect) {
        Rect rect2 = new Rect();
        this.F = rect2;
        rect2.set(rect);
    }
}
