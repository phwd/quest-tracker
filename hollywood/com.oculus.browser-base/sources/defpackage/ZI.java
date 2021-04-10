package defpackage;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/* renamed from: ZI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZI extends YI {
    public boolean G = true;

    public ZI(Drawable drawable) {
        super(drawable);
    }

    @Override // defpackage.YI
    public void draw(Canvas canvas) {
        if (this.G) {
            this.F.draw(canvas);
        }
    }

    @Override // defpackage.YI
    public void setHotspot(float f, float f2) {
        if (this.G) {
            this.F.setHotspot(f, f2);
        }
    }

    @Override // defpackage.YI
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        if (this.G) {
            this.F.setHotspotBounds(i, i2, i3, i4);
        }
    }

    @Override // defpackage.YI
    public boolean setState(int[] iArr) {
        if (this.G) {
            return this.F.setState(iArr);
        }
        return false;
    }

    @Override // defpackage.YI
    public boolean setVisible(boolean z, boolean z2) {
        if (this.G) {
            return super.setVisible(z, z2);
        }
        return false;
    }
}
