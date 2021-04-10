package androidx.appcompat.widget;

import X.AbstractC06001Eq;
import X.AbstractC11941tc;
import X.AbstractC12021tm;
import X.AbstractC12031tn;
import X.AnonymousClass02C;
import X.AnonymousClass07f;
import X.AnonymousClass1Cz;
import X.AnonymousClass1pW;
import X.AnonymousClass1sG;
import X.AnonymousClass1sI;
import X.AnonymousClass1sS;
import X.AnonymousClass1tL;
import X.AnonymousClass1tM;
import X.AnonymousClass1tQ;
import X.AnonymousClass1tV;
import X.C05941Dt;
import X.C10901qA;
import X.C10931qF;
import X.C11011qQ;
import X.C11021qR;
import X.C11081qa;
import X.C11571sK;
import X.C11581sN;
import X.C11591sO;
import X.C11961tf;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.customview.view.AbsSavedState;
import com.oculus.socialplatform.R;
import java.util.ArrayList;

public class Toolbar extends ViewGroup {
    public int A00;
    public View A01;
    public ImageButton A02;
    public int A03;
    public int A04;
    public int A05;
    public ImageView A06;
    public TextView A07;
    public TextView A08;
    public ActionMenuView A09;
    public AnonymousClass1tM A0A;
    public AnonymousClass1sS A0B;
    public AbstractC12031tn A0C;
    public CharSequence A0D;
    public CharSequence A0E;
    public boolean A0F;
    public int A0G;
    public int A0H;
    public int A0I;
    public int A0J;
    public int A0K;
    public int A0L;
    public int A0M;
    public int A0N;
    public Context A0O;
    public ColorStateList A0P;
    public ColorStateList A0Q;
    public Drawable A0R;
    public ImageButton A0S;
    public AnonymousClass1tQ A0T;
    public AbstractC11941tc A0U;
    public C11591sO A0V;
    public AnonymousClass1sI A0W;
    public CharSequence A0X;
    public boolean A0Y;
    public boolean A0Z;
    public final Runnable A0a;
    public final ArrayList<View> A0b;
    public final AbstractC12021tm A0c;
    public final ArrayList<View> A0d;
    public final int[] A0e;

