package androidx.core.widget;

import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass07J;
import X.AnonymousClass07K;
import X.AnonymousClass07f;
import X.AnonymousClass08Q;
import X.AnonymousClass08R;
import X.AnonymousClass0WA;
import X.AnonymousClass0vR;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.vrshell.panels.AndroidPanelLayer;

public class NestedScrollView extends FrameLayout implements AnonymousClass0WA {
    public static final AnonymousClass0vR A0Q = new AnonymousClass0vR();
    public static final int[] A0R = {16843130};
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public VelocityTracker A04;
    public EdgeEffect A05;
    public EdgeEffect A06;
    public OverScroller A07;
    public boolean A08;
    public float A09;
    public int A0A;
    public int A0B;
    public int A0C;
    public long A0D;
    public View A0E;
    public AnonymousClass08Q A0F;
    public SavedState A0G;
    public boolean A0H;
    public boolean A0I;
    public boolean A0J;
    public boolean A0K;
    public final Rect A0L;
    public final AnonymousClass07J A0M;
    public final AnonymousClass07K A0N;
    public final int[] A0O;
    public final int[] A0P;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass08R();
        public int A00;

        @NonNull
        public final String toString() {
            StringBuilder sb = new StringBuilder("HorizontalScrollView.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" scrollPosition=");
            sb.append(this.A00);
            sb.append("}");
            return sb.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.A00);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.A00 = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    private final boolean A08(int i) {
        int childCount;
        boolean z = false;
        if (i == 130) {
            z = true;
        }
        int height = getHeight();
        Rect rect = this.A0L;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            rect.bottom = bottom;
            rect.top = bottom - height;
        }
        return A09(i, rect.top, rect.bottom);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean A0A(int r13, int r14, int r15, int r16) {
        /*
            r12 = this;
            r8 = 0
            int r0 = r12.getOverScrollMode()
            r12.computeHorizontalScrollRange()
            r12.computeHorizontalScrollExtent()
            r4 = 0
            r3 = 1
            r12.computeVerticalScrollRange()
            r12.computeVerticalScrollExtent()
            int r14 = r14 + r8
            r2 = 0
            int r7 = r15 + r13
            int r6 = -r8
            r1 = r6
            int r0 = r8 + r16
            if (r14 <= r8) goto L_0x0045
            r6 = 0
        L_0x001e:
            r2 = 1
        L_0x001f:
            if (r7 <= r0) goto L_0x003f
            r7 = r0
        L_0x0022:
            r1 = 1
            X.07J r0 = r12.A0M
            boolean r0 = r0.A04(r3)
            if (r0 != 0) goto L_0x0036
            android.widget.OverScroller r5 = r12.A07
            int r11 = r12.getScrollRange()
            r9 = r8
            r10 = r8
            r5.springBack(r6, r7, r8, r9, r10, r11)
        L_0x0036:
            r12.onOverScrolled(r6, r7, r2, r1)
            if (r2 != 0) goto L_0x003d
            if (r1 == 0) goto L_0x003e
        L_0x003d:
            r4 = 1
        L_0x003e:
            return r4
        L_0x003f:
            if (r7 >= r1) goto L_0x0043
            r7 = r1
            goto L_0x0022
        L_0x0043:
            r1 = 0
            goto L_0x0036
        L_0x0045:
            if (r14 < r6) goto L_0x001e
            r6 = r14
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.A0A(int, int, int, int):boolean");
    }

    public static boolean A0C(View view, View view2) {
        if (view != view2) {
            ViewParent parent = view.getParent();
            if (!(parent instanceof ViewGroup) || !A0C((View) parent, view2)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC05480vn
    public final void A7M(@NonNull View view, int i, int i2, @NonNull int[] iArr, int i3) {
        this.A0M.A06(i, i2, iArr, null, i3);
    }

    @Override // X.AbstractC05480vn
    public final void A7N(@NonNull View view, int i, int i2, int i3, int i4, int i5) {
        A04(i4, i5, null);
    }

    @Override // X.AbstractC05480vn
    public final boolean A86(@NonNull View view, @NonNull View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.A0M.A06(i, i2, iArr, iArr2, 0);
    }

    public final boolean hasNestedScrollingParent() {
        return this.A0M.A04(0);
    }

    public final void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        A7M(view, i, i2, iArr, 0);
    }

    public final void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        A04(i4, 0, null);
    }

    public final void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        A7P(view, view2, i, 0);
    }

    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        View findNextFocusFromRect;
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        FocusFinder instance = FocusFinder.getInstance();
        if (rect == null) {
            findNextFocusFromRect = instance.findNextFocus(this, null, i);
        } else {
            findNextFocusFromRect = instance.findNextFocusFromRect(this, rect, i);
        }
        if (findNextFocusFromRect == null || (!A0B(findNextFocusFromRect, 0, getHeight()))) {
            return false;
        }
        return findNextFocusFromRect.requestFocus(i, rect);
    }

    public final boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        return A86(view, view2, i, 0);
    }

    public final void onStopNestedScroll(@NonNull View view) {
        A88(view, 0);
    }

    public final void requestLayout() {
        this.A0J = true;
        super.requestLayout();
    }

    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    public final boolean startNestedScroll(int i) {
        return this.A0M.A05(i, 0);
    }

    public final void stopNestedScroll() {
        this.A0M.A01(0);
    }

    private void A02(int i) {
        if (i == 0) {
            return;
        }
        if (this.A0K) {
            A06(this, 0, i, false);
        } else {
            scrollBy(0, i);
        }
    }

    public static void A06(NestedScrollView nestedScrollView, int i, int i2, boolean z) {
        if (nestedScrollView.getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - nestedScrollView.A0D > 250) {
                View childAt = nestedScrollView.getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int scrollY = nestedScrollView.getScrollY();
                OverScroller overScroller = nestedScrollView.A07;
                int scrollX = nestedScrollView.getScrollX();
                overScroller.startScroll(scrollX, scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingTop()) - nestedScrollView.getPaddingBottom())))) - scrollY, 250);
                if (z) {
                    nestedScrollView.A0M.A05(2, 1);
                } else {
                    nestedScrollView.A0M.A01(1);
                }
                nestedScrollView.A00 = nestedScrollView.getScrollY();
                nestedScrollView.postInvalidateOnAnimation();
            } else {
                if (!nestedScrollView.A07.isFinished()) {
                    nestedScrollView.A07.abortAnimation();
                    nestedScrollView.A0M.A01(1);
                }
                nestedScrollView.scrollBy(i, i2);
            }
            nestedScrollView.A0D = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        if (r2 >= r20) goto L_0x003e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean A09(int r18, int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.A09(int, int, int):boolean");
    }

    private boolean A0B(View view, int i, int i2) {
        Rect rect = this.A0L;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        if (rect.bottom + i < getScrollY() || rect.top - i > getScrollY() + i2) {
            return false;
        }
        return true;
    }

    private float getVerticalScrollFactorCompat() {
        float f = this.A09;
        if (f != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            return f;
        }
        TypedValue typedValue = new TypedValue();
        Context context = getContext();
        if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
            float dimension = typedValue.getDimension(context.getResources().getDisplayMetrics());
            this.A09 = dimension;
            return dimension;
        }
        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
    }

    public final boolean A0D(@NonNull KeyEvent keyEvent) {
        Rect rect = this.A0L;
        rect.setEmpty();
        boolean z = false;
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                z = true;
            }
        }
        int i = 130;
        if (!z) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            if (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) {
                return false;
            }
            return true;
        } else if (keyEvent.getAction() != 0) {
            return false;
        } else {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 19) {
                if (keyCode != 20) {
                    if (keyCode != 62) {
                        return false;
                    }
                    if (keyEvent.isShiftPressed()) {
                        i = 33;
                    }
                    boolean z2 = false;
                    if (i == 130) {
                        z2 = true;
                    }
                    int height = getHeight();
                    if (z2) {
                        rect.top = getScrollY() + height;
                        int childCount = getChildCount();
                        if (childCount > 0) {
                            View childAt2 = getChildAt(childCount - 1);
                            int bottom = childAt2.getBottom() + ((FrameLayout.LayoutParams) childAt2.getLayoutParams()).bottomMargin + getPaddingBottom();
                            if (rect.top + height > bottom) {
                                rect.top = bottom - height;
                            }
                        }
                    } else {
                        int scrollY = getScrollY() - height;
                        rect.top = scrollY;
                        if (scrollY < 0) {
                            rect.top = 0;
                        }
                    }
                    int i2 = rect.top;
                    int i3 = i2 + height;
                    rect.bottom = i3;
                    A09(i, i2, i3);
                    return false;
                } else if (!keyEvent.isAltPressed()) {
                    return A07(130);
                } else {
                    return A08(130);
                }
            } else if (!keyEvent.isAltPressed()) {
                return A07(33);
            } else {
                return A08(33);
            }
        }
    }

    @Override // X.AbstractC05480vn
    public final void A7P(@NonNull View view, @NonNull View view2, int i, int i2) {
        AnonymousClass07K r1 = this.A0N;
        if (i2 == 1) {
            r1.A00 = i;
        } else {
            r1.A01 = i;
        }
        this.A0M.A05(2, i2);
    }

    @Override // X.AbstractC05480vn
    public final void A88(@NonNull View view, int i) {
        AnonymousClass07K r2 = this.A0N;
        if (i == 1) {
            r2.A00 = 0;
        } else {
            r2.A01 = 0;
        }
        this.A0M.A01(i);
    }

    public final void computeScroll() {
        EdgeEffect edgeEffect;
        if (!this.A07.isFinished()) {
            this.A07.computeScrollOffset();
            int currY = this.A07.getCurrY();
            int i = currY - this.A00;
            this.A00 = currY;
            int[] iArr = this.A0O;
            iArr[1] = 0;
            AnonymousClass07J r4 = this.A0M;
            r4.A06(0, i, iArr, null, 1);
            int i2 = i - iArr[1];
            int scrollRange = getScrollRange();
            if (i2 != 0) {
                int scrollY = getScrollY();
                A0A(i2, getScrollX(), scrollY, scrollRange);
                int scrollY2 = getScrollY() - scrollY;
                int i3 = i2 - scrollY2;
                iArr[1] = 0;
                AnonymousClass07J.A00(r4, 0, scrollY2, 0, i3, this.A0P, 1, iArr);
                int i4 = i3 - iArr[1];
                if (i4 != 0) {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                        A01();
                        if (i4 < 0) {
                            if (this.A06.isFinished()) {
                                edgeEffect = this.A06;
                            }
                        } else if (this.A05.isFinished()) {
                            edgeEffect = this.A05;
                        }
                        edgeEffect.onAbsorb((int) this.A07.getCurrVelocity());
                    }
                    this.A07.abortAnimation();
                    r4.A01(1);
                }
            }
            if (!this.A07.isFinished()) {
                postInvalidateOnAnimation();
            } else {
                r4.A01(1);
            }
        }
    }

    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.A0M.A03(f, f2, z);
    }

    public final boolean dispatchNestedPreFling(float f, float f2) {
        return this.A0M.A02(f, f2);
    }

    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return AnonymousClass07J.A00(this.A0M, i, i2, i3, i4, iArr, 0, null);
    }

    public int getNestedScrollAxes() {
        AnonymousClass07K r0 = this.A0N;
        return r0.A01 | r0.A00;
    }

    public final boolean isNestedScrollingEnabled() {
        return this.A0M.A02;
    }

    public final boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, f2, true);
        A03((int) f2);
        return true;
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.A0G = savedState;
        requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01c9, code lost:
        if (getChildCount() > 0) goto L_0x01cb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r21) {
        /*
        // Method dump skipped, instructions count: 550
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void requestChildFocus(View view, View view2) {
        if (!this.A0J) {
            Rect rect = this.A0L;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int A002 = A00(rect);
            if (A002 != 0) {
                scrollBy(0, A002);
            }
        } else {
            this.A0E = view2;
        }
        super.requestChildFocus(view, view2);
    }

    public final void requestDisallowInterceptTouchEvent(boolean z) {
        VelocityTracker velocityTracker;
        if (z && (velocityTracker = this.A04) != null) {
            velocityTracker.recycle();
            this.A04 = null;
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void setFillViewport(boolean z) {
        if (z != this.A0H) {
            this.A0H = z;
            requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        AnonymousClass07J r1 = this.A0M;
        if (r1.A02) {
            r1.A04.stopNestedScroll();
        }
        r1.A02 = z;
    }

    private final int A00(Rect rect) {
        int i;
        int i2;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i3 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i4 = i3 - verticalFadingEdgeLength;
        if (rect.bottom >= childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin) {
            i4 = i3;
        }
        int i5 = rect.bottom;
        if (i5 > i4 && rect.top > scrollY) {
            if (rect.height() > height) {
                i2 = rect.top - scrollY;
            } else {
                i2 = rect.bottom - i4;
            }
            return Math.min(i2 + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i3);
        } else if (rect.top >= scrollY || i5 >= i4) {
            return 0;
        } else {
            if (rect.height() > height) {
                i = 0 - (i4 - rect.bottom);
            } else {
                i = 0 - (scrollY - rect.top);
            }
            return Math.max(i, -getScrollY());
        }
    }

    private void A01() {
        EdgeEffect edgeEffect;
        if (getOverScrollMode() == 2) {
            edgeEffect = null;
            this.A06 = null;
        } else if (this.A06 == null) {
            Context context = getContext();
            this.A06 = new EdgeEffect(context);
            edgeEffect = new EdgeEffect(context);
        } else {
            return;
        }
        this.A05 = edgeEffect;
    }

    private final void A03(int i) {
        if (getChildCount() > 0) {
            this.A07.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.A0M.A05(2, 1);
            this.A00 = getScrollY();
            postInvalidateOnAnimation();
        }
    }

    private void A04(int i, int i2, @Nullable int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        AnonymousClass07J.A00(this.A0M, 0, scrollY2, 0, i - scrollY2, null, i2, iArr);
    }

    private void A05(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.A0A) {
            int i = 0;
            if (actionIndex == 0) {
                i = 1;
            }
            this.A0B = (int) motionEvent.getY(i);
            this.A0A = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.A04;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private final boolean A07(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !A0B(findNextFocus, maxScrollAmount, getHeight())) {
            if (i == 33) {
                if (getScrollY() < maxScrollAmount) {
                    maxScrollAmount = getScrollY();
                }
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            A02(maxScrollAmount);
        } else {
            Rect rect = this.A0L;
            findNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(findNextFocus, rect);
            A02(A00(rect));
            findNextFocus.requestFocus(i);
        }
        if (findFocus == null || !findFocus.isFocused() || !(!A0B(findFocus, 0, getHeight()))) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        if (scrollY > max) {
            return bottom + (scrollY - max);
        }
        return bottom;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent) || A0D(keyEvent)) {
            return true;
        }
        return false;
    }

    public final void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.A06 != null) {
            int scrollY = getScrollY();
            int i2 = 0;
            if (!this.A06.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int min = Math.min(0, scrollY);
                if (getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    i = getPaddingLeft() + 0;
                } else {
                    i = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    min += getPaddingTop();
                }
                canvas.translate((float) i, (float) min);
                this.A06.setSize(width, height);
                if (this.A06.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(save);
            }
            if (!this.A05.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int max = Math.max(getScrollRange(), scrollY) + height2;
                if (getClipToPadding()) {
                    width2 -= getPaddingLeft() + getPaddingRight();
                    i2 = 0 + getPaddingLeft();
                }
                if (getClipToPadding()) {
                    height2 -= getPaddingTop() + getPaddingBottom();
                    max -= getPaddingBottom();
                }
                canvas.translate((float) (i2 - width2), (float) max);
                canvas.rotate(180.0f, (float) width2, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                this.A05.setSize(width2, height2);
                if (this.A05.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(save2);
            }
        }
    }

    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (((float) getHeight()) * 0.5f);
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public final void measureChild(View view, int i, int i2) {
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public final void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.A0I = false;
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.A08) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int verticalScrollFactorCompat = scrollY - ((int) (axisValue * getVerticalScrollFactorCompat()));
                if (verticalScrollFactorCompat < 0) {
                    verticalScrollFactorCompat = 0;
                } else if (verticalScrollFactorCompat > scrollRange) {
                    verticalScrollFactorCompat = scrollRange;
                }
                if (verticalScrollFactorCompat != scrollY) {
                    super.scrollTo(getScrollX(), verticalScrollFactorCompat);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.A08) {
            return true;
        }
        int i = action & MediaProviderUtils.JPEG_HEADER;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    int i2 = this.A0A;
                    if (i2 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i2);
                        if (findPointerIndex == -1) {
                            Log.e("NestedScrollView", AnonymousClass006.A04("Invalid pointerId=", i2, " in onInterceptTouchEvent"));
                        } else {
                            int y = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y - this.A0B) > this.A03 && (2 & getNestedScrollAxes()) == 0) {
                                this.A08 = true;
                                this.A0B = y;
                                VelocityTracker velocityTracker = this.A04;
                                if (velocityTracker == null) {
                                    velocityTracker = VelocityTracker.obtain();
                                    this.A04 = velocityTracker;
                                }
                                velocityTracker.addMovement(motionEvent);
                                this.A0C = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                } else if (i != 3) {
                    if (i == 6) {
                        A05(motionEvent);
                    }
                }
            }
            this.A08 = false;
            this.A0A = -1;
            VelocityTracker velocityTracker2 = this.A04;
            if (velocityTracker2 != null) {
                velocityTracker2.recycle();
                this.A04 = null;
            }
            if (this.A07.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            this.A0M.A01(0);
        } else {
            int y2 = (int) motionEvent.getY();
            int x = (int) motionEvent.getX();
            if (getChildCount() > 0) {
                int scrollY = getScrollY();
                View childAt = getChildAt(0);
                if (y2 >= childAt.getTop() - scrollY && y2 < childAt.getBottom() - scrollY && x >= childAt.getLeft() && x < childAt.getRight()) {
                    this.A0B = y2;
                    this.A0A = motionEvent.getPointerId(0);
                    VelocityTracker velocityTracker3 = this.A04;
                    if (velocityTracker3 == null) {
                        this.A04 = VelocityTracker.obtain();
                    } else {
                        velocityTracker3.clear();
                    }
                    this.A04.addMovement(motionEvent);
                    this.A07.computeScrollOffset();
                    this.A08 = !this.A07.isFinished();
                    this.A0M.A05(2, 0);
                }
            }
            this.A08 = false;
            VelocityTracker velocityTracker4 = this.A04;
            if (velocityTracker4 != null) {
                velocityTracker4.recycle();
                this.A04 = null;
            }
        }
        return this.A08;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if (A0C((android.view.View) r1, r4) != false) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onLayout(boolean, int, int, int, int):void");
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.A0H && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    public final boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.A00 = getScrollY();
        return savedState;
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && A0B(findFocus, 0, i4)) {
            Rect rect = this.A0L;
            findFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(findFocus, rect);
            A02(A00(rect));
        }
    }

    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int A002 = A00(rect);
        boolean z2 = false;
        if (A002 != 0) {
            z2 = true;
            if (z) {
                scrollBy(0, A002);
            } else {
                A06(this, 0, A002, false);
                return true;
            }
        }
        return z2;
    }

    public final void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width >= width2 || i < 0) {
                i = 0;
            } else if (width + i > width2) {
                i = width2 - width;
            }
            if (height >= height2 || i2 < 0) {
                i2 = 0;
            } else if (height + i2 > height2) {
                i2 = height2 - height;
            }
            if (i != getScrollX() || i2 != getScrollY()) {
                super.scrollTo(i, i2);
            }
        }
    }

    public void setOnScrollChangeListener(@Nullable AnonymousClass08Q r1) {
        this.A0F = r1;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.A0K = z;
    }

    public final void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    public final void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // X.AnonymousClass0WA
    public final void A7O(@NonNull View view, int i, int i2, int i3, int i4, int i5, @NonNull int[] iArr) {
        A04(i4, i5, iArr);
    }

    public NestedScrollView(@NonNull Context context) {
        this(context, null);
    }

    public NestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A0L = new Rect();
        this.A0J = true;
        this.A0I = false;
        this.A0E = null;
        this.A08 = false;
        this.A0K = true;
        this.A0A = -1;
        this.A0P = new int[2];
        this.A0O = new int[2];
        Context context2 = getContext();
        this.A07 = new OverScroller(context2);
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        getContext();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context2);
        this.A03 = viewConfiguration.getScaledTouchSlop();
        this.A02 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.A01 = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, A0R, i, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.A0N = new AnonymousClass07K();
        this.A0M = new AnonymousClass07J(this);
        setNestedScrollingEnabled(true);
        AnonymousClass07f.A07(this, A0Q);
    }

    public final void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
