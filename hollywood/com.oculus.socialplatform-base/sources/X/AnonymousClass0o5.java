package X;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* renamed from: X.0o5  reason: invalid class name */
public class AnonymousClass0o5 extends Drawable implements AbstractC00940Mi, Drawable.Callback, AnonymousClass1mn {
    @Nullable
    public AbstractC00940Mi A00;
    public boolean A01;
    public boolean A02;
    public boolean A03;
    public final Drawable[] A04;
    public final AnonymousClass0Mg[] A05;
    public final Rect A06 = new Rect();
    public final C00930Mh A07 = new C00930Mh();

    @Nullable
    public final Drawable A01(int i) {
        boolean z = true;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z2));
        Drawable[] drawableArr = this.A04;
        if (i >= drawableArr.length) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        return drawableArr[i];
    }

    @Nullable
    public final Drawable A02(int i, @Nullable Drawable drawable) {
        boolean z = true;
        C00740Ii.A01(true);
        Drawable[] drawableArr = this.A04;
        if (i >= drawableArr.length) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        Drawable drawable2 = drawableArr[i];
        if (drawable != drawable2) {
            if (drawable != null && this.A01) {
                drawable.mutate();
            }
            C10281lk.A01(drawableArr[i], null, null);
            C10281lk.A01(drawable, null, null);
            C10281lk.A03(drawable, this.A07);
            C10281lk.A02(drawable, this);
            C10281lk.A01(drawable, this, this);
            this.A03 = false;
            drawableArr[i] = drawable;
            invalidateSelf();
        }
        return drawable2;
    }

    public void draw(Canvas canvas) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.draw(canvas);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public final int getIntrinsicHeight() {
        int i = 0;
        int i2 = -1;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                i2 = Math.max(i2, drawable.getIntrinsicHeight());
            }
            i++;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    public final int getIntrinsicWidth() {
        int i = 0;
        int i2 = -1;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                i2 = Math.max(i2, drawable.getIntrinsicWidth());
            }
            i++;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    public final boolean getPadding(Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        Rect rect2 = this.A06;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i >= drawableArr.length) {
                return true;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                drawable.getPadding(rect2);
                rect.left = Math.max(rect.left, rect2.left);
                rect.top = Math.max(rect.top, rect2.top);
                rect.right = Math.max(rect.right, rect2.right);
                rect.bottom = Math.max(rect.bottom, rect2.bottom);
            }
            i++;
        }
    }

    public final Drawable mutate() {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.mutate();
                }
                i++;
            } else {
                this.A01 = true;
                return this;
            }
        }
    }

    public final void onBoundsChange(Rect rect) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setBounds(rect);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public final boolean onLevelChange(int i) {
        int i2 = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i2 >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[i2];
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
            i2++;
        }
    }

    public final boolean onStateChange(int[] iArr) {
        int i = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null && drawable.setState(iArr)) {
                z = true;
            }
            i++;
        }
    }

    @TargetApi(21)
    public final void setHotspot(float f, float f2) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setHotspot(f, f2);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // X.AbstractC00940Mi
    public final void A4r(RectF rectF) {
        AbstractC00940Mi r0 = this.A00;
        if (r0 != null) {
            r0.A4r(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @Override // X.AbstractC00940Mi
    public final void A55(Matrix matrix) {
        AbstractC00940Mi r0 = this.A00;
        if (r0 != null) {
            r0.A55(matrix);
        } else {
            matrix.reset();
        }
    }

    public final int getOpacity() {
        Drawable[] drawableArr = this.A04;
        int length = drawableArr.length;
        int i = -2;
        if (length != 0) {
            i = -1;
            for (int i2 = 1; i2 < length; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable != null) {
                    i = Drawable.resolveOpacity(i, drawable.getOpacity());
                }
            }
        }
        return i;
    }

    public final boolean isStateful() {
        if (!this.A03) {
            this.A02 = false;
            boolean z = false;
            int i = 0;
            while (true) {
                Drawable[] drawableArr = this.A04;
                boolean z2 = true;
                if (i >= drawableArr.length) {
                    break;
                }
                Drawable drawable = drawableArr[i];
                if (drawable == null || !drawable.isStateful()) {
                    z2 = false;
                }
                z |= z2;
                this.A02 = z;
                i++;
            }
            this.A03 = true;
        }
        return this.A02;
    }

    public void setAlpha(int i) {
        this.A07.A00 = i;
        int i2 = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i2 < drawableArr.length) {
                Drawable drawable = drawableArr[i2];
                if (drawable != null) {
                    drawable.setAlpha(i);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        C00930Mh r1 = this.A07;
        r1.A03 = colorFilter;
        r1.A04 = true;
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setColorFilter(colorFilter);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public final void setDither(boolean z) {
        this.A07.A01 = z ? 1 : 0;
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setDither(z);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public final void setFilterBitmap(boolean z) {
        this.A07.A02 = z ? 1 : 0;
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setFilterBitmap(z);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public AnonymousClass0o5(Drawable[] drawableArr) {
        int i = 0;
        this.A02 = false;
        this.A03 = false;
        this.A01 = false;
        this.A04 = drawableArr;
        while (true) {
            Drawable[] drawableArr2 = this.A04;
            int length = drawableArr2.length;
            if (i < length) {
                C10281lk.A01(drawableArr2[i], this, this);
                i++;
            } else {
                this.A05 = new AnonymousClass0Mg[length];
                return;
            }
        }
    }

    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.A04;
            if (i >= drawableArr.length) {
                return visible;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
            i++;
        }
    }

    @Override // X.AnonymousClass1mn
    public final void AAF(AbstractC00940Mi r1) {
        this.A00 = r1;
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
