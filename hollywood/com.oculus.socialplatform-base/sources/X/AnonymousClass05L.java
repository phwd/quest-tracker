package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

/* renamed from: X.05L  reason: invalid class name */
public abstract class AnonymousClass05L extends Drawable {
    public float A00;
    public int A01;
    public int A02;
    public int A03 = 160;
    public boolean A04 = true;
    public boolean A05;
    public int A06 = 119;
    public final Rect A07 = new Rect();
    public final Bitmap A08;
    public final BitmapShader A09;
    public final Paint A0A = new Paint(3);
    public final Matrix A0B = new Matrix();
    public final RectF A0C = new RectF();

    public final void A00() {
        Rect rect;
        if (this.A04) {
            if (this.A05) {
                int min = Math.min(this.A02, this.A01);
                int i = this.A06;
                Rect bounds = getBounds();
                rect = this.A07;
                A02(i, min, min, bounds, rect);
                int min2 = Math.min(rect.width(), rect.height());
                rect.inset(Math.max(0, (rect.width() - min2) >> 1), Math.max(0, (rect.height() - min2) >> 1));
                this.A00 = ((float) min2) * 0.5f;
            } else {
                int i2 = this.A06;
                int i3 = this.A02;
                int i4 = this.A01;
                Rect bounds2 = getBounds();
                rect = this.A07;
                A02(i2, i3, i4, bounds2, rect);
            }
            RectF rectF = this.A0C;
            rectF.set(rect);
            BitmapShader bitmapShader = this.A09;
            if (bitmapShader != null) {
                Matrix matrix = this.A0B;
                matrix.setTranslate(rectF.left, rectF.top);
                float width = rectF.width();
                Bitmap bitmap = this.A08;
                matrix.preScale(width / ((float) bitmap.getWidth()), rectF.height() / ((float) bitmap.getHeight()));
                bitmapShader.setLocalMatrix(matrix);
                this.A0A.setShader(bitmapShader);
            }
            this.A04 = false;
        }
    }

    public final void A01(float f) {
        BitmapShader bitmapShader;
        if (this.A00 != f) {
            this.A05 = false;
            int i = (f > 0.05f ? 1 : (f == 0.05f ? 0 : -1));
            Paint paint = this.A0A;
            if (i > 0) {
                bitmapShader = this.A09;
            } else {
                bitmapShader = null;
            }
            paint.setShader(bitmapShader);
            this.A00 = f;
            invalidateSelf();
        }
    }

    public void A02(int i, int i2, int i3, Rect rect, Rect rect2) {
        throw new UnsupportedOperationException();
    }

    public final void draw(@NonNull Canvas canvas) {
        Bitmap bitmap = this.A08;
        if (bitmap != null) {
            A00();
            Paint paint = this.A0A;
            if (paint.getShader() == null) {
                canvas.drawBitmap(bitmap, (Rect) null, this.A07, paint);
                return;
            }
            RectF rectF = this.A0C;
            float f = this.A00;
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    public final int getAlpha() {
        return this.A0A.getAlpha();
    }

    public final ColorFilter getColorFilter() {
        return this.A0A.getColorFilter();
    }

    public final int getOpacity() {
        Bitmap bitmap;
        if (this.A06 != 119 || this.A05 || (bitmap = this.A08) == null || bitmap.hasAlpha() || this.A0A.getAlpha() < 255 || this.A00 > 0.05f) {
            return -3;
        }
        return -1;
    }

    public final void setAlpha(int i) {
        Paint paint = this.A0A;
        if (i != paint.getAlpha()) {
            paint.setAlpha(i);
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.A0A.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final void setDither(boolean z) {
        this.A0A.setDither(z);
        invalidateSelf();
    }

    public final void setFilterBitmap(boolean z) {
        this.A0A.setFilterBitmap(z);
        invalidateSelf();
    }

    public AnonymousClass05L(Resources resources, Bitmap bitmap) {
        if (resources != null) {
            this.A03 = resources.getDisplayMetrics().densityDpi;
        }
        this.A08 = bitmap;
        if (bitmap != null) {
            this.A02 = bitmap.getScaledWidth(this.A03);
            this.A01 = bitmap.getScaledHeight(this.A03);
            Bitmap bitmap2 = this.A08;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.A09 = new BitmapShader(bitmap2, tileMode, tileMode);
            return;
        }
        this.A01 = -1;
        this.A02 = -1;
    }

    public final int getIntrinsicHeight() {
        return this.A01;
    }

    public final int getIntrinsicWidth() {
        return this.A02;
    }

    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.A05) {
            this.A00 = (float) (Math.min(this.A01, this.A02) >> 1);
        }
        this.A04 = true;
    }
}
