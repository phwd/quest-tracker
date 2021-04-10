package org.chromium.components.browser_ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MaterialProgressBar extends View implements ValueAnimator.AnimatorUpdateListener {
    public final ValueAnimator F;
    public final Paint G = new Paint();
    public final Paint H;
    public final Paint I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10820J;

    public MaterialProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 3.0f);
        this.F = ofFloat;
        Paint paint = new Paint();
        this.H = paint;
        Paint paint2 = new Paint();
        this.I = paint2;
        Resources resources = context.getResources();
        int color = resources.getColor(R.color.f14740_resource_name_obfuscated_RES_2131100164);
        int color2 = resources.getColor(R.color.f14760_resource_name_obfuscated_RES_2131100166);
        int color3 = resources.getColor(R.color.f14770_resource_name_obfuscated_RES_2131100167);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.f0, 0, 0);
            color = obtainStyledAttributes.getColor(0, color);
            color2 = obtainStyledAttributes.getColor(1, color2);
            color3 = obtainStyledAttributes.getColor(2, color3);
            obtainStyledAttributes.recycle();
        }
        setBackgroundColor(color);
        paint.setColor(color2);
        postInvalidateOnAnimation();
        paint2.setColor(color3);
        postInvalidateOnAnimation();
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration(3000L);
        ofFloat.addUpdateListener(this);
    }

    public final void a(Canvas canvas, Paint paint, float f, float f2) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (getLayoutDirection() == 1) {
            float width = (float) canvas.getWidth();
            canvas.drawRect(width - f2, 0.0f, width - f, (float) canvas.getHeight(), paint);
            return;
        }
        canvas.drawRect(f, 0.0f, f2, (float) canvas.getHeight(), paint);
    }

    public final void b() {
        if (this.f10820J && !this.F.isRunning()) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (isAttachedToWindow() && getVisibility() == 0) {
                this.F.start();
            }
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        postInvalidateOnAnimation();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.F.isRunning()) {
            this.F.cancel();
        }
    }

    public void onDraw(Canvas canvas) {
        if (this.f10820J) {
            float width = (float) canvas.getWidth();
            a(canvas, this.G, 0.0f, width);
            float floatValue = ((Float) this.F.getAnimatedValue()).floatValue();
            a(canvas, this.H, ((float) (Math.pow((double) floatValue, 1.5d) - 0.5d)) * width, width * floatValue);
            if (floatValue >= 1.1f) {
                float f = (floatValue - 1.1f) / 1.0f;
                a(canvas, this.H, ((float) (Math.pow((double) f, 2.5d) - 0.10000000149011612d)) * width, width * f);
                return;
            }
            return;
        }
        a(canvas, this.G, 0.0f, (float) canvas.getWidth());
    }

    public void setBackgroundColor(int i) {
        this.G.setColor(i);
        postInvalidateOnAnimation();
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            b();
        } else if (this.F.isRunning()) {
            this.F.cancel();
        }
    }
}
