package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

/* renamed from: X.1r6  reason: invalid class name and case insensitive filesystem */
public final class C11191r6 extends RadioButton {
    public final C10991qO A00;
    public final C11171r4 A01;
    public final AnonymousClass1qE A02;

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
    public ColorStateList getSupportButtonTintList() {
        C11171r4 r0 = this.A01;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportButtonTintMode() {
        C11171r4 r0 = this.A01;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
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
    public void setSupportButtonTintList(@Nullable ColorStateList colorStateList) {
        C11171r4 r1 = this.A01;
        if (r1 != null) {
            r1.A00 = colorStateList;
            r1.A02 = true;
            C11171r4.A00(r1);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportButtonTintMode(@Nullable PorterDuff.Mode mode) {
        C11171r4 r1 = this.A01;
        if (r1 != null) {
            r1.A01 = mode;
            r1.A03 = true;
            C11171r4.A00(r1);
        }
    }

    public C11191r6(Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass1RS.A00(context), attributeSet, R.attr.radioButtonStyle);
        C10891q9.A03(this, getContext());
        C11171r4 r0 = new C11171r4(this);
        this.A01 = r0;
        r0.A01(attributeSet, R.attr.radioButtonStyle);
        C10991qO r02 = new C10991qO(this);
        this.A00 = r02;
        r02.A07(attributeSet, R.attr.radioButtonStyle);
        AnonymousClass1qE r03 = new AnonymousClass1qE(this);
        this.A02 = r03;
        r03.A0A(attributeSet, R.attr.radioButtonStyle);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A03();
        }
        AnonymousClass1qE r02 = this.A02;
        if (r02 != null) {
            r02.A04();
        }
    }

    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft();
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

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(@DrawableRes int i) {
        setButtonDrawable(AnonymousClass1pW.A00(getContext(), i));
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C11171r4 r1 = this.A01;
        if (r1 == null) {
            return;
        }
        if (r1.A04) {
            r1.A04 = false;
            return;
        }
        r1.A04 = true;
        C11171r4.A00(r1);
    }
}
