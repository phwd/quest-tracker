package androidx.appcompat.widget;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.Property;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SwitchCompat extends CompoundButton {
    public static final Property F = new L41(Float.class, "thumbPos");
    public static final int[] G = {16842912};
    public Drawable H;
    public ColorStateList I = null;

    /* renamed from: J  reason: collision with root package name */
    public PorterDuff.Mode f9463J = null;
    public boolean K = false;
    public boolean L = false;
    public Drawable M;
    public ColorStateList N = null;
    public PorterDuff.Mode O = null;
    public boolean P = false;
    public boolean Q = false;
    public int R;
    public int S;
    public int T;
    public boolean U;
    public CharSequence V;
    public CharSequence W;
    public boolean a0;
    public int b0;
    public int c0;
    public float d0;
    public float e0;
    public VelocityTracker f0 = VelocityTracker.obtain();
    public int g0;
    public float h0;
    public int i0;
    public int j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public int o0;
    public final TextPaint p0;
    public ColorStateList q0;
    public Layout r0;
    public Layout s0;
    public TransformationMethod t0;
    public ObjectAnimator u0;
    public final M8 v0;
    public final Rect w0 = new Rect();

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0183, code lost:
        if (r4 != null) goto L_0x018a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwitchCompat(android.content.Context r19, android.util.AttributeSet r20) {
        /*
        // Method dump skipped, instructions count: 569
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final int a() {
        float f;
        if (AbstractC4826sv1.a(this)) {
            f = 1.0f - this.h0;
        } else {
            f = this.h0;
        }
        return (int) ((f * ((float) b())) + 0.5f);
    }

    public final int b() {
        Rect rect;
        Drawable drawable = this.M;
        if (drawable == null) {
            return 0;
        }
        Rect rect2 = this.w0;
        drawable.getPadding(rect2);
        Drawable drawable2 = this.H;
        if (drawable2 != null) {
            rect = XI.b(drawable2);
        } else {
            rect = XI.c;
        }
        return ((((this.i0 - this.k0) - rect2.left) - rect2.right) - rect.left) - rect.right;
    }

    public final Layout c(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.t0;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        TextPaint textPaint = this.p0;
        return new StaticLayout(charSequence, textPaint, charSequence != null ? (int) Math.ceil((double) Layout.getDesiredWidth(charSequence, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public void d(Typeface typeface) {
        if ((this.p0.getTypeface() != null && !this.p0.getTypeface().equals(typeface)) || (this.p0.getTypeface() == null && typeface != null)) {
            this.p0.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void draw(Canvas canvas) {
        Rect rect;
        int i;
        int i2;
        Rect rect2 = this.w0;
        int i3 = this.l0;
        int i4 = this.m0;
        int i5 = this.n0;
        int i6 = this.o0;
        int a2 = a() + i3;
        Drawable drawable = this.H;
        if (drawable != null) {
            rect = XI.b(drawable);
        } else {
            rect = XI.c;
        }
        Drawable drawable2 = this.M;
        if (drawable2 != null) {
            drawable2.getPadding(rect2);
            int i7 = rect2.left;
            a2 += i7;
            if (rect != null) {
                int i8 = rect.left;
                if (i8 > i7) {
                    i3 += i8 - i7;
                }
                int i9 = rect.top;
                int i10 = rect2.top;
                i = i9 > i10 ? (i9 - i10) + i4 : i4;
                int i11 = rect.right;
                int i12 = rect2.right;
                if (i11 > i12) {
                    i5 -= i11 - i12;
                }
                int i13 = rect.bottom;
                int i14 = rect2.bottom;
                if (i13 > i14) {
                    i2 = i6 - (i13 - i14);
                    this.M.setBounds(i3, i, i5, i2);
                }
            } else {
                i = i4;
            }
            i2 = i6;
            this.M.setBounds(i3, i, i5, i2);
        }
        Drawable drawable3 = this.H;
        if (drawable3 != null) {
            drawable3.getPadding(rect2);
            int i15 = a2 - rect2.left;
            int i16 = a2 + this.k0 + rect2.right;
            this.H.setBounds(i15, i4, i16, i6);
            Drawable background = getBackground();
            if (background != null) {
                background.setHotspotBounds(i15, i4, i16, i6);
            }
        }
        super.draw(canvas);
    }

    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.H;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
        Drawable drawable2 = this.M;
        if (drawable2 != null) {
            drawable2.setHotspot(f, f2);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.H;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.M;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    public int getCompoundPaddingLeft() {
        if (!AbstractC4826sv1.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.i0;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.T : compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (AbstractC4826sv1.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.i0;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.T : compoundPaddingRight;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.H;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.M;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.u0;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.u0.end();
            this.u0 = null;
        }
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(onCreateDrawableState, G);
        }
        return onCreateDrawableState;
    }

    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        Rect rect = this.w0;
        Drawable drawable = this.M;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.m0;
        int i3 = this.o0;
        int i4 = i2 + rect.top;
        int i5 = i3 - rect.bottom;
        Drawable drawable2 = this.H;
        if (drawable != null) {
            if (!this.U || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect b = XI.b(drawable2);
                drawable2.copyBounds(rect);
                rect.left += b.left;
                rect.right -= b.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = (this.h0 > 0.5f ? 1 : (this.h0 == 0.5f ? 0 : -1)) > 0 ? this.r0 : this.s0;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.q0;
            if (colorStateList != null) {
                this.p0.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.p0.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                i = bounds.left + bounds.right;
            } else {
                i = getWidth();
            }
            canvas.translate((float) ((i / 2) - (layout.getWidth() / 2)), (float) (((i4 + i5) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.V : this.W;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        super.onLayout(z, i, i2, i3, i4);
        int i10 = 0;
        if (this.H != null) {
            Rect rect = this.w0;
            Drawable drawable = this.M;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect b = XI.b(this.H);
            i5 = Math.max(0, b.left - rect.left);
            i10 = Math.max(0, b.right - rect.right);
        } else {
            i5 = 0;
        }
        if (AbstractC4826sv1.a(this)) {
            i7 = getPaddingLeft() + i5;
            i6 = ((this.i0 + i7) - i5) - i10;
        } else {
            i6 = (getWidth() - getPaddingRight()) - i10;
            i7 = (i6 - this.i0) + i5 + i10;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop = getPaddingTop();
            int i11 = this.j0;
            int height = (((getHeight() + paddingTop) - getPaddingBottom()) / 2) - (i11 / 2);
            i8 = i11 + height;
            i9 = height;
        } else if (gravity != 80) {
            i9 = getPaddingTop();
            i8 = this.j0 + i9;
        } else {
            i8 = getHeight() - getPaddingBottom();
            i9 = i8 - this.j0;
        }
        this.l0 = i7;
        this.m0 = i9;
        this.o0 = i8;
        this.n0 = i6;
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        if (this.a0) {
            if (this.r0 == null) {
                this.r0 = c(this.V);
            }
            if (this.s0 == null) {
                this.s0 = c(this.W);
            }
        }
        Rect rect = this.w0;
        Drawable drawable = this.H;
        int i6 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i4 = (this.H.getIntrinsicWidth() - rect.left) - rect.right;
            i3 = this.H.getIntrinsicHeight();
        } else {
            i4 = 0;
            i3 = 0;
        }
        if (this.a0) {
            i5 = (this.R * 2) + Math.max(this.r0.getWidth(), this.s0.getWidth());
        } else {
            i5 = 0;
        }
        this.k0 = Math.max(i5, i4);
        Drawable drawable2 = this.M;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i6 = this.M.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i7 = rect.left;
        int i8 = rect.right;
        Drawable drawable3 = this.H;
        if (drawable3 != null) {
            Rect b = XI.b(drawable3);
            i7 = Math.max(i7, b.left);
            i8 = Math.max(i8, b.right);
        }
        int max = Math.max(this.S, (this.k0 * 2) + i7 + i8);
        int max2 = Math.max(i6, i3);
        this.i0 = max;
        this.j0 = max2;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.V : this.W;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        if (r0 != 3) goto L_0x0156;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
        // Method dump skipped, instructions count: 347
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean isChecked = isChecked();
        float f = 1.0f;
        if (getWindowToken() != null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (isLaidOut()) {
                if (!isChecked) {
                    f = 0.0f;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, F, f);
                this.u0 = ofFloat;
                ofFloat.setDuration(250L);
                this.u0.setAutoCancel(true);
                this.u0.start();
                return;
            }
        }
        ObjectAnimator objectAnimator = this.u0;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        if (!isChecked) {
            f = 0.0f;
        }
        this.h0 = f;
        invalidate();
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AbstractC0751Mg1.d(this, callback));
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.H || drawable == this.M;
    }
}