    private void A09(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + 0, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i4 >= 0) {
            if (mode != 0) {
                i4 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i4);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1Cz();
        public int A00;
        public boolean A01;

        @Override // androidx.customview.view.AbsSavedState
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.A00);
            parcel.writeInt(this.A01 ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.A00 = parcel.readInt();
            this.A01 = parcel.readInt() != 0;
        }
    }

    public static final AnonymousClass1sG A05(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof AnonymousClass1sG) {
            return new AnonymousClass1sG((AnonymousClass1sG) layoutParams);
        }
        if (layoutParams instanceof C05941Dt) {
            return new AnonymousClass1sG((C05941Dt) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new AnonymousClass1sG((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new AnonymousClass1sG(layoutParams);
    }

    private void A07() {
        if (this.A09 == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.A09 = actionMenuView;
            actionMenuView.setPopupTheme(this.A0J);
            ActionMenuView actionMenuView2 = this.A09;
            actionMenuView2.A05 = this.A0c;
            actionMenuView2.setMenuCallbacks(this.A0U, this.A0T);
            AnonymousClass1sG r2 = new AnonymousClass1sG();
            ((C05941Dt) r2).A00 = 8388613 | (this.A00 & 112);
            this.A09.setLayoutParams(r2);
            A0A(this.A09, false);
        }
    }

    private void A08() {
        if (this.A0S == null) {
            this.A0S = new C11021qR(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            AnonymousClass1sG r2 = new AnonymousClass1sG();
            ((C05941Dt) r2).A00 = 8388611 | (this.A00 & 112);
            this.A0S.setLayoutParams(r2);
        }
    }

    private boolean A0D(View view) {
        if (view == null || view.getParent() != this || view.getVisibility() == 8) {
            return false;
        }
        return true;
    }

    public final void A0E() {
        if (this.A02 == null) {
            C11021qR r1 = new C11021qR(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.A02 = r1;
            r1.setImageDrawable(this.A0R);
            this.A02.setContentDescription(this.A0X);
            AnonymousClass1sG r2 = new AnonymousClass1sG();
            ((C05941Dt) r2).A00 = 8388611 | (this.A00 & 112);
            r2.A00 = 2;
            this.A02.setLayoutParams(r2);
            this.A02.setOnClickListener(new AnonymousClass1tL(this));
        }
    }

    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new AnonymousClass1sG();
    }

    @Nullable
    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.A02;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    @Nullable
    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.A02;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        AnonymousClass1tM r1 = this.A0A;
        if (r1 == null) {
            return 0;
        }
        if (r1.A07) {
            return r1.A03;
        }
        return r1.A04;
    }

    public int getContentInsetEndWithActions() {
        int i = this.A0G;
        if (i == Integer.MIN_VALUE) {
            return getContentInsetEnd();
        }
        return i;
    }

    public int getContentInsetLeft() {
        AnonymousClass1tM r0 = this.A0A;
        if (r0 != null) {
            return r0.A03;
        }
        return 0;
    }

    public int getContentInsetRight() {
        AnonymousClass1tM r0 = this.A0A;
        if (r0 != null) {
            return r0.A04;
        }
        return 0;
    }

    public int getContentInsetStart() {
        AnonymousClass1tM r1 = this.A0A;
        if (r1 == null) {
            return 0;
        }
        if (r1.A07) {
            return r1.A04;
        }
        return r1.A03;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.A0H;
        if (i == Integer.MIN_VALUE) {
            return getContentInsetStart();
        }
        return i;
    }

    public int getCurrentContentInsetEnd() {
        C11581sN r0;
        ActionMenuView actionMenuView = this.A09;
        if (actionMenuView == null || (r0 = actionMenuView.A03) == null || !r0.hasVisibleItems()) {
            return getContentInsetEnd();
        }
        return Math.max(getContentInsetEnd(), Math.max(this.A0G, 0));
    }

    public Drawable getLogo() {
        ImageView imageView = this.A06;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.A06;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    @Nullable
    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.A0S;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    @Nullable
    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.A0S;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public C11591sO getOuterActionMenuPresenter() {
        return this.A0V;
    }

    public Context getPopupContext() {
        return this.A0O;
    }

    public int getPopupTheme() {
        return this.A0J;
    }

    public CharSequence getSubtitle() {
        return this.A0D;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.TESTS})
    public final TextView getSubtitleTextView() {
        return this.A07;
    }

    public CharSequence getTitle() {
        return this.A0E;
    }

    public int getTitleMarginBottom() {
        return this.A0K;
    }

    public int getTitleMarginEnd() {
        return this.A0L;
    }

    public int getTitleMarginStart() {
        return this.A0M;
    }

    public int getTitleMarginTop() {
        return this.A0N;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.TESTS})
    public final TextView getTitleTextView() {
        return this.A08;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public AbstractC06001Eq getWrapper() {
        AnonymousClass1sI r1 = this.A0W;
        if (r1 != null) {
            return r1;
        }
        AnonymousClass1sI r12 = new AnonymousClass1sI(this, true);
        this.A0W = r12;
        return r12;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x028b A[LOOP:1: B:103:0x0289->B:104:0x028b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02a8 A[LOOP:2: B:106:0x02a6->B:107:0x02a8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x02f1 A[LOOP:3: B:114:0x02ef->B:115:0x02f1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a4 A[LOOP:0: B:68:0x01a2->B:69:0x01a4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0246  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r22, int r23, int r24, int r25, int r26) {
        /*
        // Method dump skipped, instructions count: 770
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int[] iArr = this.A0e;
        boolean z = true;
        if (getLayoutDirection() != 1) {
            z = false;
        }
        char c = 0;
        char c2 = 1;
        if (z) {
            c = 1;
            c2 = 0;
        }
        if (A0D(this.A0S)) {
            A09(this.A0S, i, 0, i2, this.A0I);
            int measuredWidth = this.A0S.getMeasuredWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.A0S.getLayoutParams();
            i3 = measuredWidth + marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
            i4 = Math.max(0, this.A0S.getMeasuredHeight() + A00(this.A0S));
            i5 = View.combineMeasuredStates(0, this.A0S.getMeasuredState());
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        if (A0D(this.A02)) {
            A09(this.A02, i, 0, i2, this.A0I);
            int measuredWidth2 = this.A02.getMeasuredWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.A02.getLayoutParams();
            i3 = measuredWidth2 + marginLayoutParams2.getMarginStart() + marginLayoutParams2.getMarginEnd();
            i4 = Math.max(i4, this.A02.getMeasuredHeight() + A00(this.A02));
            i5 = View.combineMeasuredStates(i5, this.A02.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = 0 + Math.max(currentContentInsetStart, i3);
        iArr[c] = Math.max(0, currentContentInsetStart - i3);
        if (A0D(this.A09)) {
            A09(this.A09, i, max, i2, this.A0I);
            int measuredWidth3 = this.A09.getMeasuredWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.A09.getLayoutParams();
            i6 = measuredWidth3 + marginLayoutParams3.getMarginStart() + marginLayoutParams3.getMarginEnd();
            i4 = Math.max(i4, this.A09.getMeasuredHeight() + A00(this.A09));
            i5 = View.combineMeasuredStates(i5, this.A09.getMeasuredState());
        } else {
            i6 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i6);
        iArr[c2] = Math.max(0, currentContentInsetEnd - i6);
        if (A0D(this.A01)) {
            max2 += A02(this.A01, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.A01.getMeasuredHeight() + A00(this.A01));
            i5 = View.combineMeasuredStates(i5, this.A01.getMeasuredState());
        }
        if (A0D(this.A06)) {
            max2 += A02(this.A06, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.A06.getMeasuredHeight() + A00(this.A06));
            i5 = View.combineMeasuredStates(i5, this.A06.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (((AnonymousClass1sG) childAt.getLayoutParams()).A00 == 0 && A0D(childAt)) {
                max2 += A02(childAt, i, max2, i2, 0, iArr);
                i4 = Math.max(i4, childAt.getMeasuredHeight() + A00(childAt));
                i5 = View.combineMeasuredStates(i5, childAt.getMeasuredState());
            }
        }
        int i10 = this.A0N + this.A0K;
        int i11 = this.A0M + this.A0L;
        if (A0D(this.A08)) {
            A02(this.A08, i, max2 + i11, i2, i10, iArr);
            int measuredWidth4 = this.A08.getMeasuredWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) this.A08.getLayoutParams();
            i7 = measuredWidth4 + marginLayoutParams4.getMarginStart() + marginLayoutParams4.getMarginEnd();
            i8 = this.A08.getMeasuredHeight() + A00(this.A08);
            i5 = View.combineMeasuredStates(i5, this.A08.getMeasuredState());
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (A0D(this.A07)) {
            i7 = Math.max(i7, A02(this.A07, i, max2 + i11, i2, i8 + i10, iArr));
            i8 += this.A07.getMeasuredHeight() + A00(this.A07);
            i5 = View.combineMeasuredStates(i5, this.A07.getMeasuredState());
        }
        int max3 = Math.max(i4, i8);
        int paddingLeft = max2 + i7 + getPaddingLeft() + getPaddingRight();
        int paddingTop = max3 + getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, -16777216 & i5);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, i5 << 16);
        if (this.A0F) {
            int childCount2 = getChildCount();
            int i12 = 0;
            while (true) {
                if (i12 >= childCount2) {
                    resolveSizeAndState2 = 0;
                    break;
                }
                View childAt2 = getChildAt(i12);
                if (A0D(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                    break;
                }
                i12++;
            }
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        C11581sN r2;
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(((AbsSavedState) savedState).A00);
        ActionMenuView actionMenuView = this.A09;
        if (actionMenuView != null) {
            r2 = actionMenuView.A03;
        } else {
            r2 = null;
        }
        int i = savedState.A00;
        if (!(i == 0 || this.A0B == null || r2 == null || (findItem = r2.findItem(i)) == null)) {
            findItem.expandActionView();
        }
        if (savedState.A01) {
            Runnable runnable = this.A0a;
            removeCallbacks(runnable);
            post(runnable);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setCollapsible(boolean z) {
        this.A0F = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.A0G) {
            this.A0G = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.A0H) {
            this.A0H = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setMenu(C11581sN r5, C11591sO r6) {
        if (r5 != null || this.A09 != null) {
            A07();
            C11581sN r1 = this.A09.A03;
            if (r1 != r5) {
                if (r1 != null) {
                    r1.A0D(this.A0V);
                    r1.A0D(this.A0B);
                }
                if (this.A0B == null) {
                    this.A0B = new AnonymousClass1sS(this);
                }
                r6.A08 = true;
                if (r5 != null) {
                    r5.A0E(r6, this.A0O);
                    r5.A0E(this.A0B, this.A0O);
                } else {
                    r6.A5e(this.A0O, null);
                    this.A0B.A5e(this.A0O, null);
                    r6.AAw(true);
                    this.A0B.AAw(true);
                }
                this.A09.setPopupTheme(this.A0J);
                this.A09.setPresenter(r6);
                this.A0V = r6;
            }
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setMenuCallbacks(AbstractC11941tc r2, AnonymousClass1tQ r3) {
        this.A0U = r2;
        this.A0T = r3;
        ActionMenuView actionMenuView = this.A09;
        if (actionMenuView != null) {
            actionMenuView.setMenuCallbacks(r2, r3);
        }
    }

    public void setPopupTheme(@StyleRes int i) {
        Context contextThemeWrapper;
        if (this.A0J != i) {
            this.A0J = i;
            if (i == 0) {
                contextThemeWrapper = getContext();
            } else {
                contextThemeWrapper = new ContextThemeWrapper(getContext(), i);
            }
            this.A0O = contextThemeWrapper;
        }
    }

    public void setTitleMarginBottom(int i) {
        this.A0K = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.A0L = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.A0M = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.A0N = i;
        requestLayout();
    }

    public static int A00(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r1 != 80) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int A01(android.view.View r9, int r10) {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.A01(android.view.View, int):int");
    }

    private int A02(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + max + i2, marginLayoutParams.width), getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private int A03(View view, int i, int[] iArr, int i2) {
        AnonymousClass1sG r4 = (AnonymousClass1sG) view.getLayoutParams();
        int i3 = r4.leftMargin - iArr[0];
        int max = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int A012 = A01(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, A012, max + measuredWidth, view.getMeasuredHeight() + A012);
        return max + measuredWidth + r4.rightMargin;
    }

    private int A04(View view, int i, int[] iArr, int i2) {
        AnonymousClass1sG r4 = (AnonymousClass1sG) view.getLayoutParams();
        int i3 = r4.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int A012 = A01(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, A012, max, view.getMeasuredHeight() + A012);
        return max - (measuredWidth + r4.leftMargin);
    }

    private void A06() {
        A07();
        ActionMenuView actionMenuView = this.A09;
        if (actionMenuView.A03 == null) {
            C11581sN r3 = (C11581sN) actionMenuView.getMenu();
            AnonymousClass1sS r2 = this.A0B;
            if (r2 == null) {
                r2 = new AnonymousClass1sS(this);
                this.A0B = r2;
            }
            this.A09.setExpandedActionViewsExclusive(true);
            r3.A0E(r2, this.A0O);
        }
    }

    private void A0A(View view, boolean z) {
        AnonymousClass1sG r1;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            r1 = new AnonymousClass1sG();
        } else if (!checkLayoutParams(layoutParams)) {
            r1 = A05(layoutParams);
        } else {
            r1 = (AnonymousClass1sG) layoutParams;
        }
        r1.A00 = 1;
        if (!z || this.A01 == null) {
            addView(view, r1);
            return;
        }
        view.setLayoutParams(r1);
        this.A0b.add(view);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x004e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0085 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0B(java.util.List<android.view.View> r10, int r11) {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.A0B(java.util.List, int):void");
    }

    private boolean A0C(View view) {
        if (view.getParent() == this || this.A0b.contains(view)) {
            return true;
        }
        return false;
    }

    private MenuInflater getMenuInflater() {
        return new C11571sK(getContext());
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!super.checkLayoutParams(layoutParams) || !(layoutParams instanceof AnonymousClass1sG)) {
            return false;
        }
        return true;
    }

    public int getCurrentContentInsetLeft() {
        if (getLayoutDirection() == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (getLayoutDirection() == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.A0H, 0));
        }
        return getContentInsetStart();
    }

    public Menu getMenu() {
        A06();
        return this.A09.getMenu();
    }

    @Nullable
    public Drawable getOverflowIcon() {
        A06();
        return this.A09.getOverflowIcon();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.A0a);
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.A0Y = false;
        }
        if (!this.A0Y) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9) {
                if (!onHoverEvent) {
                    this.A0Y = true;
                }
                return true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.A0Y = false;
            return true;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        if (r0 != Integer.MIN_VALUE) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onRtlPropertiesChanged(int r5) {
        /*
            r4 = this;
            super.onRtlPropertiesChanged(r5)
            X.1tM r3 = r4.A0A
            if (r3 != 0) goto L_0x000e
            X.1tM r3 = new X.1tM
            r3.<init>()
            r4.A0A = r3
        L_0x000e:
            r2 = 1
            if (r5 == r2) goto L_0x0012
            r2 = 0
        L_0x0012:
            boolean r0 = r3.A07
            if (r2 == r0) goto L_0x002e
            r3.A07 = r2
            boolean r0 = r3.A06
            if (r0 == 0) goto L_0x003a
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == 0) goto L_0x002f
            int r0 = r3.A00
            if (r0 != r1) goto L_0x0026
            int r0 = r3.A01
        L_0x0026:
            r3.A03 = r0
            int r0 = r3.A05
        L_0x002a:
            if (r0 == r1) goto L_0x003e
        L_0x002c:
            r3.A04 = r0
        L_0x002e:
            return
        L_0x002f:
            int r0 = r3.A05
            if (r0 != r1) goto L_0x0035
            int r0 = r3.A01
        L_0x0035:
            r3.A03 = r0
            int r0 = r3.A00
            goto L_0x002a
        L_0x003a:
            int r0 = r3.A01
            r3.A03 = r0
        L_0x003e:
            int r0 = r3.A02
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onRtlPropertiesChanged(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        if (r1 == false) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable onSaveInstanceState() {
        /*
            r3 = this;
            android.os.Parcelable r0 = super.onSaveInstanceState()
            androidx.appcompat.widget.Toolbar$SavedState r2 = new androidx.appcompat.widget.Toolbar$SavedState
            r2.<init>(r0)
            X.1sS r0 = r3.A0B
            if (r0 == 0) goto L_0x0017
            X.1sP r0 = r0.A01
            if (r0 == 0) goto L_0x0017
            int r0 = r0.getItemId()
            r2.A00 = r0
        L_0x0017:
            androidx.appcompat.widget.ActionMenuView r0 = r3.A09
            if (r0 == 0) goto L_0x0026
            X.1sO r0 = r0.A04
            if (r0 == 0) goto L_0x0026
            boolean r1 = r0.A04()
            r0 = 1
            if (r1 != 0) goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            r2.A01 = r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onSaveInstanceState():android.os.Parcelable");
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.A0Z = false;
        }
        if (!this.A0Z) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0) {
                if (!onTouchEvent) {
                    this.A0Z = true;
                }
                return true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.A0Z = false;
            return true;
        }
        return true;
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        A08();
        this.A0S.setOnClickListener(onClickListener);
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        A06();
        this.A09.setOverflowIcon(drawable);
    }

    public void setOnMenuItemClickListener(AbstractC12031tn r1) {
        this.A0C = r1;
    }

    public Toolbar(@NonNull Context context) {
        this(context, null);
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A03 = 8388627;
        this.A0d = new ArrayList<>();
        this.A0b = new ArrayList<>();
        this.A0e = new int[2];
        this.A0c = new C11961tf(this);
        this.A0a = new AnonymousClass1tV(this);
        Context context2 = getContext();
        int[] iArr = C11081qa.A0N;
        C10901qA A002 = C10901qA.A00(context2, attributeSet, iArr, i, 0);
        TypedArray typedArray = A002.A02;
        AnonymousClass07f.A04(this, context, iArr, attributeSet, typedArray, i);
        this.A05 = typedArray.getResourceId(28, 0);
        this.A04 = typedArray.getResourceId(19, 0);
        this.A03 = typedArray.getInteger(0, this.A03);
        this.A00 = typedArray.getInteger(2, 48);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(22, 0);
        dimensionPixelOffset = typedArray.hasValue(27) ? typedArray.getDimensionPixelOffset(27, dimensionPixelOffset) : dimensionPixelOffset;
        this.A0K = dimensionPixelOffset;
        this.A0N = dimensionPixelOffset;
        this.A0L = dimensionPixelOffset;
        this.A0M = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(25, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.A0M = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(24, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.A0L = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(26, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.A0N = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(23, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.A0K = dimensionPixelOffset5;
        }
        this.A0I = typedArray.getDimensionPixelSize(13, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(9, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(5, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(7, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(8, 0);
        AnonymousClass1tM r0 = this.A0A;
        if (r0 == null) {
            r0 = new AnonymousClass1tM();
            this.A0A = r0;
        }
        r0.A06 = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            r0.A01 = dimensionPixelSize;
            r0.A03 = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            r0.A02 = dimensionPixelSize2;
            r0.A04 = dimensionPixelSize2;
        }
        if (!(dimensionPixelOffset6 == Integer.MIN_VALUE && dimensionPixelOffset7 == Integer.MIN_VALUE)) {
            r0.A00(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.A0H = typedArray.getDimensionPixelOffset(10, Integer.MIN_VALUE);
        this.A0G = typedArray.getDimensionPixelOffset(6, Integer.MIN_VALUE);
        this.A0R = A002.A02(4);
        this.A0X = typedArray.getText(3);
        CharSequence text = typedArray.getText(21);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(18);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        getContext();
        this.A0O = context2;
        setPopupTheme(typedArray.getResourceId(17, 0));
        Drawable A022 = A002.A02(16);
        if (A022 != null) {
            setNavigationIcon(A022);
        }
        CharSequence text3 = typedArray.getText(15);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable A023 = A002.A02(11);
        if (A023 != null) {
            setLogo(A023);
        }
        CharSequence text4 = typedArray.getText(12);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(29)) {
            setTitleTextColor(A002.A01(29));
        }
        if (typedArray.hasValue(20)) {
            setSubtitleTextColor(A002.A01(20));
        }
        if (typedArray.hasValue(14)) {
            getMenuInflater().inflate(typedArray.getResourceId(14, 0), getMenu());
        }
        A002.A04();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new AnonymousClass1sG(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return A05(layoutParams);
    }

    public void setCollapseContentDescription(@StringRes int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseContentDescription(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            A0E();
        }
        ImageButton imageButton = this.A02;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(@DrawableRes int i) {
        setCollapseIcon(AnonymousClass1pW.A00(getContext(), i));
    }

    public void setCollapseIcon(@Nullable Drawable drawable) {
        if (drawable != null) {
            A0E();
            this.A02.setImageDrawable(drawable);
            return;
        }
        ImageButton imageButton = this.A02;
        if (imageButton != null) {
            imageButton.setImageDrawable(this.A0R);
        }
    }

    public void setTitleTextColor(@NonNull ColorStateList colorStateList) {
        this.A0Q = colorStateList;
        TextView textView = this.A08;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setLogo(@DrawableRes int i) {
        setLogo(AnonymousClass1pW.A00(getContext(), i));
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            ImageView imageView = this.A06;
            if (imageView == null) {
                imageView = new C11011qQ(getContext(), null, 0);
                this.A06 = imageView;
            }
            if (!A0C(imageView)) {
                A0A(this.A06, true);
            }
        } else {
            View view = this.A06;
            if (view != null && A0C(view)) {
                removeView(this.A06);
                this.A0b.remove(this.A06);
            }
        }
        ImageView imageView2 = this.A06;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(@StringRes int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.A06 == null) {
            this.A06 = new C11011qQ(getContext(), null, 0);
        }
        ImageView imageView = this.A06;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(@StringRes int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            A08();
        }
        ImageButton imageButton = this.A0S;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(@DrawableRes int i) {
        setNavigationIcon(AnonymousClass1pW.A00(getContext(), i));
    }

    public void setNavigationIcon(@Nullable Drawable drawable) {
        if (drawable != null) {
            A08();
            if (!A0C(this.A0S)) {
                A0A(this.A0S, true);
            }
        } else {
            ImageButton imageButton = this.A0S;
            if (imageButton != null && A0C(imageButton)) {
                removeView(this.A0S);
                this.A0b.remove(this.A0S);
            }
        }
        ImageButton imageButton2 = this.A0S;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(@StringRes int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.A07 == null) {
                Context context = getContext();
                C10931qF r0 = new C10931qF(context, null, 16842884);
                this.A07 = r0;
                r0.setSingleLine();
                this.A07.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.A04;
                if (i != 0) {
                    this.A07.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A0P;
                if (colorStateList != null) {
                    this.A07.setTextColor(colorStateList);
                }
            }
            if (!A0C(this.A07)) {
                A0A(this.A07, true);
            }
        } else {
            TextView textView = this.A07;
            if (textView != null && A0C(textView)) {
                removeView(this.A07);
                this.A0b.remove(this.A07);
            }
        }
        TextView textView2 = this.A07;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.A0D = charSequence;
    }

    public void setSubtitleTextColor(@ColorInt int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setSubtitleTextColor(@NonNull ColorStateList colorStateList) {
        this.A0P = colorStateList;
        TextView textView = this.A07;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(@StringRes int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.A08 == null) {
                Context context = getContext();
                C10931qF r0 = new C10931qF(context, null, 16842884);
                this.A08 = r0;
                r0.setSingleLine();
                this.A08.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.A05;
                if (i != 0) {
                    this.A08.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A0Q;
                if (colorStateList != null) {
                    this.A08.setTextColor(colorStateList);
                }
            }
            if (!A0C(this.A08)) {
                A0A(this.A08, true);
            }
        } else {
            TextView textView = this.A08;
            if (textView != null && A0C(textView)) {
                removeView(this.A08);
                this.A0b.remove(this.A08);
            }
        }
        TextView textView2 = this.A08;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.A0E = charSequence;
    }

    public void setTitleTextColor(@ColorInt int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }
}
