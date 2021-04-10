package androidx.viewpager.widget;

import X.AbstractC00660Cc;
import X.AbstractC00720Cp;
import X.AnonymousClass006;
import X.AnonymousClass06n;
import X.AnonymousClass07Q;
import X.AnonymousClass0Cn;
import X.AnonymousClass0Co;
import X.AnonymousClass0Cq;
import X.AnonymousClass0Cr;
import X.AnonymousClass0rK;
import X.C00670Ch;
import X.C00700Cl;
import X.C00710Cm;
import X.C00730Cs;
import X.C07160rJ;
import X.RunnableC00690Cj;
import X.animation.InterpolatorC00680Ci;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
    public static final String ACCESSIBILITY_CLASS_NAME = "androidx.viewpager.widget.ViewPager";
    public static final int CLOSE_ENOUGH = 2;
    public static final Comparator<C00700Cl> COMPARATOR = new C00670Ch();
    public static final boolean DEBUG = false;
    public static final int DEFAULT_GUTTER_SIZE = 16;
    public static final int DEFAULT_OFFSCREEN_PAGES = 1;
    public static final int DRAW_ORDER_DEFAULT = 0;
    public static final int DRAW_ORDER_FORWARD = 1;
    public static final int DRAW_ORDER_REVERSE = 2;
    public static final int INVALID_POINTER = -1;
    public static final int[] LAYOUT_ATTRS = {16842931};
    public static final int MAX_SETTLE_DURATION = 600;
    public static final int MIN_DISTANCE_FOR_FLING = 25;
    public static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final String TAG = "ViewPager";
    public static final Interpolator sInterpolator = new animation.InterpolatorC00680Ci();
    public static final C00730Cs sPositionComparator = new C00730Cs();
    public int mActivePointerId = -1;
    public AbstractC00660Cc mAdapter;
    public List<AnonymousClass0Cn> mAdapterChangeListeners;
    public int mBottomPageBounds;
    public boolean mCalledSuper;
    public int mCloseEnough;
    public int mCurItem;
    public int mDecorChildCount;
    public int mDefaultGutterSize;
    public boolean mDragInGutterEnabled = true;
    public int mDrawingOrder;
    public ArrayList<View> mDrawingOrderedChildren;
    public final Runnable mEndScrollRunnable = new RunnableC00690Cj(this);
    public int mExpectedAdapterCount;
    public long mFakeDragBeginTime;
    public boolean mFakeDragging;
    public boolean mFirstLayout = true;
    public float mFirstOffset = -3.4028235E38f;
    public int mFlingDistance;
    public int mGutterSize;
    public boolean mInLayout;
    public float mInitialMotionX;
    public float mInitialMotionY;
    public AnonymousClass0Co mInternalPageChangeListener;
    public boolean mIsBeingDragged;
    public boolean mIsScrollStarted;
    public boolean mIsUnableToDrag;
    public final ArrayList<C00700Cl> mItems = new ArrayList<>();
    public float mLastMotionX;
    public float mLastMotionY;
    public float mLastOffset = Float.MAX_VALUE;
    public EdgeEffect mLeftEdge;
    public Drawable mMarginDrawable;
    public int mMaximumVelocity;
    public int mMinimumVelocity;
    public AnonymousClass0Cq mObserver;
    public int mOffscreenPageLimit = 1;
    public AnonymousClass0Co mOnPageChangeListener;
    public List<AnonymousClass0Co> mOnPageChangeListeners;
    public int mPageMargin;
    public AbstractC00720Cp mPageTransformer;
    public int mPageTransformerLayerType;
    public boolean mPopulatePending;
    public Parcelable mRestoredAdapterState = null;
    public ClassLoader mRestoredClassLoader = null;
    public int mRestoredCurItem = -1;
    public EdgeEffect mRightEdge;
    public int mScrollState = 0;
    public Scroller mScroller;
    public boolean mScrollingCacheEnabled;
    public final C00700Cl mTempItem = new C00700Cl();
    public final Rect mTempRect = new Rect();
    public int mTopPageBounds;
    public int mTouchSlop;
    public VelocityTracker mVelocityTracker;

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DecorView {
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass0Cr();
        public int A00;
        public Parcelable A01;
        public ClassLoader A02;

        public final String toString() {
            StringBuilder sb = new StringBuilder("FragmentPager.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" position=");
            sb.append(this.A00);
            sb.append("}");
            return sb.toString();
        }

        @Override // androidx.customview.view.AbsSavedState
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.A00);
            parcel.writeParcelable(this.A01, i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.A00 = parcel.readInt();
            this.A01 = parcel.readParcelable(classLoader);
            this.A02 = classLoader;
        }

        public SavedState(@NonNull Parcelable parcelable) {
            super(parcelable);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void removeNonDecorViews() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((C00710Cm) getChildAt(i).getLayoutParams()).A02) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        if (this.mLeftEdge.isFinished() || this.mRightEdge.isFinished()) {
            return true;
        }
        return false;
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addTouchables(ArrayList<View> arrayList) {
        C00700Cl infoForChild;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.A02 == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!pageScrolled(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        postInvalidateOnAnimation();
    }

    public C00700Cl infoForChild(View view) {
        ArrayList<C00700Cl> arrayList = this.mItems;
        if (0 >= arrayList.size()) {
            return null;
        }
        arrayList.get(0);
        throw null;
    }

    public C00700Cl infoForPosition(int i) {
        int i2 = 0;
        while (true) {
            ArrayList<C00700Cl> arrayList = this.mItems;
            if (i2 >= arrayList.size()) {
                return null;
            }
            C00700Cl r1 = arrayList.get(i2);
            if (r1.A02 == i) {
                return r1;
            }
            i2++;
        }
    }

    public void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f);
        this.mCloseEnough = (int) (2.0f * f);
        this.mDefaultGutterSize = (int) (f * 16.0f);
        setAccessibilityDelegate(((AnonymousClass06n) new C07160rJ(this)).A00);
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        setOnApplyWindowInsetsListener(new AnonymousClass07Q(new AnonymousClass0rK(this)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0065, code lost:
        if (r1 == 80) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b7, code lost:
        if (r9 == false) goto L_0x0075;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r16, int r17) {
        /*
        // Method dump skipped, instructions count: 244
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onMeasure(int, int):void");
    }

    public void setAdapter(@Nullable AbstractC00660Cc r5) {
        this.mAdapter = r5;
        this.mExpectedAdapterCount = 0;
        List<AnonymousClass0Cn> list = this.mAdapterChangeListeners;
        if (!(list == null || list.isEmpty())) {
            int size = this.mAdapterChangeListeners.size();
            for (int i = 0; i < size; i++) {
                this.mAdapterChangeListeners.get(i).A5g(this, null, r5);
            }
        }
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w(TAG, AnonymousClass006.A03("Requested offscreen page limit ", i, " too small; defaulting to ", 1));
            i = 1;
        }
        if (i != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i;
            populate();
        }
    }

    private void completeScroll(boolean z) {
        boolean z2 = false;
        if (this.mScrollState == 2) {
            z2 = true;
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        pageScrolled(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        int i = 0;
        while (true) {
            ArrayList<C00700Cl> arrayList = this.mItems;
            if (i >= arrayList.size()) {
                break;
            }
            arrayList.get(i);
            i++;
        }
        if (!z2) {
            return;
        }
        if (z) {
            postOnAnimation(this.mEndScrollRunnable);
        } else {
            this.mEndScrollRunnable.run();
        }
    }

    private void dispatchOnPageScrolled(int i, float f, int i2) {
        AnonymousClass0Co r0 = this.mOnPageChangeListener;
        if (r0 != null) {
            r0.A6P(i, f, i2);
        }
        List<AnonymousClass0Co> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                AnonymousClass0Co r02 = this.mOnPageChangeListeners.get(i3);
                if (r02 != null) {
                    r02.A6P(i, f, i2);
                }
            }
        }
        AnonymousClass0Co r03 = this.mInternalPageChangeListener;
        if (r03 != null) {
            r03.A6P(i, f, i2);
        }
    }

    private void dispatchOnPageSelected(int i) {
        AnonymousClass0Co r0 = this.mOnPageChangeListener;
        if (r0 != null) {
            r0.A6Q(i);
        }
        List<AnonymousClass0Co> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                AnonymousClass0Co r02 = this.mOnPageChangeListeners.get(i2);
                if (r02 != null) {
                    r02.A6Q(i);
                }
            }
        }
        AnonymousClass0Co r03 = this.mInternalPageChangeListener;
        if (r03 != null) {
            r03.A6Q(i);
        }
    }

    private void dispatchOnScrollStateChanged(int i) {
        AnonymousClass0Co r0 = this.mOnPageChangeListener;
        if (r0 != null) {
            r0.A6O(i);
        }
        List<AnonymousClass0Co> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                AnonymousClass0Co r02 = this.mOnPageChangeListeners.get(i2);
                if (r02 != null) {
                    r02.A6O(i);
                }
            }
        }
        AnonymousClass0Co r03 = this.mInternalPageChangeListener;
        if (r03 != null) {
            r03.A6O(i);
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.left = view.getLeft();
            rect.right = view.getRight();
            rect.top = view.getTop();
            rect.bottom = view.getBottom();
            ViewParent parent = view.getParent();
            while ((parent instanceof ViewGroup) && parent != this) {
                ViewGroup viewGroup = (ViewGroup) parent;
                rect.left += viewGroup.getLeft();
                rect.right += viewGroup.getRight();
                rect.top += viewGroup.getTop();
                rect.bottom += viewGroup.getBottom();
                parent = viewGroup.getParent();
            }
        }
        return rect;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0017, code lost:
        if (r8.mCalledSuper != false) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean pageScrolled(int r9) {
        /*
            r8 = this;
            java.util.ArrayList<X.0Cl> r0 = r8.mItems
            int r0 = r0.size()
            java.lang.String r5 = "onPageScrolled did not call superclass implementation"
            r7 = 0
            if (r0 != 0) goto L_0x001a
            boolean r0 = r8.mFirstLayout
            if (r0 != 0) goto L_0x0019
            r8.mCalledSuper = r7
            r0 = 0
            r8.onPageScrolled(r7, r0, r7)
            boolean r0 = r8.mCalledSuper
            if (r0 == 0) goto L_0x0042
        L_0x0019:
            return r7
        L_0x001a:
            X.0Cl r6 = r8.infoForCurrentScrollPosition()
            int r1 = r8.getClientWidth()
            int r0 = r8.mPageMargin
            int r4 = r1 + r0
            float r3 = (float) r0
            float r0 = (float) r1
            float r3 = r3 / r0
            int r2 = r6.A02
            float r1 = (float) r9
            float r1 = r1 / r0
            float r0 = r6.A00
            float r1 = r1 - r0
            float r0 = r6.A01
            float r0 = r0 + r3
            float r1 = r1 / r0
            float r0 = (float) r4
            float r0 = r0 * r1
            int r0 = (int) r0
            r8.mCalledSuper = r7
            r8.onPageScrolled(r2, r1, r0)
            boolean r0 = r8.mCalledSuper
            if (r0 == 0) goto L_0x0042
            r0 = 1
            return r0
        L_0x0042:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.pageScrolled(int):boolean");
    }

    private boolean performDrag(float f) {
        this.mLastMotionX = f;
        getScrollX();
        getClientWidth();
        ArrayList<C00700Cl> arrayList = this.mItems;
        arrayList.get(0);
        arrayList.get(arrayList.size() - 1);
        throw null;
    }

    private void recomputeScrollPosition(int i, int i2, int i3, int i4) {
        int i5;
        float f;
        if (i2 <= 0 || this.mItems.isEmpty()) {
            C00700Cl infoForPosition = infoForPosition(this.mCurItem);
            if (infoForPosition != null) {
                f = Math.min(infoForPosition.A00, this.mLastOffset);
            } else {
                f = 0.0f;
            }
            i5 = (int) (f * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (i5 != getScrollX()) {
                completeScroll(false);
            } else {
                return;
            }
        } else if (!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(this.mCurItem * getClientWidth());
            return;
        } else {
            i5 = (int) ((((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))) * ((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3)));
        }
        scrollTo(i5, getScrollY());
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.mDrawingOrderedChildren.add(getChildAt(i));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    public void addOnAdapterChangeListener(@NonNull AnonymousClass0Cn r2) {
        List list = this.mAdapterChangeListeners;
        if (list == null) {
            list = new ArrayList();
            this.mAdapterChangeListeners = list;
        }
        list.add(r2);
    }

    public void addOnPageChangeListener(@NonNull AnonymousClass0Co r2) {
        List list = this.mOnPageChangeListeners;
        if (list == null) {
            list = new ArrayList();
            this.mOnPageChangeListeners = list;
        }
        list.add(r2);
    }

    public boolean beginFakeDrag() {
        boolean z = false;
        if (!this.mIsBeingDragged) {
            z = true;
            this.mFakeDragging = true;
            setScrollState(1);
            this.mLastMotionX = 0.0f;
            this.mInitialMotionX = 0.0f;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            } else {
                velocityTracker.clear();
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
            this.mVelocityTracker.addMovement(obtain);
            obtain.recycle();
            this.mFakeDragBeginTime = uptimeMillis;
        }
        return z;
    }

    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        int i4;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i2 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom() && canScroll(childAt, true, i, i5 - childAt.getLeft(), i4 - childAt.getTop())) {
                    return true;
                }
            }
        }
        if (!z || !view.canScrollHorizontally(-i)) {
            return false;
        }
        return true;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof C00710Cm) || !super.checkLayoutParams(layoutParams)) {
            return false;
        }
        return true;
    }

    public void clearOnPageChangeListeners() {
        List<AnonymousClass0Co> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    public float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    public void endFakeDrag() {
        if (this.mFakeDragging) {
            endDrag();
            this.mFakeDragging = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C00710Cm();
    }

    public int getChildDrawingOrder(int i, int i2) {
        if (this.mDrawingOrder == 2) {
            i2 = (i - 1) - i2;
        }
        this.mDrawingOrderedChildren.get(i2).getLayoutParams();
        return 0;
    }

    public boolean isGutterDrag(float f, float f2) {
        if (this.mDragInGutterEnabled) {
            return false;
        }
        if ((f >= ((float) this.mGutterSize) || f2 <= 0.0f) && (f <= ((float) (getWidth() - this.mGutterSize)) || f2 >= 0.0f)) {
            return false;
        }
        return true;
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            resetTouch();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) {
                completeScroll(false);
                this.mIsBeingDragged = false;
            } else {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i = this.mActivePointerId;
            if (i != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i);
                float x2 = motionEvent.getX(findPointerIndex);
                float f2 = x2 - this.mLastMotionX;
                float abs = Math.abs(f2);
                float y2 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.mInitialMotionY);
                if (f2 == 0.0f || isGutterDrag(this.mLastMotionX, f2) || !canScroll(this, false, (int) f2, (int) x2, (int) y2)) {
                    float f3 = (float) this.mTouchSlop;
                    if (abs > f3 && abs * 0.5f > abs2) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        if (f2 > 0.0f) {
                            f = this.mInitialMotionX + ((float) this.mTouchSlop);
                        } else {
                            f = this.mInitialMotionX - ((float) this.mTouchSlop);
                        }
                        this.mLastMotionX = f;
                        this.mLastMotionY = y2;
                        setScrollingCacheEnabled(true);
                    } else if (abs2 > f3) {
                        this.mIsUnableToDrag = true;
                    }
                    if (this.mIsBeingDragged) {
                        performDrag(x2);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    this.mLastMotionX = x2;
                    this.mLastMotionY = y2;
                    this.mIsUnableToDrag = true;
                    return false;
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
            this.mVelocityTracker = velocityTracker;
        }
        velocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    @androidx.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r12, float r13, int r14) {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onPageScrolled(int, float, int):void");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(((AbsSavedState) savedState).A00);
        this.mRestoredCurItem = savedState.A00;
        this.mRestoredAdapterState = savedState.A01;
        this.mRestoredClassLoader = savedState.A02;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (!this.mFakeDragging) {
            z = false;
            if (motionEvent.getAction() == 0) {
                motionEvent.getEdgeFlags();
            }
        }
        return z;
    }

    public boolean pageLeft() {
        int i = this.mCurItem;
        if (i <= 0) {
            return false;
        }
        setCurrentItem(i - 1, true);
        return true;
    }

    public void removeOnAdapterChangeListener(@NonNull AnonymousClass0Cn r2) {
        List<AnonymousClass0Cn> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(r2);
        }
    }

    public void removeOnPageChangeListener(@NonNull AnonymousClass0Co r2) {
        List<AnonymousClass0Co> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(r2);
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public AnonymousClass0Co setInternalPageChangeListener(AnonymousClass0Co r2) {
        AnonymousClass0Co r0 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = r2;
        return r0;
    }

    public void setPageMargin(int i) {
        int i2 = this.mPageMargin;
        this.mPageMargin = i;
        int width = getWidth();
        recomputeScrollPosition(width, width, i, i2);
        requestLayout();
    }

    public void setScrollState(int i) {
        if (this.mScrollState != i) {
            this.mScrollState = i;
            dispatchOnScrollStateChanged(i);
        }
    }

    private int determineTargetPage(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.mFlingDistance || Math.abs(i2) <= this.mMinimumVelocity) {
            float f2 = 0.6f;
            if (i >= this.mCurItem) {
                f2 = 0.4f;
            }
            i += (int) (f + f2);
        } else if (i2 <= 0) {
            i++;
        }
        ArrayList<C00700Cl> arrayList = this.mItems;
        if (arrayList.size() > 0) {
            return Math.max(arrayList.get(0).A02, Math.min(i, arrayList.get(arrayList.size() - 1).A02));
        }
        return i;
    }

    private void enableLayers(boolean z) {
        int i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (z) {
                i = this.mPageTransformerLayerType;
            } else {
                i = 0;
            }
            getChildAt(i2).setLayerType(i, null);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private C00700Cl infoForCurrentScrollPosition() {
        float f;
        float f2;
        int i;
        int clientWidth = getClientWidth();
        if (clientWidth > 0) {
            float f3 = (float) clientWidth;
            f = ((float) getScrollX()) / f3;
            f2 = ((float) this.mPageMargin) / f3;
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        C00700Cl r11 = null;
        int i2 = 0;
        boolean z = true;
        int i3 = -1;
        float f4 = 0.0f;
        float f5 = 0.0f;
        while (true) {
            ArrayList<C00700Cl> arrayList = this.mItems;
            if (i2 >= arrayList.size()) {
                break;
            }
            C00700Cl r3 = arrayList.get(i2);
            if (!z && r3.A02 != (i = i3 + 1)) {
                r3 = this.mTempItem;
                r3.A00 = f4 + f5 + f2;
                r3.A02 = i;
                r3.A01 = 1.0f;
                i2--;
            }
            f4 = r3.A00;
            f5 = r3.A01;
            float f6 = f5 + f4 + f2;
            if (!z && f < f4) {
                break;
            } else if (f < f6 || i2 == arrayList.size() - 1) {
                return r3;
            } else {
                i3 = r3.A02;
                i2++;
                r11 = r3;
                z = false;
            }
        }
        return r11;
    }

    public static boolean isDecorView(@NonNull View view) {
        if (view.getClass().getAnnotation(DecorView.class) != null) {
            return true;
        }
        return false;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i = 0;
            if (actionIndex == 0) {
                i = 1;
            }
            this.mLastMotionX = motionEvent.getX(i);
            this.mActivePointerId = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void scrollToItem(int i, boolean z, int i2, boolean z2) {
        int i3;
        C00700Cl infoForPosition = infoForPosition(i);
        if (infoForPosition != null) {
            i3 = (int) (((float) getClientWidth()) * Math.max(this.mFirstOffset, Math.min(infoForPosition.A00, this.mLastOffset)));
        } else {
            i3 = 0;
        }
        if (z) {
            smoothScrollTo(i3, 0, i2);
            if (z2) {
                dispatchOnPageSelected(i);
                return;
            }
            return;
        }
        if (z2) {
            dispatchOnPageSelected(i);
        }
        completeScroll(false);
        scrollTo(i3, 0);
        pageScrolled(i3);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        C00700Cl infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.A02 == this.mCurItem) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
            if (descendantFocusability == 262144 && size != arrayList.size()) {
                return;
            }
        }
        if (!isFocusable()) {
            return;
        }
        if ((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) {
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        C00710Cm r2 = (C00710Cm) layoutParams;
        boolean isDecorView = r2.A02 | isDecorView(view);
        r2.A02 = isDecorView;
        if (!this.mInLayout) {
            super.addView(view, i, layoutParams);
        } else if (!isDecorView) {
            r2.A00 = true;
            addViewInLayout(view, i, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007c, code lost:
        if (r1 >= r0) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a0, code lost:
        if (r1 <= r0) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00aa, code lost:
        if (r8 != 1) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean arrowScroll(int r8) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.arrowScroll(int):boolean");
    }

    public void dataSetChanged() {
        throw null;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        C00700Cl infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.A02 == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean z = false;
        if (getOverScrollMode() != 0) {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
            return;
        }
        if (!this.mLeftEdge.isFinished()) {
            int save = canvas.save();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int width = getWidth();
            canvas.rotate(270.0f);
            canvas.translate((float) ((-height) + getPaddingTop()), this.mFirstOffset * ((float) width));
            this.mLeftEdge.setSize(height, width);
            z = false | this.mLeftEdge.draw(canvas);
            canvas.restoreToCount(save);
        }
        if (!this.mRightEdge.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            canvas.rotate(90.0f);
            canvas.translate((float) (-getPaddingTop()), (-(this.mLastOffset + 1.0f)) * ((float) width2));
            this.mRightEdge.setSize(height2, width2);
            z |= this.mRightEdge.draw(canvas);
            canvas.restoreToCount(save2);
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    public boolean executeKeyEvent(@NonNull KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        int i = 2;
        if (keyCode != 21) {
            if (keyCode != 22) {
                if (keyCode != 61) {
                    return false;
                }
                if (!keyEvent.hasNoModifiers()) {
                    i = 1;
                    if (!keyEvent.hasModifiers(1)) {
                        return false;
                    }
                }
                return arrowScroll(i);
            } else if (!keyEvent.hasModifiers(2)) {
                return arrowScroll(66);
            } else {
                return false;
            }
        } else if (keyEvent.hasModifiers(2)) {
            return pageLeft();
        } else {
            return arrowScroll(17);
        }
    }

    @Nullable
    public AbstractC00660Cc getAdapter() {
        return null;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public C00700Cl infoForAnyChild(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (!(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    public boolean isDragInGutterEnabled() {
        return this.mDragInGutterEnabled;
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null) {
            this.mItems.size();
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        C00700Cl infoForChild;
        int max;
        int max2;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                C00710Cm r3 = (C00710Cm) childAt.getLayoutParams();
                if (r3.A02) {
                    int i9 = r3.A01;
                    int i10 = i9 & 7;
                    int i11 = i9 & 112;
                    if (i10 == 1) {
                        max = Math.max((i5 - childAt.getMeasuredWidth()) >> 1, paddingLeft);
                    } else if (i10 != 3) {
                        max = paddingLeft;
                        if (i10 == 5) {
                            max = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                        }
                    } else {
                        max = paddingLeft;
                        paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                    }
                    if (i11 == 16) {
                        max2 = Math.max((i6 - childAt.getMeasuredHeight()) >> 1, paddingTop);
                    } else if (i11 != 48) {
                        max2 = paddingTop;
                        if (i11 == 80) {
                            max2 = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            paddingBottom += childAt.getMeasuredHeight();
                        }
                    } else {
                        max2 = paddingTop;
                        paddingTop = childAt.getMeasuredHeight() + paddingTop;
                    }
                    int i12 = max + scrollX;
                    childAt.layout(i12, max2, childAt.getMeasuredWidth() + i12, childAt.getMeasuredHeight() + max2);
                    i7++;
                }
            }
        }
        int i13 = (i5 - paddingLeft) - paddingRight;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8) {
                C00710Cm r12 = (C00710Cm) childAt2.getLayoutParams();
                if (!r12.A02 && (infoForChild = infoForChild(childAt2)) != null) {
                    float f = (float) i13;
                    int i15 = ((int) (infoForChild.A00 * f)) + paddingLeft;
                    if (r12.A00) {
                        r12.A00 = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f * 0.0f), 1073741824), View.MeasureSpec.makeMeasureSpec((i6 - paddingTop) - paddingBottom, 1073741824));
                    }
                    childAt2.layout(i15, paddingTop, childAt2.getMeasuredWidth() + i15, childAt2.getMeasuredHeight() + paddingTop);
                }
            }
        }
        this.mTopPageBounds = paddingTop;
        this.mBottomPageBounds = i6 - paddingBottom;
        this.mDecorChildCount = i7;
        if (this.mFirstLayout) {
            z2 = false;
            scrollToItem(this.mCurItem, false, 0, false);
        } else {
            z2 = false;
        }
        this.mFirstLayout = z2;
    }

    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3;
        C00700Cl infoForChild;
        int childCount = getChildCount();
        int i4 = -1;
        if ((i & 2) != 0) {
            i4 = childCount;
            i2 = 0;
            i3 = 1;
        } else {
            i2 = childCount - 1;
            i3 = -1;
        }
        while (i2 != i4) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.A02 == this.mCurItem && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.A00 = this.mCurItem;
        return savedState;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            int i5 = this.mPageMargin;
            recomputeScrollPosition(i, i3, i5, i5);
        }
    }

    public boolean pageRight() {
        return false;
    }

    public void smoothScrollTo(int i, int i2, int i3) {
        int scrollX;
        int i4;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.isFinished()) {
            scrollX = getScrollX();
        } else {
            if (this.mIsScrollStarted) {
                scrollX = this.mScroller.getCurrX();
            } else {
                scrollX = this.mScroller.getStartX();
            }
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        }
        int scrollY = getScrollY();
        int i5 = i - scrollX;
        int i6 = i2 - scrollY;
        if (i5 == 0 && i6 == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i7 = clientWidth >> 1;
        float abs = (float) Math.abs(i5);
        float f = (float) clientWidth;
        float f2 = (float) i7;
        float distanceInfluenceForSnapDuration = f2 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (abs * 1.0f) / f)) * f2);
        int abs2 = Math.abs(i3);
        if (abs2 > 0) {
            i4 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs2)) * 1000.0f) << 2;
        } else {
            i4 = (int) (((abs / ((f * 1.0f) + ((float) this.mPageMargin))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i4, (int) MAX_SETTLE_DURATION);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(scrollX, scrollY, i5, i6, min);
        postInvalidateOnAnimation();
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.mMarginDrawable) {
            return true;
        }
        return false;
    }

    public boolean canScrollHorizontally(int i) {
        return false;
    }

    public void setDragInGutterEnabled(boolean z) {
        this.mDragInGutterEnabled = z;
    }

    @Deprecated
    public void setOnPageChangeListener(AnonymousClass0Co r1) {
        this.mOnPageChangeListener = r1;
    }

    private void calculatePageOffsets(C00700Cl r2, int i, C00700Cl r4) {
        throw null;
    }

    public C00700Cl addNewItem(int i, int i2) {
        throw null;
    }

    public ViewPager(@NonNull Context context) {
        super(context);
        initViewPager();
    }

    public ViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initViewPager();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C00710Cm(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public void populate() {
        populate(this.mCurItem);
    }

    public void populate(int i) {
        int i2 = this.mCurItem;
        if (i2 != i) {
            infoForPosition(i2);
            this.mCurItem = i;
        }
        sortChildDrawingOrder();
    }

    public void setCurrentItem(int i) {
        this.mPopulatePending = false;
        setScrollingCacheEnabled(false);
    }

    public void setCurrentItem(int i, boolean z) {
        this.mPopulatePending = false;
        setScrollingCacheEnabled(false);
    }

    public void setCurrentItemInternal(int i, boolean z, boolean z2) {
        setScrollingCacheEnabled(false);
    }

    public void setCurrentItemInternal(int i, boolean z, boolean z2, int i2) {
        setScrollingCacheEnabled(false);
    }

    public void setPageMarginDrawable(@DrawableRes int i) {
        setPageMarginDrawable(getContext().getDrawable(i));
    }

    public void setPageMarginDrawable(@Nullable Drawable drawable) {
        boolean z;
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
            z = false;
        } else {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setPageTransformer(boolean z, @Nullable AbstractC00720Cp r3) {
        setPageTransformer(z, r3, 2);
    }

    public void setPageTransformer(boolean z, @Nullable AbstractC00720Cp r6, int i) {
        int i2 = 1;
        boolean z2 = false;
        boolean z3 = false;
        if (r6 != null) {
            z2 = true;
            z3 = true;
        }
        this.mPageTransformer = r6;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            if (z) {
                i2 = 2;
            }
            this.mDrawingOrder = i2;
            this.mPageTransformerLayerType = i;
        } else {
            this.mDrawingOrder = 0;
        }
        if (z3) {
            populate();
        }
    }
}
