package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: Fs1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Fs1 extends AbstractC5327vs1 {
    public static final PorterDuff.Mode G = PorterDuff.Mode.SRC_IN;
    public Ds1 H;
    public PorterDuffColorFilter I;

    /* renamed from: J  reason: collision with root package name */
    public ColorFilter f8045J;
    public boolean K;
    public boolean L;
    public final float[] M;
    public final Matrix N;
    public final Rect O;

    public Fs1() {
        this.L = true;
        this.M = new float[9];
        this.N = new Matrix();
        this.O = new Rect();
        this.H = new Ds1();
    }

    public static Fs1 a(Resources resources, int i, Resources.Theme theme) {
        Fs1 fs1 = new Fs1();
        fs1.F = resources.getDrawable(i, theme);
        return fs1;
    }

    public PorterDuffColorFilter b(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.F;
        if (drawable == null) {
            return false;
        }
        drawable.canApplyTheme();
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cf, code lost:
        if ((r1 == r7.getWidth() && r3 == r6.f.getHeight()) == false) goto L_0x00d1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r11) {
        /*
        // Method dump skipped, instructions count: 360
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Fs1.draw(android.graphics.Canvas):void");
    }

    public int getAlpha() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getAlpha();
        }
        return this.H.b.getRootAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.H.getChangingConfigurations();
    }

    public ColorFilter getColorFilter() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getColorFilter();
        }
        return this.f8045J;
    }

    public Drawable.ConstantState getConstantState() {
        if (this.F != null) {
            return new Es1(this.F.getConstantState());
        }
        this.H.f7918a = getChangingConfigurations();
        return this.H;
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return (int) this.H.b.k;
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return (int) this.H.b.j;
    }

    public int getOpacity() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    public void invalidateSelf() {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.isAutoMirrored();
        }
        return this.H.e;
    }

    public boolean isStateful() {
        Ds1 ds1;
        ColorStateList colorStateList;
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return super.isStateful() || ((ds1 = this.H) != null && (ds1.a() || ((colorStateList = this.H.c) != null && colorStateList.isStateful())));
    }

    public Drawable mutate() {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.K && super.mutate() == this) {
            this.H = new Ds1(this.H);
            this.K = true;
        }
        return this;
    }

    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        Ds1 ds1 = this.H;
        ColorStateList colorStateList = ds1.c;
        if (!(colorStateList == null || (mode = ds1.d) == null)) {
            this.I = b(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (ds1.a()) {
            boolean b = ds1.b.i.b(iArr);
            ds1.k |= b;
            if (b) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    public void setAlpha(int i) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.H.b.getRootAlpha() != i) {
            this.H.b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.H.e = z;
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        this.f8045J = colorFilter;
        invalidateSelf();
    }

    public void setTint(int i) {
        Drawable drawable = this.F;
        if (drawable != null) {
            VI.a(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.F;
        if (drawable != null) {
            VI.b(drawable, colorStateList);
            return;
        }
        Ds1 ds1 = this.H;
        if (ds1.c != colorStateList) {
            ds1.c = colorStateList;
            this.I = b(colorStateList, ds1.d);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.F;
        if (drawable != null) {
            VI.c(drawable, mode);
            return;
        }
        Ds1 ds1 = this.H;
        if (ds1.d != mode) {
            ds1.d = mode;
            this.I = b(ds1.c, mode);
            invalidateSelf();
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.F;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.F;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:201:0x04dd  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f7  */
    @Override // android.graphics.drawable.Drawable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inflate(android.content.res.Resources r29, org.xmlpull.v1.XmlPullParser r30, android.util.AttributeSet r31, android.content.res.Resources.Theme r32) {
        /*
        // Method dump skipped, instructions count: 1284
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Fs1.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    public Fs1(Ds1 ds1) {
        this.L = true;
        this.M = new float[9];
        this.N = new Matrix();
        this.O = new Rect();
        this.H = ds1;
        this.I = b(ds1.c, ds1.d);
    }
}
