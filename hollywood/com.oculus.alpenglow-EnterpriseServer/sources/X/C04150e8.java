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
import com.oculus.alpenglow.R;

/* renamed from: X.0e8  reason: invalid class name and case insensitive filesystem */
public final class C04150e8 extends RadioButton {
    public final AnonymousClass04A A00;
    public final AnonymousClass04C A01;
    public final AnonymousClass04U A02;

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
    public ColorStateList getSupportButtonTintList() {
        AnonymousClass04C r0 = this.A01;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportButtonTintMode() {
        AnonymousClass04C r0 = this.A01;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
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
    public void setSupportButtonTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04C r1 = this.A01;
        if (r1 != null) {
            r1.A00 = colorStateList;
            r1.A02 = true;
            AnonymousClass04C.A00(r1);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportButtonTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04C r1 = this.A01;
        if (r1 != null) {
            r1.A01 = mode;
            r1.A03 = true;
            AnonymousClass04C.A00(r1);
        }
    }

    public C04150e8(Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass17C.A00(context), attributeSet, R.attr.radioButtonStyle);
        AnonymousClass05V.A03(this, getContext());
        AnonymousClass04C r0 = new AnonymousClass04C(this);
        this.A01 = r0;
        r0.A01(attributeSet, R.attr.radioButtonStyle);
        AnonymousClass04A r02 = new AnonymousClass04A(this);
        this.A00 = r02;
        r02.A08(attributeSet, R.attr.radioButtonStyle);
        AnonymousClass04U r03 = new AnonymousClass04U(this);
        this.A02 = r03;
        r03.A0C(attributeSet, R.attr.radioButtonStyle);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A04();
        }
        AnonymousClass04U r02 = this.A02;
        if (r02 != null) {
            r02.A06();
        }
    }

    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft();
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

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(@DrawableRes int i) {
        setButtonDrawable(AnonymousClass17E.A00(getContext(), i));
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        AnonymousClass04C r1 = this.A01;
        if (r1 == null) {
            return;
        }
        if (r1.A04) {
            r1.A04 = false;
            return;
        }
        r1.A04 = true;
        AnonymousClass04C.A00(r1);
    }
}
