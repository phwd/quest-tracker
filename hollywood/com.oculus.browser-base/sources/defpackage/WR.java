package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

/* renamed from: WR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class WR extends AbstractC5715y80 {
    public Drawable U;
    public final Rect V = new Rect();
    public final Rect W = new Rect();
    public int a0 = 119;
    public boolean b0 = true;
    public boolean c0 = false;

    public WR(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int[] iArr = FJ0.O;
        AbstractC1178Tg1.a(context, attributeSet, i, 0);
        AbstractC1178Tg1.b(context, attributeSet, iArr, i, 0, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        this.a0 = obtainStyledAttributes.getInt(1, this.a0);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.b0 = obtainStyledAttributes.getBoolean(2, true);
        obtainStyledAttributes.recycle();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.U;
        if (drawable != null) {
            if (this.c0) {
                this.c0 = false;
                Rect rect = this.V;
                Rect rect2 = this.W;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                if (this.b0) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                Gravity.apply(this.a0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.U;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.U;
        if (drawable != null && drawable.isStateful()) {
            this.U.setState(getDrawableState());
        }
    }

    public Drawable getForeground() {
        return this.U;
    }

    public int getForegroundGravity() {
        return this.a0;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.U;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // defpackage.AbstractC5715y80
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.c0 = z | this.c0;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.c0 = true;
    }

    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.U;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.U);
            }
            this.U = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.a0 == 119) {
                    drawable.getPadding(new Rect());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public void setForegroundGravity(int i) {
        if (this.a0 != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.a0 = i;
            if (i == 119 && this.U != null) {
                this.U.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.U;
    }
}
