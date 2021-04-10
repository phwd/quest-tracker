package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* renamed from: Dh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0204Dh extends HJ {
    public final int F;
    public Bitmap G;
    public final Rect H = new Rect();
    public boolean I = true;

    public C0204Dh(int i) {
        this.F = i;
    }

    @Override // defpackage.AbstractC3197jM0
    public Rect a() {
        return this.H;
    }

    @Override // defpackage.AbstractC3197jM0
    public C3783mp0 b() {
        return null;
    }

    @Override // defpackage.AbstractC3197jM0
    public Bitmap c() {
        this.I = false;
        Bitmap bitmap = this.G;
        this.G = null;
        return bitmap;
    }

    @Override // defpackage.AbstractC3197jM0
    public long d() {
        return AbstractC4052oM0.a(null);
    }

    @Override // defpackage.HJ
    public boolean e() {
        return this.I;
    }

    public void f(Bitmap bitmap) {
        if (bitmap != null) {
            this.I = true;
            this.G = bitmap;
            this.H.set(0, 0, bitmap.getWidth(), this.G.getHeight());
        }
    }
}
