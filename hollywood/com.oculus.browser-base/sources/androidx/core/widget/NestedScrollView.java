package androidx.core.widget;

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
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NestedScrollView extends FrameLayout implements AbstractC4971tn0, AbstractC4461qn0 {
    public static final C4119on0 F = new C4119on0();
    public static final int[] G = {16843130};
    public long H;
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public OverScroller f9469J = new OverScroller(getContext());
    public EdgeEffect K;
    public EdgeEffect L;
    public int M;
    public boolean N = true;
    public boolean O = false;
    public View P = null;
    public boolean Q = false;
    public VelocityTracker R;
    public boolean S;
    public boolean T = true;
    public int U;
    public int V;
    public int W;
    public int a0 = -1;
    public final int[] b0 = new int[2];
    public final int[] c0 = new int[2];
    public int d0;
    public int e0;
    public SavedState f0;
    public final C5141un0 g0;
    public final C4631rn0 h0;
    public float i0;
    public W3 j0;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new C4290pn0();
        public int F;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder i = AbstractC2531fV.i("HorizontalScrollView.SavedState{");
            i.append(Integer.toHexString(System.identityHashCode(this)));
            i.append(" scrollPosition=");
            i.append(this.F);
            i.append("}");
            return i.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.F);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.F = parcel.readInt();
        }
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.U = viewConfiguration.getScaledTouchSlop();
        this.V = viewConfiguration.getScaledMinimumFlingVelocity();
        this.W = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, G, 0, 0);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        if (z != this.S) {
            this.S = z;
            requestLayout();
        }
        obtainStyledAttributes.recycle();
        this.g0 = new C5141un0();
        this.h0 = new C4631rn0(this);
        setNestedScrollingEnabled(true);
        AbstractC1920bu1.n(this, F);
    }

    public static int i(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    public static boolean t(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || !t((View) parent, view2)) {
            return false;
        }
        return true;
    }

    public final boolean A(int i, int i2, int i3) {
        boolean z;
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = height + scrollY;
        boolean z2 = i == 33;
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z3 = false;
        for (int i5 = 0; i5 < size; i5++) {
            View view2 = (View) focusables.get(i5);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i2 < bottom && top < i3) {
                boolean z4 = i2 < top && bottom < i3;
                if (view == null) {
                    view = view2;
                    z3 = z4;
                } else {
                    boolean z5 = (z2 && top < view.getTop()) || (!z2 && bottom > view.getBottom());
                    if (z3) {
                        if (z4) {
                            if (!z5) {
                            }
                        }
                    } else if (z4) {
                        view = view2;
                        z3 = true;
                    } else if (!z5) {
                    }
                    view = view2;
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i2 < scrollY || i3 > i4) {
            l(z2 ? i2 - scrollY : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (view != findFocus()) {
            view.requestFocus(i);
        }
        return z;
    }

    public final void B(View view) {
        view.getDrawingRect(this.I);
        offsetDescendantRectToMyCoords(view, this.I);
        int j = j(this.I);
        if (j != 0) {
            scrollBy(0, j);
        }
    }

    public final void C(int i, int i2, int i3, boolean z) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.H > 250) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int scrollY = getScrollY();
                OverScroller overScroller = this.f9469J;
                int scrollX = getScrollX();
                overScroller.startScroll(scrollX, scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom())))) - scrollY, i3);
                z(z);
            } else {
                if (!this.f9469J.isFinished()) {
                    g();
                }
                scrollBy(i, i2);
            }
            this.H = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public boolean D(int i, int i2) {
        return this.h0.i(i, i2);
    }

    public void E(int i) {
        this.h0.j(i);
    }

    @Override // defpackage.AbstractC4801sn0
    public void a(View view, View view2, int i, int i2) {
        C5141un0 un0 = this.g0;
        if (i2 == 1) {
            un0.b = i;
        } else {
            un0.f11435a = i;
        }
        D(2, i2);
    }

    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // defpackage.AbstractC4801sn0
    public void b(View view, int i) {
        C5141un0 un0 = this.g0;
        if (i == 1) {
            un0.b = 0;
        } else {
            un0.f11435a = 0;
        }
        this.h0.j(i);
    }

    @Override // defpackage.AbstractC4801sn0
    public void c(View view, int i, int i2, int[] iArr, int i3) {
        k(i, i2, iArr, null, i3);
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public void computeScroll() {
        if (!this.f9469J.isFinished()) {
            this.f9469J.computeScrollOffset();
            int currY = this.f9469J.getCurrY();
            int i = currY - this.e0;
            this.e0 = currY;
            int[] iArr = this.c0;
            boolean z = false;
            iArr[1] = 0;
            k(0, i, iArr, null, 1);
            int i2 = i - this.c0[1];
            int r = r();
            if (i2 != 0) {
                int scrollY = getScrollY();
                x(0, i2, getScrollX(), scrollY, 0, r, 0, 0);
                int scrollY2 = getScrollY() - scrollY;
                int i3 = i2 - scrollY2;
                int[] iArr2 = this.c0;
                iArr2[1] = 0;
                this.h0.f(0, scrollY2, 0, i3, this.b0, 1, iArr2);
                i2 = i3 - this.c0[1];
            }
            if (i2 != 0) {
                int overScrollMode = getOverScrollMode();
                if (overScrollMode == 0 || (overScrollMode == 1 && r > 0)) {
                    z = true;
                }
                if (z) {
                    n();
                    if (i2 < 0) {
                        if (this.K.isFinished()) {
                            this.K.onAbsorb((int) this.f9469J.getCurrVelocity());
                        }
                    } else if (this.L.isFinished()) {
                        this.L.onAbsorb((int) this.f9469J.getCurrVelocity());
                    }
                }
                g();
            }
            if (!this.f9469J.isFinished()) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                postInvalidateOnAnimation();
                return;
            }
            this.h0.j(1);
        }
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollRange() {
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
        return scrollY > max ? bottom + (scrollY - max) : bottom;
    }

    @Override // defpackage.AbstractC4971tn0
    public void d(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        v(i4, i5, iArr);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || o(keyEvent);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.h0.a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.h0.b(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return k(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.h0.e(i, i2, i3, i4, iArr);
    }

    public void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.K != null) {
            int scrollY = getScrollY();
            int i2 = 0;
            if (!this.K.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int min = Math.min(0, scrollY);
                if (getClipToPadding()) {
                    width -= getPaddingRight() + getPaddingLeft();
                    i = getPaddingLeft() + 0;
                } else {
                    i = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingBottom() + getPaddingTop();
                    min += getPaddingTop();
                }
                canvas.translate((float) i, (float) min);
                this.K.setSize(width, height);
                if (this.K.draw(canvas)) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(save);
            }
            if (!this.L.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int max = Math.max(r(), scrollY) + height2;
                if (getClipToPadding()) {
                    width2 -= getPaddingRight() + getPaddingLeft();
                    i2 = 0 + getPaddingLeft();
                }
                if (getClipToPadding()) {
                    height2 -= getPaddingBottom() + getPaddingTop();
                    max -= getPaddingBottom();
                }
                canvas.translate((float) (i2 - width2), (float) max);
                canvas.rotate(180.0f, (float) width2, 0.0f);
                this.L.setSize(width2, height2);
                if (this.L.draw(canvas)) {
                    AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(save2);
            }
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public void e(View view, int i, int i2, int i3, int i4, int i5) {
        v(i4, i5, null);
    }

    @Override // defpackage.AbstractC4801sn0
    public boolean f(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public final void g() {
        this.f9469J.abortAnimation();
        this.h0.j(1);
    }

    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getNestedScrollAxes() {
        return this.g0.a();
    }

    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public boolean h(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int height = (int) (((float) getHeight()) * 0.5f);
        if (findNextFocus == null || !u(findNextFocus, height, getHeight())) {
            if (i == 33 && getScrollY() < height) {
                height = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                height = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), height);
            }
            if (height == 0) {
                return false;
            }
            if (i != 130) {
                height = -height;
            }
            l(height);
        } else {
            findNextFocus.getDrawingRect(this.I);
            offsetDescendantRectToMyCoords(findNextFocus, this.I);
            l(j(this.I));
            findNextFocus.requestFocus(i);
        }
        if (findFocus != null && findFocus.isFocused() && (!u(findFocus, 0, getHeight()))) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    public boolean hasNestedScrollingParent() {
        return s(0);
    }

    public boolean isNestedScrollingEnabled() {
        return this.h0.d;
    }

    public int j(Rect rect) {
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
        int i4 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i3 - verticalFadingEdgeLength : i3;
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

    public boolean k(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.h0.c(i, i2, iArr, iArr2, i3);
    }

    public final void l(int i) {
        if (i == 0) {
            return;
        }
        if (this.T) {
            C(0, i, 250, false);
        } else {
            scrollBy(0, i);
        }
    }

    public final void m() {
        this.Q = false;
        y();
        this.h0.j(0);
        EdgeEffect edgeEffect = this.K;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.L.onRelease();
        }
    }

    public void measureChild(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void n() {
        if (getOverScrollMode() == 2) {
            this.K = null;
            this.L = null;
        } else if (this.K == null) {
            Context context = getContext();
            this.K = new EdgeEffect(context);
            this.L = new EdgeEffect(context);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean o(android.view.KeyEvent r7) {
        /*
        // Method dump skipped, instructions count: 252
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.o(android.view.KeyEvent):boolean");
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.O = false;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.Q) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                if (this.i0 == 0.0f) {
                    TypedValue typedValue = new TypedValue();
                    Context context = getContext();
                    if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                        this.i0 = typedValue.getDimension(context.getResources().getDisplayMetrics());
                    } else {
                        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
                    }
                }
                int r = r();
                int scrollY = getScrollY();
                int i = scrollY - ((int) (axisValue * this.i0));
                if (i < 0) {
                    r = 0;
                } else if (i <= r) {
                    r = i;
                }
                if (r != scrollY) {
                    super.scrollTo(getScrollX(), r);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r12) {
        /*
        // Method dump skipped, instructions count: 282
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        this.N = false;
        View view = this.P;
        if (view != null && t(view, this)) {
            B(this.P);
        }
        this.P = null;
        if (!this.O) {
            if (this.f0 != null) {
                scrollTo(getScrollX(), this.f0.F);
                this.f0 = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i5 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int i6 = i(scrollY, paddingTop, i5);
            if (i6 != scrollY) {
                scrollTo(getScrollX(), i6);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.O = true;
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.S && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f2, true);
        p((int) f2);
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        k(i, i2, iArr, null, 0);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        v(i4, 0, null);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.g0.f11435a = i;
        D(2, 0);
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        View view;
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus(this, null, i);
        } else {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        }
        if (view != null && !(true ^ u(view, 0, getHeight()))) {
            return view.requestFocus(i, rect);
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f0 = savedState;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.F = getScrollY();
        return savedState;
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        W3 w3 = this.j0;
        if (w3 != null) {
            C2120d4.c(this, w3.f9124a, w3.b);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && u(findFocus, 0, i4)) {
            findFocus.getDrawingRect(this.I);
            offsetDescendantRectToMyCoords(findFocus, this.I);
            l(j(this.I));
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public void onStopNestedScroll(View view) {
        this.g0.f11435a = 0;
        E(0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.d0 = 0;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(0.0f, (float) this.d0);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.R;
                velocityTracker.computeCurrentVelocity(1000, (float) this.W);
                int yVelocity = (int) velocityTracker.getYVelocity(this.a0);
                if (Math.abs(yVelocity) >= this.V) {
                    int i = -yVelocity;
                    float f = (float) i;
                    if (!dispatchNestedPreFling(0.0f, f)) {
                        dispatchNestedFling(0.0f, f, true);
                        p(i);
                    }
                } else if (this.f9469J.springBack(getScrollX(), getScrollY(), 0, 0, 0, r())) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    postInvalidateOnAnimation();
                }
                this.a0 = -1;
                m();
            } else if (actionMasked == 2) {
                int findPointerIndex = motionEvent.findPointerIndex(this.a0);
                if (findPointerIndex == -1) {
                    StringBuilder i2 = AbstractC2531fV.i("Invalid pointerId=");
                    i2.append(this.a0);
                    i2.append(" in onTouchEvent");
                    Log.e("NestedScrollView", i2.toString());
                } else {
                    int y = (int) motionEvent.getY(findPointerIndex);
                    int i3 = this.M - y;
                    if (!this.Q && Math.abs(i3) > this.U) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.Q = true;
                        i3 = i3 > 0 ? i3 - this.U : i3 + this.U;
                    }
                    int i4 = i3;
                    if (this.Q) {
                        if (k(0, i4, this.c0, this.b0, 0)) {
                            i4 -= this.c0[1];
                            this.d0 += this.b0[1];
                        }
                        this.M = y - this.b0[1];
                        int scrollY = getScrollY();
                        int r = r();
                        int overScrollMode = getOverScrollMode();
                        boolean z = overScrollMode == 0 || (overScrollMode == 1 && r > 0);
                        if (x(0, i4, 0, getScrollY(), 0, r, 0, 0) && !s(0)) {
                            this.R.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.c0;
                        iArr[1] = 0;
                        this.h0.f(0, scrollY2, 0, i4 - scrollY2, this.b0, 0, iArr);
                        int i5 = this.M;
                        int[] iArr2 = this.b0;
                        this.M = i5 - iArr2[1];
                        this.d0 += iArr2[1];
                        if (z) {
                            int i6 = i4 - this.c0[1];
                            n();
                            int i7 = scrollY + i6;
                            if (i7 < 0) {
                                this.K.onPull(((float) i6) / ((float) getHeight()), motionEvent.getX(findPointerIndex) / ((float) getWidth()));
                                if (!this.L.isFinished()) {
                                    this.L.onRelease();
                                }
                            } else if (i7 > r) {
                                this.L.onPull(((float) i6) / ((float) getHeight()), 1.0f - (motionEvent.getX(findPointerIndex) / ((float) getWidth())));
                                if (!this.K.isFinished()) {
                                    this.K.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.K;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.L.isFinished())) {
                                AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                                postInvalidateOnAnimation();
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.Q && getChildCount() > 0 && this.f9469J.springBack(getScrollX(), getScrollY(), 0, 0, 0, r())) {
                    AtomicInteger atomicInteger3 = AbstractC1920bu1.f9571a;
                    postInvalidateOnAnimation();
                }
                this.a0 = -1;
                m();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.M = (int) motionEvent.getY(actionIndex);
                this.a0 = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                w(motionEvent);
                this.M = (int) motionEvent.getY(motionEvent.findPointerIndex(this.a0));
            }
        } else if (getChildCount() == 0) {
            return false;
        } else {
            boolean z2 = !this.f9469J.isFinished();
            this.Q = z2;
            if (z2 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f9469J.isFinished()) {
                g();
            }
            this.M = (int) motionEvent.getY();
            this.a0 = motionEvent.getPointerId(0);
            D(2, 0);
        }
        VelocityTracker velocityTracker2 = this.R;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    public void p(int i) {
        if (getChildCount() > 0) {
            this.f9469J.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            z(true);
        }
    }

    public boolean q(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.I;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.I.bottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            Rect rect2 = this.I;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.I;
        return A(i, rect3.top, rect3.bottom);
    }

    public int r() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.N) {
            B(view2);
        } else {
            this.P = view2;
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int j = j(rect);
        boolean z2 = j != 0;
        if (z2) {
            if (z) {
                scrollBy(0, j);
            } else {
                C(0, j, 250, false);
            }
        }
        return z2;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            y();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        this.N = true;
        super.requestLayout();
    }

    public boolean s(int i) {
        return this.h0.g(i) != null;
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int i3 = i(i, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int i4 = i(i2, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (i3 != getScrollX() || i4 != getScrollY()) {
                super.scrollTo(i3, i4);
            }
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        C4631rn0 rn0 = this.h0;
        if (rn0.d) {
            View view = rn0.c;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.stopNestedScroll();
        }
        rn0.d = z;
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public boolean startNestedScroll(int i) {
        return this.h0.i(i, 0);
    }

    public void stopNestedScroll() {
        this.h0.j(0);
    }

    public final boolean u(View view, int i, int i2) {
        view.getDrawingRect(this.I);
        offsetDescendantRectToMyCoords(view, this.I);
        return this.I.bottom + i >= getScrollY() && this.I.top - i <= getScrollY() + i2;
    }

    public final void v(int i, int i2, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.h0.d(0, scrollY2, 0, i - scrollY2, null, i2, iArr);
    }

    public final void w(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.a0) {
            int i = actionIndex == 0 ? 1 : 0;
            this.M = (int) motionEvent.getY(i);
            this.a0 = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.R;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean x(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean z;
        boolean z2;
        int overScrollMode = getOverScrollMode();
        boolean z3 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z4 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z5 = overScrollMode == 0 || (overScrollMode == 1 && z3);
        boolean z6 = overScrollMode == 0 || (overScrollMode == 1 && z4);
        int i9 = i3 + i;
        int i10 = !z5 ? 0 : i7;
        int i11 = i4 + i2;
        int i12 = !z6 ? 0 : i8;
        int i13 = -i10;
        int i14 = i10 + i5;
        int i15 = -i12;
        int i16 = i12 + i6;
        if (i9 > i14) {
            i9 = i14;
            z = true;
        } else if (i9 < i13) {
            z = true;
            i9 = i13;
        } else {
            z = false;
        }
        if (i11 > i16) {
            i11 = i16;
            z2 = true;
        } else if (i11 < i15) {
            z2 = true;
            i11 = i15;
        } else {
            z2 = false;
        }
        if (z2 && !s(1)) {
            this.f9469J.springBack(i9, i11, 0, 0, 0, r());
        }
        onOverScrolled(i9, i11, z, z2);
        return z || z2;
    }

    public final void y() {
        VelocityTracker velocityTracker = this.R;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.R = null;
        }
    }

    public final void z(boolean z) {
        if (z) {
            D(2, 1);
        } else {
            this.h0.j(1);
        }
        this.e0 = getScrollY();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        postInvalidateOnAnimation();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
