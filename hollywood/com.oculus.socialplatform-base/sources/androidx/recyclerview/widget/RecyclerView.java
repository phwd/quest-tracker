package androidx.recyclerview.widget;

import X.AbstractC05811Bb;
import X.AbstractC05821Bc;
import X.AnonymousClass006;
import X.AnonymousClass02n;
import X.AnonymousClass07J;
import X.AnonymousClass07f;
import X.AnonymousClass07g;
import X.AnonymousClass1Ae;
import X.AnonymousClass1Af;
import X.AnonymousClass1Ag;
import X.AnonymousClass1Ah;
import X.AnonymousClass1Ai;
import X.AnonymousClass1Aj;
import X.AnonymousClass1Ak;
import X.AnonymousClass1Al;
import X.AnonymousClass1Am;
import X.AnonymousClass1Ap;
import X.AnonymousClass1Aq;
import X.AnonymousClass1Ar;
import X.AnonymousClass1As;
import X.AnonymousClass1At;
import X.AnonymousClass1Au;
import X.AnonymousClass1Av;
import X.AnonymousClass1Az;
import X.AnonymousClass1B0;
import X.AnonymousClass1B2;
import X.AnonymousClass1B3;
import X.AnonymousClass1B5;
import X.AnonymousClass1B6;
import X.AnonymousClass1B9;
import X.AnonymousClass1BB;
import X.AnonymousClass1BD;
import X.AnonymousClass1BF;
import X.AnonymousClass1BH;
import X.AnonymousClass1BI;
import X.AnonymousClass1BK;
import X.AnonymousClass1BM;
import X.AnonymousClass1BP;
import X.AnonymousClass1BQ;
import X.AnonymousClass1BS;
import X.AnonymousClass1BT;
import X.AnonymousClass1BW;
import X.AnonymousClass1BX;
import X.AnonymousClass1BY;
import X.AnonymousClass1BZ;
import X.AnonymousClass1CG;
import X.AnonymousClass1CP;
import X.AnonymousClass1EA;
import X.AnonymousClass1Ef;
import X.C000502v;
import X.C05831Bi;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.customview.view.AbsSavedState;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;

public class RecyclerView extends ViewGroup {
    public static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    public static final boolean ALLOW_THREAD_GAP_WORK;
    public static final boolean DEBUG;
    public static final int DEFAULT_ORIENTATION;
    public static final boolean DISPATCH_TEMP_DETACH;
    public static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    public static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    public static final long FOREVER_NS;
    public static final int HORIZONTAL;
    public static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    public static final int INVALID_POINTER;
    public static final int INVALID_TYPE;
    public static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    public static final int MAX_SCROLL_DURATION;
    public static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    public static final long NO_ID;
    public static final int NO_POSITION;
    public static final boolean POST_UPDATES_ON_ANIMATION;
    public static final int SCROLL_STATE_DRAGGING;
    public static final int SCROLL_STATE_IDLE;
    public static final int SCROLL_STATE_SETTLING;
    public static final String TAG;
    public static final int TOUCH_SLOP_DEFAULT;
    public static final int TOUCH_SLOP_PAGING;
    public static final String TRACE_BIND_VIEW_TAG;
    public static final String TRACE_CREATE_VIEW_TAG;
    public static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG;
    public static final String TRACE_NESTED_PREFETCH_TAG;
    public static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG;
    public static final String TRACE_ON_LAYOUT_TAG;
    public static final String TRACE_PREFETCH_TAG;
    public static final String TRACE_SCROLL_TAG;
    public static final int UNDEFINED_DURATION;
    public static final boolean VERBOSE_TRACING;
    public static final int VERTICAL;
    public static final Interpolator sQuinticInterpolator = new AnonymousClass1BP();
    public AnonymousClass1B3 mAccessibilityDelegate;
    public final AccessibilityManager mAccessibilityManager;
    public AnonymousClass1Aj mAdapter;
    public AnonymousClass1Aq mAdapterHelper;
    public boolean mAdapterUpdateDuringMeasure;
    public EdgeEffect mBottomGlow;
    public AnonymousClass1BZ mChildDrawingOrderCallback;
    public AnonymousClass1Am mChildHelper;
    public boolean mClipToPadding;
    public boolean mDataSetHasChangedAfterLayout;
    public boolean mDispatchItemsChangedEvent;
    public int mDispatchScrollCounter;
    public int mEatenAccessibilityChangeFlags;
    public AnonymousClass1BW mEdgeEffectFactory;
    public boolean mEnableFastScroller;
    public boolean mFirstLayoutComplete;
    public AnonymousClass1Ai mGapWorker;
    public boolean mHasFixedSize;
    public boolean mIgnoreMotionEventTillDown;
    public int mInitialTouchX;
    public int mInitialTouchY;
    public int mInterceptRequestLayoutDepth;
    public AnonymousClass1BM mInterceptingOnItemTouchListener;
    public boolean mIsAttached;
    public AnonymousClass1Al mItemAnimator;
    public AnonymousClass1BQ mItemAnimatorListener;
    public Runnable mItemAnimatorRunner;
    public final ArrayList<AnonymousClass1B2> mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    public int mLastAutoMeasureNonExactMeasuredHeight;
    public int mLastAutoMeasureNonExactMeasuredWidth;
    public boolean mLastAutoMeasureSkippedDueToExact;
    public int mLastTouchX;
    public int mLastTouchY;
    public AnonymousClass1Ag mLayout;
    public int mLayoutOrScrollCounter;
    public boolean mLayoutSuppressed;
    public boolean mLayoutWasDefered;
    public EdgeEffect mLeftGlow;
    public final int mMaxFlingVelocity;
    public final int mMinFlingVelocity;
    public final int[] mMinMaxLayoutPositions;
    public final int[] mNestedOffsets;
    public final AnonymousClass1Ap mObserver;
    public List<AnonymousClass1BX> mOnChildAttachStateListeners;
    public AbstractC05811Bb mOnFlingListener;
    public final ArrayList<AnonymousClass1BM> mOnItemTouchListeners;
    public final List<AnonymousClass1Ah> mPendingAccessibilityImportanceChange;
    public SavedState mPendingSavedState;
    public boolean mPostedAnimatorRunner;
    public AnonymousClass1Ak mPrefetchRegistry;
    public boolean mPreserveFocusAfterLayout;
    public final AnonymousClass1Af mRecycler;
    public AnonymousClass1BT mRecyclerListener;
    public final List<AnonymousClass1BT> mRecyclerListeners;
    public final int[] mReusableIntPair;
    public EdgeEffect mRightGlow;
    public float mScaledHorizontalScrollFactor;
    public float mScaledVerticalScrollFactor;
    public AnonymousClass1CG mScrollListener;
    public List<AnonymousClass1CG> mScrollListeners;
    public final int[] mScrollOffset;
    public int mScrollPointerId;
    public int mScrollState;
    public AnonymousClass07J mScrollingChildHelper;
    public final AnonymousClass1As mState;
    public final Rect mTempRect;
    public final Rect mTempRect2;
    public final RectF mTempRectF;
    public EdgeEffect mTopGlow;
    public int mTouchSlop;
    public final Runnable mUpdateChildViewsRunnable;
    public VelocityTracker mVelocityTracker;
    public AnonymousClass1Ae mViewFlinger;
    public final AnonymousClass1BF mViewInfoProcessCallback;
    public final AnonymousClass1At mViewInfoStore;

    public @interface Orientation {
    }

    private void animateChange(AnonymousClass1Ah r3, AnonymousClass1Ah r4, AnonymousClass1BS r5, AnonymousClass1BS r6, boolean z, boolean z2) {
        r3.setIsRecyclable(false);
        if (z) {
            addAnimatingView(r3);
        }
        if (r3 != r4) {
            if (z2) {
                addAnimatingView(r4);
            }
            r3.mShadowedHolder = r4;
            addAnimatingView(r3);
            this.mRecycler.A08(r3);
            r4.setIsRecyclable(false);
            r4.mShadowingHolder = r3;
        }
        if (this.mItemAnimator.A09(r3, r4, r5, r6)) {
            postAnimationRunner();
        }
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return AnonymousClass006.A07(context.getPackageName(), str);
        }
        if (str.contains(".")) {
            return str;
        }
        return AnonymousClass006.A02(RecyclerView.class.getPackage().getName(), '.', str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ab, code lost:
        if (r3 <= r2) goto L_0x00ad;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0084 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isPreferredNextFocus(android.view.View r12, android.view.View r13, int r14) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.isPreferredNextFocus(android.view.View, android.view.View, int):boolean");
    }

