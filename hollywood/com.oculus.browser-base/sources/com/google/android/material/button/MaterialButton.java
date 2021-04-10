package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.customview.view.AbsSavedState;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MaterialButton extends L7 implements Checkable, AbstractC5258vT0 {
    public static final int[] H = {16842911};
    public static final int[] I = {16842912};

    /* renamed from: J  reason: collision with root package name */
    public final C0434Hc0 f9688J;
    public final LinkedHashSet K = new LinkedHashSet();
    public PorterDuff.Mode L;
    public ColorStateList M;
    public Drawable N;
    public int O;
    public int P;
    public int Q;
    public boolean R = false;
    public boolean S = false;
    public int T;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C0373Gc0();
        public boolean H;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            parcel.writeInt(this.H ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.H = parcel.readInt() != 1 ? false : true;
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, R.attr.f6110_resource_name_obfuscated_RES_2130969057, R.style.f75230_resource_name_obfuscated_RES_2132018096), attributeSet, R.attr.f6110_resource_name_obfuscated_RES_2130969057);
        Context context2 = getContext();
        TypedArray d = AbstractC1178Tg1.d(context2, attributeSet, FJ0.c0, R.attr.f6110_resource_name_obfuscated_RES_2130969057, R.style.f75230_resource_name_obfuscated_RES_2132018096, new int[0]);
        this.Q = d.getDimensionPixelSize(12, 0);
        this.L = AbstractC4486qv1.b(d.getInt(15, -1), PorterDuff.Mode.SRC_IN);
        this.M = AbstractC2722gd0.b(getContext(), d, 14);
        this.N = AbstractC2722gd0.c(getContext(), d, 10);
        this.T = d.getInteger(11, 1);
        this.O = d.getDimensionPixelSize(13, 0);
        C0434Hc0 hc0 = new C0434Hc0(this, C3553lT0.b(context2, attributeSet, R.attr.f6110_resource_name_obfuscated_RES_2130969057, R.style.f75230_resource_name_obfuscated_RES_2132018096).a());
        this.f9688J = hc0;
        hc0.c = d.getDimensionPixelOffset(1, 0);
        hc0.d = d.getDimensionPixelOffset(2, 0);
        hc0.e = d.getDimensionPixelOffset(3, 0);
        hc0.f = d.getDimensionPixelOffset(4, 0);
        if (d.hasValue(8)) {
            int dimensionPixelSize = d.getDimensionPixelSize(8, -1);
            hc0.g = dimensionPixelSize;
            hc0.d(hc0.b.e((float) dimensionPixelSize));
        }
        hc0.h = d.getDimensionPixelSize(20, 0);
        hc0.i = AbstractC4486qv1.b(d.getInt(7, -1), PorterDuff.Mode.SRC_IN);
        hc0.j = AbstractC2722gd0.b(getContext(), d, 6);
        hc0.k = AbstractC2722gd0.b(getContext(), d, 19);
        hc0.l = AbstractC2722gd0.b(getContext(), d, 16);
        hc0.o = d.getBoolean(5, false);
        int dimensionPixelSize2 = d.getDimensionPixelSize(9, 0);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        if (d.hasValue(0)) {
            hc0.n = true;
            g(hc0.j);
            h(hc0.i);
        } else {
            C3234jd0 jd0 = new C3234jd0(hc0.b);
            jd0.m(getContext());
            jd0.setTintList(hc0.j);
            PorterDuff.Mode mode = hc0.i;
            if (mode != null) {
                jd0.setTintMode(mode);
            }
            ColorStateList colorStateList = hc0.k;
            jd0.H.l = (float) hc0.h;
            jd0.invalidateSelf();
            jd0.p(colorStateList);
            C3234jd0 jd02 = new C3234jd0(hc0.b);
            jd02.setTint(0);
            jd02.H.l = (float) hc0.h;
            jd02.invalidateSelf();
            jd02.p(ColorStateList.valueOf(0));
            C3234jd0 jd03 = new C3234jd0(hc0.b);
            hc0.m = jd03;
            jd03.setTint(-1);
            RippleDrawable rippleDrawable = new RippleDrawable(AbstractC2004cN0.b(hc0.l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{jd02, jd0}), hc0.c, hc0.e, hc0.d, hc0.f), hc0.m);
            hc0.p = rippleDrawable;
            f(rippleDrawable);
            C3234jd0 b = hc0.b();
            if (b != null) {
                b.n((float) dimensionPixelSize2);
            }
        }
        setPaddingRelative(paddingStart + hc0.c, paddingTop + hc0.e, paddingEnd + hc0.d, paddingBottom + hc0.f);
        d.recycle();
        setCompoundDrawablePadding(this.Q);
        i(this.N != null);
    }

    @Override // defpackage.AbstractC5258vT0
    public void a(C3553lT0 lt0) {
        if (e()) {
            this.f9688J.d(lt0);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    public ColorStateList b() {
        if (e()) {
            return this.f9688J.j;
        }
        K7 k7 = this.F;
        if (k7 != null) {
            return k7.b();
        }
        return null;
    }

    public PorterDuff.Mode c() {
        if (e()) {
            return this.f9688J.i;
        }
        K7 k7 = this.F;
        if (k7 != null) {
            return k7.c();
        }
        return null;
    }

    public boolean d() {
        C0434Hc0 hc0 = this.f9688J;
        return hc0 != null && hc0.o;
    }

    public final boolean e() {
        C0434Hc0 hc0 = this.f9688J;
        return hc0 != null && !hc0.n;
    }

    public void f(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void g(ColorStateList colorStateList) {
        if (e()) {
            C0434Hc0 hc0 = this.f9688J;
            if (hc0.j != colorStateList) {
                hc0.j = colorStateList;
                if (hc0.b() != null) {
                    hc0.b().setTintList(hc0.j);
                    return;
                }
                return;
            }
            return;
        }
        K7 k7 = this.F;
        if (k7 != null) {
            k7.h(colorStateList);
        }
    }

    public ColorStateList getBackgroundTintList() {
        return b();
    }

    public PorterDuff.Mode getBackgroundTintMode() {
        return c();
    }

    public void h(PorterDuff.Mode mode) {
        if (e()) {
            C0434Hc0 hc0 = this.f9688J;
            if (hc0.i != mode) {
                hc0.i = mode;
                if (hc0.b() != null && hc0.i != null) {
                    hc0.b().setTintMode(hc0.i);
                    return;
                }
                return;
            }
            return;
        }
        K7 k7 = this.F;
        if (k7 != null) {
            k7.i(mode);
        }
    }

    public final void i(boolean z) {
        Drawable drawable = this.N;
        boolean z2 = false;
        if (drawable != null) {
            Drawable mutate = drawable.mutate();
            this.N = mutate;
            mutate.setTintList(this.M);
            PorterDuff.Mode mode = this.L;
            if (mode != null) {
                this.N.setTintMode(mode);
            }
            int i = this.O;
            if (i == 0) {
                i = this.N.getIntrinsicWidth();
            }
            int i2 = this.O;
            if (i2 == 0) {
                i2 = this.N.getIntrinsicHeight();
            }
            Drawable drawable2 = this.N;
            int i3 = this.P;
            drawable2.setBounds(i3, 0, i + i3, i2);
        }
        int i4 = this.T;
        boolean z3 = i4 == 1 || i4 == 2;
        if (!z) {
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            Drawable drawable3 = compoundDrawablesRelative[0];
            Drawable drawable4 = compoundDrawablesRelative[2];
            if ((z3 && drawable3 != this.N) || (!z3 && drawable4 != this.N)) {
                z2 = true;
            }
            if (!z2) {
                return;
            }
            if (z3) {
                setCompoundDrawablesRelative(this.N, null, null, null);
            } else {
                setCompoundDrawablesRelative(null, null, this.N, null);
            }
        } else if (z3) {
            setCompoundDrawablesRelative(this.N, null, null, null);
        } else {
            setCompoundDrawablesRelative(null, null, this.N, null);
        }
    }

    public boolean isChecked() {
        return this.R;
    }

    public final void j() {
        if (this.N != null && getLayout() != null) {
            int i = this.T;
            boolean z = true;
            if (i == 1 || i == 3) {
                this.P = 0;
                i(false);
                return;
            }
            TextPaint paint = getPaint();
            String charSequence = getText().toString();
            if (getTransformationMethod() != null) {
                charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
            }
            int min = Math.min((int) paint.measureText(charSequence), getLayout().getEllipsizedWidth());
            int i2 = this.O;
            if (i2 == 0) {
                i2 = this.N.getIntrinsicWidth();
            }
            int measuredWidth = getMeasuredWidth() - min;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            int paddingEnd = ((((measuredWidth - getPaddingEnd()) - i2) - this.Q) - getPaddingStart()) / 2;
            boolean z2 = getLayoutDirection() == 1;
            if (this.T != 4) {
                z = false;
            }
            if (z2 != z) {
                paddingEnd = -paddingEnd;
            }
            if (this.P != paddingEnd) {
                this.P = paddingEnd;
                i(false);
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (e()) {
            AbstractC3405kd0.c(this, this.f9688J.b());
        }
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (d()) {
            Button.mergeDrawableStates(onCreateDrawableState, H);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(onCreateDrawableState, I);
        }
        return onCreateDrawableState;
    }

    @Override // defpackage.L7
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName((d() ? CompoundButton.class : Button.class).getName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // defpackage.L7
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((d() ? CompoundButton.class : Button.class).getName());
        accessibilityNodeInfo.setCheckable(d());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // defpackage.L7
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        j();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        setChecked(savedState.H);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.H = this.R;
        return savedState;
    }

    @Override // defpackage.L7
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        j();
    }

    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundColor(int i) {
        if (e()) {
            C0434Hc0 hc0 = this.f9688J;
            if (hc0.b() != null) {
                hc0.b().setTint(i);
                return;
            }
            return;
        }
        super.setBackgroundColor(i);
    }

    @Override // defpackage.L7
    public void setBackgroundDrawable(Drawable drawable) {
        if (!e()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.w("MaterialButton", "Do not set the background; MaterialButton manages its own background drawable.");
            C0434Hc0 hc0 = this.f9688J;
            hc0.n = true;
            hc0.f8164a.g(hc0.j);
            hc0.f8164a.h(hc0.i);
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    @Override // defpackage.L7
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? AbstractC5544x8.a(getContext(), i) : null);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        g(colorStateList);
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        h(mode);
    }

    public void setChecked(boolean z) {
        if (d() && isEnabled() && this.R != z) {
            this.R = z;
            refreshDrawableState();
            if (!this.S) {
                this.S = true;
                Iterator it = this.K.iterator();
                while (it.hasNext()) {
                    ((AbstractC0312Fc0) it.next()).a(this, this.R);
                }
                this.S = false;
            }
        }
    }

    public void setElevation(float f) {
        super.setElevation(f);
        if (e()) {
            C3234jd0 b = this.f9688J.b();
            C3064id0 id0 = b.H;
            if (id0.o != f) {
                id0.o = f;
                b.s();
            }
        }
    }

    public void setPressed(boolean z) {
        super.setPressed(z);
    }

    public void toggle() {
        setChecked(!this.R);
    }
}
