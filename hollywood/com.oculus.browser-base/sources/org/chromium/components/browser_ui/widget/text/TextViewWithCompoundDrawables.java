package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextViewWithCompoundDrawables extends N8 {
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f10829J;
    public ColorStateList K;

    public TextViewWithCompoundDrawables(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.L0, 0, 0);
        this.I = obtainStyledAttributes.getDimensionPixelSize(2, -1);
        this.f10829J = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.K = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        if (this.I > 0 || this.f10829J > 0 || this.K != null) {
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            f(compoundDrawablesRelative);
            if (this.K != null) {
                g(compoundDrawablesRelative);
            }
            setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
    }

    @Override // defpackage.N8
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.K != null) {
            g(getCompoundDrawablesRelative());
        }
    }

    public final void f(Drawable[] drawableArr) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null && (this.I > 0 || this.f10829J > 0)) {
                Rect copyBounds = drawable.copyBounds();
                int i = this.I;
                if (i > 0) {
                    copyBounds.right = copyBounds.left + i;
                }
                int i2 = this.f10829J;
                if (i2 > 0) {
                    copyBounds.bottom = copyBounds.top + i2;
                }
                drawable.setBounds(copyBounds);
            }
        }
    }

    public final void g(Drawable[] drawableArr) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(this.K.getColorForState(getDrawableState(), 0), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public void h(ColorStateList colorStateList) {
        this.K = colorStateList;
        g(getCompoundDrawablesRelative());
    }

    @Override // defpackage.N8
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawable[] drawableArr = {drawable, drawable2, drawable3, drawable4};
        f(drawableArr);
        if (this.K != null) {
            g(drawableArr);
        }
        super.setCompoundDrawablesRelative(drawableArr[0], drawableArr[1], drawableArr[2], drawableArr[3]);
    }
}
