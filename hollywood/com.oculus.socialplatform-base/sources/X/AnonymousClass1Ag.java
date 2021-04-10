package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* renamed from: X.1Ag  reason: invalid class name */
public abstract class AnonymousClass1Ag {
    public boolean mAutoMeasure = false;
    public AnonymousClass1Am mChildHelper;
    public int mHeight;
    public int mHeightMode;
    public AnonymousClass2fg mHorizontalBoundCheck;
    public final AnonymousClass2ff mHorizontalBoundCheckCallback;
    public boolean mIsAttachedToWindow = false;
    public boolean mItemPrefetchEnabled = true;
    public boolean mMeasurementCacheEnabled = true;
    public int mPrefetchMaxCountObserved;
    public boolean mPrefetchMaxObservedInInitialPrefetch;
    public RecyclerView mRecyclerView;
    public boolean mRequestedSimpleAnimations = false;
    @Nullable
    public AnonymousClass1An mSmoothScroller;
    public AnonymousClass2fg mVerticalBoundCheck;
    public final AnonymousClass2ff mVerticalBoundCheckCallback;
    public int mWidth;
    public int mWidthMode;

    public boolean canScrollHorizontally() {
        return false;
    }

    public boolean canScrollVertically() {
        return false;
    }

    public boolean checkLayoutParams(C05831Bi r2) {
        return r2 != null;
    }

    public void collectAdjacentPrefetchPositions(int i, int i2, AnonymousClass1As r3, AnonymousClass1CF r4) {
    }

    public void collectInitialPrefetchPositions(int i, AnonymousClass1CF r2) {
    }

    public int computeHorizontalScrollExtent(@NonNull AnonymousClass1As r2) {
        return 0;
    }

    public int computeHorizontalScrollOffset(@NonNull AnonymousClass1As r2) {
        return 0;
    }

    public int computeHorizontalScrollRange(@NonNull AnonymousClass1As r2) {
        return 0;
    }

    public int computeVerticalScrollExtent(@NonNull AnonymousClass1As r2) {
        return 0;
    }

    public int computeVerticalScrollOffset(@NonNull AnonymousClass1As r2) {
        return 0;
    }

    public int computeVerticalScrollRange(@NonNull AnonymousClass1As r2) {
        return 0;
    }

    public void dispatchAttachedToWindow(RecyclerView recyclerView) {
        this.mIsAttachedToWindow = true;
        onAttachedToWindow(recyclerView);
    }

    public void dispatchDetachedFromWindow(RecyclerView recyclerView, AnonymousClass1Af r3) {
        this.mIsAttachedToWindow = false;
        onDetachedFromWindow(recyclerView, r3);
    }

    public abstract C05831Bi generateDefaultLayoutParams();

    public int getBaseline() {
        return -1;
    }

    public int getColumnCountForAccessibility(@NonNull AnonymousClass1Af r2, @NonNull AnonymousClass1As r3) {
        return -1;
    }

    public int getRowCountForAccessibility(@NonNull AnonymousClass1Af r2, @NonNull AnonymousClass1As r3) {
        return -1;
    }

    public int getSelectionModeForAccessibility(@NonNull AnonymousClass1Af r2, @NonNull AnonymousClass1As r3) {
        return 0;
    }

    public boolean isLayoutHierarchical(@NonNull AnonymousClass1Af r2, @NonNull AnonymousClass1As r3) {
        return false;
    }

    public void onAdapterChanged(@Nullable AnonymousClass1Aj r1, @Nullable AnonymousClass1Aj r2) {
    }

    public boolean onAddFocusables(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> arrayList, int i, int i2) {
        return false;
    }

    @CallSuper
    public void onAttachedToWindow(RecyclerView recyclerView) {
    }

    @Deprecated
    public void onDetachedFromWindow(RecyclerView recyclerView) {
    }

    @Nullable
    public View onFocusSearchFailed(@NonNull View view, int i, @NonNull AnonymousClass1Af r4, @NonNull AnonymousClass1As r5) {
        return null;
    }

