package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Chip extends N7 implements AbstractC2928hp, AbstractC5258vT0 {
    public static final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public static final int[] f9689J = {16842913};
    public static final int[] K = {16842911};
    public C3098ip L;
    public InsetDrawable M;
    public RippleDrawable N;
    public CompoundButton.OnCheckedChangeListener O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public int U;
    public int V;
    public final C2757gp W;
    public final Rect a0 = new Rect();
    public final RectF b0 = new RectF();
    public final AbstractC0931Pf1 c0 = new C2415ep(this);

    public Chip(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110), attributeSet, R.attr.f2850_resource_name_obfuscated_RES_2130968731);
        ColorStateList b;
        int resourceId;
        Context context2 = getContext();
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
                Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            } else if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                Log.w("Chip", "Chip text must be vertically center and start aligned");
            }
        }
        C3098ip ipVar = new C3098ip(context2, attributeSet, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110);
        Context context3 = ipVar.J0;
        int[] iArr = FJ0.z;
        TypedArray d = AbstractC1178Tg1.d(context3, attributeSet, iArr, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110, new int[0]);
        ipVar.k1 = d.hasValue(36);
        ColorStateList b2 = AbstractC2722gd0.b(ipVar.J0, d, 23);
        if (ipVar.f0 != b2) {
            ipVar.f0 = b2;
            ipVar.onStateChange(ipVar.getState());
        }
        ColorStateList b3 = AbstractC2722gd0.b(ipVar.J0, d, 10);
        if (ipVar.g0 != b3) {
            ipVar.g0 = b3;
            ipVar.onStateChange(ipVar.getState());
        }
        float dimension = d.getDimension(18, 0.0f);
        if (ipVar.h0 != dimension) {
            ipVar.h0 = dimension;
            ipVar.invalidateSelf();
            ipVar.C();
        }
        if (d.hasValue(11)) {
            float dimension2 = d.getDimension(11, 0.0f);
            if (ipVar.i0 != dimension2) {
                ipVar.i0 = dimension2;
                ipVar.H.f10150a = ipVar.H.f10150a.e(dimension2);
                ipVar.invalidateSelf();
            }
        }
        ColorStateList b4 = AbstractC2722gd0.b(ipVar.J0, d, 21);
        if (ipVar.j0 != b4) {
            ipVar.j0 = b4;
            if (ipVar.k1) {
                ipVar.p(b4);
            }
            ipVar.onStateChange(ipVar.getState());
        }
        float dimension3 = d.getDimension(22, 0.0f);
        if (ipVar.k0 != dimension3) {
            ipVar.k0 = dimension3;
            ipVar.K0.setStrokeWidth(dimension3);
            if (ipVar.k1) {
                ipVar.H.l = dimension3;
                ipVar.invalidateSelf();
            }
            ipVar.invalidateSelf();
        }
        ColorStateList b5 = AbstractC2722gd0.b(ipVar.J0, d, 35);
        if (ipVar.l0 != b5) {
            ipVar.l0 = b5;
            ipVar.f1 = ipVar.e1 ? AbstractC2004cN0.b(b5) : null;
            ipVar.onStateChange(ipVar.getState());
        }
        ipVar.I(d.getText(4));
        ipVar.J((!d.hasValue(0) || (resourceId = d.getResourceId(0, 0)) == 0) ? null : new C0870Of1(ipVar.J0, resourceId));
        int i = d.getInt(2, 0);
        if (i == 1) {
            ipVar.h1 = TextUtils.TruncateAt.START;
        } else if (i == 2) {
            ipVar.h1 = TextUtils.TruncateAt.MIDDLE;
        } else if (i == 3) {
            ipVar.h1 = TextUtils.TruncateAt.END;
        }
        ipVar.F(d.getBoolean(17, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") != null)) {
            ipVar.F(d.getBoolean(14, false));
        }
        Drawable c = AbstractC2722gd0.c(ipVar.J0, d, 13);
        Drawable drawable = ipVar.o0;
        Drawable d2 = drawable != null ? VI.d(drawable) : null;
        if (d2 != c) {
            float v = ipVar.v();
            ipVar.o0 = c != null ? c.mutate() : null;
            float v2 = ipVar.v();
            ipVar.O(d2);
            if (ipVar.M()) {
                ipVar.t(ipVar.o0);
            }
            ipVar.invalidateSelf();
            if (v != v2) {
                ipVar.C();
            }
        }
        if (d.hasValue(16)) {
            ColorStateList b6 = AbstractC2722gd0.b(ipVar.J0, d, 16);
            ipVar.r0 = true;
            if (ipVar.p0 != b6) {
                ipVar.p0 = b6;
                if (ipVar.M()) {
                    ipVar.o0.setTintList(b6);
                }
                ipVar.onStateChange(ipVar.getState());
            }
        }
        float dimension4 = d.getDimension(15, 0.0f);
        if (ipVar.q0 != dimension4) {
            float v3 = ipVar.v();
            ipVar.q0 = dimension4;
            float v4 = ipVar.v();
            ipVar.invalidateSelf();
            if (v3 != v4) {
                ipVar.C();
            }
        }
        ipVar.H(d.getBoolean(30, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") != null)) {
            ipVar.H(d.getBoolean(25, false));
        }
        Drawable c2 = AbstractC2722gd0.c(ipVar.J0, d, 24);
        Drawable drawable2 = ipVar.t0;
        Drawable d3 = drawable2 != null ? VI.d(drawable2) : null;
        if (d3 != c2) {
            float y = ipVar.y();
            ipVar.t0 = c2 != null ? c2.mutate() : null;
            int[] iArr2 = AbstractC2004cN0.f9604a;
            ipVar.u0 = new RippleDrawable(AbstractC2004cN0.b(ipVar.l0), ipVar.t0, C3098ip.e0);
            float y2 = ipVar.y();
            ipVar.O(d3);
            if (ipVar.N()) {
                ipVar.t(ipVar.t0);
            }
            ipVar.invalidateSelf();
            if (y != y2) {
                ipVar.C();
            }
        }
        ColorStateList b7 = AbstractC2722gd0.b(ipVar.J0, d, 29);
        if (ipVar.v0 != b7) {
            ipVar.v0 = b7;
            if (ipVar.N()) {
                ipVar.t0.setTintList(b7);
            }
            ipVar.onStateChange(ipVar.getState());
        }
        float dimension5 = d.getDimension(27, 0.0f);
        if (ipVar.w0 != dimension5) {
            ipVar.w0 = dimension5;
            ipVar.invalidateSelf();
            if (ipVar.N()) {
                ipVar.C();
            }
        }
        boolean z = d.getBoolean(5, false);
        if (ipVar.x0 != z) {
            ipVar.x0 = z;
            float v5 = ipVar.v();
            if (!z && ipVar.W0) {
                ipVar.W0 = false;
            }
            float v6 = ipVar.v();
            ipVar.invalidateSelf();
            if (v5 != v6) {
                ipVar.C();
            }
        }
        ipVar.E(d.getBoolean(9, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") != null)) {
            ipVar.E(d.getBoolean(7, false));
        }
        Drawable c3 = AbstractC2722gd0.c(ipVar.J0, d, 6);
        if (ipVar.z0 != c3) {
            float v7 = ipVar.v();
            ipVar.z0 = c3;
            float v8 = ipVar.v();
            ipVar.O(ipVar.z0);
            ipVar.t(ipVar.z0);
            ipVar.invalidateSelf();
            if (v7 != v8) {
                ipVar.C();
            }
        }
        if (d.hasValue(8) && ipVar.A0 != (b = AbstractC2722gd0.b(ipVar.J0, d, 8))) {
            ipVar.A0 = b;
            if (ipVar.y0 && ipVar.z0 != null && ipVar.x0) {
                ipVar.z0.setTintList(b);
            }
            ipVar.onStateChange(ipVar.getState());
        }
        C1373Wl0.a(ipVar.J0, d, 38);
        C1373Wl0.a(ipVar.J0, d, 32);
        float dimension6 = d.getDimension(20, 0.0f);
        if (ipVar.B0 != dimension6) {
            ipVar.B0 = dimension6;
            ipVar.invalidateSelf();
            ipVar.C();
        }
        float dimension7 = d.getDimension(34, 0.0f);
        if (ipVar.C0 != dimension7) {
            float v9 = ipVar.v();
            ipVar.C0 = dimension7;
            float v10 = ipVar.v();
            ipVar.invalidateSelf();
            if (v9 != v10) {
                ipVar.C();
            }
        }
        float dimension8 = d.getDimension(33, 0.0f);
        if (ipVar.D0 != dimension8) {
            float v11 = ipVar.v();
            ipVar.D0 = dimension8;
            float v12 = ipVar.v();
            ipVar.invalidateSelf();
            if (v11 != v12) {
                ipVar.C();
            }
        }
        float dimension9 = d.getDimension(40, 0.0f);
        if (ipVar.E0 != dimension9) {
            ipVar.E0 = dimension9;
            ipVar.invalidateSelf();
            ipVar.C();
        }
        float dimension10 = d.getDimension(39, 0.0f);
        if (ipVar.F0 != dimension10) {
            ipVar.F0 = dimension10;
            ipVar.invalidateSelf();
            ipVar.C();
        }
        float dimension11 = d.getDimension(28, 0.0f);
        if (ipVar.G0 != dimension11) {
            ipVar.G0 = dimension11;
            ipVar.invalidateSelf();
            if (ipVar.N()) {
                ipVar.C();
            }
        }
        float dimension12 = d.getDimension(26, 0.0f);
        if (ipVar.H0 != dimension12) {
            ipVar.H0 = dimension12;
            ipVar.invalidateSelf();
            if (ipVar.N()) {
                ipVar.C();
            }
        }
        float dimension13 = d.getDimension(12, 0.0f);
        if (ipVar.I0 != dimension13) {
            ipVar.I0 = dimension13;
            ipVar.invalidateSelf();
            ipVar.C();
        }
        ipVar.j1 = d.getDimensionPixelSize(3, Integer.MAX_VALUE);
        d.recycle();
        AbstractC1178Tg1.a(context2, attributeSet, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110);
        AbstractC1178Tg1.b(context2, attributeSet, iArr, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110);
        this.T = obtainStyledAttributes.getBoolean(31, false);
        this.V = (int) Math.ceil((double) obtainStyledAttributes.getDimension(19, (float) Math.ceil((double) AbstractC4486qv1.a(getContext(), 48))));
        obtainStyledAttributes.recycle();
        C3098ip ipVar2 = this.L;
        if (ipVar2 != ipVar) {
            if (ipVar2 != null) {
                ipVar2.g1 = new WeakReference(null);
            }
            this.L = ipVar;
            ipVar.i1 = false;
            ipVar.g1 = new WeakReference(this);
            b(this.V);
        }
        ipVar.n(getElevation());
        AbstractC1178Tg1.a(context2, attributeSet, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110);
        AbstractC1178Tg1.b(context2, attributeSet, iArr, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110, new int[0]);
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, iArr, R.attr.f2850_resource_name_obfuscated_RES_2130968731, R.style.f75370_resource_name_obfuscated_RES_2132018110);
        boolean hasValue = obtainStyledAttributes2.hasValue(36);
        obtainStyledAttributes2.recycle();
        this.W = new C2757gp(this, this);
        if (f()) {
            C3098ip ipVar3 = this.L;
        }
        AbstractC1920bu1.n(this, null);
        if (!hasValue) {
            setOutlineProvider(new C2586fp(this));
        }
        setChecked(this.P);
        setText(ipVar.m0);
        setEllipsize(ipVar.h1);
        setIncludeFontPadding(false);
        l();
        if (!this.L.i1) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        k();
        if (this.T) {
            setMinHeight(this.V);
        }
        this.U = getLayoutDirection();
    }

    @Override // defpackage.AbstractC5258vT0
    public void a(C3553lT0 lt0) {
        C3098ip ipVar = this.L;
        ipVar.H.f10150a = lt0;
        ipVar.invalidateSelf();
    }

    public boolean b(int i) {
        this.V = i;
        int i2 = 0;
        if (!this.T) {
            if (this.M != null) {
                h();
            } else {
                j();
            }
            return false;
        }
        int max = Math.max(0, i - ((int) this.L.h0));
        int max2 = Math.max(0, i - this.L.getIntrinsicWidth());
        if (max2 > 0 || max > 0) {
            int i3 = max2 > 0 ? max2 / 2 : 0;
            if (max > 0) {
                i2 = max / 2;
            }
            if (this.M != null) {
                Rect rect = new Rect();
                this.M.getPadding(rect);
                if (rect.top == i2 && rect.bottom == i2 && rect.left == i3 && rect.right == i3) {
                    j();
                    return true;
                }
            }
            if (getMinHeight() != i) {
                setMinHeight(i);
            }
            if (getMinWidth() != i) {
                setMinWidth(i);
            }
            this.M = new InsetDrawable((Drawable) this.L, i3, i2, i3, i2);
            j();
            return true;
        }
        if (this.M != null) {
            h();
        } else {
            j();
        }
        return false;
    }

    public Drawable c() {
        InsetDrawable insetDrawable = this.M;
        return insetDrawable == null ? this.L : insetDrawable;
    }

    public final RectF d() {
        this.b0.setEmpty();
        if (f()) {
            C3098ip ipVar = this.L;
            ipVar.x(ipVar.getBounds(), this.b0);
        }
        return this.b0;
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        boolean z;
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = AbstractC5583xM.class.getDeclaredField("p");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.W)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = AbstractC5583xM.class.getDeclaredMethod("z", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.W, Integer.MIN_VALUE);
                    z = true;
                    return !z ? true : true;
                }
            } catch (NoSuchMethodException e) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e);
            } catch (IllegalAccessException e2) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e2);
            } catch (InvocationTargetException e3) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e3);
            } catch (NoSuchFieldException e4) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e4);
            }
        }
        z = false;
        return !z ? true : true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        C2757gp gpVar = this.W;
        Objects.requireNonNull(gpVar);
        boolean z = false;
        int i = 0;
        z = false;
        z = false;
        z = false;
        z = false;
        z = false;
        if (keyEvent.getAction() != 1) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                int i2 = 66;
                if (keyCode != 66) {
                    switch (keyCode) {
                        case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                        case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                        case Version.VERSION_21 /*{ENCODED_INT: 21}*/:
                        case Version.VERSION_22 /*{ENCODED_INT: 22}*/:
                            if (keyEvent.hasNoModifiers()) {
                                if (keyCode == 19) {
                                    i2 = 33;
                                } else if (keyCode == 21) {
                                    i2 = 17;
                                } else if (keyCode != 22) {
                                    i2 = 130;
                                }
                                int repeatCount = keyEvent.getRepeatCount() + 1;
                                boolean z2 = false;
                                while (i < repeatCount && gpVar.q(i2, null)) {
                                    i++;
                                    z2 = true;
                                }
                                z = z2;
                                break;
                            }
                            break;
                    }
                }
                if (keyEvent.hasNoModifiers() && keyEvent.getRepeatCount() == 0) {
                    int i3 = gpVar.o;
                    if (i3 != Integer.MIN_VALUE) {
                        gpVar.s(i3, 16, null);
                    }
                    z = true;
                }
            } else if (keyEvent.hasNoModifiers()) {
                z = gpVar.q(2, null);
            } else if (keyEvent.hasModifiers(1)) {
                z = gpVar.q(1, null);
            }
        }
        if (!z || this.W.o == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [boolean, int] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // defpackage.N7
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawableStateChanged() {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.drawableStateChanged():void");
    }

    public final Rect e() {
        RectF d = d();
        this.a0.set((int) d.left, (int) d.top, (int) d.right, (int) d.bottom);
        return this.a0;
    }

    public final boolean f() {
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            Drawable drawable = ipVar.t0;
            if ((drawable != null ? VI.d(drawable) : null) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean g() {
        C3098ip ipVar = this.L;
        return ipVar != null && ipVar.x0;
    }

    public TextUtils.TruncateAt getEllipsize() {
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            return ipVar.h1;
        }
        return null;
    }

    public void getFocusedRect(Rect rect) {
        C2757gp gpVar = this.W;
        if (gpVar.o == 1 || gpVar.n == 1) {
            rect.set(e());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public final void h() {
        if (this.M != null) {
            this.M = null;
            setMinWidth(0);
            C3098ip ipVar = this.L;
            setMinHeight((int) (ipVar != null ? ipVar.h0 : 0.0f));
            j();
        }
    }

    public final void i(boolean z) {
        if (this.Q != z) {
            this.Q = z;
            refreshDrawableState();
        }
    }

    public final void j() {
        int[] iArr = AbstractC2004cN0.f9604a;
        this.N = new RippleDrawable(AbstractC2004cN0.b(this.L.l0), c(), null);
        this.L.K(false);
        RippleDrawable rippleDrawable = this.N;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setBackground(rippleDrawable);
        k();
    }

    public final void k() {
        C3098ip ipVar;
        if (!TextUtils.isEmpty(getText()) && (ipVar = this.L) != null) {
            int y = (int) (ipVar.y() + ipVar.I0 + ipVar.F0);
            C3098ip ipVar2 = this.L;
            int v = (int) (ipVar2.v() + ipVar2.B0 + ipVar2.E0);
            if (this.M != null) {
                Rect rect = new Rect();
                this.M.getPadding(rect);
                v += rect.left;
                y += rect.right;
            }
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            setPaddingRelative(v, paddingTop, y, paddingBottom);
        }
    }

    public final void l() {
        TextPaint paint = getPaint();
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            paint.drawableState = ipVar.getState();
        }
        C3098ip ipVar2 = this.L;
        C0870Of1 of1 = ipVar2 != null ? ipVar2.P0.f : null;
        if (of1 != null) {
            of1.c(getContext(), paint, this.c0);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AbstractC3405kd0.c(this, this.L);
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, f9689J);
        }
        if (g()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, K);
        }
        return onCreateDrawableState;
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        C2757gp gpVar = this.W;
        int i2 = gpVar.o;
        if (i2 != Integer.MIN_VALUE) {
            gpVar.k(i2);
        }
        if (z) {
            gpVar.q(i, rect);
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            boolean contains = d().contains(motionEvent.getX(), motionEvent.getY());
            if (this.R != contains) {
                this.R = contains;
                refreshDrawableState();
            }
        } else if (actionMasked == 10 && this.R) {
            this.R = false;
            refreshDrawableState();
        }
        return super.onHoverEvent(motionEvent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (g() || isClickable()) {
            accessibilityNodeInfo.setClassName(g() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            accessibilityNodeInfo.setClassName("android.view.View");
        }
        accessibilityNodeInfo.setCheckable(g());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            int i2 = -1;
            if (chipGroup.H) {
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i3 >= chipGroup.getChildCount()) {
                        i4 = -1;
                        break;
                    }
                    if (chipGroup.getChildAt(i3) instanceof Chip) {
                        if (((Chip) chipGroup.getChildAt(i3)) == this) {
                            break;
                        }
                        i4++;
                    }
                    i3++;
                }
                i = i4;
            } else {
                i = -1;
            }
            Object tag = getTag(R.id.row_index_key);
            if (tag instanceof Integer) {
                i2 = ((Integer) tag).intValue();
            }
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) C.a(i2, 1, i, 1, false, isChecked()).f7778a);
        }
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (!d().contains(motionEvent.getX(), motionEvent.getY()) || !isEnabled()) {
            return null;
        }
        return PointerIcon.getSystemIcon(getContext(), 1002);
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.U != i) {
            this.U = i;
            k();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        if (r0 != 3) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.d()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x003e
            if (r0 == r3) goto L_0x002b
            r4 = 2
            if (r0 == r4) goto L_0x0021
            r1 = 3
            if (r0 == r1) goto L_0x0039
            goto L_0x0045
        L_0x0021:
            boolean r0 = r5.Q
            if (r0 == 0) goto L_0x0045
            if (r1 != 0) goto L_0x0043
            r5.i(r2)
            goto L_0x0043
        L_0x002b:
            boolean r0 = r5.Q
            if (r0 == 0) goto L_0x0039
            r5.playSoundEffect(r2)
            gp r0 = r5.W
            r0.y(r3, r3)
            r0 = r3
            goto L_0x003a
        L_0x0039:
            r0 = r2
        L_0x003a:
            r5.i(r2)
            goto L_0x0046
        L_0x003e:
            if (r1 == 0) goto L_0x0045
            r5.i(r3)
        L_0x0043:
            r0 = r3
            goto L_0x0046
        L_0x0045:
            r0 = r2
        L_0x0046:
            if (r0 != 0) goto L_0x004e
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L_0x004f
        L_0x004e:
            r2 = r3
        L_0x004f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackground(Drawable drawable) {
        if (drawable == c() || drawable == this.N) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    public void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // defpackage.N7
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == c() || drawable == this.N) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // defpackage.N7
    public void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        C3098ip ipVar = this.L;
        if (ipVar == null) {
            this.P = z;
        } else if (ipVar.x0) {
            boolean isChecked = isChecked();
            super.setChecked(z);
            if (isChecked != z && (onCheckedChangeListener = this.O) != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public void setElevation(float f) {
        super.setElevation(f);
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            C3064id0 id0 = ipVar.H;
            if (id0.o != f) {
                id0.o = f;
                ipVar.s();
            }
        }
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.L != null) {
            if (truncateAt != TextUtils.TruncateAt.MARQUEE) {
                super.setEllipsize(truncateAt);
                C3098ip ipVar = this.L;
                if (ipVar != null) {
                    ipVar.h1 = truncateAt;
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
    }

    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setLayoutDirection(int i) {
        if (this.L != null) {
            super.setLayoutDirection(i);
        }
    }

    public void setLines(int i) {
        if (i <= 1) {
            super.setLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMaxLines(int i) {
        if (i <= 1) {
            super.setMaxLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            ipVar.j1 = i;
        }
    }

    public void setMinLines(int i) {
        if (i <= 1) {
            super.setMinLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setSingleLine(boolean z) {
        if (z) {
            super.setSingleLine(z);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            if (charSequence == null) {
                charSequence = "";
            }
            super.setText(ipVar.i1 ? null : charSequence, bufferType);
            C3098ip ipVar2 = this.L;
            if (ipVar2 != null) {
                ipVar2.I(charSequence);
            }
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            ipVar.J(new C0870Of1(ipVar.J0, i));
        }
        l();
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
    }

    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        C3098ip ipVar = this.L;
        if (ipVar != null) {
            ipVar.J(new C0870Of1(ipVar.J0, i));
        }
        l();
    }
}
