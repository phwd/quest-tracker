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

/* renamed from: X.1qQ  reason: invalid class name and case insensitive filesystem */
public class C11011qQ extends ImageView {
    public final C10991qO A00;
    public final C11001qP A01;

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
    public ColorStateList getSupportImageTintList() {
        C11101qc r0;
        C11001qP r02 = this.A01;
        if (r02 == null || (r0 = r02.A00) == null) {
            return null;
        }
        return r0.A00;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportImageTintMode() {
        C11101qc r0;
        C11001qP r02 = this.A01;
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
        C11001qP r0 = this.A01;
        if (r0 != null) {
            r0.A01(i);
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
    public void setSupportImageTintList(@Nullable ColorStateList colorStateList) {
        C11001qP r2 = this.A01;
        if (r2 != null) {
            C11101qc r1 = r2.A00;
            if (r1 == null) {
                r1 = new C11101qc();
                r2.A00 = r1;
            }
            r1.A00 = colorStateList;
            r1.A02 = true;
            r2.A00();
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportImageTintMode(@Nullable PorterDuff.Mode mode) {
        C11001qP r2 = this.A01;
        if (r2 != null) {
            C11101qc r1 = r2.A00;
            if (r1 == null) {
                r1 = new C11101qc();
                r2.A00 = r1;
            }
            r1.A01 = mode;
            r1.A03 = true;
            r2.A00();
        }
    }

    public C11011qQ(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(AnonymousClass1RS.A00(context), attributeSet, i);
        C10891q9.A03(this, getContext());
        C10991qO r0 = new C10991qO(this);
        this.A00 = r0;
        r0.A07(attributeSet, i);
        C11001qP r02 = new C11001qP(this);
        this.A01 = r02;
        r02.A02(attributeSet, i);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A03();
        }
        C11001qP r02 = this.A01;
        if (r02 != null) {
            r02.A00();
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

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C11001qP r0 = this.A01;
        if (r0 != null) {
            r0.A00();
        }
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        C11001qP r0 = this.A01;
        if (r0 != null) {
            r0.A00();
        }
    }

    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        C11001qP r0 = this.A01;
        if (r0 != null) {
            r0.A00();
        }
    }
}
