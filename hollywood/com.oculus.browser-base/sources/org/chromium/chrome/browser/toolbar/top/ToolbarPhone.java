package org.chromium.chrome.browser.toolbar.top;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Property;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.ui.base.LocalizationUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ToolbarPhone extends Wj1 implements View.OnClickListener, AbstractC5710y61 {
    public static final /* synthetic */ int U = 0;
    public boolean A0;
    public int B0;
    public int C0;
    public boolean D0;
    public int E0 = 255;
    public float F0 = -1.0f;
    public ColorDrawable G0;
    public Drawable H0;
    public Drawable I0;
    public boolean J0;
    public final Rect K0 = new Rect();
    public final Rect L0 = new Rect();
    public final Rect M0 = new Rect();
    public float N0;
    public float O0;
    public final Rect P0 = new Rect();
    public final Point Q0 = new Point();
    public final int R0 = getResources().getDimensionPixelOffset(R.dimen.f26320_resource_name_obfuscated_RES_2131166251);
    public ValueAnimator S0;
    public boolean T0;
    public boolean U0;
    public C5880z61 V;
    public Runnable V0;
    public C3909na0 W;
    public int W0;
    public int X0;
    public boolean Y0;
    public int Z0 = 0;
    public ViewGroup a0;
    public float a1 = 1.0f;
    public ToggleTabStackButton b0;
    public int b1;
    public HomeButton c0;
    public boolean c1;
    public TextView d0;
    public AnimatorSet d1;
    public View e0;
    public boolean e1;
    public ImageView f0;
    public int f1;
    public ImageButton g0;
    public int g1;
    public boolean h0;
    public float h1;
    public ObjectAnimator i0;
    public ViewTreeObserver.OnGlobalLayoutListener i1;
    public ObjectAnimator j0;
    public final Property j1 = new C2064cl1(this, Float.class, "");
    @ViewDebug.ExportedProperty(category = "chrome")
    public int k0;
    public final Property k1 = new C2405el1(this, Float.class, "");
    @ViewDebug.ExportedProperty(category = "chrome")
    public boolean l0;
    public final Property l1 = new C2576fl1(this, Float.class, "");
    public boolean m0;
    public boolean n0;
    public int o0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public boolean p0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public boolean q0;
    public C1410Xc1 r0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public float s0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public Rect t0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public boolean u0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public float v0;
    @ViewDebug.ExportedProperty(category = "chrome")
    public float w0;
    public AnimatorSet x0;
    public boolean y0;
    public boolean z0;

    public ToolbarPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static boolean m0(int i) {
        return i == 0 || i == 2;
    }

    @Override // defpackage.Wj1
    public void A() {
        u0();
        D0();
    }

    public final void A0() {
        HomeButton homeButton;
        int i = 0;
        if (!(this.k0 != 0)) {
            if (this.w0 == 1.0f) {
                i = 4;
            }
            this.a0.setVisibility(i);
            if (!(this.f9169J.g() || (homeButton = this.c0) == null || homeButton.getVisibility() == 8)) {
                this.c0.setVisibility(i);
            }
            s0();
        }
    }

    @Override // defpackage.Wj1
    public void B() {
        u0();
        D0();
    }

    public final void B0() {
        this.w0 = Math.max(this.F0, this.v0);
        Iterator it = this.G.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                P11 p11 = (P11) ((Ul1) uq0.next());
                p11.Z = this.w0;
                if (p11.a0) {
                    p11.i();
                }
            } else {
                return;
            }
        }
    }

    @Override // defpackage.Wj1
    public void C() {
        Sj1 sj1;
        float f = 1.0f;
        setAlpha(1.0f);
        this.t0 = null;
        if (this.k0 == 3) {
            this.W.i(true);
            this.k0 = 0;
            D0();
        }
        if (this.k0 == 2) {
            this.k0 = 1;
        }
        requestLayout();
        if (this.k0 == 0) {
            f = 0.0f;
        }
        this.s0 = f;
        if (!this.p0) {
            e();
            D0();
        }
        if (this.q0) {
            this.q0 = false;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, (float) (-getHeight()), 0.0f);
            ofFloat.setDuration(100L);
            ofFloat.setInterpolator(animation.InterpolatorC5286vf.e);
            ofFloat.addListener(new C3259jl1(this));
            this.j0 = ofFloat;
            ofFloat.start();
        } else {
            C0();
        }
        if (this.f9169J.f() && this.k0 == 0 && (sj1 = this.f9169J) != null && sj1.h() != null) {
            this.d0.setText(this.f9169J.h().e);
        }
    }

    public final void C0() {
        int i = 0;
        setVisibility(this.k0 == 0 ? 0 : 4);
        C4456ql1 ql1 = this.L;
        if (this.k0 != 0) {
            i = 4;
        }
        ql1.setVisibility(i);
        w0();
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            if (this.k0 == 2) {
                toggleTabStackButton.setBackgroundColor(getResources().getColor(17170445));
                return;
            }
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(R.style.f73990_resource_name_obfuscated_RES_2132017972, typedValue, true);
            this.b0.setBackgroundResource(typedValue.resourceId);
        }
    }

    @Override // defpackage.Wj1
    public void D(boolean z) {
        this.N = z;
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setClickable(!z);
        }
        if (this.f9169J.g()) {
            this.d0.setText("");
            if (!z) {
                return;
            }
        }
        p0(z, z);
    }

    public final void D0() {
        int i;
        int i2;
        Runnable runnable;
        TraceEvent.Y("ToolbarPhone.updateVisualsForLocationBarState", null);
        int i3 = this.k0;
        boolean z = i3 == 0 || i3 == 3;
        if (k0()) {
            i = 3;
        } else if (p()) {
            i = 1;
        } else {
            i = this.f9169J.j() ? 2 : 0;
        }
        if (!this.T0 || !m0(this.Z0) || !m0(i)) {
            ValueAnimator valueAnimator = this.S0;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.S0.end();
            }
            boolean z2 = this.Z0 != i;
            int c = this.f9169J.c();
            int c2 = this.f9169J.c();
            Sj1 sj1 = this.f9169J;
            if (!(sj1 == null || sj1.d() == null || !this.f9169J.d().isNativePage())) {
                c2 = f0(p() ? 1 : 0);
            }
            boolean z3 = z2;
            z3 = z2;
            if (this.Z0 == 2 && !z2) {
                if ((!AbstractC1270Uv.h(c)) != this.D0) {
                    z3 = true;
                } else {
                    y0(2);
                    this.L.i(c2, p());
                    z3 = z2;
                }
            }
            this.Z0 = i;
            if ((i == 2 || z3) && (runnable = this.V0) != null) {
                runnable.run();
            }
            w0();
            A0();
            if (this.k0 != 3) {
                y0(this.Z0);
            }
            if (!z3) {
                if (this.Z0 == 3) {
                    v0();
                } else {
                    o0();
                }
                TraceEvent.f0("ToolbarPhone.updateVisualsForLocationBarState");
                return;
            }
            this.D0 = false;
            int i4 = 255;
            this.E0 = 255;
            this.L.i(c2, p());
            if (p()) {
                this.E0 = 51;
            } else if (this.Z0 == 2) {
                boolean z4 = !AbstractC1270Uv.h(c);
                this.D0 = z4;
                if (z4) {
                    i4 = 51;
                }
                this.E0 = i4;
            }
            t0(d0(c));
            this.W.H();
            if (k0() && z) {
                v0();
            }
            this.R.e(true);
            Drawable drawable = this.H0;
            if (p()) {
                i2 = -1;
            } else {
                i2 = getResources().getColor(R.color.f15380_resource_name_obfuscated_RES_2131100228);
            }
            drawable.setTint(i2);
            TraceEvent.f0("ToolbarPhone.updateVisualsForLocationBarState");
            return;
        }
        TraceEvent.f0("ToolbarPhone.updateVisualsForLocationBarState");
    }

    @Override // defpackage.Wj1
    public void G(boolean z) {
        D0();
    }

    @Override // defpackage.Wj1
    public boolean I(boolean z) {
        boolean z2 = false;
        if (z) {
            boolean z3 = this.n0;
            AbstractC1117Sg1 sg1 = this.Q;
            boolean z4 = z3 != (sg1 != null && sg1.d());
            this.m0 = z4;
            C1410Xc1 xc1 = this.r0;
            if (!(xc1 == null || this.b0 == null)) {
                if (z4 || this.o0 != xc1.f) {
                    z2 = true;
                }
                this.m0 = z2;
            }
            return this.m0;
        }
        this.m0 = z;
        return false;
    }

    @Override // defpackage.Wj1
    public void J(Runnable runnable) {
        this.V0 = runnable;
    }

    @Override // defpackage.Wj1
    public void K(C3909na0 na0) {
        this.W = na0;
        Resources resources = getResources();
        this.W0 = resources.getDimensionPixelSize(R.dimen.f20540_resource_name_obfuscated_RES_2131165673);
        Resources resources2 = getResources();
        Drawable c = AbstractC3153j7.c(resources2, R.drawable.f33780_resource_name_obfuscated_RES_2131231418);
        c.mutate();
        c.setColorFilter(resources2.getColor(R.color.f15380_resource_name_obfuscated_RES_2131100228), PorterDuff.Mode.SRC_IN);
        this.H0 = c;
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.f20460_resource_name_obfuscated_RES_2131165665);
        ((C4080oa0) this.W.G).F.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.I0 = this.H0;
    }

    @Override // defpackage.Wj1
    public void L(View.OnClickListener onClickListener) {
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.S = onClickListener;
        }
    }

    @Override // defpackage.Wj1
    public void M(View.OnLongClickListener onLongClickListener) {
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.T = onLongClickListener;
        }
    }

    @Override // defpackage.Wj1
    public void N(C5880z61 z61) {
        this.V = z61;
        z61.f11721a.b(this);
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.R = z61;
            z61.a(toggleTabStackButton);
        }
    }

    @Override // defpackage.Wj1
    public void P(boolean z, boolean z2, boolean z3, C5976zi0 zi0) {
        boolean z4;
        int i;
        int i2;
        if (!this.f9169J.f()) {
            z4 = false;
        } else {
            ToggleTabStackButton toggleTabStackButton = this.b0;
            if (toggleTabStackButton != null) {
                toggleTabStackButton.setVisibility(z ? 8 : 0);
            }
            this.R.e(!z);
            if (this.M) {
                p0(z && !this.N, false);
            } else {
                this.Y0 = true;
            }
            if (z) {
                this.d0.setText("");
            }
            z4 = true;
        }
        if (!z4) {
            int i3 = 2;
            if (z && ((i2 = this.k0) == 1 || i2 == 2)) {
                return;
            }
            if (z || !((i = this.k0) == 0 || i == 3)) {
                if (!z) {
                    i3 = 3;
                }
                this.k0 = i3;
                requestLayout();
                this.W.i(false);
                e();
                this.q0 = z3;
                long j = 200;
                if (z) {
                    AnimatorSet animatorSet = this.x0;
                    if (animatorSet != null && animatorSet.isRunning()) {
                        this.x0.end();
                        this.x0 = null;
                        n0(getMeasuredWidth());
                        A0();
                    }
                    C0();
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.k1, 1.0f);
                    ofFloat.setDuration(200L);
                    ofFloat.setInterpolator(G30.d);
                    this.i0 = ofFloat;
                } else if (!z3) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, this.k1, 0.0f);
                    if (!z2) {
                        j = 100;
                    }
                    ofFloat2.setDuration(j);
                    ofFloat2.setInterpolator(G30.d);
                    ofFloat2.addListener(new C3088il1(this));
                    this.i0 = ofFloat2;
                }
                q0();
                this.p0 = z2;
                ObjectAnimator objectAnimator = this.i0;
                if (objectAnimator != null) {
                    objectAnimator.start();
                }
                if (C5052uE.a()) {
                    e();
                }
                postInvalidateOnAnimation();
            }
        }
    }

    @Override // defpackage.Wj1
    public void Q(boolean z) {
        this.l0 = z;
        if (z) {
            if (!i0() && !j0()) {
                if (!(!p() && AbstractC5154ur1.g(this.f9169J.i()) && this.F0 < 1.0f)) {
                    this.f0.setVisibility(0);
                }
            }
            this.a1 = getAlpha();
            this.b1 = getVisibility();
            setAlpha(1.0f);
            setVisibility(0);
            return;
        }
        setAlpha(this.a1);
        setVisibility(this.b1);
        w0();
        this.a1 = 1.0f;
    }

    @Override // defpackage.Wj1
    public boolean R() {
        return super.R() || this.w0 > 0.0f || ((float) this.Q0.y) < 0.0f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031  */
    @Override // defpackage.Wj1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void U() {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.ToolbarPhone.U():void");
    }

    @Override // defpackage.Wj1
    public void W(C0517Ik ik) {
        if (this.g0 == null) {
            this.g0 = (ImageButton) ((ViewStub) findViewById(R.id.optional_button_stub)).inflate();
            this.f1 = getResources().getDimensionPixelSize(R.dimen.f26370_resource_name_obfuscated_RES_2131166256);
            if (getLayoutDirection() == 1) {
                this.f1 *= -1;
            }
        } else if (this.e1) {
            this.d1.end();
        }
        this.g0.setOnClickListener(ik.c);
        this.g0.setImageDrawable(ik.b);
        this.g0.setContentDescription(getContext().getResources().getString(ik.d));
        this.g0.setEnabled(ik.f);
        boolean z = ik.e;
        this.h0 = z;
        if (z) {
            this.g0.setImageTintList(l());
        } else {
            this.g0.setImageTintList(null);
        }
        this.i1 = new Wk1(this);
        if (this.k0 != 0) {
            this.g0.setVisibility(0);
            getViewTreeObserver().addOnGlobalLayoutListener(this.i1);
        } else if (this.u0 || this.N || this.g0.getVisibility() != 8) {
            this.g0.setVisibility(0);
            this.d1 = null;
            this.g0.setAlpha(1.0f);
            this.g0.setTranslationX(0.0f);
        } else {
            if (this.e1) {
                this.d1.end();
            }
            ArrayList arrayList = new ArrayList();
            this.h1 = 1.0f;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.l1, 0.0f);
            ofFloat.setDuration(225L);
            animation.InterpolatorC5286vf vfVar = animation.InterpolatorC5286vf.e;
            ofFloat.setInterpolator(vfVar);
            arrayList.add(ofFloat);
            this.g0.setAlpha(0.0f);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.g0, View.ALPHA, 1.0f);
            ofFloat2.setInterpolator(vfVar);
            ofFloat2.setStartDelay(125);
            ofFloat2.setDuration(100L);
            arrayList.add(ofFloat2);
            this.g0.setTranslationX((float) this.f1);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.g0, View.TRANSLATION_X, 0.0f);
            ofFloat3.setInterpolator(vfVar);
            ofFloat3.setStartDelay(125);
            ofFloat3.setDuration(100L);
            arrayList.add(ofFloat3);
            AnimatorSet animatorSet = new AnimatorSet();
            this.d1 = animatorSet;
            animatorSet.addListener(new C1713al1(this));
            this.d1.playTogether(arrayList);
            this.d1.start();
        }
    }

    public final boolean Y(Canvas canvas, long j) {
        TraceEvent.Y("ToolbarPhone.drawLocationBar", null);
        boolean z = false;
        if (this.H0 != null && (this.k0 == 0 || this.l0)) {
            canvas.save();
            if ((((C4080oa0) this.W.G).f() > 0.0f || this.J0) && !this.l0) {
                z = true;
            }
            if (z) {
                Drawable drawable = this.I0;
                if (drawable instanceof C3601ll1) {
                    ((C3601ll1) drawable).L = true;
                }
                Rect rect = this.K0;
                int i = rect.left;
                Rect rect2 = this.M0;
                drawable.setBounds(i + rect2.left, rect.top + rect2.top, rect.right + rect2.right, rect.bottom + rect2.bottom);
                this.I0.draw(canvas);
            }
            Rect rect3 = this.K0;
            int i2 = rect3.left;
            Rect rect4 = this.M0;
            float f = (float) (i2 + rect4.left);
            float f2 = (float) (rect3.right + rect4.right);
            float f3 = (float) (rect3.top + rect4.top);
            float f4 = (float) (rect3.bottom + rect4.bottom);
            int paddingStart = ((C4080oa0) this.W.G).F.getPaddingStart();
            int paddingEnd = ((C4080oa0) this.W.G).F.getPaddingEnd();
            int g = ((C4080oa0) this.W.G).g();
            if (this.w0 != 1.0f && !this.e1) {
                int g02 = this.C0 - g0(this.Z0);
                int h02 = (h0(this.Z0) - this.C0) - this.B0;
                float f5 = 1.0f - this.w0;
                f += ((float) g02) * f5;
                f2 -= ((float) h02) * f5;
                if (g == 1) {
                    f += ((float) paddingStart) * f5;
                } else {
                    f2 -= ((float) paddingEnd) * f5;
                }
            }
            if (this.e1) {
                if (g == 1) {
                    f += (float) paddingStart;
                } else {
                    f2 -= (float) paddingEnd;
                }
            }
            if (AbstractC5762yQ0.g(p()) && k0() && this.N && this.u0) {
                float f6 = (float) paddingStart;
                if (g == 1) {
                    f2 -= f6;
                } else {
                    f += f6;
                }
            }
            canvas.clipRect(f, f3, f2, f4);
            z = true;
        }
        boolean drawChild = super.drawChild(canvas, ((C4080oa0) this.W.G).F, j);
        if (z) {
            canvas.restore();
        }
        TraceEvent.f0("ToolbarPhone.drawLocationBar");
        return drawChild;
    }

    public void Z(Canvas canvas, float f) {
        BitmapDrawable bitmapDrawable;
        if (this.M) {
            float f2 = 1.0f - f;
            int i = (int) (255.0f * f2);
            canvas.save();
            canvas.translate(0.0f, (-f) * ((float) this.L0.height()));
            canvas.clipRect(this.L0);
            HomeButton homeButton = this.c0;
            if (!(homeButton == null || homeButton.getVisibility() == 8)) {
                float alpha = this.c0.getAlpha();
                this.c0.setAlpha(alpha * f2);
                drawChild(canvas, this.c0, SystemClock.uptimeMillis());
                this.c0.setAlpha(alpha);
            }
            float f3 = ((C4080oa0) this.W.G).f();
            ((C4080oa0) this.W.G).F.setAlpha(f2 * f3);
            boolean z = false;
            if (((C4080oa0) this.W.G).f() != 0.0f) {
                if (!k0() || this.w0 > 0.0f) {
                    Y(canvas, SystemClock.uptimeMillis());
                }
            }
            ((C4080oa0) this.W.G).F.setAlpha(f3);
            AbstractC4656rv1.h(this, this.a0, canvas);
            ImageButton imageButton = this.g0;
            if (!(imageButton == null || imageButton.getVisibility() == 8)) {
                canvas.save();
                Drawable drawable = this.g0.getDrawable();
                AbstractC4656rv1.h(this.a0, this.g0, canvas);
                canvas.translate((float) (this.g0.getPaddingLeft() + ((((this.g0.getWidth() - this.g0.getPaddingLeft()) - this.g0.getPaddingRight()) - this.g0.getDrawable().getIntrinsicWidth()) / 2)), (float) (this.g0.getPaddingTop() + ((((this.g0.getHeight() - this.g0.getPaddingTop()) - this.g0.getPaddingBottom()) - this.g0.getDrawable().getIntrinsicHeight()) / 2)));
                drawable.setAlpha(i);
                drawable.draw(canvas);
                canvas.restore();
            }
            if (!(this.r0 == null || this.b0 == null || this.w0 == 1.0f)) {
                canvas.save();
                AbstractC4656rv1.h(this.a0, this.b0, canvas);
                canvas.translate((float) (this.b0.getPaddingLeft() + ((((this.b0.getWidth() - this.b0.getPaddingLeft()) - this.b0.getPaddingRight()) - this.b0.getDrawable().getIntrinsicWidth()) / 2)), (float) (this.b0.getPaddingTop() + ((((this.b0.getHeight() - this.b0.getPaddingTop()) - this.b0.getPaddingBottom()) - this.b0.getDrawable().getIntrinsicHeight()) / 2)));
                this.r0.setBounds(this.b0.getDrawable().getBounds());
                this.r0.setAlpha(i);
                this.r0.draw(canvas);
                canvas.restore();
            }
            C5976zi0 zi0 = this.R;
            if (zi0 != null) {
                ViewGroup viewGroup = this.a0;
                canvas.save();
                AbstractC4656rv1.h(viewGroup, zi0.e, canvas);
                MenuButton menuButton = zi0.e;
                if (menuButton.P == null && menuButton.O == null) {
                    menuButton.b();
                }
                ImageView imageView = menuButton.G;
                if (imageView != null && imageView.getVisibility() == 0) {
                    bitmapDrawable = menuButton.P;
                } else {
                    bitmapDrawable = menuButton.O;
                }
                bitmapDrawable.setAlpha(i);
                bitmapDrawable.draw(canvas);
                canvas.restore();
            }
            AbstractC1117Sg1 sg1 = this.Q;
            if (sg1 != null && sg1.d()) {
                z = true;
            }
            this.n0 = z;
            C1410Xc1 xc1 = this.r0;
            if (!(xc1 == null || this.b0 == null)) {
                this.o0 = xc1.f;
            }
            canvas.restore();
        }
    }

    public final int a0() {
        return getResources().getDimensionPixelSize(R.dimen.f24900_resource_name_obfuscated_RES_2131166109) - getResources().getDimensionPixelSize(R.dimen.f24860_resource_name_obfuscated_RES_2131166105);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    @Override // defpackage.AbstractC5710y61
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r6, boolean r7) {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.ToolbarPhone.b(int, boolean):void");
    }

    public final int b0() {
        int i = this.R0;
        HomeButton homeButton = this.c0;
        return (homeButton == null || homeButton.getVisibility() == 8) ? i : this.c0.getMeasuredWidth();
    }

    @Override // defpackage.AbstractC1056Rg1, defpackage.Wj1
    public void c(ColorStateList colorStateList, boolean z) {
        HomeButton homeButton = this.c0;
        if (homeButton != null) {
            homeButton.setImageTintList(colorStateList);
        }
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setImageDrawable(z ? toggleTabStackButton.Q : toggleTabStackButton.P);
            C1410Xc1 xc1 = this.r0;
            if (xc1 != null) {
                xc1.c(colorStateList);
            }
        }
        ImageButton imageButton = this.g0;
        if (imageButton != null && this.h0) {
            imageButton.setImageTintList(colorStateList);
        }
        C3909na0 na0 = this.W;
        if (na0 != null) {
            na0.H();
        }
        Runnable runnable = this.V0;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final float c0(int i) {
        if ((i != 3 || this.k0 != 0) && !this.f9169J.g()) {
            return this.w0;
        }
        return 1.0f;
    }

    @Override // defpackage.Wj1
    public void d() {
        AnimatorSet animatorSet = this.x0;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.x0.cancel();
        }
        ValueAnimator valueAnimator = this.S0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.S0.cancel();
        }
        AnimatorSet animatorSet2 = this.d1;
        if (animatorSet2 != null && animatorSet2.isRunning()) {
            this.d1.cancel();
        }
        super.d();
    }

    public final int d0(int i) {
        return Pj1.c(getResources(), i, p());
    }

    public void dispatchDraw(Canvas canvas) {
        boolean z = false;
        if (!this.l0 && this.G0.getColor() != 0) {
            this.G0.setBounds(0, 0, getWidth(), getHeight());
            this.G0.draw(canvas);
        }
        if (this.H0 != null && (((C4080oa0) this.W.G).F.getVisibility() == 0 || this.l0)) {
            r0(this.K0, this.Z0);
        }
        if (this.l0) {
            Z(canvas, 0.0f);
            return;
        }
        ObjectAnimator objectAnimator = this.i0;
        if (objectAnimator != null) {
            boolean z2 = !objectAnimator.isRunning();
            if (!this.p0) {
                float f = this.s0;
                setAlpha(f);
                if (z2) {
                    this.t0 = null;
                } else if (this.t0 == null) {
                    this.t0 = new Rect();
                }
                Rect rect = this.t0;
                if (rect != null) {
                    rect.set(0, 0, getWidth(), (int) (((float) getHeight()) * f));
                }
            }
            z = z2;
        }
        super.dispatchDraw(canvas);
        if (this.i0 != null) {
            if (this.p0) {
                Z(canvas, this.s0);
            }
            if (z) {
                this.i0 = null;
            }
        }
    }

    @Override // defpackage.Wj1
    public void draw(Canvas canvas) {
        boolean z = !this.l0 && this.t0 != null;
        if (z) {
            canvas.save();
            canvas.clipRect(this.t0);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
            if (this.t0 == null) {
                postInvalidate();
            }
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        int i;
        C3909na0 na0 = this.W;
        if (na0 != null && view == ((C4080oa0) na0.G).F) {
            return Y(canvas, j);
        }
        boolean z2 = true;
        int i2 = 0;
        if (this.H0 != null) {
            canvas.save();
            int translationY = (int) ((C4080oa0) this.W.G).F.getTranslationY();
            int i3 = this.K0.top + translationY;
            if (this.w0 != 0.0f && i3 < view.getBottom()) {
                HomeButton homeButton = this.c0;
                boolean isLayoutRtl = (homeButton != null && view == homeButton) ^ LocalizationUtils.isLayoutRtl();
                int i4 = this.K0.bottom + translationY;
                if (((float) translationY) > 0.0f) {
                    i3 = view.getTop();
                    z = true;
                    i4 = i3;
                } else {
                    z = false;
                }
                if (isLayoutRtl) {
                    if (z) {
                        i = view.getMeasuredWidth();
                    } else {
                        i = this.K0.left;
                    }
                    canvas.clipRect(0, i3, i, i4);
                } else {
                    if (!z) {
                        i2 = this.K0.right;
                    }
                    canvas.clipRect(i2, i3, getMeasuredWidth(), i4);
                }
            }
        } else {
            z2 = false;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        if (z2) {
            canvas.restore();
        }
        return drawChild;
    }

    @Override // defpackage.Wj1
    public void e() {
        this.t0 = null;
        ObjectAnimator objectAnimator = this.i0;
        if (objectAnimator != null) {
            objectAnimator.end();
            this.i0 = null;
        }
        ObjectAnimator objectAnimator2 = this.j0;
        if (objectAnimator2 != null) {
            objectAnimator2.end();
            this.j0 = null;
        }
        this.L.setVisibility(this.k0 != 0 ? 4 : 0);
    }

    public float e0() {
        float width = (float) this.g0.getWidth();
        return !l0() ? width - ((float) this.R0) : width;
    }

    public final int f0(int i) {
        Resources resources = getResources();
        if (i == 0) {
            return AbstractC2934hr.a(getResources(), false);
        }
        if (i == 1) {
            return AbstractC2934hr.a(getResources(), true);
        }
        if (i == 2) {
            return this.f9169J.c();
        }
        if (i != 3) {
            return resources.getColor(R.color.f15340_resource_name_obfuscated_RES_2131100224);
        }
        return AbstractC1331Vv.h(AbstractC2934hr.a(getResources(), false), Math.round(this.w0 * 255.0f));
    }

    @Override // defpackage.Wj1
    public HomeButton g() {
        return this.c0;
    }

    public final int g0(int i) {
        if (i == 3 && this.k0 == 0) {
            return this.R0;
        }
        if (getLayoutDirection() == 1) {
            return Math.max(this.R0, this.a0.getMeasuredWidth());
        }
        return b0();
    }

    @Override // defpackage.Wj1
    public AbstractC3225ja0 h() {
        return this.W;
    }

    public final int h0(int i) {
        int i2;
        int i3;
        if (i == 3 && this.k0 == 0) {
            i2 = getMeasuredWidth();
            i3 = this.R0;
        } else if (getLayoutDirection() != 1) {
            return getMeasuredWidth() - Math.max(this.R0, this.a0.getMeasuredWidth());
        } else {
            i2 = getMeasuredWidth();
            i3 = b0();
        }
        return i2 - i3;
    }

    @Override // defpackage.Wj1
    public void i(Rect rect) {
        r0(rect, 0);
    }

    public final boolean i0() {
        return p() && AbstractC5154ur1.g(this.f9169J.i());
    }

    @Override // defpackage.Wj1
    public View j() {
        return this.g0;
    }

    public final boolean j0() {
        Sj1 sj1 = this.f9169J;
        return (sj1 == null || sj1.d() == null || !this.f9169J.d().p()) ? false : true;
    }

    public final boolean k0() {
        return this.f9169J.e().h();
    }

    public final boolean l0() {
        MenuButton menuButton = this.R.e;
        return menuButton != null && menuButton.getVisibility() == 0;
    }

    @Override // defpackage.Wj1
    public void m(boolean z) {
        setVisibility(z ? 8 : this.k0 == 0 ? 0 : 4);
    }

    @Override // defpackage.Wj1
    public void n() {
        ImageButton imageButton = this.g0;
        if (imageButton != null && imageButton.getVisibility() != 8 && !this.A0) {
            boolean i = this.f9169J.e().i();
            if (this.k0 != 0 || this.u0 || this.N || i) {
                this.g0.setVisibility(8);
                getViewTreeObserver().addOnGlobalLayoutListener(this.i1);
                return;
            }
            if (this.e1) {
                this.d1.end();
            }
            ArrayList arrayList = new ArrayList();
            this.h1 = 0.0f;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.l1, 1.0f);
            ofFloat.setDuration(225L);
            ofFloat.setInterpolator(animation.InterpolatorC5286vf.e);
            arrayList.add(ofFloat);
            this.g0.setAlpha(1.0f);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.g0, View.ALPHA, 0.0f);
            animation.InterpolatorC5286vf vfVar = animation.InterpolatorC5286vf.f;
            ofFloat2.setInterpolator(vfVar);
            ofFloat2.setDuration(100L);
            arrayList.add(ofFloat2);
            this.g0.setTranslationX(0.0f);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.g0, View.TRANSLATION_X, (float) this.f1);
            ofFloat3.setInterpolator(vfVar);
            ofFloat3.setDuration(100L);
            arrayList.add(ofFloat3);
            AnimatorSet animatorSet = new AnimatorSet();
            this.d1 = animatorSet;
            animatorSet.addListener(new C1893bl1(this));
            this.d1.playTogether(arrayList);
            this.d1.start();
        }
    }

    public final boolean n0(int i) {
        int i2;
        int i3;
        View childAt;
        TraceEvent.Y("ToolbarPhone.layoutLocationBar", null);
        AbstractView$OnClickListenerC5272va0 va0 = this.W.F;
        Objects.requireNonNull(va0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) va0.getLayoutParams();
        layoutParams.gravity = 51;
        boolean z = false;
        boolean z02 = z0() | false;
        if (this.z0 || (this.Z0 == 3 && this.k0 == 0)) {
            C4080oa0 oa0 = (C4080oa0) this.W.G;
            int i4 = 0;
            int i5 = 0;
            while (i4 < oa0.F.getChildCount() && (childAt = oa0.F.getChildAt(i4)) != oa0.F.g0) {
                if (childAt.getVisibility() != 8) {
                    i5 += childAt.getMeasuredWidth();
                }
                i4++;
            }
            i3 = this.R0;
            i2 = (i - (i3 * 2)) + i5;
            if (((C4080oa0) this.W.G).g() != 1) {
                i3 -= i5;
            }
        } else {
            i2 = this.B0;
            i3 = this.C0;
        }
        if (this.A0) {
            float e02 = e0();
            if (getLayoutDirection() == 1) {
                i3 -= (int) e02;
            }
            i2 += (int) e02;
        }
        boolean z2 = z02 | (i2 != layoutParams.width);
        layoutParams.width = i2;
        if (i3 != layoutParams.leftMargin) {
            z = true;
        }
        boolean z3 = z2 | z;
        layoutParams.leftMargin = i3;
        if (z3) {
            s0();
        }
        TraceEvent.f0("ToolbarPhone.layoutLocationBar");
        return z3;
    }

    public final void o0() {
        this.M0.setEmpty();
        this.I0 = this.H0;
        this.Q0.set(0, 0);
        float f = 0.0f;
        ((C4080oa0) this.W.G).F.setTranslationY(0.0f);
        if (!this.u0) {
            this.a0.setTranslationY(0.0f);
            HomeButton homeButton = this.c0;
            if (homeButton != null) {
                homeButton.setTranslationY(0.0f);
            }
        }
        if (!this.u0) {
            ImageView imageView = this.f0;
            if (!this.d0.hasFocus()) {
                f = 1.0f;
            }
            imageView.setAlpha(f);
        }
        ((C4080oa0) this.W.G).F.setAlpha(1.0f);
        this.J0 = false;
        this.E0 = 255;
        if (p() || (this.D0 && !this.u0 && !((C4080oa0) this.W.G).F.hasFocus())) {
            this.E0 = 51;
        }
        if (k0()) {
            AbstractC4656rv1.f(this, true);
        }
        this.F0 = -1.0f;
        B0();
    }

    @Override // defpackage.Wj1
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f0 = (ImageView) getRootView().findViewById(R.id.toolbar_shadow);
        w0();
    }

    public void onClick(View view) {
        HomeButton homeButton;
        C3909na0 na0 = this.W;
        if ((na0 == null || !((C4080oa0) na0.G).F.hasFocus()) && (homeButton = this.c0) != null && homeButton == view) {
            E();
            if (this.M && PartnerBrowserCustomizations.c().e()) {
                Um1.a(this.f9169J.b()).notifyEvent("partner_home_page_button_pressed");
            }
        }
    }

    @Override // defpackage.Wj1
    public void onFinishInflate() {
        TraceEvent j02 = TraceEvent.j0("ToolbarPhone.onFinishInflate");
        try {
            super.onFinishInflate();
            this.a0 = (ViewGroup) findViewById(R.id.toolbar_buttons);
            this.c0 = (HomeButton) findViewById(R.id.home_button);
            this.d0 = (TextView) findViewById(R.id.url_bar);
            this.e0 = findViewById(R.id.url_action_container);
            this.G0 = new ColorDrawable(f0(0));
            setLayoutTransition(null);
            C5976zi0 zi0 = this.R;
            if (zi0 != null) {
                zi0.e(true);
            }
            ToggleTabStackButton toggleTabStackButton = (ToggleTabStackButton) findViewById(R.id.tab_switcher_button);
            this.b0 = toggleTabStackButton;
            toggleTabStackButton.setClickable(false);
            setWillNotDraw(false);
            this.g1 = getResources().getDimensionPixelSize(R.dimen.f26450_resource_name_obfuscated_RES_2131166264);
            if (j02 != null) {
                j02.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f = this.F0;
        if (f == 0.0f || f == 1.0f || f == -1.0f) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // defpackage.Wj1
    public void onMeasure(int i, int i2) {
        if (!this.y0) {
            super.onMeasure(i, i2);
            boolean n02 = n0(View.MeasureSpec.getSize(i));
            if (!(this.k0 != 0)) {
                A0();
            }
            if (!n02) {
                return;
            }
        } else {
            z0();
        }
        super.onMeasure(i, i2);
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.L0.set(0, 0, i, i2);
        super.onSizeChanged(i, i2, i3, i4);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.Q0.y >= 0 || ((C4080oa0) this.W.G).F.getTranslationY() <= 0.0f) {
            return super.onTouchEvent(motionEvent);
        }
        return this.f9169J.e().dispatchTouchEvent(motionEvent);
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        U();
    }

    public final void p0(boolean z, boolean z2) {
        boolean z3;
        ImageView imageView;
        char c;
        TraceEvent.Y("ToolbarPhone.triggerUrlFocusAnimation", null);
        AnimatorSet animatorSet = this.x0;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.x0.cancel();
            this.x0 = null;
        }
        if (this.e1) {
            this.d1.end();
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            TraceEvent.Y("ToolbarPhone.populateUrlFocusingAnimatorSet", null);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.j1, 1.0f);
            ofFloat.setDuration(225L);
            animation.InterpolatorC5286vf vfVar = animation.InterpolatorC5286vf.e;
            ofFloat.setInterpolator(vfVar);
            arrayList.add(ofFloat);
            z3 = true;
            ((C4080oa0) this.W.G).h(arrayList, 0, 225, 0.0f);
            float f = getContext().getResources().getDisplayMetrics().density;
            boolean z4 = getLayoutDirection() == 1;
            int i = this.g1;
            if (z4) {
                i = -i;
            }
            float f2 = ((float) i) * f;
            Animator b = this.R.b(true);
            b.setDuration(100);
            animation.InterpolatorC5286vf vfVar2 = animation.InterpolatorC5286vf.f;
            b.setInterpolator(vfVar2);
            arrayList.add(b);
            ToggleTabStackButton toggleTabStackButton = this.b0;
            if (toggleTabStackButton != null) {
                c = 0;
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(toggleTabStackButton, FrameLayout.TRANSLATION_X, f2);
                ofFloat2.setDuration(100L);
                ofFloat2.setInterpolator(vfVar2);
                arrayList.add(ofFloat2);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.b0, FrameLayout.ALPHA, 0.0f);
                ofFloat3.setDuration(100L);
                ofFloat3.setInterpolator(vfVar2);
                arrayList.add(ofFloat3);
            } else {
                c = 0;
            }
            ImageButton imageButton = this.g0;
            if (!(imageButton == null || imageButton.getVisibility() == 8)) {
                ImageButton imageButton2 = this.g0;
                Property property = FrameLayout.TRANSLATION_X;
                float[] fArr = new float[1];
                fArr[c] = f2;
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageButton2, property, fArr);
                ofFloat4.setDuration(100L);
                ofFloat4.setInterpolator(vfVar2);
                arrayList.add(ofFloat4);
                ImageButton imageButton3 = this.g0;
                Property property2 = FrameLayout.ALPHA;
                float[] fArr2 = new float[1];
                fArr2[c] = 0.0f;
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageButton3, property2, fArr2);
                ofFloat5.setDuration(100L);
                ofFloat5.setInterpolator(vfVar2);
                arrayList.add(ofFloat5);
            }
            ImageView imageView2 = this.f0;
            if (imageView2 != null) {
                Property property3 = FrameLayout.ALPHA;
                float[] fArr3 = new float[1];
                fArr3[c] = 0.0f;
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(imageView2, property3, fArr3);
                ofFloat6.setDuration(225L);
                ofFloat6.setInterpolator(vfVar);
                arrayList.add(ofFloat6);
            }
            TraceEvent.f0("ToolbarPhone.populateUrlFocusingAnimatorSet");
        } else {
            z3 = true;
            ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this, this.j1, 0.0f);
            ofFloat7.setDuration(225L);
            animation.InterpolatorC5286vf vfVar3 = animation.InterpolatorC5286vf.e;
            ofFloat7.setInterpolator(vfVar3);
            arrayList.add(ofFloat7);
            Animator b2 = this.R.b(false);
            b2.setDuration(100);
            b2.setInterpolator(animation.InterpolatorC5286vf.f);
            arrayList.add(b2);
            ToggleTabStackButton toggleTabStackButton2 = this.b0;
            if (toggleTabStackButton2 != null) {
                ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(toggleTabStackButton2, FrameLayout.TRANSLATION_X, 0.0f);
                ofFloat8.setDuration(100L);
                ofFloat8.setStartDelay(200);
                ofFloat8.setInterpolator(vfVar3);
                arrayList.add(ofFloat8);
                ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this.b0, FrameLayout.ALPHA, 1.0f);
                ofFloat9.setDuration(100L);
                ofFloat9.setStartDelay(200);
                ofFloat9.setInterpolator(vfVar3);
                arrayList.add(ofFloat9);
            }
            ImageButton imageButton4 = this.g0;
            if (!(imageButton4 == null || imageButton4.getVisibility() == 8)) {
                ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(this.g0, FrameLayout.TRANSLATION_X, 0.0f);
                ofFloat10.setDuration(100L);
                ofFloat10.setStartDelay(150);
                ofFloat10.setInterpolator(vfVar3);
                arrayList.add(ofFloat10);
                ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(this.g0, FrameLayout.ALPHA, 1.0f);
                ofFloat11.setDuration(100L);
                ofFloat11.setStartDelay(150);
                ofFloat11.setInterpolator(vfVar3);
                arrayList.add(ofFloat11);
            }
            ((C4080oa0) this.W.G).h(arrayList, 100, 250, 1.0f);
            if ((!k0() || this.F0 != 0.0f) && (imageView = this.f0) != null) {
                ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(imageView, FrameLayout.ALPHA, 1.0f);
                ofFloat12.setDuration(225L);
                ofFloat12.setInterpolator(vfVar3);
                arrayList.add(ofFloat12);
            }
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.x0 = animatorSet2;
        animatorSet2.playTogether(arrayList);
        this.u0 = z3;
        this.x0.addListener(new C3430kl1(this, z, z2));
        this.x0.start();
        TraceEvent.f0("ToolbarPhone.triggerUrlFocusAnimation");
    }

    @Override // defpackage.Wj1
    public boolean q() {
        if (this.m0) {
            return true;
        }
        if (this.N || this.u0) {
            return false;
        }
        return true;
    }

    public final void q0() {
        int i = 0;
        if (this.k0 == 0) {
            i = Math.min(this.Q0.y, 0);
        }
        float f = (float) i;
        this.a0.setTranslationY(f);
        HomeButton homeButton = this.c0;
        if (homeButton != null) {
            homeButton.setTranslationY(f);
        }
    }

    public final void r0(Rect rect, int i) {
        c0(i);
        int e = (int) AbstractC4089od0.e((float) g0(i), (float) this.R0, c0(i));
        if (this.e1 && getLayoutDirection() == 1) {
            e -= (int) (e0() * this.h1);
        }
        int e2 = (int) AbstractC4089od0.e((float) h0(i), (float) (getWidth() - this.R0), c0(i));
        if (this.e1 && getLayoutDirection() != 1) {
            e2 += (int) (e0() * this.h1);
        }
        rect.set(e, ((C4080oa0) this.W.G).F.getTop() + this.W0, e2, ((C4080oa0) this.W.G).F.getBottom() - this.W0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x013f, code lost:
        if (r11 != false) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0143, code lost:
        if (r11 != false) goto L_0x0141;
     */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0143  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void s0() {
        /*
        // Method dump skipped, instructions count: 458
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.ToolbarPhone.s0():void");
    }

    @Override // defpackage.Wj1
    public void t() {
        post(new Zk1(this));
    }

    public final void t0(int i) {
        if (this.X0 != i) {
            this.X0 = i;
            this.H0.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // defpackage.Wj1
    public void u(boolean z) {
        this.U0 = z;
        U();
    }

    public final void u0() {
        AbstractC1377Wn0 e = this.f9169J.e();
        boolean b = e.b();
        float f = this.F0;
        o0();
        e.e(new Vk1(this));
        if (e.h()) {
            if (Build.VERSION.SDK_INT >= 28) {
                C3601ll1 ll1 = new C3601ll1(getContext(), this);
                e.c(ll1);
                this.I0 = ll1;
            }
            requestLayout();
        } else if (b) {
            if (this.k0 == 0 && f > 0.0f) {
                this.v0 = Math.max(f, this.v0);
                p0(false, false);
            }
            requestLayout();
        }
    }

    public final void v0() {
        int i = this.k0;
        boolean z = true;
        if (i != 1 && i != 2) {
            float f = 0.0f;
            boolean z2 = this.w0 > 0.0f;
            boolean z3 = !z2;
            if (k0()) {
                AbstractC4656rv1.f(this, z3);
            }
            if (!this.u0) {
                if (!this.d0.hasFocus() && this.F0 == 1.0f) {
                    f = 1.0f;
                }
                this.f0.setAlpha(f);
            }
            AbstractC1377Wn0 e = this.f9169J.e();
            e.f(this.P0, this.Q0);
            int max = Math.max(0, this.P0.top - ((C4080oa0) this.W.G).F.getTop());
            ((C4080oa0) this.W.G).F.setTranslationY((float) max);
            q0();
            float interpolation = 1.0f - G30.c.getInterpolation(this.w0);
            Rect rect = this.P0;
            int i2 = rect.left;
            Rect rect2 = this.K0;
            int i3 = i2 - rect2.left;
            int i4 = rect.right - rect2.right;
            int dimensionPixelSize = (int) ((1.0f - this.w0) * ((float) getResources().getDimensionPixelSize(R.dimen.f23110_resource_name_obfuscated_RES_2131165930)));
            float f2 = ((float) i3) * interpolation;
            float f3 = ((float) i4) * interpolation;
            this.M0.set(Math.round(f2), max, Math.round(f3), max);
            this.M0.inset(0, dimensionPixelSize);
            this.N0 = f2;
            this.O0 = f3;
            int i5 = z2 ? 255 : 0;
            this.E0 = i5;
            if (i5 <= 0) {
                z = false;
            }
            this.J0 = z;
            float f4 = ((float) i5) / 255.0f;
            ((C4080oa0) this.W.G).F.setAlpha(f4);
            e.j(1.0f - f4);
            if (!this.J0) {
                Drawable drawable = this.I0;
                if (drawable instanceof C3601ll1) {
                    C3601ll1 ll1 = (C3601ll1) drawable;
                    ll1.setBounds(ll1.H, ll1.I, ll1.f10370J, ll1.K);
                }
            }
            y0(this.Z0);
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.I0;
    }

    @Override // defpackage.Wj1
    public void w() {
        super.w();
        this.b0.setOnKeyListener(new C2747gl1(this));
        HomeButton homeButton = this.c0;
        if (homeButton != null) {
            homeButton.setOnClickListener(this);
        }
        C5976zi0 zi0 = this.R;
        C2918hl1 hl1 = new C2918hl1(this);
        MenuButton menuButton = zi0.e;
        if (menuButton != null) {
            menuButton.setOnKeyListener(hl1);
        }
        if (this.Y0) {
            this.Y0 = false;
            p0(this.f9169J.g() && !this.N, false);
        }
        D0();
    }

    public final void w0() {
        Sj1 sj1;
        int i = 0;
        if (!(this.k0 == 0 && !i0() && !j0() && (sj1 = this.f9169J) != null && !sj1.g())) {
            i = 4;
        }
        ImageView imageView = this.f0;
        if (imageView != null && imageView.getVisibility() != i) {
            this.f0.setVisibility(i);
        }
    }

    public final void x0(int i) {
        if (this.G0.getColor() != i) {
            this.G0.setColor(i);
            invalidate();
        }
    }

    @Override // defpackage.Wj1
    public void y(boolean z) {
        if (this.T0) {
            this.S0.end();
        }
        int color = this.G0.getColor();
        int c = this.f9169J.c();
        if (color != c) {
            int d02 = d0(color);
            int d03 = d0(c);
            if (m0(this.Z0)) {
                if (!z) {
                    x0(c);
                    return;
                }
                boolean h = AbstractC1270Uv.h(c);
                int i = this.E0;
                int i2 = h ? 255 : 51;
                boolean z2 = i != i2;
                ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(250L);
                this.S0 = duration;
                duration.setInterpolator(animation.InterpolatorC5286vf.e);
                this.S0.addUpdateListener(new Xk1(this, z2, i, i2, color, c, d02, d03));
                this.S0.addListener(new Yk1(this));
                this.S0.start();
                this.T0 = true;
                Runnable runnable = this.V0;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    public final void y0(int i) {
        x0(f0(i));
    }

    @Override // defpackage.Wj1
    public void z() {
        ToggleTabStackButton toggleTabStackButton = this.b0;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setClickable(true);
        }
    }

    public final boolean z0() {
        int g02 = g0(this.Z0);
        int h02 = h0(this.Z0);
        this.C0 = g02;
        int i = h02 - g02;
        if (this.B0 == i) {
            return false;
        }
        this.B0 = i;
        this.W.N.F.l(i);
        return true;
    }
}
