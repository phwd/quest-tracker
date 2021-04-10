package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RoundedCornerImageView extends AppCompatImageView {
    public final RectF H;
    public final Matrix I;

    /* renamed from: J  reason: collision with root package name */
    public final Paint f10824J;
    public Paint K;
    public boolean L;
    public final boolean M;
    public Shape N;
    public int O;

    public RoundedCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void c() {
        Drawable drawable = getDrawable();
        if (this.M) {
            if (drawable instanceof ColorDrawable) {
                this.f10824J.setColor(((ColorDrawable) getDrawable()).getColor());
                this.K = null;
                return;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    this.f10824J.setColor(this.O);
                    this.K = new Paint(1);
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Paint paint = this.K;
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
                    return;
                }
            }
            this.f10824J.setColor(this.O);
            this.K = null;
        }
    }

    public void d(int i, int i2, int i3, int i4) {
        float[] fArr;
        boolean z = (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? false : true;
        this.L = z;
        if (z) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (getLayoutDirection() == 0) {
                float f = (float) i;
                float f2 = (float) i2;
                float f3 = (float) i4;
                float f4 = (float) i3;
                fArr = new float[]{f, f, f2, f2, f3, f3, f4, f4};
            } else {
                float f5 = (float) i2;
                float f6 = (float) i;
                float f7 = (float) i3;
                float f8 = (float) i4;
                fArr = new float[]{f5, f5, f6, f6, f7, f7, f8, f8};
            }
            this.N = new RoundRectShape(fArr, null, null);
        }
    }

    public void onDraw(Canvas canvas) {
        if (!this.L) {
            super.onDraw(canvas);
            return;
        }
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        if (width > 0 && height > 0) {
            this.N.resize((float) width, (float) height);
            int save = canvas.save();
            try {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
                if (this.f10824J.getColor() != 0) {
                    this.N.draw(canvas, this.f10824J);
                    if (getDrawable() instanceof ColorDrawable) {
                        return;
                    }
                }
                Paint paint = this.K;
                if (paint == null) {
                    canvas.restoreToCount(save);
                    super.onDraw(canvas);
                    canvas.restoreToCount(save);
                    return;
                }
                Shader shader = paint.getShader();
                if (shader != null) {
                    Drawable drawable = getDrawable();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    this.I.set(getImageMatrix());
                    this.I.preScale(((float) drawable.getIntrinsicWidth()) / ((float) bitmap.getWidth()), ((float) drawable.getIntrinsicHeight()) / ((float) bitmap.getHeight()));
                    shader.setLocalMatrix(this.I);
                    this.H.set(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
                    this.I.mapRect(this.H);
                    canvas.clipRect(this.H);
                }
                this.N.draw(canvas, this.K);
                canvas.restoreToCount(save);
            } finally {
                canvas.restoreToCount(save);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        c();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        c();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView
    public void setImageResource(int i) {
        C4523r8 r8Var = this.G;
        if (r8Var != null) {
            r8Var.c(i);
        }
        c();
    }

    public RoundedCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        int i3;
        int i4;
        int i5;
        this.H = new RectF();
        this.I = new Matrix();
        Paint paint = new Paint(1);
        this.f10824J = paint;
        new Matrix();
        int i6 = 0;
        this.O = 0;
        this.M = true;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, FJ0.w0, 0, 0);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            i5 = obtainStyledAttributes.getDimensionPixelSize(2, 0);
            i2 = obtainStyledAttributes.getDimensionPixelSize(1, 0);
            i4 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            int color = obtainStyledAttributes.getColor(4, 0);
            obtainStyledAttributes.recycle();
            i3 = color;
            i6 = dimensionPixelSize;
        } else {
            i3 = 0;
            i2 = 0;
            i5 = 0;
            i4 = 0;
        }
        d(i6, i5, i2, i4);
        this.O = i3;
        paint.setColor(i3);
        invalidate();
        c();
    }
}
