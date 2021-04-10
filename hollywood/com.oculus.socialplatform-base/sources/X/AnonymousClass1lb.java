package X;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* renamed from: X.1lb  reason: invalid class name */
public class AnonymousClass1lb extends Drawable implements AnonymousClass0Mg, AbstractC00940Mi, Drawable.Callback, AnonymousClass1mn {
    public static final Matrix A03 = new Matrix();
    @Nullable
    public Drawable A00;
    @Nullable
    public AbstractC00940Mi A01;
    public final C00930Mh A02 = new C00930Mh();

    @Nullable
    public Drawable A01(@Nullable Drawable drawable) {
        Drawable drawable2 = this.A00;
        C10281lk.A01(drawable2, null, null);
        C10281lk.A01(drawable, null, null);
        C10281lk.A03(drawable, this.A02);
        C10281lk.A02(drawable, this);
        C10281lk.A01(drawable, this, this);
        this.A00 = drawable;
        invalidateSelf();
        return drawable2;
    }

    @Override // X.AbstractC00940Mi
    public final void A4r(RectF rectF) {
        AbstractC00940Mi r0 = this.A01;
        if (r0 != null) {
            r0.A4r(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @Override // X.AbstractC00940Mi
    public final void A55(Matrix matrix) {
        if (this instanceof AnonymousClass1jV) {
            AnonymousClass1jV r2 = (AnonymousClass1jV) this;
            AbstractC00940Mi r0 = ((AnonymousClass1lb) r2).A01;
            if (r0 != null) {
                r0.A55(matrix);
            } else {
                matrix.reset();
            }
            if (!(r2.A01 == r2.getCurrent().getIntrinsicWidth() && r2.A00 == r2.getCurrent().getIntrinsicHeight())) {
                AnonymousClass1jV.A00(r2);
            }
            Matrix matrix2 = r2.A02;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
        } else if (!(this instanceof C09681jU)) {
            AbstractC00940Mi r02 = this.A01;
            if (r02 != null) {
                r02.A55(matrix);
            } else {
                matrix.reset();
            }
        } else {
            C09681jU r1 = (C09681jU) this;
            AbstractC00940Mi r03 = ((AnonymousClass1lb) r1).A01;
            if (r03 != null) {
                r03.A55(matrix);
            } else {
                matrix.reset();
            }
            Matrix matrix3 = r1.A02;
            if (!matrix3.isIdentity()) {
                matrix.preConcat(matrix3);
            }
        }
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    @Nullable
    public final Drawable.ConstantState getConstantState() {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return super.getConstantState();
        }
        return drawable.getConstantState();
    }

    @Nullable
    public final Drawable getCurrent() {
        return this.A00;
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return super.getIntrinsicHeight();
        }
        return drawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return super.getIntrinsicWidth();
        }
        return drawable.getIntrinsicWidth();
    }

    public final int getOpacity() {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return 0;
        }
        return drawable.getOpacity();
    }

    public final boolean getPadding(Rect rect) {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return super.getPadding(rect);
        }
        return drawable.getPadding(rect);
    }

    public final boolean isStateful() {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return false;
        }
        return drawable.isStateful();
    }

    public final Drawable mutate() {
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    public final boolean onLevelChange(int i) {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return super.onLevelChange(i);
        }
        return drawable.setLevel(i);
    }

    public final boolean onStateChange(int[] iArr) {
        Drawable drawable = this.A00;
        if (drawable == null) {
            return super.onStateChange(iArr);
        }
        return drawable.setState(iArr);
    }

    public final void setAlpha(int i) {
        this.A02.A00 = i;
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setAlpha(i);
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        C00930Mh r1 = this.A02;
        r1.A03 = colorFilter;
        r1.A04 = true;
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
    }

    public final void setDither(boolean z) {
        this.A02.A01 = z ? 1 : 0;
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setDither(z);
        }
    }

    public final void setFilterBitmap(boolean z) {
        this.A02.A02 = z ? 1 : 0;
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setFilterBitmap(z);
        }
    }

    @TargetApi(21)
    public final void setHotspot(float f, float f2) {
        Drawable drawable = this.A00;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    public AnonymousClass1lb(@Nullable Drawable drawable) {
        this.A00 = drawable;
        C10281lk.A01(drawable, this, this);
    }

    @Override // X.AnonymousClass0Mg
    @Nullable
    public final Drawable A3p() {
        return getCurrent();
    }

    @Override // X.AnonymousClass0Mg
    public final Drawable A9r(@Nullable Drawable drawable) {
        return A01(drawable);
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.A00;
        if (drawable == null) {
            return visible;
        }
        return drawable.setVisible(z, z2);
    }

    @Override // X.AnonymousClass1mn
    public final void AAF(AbstractC00940Mi r1) {
        this.A01 = r1;
    }

    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }
}
