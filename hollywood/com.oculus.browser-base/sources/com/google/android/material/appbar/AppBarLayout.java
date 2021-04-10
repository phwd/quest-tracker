package com.google.android.material.appbar;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppBarLayout extends LinearLayout implements KA {
    public int F;
    public int G = -1;
    public int H = -1;
    public int I = -1;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9686J;
    public int K = 0;
    public C3985nz1 L;
    public List M;
    public boolean N;
    public boolean O;
    public boolean P;
    public int Q;
    public WeakReference R;
    public ValueAnimator S;
    public int[] T;
    public Drawable U;

    /* JADX INFO: finally extract failed */
    public AppBarLayout(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, R.attr.f1800_resource_name_obfuscated_RES_2130968626, R.style.f74920_resource_name_obfuscated_RES_2132018065), attributeSet, R.attr.f1800_resource_name_obfuscated_RES_2130968626);
        Context context2 = getContext();
        boolean z = true;
        setOrientation(1);
        int i = Build.VERSION.SDK_INT;
        setOutlineProvider(ViewOutlineProvider.BOUNDS);
        Context context3 = getContext();
        TypedArray d = AbstractC1178Tg1.d(context3, attributeSet, AbstractC6016zv1.f11781a, R.attr.f1800_resource_name_obfuscated_RES_2130968626, R.style.f74920_resource_name_obfuscated_RES_2132018065, new int[0]);
        try {
            if (d.hasValue(0)) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(context3, d.getResourceId(0, 0)));
            }
            d.recycle();
            TypedArray d2 = AbstractC1178Tg1.d(context2, attributeSet, FJ0.i, R.attr.f1800_resource_name_obfuscated_RES_2130968626, R.style.f74920_resource_name_obfuscated_RES_2132018065, new int[0]);
            Drawable drawable = d2.getDrawable(0);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            setBackground(drawable);
            if (getBackground() instanceof ColorDrawable) {
                C3234jd0 jd0 = new C3234jd0();
                jd0.o(ColorStateList.valueOf(((ColorDrawable) getBackground()).getColor()));
                jd0.H.b = new EK(context2);
                jd0.s();
                setBackground(jd0);
            }
            if (d2.hasValue(4)) {
                h(d2.getBoolean(4, false), false, false);
            }
            if (d2.hasValue(3)) {
                int integer = getResources().getInteger(R.integer.f35690_resource_name_obfuscated_RES_2131492866);
                StateListAnimator stateListAnimator = new StateListAnimator();
                long j = (long) integer;
                stateListAnimator.addState(new int[]{16842766, R.attr.f7910_resource_name_obfuscated_RES_2130969237, -2130969238}, ObjectAnimator.ofFloat(this, "elevation", 0.0f).setDuration(j));
                stateListAnimator.addState(new int[]{16842766}, ObjectAnimator.ofFloat(this, "elevation", (float) d2.getDimensionPixelSize(3, 0)).setDuration(j));
                stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(this, "elevation", 0.0f).setDuration(0L));
                setStateListAnimator(stateListAnimator);
            }
            if (i >= 26) {
                if (d2.hasValue(2)) {
                    setKeyboardNavigationCluster(d2.getBoolean(2, false));
                }
                if (d2.hasValue(1)) {
                    setTouchscreenBlocksFocus(d2.getBoolean(1, false));
                }
            }
            this.P = d2.getBoolean(5, false);
            this.Q = d2.getResourceId(6, -1);
            Drawable drawable2 = d2.getDrawable(7);
            Drawable drawable3 = this.U;
            if (drawable3 != drawable2) {
                Drawable drawable4 = null;
                if (drawable3 != null) {
                    drawable3.setCallback(null);
                }
                drawable4 = drawable2 != null ? drawable2.mutate() : drawable4;
                this.U = drawable4;
                if (drawable4 != null) {
                    if (drawable4.isStateful()) {
                        this.U.setState(getDrawableState());
                    }
                    this.U.setLayoutDirection(getLayoutDirection());
                    this.U.setVisible(getVisibility() != 0 ? false : z, false);
                    this.U.setCallback(this);
                }
                l();
                postInvalidateOnAnimation();
            }
            d2.recycle();
            AbstractC1920bu1.o(this, new C5881z7(this));
        } catch (Throwable th) {
            d.recycle();
            throw th;
        }
    }

    public void a(H7 h7) {
        if (this.M == null) {
            this.M = new ArrayList();
        }
        if (h7 != null && !this.M.contains(h7)) {
            this.M.add(h7);
        }
    }

    /* renamed from: b */
    public G7 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new G7((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new G7((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new G7(layoutParams);
    }

    public int c() {
        int i;
        int i2 = this.H;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            G7 g7 = (G7) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = g7.f8065a;
            if ((i4 & 5) == 5) {
                int i5 = ((LinearLayout.LayoutParams) g7).topMargin + ((LinearLayout.LayoutParams) g7).bottomMargin;
                if ((i4 & 8) != 0) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    i = i5 + childAt.getMinimumHeight();
                } else if ((i4 & 2) != 0) {
                    AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                    i = i5 + (measuredHeight - childAt.getMinimumHeight());
                } else {
                    i = i5 + measuredHeight;
                }
                if (childCount == 0) {
                    AtomicInteger atomicInteger3 = AbstractC1920bu1.f9571a;
                    if (childAt.getFitsSystemWindows()) {
                        i = Math.min(i, measuredHeight - e());
                    }
                }
                i3 += i;
            } else if (i3 > 0) {
                break;
            }
        }
        int max = Math.max(0, i3);
        this.H = max;
        return max;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof G7;
    }

    public int d() {
        int i = this.I;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            G7 g7 = (G7) childAt.getLayoutParams();
            int measuredHeight = ((LinearLayout.LayoutParams) g7).topMargin + ((LinearLayout.LayoutParams) g7).bottomMargin + childAt.getMeasuredHeight();
            int i4 = g7.f8065a;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 += measuredHeight;
            if ((i4 & 2) != 0) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                i3 -= childAt.getMinimumHeight();
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.I = max;
        return max;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.U != null && e() > 0) {
            int save = canvas.save();
            canvas.translate(0.0f, (float) (-this.F));
            this.U.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.U;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final int e() {
        C3985nz1 nz1 = this.L;
        if (nz1 != null) {
            return nz1.d();
        }
        return 0;
    }

    public final int f() {
        int i = this.G;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            G7 g7 = (G7) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = g7.f8065a;
            if ((i4 & 1) == 0) {
                break;
            }
            int i5 = measuredHeight + ((LinearLayout.LayoutParams) g7).topMargin + ((LinearLayout.LayoutParams) g7).bottomMargin + i3;
            if (i2 == 0) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                if (childAt.getFitsSystemWindows()) {
                    i5 -= e();
                }
            }
            i3 = i5;
            if ((i4 & 2) != 0) {
                AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                i3 -= childAt.getMinimumHeight();
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.G = max;
        return max;
    }

    public void g(int i) {
        this.F = i;
        if (!willNotDraw()) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
        List list = this.M;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                F7 f7 = (F7) this.M.get(i2);
                if (f7 != null) {
                    f7.a(this, i);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.widget.LinearLayout
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new G7(-1, -2);
    }

    public final void h(boolean z, boolean z2, boolean z3) {
        int i = 0;
        int i2 = (z ? 1 : 2) | (z2 ? 4 : 0);
        if (z3) {
            i = 8;
        }
        this.K = i2 | i;
        requestLayout();
    }

    public boolean i(boolean z) {
        if (this.O == z) {
            return false;
        }
        this.O = z;
        refreshDrawableState();
        if (this.P && (getBackground() instanceof C3234jd0)) {
            C3234jd0 jd0 = (C3234jd0) getBackground();
            float dimension = getResources().getDimension(R.dimen.f18140_resource_name_obfuscated_RES_2131165433);
            float f = z ? 0.0f : dimension;
            if (!z) {
                dimension = 0.0f;
            }
            ValueAnimator valueAnimator = this.S;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f, dimension);
            this.S = ofFloat;
            ofFloat.setDuration((long) getResources().getInteger(R.integer.f35690_resource_name_obfuscated_RES_2131492866));
            this.S.setInterpolator(P6.f8667a);
            this.S.addUpdateListener(new A7(this, jd0));
            this.S.start();
        }
        return true;
    }

    public boolean j(View view) {
        int i;
        View view2 = null;
        if (this.R == null && (i = this.Q) != -1) {
            View findViewById = view != null ? view.findViewById(i) : null;
            if (findViewById == null && (getParent() instanceof ViewGroup)) {
                findViewById = ((ViewGroup) getParent()).findViewById(this.Q);
            }
            if (findViewById != null) {
                this.R = new WeakReference(findViewById);
            }
        }
        WeakReference weakReference = this.R;
        if (weakReference != null) {
            view2 = (View) weakReference.get();
        }
        if (view2 != null) {
            view = view2;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    public final boolean k() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt.getVisibility() == 8) {
            return false;
        }
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (!childAt.getFitsSystemWindows()) {
            return true;
        }
        return false;
    }

    public final void l() {
        setWillNotDraw(!(this.U != null && e() > 0));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof C3234jd0) {
            AbstractC3405kd0.c(this, (C3234jd0) background);
        }
    }

    public int[] onCreateDrawableState(int i) {
        if (this.T == null) {
            this.T = new int[4];
        }
        int[] iArr = this.T;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.N;
        iArr[0] = z ? R.attr.f7910_resource_name_obfuscated_RES_2130969237 : -2130969237;
        iArr[1] = (!z || !this.O) ? -2130969238 : R.attr.f7920_resource_name_obfuscated_RES_2130969238;
        iArr[2] = z ? R.attr.f7890_resource_name_obfuscated_RES_2130969235 : -2130969235;
        iArr[3] = (!z || !this.O) ? -2130969234 : R.attr.f7880_resource_name_obfuscated_RES_2130969234;
        return LinearLayout.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.R;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.R = null;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        super.onLayout(z, i, i2, i3, i4);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean z3 = true;
        if (getFitsSystemWindows() && k()) {
            int e = e();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).offsetTopAndBottom(e);
            }
        }
        this.G = -1;
        this.H = -1;
        this.I = -1;
        this.f9686J = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            } else if (((G7) getChildAt(i5).getLayoutParams()).b != null) {
                this.f9686J = true;
                break;
            } else {
                i5++;
            }
        }
        Drawable drawable = this.U;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), e());
        }
        if (!this.P) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z2 = false;
                    break;
                }
                int i7 = ((G7) getChildAt(i6).getLayoutParams()).f8065a;
                if ((i7 & 1) == 1 && (i7 & 10) != 0) {
                    z2 = true;
                    break;
                }
                i6++;
            }
            if (!z2) {
                z3 = false;
            }
        }
        if (this.N != z3) {
            this.N = z3;
            refreshDrawableState();
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (getFitsSystemWindows() && k()) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = AbstractC4260pd0.a(e() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
                } else if (mode == 0) {
                    measuredHeight += e();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        this.G = -1;
        this.H = -1;
        this.I = -1;
    }

    public void setElevation(float f) {
        super.setElevation(f);
        AbstractC3405kd0.b(this, f);
    }

    public void setOrientation(int i) {
        if (i == 1) {
            super.setOrientation(i);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.U;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.U;
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Behavior extends VW {
        public int j;
        public int k;
        public ValueAnimator l;
        public int m = -1;
        public boolean n;
        public float o;
        public WeakReference p;

        public Behavior() {
        }

        public static boolean A(int i, int i2) {
            return (i & i2) == i2;
        }

        public final View B(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof AbstractC4461qn0) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public final int C(AppBarLayout appBarLayout, int i) {
            int childCount = appBarLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = appBarLayout.getChildAt(i2);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                G7 g7 = (G7) childAt.getLayoutParams();
                if (A(g7.f8065a, 32)) {
                    top -= ((LinearLayout.LayoutParams) g7).topMargin;
                    bottom += ((LinearLayout.LayoutParams) g7).bottomMargin;
                }
                int i3 = -i;
                if (top <= i3 && bottom >= i3) {
                    return i2;
                }
            }
            return -1;
        }

        public final int D(AppBarLayout appBarLayout, int i) {
            int abs = Math.abs(i);
            int childCount = appBarLayout.getChildCount();
            int i2 = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = appBarLayout.getChildAt(i3);
                G7 g7 = (G7) childAt.getLayoutParams();
                Interpolator interpolator = g7.b;
                if (abs >= childAt.getTop() && abs <= childAt.getBottom()) {
                    if (interpolator == null) {
                        return i;
                    } else {
                        int i4 = g7.f8065a;
                        if ((i4 & 1) != 0) {
                            i2 = 0 + childAt.getHeight() + ((LinearLayout.LayoutParams) g7).topMargin + ((LinearLayout.LayoutParams) g7).bottomMargin;
                            if ((i4 & 2) != 0) {
                                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                                i2 -= childAt.getMinimumHeight();
                            }
                        }
                        AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                        if (childAt.getFitsSystemWindows()) {
                            i2 -= appBarLayout.e();
                        }
                        if (i2 <= 0) {
                            return i;
                        }
                        float f = (float) i2;
                        return Integer.signum(i) * (childAt.getTop() + Math.round(interpolator.getInterpolation(((float) (abs - childAt.getTop())) / f) * f));
                    }
                }
            }
            return i;
        }

        /* renamed from: E */
        public void i(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
            int i4;
            int i5;
            if (i2 != 0) {
                if (i2 < 0) {
                    i5 = -appBarLayout.f();
                    i4 = appBarLayout.c() + i5;
                } else {
                    i5 = -appBarLayout.f();
                    i4 = 0;
                }
                if (i5 != i4) {
                    iArr[1] = s(coordinatorLayout, appBarLayout, i2, i5, i4);
                }
            }
            if (appBarLayout.P) {
                appBarLayout.i(appBarLayout.j(view));
            }
        }

        public final boolean F(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            List list = (List) coordinatorLayout.L.b.getOrDefault(appBarLayout, null);
            coordinatorLayout.N.clear();
            if (list != null) {
                coordinatorLayout.N.addAll(list);
            }
            List list2 = coordinatorLayout.N;
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                AbstractC4993tu1 tu1 = ((NA) ((View) list2.get(i)).getLayoutParams()).f8531a;
                if (tu1 instanceof ScrollingViewBehavior) {
                    if (((ScrollingViewBehavior) tu1).f != 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        public final void G(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int r = r();
            int C = C(appBarLayout, r);
            if (C >= 0) {
                View childAt = appBarLayout.getChildAt(C);
                G7 g7 = (G7) childAt.getLayoutParams();
                int i = g7.f8065a;
                if ((i & 17) == 17) {
                    int i2 = -childAt.getTop();
                    int i3 = -childAt.getBottom();
                    if (C == appBarLayout.getChildCount() - 1) {
                        i3 += appBarLayout.e();
                    }
                    if (A(i, 2)) {
                        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                        i3 += childAt.getMinimumHeight();
                    } else if (A(i, 5)) {
                        AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                        int minimumHeight = childAt.getMinimumHeight() + i3;
                        if (r < minimumHeight) {
                            i2 = minimumHeight;
                        } else {
                            i3 = minimumHeight;
                        }
                    }
                    if (A(i, 32)) {
                        i2 += ((LinearLayout.LayoutParams) g7).topMargin;
                        i3 -= ((LinearLayout.LayoutParams) g7).bottomMargin;
                    }
                    if (r < (i3 + i2) / 2) {
                        i2 = i3;
                    }
                    x(coordinatorLayout, appBarLayout, AbstractC4260pd0.a(i2, -appBarLayout.f(), 0), 0.0f);
                }
            }
        }

        public final void H(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            AbstractC1920bu1.k(A.b.a(), coordinatorLayout);
            AbstractC1920bu1.h(coordinatorLayout, 0);
            AbstractC1920bu1.k(A.c.a(), coordinatorLayout);
            AbstractC1920bu1.h(coordinatorLayout, 0);
            View B = B(coordinatorLayout);
            if (B != null && appBarLayout.f() != 0 && (((NA) B.getLayoutParams()).f8531a instanceof ScrollingViewBehavior)) {
                v(coordinatorLayout, appBarLayout, B);
            }
        }

        public final void I(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, boolean z) {
            View view;
            int abs = Math.abs(i);
            int childCount = appBarLayout.getChildCount();
            boolean z2 = false;
            int i3 = 0;
            while (true) {
                if (i3 >= childCount) {
                    view = null;
                    break;
                }
                view = appBarLayout.getChildAt(i3);
                if (abs >= view.getTop() && abs <= view.getBottom()) {
                    break;
                }
                i3++;
            }
            if (view != null) {
                int i4 = ((G7) view.getLayoutParams()).f8065a;
                if ((i4 & 1) != 0) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    int minimumHeight = view.getMinimumHeight();
                    if (i2 <= 0 || (i4 & 12) == 0 ? !((i4 & 2) == 0 || (-i) < (view.getBottom() - minimumHeight) - appBarLayout.e()) : (-i) >= (view.getBottom() - minimumHeight) - appBarLayout.e()) {
                        z2 = true;
                    }
                }
                if (appBarLayout.P) {
                    z2 = appBarLayout.j(B(coordinatorLayout));
                }
                boolean i5 = appBarLayout.i(z2);
                if (z || (i5 && F(coordinatorLayout, appBarLayout))) {
                    appBarLayout.jumpDrawablesToCurrentState();
                }
            }
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
            int i2;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.g(coordinatorLayout, appBarLayout, i);
            int i3 = appBarLayout.K;
            int i4 = this.m;
            if (i4 >= 0 && (i3 & 8) == 0) {
                View childAt = appBarLayout.getChildAt(i4);
                int i5 = -childAt.getBottom();
                if (this.n) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    i2 = appBarLayout.e() + childAt.getMinimumHeight() + i5;
                } else {
                    i2 = Math.round(((float) childAt.getHeight()) * this.o) + i5;
                }
                t(coordinatorLayout, appBarLayout, i2);
            } else if (i3 != 0) {
                boolean z = (i3 & 4) != 0;
                if ((i3 & 2) != 0) {
                    int i6 = -appBarLayout.f();
                    if (z) {
                        x(coordinatorLayout, appBarLayout, i6, 0.0f);
                    } else {
                        t(coordinatorLayout, appBarLayout, i6);
                    }
                } else if ((i3 & 1) != 0) {
                    if (z) {
                        x(coordinatorLayout, appBarLayout, 0, 0.0f);
                    } else {
                        t(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            appBarLayout.K = 0;
            this.m = -1;
            q(AbstractC4260pd0.a(a(), -appBarLayout.f(), 0));
            I(coordinatorLayout, appBarLayout, a(), 0, true);
            appBarLayout.g(a());
            H(coordinatorLayout, appBarLayout);
            return true;
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean h(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((ViewGroup.MarginLayoutParams) ((NA) appBarLayout.getLayoutParams())).height != -2) {
                return false;
            }
            coordinatorLayout.r(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        @Override // defpackage.AbstractC4993tu1
        public void j(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i4 < 0) {
                iArr[1] = s(coordinatorLayout, appBarLayout, i4, -appBarLayout.d(), 0);
            }
            if (i4 == 0) {
                H(coordinatorLayout, appBarLayout);
            }
        }

        @Override // defpackage.AbstractC4993tu1
        public void l(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof AppBarLayout$BaseBehavior$SavedState) {
                AppBarLayout$BaseBehavior$SavedState appBarLayout$BaseBehavior$SavedState = (AppBarLayout$BaseBehavior$SavedState) parcelable;
                this.m = appBarLayout$BaseBehavior$SavedState.H;
                this.o = appBarLayout$BaseBehavior$SavedState.I;
                this.n = appBarLayout$BaseBehavior$SavedState.f9687J;
                return;
            }
            this.m = -1;
        }

        @Override // defpackage.AbstractC4993tu1
        public Parcelable m(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            int a2 = a();
            int childCount = appBarLayout.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + a2;
                if (childAt.getTop() + a2 <= 0 && bottom >= 0) {
                    AppBarLayout$BaseBehavior$SavedState appBarLayout$BaseBehavior$SavedState = new AppBarLayout$BaseBehavior$SavedState(absSavedState);
                    appBarLayout$BaseBehavior$SavedState.H = i;
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    if (bottom == appBarLayout.e() + childAt.getMinimumHeight()) {
                        z = true;
                    }
                    appBarLayout$BaseBehavior$SavedState.f9687J = z;
                    appBarLayout$BaseBehavior$SavedState.I = ((float) bottom) / ((float) childAt.getHeight());
                    return appBarLayout$BaseBehavior$SavedState;
                }
            }
            return absSavedState;
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean n(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            ValueAnimator valueAnimator;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z = (i & 2) != 0 && (appBarLayout.P || z(coordinatorLayout, appBarLayout, view2));
            if (z && (valueAnimator = this.l) != null) {
                valueAnimator.cancel();
            }
            this.p = null;
            this.k = i2;
            return z;
        }

        @Override // defpackage.AbstractC4993tu1
        public void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.k == 0 || i == 1) {
                G(coordinatorLayout, appBarLayout);
                if (appBarLayout.P) {
                    appBarLayout.i(appBarLayout.j(view2));
                }
            }
            this.p = new WeakReference(view2);
        }

        @Override // defpackage.VW
        public int r() {
            return a() + this.j;
        }

        @Override // defpackage.VW
        public int u(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            List list;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int r = r();
            int i4 = 0;
            if (i2 == 0 || r < i2 || r > i3) {
                this.j = 0;
            } else {
                int a2 = AbstractC4260pd0.a(i, i2, i3);
                if (r != a2) {
                    int D = appBarLayout.f9686J ? D(appBarLayout, a2) : a2;
                    boolean q = q(D);
                    int i5 = r - a2;
                    this.j = a2 - D;
                    if (!q && appBarLayout.f9686J && (list = (List) coordinatorLayout.L.b.getOrDefault(appBarLayout, null)) != null && !list.isEmpty()) {
                        while (i4 < list.size()) {
                            View view2 = (View) list.get(i4);
                            AbstractC4993tu1 tu1 = ((NA) view2.getLayoutParams()).f8531a;
                            if (tu1 != null) {
                                tu1.d(coordinatorLayout, view2, appBarLayout);
                            }
                            i4++;
                        }
                    }
                    appBarLayout.g(a());
                    I(coordinatorLayout, appBarLayout, a2, a2 < r ? -1 : 1, false);
                    i4 = i5;
                }
            }
            H(coordinatorLayout, appBarLayout);
            return i4;
        }

        public final void v(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view) {
            if (r() != (-appBarLayout.f()) && view.canScrollVertically(1)) {
                w(coordinatorLayout, appBarLayout, A.b, false);
            }
            if (r() == 0) {
                return;
            }
            if (view.canScrollVertically(-1)) {
                int i = -appBarLayout.c();
                if (i != 0) {
                    AbstractC1920bu1.l(coordinatorLayout, A.c, null, new C7(this, coordinatorLayout, appBarLayout, view, i));
                    return;
                }
                return;
            }
            w(coordinatorLayout, appBarLayout, A.c, true);
        }

        public final void w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, A a2, boolean z) {
            AbstractC1920bu1.l(coordinatorLayout, a2, null, new D7(this, appBarLayout, z));
        }

        public final void x(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, float f) {
            int i2;
            int abs = Math.abs(r() - i);
            float abs2 = Math.abs(f);
            if (abs2 > 0.0f) {
                i2 = Math.round((((float) abs) / abs2) * 1000.0f) * 3;
            } else {
                i2 = (int) (((((float) abs) / ((float) appBarLayout.getHeight())) + 1.0f) * 150.0f);
            }
            y(coordinatorLayout, appBarLayout, i, i2);
        }

        public final void y(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2) {
            int r = r();
            if (r == i) {
                ValueAnimator valueAnimator = this.l;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.l.cancel();
                    return;
                }
                return;
            }
            ValueAnimator valueAnimator2 = this.l;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.l = valueAnimator3;
                valueAnimator3.setInterpolator(P6.e);
                this.l.addUpdateListener(new B7(this, coordinatorLayout, appBarLayout));
            } else {
                valueAnimator2.cancel();
            }
            this.l.setDuration((long) Math.min(i2, 600));
            this.l.setIntValues(r, i);
            this.l.start();
        }

        public final boolean z(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view) {
            return (appBarLayout.f() != 0) && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight();
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    @Override // android.widget.LinearLayout, android.widget.LinearLayout
    /* renamed from: generateDefaultLayoutParams  reason: collision with other method in class */
    public LinearLayout.LayoutParams m2generateDefaultLayoutParams() {
        return new G7(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.widget.LinearLayout, android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new G7(getContext(), attributeSet);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ScrollingViewBehavior extends AbstractC4993tu1 {
        public final Rect c = new Rect();
        public final Rect d = new Rect();
        public int e = 0;
        public int f;

        public ScrollingViewBehavior() {
        }

        @Override // defpackage.AbstractC4993tu1
        public void b(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout r = r(coordinatorLayout.j(view));
            if (r != null) {
                NA na = (NA) view.getLayoutParams();
                Rect rect = this.c;
                rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) na).leftMargin, r.getBottom() + ((ViewGroup.MarginLayoutParams) na).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) na).rightMargin, ((r.getBottom() + coordinatorLayout.getHeight()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) na).bottomMargin);
                C3985nz1 nz1 = coordinatorLayout.a0;
                if (nz1 != null) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                        rect.left = nz1.b() + rect.left;
                        rect.right -= nz1.c();
                    }
                }
                Rect rect2 = this.d;
                int i2 = na.c;
                if (i2 == 0) {
                    i2 = 8388659;
                }
                Gravity.apply(i2, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
                int s = s(r);
                view.layout(rect2.left, rect2.top - s, rect2.right, rect2.bottom - s);
                this.e = rect2.top - r.getBottom();
                return;
            }
            coordinatorLayout.q(view, i);
            this.e = 0;
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean c(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
            AbstractC4993tu1 tu1 = ((NA) view2.getLayoutParams()).f8531a;
            if (tu1 instanceof Behavior) {
                int bottom = (((view2.getBottom() - view.getTop()) + ((Behavior) tu1).j) + this.e) - s(view2);
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                view.offsetTopAndBottom(bottom);
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.P) {
                return false;
            }
            appBarLayout.i(appBarLayout.j(view));
            return false;
        }

        @Override // defpackage.AbstractC4993tu1
        public void e(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AbstractC1920bu1.k(A.b.a(), coordinatorLayout);
                AbstractC1920bu1.h(coordinatorLayout, 0);
                AbstractC1920bu1.k(A.c.a(), coordinatorLayout);
                AbstractC1920bu1.h(coordinatorLayout, 0);
            }
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean h(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            int i5 = view.getLayoutParams().height;
            if (i5 == -1 || i5 == -2) {
                AppBarLayout r = r(coordinatorLayout.j(view));
                if (r != null) {
                    int size = View.MeasureSpec.getSize(i3);
                    if (size > 0) {
                        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                        if (r.getFitsSystemWindows()) {
                            C3985nz1 nz1 = coordinatorLayout.a0;
                            if (nz1 != null) {
                                size += nz1.a() + nz1.d();
                            }
                        }
                    } else {
                        size = coordinatorLayout.getHeight();
                    }
                    coordinatorLayout.r(view, i, i2, View.MeasureSpec.makeMeasureSpec((r.f() + size) - r.getMeasuredHeight(), i5 == -1 ? 1073741824 : Integer.MIN_VALUE), i4);
                    return true;
                }
            }
            return false;
        }

        @Override // defpackage.AbstractC4993tu1
        public boolean k(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout r = r(coordinatorLayout.j(view));
            if (r != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.c;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    r.h(false, !z, true);
                    return true;
                }
            }
            return false;
        }

        public AppBarLayout r(List list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        public final int s(View view) {
            int i;
            if (this.f == 0) {
                return 0;
            }
            float f2 = 0.0f;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int f3 = appBarLayout.f();
                int c2 = appBarLayout.c();
                AbstractC4993tu1 tu1 = ((NA) appBarLayout.getLayoutParams()).f8531a;
                int r = tu1 instanceof Behavior ? ((Behavior) tu1).r() : 0;
                if ((c2 == 0 || f3 + r > c2) && (i = f3 - c2) != 0) {
                    f2 = 1.0f + (((float) r) / ((float) i));
                }
            }
            int i2 = this.f;
            return AbstractC4260pd0.a((int) (f2 * ((float) i2)), 0, i2);
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.x0);
            this.f = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.LinearLayout, android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: generateLayoutParams  reason: collision with other method in class */
    public LinearLayout.LayoutParams m3generateLayoutParams(AttributeSet attributeSet) {
        return new G7(getContext(), attributeSet);
    }
}
