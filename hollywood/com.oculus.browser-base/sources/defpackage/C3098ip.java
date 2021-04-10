package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.material.chip.Chip;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* renamed from: ip  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3098ip extends C3234jd0 implements Drawable.Callback, AbstractC3586lg1 {
    public static final int[] d0 = {16842910};
    public static final ShapeDrawable e0 = new ShapeDrawable(new OvalShape());
    public ColorStateList A0;
    public float B0;
    public float C0;
    public float D0;
    public float E0;
    public float F0;
    public float G0;
    public float H0;
    public float I0;
    public final Context J0;
    public final Paint K0 = new Paint(1);
    public final Paint.FontMetrics L0 = new Paint.FontMetrics();
    public final RectF M0 = new RectF();
    public final PointF N0 = new PointF();
    public final Path O0 = new Path();
    public final C3757mg1 P0;
    public int Q0;
    public int R0;
    public int S0;
    public int T0;
    public int U0;
    public int V0;
    public boolean W0;
    public int X0;
    public int Y0 = 255;
    public ColorFilter Z0;
    public PorterDuffColorFilter a1;
    public ColorStateList b1;
    public PorterDuff.Mode c1 = PorterDuff.Mode.SRC_IN;
    public int[] d1;
    public boolean e1;
    public ColorStateList f0;
    public ColorStateList f1;
    public ColorStateList g0;
    public WeakReference g1 = new WeakReference(null);
    public float h0;
    public TextUtils.TruncateAt h1;
    public float i0 = -1.0f;
    public boolean i1;
    public ColorStateList j0;
    public int j1;
    public float k0;
    public boolean k1;
    public ColorStateList l0;
    public CharSequence m0;
    public boolean n0;
    public Drawable o0;
    public ColorStateList p0;
    public float q0;
    public boolean r0;
    public boolean s0;
    public Drawable t0;
    public Drawable u0;
    public ColorStateList v0;
    public float w0;
    public boolean x0;
    public boolean y0;
    public Drawable z0;

    public C3098ip(Context context, AttributeSet attributeSet, int i, int i2) {
        super(C3553lT0.b(context, attributeSet, i, i2).a());
        this.H.b = new EK(context);
        s();
        this.J0 = context;
        C3757mg1 mg1 = new C3757mg1(this);
        this.P0 = mg1;
        this.m0 = "";
        mg1.f10438a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = d0;
        setState(iArr);
        G(iArr);
        this.i1 = true;
        int[] iArr2 = AbstractC2004cN0.f9604a;
        e0.setTint(-1);
    }

    public static boolean A(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean B(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public void C() {
        AbstractC2928hp hpVar = (AbstractC2928hp) this.g1.get();
        if (hpVar != null) {
            Chip chip = (Chip) hpVar;
            chip.b(chip.V);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:77:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0147  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean D(int[] r9, int[] r10) {
        /*
        // Method dump skipped, instructions count: 331
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3098ip.D(int[], int[]):boolean");
    }

    public void E(boolean z) {
        if (this.y0 != z) {
            boolean L = L();
            this.y0 = z;
            boolean L2 = L();
            if (L != L2) {
                if (L2) {
                    t(this.z0);
                } else {
                    O(this.z0);
                }
                invalidateSelf();
                C();
            }
        }
    }

    public void F(boolean z) {
        if (this.n0 != z) {
            boolean M = M();
            this.n0 = z;
            boolean M2 = M();
            if (M != M2) {
                if (M2) {
                    t(this.o0);
                } else {
                    O(this.o0);
                }
                invalidateSelf();
                C();
            }
        }
    }

    public boolean G(int[] iArr) {
        if (Arrays.equals(this.d1, iArr)) {
            return false;
        }
        this.d1 = iArr;
        if (N()) {
            return D(getState(), iArr);
        }
        return false;
    }

    public void H(boolean z) {
        if (this.s0 != z) {
            boolean N = N();
            this.s0 = z;
            boolean N2 = N();
            if (N != N2) {
                if (N2) {
                    t(this.t0);
                } else {
                    O(this.t0);
                }
                invalidateSelf();
                C();
            }
        }
    }

    public void I(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (!TextUtils.equals(this.m0, charSequence)) {
            this.m0 = charSequence;
            this.P0.d = true;
            invalidateSelf();
            C();
        }
    }

    public void J(C0870Of1 of1) {
        C3757mg1 mg1 = this.P0;
        Context context = this.J0;
        if (mg1.f != of1) {
            mg1.f = of1;
            if (of1 != null) {
                TextPaint textPaint = mg1.f10438a;
                AbstractC0931Pf1 pf1 = mg1.b;
                of1.a();
                of1.d(textPaint, of1.l);
                of1.b(context, new C0809Nf1(of1, textPaint, pf1));
                AbstractC3586lg1 lg1 = (AbstractC3586lg1) mg1.e.get();
                if (lg1 != null) {
                    mg1.f10438a.drawableState = lg1.getState();
                }
                of1.c(context, mg1.f10438a, mg1.b);
                mg1.d = true;
            }
            AbstractC3586lg1 lg12 = (AbstractC3586lg1) mg1.e.get();
            if (lg12 != null) {
                C3098ip ipVar = (C3098ip) lg12;
                ipVar.C();
                ipVar.invalidateSelf();
                ipVar.onStateChange(lg12.getState());
            }
        }
    }

    public void K(boolean z) {
        if (this.e1 != z) {
            this.e1 = z;
            this.f1 = z ? AbstractC2004cN0.b(this.l0) : null;
            onStateChange(getState());
        }
    }

    public final boolean L() {
        return this.y0 && this.z0 != null && this.W0;
    }

    public final boolean M() {
        return this.n0 && this.o0 != null;
    }

    public final boolean N() {
        return this.s0 && this.t0 != null;
    }

    public final void O(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    @Override // defpackage.C3234jd0
    public void draw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && (i = this.Y0) != 0) {
            int saveLayerAlpha = i < 255 ? canvas.saveLayerAlpha((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, i) : 0;
            if (!this.k1) {
                this.K0.setColor(this.Q0);
                this.K0.setStyle(Paint.Style.FILL);
                this.M0.set(bounds);
                canvas.drawRoundRect(this.M0, z(), z(), this.K0);
            }
            if (!this.k1) {
                this.K0.setColor(this.R0);
                this.K0.setStyle(Paint.Style.FILL);
                Paint paint = this.K0;
                ColorFilter colorFilter = this.Z0;
                if (colorFilter == null) {
                    colorFilter = this.a1;
                }
                paint.setColorFilter(colorFilter);
                this.M0.set(bounds);
                canvas.drawRoundRect(this.M0, z(), z(), this.K0);
            }
            if (this.k1) {
                super.draw(canvas);
            }
            if (this.k0 > 0.0f && !this.k1) {
                this.K0.setColor(this.T0);
                this.K0.setStyle(Paint.Style.STROKE);
                if (!this.k1) {
                    Paint paint2 = this.K0;
                    ColorFilter colorFilter2 = this.Z0;
                    if (colorFilter2 == null) {
                        colorFilter2 = this.a1;
                    }
                    paint2.setColorFilter(colorFilter2);
                }
                RectF rectF = this.M0;
                float f = this.k0 / 2.0f;
                rectF.set(((float) bounds.left) + f, ((float) bounds.top) + f, ((float) bounds.right) - f, ((float) bounds.bottom) - f);
                float f2 = this.i0 - (this.k0 / 2.0f);
                canvas.drawRoundRect(this.M0, f2, f2, this.K0);
            }
            this.K0.setColor(this.U0);
            this.K0.setStyle(Paint.Style.FILL);
            this.M0.set(bounds);
            if (!this.k1) {
                canvas.drawRoundRect(this.M0, z(), z(), this.K0);
            } else {
                c(new RectF(bounds), this.O0);
                g(canvas, this.K0, this.O0, this.H.f10150a, h());
            }
            if (M()) {
                u(bounds, this.M0);
                RectF rectF2 = this.M0;
                float f3 = rectF2.left;
                float f4 = rectF2.top;
                canvas.translate(f3, f4);
                this.o0.setBounds(0, 0, (int) this.M0.width(), (int) this.M0.height());
                this.o0.draw(canvas);
                canvas.translate(-f3, -f4);
            }
            if (L()) {
                u(bounds, this.M0);
                RectF rectF3 = this.M0;
                float f5 = rectF3.left;
                float f6 = rectF3.top;
                canvas.translate(f5, f6);
                this.z0.setBounds(0, 0, (int) this.M0.width(), (int) this.M0.height());
                this.z0.draw(canvas);
                canvas.translate(-f5, -f6);
            }
            if (!this.i1 || this.m0 == null) {
                i2 = saveLayerAlpha;
                i3 = 0;
                i4 = 255;
            } else {
                PointF pointF = this.N0;
                pointF.set(0.0f, 0.0f);
                Paint.Align align = Paint.Align.LEFT;
                if (this.m0 != null) {
                    float v = v() + this.B0 + this.E0;
                    if (getLayoutDirection() == 0) {
                        pointF.x = ((float) bounds.left) + v;
                        align = Paint.Align.LEFT;
                    } else {
                        pointF.x = ((float) bounds.right) - v;
                        align = Paint.Align.RIGHT;
                    }
                    this.P0.f10438a.getFontMetrics(this.L0);
                    Paint.FontMetrics fontMetrics = this.L0;
                    pointF.y = ((float) bounds.centerY()) - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
                }
                RectF rectF4 = this.M0;
                rectF4.setEmpty();
                if (this.m0 != null) {
                    float v2 = v() + this.B0 + this.E0;
                    float y = y() + this.I0 + this.F0;
                    if (getLayoutDirection() == 0) {
                        rectF4.left = ((float) bounds.left) + v2;
                        rectF4.right = ((float) bounds.right) - y;
                    } else {
                        rectF4.left = ((float) bounds.left) + y;
                        rectF4.right = ((float) bounds.right) - v2;
                    }
                    rectF4.top = (float) bounds.top;
                    rectF4.bottom = (float) bounds.bottom;
                }
                C3757mg1 mg1 = this.P0;
                if (mg1.f != null) {
                    mg1.f10438a.drawableState = getState();
                    C3757mg1 mg12 = this.P0;
                    mg12.f.c(this.J0, mg12.f10438a, mg12.b);
                }
                this.P0.f10438a.setTextAlign(align);
                boolean z = Math.round(this.P0.a(this.m0.toString())) > Math.round(this.M0.width());
                if (z) {
                    i5 = canvas.save();
                    canvas.clipRect(this.M0);
                } else {
                    i5 = 0;
                }
                CharSequence charSequence = this.m0;
                if (z && this.h1 != null) {
                    charSequence = TextUtils.ellipsize(charSequence, this.P0.f10438a, this.M0.width(), this.h1);
                }
                int length = charSequence.length();
                PointF pointF2 = this.N0;
                i2 = saveLayerAlpha;
                i3 = 0;
                i4 = 255;
                canvas.drawText(charSequence, 0, length, pointF2.x, pointF2.y, this.P0.f10438a);
                if (z) {
                    canvas.restoreToCount(i5);
                }
            }
            if (N()) {
                w(bounds, this.M0);
                RectF rectF5 = this.M0;
                float f7 = rectF5.left;
                float f8 = rectF5.top;
                canvas.translate(f7, f8);
                this.t0.setBounds(i3, i3, (int) this.M0.width(), (int) this.M0.height());
                int[] iArr = AbstractC2004cN0.f9604a;
                this.u0.setBounds(this.t0.getBounds());
                this.u0.jumpToCurrentState();
                this.u0.draw(canvas);
                canvas.translate(-f7, -f8);
            }
            if (this.Y0 < i4) {
                canvas.restoreToCount(i2);
            }
        }
    }

    public int getAlpha() {
        return this.Y0;
    }

    public ColorFilter getColorFilter() {
        return this.Z0;
    }

    public int getIntrinsicHeight() {
        return (int) this.h0;
    }

    public int getIntrinsicWidth() {
        return Math.min(Math.round(y() + this.P0.a(this.m0.toString()) + v() + this.B0 + this.E0 + this.F0 + this.I0), this.j1);
    }

    @Override // defpackage.C3234jd0
    public int getOpacity() {
        return -3;
    }

    @Override // defpackage.C3234jd0
    public void getOutline(Outline outline) {
        if (this.k1) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.i0);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.h0, this.i0);
        }
        outline.setAlpha(((float) this.Y0) / 255.0f);
    }

    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // defpackage.C3234jd0
    public boolean isStateful() {
        ColorStateList colorStateList;
        if (!A(this.f0) && !A(this.g0) && !A(this.j0) && (!this.e1 || !A(this.f1))) {
            C0870Of1 of1 = this.P0.f;
            if (!((of1 == null || (colorStateList = of1.b) == null || !colorStateList.isStateful()) ? false : true)) {
                if (!(this.y0 && this.z0 != null && this.x0) && !B(this.o0) && !B(this.z0) && !A(this.b1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (M()) {
            onLayoutDirectionChanged |= this.o0.setLayoutDirection(i);
        }
        if (L()) {
            onLayoutDirectionChanged |= this.z0.setLayoutDirection(i);
        }
        if (N()) {
            onLayoutDirectionChanged |= this.t0.setLayoutDirection(i);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    public boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (M()) {
            onLevelChange |= this.o0.setLevel(i);
        }
        if (L()) {
            onLevelChange |= this.z0.setLevel(i);
        }
        if (N()) {
            onLevelChange |= this.t0.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    @Override // defpackage.C3234jd0
    public boolean onStateChange(int[] iArr) {
        if (this.k1) {
            super.onStateChange(iArr);
        }
        return D(iArr, this.d1);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // defpackage.C3234jd0
    public void setAlpha(int i) {
        if (this.Y0 != i) {
            this.Y0 = i;
            invalidateSelf();
        }
    }

    @Override // defpackage.C3234jd0
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.Z0 != colorFilter) {
            this.Z0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // defpackage.C3234jd0
    public void setTintList(ColorStateList colorStateList) {
        if (this.b1 != colorStateList) {
            this.b1 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // defpackage.C3234jd0
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.c1 != mode) {
            this.c1 = mode;
            this.a1 = WI.a(this, this.b1, mode);
            invalidateSelf();
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (M()) {
            visible |= this.o0.setVisible(z, z2);
        }
        if (L()) {
            visible |= this.z0.setVisible(z, z2);
        }
        if (N()) {
            visible |= this.t0.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public final void t(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.t0) {
                if (drawable.isStateful()) {
                    drawable.setState(this.d1);
                }
                drawable.setTintList(this.v0);
                return;
            }
            if (drawable.isStateful()) {
                drawable.setState(getState());
            }
            Drawable drawable2 = this.o0;
            if (drawable == drawable2 && this.r0) {
                drawable2.setTintList(this.p0);
            }
        }
    }

    public final void u(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (M() || L()) {
            float f = this.B0 + this.C0;
            if (getLayoutDirection() == 0) {
                float f2 = ((float) rect.left) + f;
                rectF.left = f2;
                rectF.right = f2 + this.q0;
            } else {
                float f3 = ((float) rect.right) - f;
                rectF.right = f3;
                rectF.left = f3 - this.q0;
            }
            float exactCenterY = rect.exactCenterY();
            float f4 = this.q0;
            float f5 = exactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public float v() {
        if (M() || L()) {
            return this.C0 + this.q0 + this.D0;
        }
        return 0.0f;
    }

    public final void w(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (N()) {
            float f = this.I0 + this.H0;
            if (getLayoutDirection() == 0) {
                float f2 = ((float) rect.right) - f;
                rectF.right = f2;
                rectF.left = f2 - this.w0;
            } else {
                float f3 = ((float) rect.left) + f;
                rectF.left = f3;
                rectF.right = f3 + this.w0;
            }
            float exactCenterY = rect.exactCenterY();
            float f4 = this.w0;
            float f5 = exactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    public final void x(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (N()) {
            float f = this.I0 + this.H0 + this.w0 + this.G0 + this.F0;
            if (getLayoutDirection() == 0) {
                float f2 = (float) rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i = rect.left;
                rectF.left = (float) i;
                rectF.right = ((float) i) + f;
            }
            rectF.top = (float) rect.top;
            rectF.bottom = (float) rect.bottom;
        }
    }

    public float y() {
        if (N()) {
            return this.G0 + this.w0 + this.H0;
        }
        return 0.0f;
    }

    public float z() {
        if (this.k1) {
            return this.H.f10150a.e.a(h());
        }
        return this.i0;
    }
}
