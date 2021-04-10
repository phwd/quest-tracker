package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import com.oculus.browser.R;

/* renamed from: wa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5441wa extends Drawable implements Drawable.Callback {
    public final Rect F = new Rect();
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final Path f11549J;
    public final Paint K;
    public final Drawable L;
    public int M;
    public boolean N;
    public boolean O;

    public C5441wa(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f25900_resource_name_obfuscated_RES_2131166209);
        this.G = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.f25890_resource_name_obfuscated_RES_2131166208);
        this.H = dimensionPixelSize2;
        int dimensionPixelSize3 = context.getResources().getDimensionPixelSize(R.dimen.f25880_resource_name_obfuscated_RES_2131166207);
        this.I = dimensionPixelSize3;
        Path path = new Path();
        this.f11549J = path;
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(((float) (-dimensionPixelSize2)) / 2.0f, (float) dimensionPixelSize3);
        path.lineTo(0.0f, 0.0f);
        path.lineTo(((float) dimensionPixelSize2) / 2.0f, (float) dimensionPixelSize3);
        path.lineTo(((float) (-dimensionPixelSize2)) / 2.0f, (float) dimensionPixelSize3);
        path.close();
        Paint paint = new Paint(1);
        this.K = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{(float) dimensionPixelSize, (float) dimensionPixelSize, (float) dimensionPixelSize, (float) dimensionPixelSize, (float) dimensionPixelSize, (float) dimensionPixelSize, (float) dimensionPixelSize, (float) dimensionPixelSize}, null, null));
        this.L = shapeDrawable;
        shapeDrawable.setCallback(this);
    }

    public void draw(Canvas canvas) {
        this.L.draw(canvas);
        if (this.O) {
            canvas.save();
            if (!this.N) {
                int height = getBounds().height() - (this.I / 2);
                canvas.scale(1.0f, -1.0f, (float) this.M, (float) height);
                canvas.translate(0.0f, (float) (height - (this.I / 2)));
            }
            canvas.translate((float) this.M, 0.0f);
            canvas.drawPath(this.f11549J, this.K);
            canvas.restore();
        }
    }

    public int getOpacity() {
        return -3;
    }

    public boolean getPadding(Rect rect) {
        this.L.getPadding(rect);
        int i = rect.left;
        int i2 = 0;
        int max = Math.max(rect.top, this.N ? this.I : 0);
        int i3 = rect.right;
        int i4 = rect.bottom;
        if (!this.N) {
            i2 = this.I;
        }
        rect.set(i, max, i3, Math.max(i4, i2));
        return true;
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (rect != null) {
            this.L.getPadding(this.F);
            Drawable drawable = this.L;
            int i = rect.left;
            int i2 = rect.top;
            boolean z = this.N;
            int i3 = 0;
            int i4 = i2 + (z ? this.I - this.F.top : 0);
            int i5 = rect.right;
            int i6 = rect.bottom;
            if (!z) {
                i3 = this.I - this.F.bottom;
            }
            drawable.setBounds(i, i4, i5, i6 - i3);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.L.setAlpha(i);
        this.K.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