    public void onInitializeAccessibilityNodeInfoForItem(@NonNull AnonymousClass1Af r1, @NonNull AnonymousClass1As r2, @NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    @Nullable
    public View onInterceptFocusSearch(@NonNull View view, int i) {
        return null;
    }

    public void onItemsAdded(@NonNull RecyclerView recyclerView, int i, int i2) {
    }

    public void onItemsChanged(@NonNull RecyclerView recyclerView) {
    }

    public void onItemsMoved(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
    }

    public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i, int i2) {
    }

    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2) {
    }

    public void onLayoutCompleted(AnonymousClass1As r1) {
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Nullable
    public Parcelable onSaveInstanceState() {
        return null;
    }

    public void onScrollStateChanged(int i) {
    }

    public boolean performAccessibilityActionForItem(@NonNull AnonymousClass1Af r2, @NonNull AnonymousClass1As r3, @NonNull View view, int i, @Nullable Bundle bundle) {
        return false;
    }

    public void requestSimpleAnimationsInNextLayout() {
        this.mRequestedSimpleAnimations = true;
    }

    public int scrollHorizontallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        return 0;
    }

    public void scrollToPosition(int i) {
    }

    public int scrollVerticallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        return 0;
    }

    public boolean shouldMeasureTwice() {
        return false;
    }

    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    private void detachViewInternal(int i, @NonNull View view) {
        AnonymousClass1Am r2 = this.mChildHelper;
        int A00 = AnonymousClass1Am.A00(r2, i);
        r2.A00.A07(A00);
        r2.A01.A2b(A00);
    }

    public static AnonymousClass1BL getProperties(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        AnonymousClass1BL r3 = new AnonymousClass1BL();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass1BY.A00, i, i2);
        r3.A00 = obtainStyledAttributes.getInt(0, 1);
        r3.A01 = obtainStyledAttributes.getInt(10, 1);
        r3.A02 = obtainStyledAttributes.getBoolean(9, false);
        r3.A03 = obtainStyledAttributes.getBoolean(11, false);
        obtainStyledAttributes.recycle();
        return r3;
    }

    public void assertInLayoutOrScroll(String str) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.assertInLayoutOrScroll(str);
        }
    }

    public void assertNotInLayoutOrScroll(String str) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    public void calculateItemDecorationsForChild(@NonNull View view, @NonNull Rect rect) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.getItemDecorInsetsForChild(view));
        }
    }

    public void detachAndScrapView(@NonNull View view, @NonNull AnonymousClass1Af r3) {
        scrapOrRecycleView(r3, this.mChildHelper.A03(view), view);
    }

    public void detachView(@NonNull View view) {
        int A03 = this.mChildHelper.A03(view);
        if (A03 >= 0) {
            detachViewInternal(A03, view);
        }
    }

    public void endAnimation(View view) {
        AnonymousClass1Al r1 = this.mRecyclerView.mItemAnimator;
        if (r1 != null) {
            r1.A07(RecyclerView.getChildViewHolderInt(view));
        }
    }

    @Nullable
    public View findContainingItemView(@NonNull View view) {
        View findContainingItemView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.A02.contains(findContainingItemView)) {
            return null;
        }
        return findContainingItemView;
    }

    @Nullable
    public View getChildAt(int i) {
        AnonymousClass1Am r0 = this.mChildHelper;
        if (r0 != null) {
            return r0.A04(i);
        }
        return null;
    }

    public int getChildCount() {
        AnonymousClass1Am r0 = this.mChildHelper;
        if (r0 != null) {
            return r0.A02();
        }
        return 0;
    }

    public boolean getClipToPadding() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || !recyclerView.mClipToPadding) {
            return false;
        }
        return true;
    }

    @Nullable
    public View getFocusedChild() {
        View focusedChild;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.A02.contains(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    @Px
    public int getHeight() {
        return this.mHeight;
    }

    public int getHeightMode() {
        return this.mHeightMode;
    }

    public int getItemCount() {
        AnonymousClass1Aj r0;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (r0 = recyclerView.mAdapter) == null) {
            return 0;
        }
        return r0.getItemCount();
    }

    public int getLayoutDirection() {
        return this.mRecyclerView.getLayoutDirection();
    }

    @Px
    public int getMinimumHeight() {
        return this.mRecyclerView.getMinimumHeight();
    }

    @Px
    public int getMinimumWidth() {
        return this.mRecyclerView.getMinimumWidth();
    }

    @Px
    public int getPaddingBottom() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    @Px
    public int getPaddingEnd() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingEnd();
        }
        return 0;
    }

    @Px
    public int getPaddingLeft() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    @Px
    public int getPaddingRight() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    @Px
    public int getPaddingStart() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingStart();
        }
        return 0;
    }

    @Px
    public int getPaddingTop() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public void getTransformedBoundingBox(@NonNull View view, boolean z, @NonNull Rect rect) {
        Matrix matrix;
        if (z) {
            Rect rect2 = ((C05831Bi) view.getLayoutParams()).A03;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        } else {
            rect.set(0, 0, view.getWidth(), view.getHeight());
        }
        if (!(this.mRecyclerView == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
            RectF rectF = this.mRecyclerView.mTempRectF;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    @Px
    public int getWidth() {
        return this.mWidth;
    }

    public int getWidthMode() {
        return this.mWidthMode;
    }

    public boolean hasFocus() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || !recyclerView.hasFocus()) {
            return false;
        }
        return true;
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttachedToWindow;
    }

    public boolean isAutoMeasureEnabled() {
        return this.mAutoMeasure;
    }

    public boolean isFocused() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || !recyclerView.isFocused()) {
            return false;
        }
        return true;
    }

    public final boolean isItemPrefetchEnabled() {
        return this.mItemPrefetchEnabled;
    }

    public boolean isMeasurementCacheEnabled() {
        return this.mMeasurementCacheEnabled;
    }

    public boolean isSmoothScrolling() {
        AnonymousClass1An r0 = this.mSmoothScroller;
        if (r0 == null || !r0.A05) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r3.mVerticalBoundCheck.A01(r4) == false) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isViewPartiallyVisible(@androidx.annotation.NonNull android.view.View r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            X.2fg r0 = r3.mHorizontalBoundCheck
            boolean r0 = r0.A01(r4)
            r2 = 1
            if (r0 == 0) goto L_0x0012
            X.2fg r0 = r3.mVerticalBoundCheck
            boolean r1 = r0.A01(r4)
            r0 = 1
            if (r1 != 0) goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            if (r5 != 0) goto L_0x0016
            r0 = r0 ^ r2
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Ag.isViewPartiallyVisible(android.view.View, boolean, boolean):boolean");
    }

    public void offsetChildrenHorizontal(@Px int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenHorizontal(i);
        }
    }

    public void offsetChildrenVertical(@Px int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenVertical(i);
        }
    }

    public void onLayoutChildren(AnonymousClass1Af r3, AnonymousClass1As r4) {
        Log.e(RecyclerView.TAG, "You must override onLayoutChildren(Recycler recycler, State state) ");
    }

    public void onMeasure(@NonNull AnonymousClass1Af r2, @NonNull AnonymousClass1As r3, int i, int i2) {
        this.mRecyclerView.defaultOnMeasure(i, i2);
    }

    public void onSmoothScrollerStopped(AnonymousClass1An r2) {
        if (this.mSmoothScroller == r2) {
            this.mSmoothScroller = null;
        }
    }

    public void postOnAnimation(Runnable runnable) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.postOnAnimation(runnable);
        }
    }

    public void removeAndRecycleScrapInt(AnonymousClass1Af r8) {
        ArrayList<AnonymousClass1Ah> arrayList = r8.A05;
        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {
            View view = arrayList.get(i).itemView;
            AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.setIsRecyclable(false);
                if (childViewHolderInt.isTmpDetached()) {
                    this.mRecyclerView.removeDetachedView(view, false);
                }
                AnonymousClass1Al r0 = this.mRecyclerView.mItemAnimator;
                if (r0 != null) {
                    r0.A07(childViewHolderInt);
                }
                childViewHolderInt.setIsRecyclable(true);
                AnonymousClass1Ah childViewHolderInt2 = RecyclerView.getChildViewHolderInt(view);
                childViewHolderInt2.mScrapContainer = null;
                childViewHolderInt2.mInChangeScrap = false;
                childViewHolderInt2.clearReturnedFromScrapFlag();
                r8.A09(childViewHolderInt2);
            }
        }
        arrayList.clear();
        ArrayList<AnonymousClass1Ah> arrayList2 = r8.A04;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.mRecyclerView.invalidate();
        }
    }

    public boolean removeCallbacks(Runnable runnable) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.removeCallbacks(runnable);
        }
        return false;
    }

    public void removeDetachedView(@NonNull View view) {
        this.mRecyclerView.removeDetachedView(view, false);
    }

    public void removeView(View view) {
        AnonymousClass1Am r3 = this.mChildHelper;
        AnonymousClass1B5 r2 = r3.A01;
        int A5a = r2.A5a(view);
        if (A5a >= 0) {
            if (r3.A00.A07(A5a)) {
                AnonymousClass1Am.A01(r3, view);
            }
            r2.A9E(A5a);
        }
    }

    public void requestLayout() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public final void setItemPrefetchEnabled(boolean z) {
        if (z != this.mItemPrefetchEnabled) {
            this.mItemPrefetchEnabled = z;
            this.mPrefetchMaxCountObserved = 0;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.mRecycler.A05();
            }
        }
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        int height;
        if (recyclerView == null) {
            this.mRecyclerView = null;
            this.mChildHelper = null;
            height = 0;
            this.mWidth = 0;
        } else {
            this.mRecyclerView = recyclerView;
            this.mChildHelper = recyclerView.mChildHelper;
            this.mWidth = recyclerView.getWidth();
            height = recyclerView.getHeight();
        }
        this.mHeight = height;
        this.mWidthMode = 1073741824;
        this.mHeightMode = 1073741824;
    }

    public boolean shouldReMeasureChild(View view, int i, int i2, C05831Bi r6) {
        if (!this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), i, r6.width) || !isMeasurementUpToDate(view.getMeasuredHeight(), i2, r6.height)) {
            return true;
        }
        return false;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, AnonymousClass1As r4, int i) {
        Log.e(RecyclerView.TAG, "You must override smoothScrollToPosition to support smooth scrolling");
    }

    public void startSmoothScroll(AnonymousClass1An r6) {
        AnonymousClass1An r1 = this.mSmoothScroller;
        if (!(r1 == null || r6 == r1 || !r1.A05)) {
            r1.A02();
        }
        this.mSmoothScroller = r6;
        RecyclerView recyclerView = this.mRecyclerView;
        AnonymousClass1Ae r12 = recyclerView.mViewFlinger;
        r12.A06.removeCallbacks(r12);
        r12.A03.abortAnimation();
        if (r6.A06) {
            String simpleName = r6.getClass().getSimpleName();
            Log.w(RecyclerView.TAG, AnonymousClass006.A0C("An instance of ", simpleName, " was started more than once. Each instance of", simpleName, " is intended to only be used once. You should create a new instance for each use."));
        }
        r6.A03 = recyclerView;
        r6.A02 = this;
        int i = r6.A00;
        if (i != -1) {
            recyclerView.mState.A09 = i;
            r6.A05 = true;
            r6.A04 = true;
            r6.A01 = recyclerView.mLayout.findViewByPosition(i);
            r6.A03.mViewFlinger.A00();
            r6.A06 = true;
            return;
        }
        throw new IllegalArgumentException("Invalid target position");
    }

    public void stopSmoothScroller() {
        AnonymousClass1An r0 = this.mSmoothScroller;
        if (r0 != null) {
            r0.A02();
        }
    }

    public AnonymousClass1Ag() {
        AnonymousClass1B7 r2 = new AnonymousClass1B7(this);
        this.mHorizontalBoundCheckCallback = r2;
        AnonymousClass1B8 r1 = new AnonymousClass1B8(this);
        this.mVerticalBoundCheckCallback = r1;
        this.mHorizontalBoundCheck = new AnonymousClass2fg(r2);
        this.mVerticalBoundCheck = new AnonymousClass2fg(r1);
    }

    private void addViewInt(View view, int i, boolean z) {
        int A00;
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (z || childViewHolderInt.isRemoved()) {
            C000502v<AnonymousClass1Ah, AnonymousClass1B9> r0 = this.mRecyclerView.mViewInfoStore.A01;
            AnonymousClass1B9 r1 = r0.get(childViewHolderInt);
            if (r1 == null) {
                r1 = AnonymousClass1B9.A00();
                r0.put(childViewHolderInt, r1);
            }
            r1.A00 |= 1;
        } else {
            this.mRecyclerView.mViewInfoStore.A01(childViewHolderInt);
        }
        C05831Bi r5 = (C05831Bi) view.getLayoutParams();
        if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.mChildHelper.A05(view, i, view.getLayoutParams(), false);
        } else if (view.getParent() == this.mRecyclerView) {
            int A03 = this.mChildHelper.A03(view);
            if (i == -1) {
                i = this.mChildHelper.A02();
            }
            if (A03 == -1) {
                throw new IllegalStateException(AnonymousClass006.A04("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:", this.mRecyclerView.indexOfChild(view), this.mRecyclerView.exceptionLabel()));
            } else if (A03 != i) {
                this.mRecyclerView.mLayout.moveView(A03, i);
            }
        } else {
            AnonymousClass1Am r2 = this.mChildHelper;
            if (i < 0) {
                A00 = r2.A01.A3V();
            } else {
                A00 = AnonymousClass1Am.A00(r2, i);
            }
            r2.A00.A05(A00, false);
            r2.A01.A1P(view, A00);
            r5.A02 = true;
            AnonymousClass1An r22 = this.mSmoothScroller;
            if (r22 != null && r22.A05 && r22.A03.getChildLayoutPosition(view) == r22.A00) {
                r22.A01 = view;
            }
        }
        if (r5.A00) {
            childViewHolderInt.itemView.invalidate();
            r5.A00 = false;
        }
    }

    public static int chooseSize(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(size, Math.max(i2, i3));
        }
        if (mode != 1073741824) {
            return Math.max(i2, i3);
        }
        return size;
    }

    private int[] getChildRectangleOnScreenScrollAmount(View view, Rect rect) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = this.mWidth - getPaddingRight();
        int paddingBottom = this.mHeight - getPaddingBottom();
        int left = (view.getLeft() + rect.left) - view.getScrollX();
        int top = (view.getTop() + rect.top) - view.getScrollY();
        int width = rect.width() + left;
        int height = rect.height() + top;
        int i = left - paddingLeft;
        int min = Math.min(0, i);
        int i2 = top - paddingTop;
        int min2 = Math.min(0, i2);
        int i3 = width - paddingRight;
        int max = Math.max(0, i3);
        int max2 = Math.max(0, height - paddingBottom);
        if (this.mRecyclerView.getLayoutDirection() != 1) {
            if (min == 0) {
                min = Math.min(i, max);
            }
            max = min;
        } else if (max == 0) {
            max = Math.max(min, i3);
        }
        if (min2 == 0) {
            min2 = Math.min(i2, max2);
        }
        return new int[]{max, min2};
    }

    private boolean isFocusedChildVisibleAfterScrolling(RecyclerView recyclerView, int i, int i2) {
        View focusedChild = recyclerView.getFocusedChild();
        if (focusedChild != null) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = this.mWidth - getPaddingRight();
            int paddingBottom = this.mHeight - getPaddingBottom();
            Rect rect = this.mRecyclerView.mTempRect;
            RecyclerView.getDecoratedBoundsWithMarginsInt(focusedChild, rect);
            if (rect.left - i >= paddingRight || rect.right - i <= paddingLeft || rect.top - i2 >= paddingBottom || rect.bottom - i2 <= paddingTop) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 <= 0 || i == i3) {
            if (mode != Integer.MIN_VALUE) {
                if (mode == 0) {
                    return true;
                }
                if (mode == 1073741824 && size == i) {
                    return true;
                }
                return false;
            } else if (size >= i) {
                return true;
            }
        }
        return false;
    }

    private void scrapOrRecycleView(AnonymousClass1Af r3, int i, View view) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.shouldIgnore()) {
            return;
        }
        if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.mRecyclerView.mAdapter.mHasStableIds) {
            detachViewAt(i);
            r3.A06(view);
            this.mRecyclerView.mViewInfoStore.A01(childViewHolderInt);
            return;
        }
        removeViewAt(i);
        r3.A09(childViewHolderInt);
    }

    public void detachAndScrapAttachedViews(@NonNull AnonymousClass1Af r3) {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                scrapOrRecycleView(r3, childCount, getChildAt(childCount));
            } else {
                return;
            }
        }
    }

    public void detachAndScrapViewAt(int i, @NonNull AnonymousClass1Af r3) {
        scrapOrRecycleView(r3, i, getChildAt(i));
    }

    public void detachViewAt(int i) {
        detachViewInternal(i, getChildAt(i));
    }

    @Nullable
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.A0A || !childViewHolderInt.isRemoved())) {
                return childAt;
            }
        }
        return null;
    }

    public int getBottomDecorationHeight(@NonNull View view) {
        return ((C05831Bi) view.getLayoutParams()).A03.bottom;
    }

    public int getDecoratedBottom(@NonNull View view) {
        return view.getBottom() + getBottomDecorationHeight(view);
    }

    public int getDecoratedLeft(@NonNull View view) {
        return view.getLeft() - getLeftDecorationWidth(view);
    }

    public int getDecoratedMeasuredHeight(@NonNull View view) {
        Rect rect = ((C05831Bi) view.getLayoutParams()).A03;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public int getDecoratedMeasuredWidth(@NonNull View view) {
        Rect rect = ((C05831Bi) view.getLayoutParams()).A03;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    public int getDecoratedRight(@NonNull View view) {
        return view.getRight() + getRightDecorationWidth(view);
    }

    public int getDecoratedTop(@NonNull View view) {
        return view.getTop() - getTopDecorationHeight(view);
    }

    public int getItemViewType(@NonNull View view) {
        return RecyclerView.getChildViewHolderInt(view).mItemViewType;
    }

    public int getLeftDecorationWidth(@NonNull View view) {
        return ((C05831Bi) view.getLayoutParams()).A03.left;
    }

    public int getPosition(@NonNull View view) {
        return ((C05831Bi) view.getLayoutParams()).A01.getLayoutPosition();
    }

    public int getRightDecorationWidth(@NonNull View view) {
        return ((C05831Bi) view.getLayoutParams()).A03.right;
    }

    public int getTopDecorationHeight(@NonNull View view) {
        return ((C05831Bi) view.getLayoutParams()).A03.top;
    }

    public boolean hasFlexibleChildInBothOrientations() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    public void ignoreView(@NonNull View view) {
        ViewParent parent = view.getParent();
        RecyclerView recyclerView = this.mRecyclerView;
        if (parent != recyclerView || recyclerView.indexOfChild(view) == -1) {
            throw new IllegalArgumentException(AnonymousClass006.A07("View should be fully attached to be ignored", this.mRecyclerView.exceptionLabel()));
        }
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.addFlags(128);
        this.mRecyclerView.mViewInfoStore.A02(childViewHolderInt);
    }

    public void layoutDecorated(@NonNull View view, int i, int i2, int i3, int i4) {
        Rect rect = ((C05831Bi) view.getLayoutParams()).A03;
        view.layout(i + rect.left, i2 + rect.top, i3 - rect.right, i4 - rect.bottom);
    }

    public void layoutDecoratedWithMargins(@NonNull View view, int i, int i2, int i3, int i4) {
        C05831Bi r2 = (C05831Bi) view.getLayoutParams();
        Rect rect = r2.A03;
        view.layout(i + rect.left + r2.leftMargin, i2 + rect.top + r2.topMargin, (i3 - rect.right) - r2.rightMargin, (i4 - rect.bottom) - r2.bottomMargin);
    }

    public void measureChild(@NonNull View view, int i, int i2) {
        C05831Bi r6 = (C05831Bi) view.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        int i3 = i + itemDecorInsetsForChild.left + itemDecorInsetsForChild.right;
        int i4 = i2 + itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom;
        int childMeasureSpec = getChildMeasureSpec(this.mWidth, this.mWidthMode, getPaddingLeft() + getPaddingRight() + i3, r6.width, canScrollHorizontally());
        int childMeasureSpec2 = getChildMeasureSpec(this.mHeight, this.mHeightMode, getPaddingTop() + getPaddingBottom() + i4, r6.height, canScrollVertically());
        if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, r6)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }

    public void measureChildWithMargins(@NonNull View view, int i, int i2) {
        C05831Bi r6 = (C05831Bi) view.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        int i3 = i + itemDecorInsetsForChild.left + itemDecorInsetsForChild.right;
        int i4 = i2 + itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom;
        int childMeasureSpec = getChildMeasureSpec(this.mWidth, this.mWidthMode, getPaddingLeft() + getPaddingRight() + r6.leftMargin + r6.rightMargin + i3, r6.width, canScrollHorizontally());
        int childMeasureSpec2 = getChildMeasureSpec(this.mHeight, this.mHeightMode, getPaddingTop() + getPaddingBottom() + r6.topMargin + r6.bottomMargin + i4, r6.height, canScrollVertically());
        if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, r6)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }

    public void moveView(int i, int i2) {
        View childAt = getChildAt(i);
        if (childAt != null) {
            detachViewAt(i);
            attachView(childAt, i2);
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A04("Cannot move a child from non-existing index:", i, this.mRecyclerView.toString()));
    }

    public void removeAllViews() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                AnonymousClass1Am r4 = this.mChildHelper;
                int A00 = AnonymousClass1Am.A00(r4, childCount);
                AnonymousClass1B5 r2 = r4.A01;
                View A3U = r2.A3U(A00);
                if (A3U != null) {
                    if (r4.A00.A07(A00)) {
                        AnonymousClass1Am.A01(r4, A3U);
                    }
                    r2.A9E(A00);
                }
            } else {
                return;
            }
        }
    }

    public void removeAndRecycleAllViews(@NonNull AnonymousClass1Af r3) {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                return;
            }
            if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                removeAndRecycleViewAt(childCount, r3);
            }
        }
    }

    public void removeAndRecycleView(@NonNull View view, @NonNull AnonymousClass1Af r2) {
        removeView(view);
        r2.A07(view);
    }

    public void removeAndRecycleViewAt(int i, @NonNull AnonymousClass1Af r3) {
        View childAt = getChildAt(i);
        removeViewAt(i);
        r3.A07(childAt);
    }

    public void removeViewAt(int i) {
        AnonymousClass1Am r4;
        int A00;
        AnonymousClass1B5 r2;
        View A3U;
        if (getChildAt(i) != null && (A3U = (r2 = r4.A01).A3U((A00 = AnonymousClass1Am.A00((r4 = this.mChildHelper), i)))) != null) {
            if (r4.A00.A07(A00)) {
                AnonymousClass1Am.A01(r4, A3U);
            }
            r2.A9E(A00);
        }
    }

    public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
        setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public void setMeasureSpecs(int i, int i2) {
        this.mWidth = View.MeasureSpec.getSize(i);
        this.mWidthMode = View.MeasureSpec.getMode(i);
        this.mHeight = View.MeasureSpec.getSize(i2);
        this.mHeightMode = View.MeasureSpec.getMode(i2);
    }

    public void setMeasuredDimensionFromChildren(int i, int i2) {
        int childCount = getChildCount();
        if (childCount == 0) {
            this.mRecyclerView.defaultOnMeasure(i, i2);
            return;
        }
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MIN_VALUE;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            Rect rect = this.mRecyclerView.mTempRect;
            RecyclerView.getDecoratedBoundsWithMarginsInt(childAt, rect);
            int i8 = rect.left;
            if (i8 < i3) {
                i3 = i8;
            }
            int i9 = rect.right;
            if (i9 > i5) {
                i5 = i9;
            }
            int i10 = rect.top;
            if (i10 < i4) {
                i4 = i10;
            }
            int i11 = rect.bottom;
            if (i11 > i6) {
                i6 = i11;
            }
        }
        this.mRecyclerView.mTempRect.set(i3, i4, i5, i6);
        setMeasuredDimension(this.mRecyclerView.mTempRect, i, i2);
    }

    public boolean shouldMeasureChild(View view, int i, int i2, C05831Bi r6) {
        if (view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), i, r6.width) || !isMeasurementUpToDate(view.getHeight(), i2, r6.height)) {
            return true;
        }
        return false;
    }

    public void stopIgnoringView(@NonNull View view) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.stopIgnoring();
        childViewHolderInt.resetInternal();
        childViewHolderInt.addFlags(4);
    }

    @Deprecated
    public void setAutoMeasureEnabled(boolean z) {
        this.mAutoMeasure = z;
    }

    public void setMeasurementCacheEnabled(boolean z) {
        this.mMeasurementCacheEnabled = z;
    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
        RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public static int getChildMeasureSpec(int i, int i2, int i3, int i4, boolean z) {
        int i5 = 0;
        int max = Math.max(0, i - i3);
        if (z) {
            if (i4 < 0) {
                if (i4 == -1) {
                    if (i2 != Integer.MIN_VALUE && (i2 == 0 || i2 != 1073741824)) {
                        i2 = 0;
                        max = 0;
                    }
                }
                max = 0;
                return View.MeasureSpec.makeMeasureSpec(max, i5);
            }
            max = i4;
            i5 = 1073741824;
            return View.MeasureSpec.makeMeasureSpec(max, i5);
        }
        if (i4 < 0) {
            if (i4 != -1) {
                if (i4 == -2) {
                    if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                        i5 = Integer.MIN_VALUE;
                    }
                    return View.MeasureSpec.makeMeasureSpec(max, i5);
                }
                max = 0;
                return View.MeasureSpec.makeMeasureSpec(max, i5);
            }
        }
        max = i4;
        i5 = 1073741824;
        return View.MeasureSpec.makeMeasureSpec(max, i5);
        i5 = i2;
        return View.MeasureSpec.makeMeasureSpec(max, i5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        if (r3 >= 0) goto L_0x000a;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getChildMeasureSpec(int r1, int r2, int r3, boolean r4) {
        /*
            int r1 = r1 - r2
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            if (r4 == 0) goto L_0x0012
            if (r3 < 0) goto L_0x001d
        L_0x000a:
            r1 = r3
        L_0x000b:
            r2 = 1073741824(0x40000000, float:2.0)
        L_0x000d:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r2)
            return r0
        L_0x0012:
            if (r3 >= 0) goto L_0x000a
            r0 = -1
            if (r3 == r0) goto L_0x000b
            r0 = -2
            if (r3 != r0) goto L_0x001d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x000d
        L_0x001d:
            r1 = 0
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Ag.getChildMeasureSpec(int, int, int, boolean):int");
    }

    public void addDisappearingView(View view) {
        addDisappearingView(view, -1);
    }

    public void addDisappearingView(View view, int i) {
        addViewInt(view, i, true);
    }

    public void addView(View view) {
        addView(view, -1);
    }

    public void addView(View view, int i) {
        addViewInt(view, i, false);
    }

    public void attachView(@NonNull View view) {
        attachView(view, -1);
    }

    public void attachView(@NonNull View view, int i) {
        attachView(view, i, (C05831Bi) view.getLayoutParams());
    }

    public void attachView(@NonNull View view, int i, C05831Bi r6) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isRemoved()) {
            C000502v<AnonymousClass1Ah, AnonymousClass1B9> r0 = this.mRecyclerView.mViewInfoStore.A01;
            AnonymousClass1B9 r1 = r0.get(childViewHolderInt);
            if (r1 == null) {
                r1 = AnonymousClass1B9.A00();
                r0.put(childViewHolderInt, r1);
            }
            r1.A00 |= 1;
        } else {
            this.mRecyclerView.mViewInfoStore.A01(childViewHolderInt);
        }
        this.mChildHelper.A05(view, i, r6, childViewHolderInt.isRemoved());
    }

    public C05831Bi generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new C05831Bi(context, attributeSet);
    }

    public C05831Bi generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C05831Bi) {
            return new C05831Bi((C05831Bi) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C05831Bi((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C05831Bi(layoutParams);
    }

    @CallSuper
    public void onDetachedFromWindow(RecyclerView recyclerView, AnonymousClass1Af r2) {
    }

    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(@NonNull AnonymousClass1Af r4, @NonNull AnonymousClass1As r5, @NonNull AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && accessibilityEvent != null) {
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            AnonymousClass1Aj r0 = this.mRecyclerView.mAdapter;
            if (r0 != null) {
                accessibilityEvent.setItemCount(r0.getItemCount());
            }
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, accessibilityNodeInfoCompat);
    }

    public void onInitializeAccessibilityNodeInfo(@NonNull AnonymousClass1Af r4, @NonNull AnonymousClass1As r5, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.A00;
            accessibilityNodeInfo.addAction(8192);
            accessibilityNodeInfo.setScrollable(true);
        }
        if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
            AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfoCompat.A00;
            accessibilityNodeInfo2.addAction(4096);
            accessibilityNodeInfo2.setScrollable(true);
        }
        accessibilityNodeInfoCompat.A00.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) new AnonymousClass07y(AccessibilityNodeInfo.CollectionInfo.obtain(getRowCountForAccessibility(r4, r5), getColumnCountForAccessibility(r4, r5), false, 0)).A00);
    }

    public void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
            AnonymousClass1Am r0 = this.mChildHelper;
            if (!r0.A02.contains(childViewHolderInt.itemView)) {
                RecyclerView recyclerView = this.mRecyclerView;
                onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
            }
        }
    }

    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2, @Nullable Object obj) {
        onItemsUpdated(recyclerView, i, i2);
    }

    @Deprecated
    public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull View view, @Nullable View view2) {
        return isSmoothScrolling() || recyclerView.isComputingLayout();
    }

    public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull AnonymousClass1As r3, @NonNull View view, @Nullable View view2) {
        return onRequestChildFocus(recyclerView, view, view2);
    }

    public boolean performAccessibilityAction(int i, @Nullable Bundle bundle) {
        RecyclerView recyclerView = this.mRecyclerView;
        return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i, bundle);
    }

    public boolean performAccessibilityAction(@NonNull AnonymousClass1Af r10, @NonNull AnonymousClass1As r11, int i, @Nullable Bundle bundle) {
        int i2;
        int i3;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            if (i == 4096) {
                if (recyclerView.canScrollVertically(1)) {
                    i2 = (this.mHeight - getPaddingTop()) - getPaddingBottom();
                } else {
                    i2 = 0;
                }
                if (this.mRecyclerView.canScrollHorizontally(1)) {
                    i3 = (this.mWidth - getPaddingLeft()) - getPaddingRight();
                }
                i3 = 0;
            } else if (i == 8192) {
                if (recyclerView.canScrollVertically(-1)) {
                    i2 = -((this.mHeight - getPaddingTop()) - getPaddingBottom());
                } else {
                    i2 = 0;
                }
                if (this.mRecyclerView.canScrollHorizontally(-1)) {
                    i3 = -((this.mWidth - getPaddingLeft()) - getPaddingRight());
                }
                i3 = 0;
            }
            if (!(i2 == 0 && i3 == 0)) {
                this.mRecyclerView.smoothScrollBy(i3, i2, null, Integer.MIN_VALUE, true);
                return true;
            }
        }
        return false;
    }

    public boolean performAccessibilityActionForItem(@NonNull View view, int i, @Nullable Bundle bundle) {
        return false;
    }

    public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z) {
        return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
    }

    public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z, boolean z2) {
        int[] childRectangleOnScreenScrollAmount = getChildRectangleOnScreenScrollAmount(view, rect);
        int i = childRectangleOnScreenScrollAmount[0];
        int i2 = childRectangleOnScreenScrollAmount[1];
        if ((z2 && !isFocusedChildVisibleAfterScrolling(recyclerView, i, i2)) || (i == 0 && i2 == 0)) {
            return false;
        }
        if (z) {
            recyclerView.scrollBy(i, i2);
            return true;
        }
        recyclerView.smoothScrollBy(i, i2);
        return true;
    }

    public void setMeasuredDimension(int i, int i2) {
        RecyclerView.access$300(this.mRecyclerView, i, i2);
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        setMeasuredDimension(chooseSize(i, rect.width() + getPaddingLeft() + getPaddingRight(), this.mRecyclerView.getMinimumWidth()), chooseSize(i2, rect.height() + getPaddingTop() + getPaddingBottom(), this.mRecyclerView.getMinimumHeight()));
    }
}
