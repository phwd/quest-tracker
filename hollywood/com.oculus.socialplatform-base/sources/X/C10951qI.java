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
import com.oculus.socialplatform.R;

/* renamed from: X.1qI  reason: invalid class name and case insensitive filesystem */
public final class C10951qI extends Button implements AnonymousClass08K {
    public final C10991qO A00;
    public final AnonymousClass1qE A01;

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMaxTextSize() {
        if (AnonymousClass08K.A00) {
            return super.getAutoSizeMaxTextSize();
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            return Math.round(r0.A0C.A00);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMinTextSize() {
        if (AnonymousClass08K.A00) {
            return super.getAutoSizeMinTextSize();
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            return Math.round(r0.A0C.A01);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeStepGranularity() {
        if (AnonymousClass08K.A00) {
            return super.getAutoSizeStepGranularity();
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            return Math.round(r0.A0C.A02);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int[] getAutoSizeTextAvailableSizes() {
        if (AnonymousClass08K.A00) {
            return super.getAutoSizeTextAvailableSizes();
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            return r0.A0C.A07;
        }
        return new int[0];
    }

    @SuppressLint({"WrongConstant"})
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeTextType() {
        if (!AnonymousClass08K.A00) {
            AnonymousClass1qE r0 = this.A01;
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
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            return r0.A01();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportCompoundDrawablesTintList() {
        C11101qc r0 = this.A01.A07;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        C11101qc r0 = this.A01.A07;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (AnonymousClass08K.A00) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            r0.A06(i, i2, i3, i4);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        if (AnonymousClass08K.A00) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            r0.A0B(iArr, i);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (AnonymousClass08K.A00) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            r0.A05(i);
        }
    }

    public void setSupportAllCaps(boolean z) {
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            r0.A0B.setAllCaps(z);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A05(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A06(mode);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportCompoundDrawablesTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass1qE r0 = this.A01;
        r0.A08(colorStateList);
        r0.A04();
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportCompoundDrawablesTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass1qE r0 = this.A01;
        r0.A09(mode);
        r0.A04();
    }

    public final void setTextSize(int i, float f) {
        boolean z = AnonymousClass08K.A00;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        AnonymousClass1qE r1 = this.A01;
        if (r1 != null && !z && !r1.A0C()) {
            r1.A0C.A07(i, f);
        }
    }

    public C10951qI(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass1RS.A00(context), attributeSet, R.attr.buttonStyle);
        C10891q9.A03(this, getContext());
        C10991qO r0 = new C10991qO(this);
        this.A00 = r0;
        r0.A07(attributeSet, R.attr.buttonStyle);
        AnonymousClass1qE r02 = new AnonymousClass1qE(this);
        this.A01 = r02;
        r02.A0A(attributeSet, R.attr.buttonStyle);
        this.A01.A04();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A03();
        }
        AnonymousClass1qE r02 = this.A01;
        if (r02 != null) {
            r02.A04();
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
        AnonymousClass1qE r1 = this.A01;
        if (r1 != null && !AnonymousClass08K.A00) {
            r1.A0C.A06();
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        AnonymousClass1qE r1 = this.A01;
        if (r1 != null && !AnonymousClass08K.A00 && r1.A0C()) {
            r1.A0C.A06();
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C10991qO r1 = this.A00;
        if (r1 != null) {
            C10991qO.A00(r1, null);
            r1.A03();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A04(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass08Y.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass1qE r0 = this.A01;
        if (r0 != null) {
            r0.A07(context, i);
        }
    }
}
