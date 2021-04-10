package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.components.browser_ui.widget.LoadingView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LogoView extends FrameLayout implements View.OnClickListener {
    public Bitmap F;
    public Bitmap G;
    public Paint H;
    public Matrix I = new Matrix();

    /* renamed from: J  reason: collision with root package name */
    public Matrix f10711J;
    public boolean K = true;
    public boolean L;
    public LoadingView M;
    public float N;
    public final Property O = new C2545fb0(this, Float.class, "");

    public LogoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.H = paint;
        paint.setFilterBitmap(true);
        setOnClickListener(this);
        setClickable(false);
        setWillNotDraw(false);
        this.M = new LoadingView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.M.setLayoutParams(layoutParams);
        this.M.setVisibility(8);
        addView(this.M);
    }

    public final void a(int i, int i2, Matrix matrix, boolean z) {
        float width = (float) getWidth();
        float f = (float) i;
        float height = (float) getHeight();
        float f2 = (float) i2;
        float min = Math.min(width / f, height / f2);
        if (z) {
            min = Math.min(1.0f, min);
        }
        int round = Math.round((width - (f * min)) * 0.5f);
        int round2 = Math.round((height - (f2 * min)) * 0.5f);
        matrix.setScale(min, min);
        matrix.postTranslate((float) round, (float) round2);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (drawable == null) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void onClick(View view) {
    }

    public void onDraw(Canvas canvas) {
        if (this.F != null) {
            float f = this.N;
            if (f < 0.5f) {
                this.H.setAlpha((int) ((0.5f - f) * 510.0f));
                canvas.save();
                canvas.concat(this.I);
                canvas.drawBitmap(this.F, 0.0f, 0.0f, this.H);
                canvas.restore();
            }
        }
        if (this.G != null) {
            float f2 = this.N;
            if (f2 > 0.5f) {
                this.H.setAlpha((int) ((f2 - 0.5f) * 510.0f));
                canvas.save();
                canvas.concat(this.f10711J);
                canvas.drawBitmap(this.G, 0.0f, 0.0f, this.H);
                canvas.restore();
            }
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            Bitmap bitmap = this.F;
            if (bitmap != null) {
                a(bitmap.getWidth(), this.F.getHeight(), this.I, this.K);
            }
            Bitmap bitmap2 = this.G;
            if (bitmap2 != null) {
                a(bitmap2.getWidth(), this.G.getHeight(), this.f10711J, this.L);
            }
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        return drawable == null || super.verifyDrawable(drawable);
    }
}
