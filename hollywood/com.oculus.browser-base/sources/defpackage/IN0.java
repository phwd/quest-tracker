package defpackage;

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

/* renamed from: IN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IN0 extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public final Bitmap f8222a;
    public int b = 160;
    public int c = 119;
    public final Paint d = new Paint(3);
    public final BitmapShader e;
    public final Matrix f = new Matrix();
    public float g;
    public final Rect h = new Rect();
    public final RectF i = new RectF();
    public boolean j = true;
    public boolean k;
    public int l;
    public int m;

    public IN0(Resources resources, Bitmap bitmap) {
        if (resources != null) {
            this.b = resources.getDisplayMetrics().densityDpi;
        }
        this.f8222a = bitmap;
        if (bitmap != null) {
            this.l = bitmap.getScaledWidth(this.b);
            this.m = bitmap.getScaledHeight(this.b);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.e = new BitmapShader(bitmap, tileMode, tileMode);
            return;
        }
        this.m = -1;
        this.l = -1;
        this.e = null;
    }

    public abstract void a(int i2, int i3, int i4, Rect rect, Rect rect2);

    public void b(float f2) {
        if (this.g != f2) {
            boolean z = false;
            this.k = false;
            if (f2 > 0.05f) {
                z = true;
            }
            if (z) {
                this.d.setShader(this.e);
            } else {
                this.d.setShader(null);
            }
            this.g = f2;
            invalidateSelf();
        }
    }

    public void c() {
        if (this.j) {
            if (this.k) {
                int min = Math.min(this.l, this.m);
                a(this.c, min, min, getBounds(), this.h);
                int min2 = Math.min(this.h.width(), this.h.height());
                this.h.inset(Math.max(0, (this.h.width() - min2) / 2), Math.max(0, (this.h.height() - min2) / 2));
                this.g = ((float) min2) * 0.5f;
            } else {
                a(this.c, this.l, this.m, getBounds(), this.h);
            }
            this.i.set(this.h);
            if (this.e != null) {
                Matrix matrix = this.f;
                RectF rectF = this.i;
                matrix.setTranslate(rectF.left, rectF.top);
                this.f.preScale(this.i.width() / ((float) this.f8222a.getWidth()), this.i.height() / ((float) this.f8222a.getHeight()));
                this.e.setLocalMatrix(this.f);
                this.d.setShader(this.e);
            }
            this.j = false;
        }
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.f8222a;
        if (bitmap != null) {
            c();
            if (this.d.getShader() == null) {
                canvas.drawBitmap(bitmap, (Rect) null, this.h, this.d);
                return;
            }
            RectF rectF = this.i;
            float f2 = this.g;
            canvas.drawRoundRect(rectF, f2, f2, this.d);
        }
    }

    public int getAlpha() {
        return this.d.getAlpha();
    }

    public ColorFilter getColorFilter() {
        return this.d.getColorFilter();
    }

    public int getIntrinsicHeight() {
        return this.m;
    }

    public int getIntrinsicWidth() {
        return this.l;
    }

    public int getOpacity() {
        Bitmap bitmap;
        if (this.c == 119 && !this.k && (bitmap = this.f8222a) != null && !bitmap.hasAlpha() && this.d.getAlpha() >= 255) {
            return (this.g > 0.05f ? 1 : (this.g == 0.05f ? 0 : -1)) > 0 ? -3 : -1;
        }
    }

    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.k) {
            this.g = (float) (Math.min(this.m, this.l) / 2);
        }
        this.j = true;
    }

    public void setAlpha(int i2) {
        if (i2 != this.d.getAlpha()) {
            this.d.setAlpha(i2);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.d.setDither(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.d.setFilterBitmap(z);
        invalidateSelf();
    }
}
