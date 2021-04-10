package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Snackbar$SnackbarLayout extends FrameLayout {
    public static final View.OnTouchListener F = new View$OnTouchListenerC0932Pg();
    public final float G;
    public ColorStateList H;
    public PorterDuff.Mode I;

    public Snackbar$SnackbarLayout(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, FJ0.B0);
        if (obtainStyledAttributes.hasValue(6)) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            setElevation((float) obtainStyledAttributes.getDimensionPixelSize(6, 0));
        }
        obtainStyledAttributes.getInt(2, 0);
        this.G = obtainStyledAttributes.getFloat(3, 1.0f);
        setBackgroundTintList(AbstractC2722gd0.b(context2, obtainStyledAttributes, 4));
        setBackgroundTintMode(AbstractC4486qv1.b(obtainStyledAttributes.getInt(5, -1), PorterDuff.Mode.SRC_IN));
        obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        setOnTouchListener(F);
        setFocusable(true);
        if (getBackground() == null) {
            Drawable a2 = a();
            AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
            setBackground(a2);
        }
    }

    public final Drawable a() {
        float dimension = getResources().getDimension(R.dimen.f22520_resource_name_obfuscated_RES_2131165871);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(AbstractC1226Uc0.c(AbstractC1226Uc0.b(this, R.attr.f3250_resource_name_obfuscated_RES_2130968771), AbstractC1226Uc0.b(this, R.attr.f3150_resource_name_obfuscated_RES_2130968761), this.G));
        ColorStateList colorStateList = this.H;
        if (colorStateList != null) {
            gradientDrawable.setTintList(colorStateList);
        }
        return gradientDrawable;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        requestApplyInsets();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getLayoutParams().width == -1) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
            }
        }
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (!(drawable == null || this.H == null)) {
            drawable = drawable.mutate();
            drawable.setTintList(this.H);
            drawable.setTintMode(this.I);
        }
        super.setBackgroundDrawable(drawable);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.H = colorStateList;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            mutate.setTintList(colorStateList);
            mutate.setTintMode(this.I);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.I = mode;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            mutate.setTintMode(mode);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : F);
        super.setOnClickListener(onClickListener);
    }
}
