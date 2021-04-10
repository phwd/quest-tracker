package androidx.appcompat.widget;

import X.AbstractC11921ta;
import X.AbstractC11941tc;
import X.AbstractC11951te;
import X.AbstractC12021tm;
import X.AnonymousClass02C;
import X.AnonymousClass1sF;
import X.AnonymousClass1sT;
import X.AnonymousClass1tQ;
import X.AnonymousClass1td;
import X.C11581sN;
import X.C11591sO;
import X.C11601sP;
import X.C11781sp;
import X.C11841sv;
import X.C11871sz;
import X.C11891tK;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;

public class ActionMenuView extends LinearLayoutCompat implements AbstractC11921ta, AnonymousClass1td {
    public AnonymousClass1tQ A00;
    public int A01;
    public int A02;
    public C11581sN A03;
    public C11591sO A04;
    public AbstractC12021tm A05;
    public boolean A06;
    public int A07;
    public int A08;
    public Context A09;
    public AbstractC11941tc A0A;
    public boolean A0B;

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    private final boolean A00(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof AbstractC11951te)) {
            z = false | ((AbstractC11951te) childAt).A6O();
        }
        if (i <= 0 || !(childAt2 instanceof AbstractC11951te)) {
            return z;
        }
        return z | ((AbstractC11951te) childAt2).A6P();
    }

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int getWindowAnimations() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        if (((X.AnonymousClass1EM) r1).A01 <= 0) goto L_0x0011;
     */
    /* renamed from: A01 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C11841sv generateLayoutParams(android.view.ViewGroup.LayoutParams r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x001c
            boolean r0 = r3 instanceof X.C11841sv
            if (r0 == 0) goto L_0x0016
            X.1sv r3 = (X.C11841sv) r3
            X.1sv r1 = new X.1sv
            r1.<init>(r3)
        L_0x000d:
            int r0 = r1.A01
            if (r0 > 0) goto L_0x0015
        L_0x0011:
            r0 = 16
            r1.A01 = r0
        L_0x0015:
            return r1
        L_0x0016:
            X.1sv r1 = new X.1sv
            r1.<init>(r3)
            goto L_0x000d
        L_0x001c:
            X.1sv r1 = new X.1sv
            r1.<init>()
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.generateLayoutParams(android.view.ViewGroup$LayoutParams):X.1sv");
    }

    @Override // X.AbstractC11921ta
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final boolean A5p(C11601sP r4) {
        return this.A03.A0K(r4, null, 0);
    }

    public Menu getMenu() {
        if (this.A03 == null) {
            Context context = getContext();
            C11581sN r1 = new C11581sN(context);
            this.A03 = r1;
            r1.A0C(new C11891tK(this));
            C11591sO r2 = new C11591sO(context);
            this.A04 = r2;
            r2.A0A = true;
            r2.A0B = true;
            AbstractC11941tc r0 = this.A0A;
            if (r0 == null) {
                r0 = new AnonymousClass1sF();
            }
            r2.A9h(r0);
            this.A03.A0E(r2, this.A09);
            C11591sO r02 = this.A04;
            ((AnonymousClass1sT) r02).A05 = this;
            A5h(((AnonymousClass1sT) r02).A03);
        }
        return this.A03;
    }

    public int getPopupTheme() {
        return this.A08;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int width;
        int i7;
        if (!this.A0B) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i8 = (i4 - i2) >> 1;
        int i9 = this.mDividerWidth;
        int i10 = i3 - i;
        int paddingRight = (i10 - getPaddingRight()) - getPaddingLeft();
        boolean z2 = true;
        if (getLayoutDirection() != 1) {
            z2 = false;
        }
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                C11841sv r11 = (C11841sv) childAt.getLayoutParams();
                if (r11.A04) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (A00(i13)) {
                        measuredWidth += i9;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (z2) {
                        i7 = getPaddingLeft() + r11.leftMargin;
                        width = i7 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - r11.rightMargin;
                        i7 = width - measuredWidth;
                    }
                    int i14 = i8 - (measuredHeight >> 1);
                    childAt.layout(i7, i14, width, measuredHeight + i14);
                    paddingRight -= measuredWidth;
                    i11 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + r11.leftMargin) + r11.rightMargin;
                    A00(i13);
                    i12++;
                }
            }
        }
        if (childCount == 1 && i11 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i15 = (i10 >> 1) - (measuredWidth2 >> 1);
            int i16 = i8 - (measuredHeight2 >> 1);
            childAt2.layout(i15, i16, measuredWidth2 + i15, measuredHeight2 + i16);
            return;
        }
        int i17 = i12 - (i11 ^ 1);
        if (i17 > 0) {
            i6 = paddingRight / i17;
            i5 = 0;
        } else {
            i5 = 0;
            i6 = 0;
        }
        int max = Math.max(i5, i6);
        if (z2) {
            int width2 = getWidth() - getPaddingRight();
            while (i5 < childCount) {
                View childAt3 = getChildAt(i5);
                C11841sv r4 = (C11841sv) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !r4.A04) {
                    int i18 = width2 - r4.rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i19 = i8 - (measuredHeight3 >> 1);
                    childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                    width2 = i18 - ((measuredWidth3 + r4.leftMargin) + max);
                }
                i5++;
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        while (i5 < childCount) {
            View childAt4 = getChildAt(i5);
            C11841sv r42 = (C11841sv) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !r42.A04) {
                int i20 = paddingLeft + r42.leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i8 - (measuredHeight4 >> 1);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft = i20 + measuredWidth4 + r42.rightMargin + max;
            }
            i5++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01e3, code lost:
        if (r24 != 1) goto L_0x01e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b9, code lost:
        if ((!android.text.TextUtils.isEmpty(((X.C10931qF) r13).getText())) == false) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f0, code lost:
        if ((!android.text.TextUtils.isEmpty(r9.getText())) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0161, code lost:
        if (r24 != 2) goto L_0x0163;
     */
    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r32, int r33) {
        /*
        // Method dump skipped, instructions count: 732
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.onMeasure(int, int):void");
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setExpandedActionViewsExclusive(boolean z) {
        this.A04.A08 = z;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setMenuCallbacks(AbstractC11941tc r1, AnonymousClass1tQ r2) {
        this.A0A = r1;
        this.A00 = r2;
    }

    public void setPopupTheme(@StyleRes int i) {
        Context contextThemeWrapper;
        if (this.A08 != i) {
            this.A08 = i;
            if (i == 0) {
                contextThemeWrapper = getContext();
            } else {
                contextThemeWrapper = new ContextThemeWrapper(getContext(), i);
            }
            this.A09 = contextThemeWrapper;
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setPresenter(C11591sO r2) {
        this.A04 = r2;
        ((AnonymousClass1sT) r2).A05 = this;
        A5h(((AnonymousClass1sT) r2).A03);
    }

    @Nullable
    public Drawable getOverflowIcon() {
        getMenu();
        C11591sO r1 = this.A04;
        C11871sz r0 = r1.A06;
        if (r0 != null) {
            return r0.getDrawable();
        }
        if (r1.A09) {
            return r1.A02;
        }
        return null;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C11591sO r1 = this.A04;
        if (r1 != null) {
            r1.AAw(false);
            if (this.A04.A04()) {
                this.A04.A03();
                this.A04.A05();
            }
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C11591sO r0 = this.A04;
        if (r0 != null) {
            r0.A03();
            C11781sp r02 = r0.A03;
            if (r02 != null) {
                r02.A03();
            }
        }
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        getMenu();
        C11591sO r1 = this.A04;
        C11871sz r0 = r1.A06;
        if (r0 != null) {
            r0.setImageDrawable(drawable);
            return;
        }
        r1.A09 = true;
        r1.A02 = drawable;
    }

    @Override // X.AnonymousClass1td
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void A5h(C11581sN r1) {
        this.A03 = r1;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C11841sv;
    }

    public void setOnMenuItemClickListener(AbstractC12021tm r1) {
        this.A05 = r1;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setOverflowReserved(boolean z) {
        this.A06 = z;
    }

    public ActionMenuView(@NonNull Context context) {
        this(context, null);
    }

    public ActionMenuView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBaselineAligned = false;
        float f = context.getResources().getDisplayMetrics().density;
        this.A02 = (int) (56.0f * f);
        this.A01 = (int) (f * 4.0f);
        this.A09 = context;
        this.A08 = 0;
    }
}