    private void nestedScrollByInternal(int i, int i2, MotionEvent motionEvent, int i3) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            int[] iArr = this.mReusableIntPair;
            int i4 = 0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean canScrollHorizontally = r1.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            int i5 = 0;
            if (canScrollHorizontally) {
                i5 = 1;
            }
            if (canScrollVertically) {
                i5 |= 2;
            }
            startNestedScroll(i5, i3);
            int i6 = 0;
            if (canScrollHorizontally) {
                i6 = i;
            }
            int i7 = 0;
            if (canScrollVertically) {
                i7 = i2;
            }
            if (dispatchNestedPreScroll(i6, i7, this.mReusableIntPair, this.mScrollOffset, i3)) {
                int[] iArr2 = this.mReusableIntPair;
                i -= iArr2[0];
                i2 -= iArr2[1];
            }
            int i8 = 0;
            if (canScrollHorizontally) {
                i8 = i;
            }
            if (canScrollVertically) {
                i4 = i2;
            }
            scrollByInternal(i8, i4, motionEvent, i3);
            AnonymousClass1Ai r0 = this.mGapWorker;
            if (!(r0 == null || (i == 0 && i2 == 0))) {
                r0.A01(this, i, i2);
            }
            stopNestedScroll(i3);
        }
    }

    private void requestChildOnScreen(View view, View view2) {
        View view3 = view;
        if (view2 != null) {
            view3 = view2;
        }
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof C05831Bi) {
            C05831Bi r1 = (C05831Bi) layoutParams;
            if (!r1.A02) {
                Rect rect = r1.A03;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        AnonymousClass1Ag r3 = this.mLayout;
        Rect rect3 = this.mTempRect;
        boolean z = !this.mFirstLayoutComplete;
        boolean z2 = false;
        if (view2 == null) {
            z2 = true;
        }
        r3.requestChildRectangleOnScreen(this, view, rect3, z, z2);
    }

    public void addRecyclerListener(AnonymousClass1BT r3) {
        boolean z = false;
        if (r3 != null) {
            z = true;
        }
        if (z) {
            this.mRecyclerListeners.add(r3);
            return;
        }
        throw new IllegalArgumentException(String.valueOf("'listener' arg cannot be null."));
    }

    public void animateAppearance(AnonymousClass1Ah r9, AnonymousClass1BS r10, AnonymousClass1BS r11) {
        int i;
        int i2;
        r9.setIsRecyclable(false);
        AnonymousClass1Az r2 = (AnonymousClass1Az) this.mItemAnimator;
        if (r10 == null || ((i = r10.A00) == (i2 = r11.A00) && r10.A01 == r11.A01)) {
            AnonymousClass1Ef r22 = (AnonymousClass1Ef) r2;
            AnonymousClass1Ef.A01(r22, r9);
            r9.itemView.setAlpha(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            r22.A06.add(r9);
        } else if (!r2.A0B(r9, i, r10.A01, i2, r11.A01)) {
            return;
        }
        postAnimationRunner();
    }

    public void animateDisappearance(AnonymousClass1Ah r10, AnonymousClass1BS r11, AnonymousClass1BS r12) {
        int i;
        int i2;
        addAnimatingView(r10);
        r10.setIsRecyclable(false);
        AnonymousClass1Az r3 = (AnonymousClass1Az) this.mItemAnimator;
        int i3 = r11.A00;
        int i4 = r11.A01;
        View view = r10.itemView;
        if (r12 == null) {
            i = view.getLeft();
        } else {
            i = r12.A00;
        }
        if (r12 == null) {
            i2 = view.getTop();
        } else {
            i2 = r12.A01;
        }
        if (r10.isRemoved() || (i3 == i && i4 == i2)) {
            AnonymousClass1Ef r32 = (AnonymousClass1Ef) r3;
            AnonymousClass1Ef.A01(r32, r10);
            r32.A09.add(r10);
        } else {
            view.layout(i, i2, view.getWidth() + i, view.getHeight() + i2);
            if (!r3.A0B(r10, i3, i4, i, i2)) {
                return;
            }
        }
        postAnimationRunner();
    }

    public AnonymousClass1Ah findViewHolderForLayoutPosition(int i) {
        return findViewHolderForPosition(i, false);
    }

    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException(AnonymousClass006.A07("Trying to set fast scroller without both required drawables.", exceptionLabel()));
        }
        Resources resources = getContext().getResources();
        new AnonymousClass1EA(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void nestedScrollBy(int i, int i2) {
        nestedScrollByInternal(i, i2, null, 1);
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    public void onScrollStateChanged(int i) {
    }

    public void onScrolled(int i, int i2) {
    }

    public void recordAnimationInfoIfBouncedHiddenView(AnonymousClass1Ah r4, AnonymousClass1BS r5) {
        r4.setFlags(0, 8192);
        if (this.mState.A0D && r4.isUpdated() && !r4.isRemoved() && !r4.shouldIgnore()) {
            this.mViewInfoStore.A00.A02(getChangedHolderKey(r4), r4);
        }
        C000502v<AnonymousClass1Ah, AnonymousClass1B9> r0 = this.mViewInfoStore.A01;
        AnonymousClass1B9 r1 = r0.get(r4);
        if (r1 == null) {
            r1 = AnonymousClass1B9.A00();
            r0.put(r4, r1);
        }
        r1.A02 = r5;
        r1.A00 |= 4;
    }

    public void setAdapter(AnonymousClass1Aj r3) {
        suppressLayout(false);
        setAdapterInternal(r3, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(AnonymousClass1BZ r2) {
        if (r2 != null) {
            this.mChildDrawingOrderCallback = r2;
            setChildrenDrawingOrderEnabled(true);
        }
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    public void swapAdapter(AnonymousClass1Aj r2, boolean z) {
        suppressLayout(false);
        setAdapterInternal(r2, true, z);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1CP();
        public Parcelable A00;

        @Override // androidx.customview.view.AbsSavedState
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.A00, 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.A00 = parcel.readParcelable(classLoader == null ? AnonymousClass1Ag.class.getClassLoader() : classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    private void addAnimatingView(AnonymousClass1Ah r7) {
        View view = r7.itemView;
        boolean z = false;
        if (view.getParent() == this) {
            z = true;
        }
        this.mRecycler.A08(getChildViewHolder(view));
        if (r7.isTmpDetached()) {
            this.mChildHelper.A05(view, -1, view.getLayoutParams(), true);
            return;
        }
        AnonymousClass1Am r2 = this.mChildHelper;
        if (!z) {
            int A3V = r2.A01.A3V();
            r2.A00.A05(A3V, true);
            r2.A02.add(view);
            AnonymousClass1B5 r0 = r2.A01;
            r0.A73(view);
            r0.A1P(view, A3V);
            return;
        }
        int A5a = r2.A01.A5a(view);
        if (A5a >= 0) {
            r2.A00.A04(A5a);
            r2.A02.add(view);
            r2.A01.A73(view);
            return;
        }
        StringBuilder sb = new StringBuilder("view is not a child, cannot hide ");
        sb.append(view);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void clearNestedRecyclerViewIfNotNested(AnonymousClass1Ah r3) {
        WeakReference<RecyclerView> weakReference = r3.mNestedRecyclerView;
        if (weakReference != null) {
            ViewParent viewParent = weakReference.get();
            while (true) {
                View view = (View) viewParent;
                while (true) {
                    if (view == null) {
                        r3.mNestedRecyclerView = null;
                        return;
                    } else if (view != r3.itemView) {
                        viewParent = view.getParent();
                        if (!(viewParent instanceof View)) {
                            view = null;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i, int i2) {
        ClassLoader classLoader;
        Constructor<? extends U> constructor;
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                String fullClassName = getFullClassName(context, trim);
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(fullClassName, false, classLoader).asSubclass(AnonymousClass1Ag.class);
                    Object[] objArr = null;
                    try {
                        constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)};
                    } catch (NoSuchMethodException e) {
                        try {
                            constructor = asSubclass.getConstructor(new Class[0]);
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(AnonymousClass006.A09(attributeSet.getPositionDescription(), ": Error creating LayoutManager ", fullClassName), e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((AnonymousClass1Ag) constructor.newInstance(objArr));
                } catch (ClassNotFoundException e3) {
                    throw new IllegalStateException(AnonymousClass006.A09(attributeSet.getPositionDescription(), ": Unable to find LayoutManager ", fullClassName), e3);
                } catch (InvocationTargetException e4) {
                    throw new IllegalStateException(AnonymousClass006.A09(attributeSet.getPositionDescription(), ": Could not instantiate the LayoutManager: ", fullClassName), e4);
                } catch (InstantiationException e5) {
                    throw new IllegalStateException(AnonymousClass006.A09(attributeSet.getPositionDescription(), ": Could not instantiate the LayoutManager: ", fullClassName), e5);
                } catch (IllegalAccessException e6) {
                    throw new IllegalStateException(AnonymousClass006.A09(attributeSet.getPositionDescription(), ": Cannot access non-public constructor ", fullClassName), e6);
                } catch (ClassCastException e7) {
                    throw new IllegalStateException(AnonymousClass006.A09(attributeSet.getPositionDescription(), ": Class is not a LayoutManager ", fullClassName), e7);
                }
            }
        }
    }

    private boolean didChildRangeChange(int i, int i2) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        if (iArr[0] == i && iArr[1] == i2) {
            return false;
        }
        return true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i != 0 && isAccessibilityEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            obtain.setContentChangeTypes(i);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    private void dispatchLayoutStep1() {
        AnonymousClass1B9 r0;
        AnonymousClass1As r02 = this.mState;
        boolean z = true;
        r02.A01(1);
        fillRemainingScrollValues(r02);
        this.mState.A0B = false;
        startInterceptRequestLayout();
        AnonymousClass1At r1 = this.mViewInfoStore;
        r1.A01.clear();
        AnonymousClass02n<AnonymousClass1Ah> r5 = r1.A00;
        int i = r5.A00;
        Object[] objArr = r5.A03;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        r5.A00 = 0;
        r5.A01 = false;
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        AnonymousClass1As r12 = this.mState;
        if (!r12.A05 || !this.mItemsChanged) {
            z = false;
        }
        r12.A0D = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        r12.A0A = r12.A0C;
        r12.A07 = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.A05) {
            int A02 = this.mChildHelper.A02();
            for (int i3 = 0; i3 < A02; i3++) {
                AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A04(i3));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.mHasStableIds)) {
                    AnonymousClass1Al r03 = this.mItemAnimator;
                    AnonymousClass1Al.A00(childViewHolderInt);
                    childViewHolderInt.getUnmodifiedPayloads();
                    AnonymousClass1BS A05 = r03.A05(childViewHolderInt);
                    C000502v<AnonymousClass1Ah, AnonymousClass1B9> r04 = this.mViewInfoStore.A01;
                    AnonymousClass1B9 r13 = r04.get(childViewHolderInt);
                    if (r13 == null) {
                        r13 = AnonymousClass1B9.A00();
                        r04.put(childViewHolderInt, r13);
                    }
                    r13.A02 = A05;
                    r13.A00 |= 4;
                    if (this.mState.A0D && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.A00.A02(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.A0C) {
            saveOldPositions();
            AnonymousClass1As r4 = this.mState;
            boolean z2 = r4.A06;
            r4.A06 = false;
            this.mLayout.onLayoutChildren(this.mRecycler, r4);
            this.mState.A06 = z2;
            for (int i4 = 0; i4 < this.mChildHelper.A02(); i4++) {
                AnonymousClass1Ah childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.A04(i4));
                if (!childViewHolderInt2.shouldIgnore() && ((r0 = this.mViewInfoStore.A01.get(childViewHolderInt2)) == null || (r0.A00 & 4) == 0)) {
                    AnonymousClass1Al.A00(childViewHolderInt2);
                    boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    AnonymousClass1Al r05 = this.mItemAnimator;
                    childViewHolderInt2.getUnmodifiedPayloads();
                    AnonymousClass1BS A052 = r05.A05(childViewHolderInt2);
                    if (hasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, A052);
                    } else {
                        C000502v<AnonymousClass1Ah, AnonymousClass1B9> r06 = this.mViewInfoStore.A01;
                        AnonymousClass1B9 r14 = r06.get(childViewHolderInt2);
                        if (r14 == null) {
                            r14 = AnonymousClass1B9.A00();
                            r06.put(childViewHolderInt2, r14);
                        }
                        r14.A00 |= 2;
                        r14.A02 = A052;
                    }
                }
            }
        }
        clearOldPositions();
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        this.mState.A08 = 2;
    }

    private void dispatchLayoutStep3() {
        AnonymousClass1BS r1;
        AnonymousClass1BS r0;
        AnonymousClass1As r12 = this.mState;
        r12.A01(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        r12.A08 = 1;
        if (r12.A05) {
            for (int A02 = this.mChildHelper.A02() - 1; A02 >= 0; A02--) {
                AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A04(A02));
                if (!childViewHolderInt.shouldIgnore()) {
                    long changedHolderKey = getChangedHolderKey(childViewHolderInt);
                    AnonymousClass1BS r6 = new AnonymousClass1BS();
                    View view = childViewHolderInt.itemView;
                    r6.A00 = view.getLeft();
                    r6.A01 = view.getTop();
                    view.getRight();
                    view.getBottom();
                    AnonymousClass1At r7 = this.mViewInfoStore;
                    AnonymousClass1Ah A01 = r7.A00.A01(changedHolderKey, null);
                    if (A01 == null || A01.shouldIgnore()) {
                        r7.A03(childViewHolderInt, r6);
                    } else {
                        AnonymousClass1B9 r2 = r7.A01.get(A01);
                        boolean z = true;
                        if (r2 == null || (r2.A00 & 1) == 0) {
                            z = false;
                        }
                        AnonymousClass1B9 r22 = this.mViewInfoStore.A01.get(childViewHolderInt);
                        boolean z2 = true;
                        if (r22 == null || (r22.A00 & 1) == 0) {
                            z2 = false;
                        }
                        if (!z || A01 != childViewHolderInt) {
                            AnonymousClass1BS A00 = AnonymousClass1At.A00(this.mViewInfoStore, A01, 4);
                            this.mViewInfoStore.A03(childViewHolderInt, r6);
                            AnonymousClass1BS A002 = AnonymousClass1At.A00(this.mViewInfoStore, childViewHolderInt, 8);
                            if (A00 == null) {
                                handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, A01);
                            } else {
                                animateChange(A01, childViewHolderInt, A00, A002, z, z2);
                            }
                        } else {
                            this.mViewInfoStore.A03(childViewHolderInt, r6);
                        }
                    }
                }
            }
            AnonymousClass1At r02 = this.mViewInfoStore;
            AnonymousClass1BF r72 = this.mViewInfoProcessCallback;
            C000502v<AnonymousClass1Ah, AnonymousClass1B9> r62 = r02.A01;
            int size = r62.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                AnonymousClass1Ah r3 = (AnonymousClass1Ah) r62.A02[size << 1];
                AnonymousClass1B9 A06 = r62.A06(size);
                int i = A06.A00;
                if ((i & 3) != 3) {
                    if ((i & 1) != 0) {
                        r1 = A06.A02;
                        if (r1 != null) {
                            r0 = A06.A01;
                        }
                    } else {
                        if ((i & 14) != 14) {
                            if ((i & 12) == 12) {
                                r72.A8c(r3, A06.A02, A06.A01);
                            } else if ((i & 4) != 0) {
                                r1 = A06.A02;
                                r0 = null;
                            } else if ((i & 8) == 0) {
                            }
                            A06.A00 = 0;
                            A06.A02 = null;
                            A06.A01 = null;
                            AnonymousClass1B9.A03.A8z(A06);
                        }
                        r72.A8a(r3, A06.A02, A06.A01);
                        A06.A00 = 0;
                        A06.A02 = null;
                        A06.A01 = null;
                        AnonymousClass1B9.A03.A8z(A06);
                    }
                    r72.A8b(r3, r1, r0);
                    A06.A00 = 0;
                    A06.A02 = null;
                    A06.A01 = null;
                    AnonymousClass1B9.A03.A8z(A06);
                }
                r72.AAr(r3);
                A06.A00 = 0;
                A06.A02 = null;
                A06.A01 = null;
                AnonymousClass1B9.A03.A8z(A06);
            }
        }
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        AnonymousClass1As r13 = this.mState;
        r13.A03 = r13.A07;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        r13.A05 = false;
        r13.A0C = false;
        AnonymousClass1Ag r23 = this.mLayout;
        r23.mRequestedSimpleAnimations = false;
        AnonymousClass1Af r14 = this.mRecycler;
        ArrayList<AnonymousClass1Ah> arrayList = r14.A04;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (r23.mPrefetchMaxObservedInInitialPrefetch) {
            r23.mPrefetchMaxCountObserved = 0;
            r23.mPrefetchMaxObservedInInitialPrefetch = false;
            r14.A05();
        }
        this.mLayout.onLayoutCompleted(this.mState);
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        AnonymousClass1At r15 = this.mViewInfoStore;
        r15.A01.clear();
        AnonymousClass02n<AnonymousClass1Ah> r5 = r15.A00;
        int i2 = r5.A00;
        Object[] objArr = r5.A03;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        r5.A00 = 0;
        r5.A01 = false;
        int[] iArr = this.mMinMaxLayoutPositions;
        if (didChildRangeChange(iArr[0], iArr[1])) {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent motionEvent) {
        AnonymousClass1BM r0 = this.mInterceptingOnItemTouchListener;
        if (r0 != null) {
            r0.A8D(this, motionEvent);
            int action = motionEvent.getAction();
            if (action == 3 || action == 1) {
                this.mInterceptingOnItemTouchListener = null;
            }
            return true;
        } else if (motionEvent.getAction() == 0) {
            return false;
        } else {
            return findInterceptingOnItemTouchListener(motionEvent);
        }
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int A02 = this.mChildHelper.A02();
        if (A02 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < A02; i3++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A04(i3));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i) {
                    i = layoutPosition;
                }
                if (layoutPosition > i2) {
                    i2 = layoutPosition;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (view instanceof ViewGroup) {
            if (view instanceof RecyclerView) {
                return (RecyclerView) view;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i));
                if (findNestedRecyclerView != null) {
                    return findNestedRecyclerView;
                }
            }
        }
        return null;
    }

    private View findNextViewToFocus() {
        AnonymousClass1Ah r1;
        AnonymousClass1As r12 = this.mState;
        int i = r12.A01;
        if (i == -1) {
            i = 0;
        }
        int A00 = r12.A00();
        int i2 = i;
        while (true) {
            if (i2 < A00) {
                r1 = findViewHolderForAdapterPosition(i2);
                if (r1 != null) {
                    if (r1.itemView.hasFocusable()) {
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        int min = Math.min(A00, i);
        do {
            min--;
            if (min < 0 || (r1 = findViewHolderForAdapterPosition(min)) == null) {
                return null;
            }
        } while (!r1.itemView.hasFocusable());
        return r1.itemView;
    }

    public static AnonymousClass1Ah getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((C05831Bi) view.getLayoutParams()).A01;
    }

    private AnonymousClass07J getScrollingChildHelper() {
        AnonymousClass07J r0 = this.mScrollingChildHelper;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass07J r02 = new AnonymousClass07J(this);
        this.mScrollingChildHelper = r02;
        return r02;
    }

    private void handleMissingPreInfoForChangeError(long j, AnonymousClass1Ah r9, AnonymousClass1Ah r10) {
        int A02 = this.mChildHelper.A02();
        for (int i = 0; i < A02; i++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A04(i));
            if (childViewHolderInt != r9 && getChangedHolderKey(childViewHolderInt) == j) {
                AnonymousClass1Aj r0 = this.mAdapter;
                if (r0 == null || !r0.mHasStableIds) {
                    StringBuilder sb = new StringBuilder("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
                    sb.append(childViewHolderInt);
                    sb.append(" \n View Holder 2:");
                    sb.append(r9);
                    sb.append(exceptionLabel());
                    throw new IllegalStateException(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
                sb2.append(childViewHolderInt);
                sb2.append(" \n View Holder 2:");
                sb2.append(r9);
                sb2.append(exceptionLabel());
                throw new IllegalStateException(sb2.toString());
            }
        }
        StringBuilder sb3 = new StringBuilder("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ");
        sb3.append(r10);
        sb3.append(" cannot be found but it is necessary for ");
        sb3.append(r9);
        sb3.append(exceptionLabel());
        Log.e(TAG, sb3.toString());
    }

    private boolean hasUpdatedView() {
        int A02 = this.mChildHelper.A02();
        for (int i = 0; i < A02; i++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A04(i));
            if (!(childViewHolderInt == null || childViewHolderInt.shouldIgnore() || !childViewHolderInt.isUpdated())) {
                return true;
            }
        }
        return false;
    }

    private void initChildrenHelper() {
        this.mChildHelper = new AnonymousClass1Am(new AnonymousClass1Au(this));
    }

    private boolean predictiveItemAnimationsEnabled() {
        if (this.mItemAnimator == null || !this.mLayout.supportsPredictiveItemAnimations()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r5.mItemsChanged != false) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processAdapterUpdatesAndSetAnimationFlags() {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.processAdapterUpdatesAndSetAnimationFlags():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void pullGlows(float r6, float r7, float r8, float r9) {
        /*
        // Method dump skipped, instructions count: 122
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.pullGlows(float, float, float, float):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recoverFocusFromState() {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.recoverFocusFromState():void");
    }

    private void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    private void resetFocusInfo() {
        AnonymousClass1As r2 = this.mState;
        r2.A04 = -1;
        r2.A01 = -1;
        r2.A02 = -1;
    }

    private void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        View focusedChild;
        AnonymousClass1Ah findContainingViewHolder;
        long j;
        int absoluteAdapterPosition;
        if (!this.mPreserveFocusAfterLayout || !hasFocus() || this.mAdapter == null || (focusedChild = getFocusedChild()) == null || (findContainingViewHolder = findContainingViewHolder(focusedChild)) == null) {
            resetFocusInfo();
            return;
        }
        AnonymousClass1As r2 = this.mState;
        if (this.mAdapter.mHasStableIds) {
            j = findContainingViewHolder.mItemId;
        } else {
            j = -1;
        }
        r2.A04 = j;
        if (this.mDataSetHasChangedAfterLayout) {
            absoluteAdapterPosition = -1;
        } else if (findContainingViewHolder.isRemoved()) {
            absoluteAdapterPosition = findContainingViewHolder.mOldPosition;
        } else {
            absoluteAdapterPosition = findContainingViewHolder.getAbsoluteAdapterPosition();
        }
        r2.A01 = absoluteAdapterPosition;
        r2.A02 = getDeepestFocusedViewWithId(findContainingViewHolder.itemView);
    }

    private void setAdapterInternal(AnonymousClass1Aj r6, boolean z, boolean z2) {
        AnonymousClass1Aj r1 = this.mAdapter;
        if (r1 != null) {
            r1.unregisterAdapterDataObserver(this.mObserver);
        }
        if (!z || z2) {
            removeAndRecycleViews();
        }
        AnonymousClass1Aq r12 = this.mAdapterHelper;
        AnonymousClass1Aq.A05(r12, r12.A04);
        AnonymousClass1Aq.A05(r12, r12.A05);
        r12.A00 = 0;
        AnonymousClass1Aj r2 = this.mAdapter;
        this.mAdapter = r6;
        if (r6 != null) {
            r6.registerAdapterDataObserver(this.mObserver);
        }
        AnonymousClass1Ag r13 = this.mLayout;
        if (r13 != null) {
            r13.onAdapterChanged(r2, this.mAdapter);
        }
        AnonymousClass1Af r14 = this.mRecycler;
        AnonymousClass1Aj r4 = this.mAdapter;
        r14.A05.clear();
        AnonymousClass1Af.A01(r14);
        AnonymousClass1BH r3 = r14.A02;
        if (r3 == null) {
            r3 = new AnonymousClass1BH();
            r14.A02 = r3;
        }
        if (r2 != null) {
            r3.A00--;
        }
        if (!z && r3.A00 == 0) {
            int i = 0;
            while (true) {
                SparseArray<AnonymousClass1BI> sparseArray = r3.A01;
                if (i >= sparseArray.size()) {
                    break;
                }
                sparseArray.valueAt(i).A02.clear();
                i++;
            }
        }
        if (r4 != null) {
            r3.A00++;
        }
        this.mState.A06 = true;
    }

    private void stopScrollersInternal() {
        AnonymousClass1Ae r1 = this.mViewFlinger;
        r1.A06.removeCallbacks(r1);
        r1.A03.abortAnimation();
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 != null) {
            r0.stopSmoothScroller();
        }
    }

    public void absorbGlows(int i, int i2) {
        if (i < 0) {
            ensureLeftGlow();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i);
            }
        } else if (i > 0) {
            ensureRightGlow();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i);
            }
        }
        if (i2 < 0) {
            ensureTopGlow();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            ensureBottomGlow();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i2);
            }
        }
        if (i != 0 || i2 != 0) {
            postInvalidateOnAnimation();
        }
    }

    public void addOnChildAttachStateChangeListener(AnonymousClass1BX r2) {
        List list = this.mOnChildAttachStateListeners;
        if (list == null) {
            list = new ArrayList();
            this.mOnChildAttachStateListeners = list;
        }
        list.add(r2);
    }

    public void addOnItemTouchListener(AnonymousClass1BM r2) {
        this.mOnItemTouchListeners.add(r2);
    }

    public void addOnScrollListener(AnonymousClass1CG r2) {
        List list = this.mScrollListeners;
        if (list == null) {
            list = new ArrayList();
            this.mScrollListeners = list;
        }
        list.add(r2);
    }

    public boolean canReuseUpdatedViewHolder(AnonymousClass1Ah r3) {
        AnonymousClass1Al r1 = this.mItemAnimator;
        if (r1 == null || r1.A0A(r3, r3.getUnmodifiedPayloads())) {
            return true;
        }
        return false;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof C05831Bi) || !this.mLayout.checkLayoutParams((C05831Bi) layoutParams)) {
            return false;
        }
        return true;
    }

    public void clearOldPositions() {
        int A3V = this.mChildHelper.A01.A3V();
        for (int i = 0; i < A3V; i++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        AnonymousClass1Af r5 = this.mRecycler;
        ArrayList<AnonymousClass1Ah> arrayList = r5.A06;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.get(i2).clearOldPosition();
        }
        ArrayList<AnonymousClass1Ah> arrayList2 = r5.A05;
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            arrayList2.get(i3).clearOldPosition();
        }
        ArrayList<AnonymousClass1Ah> arrayList3 = r5.A04;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i4 = 0; i4 < size3; i4++) {
                arrayList3.get(i4).clearOldPosition();
            }
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<AnonymousClass1BX> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<AnonymousClass1CG> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    public int computeHorizontalScrollExtent() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 == null || !r0.canScrollHorizontally()) {
            return 0;
        }
        return this.mLayout.computeHorizontalScrollExtent(this.mState);
    }

    public int computeHorizontalScrollOffset() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 == null || !r0.canScrollHorizontally()) {
            return 0;
        }
        return this.mLayout.computeHorizontalScrollOffset(this.mState);
    }

    public int computeHorizontalScrollRange() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 == null || !r0.canScrollHorizontally()) {
            return 0;
        }
        return this.mLayout.computeHorizontalScrollRange(this.mState);
    }

    public int computeVerticalScrollExtent() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 == null || !r0.canScrollVertically()) {
            return 0;
        }
        return this.mLayout.computeVerticalScrollExtent(this.mState);
    }

    public int computeVerticalScrollOffset() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 == null || !r0.canScrollVertically()) {
            return 0;
        }
        return this.mLayout.computeVerticalScrollOffset(this.mState);
    }

    public int computeVerticalScrollRange() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 == null || !r0.canScrollVertically()) {
            return 0;
        }
        return this.mLayout.computeVerticalScrollRange(this.mState);
    }

    public void considerReleasingGlowsOnScroll(int i, int i2) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    public void consumePendingUpdateOperations() {
        if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout) {
            AnonymousClass1Aq r2 = this.mAdapterHelper;
            if (r2.A04.size() > 0) {
                int i = r2.A00;
                if ((4 & i) != 0 && (11 & i) == 0) {
                    Trace.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
                    startInterceptRequestLayout();
                    onEnterLayoutOrScroll();
                    this.mAdapterHelper.A09();
                    if (!this.mLayoutWasDefered) {
                        if (hasUpdatedView()) {
                            dispatchLayout();
                        } else {
                            this.mAdapterHelper.A07();
                        }
                    }
                    stopInterceptRequestLayout(true);
                    onExitLayoutOrScroll(true);
                    Trace.endSection();
                } else if (r2.A04.size() <= 0) {
                    return;
                }
            } else {
                return;
            }
        }
        Trace.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
        dispatchLayout();
        Trace.endSection();
    }

    public void dispatchLayout() {
        boolean z;
        if (this.mAdapter == null) {
            Log.w(TAG, "No adapter attached; skipping layout");
        } else if (this.mLayout == null) {
            Log.e(TAG, "No layout manager attached; skipping layout");
        } else {
            this.mState.A0B = false;
            if (!this.mLastAutoMeasureSkippedDueToExact || (this.mLastAutoMeasureNonExactMeasuredWidth == getWidth() && this.mLastAutoMeasureNonExactMeasuredHeight == getHeight())) {
                z = false;
            } else {
                z = true;
            }
            this.mLastAutoMeasureNonExactMeasuredWidth = 0;
            this.mLastAutoMeasureNonExactMeasuredHeight = 0;
            this.mLastAutoMeasureSkippedDueToExact = false;
            if (this.mState.A08 == 1) {
                dispatchLayoutStep1();
            } else {
                AnonymousClass1Aq r1 = this.mAdapterHelper;
                if ((r1.A05.isEmpty() || r1.A04.isEmpty()) && !z && this.mLayout.mWidth == getWidth() && this.mLayout.mHeight == getHeight()) {
                    this.mLayout.setExactMeasureSpecsFrom(this);
                    dispatchLayoutStep3();
                }
            }
            this.mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
            dispatchLayoutStep3();
        }
    }

    public void dispatchOnScrollStateChanged(int i) {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 != null) {
            r0.onScrollStateChanged(i);
        }
        List<AnonymousClass1CG> list = this.mScrollListeners;
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    this.mScrollListeners.get(size);
                } else {
                    return;
                }
            }
        }
    }

    public void dispatchOnScrolled(int i, int i2) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        AnonymousClass1CG r0 = this.mScrollListener;
        if (r0 != null) {
            r0.onScrolled(this, i, i2);
        }
        List<AnonymousClass1CG> list = this.mScrollListeners;
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                this.mScrollListeners.get(size).onScrolled(this, i, i2);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int i;
        int size = this.mPendingAccessibilityImportanceChange.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass1Ah r3 = this.mPendingAccessibilityImportanceChange.get(size);
                if (r3.itemView.getParent() == this && !r3.shouldIgnore() && (i = r3.mPendingAccessibilityState) != -1) {
                    r3.itemView.setImportantForAccessibility(i);
                    r3.mPendingAccessibilityState = -1;
                }
            } else {
                this.mPendingAccessibilityImportanceChange.clear();
                return;
            }
        }
    }

    public void ensureBottomGlow() {
        int measuredHeight;
        if (this.mBottomGlow == null) {
            EdgeEffect edgeEffect = new EdgeEffect(getContext());
            this.mBottomGlow = edgeEffect;
            boolean z = this.mClipToPadding;
            int measuredWidth = getMeasuredWidth();
            if (z) {
                measuredWidth = (measuredWidth - getPaddingLeft()) - getPaddingRight();
                measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            } else {
                measuredHeight = getMeasuredHeight();
            }
            edgeEffect.setSize(measuredWidth, measuredHeight);
        }
    }

    public void ensureLeftGlow() {
        int measuredWidth;
        if (this.mLeftGlow == null) {
            EdgeEffect edgeEffect = new EdgeEffect(getContext());
            this.mLeftGlow = edgeEffect;
            boolean z = this.mClipToPadding;
            int measuredHeight = getMeasuredHeight();
            if (z) {
                measuredHeight = (measuredHeight - getPaddingTop()) - getPaddingBottom();
                measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            } else {
                measuredWidth = getMeasuredWidth();
            }
            edgeEffect.setSize(measuredHeight, measuredWidth);
        }
    }

    public void ensureRightGlow() {
        int measuredWidth;
        if (this.mRightGlow == null) {
            EdgeEffect edgeEffect = new EdgeEffect(getContext());
            this.mRightGlow = edgeEffect;
            boolean z = this.mClipToPadding;
            int measuredHeight = getMeasuredHeight();
            if (z) {
                measuredHeight = (measuredHeight - getPaddingTop()) - getPaddingBottom();
                measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            } else {
                measuredWidth = getMeasuredWidth();
            }
            edgeEffect.setSize(measuredHeight, measuredWidth);
        }
    }

    public void ensureTopGlow() {
        int measuredHeight;
        if (this.mTopGlow == null) {
            EdgeEffect edgeEffect = new EdgeEffect(getContext());
            this.mTopGlow = edgeEffect;
            boolean z = this.mClipToPadding;
            int measuredWidth = getMeasuredWidth();
            if (z) {
                measuredWidth = (measuredWidth - getPaddingLeft()) - getPaddingRight();
                measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            } else {
                measuredHeight = getMeasuredHeight();
            }
            edgeEffect.setSize(measuredWidth, measuredHeight);
        }
    }

    public String exceptionLabel() {
        StringBuilder sb = new StringBuilder(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
        sb.append(super.toString());
        sb.append(", adapter:");
        sb.append(this.mAdapter);
        sb.append(", layout:");
        sb.append(this.mLayout);
        sb.append(", context:");
        sb.append(getContext());
        return sb.toString();
    }

    public final void fillRemainingScrollValues(AnonymousClass1As r3) {
        if (this.mScrollState == 2) {
            OverScroller overScroller = this.mViewFlinger.A03;
            overScroller.getFinalX();
            overScroller.getCurrX();
            overScroller.getFinalY();
            overScroller.getCurrY();
        }
    }

    public View findChildViewUnder(float f, float f2) {
        int A02 = this.mChildHelper.A02();
        while (true) {
            A02--;
            if (A02 < 0) {
                return null;
            }
            View A04 = this.mChildHelper.A04(A02);
            float translationX = A04.getTranslationX();
            float translationY = A04.getTranslationY();
            if (f >= ((float) A04.getLeft()) + translationX && f <= ((float) A04.getRight()) + translationX && f2 >= ((float) A04.getTop()) + translationY && f2 <= ((float) A04.getBottom()) + translationY) {
                return A04;
            }
        }
    }

    public AnonymousClass1Ah findViewHolderForAdapterPosition(int i) {
        AnonymousClass1Ah r1 = null;
        if (!this.mDataSetHasChangedAfterLayout) {
            int A3V = this.mChildHelper.A01.A3V();
            for (int i2 = 0; i2 < A3V; i2++) {
                AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i2));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionInRecyclerView(childViewHolderInt) == i) {
                    AnonymousClass1Am r0 = this.mChildHelper;
                    if (!r0.A02.contains(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    r1 = childViewHolderInt;
                }
            }
        }
        return r1;
    }

    public AnonymousClass1Ah findViewHolderForItemId(long j) {
        AnonymousClass1Aj r0 = this.mAdapter;
        AnonymousClass1Ah r6 = null;
        if (r0 != null && r0.mHasStableIds) {
            int A3V = this.mChildHelper.A01.A3V();
            for (int i = 0; i < A3V; i++) {
                AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.mItemId == j) {
                    AnonymousClass1Am r02 = this.mChildHelper;
                    if (!r02.A02.contains(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    r6 = childViewHolderInt;
                }
            }
        }
        return r6;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        if (r5 != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean fling(int r15, int r16) {
        /*
        // Method dump skipped, instructions count: 155
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.fling(int, int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        if (r5.findNextFocus(r6, r7, r0) == null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        consumePendingUpdateOperations();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        if (findContainingItemView(r7) == null) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        startInterceptRequestLayout();
        r6.mLayout.onFocusSearchFailed(r7, r8, r6.mRecycler, r6.mState);
        stopInterceptRequestLayout(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0089, code lost:
        if (r5.findNextFocus(r6, r7, r0) == null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        if (r6.mLayoutSuppressed != false) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View focusSearch(android.view.View r7, int r8) {
        /*
        // Method dump skipped, instructions count: 190
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 != null) {
            return r0.generateDefaultLayoutParams();
        }
        throw new IllegalStateException(AnonymousClass006.A07("RecyclerView has no LayoutManager", exceptionLabel()));
    }

    public AnonymousClass1Aj getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionInRecyclerView(AnonymousClass1Ah r8) {
        if (!r8.hasAnyOfTheFlags(524) && r8.isBound()) {
            AnonymousClass1Aq r0 = this.mAdapterHelper;
            int i = r8.mPosition;
            ArrayList<AnonymousClass1B0> arrayList = r0.A04;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                AnonymousClass1B0 r6 = arrayList.get(i2);
                int i3 = r6.A00;
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 == 8) {
                            if (r6.A02 == i) {
                                i = r6.A01;
                            } else {
                                if (r6.A02 < i) {
                                    i--;
                                }
                                if (r6.A01 <= i) {
                                    i++;
                                }
                            }
                        }
                    } else if (r6.A02 > i) {
                        continue;
                    } else if (r6.A02 + r6.A01 <= i) {
                        i -= r6.A01;
                    }
                } else if (r6.A02 <= i) {
                    i += r6.A01;
                }
            }
            return i;
        }
        return -1;
    }

    public int getBaseline() {
        if (this.mLayout != null) {
            return -1;
        }
        return super.getBaseline();
    }

    public long getChangedHolderKey(AnonymousClass1Ah r3) {
        if (this.mAdapter.mHasStableIds) {
            return r3.mItemId;
        }
        return (long) r3.mPosition;
    }

    public long getChildItemId(View view) {
        AnonymousClass1Ah childViewHolderInt;
        AnonymousClass1Aj r2 = this.mAdapter;
        if (r2 == null || !r2.mHasStableIds || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1;
        }
        return childViewHolderInt.mItemId;
    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public AnonymousClass1B3 getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public AnonymousClass1BW getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public AnonymousClass1Al getItemAnimator() {
        return this.mItemAnimator;
    }

    public AnonymousClass1B2 getItemDecorationAt(int i) {
        int size = this.mItemDecorations.size();
        if (i >= 0 && i < size) {
            return this.mItemDecorations.get(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" is an invalid index for size ");
        sb.append(size);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public AnonymousClass1Ag getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public AbstractC05811Bb getOnFlingListener() {
        return null;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public AnonymousClass1BH getRecycledViewPool() {
        AnonymousClass1Af r1 = this.mRecycler;
        AnonymousClass1BH r0 = r1.A02;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1BH r02 = new AnonymousClass1BH();
        r1.A02 = r02;
        return r02;
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    public boolean hasPendingAdapterUpdates() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.A04.size() > 0) {
            return true;
        }
        return false;
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new AnonymousClass1Aq(new AnonymousClass1Ar(this));
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            AnonymousClass1Ag r1 = this.mLayout;
            if (r1 != null) {
                r1.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }
            markItemDecorInsetsDirty();
            requestLayout();
        }
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return false;
        }
        return true;
    }

    public boolean isAnimating() {
        AnonymousClass1Al r0 = this.mItemAnimator;
        if (r0 == null || !r0.A08()) {
            return false;
        }
        return true;
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        if (this.mLayoutOrScrollCounter > 0) {
            return true;
        }
        return false;
    }

    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    public void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout != null) {
            setScrollState(2);
            this.mLayout.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    public void markItemDecorInsetsDirty() {
        int A3V = this.mChildHelper.A01.A3V();
        for (int i = 0; i < A3V; i++) {
            ((C05831Bi) this.mChildHelper.A01.A3U(i).getLayoutParams()).A02 = true;
        }
        ArrayList<AnonymousClass1Ah> arrayList = this.mRecycler.A06;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            C05831Bi r1 = (C05831Bi) arrayList.get(i2).itemView.getLayoutParams();
            if (r1 != null) {
                r1.A02 = true;
            }
        }
    }

    public void markKnownViewsInvalid() {
        int A3V = this.mChildHelper.A01.A3V();
        for (int i = 0; i < A3V; i++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        AnonymousClass1Af r5 = this.mRecycler;
        ArrayList<AnonymousClass1Ah> arrayList = r5.A06;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            AnonymousClass1Ah r1 = arrayList.get(i2);
            if (r1 != null) {
                r1.addFlags(6);
                r1.addChangePayload(null);
            }
        }
        AnonymousClass1Aj r0 = r5.A08.mAdapter;
        if (r0 == null || !r0.mHasStableIds) {
            AnonymousClass1Af.A01(r5);
        }
    }

    public void offsetChildrenHorizontal(int i) {
        int A02 = this.mChildHelper.A02();
        for (int i2 = 0; i2 < A02; i2++) {
            this.mChildHelper.A04(i2).offsetLeftAndRight(i);
        }
    }

    public void offsetChildrenVertical(int i) {
        int A02 = this.mChildHelper.A02();
        for (int i2 = 0; i2 < A02; i2++) {
            this.mChildHelper.A04(i2).offsetTopAndBottom(i);
        }
    }

    public void offsetPositionRecordsForInsert(int i, int i2) {
        int A3V = this.mChildHelper.A01.A3V();
        for (int i3 = 0; i3 < A3V; i3++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i3));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                childViewHolderInt.offsetPosition(i2, false);
                this.mState.A06 = true;
            }
        }
        ArrayList<AnonymousClass1Ah> arrayList = this.mRecycler.A06;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            AnonymousClass1Ah r1 = arrayList.get(i4);
            if (r1 != null && r1.mPosition >= i) {
                r1.offsetPosition(i2, true);
            }
        }
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int i, int i2) {
        int i3;
        int A3V = this.mChildHelper.A01.A3V();
        int i4 = i;
        int i5 = i2;
        int i6 = 1;
        if (i < i2) {
            i5 = i;
            i4 = i2;
            i6 = -1;
        }
        for (int i7 = 0; i7 < A3V; i7++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i7));
            if (childViewHolderInt != null && (i3 = childViewHolderInt.mPosition) >= i5 && i3 <= i4) {
                if (i3 == i) {
                    childViewHolderInt.offsetPosition(i2 - i, false);
                } else {
                    childViewHolderInt.offsetPosition(i6, false);
                }
                this.mState.A06 = true;
            }
        }
        AnonymousClass1Af r0 = this.mRecycler;
        int i8 = i;
        int i9 = i2;
        int i10 = 1;
        if (i < i2) {
            i9 = i;
            i8 = i2;
            i10 = -1;
        }
        ArrayList<AnonymousClass1Ah> arrayList = r0.A06;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            AnonymousClass1Ah r1 = arrayList.get(i11);
            if (r1 != null && r1.mPosition >= i9 && r1.mPosition <= i8) {
                if (r1.mPosition == i) {
                    r1.offsetPosition(i2 - i, false);
                } else {
                    r1.offsetPosition(i10, false);
                }
            }
        }
        requestLayout();
    }

    public void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3 = i + i2;
        int A3V = this.mChildHelper.A01.A3V();
        for (int i4 = 0; i4 < A3V; i4++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i5 = childViewHolderInt.mPosition;
                if (i5 >= i3) {
                    childViewHolderInt.offsetPosition(-i2, z);
                } else if (i5 >= i) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(i - 1, -i2, z);
                }
                this.mState.A06 = true;
            }
        }
        AnonymousClass1Af r4 = this.mRecycler;
        ArrayList<AnonymousClass1Ah> arrayList = r4.A06;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass1Ah r1 = arrayList.get(size);
                if (r1 != null) {
                    int i6 = r1.mPosition;
                    if (i6 >= i3) {
                        r1.offsetPosition(-i2, z);
                    } else if (i6 >= i) {
                        r1.addFlags(8);
                        AnonymousClass1Af.A02(r4, size);
                    }
                }
            } else {
                requestLayout();
                return;
            }
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        if (r3 == com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r1 != com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        nestedScrollByInternal((int) (r1 * r5.mScaledHorizontalScrollFactor), (int) (r3 * r5.mScaledVerticalScrollFactor), r6, 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        if (r4 != false) goto L_0x00b9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r10) {
        /*
        // Method dump skipped, instructions count: 272
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Trace.beginSection(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        Trace.endSection();
        this.mFirstLayoutComplete = true;
    }

    public void onMeasure(int i, int i2) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 == null) {
            defaultOnMeasure(i, i2);
            return;
        }
        boolean z = false;
        if (r1.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.mRecyclerView.defaultOnMeasure(i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if (!z && this.mAdapter != null) {
                if (this.mState.A08 == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(i, i2);
                this.mState.A0B = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.A0B = true;
                    dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                }
                this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
                this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
            }
        } else if (this.mHasFixedSize) {
            r1.mRecyclerView.defaultOnMeasure(i, i2);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll(true);
                AnonymousClass1As r12 = this.mState;
                if (r12.A0C) {
                    r12.A0A = true;
                } else {
                    this.mAdapterHelper.A08();
                    this.mState.A0A = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                stopInterceptRequestLayout(false);
            } else if (this.mState.A0C) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            AnonymousClass1Aj r0 = this.mAdapter;
            if (r0 != null) {
                this.mState.A07 = r0.getItemCount();
            } else {
                this.mState.A07 = 0;
            }
            startInterceptRequestLayout();
            this.mLayout.mRecyclerView.defaultOnMeasure(i, i2);
            stopInterceptRequestLayout(false);
            this.mState.A0A = false;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(((AbsSavedState) savedState).A00);
        requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c6, code lost:
        if (r6 == 0) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d4, code lost:
        if (r5 != 0) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d6, code lost:
        setScrollState(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0150, code lost:
        if (r1 != false) goto L_0x00d6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r21) {
        /*
        // Method dump skipped, instructions count: 452
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            postOnAnimation(this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    public void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    public void removeAndRecycleViews() {
        AnonymousClass1Al r0 = this.mItemAnimator;
        if (r0 != null) {
            r0.A06();
        }
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 != null) {
            r1.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        AnonymousClass1Af r12 = this.mRecycler;
        r12.A05.clear();
        AnonymousClass1Af.A01(r12);
    }

    public void removeItemDecoration(AnonymousClass1B2 r4) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 != null) {
            r1.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(r4);
        if (this.mItemDecorations.isEmpty()) {
            boolean z = false;
            if (getOverScrollMode() == 2) {
                z = true;
            }
            setWillNotDraw(z);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeItemDecorationAt(int i) {
        int size = this.mItemDecorations.size();
        if (i < 0 || i >= size) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" is an invalid index for size ");
            sb.append(size);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        removeItemDecoration(getItemDecorationAt(i));
    }

    public void removeOnChildAttachStateChangeListener(AnonymousClass1BX r2) {
        List<AnonymousClass1BX> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.remove(r2);
        }
    }

    public void removeOnItemTouchListener(AnonymousClass1BM r2) {
        this.mOnItemTouchListeners.remove(r2);
        if (this.mInterceptingOnItemTouchListener == r2) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(AnonymousClass1CG r2) {
        List<AnonymousClass1CG> list = this.mScrollListeners;
        if (list != null) {
            list.remove(r2);
        }
    }

    public void removeRecyclerListener(AnonymousClass1BT r2) {
        this.mRecyclerListeners.remove(r2);
    }

    public void repositionShadowingViews() {
        AnonymousClass1Ah r0;
        int A02 = this.mChildHelper.A02();
        for (int i = 0; i < A02; i++) {
            View A04 = this.mChildHelper.A04(i);
            AnonymousClass1Ah childViewHolder = getChildViewHolder(A04);
            if (!(childViewHolder == null || (r0 = childViewHolder.mShadowingHolder) == null)) {
                View view = r0.itemView;
                int left = A04.getLeft();
                int top = A04.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus(this, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ArrayList<AnonymousClass1BM> arrayList = this.mOnItemTouchListeners;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutSuppressed) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public void saveOldPositions() {
        int A3V = this.mChildHelper.A01.A3V();
        for (int i = 0; i < A3V; i++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    public void scrollBy(int i, int i2) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            boolean canScrollHorizontally = r1.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i = 0;
                }
                if (!canScrollVertically) {
                    i2 = 0;
                }
                scrollByInternal(i, i2, null, 0);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0047, code lost:
        if (r0 != 0) goto L_0x0049;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scrollByInternal(int r19, int r20, android.view.MotionEvent r21, int r22) {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent, int):boolean");
    }

    public void scrollTo(int i, int i2) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i) {
        if (!this.mLayoutSuppressed) {
            stopScroll();
            AnonymousClass1Ag r0 = this.mLayout;
            if (r0 == null) {
                Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            r0.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    public void setAccessibilityDelegateCompat(AnonymousClass1B3 r1) {
        this.mAccessibilityDelegate = r1;
        AnonymousClass07f.A07(this, r1);
    }

    public void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(AnonymousClass1BW r2) {
        if (r2 != null) {
            this.mEdgeEffectFactory = r2;
            invalidateGlows();
            return;
        }
        throw null;
    }

    public void setItemAnimator(AnonymousClass1Al r3) {
        AnonymousClass1Al r0 = this.mItemAnimator;
        if (r0 != null) {
            r0.A06();
            this.mItemAnimator.A04 = null;
        }
        this.mItemAnimator = r3;
        if (r3 != null) {
            r3.A04 = this.mItemAnimatorListener;
        }
    }

    public void setItemViewCacheSize(int i) {
        AnonymousClass1Af r0 = this.mRecycler;
        r0.A01 = i;
        r0.A05();
    }

    public void setLayoutManager(AnonymousClass1Ag r6) {
        if (r6 != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                AnonymousClass1Al r0 = this.mItemAnimator;
                if (r0 != null) {
                    r0.A06();
                }
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                AnonymousClass1Af r1 = this.mRecycler;
                r1.A05.clear();
                AnonymousClass1Af.A01(r1);
                if (this.mIsAttached) {
                    this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
                }
                this.mLayout.setRecyclerView(null);
                this.mLayout = null;
            } else {
                AnonymousClass1Af r12 = this.mRecycler;
                r12.A05.clear();
                AnonymousClass1Af.A01(r12);
            }
            AnonymousClass1Am r4 = this.mChildHelper;
            AnonymousClass1B6 r2 = r4.A00;
            r2.A00 = 0;
            AnonymousClass1B6 r22 = r2.A01;
            if (r22 != null) {
                r22.A00 = 0;
                AnonymousClass1B6 r23 = r22.A01;
                if (r23 != null) {
                    r23.A00 = 0;
                    AnonymousClass1B6 r02 = r23.A01;
                    if (r02 != null) {
                        r02.A02();
                    }
                }
            }
            List<View> list = r4.A02;
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                r4.A01.A7E(list.get(size));
                list.remove(size);
            }
            r4.A01.A98();
            this.mLayout = r6;
            if (r6 != null) {
                if (r6.mRecyclerView == null) {
                    r6.setRecyclerView(this);
                    if (this.mIsAttached) {
                        this.mLayout.dispatchAttachedToWindow(this);
                    }
                } else {
                    StringBuilder sb = new StringBuilder("LayoutManager ");
                    sb.append(r6);
                    sb.append(" is already attached to a RecyclerView:");
                    sb.append(r6.mRecyclerView.exceptionLabel());
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            this.mRecycler.A05();
            requestLayout();
        }
    }

    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    public void setRecycledViewPool(AnonymousClass1BH r4) {
        AnonymousClass1Af r2 = this.mRecycler;
        AnonymousClass1BH r1 = r2.A02;
        if (r1 != null) {
            r1.A00--;
        }
        r2.A02 = r4;
        if (r4 != null && r2.A08.mAdapter != null) {
            r4.A00++;
        }
    }

    public void setScrollState(int i) {
        if (i != this.mScrollState) {
            this.mScrollState = i;
            if (i != 2) {
                stopScrollersInternal();
            }
            dispatchOnScrollStateChanged(i);
        }
    }

    public void setViewCacheExtension(AbstractC05821Bc r2) {
        this.mRecycler.A03 = r2;
    }

    public void smoothScrollToPosition(int i) {
        if (!this.mLayoutSuppressed) {
            AnonymousClass1Ag r1 = this.mLayout;
            if (r1 == null) {
                Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                r1.smoothScrollToPosition(this, this.mState, i);
            }
        }
    }

    public void startInterceptRequestLayout() {
        int i = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i;
        if (i == 1 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
    }

    public void stopInterceptRequestLayout(boolean z) {
        int i = this.mInterceptRequestLayoutDepth;
        if (i < 1) {
            this.mInterceptRequestLayoutDepth = 1;
            i = 1;
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (i == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.mLayoutSuppressed = false;
                if (!(!this.mLayoutWasDefered || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public void viewRangeUpdate(int i, int i2, Object obj) {
        int i3;
        int i4;
        int A3V = this.mChildHelper.A01.A3V();
        int i5 = i + i2;
        for (int i6 = 0; i6 < A3V; i6++) {
            View A3U = this.mChildHelper.A01.A3U(i6);
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(A3U);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i4 = childViewHolderInt.mPosition) >= i && i4 < i5) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((C05831Bi) A3U.getLayoutParams()).A02 = true;
            }
        }
        AnonymousClass1Af r4 = this.mRecycler;
        ArrayList<AnonymousClass1Ah> arrayList = r4.A06;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                AnonymousClass1Ah r1 = arrayList.get(size);
                if (r1 != null && (i3 = r1.mPosition) >= i && i3 < i5) {
                    r1.addFlags(2);
                    AnonymousClass1Af.A02(r4, size);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (r2 == 20) goto L_0x0018;
     */
    static {
        /*
            r0 = 16843830(0x1010436, float:2.369658E-38)
            int[] r0 = new int[]{r0}
            androidx.recyclerview.widget.RecyclerView.NESTED_SCROLLING_ATTRS = r0
            int r2 = android.os.Build.VERSION.SDK_INT
            r0 = 18
            if (r2 == r0) goto L_0x0018
            r0 = 19
            if (r2 == r0) goto L_0x0018
            r1 = 20
            r0 = 0
            if (r2 != r1) goto L_0x0019
        L_0x0018:
            r0 = 1
        L_0x0019:
            androidx.recyclerview.widget.RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST = r0
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            java.lang.Class<android.util.AttributeSet> r1 = android.util.AttributeSet.class
            java.lang.Class r0 = java.lang.Integer.TYPE
            java.lang.Class[] r0 = new java.lang.Class[]{r2, r1, r0, r0}
            androidx.recyclerview.widget.RecyclerView.LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = r0
            X.1BP r0 = new X.1BP
            r0.<init>()
            androidx.recyclerview.widget.RecyclerView.sQuinticInterpolator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.<clinit>():void");
    }

    private void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0050, code lost:
        if (r4.mItemAnimator == null) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dispatchLayoutStep2() {
        /*
            r4 = this;
            r4.startInterceptRequestLayout()
            r4.onEnterLayoutOrScroll()
            X.1As r1 = r4.mState
            r0 = 6
            r1.A01(r0)
            X.1Aq r0 = r4.mAdapterHelper
            r0.A08()
            X.1As r1 = r4.mState
            X.1Aj r0 = r4.mAdapter
            int r0 = r0.getItemCount()
            r1.A07 = r0
            X.1As r0 = r4.mState
            r3 = 0
            r0.A00 = r3
            androidx.recyclerview.widget.RecyclerView$SavedState r0 = r4.mPendingSavedState
            if (r0 == 0) goto L_0x003a
            X.1Aj r0 = r4.mAdapter
            boolean r0 = r0.canRestoreState()
            if (r0 == 0) goto L_0x003a
            androidx.recyclerview.widget.RecyclerView$SavedState r0 = r4.mPendingSavedState
            android.os.Parcelable r1 = r0.A00
            if (r1 == 0) goto L_0x0037
            X.1Ag r0 = r4.mLayout
            r0.onRestoreInstanceState(r1)
        L_0x0037:
            r0 = 0
            r4.mPendingSavedState = r0
        L_0x003a:
            X.1As r2 = r4.mState
            r2.A0A = r3
            X.1Ag r1 = r4.mLayout
            X.1Af r0 = r4.mRecycler
            r1.onLayoutChildren(r0, r2)
            X.1As r2 = r4.mState
            r2.A06 = r3
            boolean r0 = r2.A05
            if (r0 == 0) goto L_0x0052
            X.1Al r1 = r4.mItemAnimator
            r0 = 1
            if (r1 != 0) goto L_0x0053
        L_0x0052:
            r0 = 0
        L_0x0053:
            r2.A05 = r0
            r0 = 4
            r2.A08 = r0
            r0 = 1
            r4.onExitLayoutOrScroll(r0)
            r4.stopInterceptRequestLayout(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchLayoutStep2():void");
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass1BM r1 = this.mOnItemTouchListeners.get(i);
            if (r1.A7B(this, motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = r1;
                return true;
            }
        }
        return false;
    }

    public static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        C05831Bi r6 = (C05831Bi) view.getLayoutParams();
        Rect rect2 = r6.A03;
        rect.set((view.getLeft() - rect2.left) - r6.leftMargin, (view.getTop() - rect2.top) - r6.topMargin, view.getRight() + rect2.right + r6.rightMargin, view.getBottom() + rect2.bottom + r6.bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id;
        loop0:
        while (true) {
            id = view.getId();
            while (true) {
                if (view.isFocused() || !(view instanceof ViewGroup) || !view.hasFocus()) {
                    return id;
                }
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                }
            }
        }
        return id;
    }

    private void initAutofill() {
        if (AnonymousClass07f.A00(this) == 0) {
            AnonymousClass07f.A03(this);
        }
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i = 0;
            if (actionIndex == 0) {
                i = 1;
            }
            this.mScrollPointerId = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    public void assertInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException(AnonymousClass006.A07("Cannot call this method unless RecyclerView is computing a layout or scrolling", exceptionLabel()));
        }
        throw new IllegalStateException(AnonymousClass006.A07(str, exceptionLabel()));
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException(AnonymousClass006.A07("Cannot call this method while RecyclerView is computing a layout or scrolling", exceptionLabel()));
            }
            throw new IllegalStateException(str);
        } else if (this.mDispatchScrollCounter > 0) {
            Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(AnonymousClass006.A07("", exceptionLabel())));
        }
    }

    public void defaultOnMeasure(int i, int i2) {
        setMeasuredDimension(AnonymousClass1Ag.chooseSize(i, getPaddingLeft() + getPaddingRight(), getMinimumWidth()), AnonymousClass1Ag.chooseSize(i2, getPaddingTop() + getPaddingBottom(), getMinimumHeight()));
    }

    public void dispatchChildAttached(View view) {
        int size;
        AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(view);
        AnonymousClass1Aj r0 = this.mAdapter;
        if (!(r0 == null || childViewHolderInt == null)) {
            r0.onViewAttachedToWindow(childViewHolderInt);
        }
        List<AnonymousClass1BX> list = this.mOnChildAttachStateListeners;
        if (list != null && list.size() - 1 >= 0) {
            this.mOnChildAttachStateListeners.get(size);
            throw new NullPointerException("onChildViewAttachedToWindow");
        }
    }

    public void dispatchChildDetached(View view) {
        int size;
        AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(view);
        AnonymousClass1Aj r0 = this.mAdapter;
        if (!(r0 == null || childViewHolderInt == null)) {
            r0.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<AnonymousClass1BX> list = this.mOnChildAttachStateListeners;
        if (list != null && list.size() - 1 >= 0) {
            this.mOnChildAttachStateListeners.get(size);
            throw new NullPointerException("onChildViewDetachedFromWindow");
        }
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().A03(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().A02(f, f2);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        if (r0.draw(r7) == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        if (r1 == false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b0, code lost:
        if (r1 == false) goto L_0x00b2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r7) {
        /*
        // Method dump skipped, instructions count: 293
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.draw(android.graphics.Canvas):void");
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0012 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View findContainingItemView(android.view.View r3) {
        /*
            r2 = this;
        L_0x0000:
            android.view.ViewParent r1 = r3.getParent()
            if (r1 == 0) goto L_0x0010
            if (r1 == r2) goto L_0x0010
            boolean r0 = r1 instanceof android.view.View
            if (r0 == 0) goto L_0x0010
            r3 = r1
            android.view.View r3 = (android.view.View) r3
            goto L_0x0000
        L_0x0010:
            if (r1 == r2) goto L_0x0013
            r3 = 0
        L_0x0013:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findContainingItemView(android.view.View):android.view.View");
    }

    public AnonymousClass1Ah findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    public int getChildAdapterPosition(View view) {
        AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAbsoluteAdapterPosition();
        }
        return -1;
    }

    public int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    public int getChildLayoutPosition(View view) {
        AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public AnonymousClass1Ah getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        StringBuilder sb = new StringBuilder("View ");
        sb.append(view);
        sb.append(" is not a direct child of ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r1.isInvalid() == false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Rect getItemDecorInsetsForChild(android.view.View r9) {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.getItemDecorInsetsForChild(android.view.View):android.graphics.Rect");
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().A02;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        if (r1 >= 30.0f) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttachedToWindow() {
        /*
            r4 = this;
            super.onAttachedToWindow()
            r2 = 0
            r4.mLayoutOrScrollCounter = r2
            r1 = 1
            r4.mIsAttached = r1
            boolean r0 = r4.mFirstLayoutComplete
            if (r0 == 0) goto L_0x005e
            boolean r0 = r4.isLayoutRequested()
            if (r0 != 0) goto L_0x005e
        L_0x0013:
            r4.mFirstLayoutComplete = r1
            X.1Ag r0 = r4.mLayout
            if (r0 == 0) goto L_0x001c
            r0.dispatchAttachedToWindow(r4)
        L_0x001c:
            r4.mPostedAnimatorRunner = r2
            java.lang.ThreadLocal<X.1Ai> r3 = X.AnonymousClass1Ai.A05
            java.lang.Object r0 = r3.get()
            X.1Ai r0 = (X.AnonymousClass1Ai) r0
            r4.mGapWorker = r0
            if (r0 != 0) goto L_0x0053
            X.1Ai r0 = new X.1Ai
            r0.<init>()
            r4.mGapWorker = r0
            android.view.Display r1 = r4.getDisplay()
            boolean r0 = r4.isInEditMode()
            if (r0 != 0) goto L_0x005b
            if (r1 == 0) goto L_0x005b
            float r1 = r1.getRefreshRate()
            r0 = 1106247680(0x41f00000, float:30.0)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x005b
        L_0x0047:
            X.1Ai r2 = r4.mGapWorker
            r0 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r0 = r0 / r1
            long r0 = (long) r0
            r2.A00 = r0
            r3.set(r2)
        L_0x0053:
            X.1Ai r0 = r4.mGapWorker
            java.util.ArrayList<androidx.recyclerview.widget.RecyclerView> r0 = r0.A02
            r0.add(r4)
            return
        L_0x005b:
            r1 = 1114636288(0x42700000, float:60.0)
            goto L_0x0047
        L_0x005e:
            r1 = 0
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnonymousClass1Al r0 = this.mItemAnimator;
        if (r0 != null) {
            r0.A06();
        }
        stopScroll();
        this.mIsAttached = false;
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 != null) {
            r1.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        do {
        } while (AnonymousClass1B9.A03.A19() != null);
        AnonymousClass1Ai r02 = this.mGapWorker;
        if (r02 != null) {
            r02.A02.remove(this);
            this.mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList<AnonymousClass1B2> arrayList = this.mItemDecorations;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i);
        }
    }

    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable parcelable;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            parcelable = savedState2.A00;
        } else {
            AnonymousClass1Ag r0 = this.mLayout;
            if (r0 != null) {
                parcelable = r0.onSaveInstanceState();
            } else {
                parcelable = null;
            }
        }
        savedState.A00 = parcelable;
        return savedState;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            invalidateGlows();
        }
    }

    public boolean removeAnimatingView(View view) {
        boolean z;
        startInterceptRequestLayout();
        AnonymousClass1Am r4 = this.mChildHelper;
        AnonymousClass1B5 r3 = r4.A01;
        int A5a = r3.A5a(view);
        if (A5a == -1) {
            AnonymousClass1Am.A01(r4, view);
        } else {
            AnonymousClass1B6 r1 = r4.A00;
            if (r1.A06(A5a)) {
                r1.A07(A5a);
                AnonymousClass1Am.A01(r4, view);
                r3.A9E(A5a);
            } else {
                z = false;
                stopInterceptRequestLayout(!z);
                return z;
            }
        }
        z = true;
        AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(view);
        this.mRecycler.A08(childViewHolderInt);
        this.mRecycler.A09(childViewHolderInt);
        stopInterceptRequestLayout(!z);
        return z;
    }

    public void removeDetachedView(View view, boolean z) {
        AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                StringBuilder sb = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(childViewHolderInt);
                sb.append(exceptionLabel());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    public void scrollStep(int i, int i2, int[] iArr) {
        int i3;
        int i4;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        Trace.beginSection(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        if (i != 0) {
            i3 = this.mLayout.scrollHorizontallyBy(i, this.mRecycler, this.mState);
        } else {
            i3 = 0;
        }
        if (i2 != 0) {
            i4 = this.mLayout.scrollVerticallyBy(i2, this.mRecycler, this.mState);
        } else {
            i4 = 0;
        }
        Trace.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = i3;
            iArr[1] = i4;
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public boolean setChildImportantForAccessibilityInternal(AnonymousClass1Ah r2, int i) {
        if (isComputingLayout()) {
            r2.mPendingAccessibilityState = i;
            this.mPendingAccessibilityImportanceChange.add(r2);
            return false;
        }
        r2.itemView.setImportantForAccessibility(i);
        return true;
    }

    public void setNestedScrollingEnabled(boolean z) {
        AnonymousClass07J scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.A02) {
            scrollingChildHelper.A04.stopNestedScroll();
        }
        scrollingChildHelper.A02 = z;
    }

    public void setScrollingTouchSlop(int i) {
        int i2;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 0) {
            if (i != 1) {
                Log.w(TAG, AnonymousClass006.A04("setScrollingTouchSlop(): bad argument constant ", i, "; using default value"));
            } else {
                i2 = viewConfiguration.getScaledPagingTouchSlop();
                this.mTouchSlop = i2;
            }
        }
        i2 = viewConfiguration.getScaledTouchSlop();
        this.mTouchSlop = i2;
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i;
        if (!isComputingLayout()) {
            return false;
        }
        if (accessibilityEvent == null || (i = accessibilityEvent.getContentChangeTypes()) == 0) {
            i = 0;
        }
        this.mEatenAccessibilityChangeFlags = i | this.mEatenAccessibilityChangeFlags;
        return true;
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setOnFlingListener(AbstractC05811Bb r1) {
        this.mOnFlingListener = r1;
    }

    public void setOnScrollListener(AnonymousClass1CG r1) {
        this.mScrollListener = r1;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecyclerListener(AnonymousClass1BT r1) {
        this.mRecyclerListener = r1;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        super.addFocusables(arrayList, i, i2);
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mObserver = new AnonymousClass1Ap(this);
        this.mRecycler = new AnonymousClass1Af(this);
        this.mViewInfoStore = new AnonymousClass1At();
        this.mUpdateChildViewsRunnable = new AnonymousClass1BD(this);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new AnonymousClass1BW();
        this.mItemAnimator = new AnonymousClass1Ef();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new AnonymousClass1Ae(this);
        this.mPrefetchRegistry = new AnonymousClass1Ak();
        this.mState = new AnonymousClass1As();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new AnonymousClass1BB(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new AnonymousClass1BK(this);
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new AnonymousClass1Av(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = AnonymousClass07g.A00(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = AnonymousClass07g.A01(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.A04 = this.mItemAnimatorListener;
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new AnonymousClass1B3(this));
        int[] iArr = AnonymousClass1BY.A00;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        AnonymousClass07f.A04(this, context, iArr, attributeSet, obtainStyledAttributes, i);
        String string = obtainStyledAttributes.getString(8);
        if (obtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(1, true);
        boolean z = obtainStyledAttributes.getBoolean(3, false);
        this.mEnableFastScroller = z;
        if (z) {
            initFastScroller((StateListDrawable) obtainStyledAttributes.getDrawable(6), obtainStyledAttributes.getDrawable(7), (StateListDrawable) obtainStyledAttributes.getDrawable(4), obtainStyledAttributes.getDrawable(5));
        }
        obtainStyledAttributes.recycle();
        createLayoutManager(context, string, attributeSet, i, 0);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, NESTED_SCROLLING_ATTRS, i, 0);
        AnonymousClass07f.A04(this, context, NESTED_SCROLLING_ATTRS, attributeSet, obtainStyledAttributes2, i);
        boolean z2 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z2);
    }

    public void addItemDecoration(AnonymousClass1B2 r2) {
        addItemDecoration(r2, -1);
    }

    public void addItemDecoration(AnonymousClass1B2 r3, int i) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 != null) {
            r1.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.mItemDecorations.add(r3);
        } else {
            this.mItemDecorations.add(i, r3);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().A06(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().A06(i, i2, iArr, iArr2, i3);
    }

    public final void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        AnonymousClass07J.A00(getScrollingChildHelper(), i, i2, i3, i4, iArr, i5, iArr2);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return AnonymousClass07J.A00(getScrollingChildHelper(), i, i2, i3, i4, iArr, 0, null);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return AnonymousClass07J.A00(getScrollingChildHelper(), i, i2, i3, i4, iArr, i5, null);
    }

    public AnonymousClass1Ah findViewHolderForPosition(int i) {
        return findViewHolderForPosition(i, false);
    }

    public AnonymousClass1Ah findViewHolderForPosition(int i, boolean z) {
        int layoutPosition;
        int A3V = this.mChildHelper.A01.A3V();
        AnonymousClass1Ah r1 = null;
        for (int i2 = 0; i2 < A3V; i2++) {
            AnonymousClass1Ah childViewHolderInt = getChildViewHolderInt(this.mChildHelper.A01.A3U(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (z) {
                    layoutPosition = childViewHolderInt.mPosition;
                } else {
                    layoutPosition = childViewHolderInt.getLayoutPosition();
                }
                if (layoutPosition == i) {
                    AnonymousClass1Am r0 = this.mChildHelper;
                    if (!r0.A02.contains(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    r1 = childViewHolderInt;
                } else {
                    continue;
                }
            }
        }
        return r1;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 != null) {
            return r1.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException(AnonymousClass006.A07("RecyclerView has no LayoutManager", exceptionLabel()));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        AnonymousClass1Ag r0 = this.mLayout;
        if (r0 != null) {
            return r0.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException(AnonymousClass006.A07("RecyclerView has no LayoutManager", exceptionLabel()));
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().A04(0);
    }

    public boolean hasNestedScrollingParent(int i) {
        return getScrollingChildHelper().A04(i);
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    public void onExitLayoutOrScroll(boolean z) {
        int i = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i;
        if (i < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public void smoothScrollBy(int i, int i2) {
        smoothScrollBy(i, i2, null);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
        smoothScrollBy(i, i2, interpolator, Integer.MIN_VALUE);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator, int i3) {
        smoothScrollBy(i, i2, interpolator, i3, false);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator, int i3, boolean z) {
        AnonymousClass1Ag r1 = this.mLayout;
        if (r1 == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            int i4 = 0;
            if (!r1.canScrollHorizontally()) {
                i = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                i2 = 0;
            }
            if (i != 0 || i2 != 0) {
                if (i3 == Integer.MIN_VALUE || i3 > 0) {
                    if (z) {
                        if (i != 0) {
                            i4 = 1;
                        }
                        if (i2 != 0) {
                            i4 |= 2;
                        }
                        startNestedScroll(i4, 1);
                    }
                    this.mViewFlinger.A01(i, i2, i3, interpolator);
                    return;
                }
                scrollBy(i, i2);
            }
        }
    }

    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().A05(i, 0);
    }

    public boolean startNestedScroll(int i, int i2) {
        return getScrollingChildHelper().A05(i, i2);
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().A01(0);
    }

    public void stopNestedScroll(int i) {
        getScrollingChildHelper().A01(i);
    }
}
