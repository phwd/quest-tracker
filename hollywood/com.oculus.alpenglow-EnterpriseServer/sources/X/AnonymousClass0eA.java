package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* renamed from: X.0eA  reason: invalid class name */
public class AnonymousClass0eA extends ImageView {
    public final AnonymousClass04A A00;
    public final AnonymousClass04G A01;

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
    public ColorStateList getSupportImageTintList() {
        AnonymousClass05X r0;
        AnonymousClass04G r02 = this.A01;
        if (r02 == null || (r0 = r02.A00) == null) {
            return null;
        }
        return r0.A00;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportImageTintMode() {
        AnonymousClass05X r0;
        AnonymousClass04G r02 = this.A01;
        if (r02 == null || (r0 = r02.A00) == null) {
            return null;
        }
        return r0.A01;
    }

    public final boolean hasOverlappingRendering() {
        if ((this.A01.A01.getBackground() instanceof RippleDrawable) || !super.hasOverlappingRendering()) {
            return false;
        }
        return true;
    }

    public void setImageResource(@DrawableRes int i) {
        AnonymousClass04G r0 = this.A01;
        if (r0 != null) {
            r0.A01(i);
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
    public void setSupportImageTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04G r2 = this.A01;
        if (r2 != null) {
            AnonymousClass05X r1 = r2.A00;
            if (r1 == null) {
                r1 = new AnonymousClass05X();
                r2.A00 = r1;
            }
            r1.A00 = colorStateList;
            r1.A02 = true;
            r2.A00();
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportImageTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04G r2 = this.A01;
        if (r2 != null) {
            AnonymousClass05X r1 = r2.A00;
            if (r1 == null) {
                r1 = new AnonymousClass05X();
                r2.A00 = r1;
            }
            r1.A01 = mode;
            r1.A03 = true;
            r2.A00();
        }
    }

    public AnonymousClass0eA(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(AnonymousClass17C.A00(context), attributeSet, i);
        AnonymousClass05V.A03(this, getContext());
        AnonymousClass04A r0 = new AnonymousClass04A(this);
        this.A00 = r0;
        r0.A08(attributeSet, i);
        AnonymousClass04G r02 = new AnonymousClass04G(this);
        this.A01 = r02;
        r02.A02(attributeSet, i);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A04();
        }
        AnonymousClass04G r02 = this.A01;
        if (r02 != null) {
            r02.A00();
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

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        AnonymousClass04G r0 = this.A01;
        if (r0 != null) {
            r0.A00();
        }
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        AnonymousClass04G r0 = this.A01;
        if (r0 != null) {
            r0.A00();
        }
    }

    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        AnonymousClass04G r0 = this.A01;
        if (r0 != null) {
            r0.A00();
        }
    }
}
