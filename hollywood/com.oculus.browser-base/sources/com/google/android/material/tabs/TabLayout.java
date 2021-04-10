package com.google.android.material.tabs;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@AbstractC6013zu1
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabLayout extends HorizontalScrollView {
    public static final C5903zE0 F = new AE0(16);
    public final ArrayList G = new ArrayList();
    public D81 H;
    public final RectF I = new RectF();

    /* renamed from: J  reason: collision with root package name */
    public final C81 f9694J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public ColorStateList P;
    public ColorStateList Q;
    public ColorStateList R;
    public Drawable S;
    public PorterDuff.Mode T;
    public float U;
    public float V;
    public final int W;
    public int a0 = Integer.MAX_VALUE;
    public final int b0;
    public final int c0;
    public final int d0;
    public int e0;
    public int f0;
    public int g0;
    public int h0;
    public int i0;
    public boolean j0;
    public boolean k0;
    public boolean l0;
    public final ArrayList m0 = new ArrayList();
    public AbstractC5546x81 n0;
    public ValueAnimator o0;
    public ViewPager p0;
    public AbstractC0966Pv0 q0;
    public DataSetObserver r0;
    public E81 s0;
    public C5376w81 t0;
    public boolean u0;
    public final C5903zE0 v0 = new C5903zE0(12);

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x021e, code lost:
        if (r13 != 2) goto L_0x0230;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TabLayout(android.content.Context r13, android.util.AttributeSet r14) {
        /*
        // Method dump skipped, instructions count: 569
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void a(D81 d81) {
        d(d81, this.G.isEmpty());
    }

    public void addView(View view) {
        e(view);
    }

    public void c(D81 d81, int i, boolean z) {
        if (d81.f == this) {
            d81.d = i;
            this.G.add(i, d81);
            int size = this.G.size();
            while (true) {
                i++;
                if (i >= size) {
                    break;
                }
                ((D81) this.G.get(i)).d = i;
            }
            G81 g81 = d81.g;
            g81.setSelected(false);
            g81.setActivated(false);
            C81 c81 = this.f9694J;
            int i2 = d81.d;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            u(layoutParams);
            c81.addView(g81, i2, layoutParams);
            if (z) {
                d81.b();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    public void d(D81 d81, boolean z) {
        c(d81, this.G.size(), z);
    }

    public final void e(View view) {
        if (view instanceof TabItem) {
            TabItem tabItem = (TabItem) view;
            D81 l = l();
            CharSequence charSequence = tabItem.F;
            if (charSequence != null) {
                l.d(charSequence);
            }
            Drawable drawable = tabItem.G;
            if (drawable != null) {
                l.c(drawable);
            }
            int i = tabItem.H;
            if (i != 0) {
                l.e = LayoutInflater.from(l.g.getContext()).inflate(i, (ViewGroup) l.g, false);
                l.e();
            }
            if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
                l.c = tabItem.getContentDescription();
                l.e();
            }
            d(l, this.G.isEmpty());
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public final void f(int i) {
        boolean z;
        if (i != -1) {
            if (getWindowToken() != null) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                if (isLaidOut()) {
                    C81 c81 = this.f9694J;
                    int childCount = c81.getChildCount();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= childCount) {
                            z = false;
                            break;
                        } else if (c81.getChildAt(i2).getWidth() <= 0) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        int scrollX = getScrollX();
                        int g = g(i, 0.0f);
                        if (scrollX != g) {
                            if (this.o0 == null) {
                                ValueAnimator valueAnimator = new ValueAnimator();
                                this.o0 = valueAnimator;
                                valueAnimator.setInterpolator(P6.b);
                                this.o0.setDuration((long) this.g0);
                                this.o0.addUpdateListener(new C5206v81(this));
                            }
                            this.o0.setIntValues(scrollX, g);
                            this.o0.start();
                        }
                        C81 c812 = this.f9694J;
                        int i3 = this.g0;
                        ValueAnimator valueAnimator2 = c812.N;
                        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                            c812.N.cancel();
                        }
                        c812.c(true, i, i3);
                        return;
                    }
                }
            }
            q(i, 0.0f, true, true);
        }
    }

    public final int g(int i, float f) {
        int i2 = this.i0;
        int i3 = 0;
        if (i2 != 0 && i2 != 2) {
            return 0;
        }
        View childAt = this.f9694J.getChildAt(i);
        int i4 = i + 1;
        View childAt2 = i4 < this.f9694J.getChildCount() ? this.f9694J.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        if (childAt2 != null) {
            i3 = childAt2.getWidth();
        }
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i5 = (int) (((float) (width + i3)) * 0.5f * f);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return getLayoutDirection() == 0 ? left + i5 : left - i5;
    }

    public int h() {
        D81 d81 = this.H;
        if (d81 != null) {
            return d81.d;
        }
        return -1;
    }

    public D81 i(int i) {
        if (i < 0 || i >= j()) {
            return null;
        }
        return (D81) this.G.get(i);
    }

    public int j() {
        return this.G.size();
    }

    public final int k() {
        int i = this.b0;
        if (i != -1) {
            return i;
        }
        int i2 = this.i0;
        if (i2 == 0 || i2 == 2) {
            return this.d0;
        }
        return 0;
    }

    public D81 l() {
        D81 d81 = (D81) F.a();
        if (d81 == null) {
            d81 = new D81();
        }
        d81.f = this;
        C5903zE0 ze0 = this.v0;
        G81 g81 = ze0 != null ? (G81) ze0.a() : null;
        if (g81 == null) {
            g81 = new G81(this, getContext());
        }
        if (d81 != g81.F) {
            g81.F = d81;
            g81.a();
        }
        g81.setFocusable(true);
        g81.setMinimumWidth(k());
        if (TextUtils.isEmpty(d81.c)) {
            g81.setContentDescription(d81.b);
        } else {
            g81.setContentDescription(d81.c);
        }
        d81.g = g81;
        return d81;
    }

    public void m() {
        int i;
        n();
        AbstractC0966Pv0 pv0 = this.q0;
        if (pv0 != null) {
            int e = pv0.e();
            for (int i2 = 0; i2 < e; i2++) {
                D81 l = l();
                Objects.requireNonNull(this.q0);
                l.d(null);
                d(l, false);
            }
            ViewPager viewPager = this.p0;
            if (viewPager != null && e > 0 && (i = viewPager.O) != h() && i < j()) {
                o(i(i), true);
            }
        }
    }

    public void n() {
        int childCount = this.f9694J.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            G81 g81 = (G81) this.f9694J.getChildAt(childCount);
            this.f9694J.removeViewAt(childCount);
            if (g81 != null) {
                if (g81.F != null) {
                    g81.F = null;
                    g81.a();
                }
                g81.setSelected(false);
                this.v0.b(g81);
            }
            requestLayout();
        }
        Iterator it = this.G.iterator();
        while (it.hasNext()) {
            D81 d81 = (D81) it.next();
            it.remove();
            d81.f = null;
            d81.g = null;
            d81.f7870a = null;
            d81.b = null;
            d81.c = null;
            d81.d = -1;
            d81.e = null;
            F.b(d81);
        }
        this.H = null;
    }

    public void o(D81 d81, boolean z) {
        D81 d812 = this.H;
        if (d812 != d81) {
            int i = d81 != null ? d81.d : -1;
            if (z) {
                if ((d812 == null || d812.d == -1) && i != -1) {
                    q(i, 0.0f, true, true);
                } else {
                    f(i);
                }
                if (i != -1) {
                    s(i);
                }
            }
            this.H = d81;
            if (d812 != null) {
                for (int size = this.m0.size() - 1; size >= 0; size--) {
                    ((AbstractC5546x81) this.m0.get(size)).c(d812);
                }
            }
            if (d81 != null) {
                for (int size2 = this.m0.size() - 1; size2 >= 0; size2--) {
                    ((AbstractC5546x81) this.m0.get(size2)).f(d81);
                }
            }
        } else if (d812 != null) {
            for (int size3 = this.m0.size() - 1; size3 >= 0; size3--) {
                ((AbstractC5546x81) this.m0.get(size3)).a(d81);
            }
            f(d81.d);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof C3234jd0) {
            AbstractC3405kd0.c(this, (C3234jd0) background);
        }
        if (this.p0 == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                t((ViewPager) parent, true, true);
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.u0) {
            t(null, true, false);
            this.u0 = false;
        }
    }

    public void onDraw(Canvas canvas) {
        G81 g81;
        Drawable drawable;
        for (int i = 0; i < this.f9694J.getChildCount(); i++) {
            View childAt = this.f9694J.getChildAt(i);
            if ((childAt instanceof G81) && (drawable = (g81 = (G81) childAt).L) != null) {
                drawable.setBounds(g81.getLeft(), g81.getTop(), g81.getRight(), g81.getBottom());
                g81.L.draw(canvas);
            }
        }
        super.onDraw(canvas);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) B.a(1, j(), false, 1).f7712a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009f, code lost:
        if (r0 != 2) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00aa, code lost:
        if (r8.getMeasuredWidth() != getMeasuredWidth()) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ac, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b6, code lost:
        if (r8.getMeasuredWidth() < getMeasuredWidth()) goto L_0x00ac;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    public void p(AbstractC0966Pv0 pv0, boolean z) {
        DataSetObserver dataSetObserver;
        AbstractC0966Pv0 pv02 = this.q0;
        if (!(pv02 == null || (dataSetObserver = this.r0) == null)) {
            pv02.f8720a.unregisterObserver(dataSetObserver);
        }
        this.q0 = pv0;
        if (z && pv0 != null) {
            if (this.r0 == null) {
                this.r0 = new C5886z81(this);
            }
            pv0.f8720a.registerObserver(this.r0);
        }
        m();
    }

    public void q(int i, float f, boolean z, boolean z2) {
        int round = Math.round(((float) i) + f);
        if (round >= 0 && round < this.f9694J.getChildCount()) {
            if (z2) {
                C81 c81 = this.f9694J;
                ValueAnimator valueAnimator = c81.N;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    c81.N.cancel();
                }
                c81.I = i;
                c81.f7788J = f;
                c81.b();
            }
            ValueAnimator valueAnimator2 = this.o0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.o0.cancel();
            }
            scrollTo(g(i, f), 0);
            if (z) {
                s(round);
            }
        }
    }

    public void r(int i) {
        C81 c81 = this.f9694J;
        if (c81.G.getColor() != i) {
            c81.G.setColor(i);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            c81.postInvalidateOnAnimation();
        }
    }

    public final void s(int i) {
        int childCount = this.f9694J.getChildCount();
        if (i < childCount) {
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.f9694J.getChildAt(i2);
                boolean z = true;
                childAt.setSelected(i2 == i);
                if (i2 != i) {
                    z = false;
                }
                childAt.setActivated(z);
                i2++;
            }
        }
    }

    public void setElevation(float f) {
        super.setElevation(f);
        AbstractC3405kd0.b(this, f);
    }

    public boolean shouldDelayChildPressedState() {
        if (Math.max(0, ((this.f9694J.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight()) > 0) {
            return true;
        }
        return false;
    }

    public final void t(ViewPager viewPager, boolean z, boolean z2) {
        List list;
        List list2;
        ViewPager viewPager2 = this.p0;
        if (viewPager2 != null) {
            E81 e81 = this.s0;
            if (!(e81 == null || (list2 = viewPager2.B0) == null)) {
                list2.remove(e81);
            }
            C5376w81 w81 = this.t0;
            if (!(w81 == null || (list = this.p0.C0) == null)) {
                list.remove(w81);
            }
        }
        AbstractC5546x81 x81 = this.n0;
        if (x81 != null) {
            this.m0.remove(x81);
            this.n0 = null;
        }
        if (viewPager != null) {
            this.p0 = viewPager;
            if (this.s0 == null) {
                this.s0 = new E81(this);
            }
            E81 e812 = this.s0;
            e812.c = 0;
            e812.b = 0;
            viewPager.b(e812);
            H81 h81 = new H81(viewPager);
            this.n0 = h81;
            if (!this.m0.contains(h81)) {
                this.m0.add(h81);
            }
            AbstractC0966Pv0 pv0 = viewPager.N;
            if (pv0 != null) {
                p(pv0, z);
            }
            if (this.t0 == null) {
                this.t0 = new C5376w81(this);
            }
            C5376w81 w812 = this.t0;
            w812.f11522a = z;
            if (viewPager.C0 == null) {
                viewPager.C0 = new ArrayList();
            }
            viewPager.C0.add(w812);
            q(viewPager.O, 0.0f, true, true);
        } else {
            this.p0 = null;
            p(null, false);
        }
        this.u0 = z2;
    }

    public final void u(LinearLayout.LayoutParams layoutParams) {
        if (this.i0 == 1 && this.f0 == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    public void v(boolean z) {
        for (int i = 0; i < this.f9694J.getChildCount(); i++) {
            View childAt = this.f9694J.getChildAt(i);
            childAt.setMinimumWidth(k());
            u((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i) {
        e(view);
    }

    @Override // android.widget.FrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        e(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        e(view);
    }
}
