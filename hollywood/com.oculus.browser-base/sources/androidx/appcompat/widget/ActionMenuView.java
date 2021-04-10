package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionMenuView extends AbstractC5715y80 implements AbstractC4446qi0, AbstractC2398ej0 {
    public C4616ri0 U;
    public Context V;
    public int W = 0;
    public boolean a0;
    public C4676s2 b0;
    public AbstractC1886bj0 c0;
    public AbstractC4275pi0 d0;
    public boolean e0;
    public int f0;
    public int g0;
    public int h0;
    public C3937nj1 i0;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.F = false;
        float f = context.getResources().getDisplayMetrics().density;
        this.g0 = (int) (56.0f * f);
        this.h0 = (int) (f * 4.0f);
        this.V = context;
    }

    public static int v(View view, int i, int i2, int i3, int i4) {
        C5186v2 v2Var = (C5186v2) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = false;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.f();
        int i5 = 2;
        if (i2 <= 0 || (z2 && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i6 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i6++;
            }
            if (!z2 || i6 >= 2) {
                i5 = i6;
            }
        }
        if (!v2Var.c && z2) {
            z = true;
        }
        v2Var.f = z;
        v2Var.d = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
        return i5;
    }

    @Override // defpackage.AbstractC4446qi0
    public boolean a(C0817Ni0 ni0) {
        return this.U.r(ni0, null, 0);
    }

    @Override // defpackage.AbstractC2398ej0
    public void c(C4616ri0 ri0) {
        this.U = ri0;
    }

    @Override // defpackage.AbstractC5715y80
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C5186v2;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // defpackage.AbstractC5715y80
    public C5545x80 j(AttributeSet attributeSet) {
        return new C5186v2(getContext(), attributeSet);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C4676s2 s2Var = this.b0;
        if (s2Var != null) {
            s2Var.h(false);
            if (this.b0.m()) {
                this.b0.f();
                this.b0.n();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C4676s2 s2Var = this.b0;
        if (s2Var != null) {
            s2Var.b();
        }
    }

    @Override // defpackage.AbstractC5715y80
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (!this.e0) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i7 = (i4 - i2) / 2;
        int i8 = this.Q;
        int i9 = i3 - i;
        int paddingRight = (i9 - getPaddingRight()) - getPaddingLeft();
        boolean a2 = AbstractC4826sv1.a(this);
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                C5186v2 v2Var = (C5186v2) childAt.getLayoutParams();
                if (v2Var.c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (u(i12)) {
                        measuredWidth += i8;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a2) {
                        i5 = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) v2Var).leftMargin;
                        i6 = i5 + measuredWidth;
                    } else {
                        i6 = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) v2Var).rightMargin;
                        i5 = i6 - measuredWidth;
                    }
                    int i13 = i7 - (measuredHeight / 2);
                    childAt.layout(i5, i13, i6, measuredHeight + i13);
                    paddingRight -= measuredWidth;
                    i10 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) v2Var).leftMargin) + ((ViewGroup.MarginLayoutParams) v2Var).rightMargin;
                    u(i12);
                    i11++;
                }
            }
        }
        if (childCount == 1 && i10 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i14 = (i9 / 2) - (measuredWidth2 / 2);
            int i15 = i7 - (measuredHeight2 / 2);
            childAt2.layout(i14, i15, measuredWidth2 + i14, measuredHeight2 + i15);
            return;
        }
        int i16 = i11 - (i10 ^ 1);
        int max = Math.max(0, i16 > 0 ? paddingRight / i16 : 0);
        if (a2) {
            int width = getWidth() - getPaddingRight();
            for (int i17 = 0; i17 < childCount; i17++) {
                View childAt3 = getChildAt(i17);
                C5186v2 v2Var2 = (C5186v2) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !v2Var2.c) {
                    int i18 = width - ((ViewGroup.MarginLayoutParams) v2Var2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i19 = i7 - (measuredHeight3 / 2);
                    childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                    width = i18 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) v2Var2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i20 = 0; i20 < childCount; i20++) {
            View childAt4 = getChildAt(i20);
            C5186v2 v2Var3 = (C5186v2) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !v2Var3.c) {
                int i21 = paddingLeft + ((ViewGroup.MarginLayoutParams) v2Var3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i22 = i7 - (measuredHeight4 / 2);
                childAt4.layout(i21, i22, i21 + measuredWidth4, measuredHeight4 + i22);
                paddingLeft = measuredWidth4 + ((ViewGroup.MarginLayoutParams) v2Var3).rightMargin + max + i21;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v33, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // defpackage.AbstractC5715y80
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r30, int r31) {
        /*
        // Method dump skipped, instructions count: 685
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.onMeasure(int, int):void");
    }

    /* renamed from: r */
    public C5186v2 i() {
        C5186v2 v2Var = new C5186v2(-2, -2);
        v2Var.b = 16;
        return v2Var;
    }

    /* renamed from: s */
    public C5186v2 k(ViewGroup.LayoutParams layoutParams) {
        C5186v2 v2Var;
        if (layoutParams == null) {
            return i();
        }
        if (layoutParams instanceof C5186v2) {
            v2Var = new C5186v2((C5186v2) layoutParams);
        } else {
            v2Var = new C5186v2(layoutParams);
        }
        if (v2Var.b <= 0) {
            v2Var.b = 16;
        }
        return v2Var;
    }

    public Menu t() {
        if (this.U == null) {
            Context context = getContext();
            C4616ri0 ri0 = new C4616ri0(context);
            this.U = ri0;
            ri0.f = new C5356w2(this);
            C4676s2 s2Var = new C4676s2(context);
            this.b0 = s2Var;
            s2Var.Q = true;
            s2Var.R = true;
            AbstractC1886bj0 bj0 = this.c0;
            if (bj0 == null) {
                bj0 = new C5016u2();
            }
            s2Var.f11246J = bj0;
            this.U.b(s2Var, this.V);
            C4676s2 s2Var2 = this.b0;
            s2Var2.M = this;
            this.U = s2Var2.H;
        }
        return this.U;
    }

    public boolean u(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof AbstractC4846t2)) {
            z = false | ((AbstractC4846t2) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof AbstractC4846t2)) ? z : z | ((AbstractC4846t2) childAt2).b();
    }

    public void w(int i) {
        if (this.W != i) {
            this.W = i;
            if (i == 0) {
                this.V = getContext();
            } else {
                this.V = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    @Override // defpackage.AbstractC5715y80, android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C5186v2(getContext(), attributeSet);
    }
}
