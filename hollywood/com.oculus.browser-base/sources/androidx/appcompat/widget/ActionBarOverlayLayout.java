package androidx.appcompat.widget;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.widget.OverScroller;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionBarOverlayLayout extends ViewGroup implements CD, AbstractC4801sn0, AbstractC4971tn0 {
    public static final int[] F = {R.attr.f1330_resource_name_obfuscated_RES_2130968579, 16842841};
    public int G;
    public int H = 0;
    public ContentFrameLayout I;

    /* renamed from: J  reason: collision with root package name */
    public ActionBarContainer f9459J;
    public Hl1 K;
    public Drawable L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public int R;
    public int S;
    public final Rect T = new Rect();
    public final Rect U = new Rect();
    public final Rect V = new Rect();
    public final Rect W = new Rect();
    public final Rect a0 = new Rect();
    public final Rect b0 = new Rect();
    public final Rect c0 = new Rect();
    public C3985nz1 d0;
    public C3985nz1 e0;
    public C3985nz1 f0;
    public C3985nz1 g0;
    public AbstractC2797h2 h0;
    public OverScroller i0;
    public ViewPropertyAnimator j0;
    public final AnimatorListenerAdapter k0;
    public final Runnable l0;
    public final Runnable m0;
    public final C5141un0 n0;

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C3985nz1 nz1 = C3985nz1.f10524a;
        this.d0 = nz1;
        this.e0 = nz1;
        this.f0 = nz1;
        this.g0 = nz1;
        this.k0 = new C2284e2(this);
        this.l0 = new RunnableC2455f2(this);
        this.m0 = new RunnableC2626g2(this);
        j(context);
        this.n0 = new C5141un0();
    }

    @Override // defpackage.AbstractC4801sn0
    public void a(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public void b(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public void c(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2968i2;
    }

    @Override // defpackage.AbstractC4971tn0
    public void d(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    public void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.L != null && !this.M) {
            if (this.f9459J.getVisibility() == 0) {
                i = (int) (this.f9459J.getTranslationY() + ((float) this.f9459J.getBottom()) + 0.5f);
            } else {
                i = 0;
            }
            this.L.setBounds(0, i, getWidth(), this.L.getIntrinsicHeight() + i);
            this.L.draw(canvas);
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public void e(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public boolean f(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    public boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public final boolean g(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        int i;
        int i2;
        int i3;
        int i4;
        C2968i2 i2Var = (C2968i2) view.getLayoutParams();
        if (!z || ((ViewGroup.MarginLayoutParams) i2Var).leftMargin == (i4 = rect.left)) {
            z5 = false;
        } else {
            ((ViewGroup.MarginLayoutParams) i2Var).leftMargin = i4;
            z5 = true;
        }
        if (z2 && ((ViewGroup.MarginLayoutParams) i2Var).topMargin != (i3 = rect.top)) {
            ((ViewGroup.MarginLayoutParams) i2Var).topMargin = i3;
            z5 = true;
        }
        if (z4 && ((ViewGroup.MarginLayoutParams) i2Var).rightMargin != (i2 = rect.right)) {
            ((ViewGroup.MarginLayoutParams) i2Var).rightMargin = i2;
            z5 = true;
        }
        if (!z3 || ((ViewGroup.MarginLayoutParams) i2Var).bottomMargin == (i = rect.bottom)) {
            return z5;
        }
        ((ViewGroup.MarginLayoutParams) i2Var).bottomMargin = i;
        return true;
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C2968i2(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C2968i2(getContext(), attributeSet);
    }

    public int getNestedScrollAxes() {
        return this.n0.a();
    }

    public boolean h() {
        ActionMenuView actionMenuView;
        m();
        Toolbar toolbar = this.K.f8179a;
        return toolbar.getVisibility() == 0 && (actionMenuView = toolbar.F) != null && actionMenuView.a0;
    }

    public void i() {
        removeCallbacks(this.l0);
        removeCallbacks(this.m0);
        ViewPropertyAnimator viewPropertyAnimator = this.j0;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void j(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(F);
        boolean z = false;
        this.G = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.L = drawable;
        setWillNotDraw(drawable == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            z = true;
        }
        this.M = z;
        this.i0 = new OverScroller(context);
    }

    public void k(int i) {
        m();
        if (i == 2) {
            Objects.requireNonNull(this.K);
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i == 5) {
            Objects.requireNonNull(this.K);
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i == 109) {
            boolean z = true;
            this.N = true;
            if (getContext().getApplicationInfo().targetSdkVersion >= 19) {
                z = false;
            }
            this.M = z;
        }
    }

    public boolean l() {
        m();
        return this.K.f8179a.v();
    }

    public void m() {
        Hl1 hl1;
        if (this.I == null) {
            this.I = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.f9459J = (ActionBarContainer) findViewById(R.id.action_bar_container);
            View findViewById = findViewById(R.id.action_bar);
            if (findViewById instanceof Hl1) {
                hl1 = (Hl1) findViewById;
            } else if (findViewById instanceof Toolbar) {
                Toolbar toolbar = (Toolbar) findViewById;
                if (toolbar.q0 == null) {
                    toolbar.q0 = new Hl1(toolbar, true);
                }
                hl1 = toolbar.q0;
            } else {
                StringBuilder i = AbstractC2531fV.i("Can't make a decor toolbar out of ");
                i.append(findViewById.getClass().getSimpleName());
                throw new IllegalStateException(i.toString());
            }
            this.K = hl1;
        }
    }

    public void n(int i) {
        i();
        this.f9459J.setTranslationY((float) (-Math.max(0, Math.min(i, this.f9459J.getHeight()))));
    }

    public void o(boolean z) {
        if (z != this.P) {
            this.P = z;
            if (!z) {
                i();
                n(0);
            }
        }
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        m();
        C3985nz1 i = C3985nz1.i(windowInsets, null);
        boolean g = g(this.f9459J, new Rect(i.b(), i.d(), i.c(), i.a()), true, true, false, true);
        this.T.setEmpty();
        C3985nz1 nz1 = this.d0;
        Rect rect = this.T;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        WindowInsets g2 = nz1.g();
        if (g2 != null) {
            C3985nz1.i(computeSystemWindowInsets(g2, rect), this);
        } else {
            rect.setEmpty();
        }
        Rect rect2 = this.T;
        C3985nz1 h = i.b.h(rect2.left, rect2.top, rect2.right, rect2.bottom);
        this.d0 = h;
        boolean z = true;
        if (!this.e0.equals(h)) {
            this.e0 = this.d0;
            g = true;
        }
        if (!this.U.equals(this.T)) {
            this.U.set(this.T);
        } else {
            z = g;
        }
        if (z) {
            requestLayout();
        }
        return i.b.a().b.c().b.b().g();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j(getContext());
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        requestApplyInsets();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                C2968i2 i2Var = (C2968i2) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) i2Var).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) i2Var).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    public void onMeasure(int i, int i2) {
        int i3;
        AbstractC2789gz1 gz1;
        m();
        measureChildWithMargins(this.f9459J, i, 0, i2, 0);
        C2968i2 i2Var = (C2968i2) this.f9459J.getLayoutParams();
        int max = Math.max(0, this.f9459J.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) i2Var).leftMargin + ((ViewGroup.MarginLayoutParams) i2Var).rightMargin);
        int max2 = Math.max(0, this.f9459J.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) i2Var).topMargin + ((ViewGroup.MarginLayoutParams) i2Var).bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f9459J.getMeasuredState());
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean z = (getWindowSystemUiVisibility() & 256) != 0;
        if (z) {
            i3 = this.G;
            if (this.O && this.f9459J.G != null) {
                i3 += i3;
            }
        } else {
            i3 = this.f9459J.getVisibility() != 8 ? this.f9459J.getMeasuredHeight() : 0;
        }
        this.V.set(this.T);
        C3985nz1 nz1 = this.d0;
        this.f0 = nz1;
        if (this.N || z) {
            X10 a2 = X10.a(nz1.b(), this.f0.d() + i3, this.f0.c(), this.f0.a() + 0);
            C3985nz1 nz12 = this.f0;
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 30) {
                gz1 = new C2618fz1(nz12);
            } else if (i4 >= 29) {
                gz1 = new C2447ez1(nz12);
            } else {
                gz1 = new C2276dz1(nz12);
            }
            gz1.c(a2);
            this.f0 = gz1.a();
        } else {
            Rect rect = this.V;
            rect.top += i3;
            rect.bottom += 0;
            this.f0 = nz1.b.h(0, i3, 0, 0);
        }
        g(this.I, this.V, true, true, true, true);
        if (!this.g0.equals(this.f0)) {
            C3985nz1 nz13 = this.f0;
            this.g0 = nz13;
            AbstractC1920bu1.b(this.I, nz13);
        }
        measureChildWithMargins(this.I, i, 0, i2, 0);
        C2968i2 i2Var2 = (C2968i2) this.I.getLayoutParams();
        int max3 = Math.max(max, this.I.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) i2Var2).leftMargin + ((ViewGroup.MarginLayoutParams) i2Var2).rightMargin);
        int max4 = Math.max(max2, this.I.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) i2Var2).topMargin + ((ViewGroup.MarginLayoutParams) i2Var2).bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.I.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + max3, getSuggestedMinimumWidth()), i, combineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + max4, getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16));
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        boolean z2 = false;
        if (!this.P || !z) {
            return false;
        }
        this.i0.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.i0.getFinalY() > this.f9459J.getHeight()) {
            z2 = true;
        }
        if (z2) {
            i();
            this.m0.run();
        } else {
            i();
            this.l0.run();
        }
        this.Q = true;
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.R + i2;
        this.R = i5;
        n(i5);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        Ty1 ty1;
        C1923bv1 bv1;
        this.n0.f11435a = i;
        ActionBarContainer actionBarContainer = this.f9459J;
        this.R = actionBarContainer != null ? -((int) actionBarContainer.getTranslationY()) : 0;
        i();
        AbstractC2797h2 h2Var = this.h0;
        if (h2Var != null && (bv1 = (ty1 = (Ty1) h2Var).w) != null) {
            bv1.a();
            ty1.w = null;
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f9459J.getVisibility() != 0) {
            return false;
        }
        return this.P;
    }

    public void onStopNestedScroll(View view) {
        if (this.P && !this.Q) {
            if (this.R <= this.f9459J.getHeight()) {
                i();
                postDelayed(this.l0, 600);
            } else {
                i();
                postDelayed(this.m0, 600);
            }
        }
        AbstractC2797h2 h2Var = this.h0;
        if (h2Var != null) {
            Objects.requireNonNull((Ty1) h2Var);
        }
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        m();
        int i2 = this.S ^ i;
        this.S = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        AbstractC2797h2 h2Var = this.h0;
        if (h2Var != null) {
            ((Ty1) h2Var).r = !z2;
            if (z || !z2) {
                Ty1 ty1 = (Ty1) h2Var;
                if (ty1.t) {
                    ty1.t = false;
                    ty1.g(true);
                }
            } else {
                Ty1 ty12 = (Ty1) h2Var;
                if (!ty12.t) {
                    ty12.t = true;
                    ty12.g(true);
                }
            }
        }
        if ((i2 & 256) != 0 && this.h0 != null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            requestApplyInsets();
        }
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.H = i;
        AbstractC2797h2 h2Var = this.h0;
        if (h2Var != null) {
            ((Ty1) h2Var).q = i;
        }
    }

    public void p(Menu menu, AbstractC1886bj0 bj0) {
        C0817Ni0 ni0;
        m();
        Hl1 hl1 = this.K;
        if (hl1.n == null) {
            hl1.n = new C4676s2(hl1.f8179a.getContext());
        }
        C4676s2 s2Var = hl1.n;
        s2Var.f11246J = bj0;
        Toolbar toolbar = hl1.f8179a;
        C4616ri0 ri0 = (C4616ri0) menu;
        if (ri0 != null || toolbar.F != null) {
            toolbar.g();
            C4616ri0 ri02 = toolbar.F.U;
            if (ri02 != ri0) {
                if (ri02 != null) {
                    ri02.t(toolbar.r0);
                    ri02.t(toolbar.s0);
                }
                if (toolbar.s0 == null) {
                    toolbar.s0 = new C4450qj1(toolbar);
                }
                s2Var.V = true;
                if (ri0 != null) {
                    ri0.b(s2Var, toolbar.O);
                    ri0.b(toolbar.s0, toolbar.O);
                } else {
                    s2Var.g(toolbar.O, null);
                    C4450qj1 qj1 = toolbar.s0;
                    C4616ri0 ri03 = qj1.F;
                    if (!(ri03 == null || (ni0 = qj1.G) == null)) {
                        ri03.d(ni0);
                    }
                    qj1.F = null;
                    s2Var.h(true);
                    toolbar.s0.h(true);
                }
                toolbar.F.w(toolbar.P);
                ActionMenuView actionMenuView = toolbar.F;
                actionMenuView.b0 = s2Var;
                s2Var.M = actionMenuView;
                actionMenuView.U = s2Var.H;
                toolbar.r0 = s2Var;
            }
        }
    }

    public void q() {
    }

    public void r(CharSequence charSequence) {
        m();
        Hl1 hl1 = this.K;
        if (!hl1.h) {
            hl1.i = charSequence;
            if ((hl1.b & 8) != 0) {
                hl1.f8179a.I(charSequence);
            }
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C2968i2(layoutParams);
    }
}
