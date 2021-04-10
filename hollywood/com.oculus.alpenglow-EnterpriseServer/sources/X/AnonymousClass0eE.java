package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;

/* renamed from: X.0eE  reason: invalid class name */
public final class AnonymousClass0eE extends Button implements AnonymousClass0Bc {
    public final AnonymousClass04A A00;
    public final AnonymousClass04U A01;

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMaxTextSize() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeMaxTextSize();
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            return Math.round(r0.A0C.A00);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMinTextSize() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeMinTextSize();
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            return Math.round(r0.A0C.A01);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeStepGranularity() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeStepGranularity();
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            return Math.round(r0.A0C.A02);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int[] getAutoSizeTextAvailableSizes() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeTextAvailableSizes();
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            return r0.A0C.A07;
        }
        return new int[0];
    }

    @SuppressLint({"WrongConstant"})
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeTextType() {
        if (!AnonymousClass0Bc.A00) {
            AnonymousClass04U r0 = this.A01;
            if (r0 != null) {
                return r0.A0C.A03;
            }
            return 0;
        } else if (super.getAutoSizeTextType() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            return r0.A03();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportCompoundDrawablesTintList() {
        AnonymousClass05X r0 = this.A01.A07;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        AnonymousClass05X r0 = this.A01.A07;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (AnonymousClass0Bc.A00) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            r0.A08(i, i2, i3, i4);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        if (AnonymousClass0Bc.A00) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            r0.A0D(iArr, i);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (AnonymousClass0Bc.A00) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            r0.A07(i);
        }
    }

    public void setSupportAllCaps(boolean z) {
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            r0.A0B.setAllCaps(z);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A06(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A07(mode);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportCompoundDrawablesTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04U r0 = this.A01;
        r0.A0A(colorStateList);
        r0.A06();
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportCompoundDrawablesTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04U r0 = this.A01;
        r0.A0B(mode);
        r0.A06();
    }

    public final void setTextSize(int i, float f) {
        boolean z = AnonymousClass0Bc.A00;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        AnonymousClass04U r1 = this.A01;
        if (r1 != null && !z && !r1.A0E()) {
            r1.A0C.A07(i, f);
        }
    }

    public AnonymousClass0eE(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass17C.A00(context), attributeSet, R.attr.buttonStyle);
        AnonymousClass05V.A03(this, getContext());
        AnonymousClass04A r0 = new AnonymousClass04A(this);
        this.A00 = r0;
        r0.A08(attributeSet, R.attr.buttonStyle);
        AnonymousClass04U r02 = new AnonymousClass04U(this);
        this.A01 = r02;
        r02.A0C(attributeSet, R.attr.buttonStyle);
        this.A01.A06();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A04();
        }
        AnonymousClass04U r02 = this.A01;
        if (r02 != null) {
            r02.A06();
        }
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        AnonymousClass04U r1 = this.A01;
        if (r1 != null && !AnonymousClass0Bc.A00) {
            r1.A0C.A06();
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        AnonymousClass04U r1 = this.A01;
        if (r1 != null && !AnonymousClass0Bc.A00 && r1.A0E()) {
            r1.A0C.A06();
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AnonymousClass04A r1 = this.A00;
        if (r1 != null) {
            AnonymousClass04A.A00(r1, null);
            r1.A04();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A05(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass0Bq.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass04U r0 = this.A01;
        if (r0 != null) {
            r0.A09(context, i);
        }
    }
}
