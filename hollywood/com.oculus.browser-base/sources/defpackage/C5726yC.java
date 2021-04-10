package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* renamed from: yC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5726yC extends C3234jd0 {
    public final Paint d0;
    public final RectF e0;
    public int f0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5726yC(C3553lT0 lt0) {
        super(lt0 == null ? new C3553lT0() : lt0);
        Paint paint = new Paint(1);
        this.d0 = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.e0 = new RectF();
    }

    @Override // defpackage.C3234jd0
    public void draw(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (callback instanceof View) {
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, null);
            }
        } else {
            this.f0 = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), null);
        }
        super.draw(canvas);
        canvas.drawRect(this.e0, this.d0);
        if (!(getCallback() instanceof View)) {
            canvas.restoreToCount(this.f0);
        }
    }

    public void t(float f, float f2, float f3, float f4) {
        RectF rectF = this.e0;
        if (f != rectF.left || f2 != rectF.top || f3 != rectF.right || f4 != rectF.bottom) {
            rectF.set(f, f2, f3, f4);
            invalidateSelf();
        }
    }
}
