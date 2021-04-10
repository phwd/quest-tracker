package X;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.0o4  reason: invalid class name */
public final class AnonymousClass0o4 extends AnonymousClass1lb implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.drawee.drawable.AutoRotateDrawable";
    @VisibleForTesting
    public float A00;
    public int A01;
    public boolean A02;
    public boolean A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0o4(Drawable drawable, int i) {
        super(drawable);
        if (drawable != null) {
            this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            this.A02 = false;
            this.A01 = i;
            this.A03 = true;
            return;
        }
        throw null;
    }

    public final void run() {
        this.A02 = false;
        this.A00 += (float) ((int) ((20.0f / ((float) this.A01)) * 360.0f));
        invalidateSelf();
    }

    @Override // X.AnonymousClass1lb
    public final void draw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = getBounds();
        int i = bounds.right;
        int i2 = bounds.left;
        int i3 = i - i2;
        int i4 = bounds.bottom;
        int i5 = bounds.top;
        int i6 = i4 - i5;
        float f = this.A00;
        if (!this.A03) {
            f = 360.0f - f;
        }
        canvas.rotate(f, (float) (i2 + (i3 >> 1)), (float) (i5 + (i6 >> 1)));
        super.draw(canvas);
        canvas.restoreToCount(save);
        if (!this.A02) {
            this.A02 = true;
            scheduleSelf(this, SystemClock.uptimeMillis() + 20);
        }
    }
}
