package androidx.appcompat.widget;

import X.AbstractC000503a;
import X.AbstractC000803d;
import X.AbstractC002203u;
import X.AbstractC002303v;
import X.AbstractC04310ee;
import X.AnonymousClass02D;
import X.AnonymousClass03V;
import X.AnonymousClass03W;
import X.AnonymousClass0Mm;
import X.AnonymousClass0eI;
import X.AnonymousClass0eK;
import X.C01850Mp;
import X.C01880Ms;
import X.C04200eJ;
import X.C04250eW;
import X.C04280eZ;
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

public class ActionMenuView extends LinearLayoutCompat implements AnonymousClass03W, AbstractC000803d {
    public AnonymousClass03V A00;
    public int A01;
    public int A02;
    public C04280eZ A03;
    public AnonymousClass0Mm A04;
    public AbstractC002303v A05;
    public boolean A06;
    public int A07;
    public int A08;
    public Context A09;
    public AbstractC000503a A0A;
    public boolean A0B;

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    private final boolean A00(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof AbstractC002203u)) {
            z = false | ((AbstractC002203u) childAt).A5j();
        }
        if (i <= 0 || !(childAt2 instanceof AbstractC002203u)) {
            return z;
        }
        return z | ((AbstractC002203u) childAt2).A5k();
    }

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getWindowAnimations() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        if (((X.C003604n) r1).A01 <= 0) goto L_0x0011;
     */
    /* renamed from: A01 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C04200eJ generateLayoutParams(android.view.ViewGroup.LayoutParams r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x001c
            boolean r0 = r3 instanceof X.C04200eJ
            if (r0 == 0) goto L_0x0016
            X.0eJ r3 = (X.C04200eJ) r3
            X.0eJ r1 = new X.0eJ
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
            X.0eJ r1 = new X.0eJ
            r1.<init>(r3)
            goto L_0x000d
        L_0x001c:
            X.0eJ r1 = new X.0eJ
            r1.<init>()
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.generateLayoutParams(android.view.ViewGroup$LayoutParams):X.0eJ");
    }

    @Override // X.AnonymousClass03W
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final boolean A5L(C04250eW r4) {
        return this.A03.A0K(r4, null, 0);
    }

    public Menu getMenu() {
        if (this.A03 == null) {
            Context context = getContext();
            C04280eZ r1 = new C04280eZ(context);
            this.A03 = r1;
            r1.A0C(new AnonymousClass0eI(this));
            AnonymousClass0Mm r2 = new AnonymousClass0Mm(context);
            this.A04 = r2;
            r2.A08 = true;
            r2.A09 = true;
            AbstractC000503a r0 = this.A0A;
            if (r0 == null) {
                r0 = new AnonymousClass0eK();
            }
            r2.A7m(r0);
            this.A03.A0E(r2, this.A09);
            AnonymousClass0Mm r02 = this.A04;
            ((AbstractC04310ee) r02).A05 = this;
            A5F(((AbstractC04310ee) r02).A03);
        }
        return this.A03;
    }

    public int getPopupTheme() {
        return this.A08;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01e3, code lost:
        if (r25 != 1) goto L_0x01e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b9, code lost:
        if ((!android.text.TextUtils.isEmpty(((X.C04090e2) r13).getText())) == false) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f0, code lost:
        if ((!android.text.TextUtils.isEmpty(r9.getText())) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0161, code lost:
        if (r25 != 2) goto L_0x0163;
     */
    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r33, int r34) {
        /*
        // Method dump skipped, instructions count: 732
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.onMeasure(int, int):void");
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setExpandedActionViewsExclusive(boolean z) {
        this.A04.A06 = z;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setMenuCallbacks(AbstractC000503a r1, AnonymousClass03V r2) {
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

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setPresenter(AnonymousClass0Mm r2) {
        this.A04 = r2;
        ((AbstractC04310ee) r2).A05 = this;
        A5F(((AbstractC04310ee) r2).A03);
    }

    @Nullable
    public Drawable getOverflowIcon() {
        getMenu();
        AnonymousClass0Mm r1 = this.A04;
        C01850Mp r0 = r1.A05;
        if (r0 != null) {
            return r0.getDrawable();
        }
        if (r1.A07) {
            return r1.A02;
        }
        return null;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        AnonymousClass0Mm r1 = this.A04;
        if (r1 != null) {
            r1.A8k(false);
            if (this.A04.A06()) {
                this.A04.A05();
                this.A04.A07();
            }
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnonymousClass0Mm r0 = this.A04;
        if (r0 != null) {
            r0.A05();
            C01880Ms r02 = r0.A03;
            if (r02 != null) {
                r02.A03();
            }
        }
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        getMenu();
        AnonymousClass0Mm r1 = this.A04;
        C01850Mp r0 = r1.A05;
        if (r0 != null) {
            r0.setImageDrawable(drawable);
            return;
        }
        r1.A07 = true;
        r1.A02 = drawable;
    }

    @Override // X.AbstractC000803d
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A5F(C04280eZ r1) {
        this.A03 = r1;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C04200eJ;
    }

    public void setOnMenuItemClickListener(AbstractC002303v r1) {
        this.A05 = r1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
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
                C04200eJ r11 = (C04200eJ) childAt.getLayoutParams();
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
                C04200eJ r4 = (C04200eJ) childAt3.getLayoutParams();
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
            C04200eJ r42 = (C04200eJ) childAt4.getLayoutParams();
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
}
