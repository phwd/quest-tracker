package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
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
import androidx.customview.view.AbsSavedState;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewPager extends ViewGroup {
    public static final int[] F = {16842931};
    public static final Comparator G = new C5333vu1();
    public static final Interpolator H = new animation.InterpolatorC5503wu1();
    public static final Gu1 I = new Gu1();
    public int A0;
    public List B0;
    public List C0;
    public final Runnable D0 = new RunnableC5673xu1(this);
    public int E0 = 0;

    /* renamed from: J  reason: collision with root package name */
    public int f9486J;
    public final ArrayList K = new ArrayList();
    public final Au1 L = new Au1();
    public final Rect M = new Rect();
    public AbstractC0966Pv0 N;
    public int O;
    public int P = -1;
    public Parcelable Q = null;
    public ClassLoader R = null;
    public Scroller S;
    public boolean T;
    public Eu1 U;
    public int V;
    public int W;
    public float a0 = -3.4028235E38f;
    public float b0 = Float.MAX_VALUE;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public int f0 = 1;
    public boolean g0;
    public boolean h0;
    public int i0;
    public int j0;
    public int k0;
    public boolean l0 = true;
    public float m0;
    public float n0;
    public float o0;
    public float p0;
    public int q0 = -1;
    public VelocityTracker r0;
    public int s0;
    public int t0;
    public int u0;
    public int v0;
    public EdgeEffect w0;
    public EdgeEffect x0;
    public boolean y0 = true;
    public boolean z0;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Fu1();
        public int H;
        public Parcelable I;

        /* renamed from: J  reason: collision with root package name */
        public ClassLoader f9487J;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder i = AbstractC2531fV.i("FragmentPager.SavedState{");
            i.append(Integer.toHexString(System.identityHashCode(this)));
            i.append(" position=");
            i.append(this.H);
            i.append("}");
            return i.toString();
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            parcel.writeInt(this.H);
            parcel.writeParcelable(this.I, i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? SavedState.class.getClassLoader() : classLoader;
            this.H = parcel.readInt();
            this.I = parcel.readParcelable(classLoader);
            this.f9487J = classLoader;
        }
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context2 = getContext();
        this.S = new Scroller(context2, H);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context2);
        float f = context2.getResources().getDisplayMetrics().density;
        this.k0 = viewConfiguration.getScaledPagingTouchSlop();
        this.s0 = (int) (400.0f * f);
        this.t0 = viewConfiguration.getScaledMaximumFlingVelocity();
        this.w0 = new EdgeEffect(context2);
        this.x0 = new EdgeEffect(context2);
        this.u0 = (int) (25.0f * f);
        this.v0 = (int) (2.0f * f);
        this.i0 = (int) (f * 16.0f);
        AbstractC1920bu1.n(this, new Cu1(this));
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        AbstractC1920bu1.o(this, new C5843yu1(this));
    }

    public void A(int i) {
        if (this.E0 != i) {
            this.E0 = i;
            List list = this.B0;
            if (list != null) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Du1 du1 = (Du1) this.B0.get(i2);
                    if (du1 != null) {
                        du1.b(i);
                    }
                }
            }
        }
    }

    public final void B(boolean z) {
        if (this.d0 != z) {
            this.d0 = z;
        }
    }

    public Au1 a(int i, int i2) {
        Au1 au1 = new Au1();
        au1.b = i;
        au1.f7705a = this.N.g(this, i);
        Objects.requireNonNull(this.N);
        au1.d = 1.0f;
        if (i2 < 0 || i2 >= this.K.size()) {
            this.K.add(au1);
        } else {
            this.K.add(i2, au1);
        }
        return au1;
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addFocusables(ArrayList arrayList, int i, int i2) {
        Au1 j;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (j = j(childAt)) != null && j.b == this.O) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if ((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) {
            arrayList.add(this);
        }
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addTouchables(ArrayList arrayList) {
        Au1 j;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (j = j(childAt)) != null && j.b == this.O) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        Bu1 bu1 = (Bu1) layoutParams;
        boolean z = bu1.f7770a | (view.getClass().getAnnotation(AbstractC6013zu1.class) != null);
        bu1.f7770a = z;
        if (!this.c0) {
            super.addView(view, i, layoutParams);
        } else if (!z) {
            bu1.d = true;
            addViewInLayout(view, i, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void b(Du1 du1) {
        if (this.B0 == null) {
            this.B0 = new ArrayList();
        }
        this.B0.add(du1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(int r7) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.c(int):boolean");
    }

    public boolean canScrollHorizontally(int i) {
        if (this.N == null) {
            return false;
        }
        int i2 = i();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX > ((int) (((float) i2) * this.a0))) {
                return true;
            }
            return false;
        } else if (i <= 0 || scrollX >= ((int) (((float) i2) * this.b0))) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof Bu1) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        this.T = true;
        if (this.S.isFinished() || !this.S.computeScrollOffset()) {
            e(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.S.getCurrX();
        int currY = this.S.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!q(currX)) {
                this.S.abortAnimation();
                scrollTo(0, currY);
            }
        }
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        postInvalidateOnAnimation();
    }

    public boolean d(View view, boolean z, int i, int i2, int i3) {
        int i4;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i2 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom() && d(childAt, true, i, i5 - childAt.getLeft(), i4 - childAt.getTop())) {
                    return true;
                }
            }
        }
        if (!z || !view.canScrollHorizontally(-i)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            boolean r0 = super.dispatchKeyEvent(r6)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x005d
            int r0 = r6.getAction()
            if (r0 != 0) goto L_0x005a
            int r0 = r6.getKeyCode()
            r3 = 21
            r4 = 2
            if (r0 == r3) goto L_0x0048
            r3 = 22
            if (r0 == r3) goto L_0x0036
            r3 = 61
            if (r0 == r3) goto L_0x0020
            goto L_0x005a
        L_0x0020:
            boolean r0 = r6.hasNoModifiers()
            if (r0 == 0) goto L_0x002b
            boolean r6 = r5.c(r4)
            goto L_0x005b
        L_0x002b:
            boolean r6 = r6.hasModifiers(r2)
            if (r6 == 0) goto L_0x005a
            boolean r6 = r5.c(r2)
            goto L_0x005b
        L_0x0036:
            boolean r6 = r6.hasModifiers(r4)
            if (r6 == 0) goto L_0x0041
            boolean r6 = r5.p()
            goto L_0x005b
        L_0x0041:
            r6 = 66
            boolean r6 = r5.c(r6)
            goto L_0x005b
        L_0x0048:
            boolean r6 = r6.hasModifiers(r4)
            if (r6 == 0) goto L_0x0053
            boolean r6 = r5.o()
            goto L_0x005b
        L_0x0053:
            r6 = 17
            boolean r6 = r5.c(r6)
            goto L_0x005b
        L_0x005a:
            r6 = r1
        L_0x005b:
            if (r6 == 0) goto L_0x005e
        L_0x005d:
            r1 = r2
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Au1 j;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (j = j(childAt)) != null && j.b == this.O && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        AbstractC0966Pv0 pv0;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (pv0 = this.N) != null && pv0.e() > 1)) {
            if (!this.w0.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) (getPaddingTop() + (-height)), this.a0 * ((float) width));
                this.w0.setSize(height, width);
                z = false | this.w0.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.x0.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.b0 + 1.0f)) * ((float) width2));
                this.x0.setSize(height2, width2);
                z |= this.x0.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.w0.finish();
            this.x0.finish();
        }
        if (z) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
    }

    public final void e(boolean z) {
        boolean z2 = this.E0 == 2;
        if (z2) {
            B(false);
            if (!this.S.isFinished()) {
                this.S.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.S.getCurrX();
                int currY = this.S.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        q(currX);
                    }
                }
            }
        }
        this.e0 = false;
        for (int i = 0; i < this.K.size(); i++) {
            Au1 au1 = (Au1) this.K.get(i);
            if (au1.c) {
                au1.c = false;
                z2 = true;
            }
        }
        if (!z2) {
            return;
        }
        if (z) {
            Runnable runnable = this.D0;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postOnAnimation(runnable);
            return;
        }
        this.D0.run();
    }

    public void f() {
        int e = this.N.e();
        this.f9486J = e;
        boolean z = this.K.size() < (this.f0 * 2) + 1 && this.K.size() < e;
        int i = this.O;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.K.size()) {
            Au1 au1 = (Au1) this.K.get(i2);
            int f = this.N.f(au1.f7705a);
            if (f != -1) {
                if (f == -2) {
                    this.K.remove(i2);
                    i2--;
                    if (!z2) {
                        Objects.requireNonNull(this.N);
                        z2 = true;
                    }
                    this.N.d(this, au1.b, au1.f7705a);
                    int i3 = this.O;
                    if (i3 == au1.b) {
                        i = Math.max(0, Math.min(i3, e - 1));
                    }
                } else {
                    int i4 = au1.b;
                    if (i4 != f) {
                        if (i4 == this.O) {
                            i = f;
                        }
                        au1.b = f;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            Objects.requireNonNull(this.N);
        }
        Collections.sort(this.K, G);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                Bu1 bu1 = (Bu1) getChildAt(i5).getLayoutParams();
                if (!bu1.f7770a) {
                    bu1.c = 0.0f;
                }
            }
            z(i, false, true, 0);
            requestLayout();
        }
    }

    public final void g(int i) {
        List list = this.B0;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                Du1 du1 = (Du1) this.B0.get(i2);
                if (du1 != null) {
                    du1.c(i);
                }
            }
        }
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new Bu1();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public int getChildDrawingOrder(int i, int i2) {
        throw null;
    }

    public final Rect h(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    public final int i() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public Au1 j(View view) {
        for (int i = 0; i < this.K.size(); i++) {
            Au1 au1 = (Au1) this.K.get(i);
            if (this.N.h(view, au1.f7705a)) {
                return au1;
            }
        }
        return null;
    }

    public final Au1 k() {
        int i;
        int i2 = i();
        float f = 0.0f;
        float scrollX = i2 > 0 ? ((float) getScrollX()) / ((float) i2) : 0.0f;
        float f2 = i2 > 0 ? ((float) 0) / ((float) i2) : 0.0f;
        Au1 au1 = null;
        int i3 = 0;
        int i4 = -1;
        boolean z = true;
        float f3 = 0.0f;
        while (i3 < this.K.size()) {
            Au1 au12 = (Au1) this.K.get(i3);
            if (!z && au12.b != (i = i4 + 1)) {
                au12 = this.L;
                au12.e = f + f3 + f2;
                au12.b = i;
                Objects.requireNonNull(this.N);
                au12.d = 1.0f;
                i3--;
            }
            f = au12.e;
            float f4 = au12.d + f + f2;
            if (!z && scrollX < f) {
                return au1;
            }
            if (scrollX < f4 || i3 == this.K.size() - 1) {
                return au12;
            }
            i4 = au12.b;
            f3 = au12.d;
            i3++;
            z = false;
            au1 = au12;
        }
        return au1;
    }

    public Au1 l(int i) {
        for (int i2 = 0; i2 < this.K.size(); i2++) {
            Au1 au1 = (Au1) this.K.get(i2);
            if (au1.b == i) {
                return au1;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m(int r13, float r14, int r15) {
        /*
        // Method dump skipped, instructions count: 136
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.m(int, float, int):void");
    }

    public final void n(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.q0) {
            int i = actionIndex == 0 ? 1 : 0;
            this.m0 = motionEvent.getX(i);
            this.q0 = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.r0;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean o() {
        int i = this.O;
        if (i <= 0) {
            return false;
        }
        y(i - 1, true);
        return true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.y0 = true;
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.D0);
        Scroller scroller = this.S;
        if (scroller != null && !scroller.isFinished()) {
            this.S.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            u();
            return false;
        }
        if (action != 0) {
            if (this.g0) {
                return true;
            }
            if (this.h0) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.o0 = x;
            this.m0 = x;
            float y = motionEvent.getY();
            this.p0 = y;
            this.n0 = y;
            this.q0 = motionEvent.getPointerId(0);
            this.h0 = false;
            this.T = true;
            this.S.computeScrollOffset();
            if (this.E0 != 2 || Math.abs(this.S.getFinalX() - this.S.getCurrX()) <= this.v0) {
                e(false);
                this.g0 = false;
            } else {
                this.S.abortAnimation();
                this.e0 = false;
                s(this.O);
                this.g0 = true;
                t(true);
                A(1);
            }
        } else if (action == 2) {
            int i = this.q0;
            if (i != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i);
                float x2 = motionEvent.getX(findPointerIndex);
                float f = x2 - this.m0;
                float abs = Math.abs(f);
                float y2 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.p0);
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i2 != 0) {
                    float f2 = this.m0;
                    if (!(!this.l0 && ((f2 < ((float) this.j0) && i2 > 0) || (f2 > ((float) (getWidth() - this.j0)) && f < 0.0f))) && d(this, false, (int) f, (int) x2, (int) y2)) {
                        this.m0 = x2;
                        this.n0 = y2;
                        this.h0 = true;
                        return false;
                    }
                }
                int i3 = this.k0;
                if (abs > ((float) i3) && abs * 0.5f > abs2) {
                    this.g0 = true;
                    t(true);
                    A(1);
                    this.m0 = i2 > 0 ? this.o0 + ((float) this.k0) : this.o0 - ((float) this.k0);
                    this.n0 = y2;
                    B(true);
                } else if (abs2 > ((float) i3)) {
                    this.h0 = true;
                }
                if (this.g0 && r(x2)) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    postInvalidateOnAnimation();
                }
            }
        } else if (action == 6) {
            n(motionEvent);
        }
        if (this.r0 == null) {
            this.r0 = VelocityTracker.obtain();
        }
        this.r0.addMovement(motionEvent);
        return this.g0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
        // Method dump skipped, instructions count: 284
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    public void onMeasure(int i, int i2) {
        Bu1 bu1;
        Bu1 bu12;
        int i3;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i), ViewGroup.getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.j0 = Math.min(measuredWidth / 10, this.i0);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i4 = 0;
        while (true) {
            boolean z = true;
            int i5 = 1073741824;
            if (i4 >= childCount) {
                break;
            }
            View childAt = getChildAt(i4);
            if (!(childAt.getVisibility() == 8 || (bu12 = (Bu1) childAt.getLayoutParams()) == null || !bu12.f7770a)) {
                int i6 = bu12.b;
                int i7 = i6 & 7;
                int i8 = i6 & 112;
                boolean z2 = i8 == 48 || i8 == 80;
                if (!(i7 == 3 || i7 == 5)) {
                    z = false;
                }
                int i9 = Integer.MIN_VALUE;
                if (z2) {
                    i3 = Integer.MIN_VALUE;
                    i9 = 1073741824;
                } else {
                    i3 = z ? 1073741824 : Integer.MIN_VALUE;
                }
                int i10 = ((ViewGroup.LayoutParams) bu12).width;
                if (i10 != -2) {
                    if (i10 == -1) {
                        i10 = paddingLeft;
                    }
                    i9 = 1073741824;
                } else {
                    i10 = paddingLeft;
                }
                int i11 = ((ViewGroup.LayoutParams) bu12).height;
                if (i11 == -2) {
                    i11 = measuredHeight;
                    i5 = i3;
                } else if (i11 == -1) {
                    i11 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i10, i9), View.MeasureSpec.makeMeasureSpec(i11, i5));
                if (z2) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i4++;
        }
        View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.c0 = true;
        s(this.O);
        this.c0 = false;
        int childCount2 = getChildCount();
        for (int i12 = 0; i12 < childCount2; i12++) {
            View childAt2 = getChildAt(i12);
            if (childAt2.getVisibility() != 8 && ((bu1 = (Bu1) childAt2.getLayoutParams()) == null || !bu1.f7770a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) paddingLeft) * bu1.c), 1073741824), makeMeasureSpec);
            }
        }
    }

    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3;
        Au1 j;
        int childCount = getChildCount();
        int i4 = -1;
        if ((i & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i2 = 1;
        } else {
            i3 = childCount - 1;
            i2 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (j = j(childAt)) != null && j.b == this.O && childAt.requestFocus(i, rect)) {
                return true;
            }
            i3 += i2;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        if (this.N != null) {
            z(savedState.H, false, true, 0);
            return;
        }
        this.P = savedState.H;
        this.Q = savedState.I;
        this.R = savedState.f9487J;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.H = this.O;
        AbstractC0966Pv0 pv0 = this.N;
        if (pv0 != null) {
            Objects.requireNonNull(pv0);
            savedState.I = null;
        }
        return savedState;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3) {
            return;
        }
        if (i3 <= 0 || this.K.isEmpty()) {
            Au1 l = l(this.O);
            int min = (int) ((l != null ? Math.min(l.e, this.b0) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                e(false);
                scrollTo(min, getScrollY());
            }
        } else if (!this.S.isFinished()) {
            this.S.setFinalX(i() * this.O);
        } else {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((i3 - getPaddingLeft()) - getPaddingRight()) + 0))) * ((float) (((i - getPaddingLeft()) - getPaddingRight()) + 0))), getScrollY());
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractC0966Pv0 pv0;
        float f;
        boolean z = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (pv0 = this.N) == null || pv0.e() == 0) {
            return false;
        }
        if (this.r0 == null) {
            this.r0 = VelocityTracker.obtain();
        }
        this.r0.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.S.abortAnimation();
            this.e0 = false;
            s(this.O);
            float x = motionEvent.getX();
            this.o0 = x;
            this.m0 = x;
            float y = motionEvent.getY();
            this.p0 = y;
            this.n0 = y;
            this.q0 = motionEvent.getPointerId(0);
        } else if (action != 1) {
            if (action == 2) {
                if (!this.g0) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.q0);
                    if (findPointerIndex == -1) {
                        z = u();
                    } else {
                        float x2 = motionEvent.getX(findPointerIndex);
                        float abs = Math.abs(x2 - this.m0);
                        float y2 = motionEvent.getY(findPointerIndex);
                        float abs2 = Math.abs(y2 - this.n0);
                        if (abs > ((float) this.k0) && abs > abs2) {
                            this.g0 = true;
                            t(true);
                            float f2 = this.o0;
                            if (x2 - f2 > 0.0f) {
                                f = f2 + ((float) this.k0);
                            } else {
                                f = f2 - ((float) this.k0);
                            }
                            this.m0 = f;
                            this.n0 = y2;
                            A(1);
                            B(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (this.g0) {
                    z = false | r(motionEvent.getX(motionEvent.findPointerIndex(this.q0)));
                }
            } else if (action != 3) {
                if (action == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    this.m0 = motionEvent.getX(actionIndex);
                    this.q0 = motionEvent.getPointerId(actionIndex);
                } else if (action == 6) {
                    n(motionEvent);
                    this.m0 = motionEvent.getX(motionEvent.findPointerIndex(this.q0));
                }
            } else if (this.g0) {
                v(this.O, true, 0, false);
                z = u();
            }
        } else if (this.g0) {
            VelocityTracker velocityTracker = this.r0;
            velocityTracker.computeCurrentVelocity(1000, (float) this.t0);
            int xVelocity = (int) velocityTracker.getXVelocity(this.q0);
            this.e0 = true;
            int i = i();
            int scrollX = getScrollX();
            Au1 k = k();
            float f3 = (float) i;
            int i2 = k.b;
            float f4 = ((((float) scrollX) / f3) - k.e) / (k.d + (((float) 0) / f3));
            if (Math.abs((int) (motionEvent.getX(motionEvent.findPointerIndex(this.q0)) - this.o0)) <= this.u0 || Math.abs(xVelocity) <= this.s0) {
                i2 += (int) (f4 + (i2 >= this.O ? 0.4f : 0.6f));
            } else if (xVelocity <= 0) {
                i2++;
            }
            if (this.K.size() > 0) {
                ArrayList arrayList = this.K;
                i2 = Math.max(((Au1) this.K.get(0)).b, Math.min(i2, ((Au1) arrayList.get(arrayList.size() - 1)).b));
            }
            z(i2, true, true, xVelocity);
            z = u();
        }
        if (z) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
        return true;
    }

    public boolean p() {
        AbstractC0966Pv0 pv0 = this.N;
        if (pv0 == null || this.O >= pv0.e() - 1) {
            return false;
        }
        y(this.O + 1, true);
        return true;
    }

    public final boolean q(int i) {
        if (this.K.size() != 0) {
            Au1 k = k();
            int i2 = i();
            int i3 = i2 + 0;
            float f = (float) i2;
            int i4 = k.b;
            float f2 = ((((float) i) / f) - k.e) / (k.d + (((float) 0) / f));
            this.z0 = false;
            m(i4, f2, (int) (((float) i3) * f2));
            if (this.z0) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.y0) {
            return false;
        } else {
            this.z0 = false;
            m(0, 0.0f, 0);
            if (this.z0) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    public final boolean r(float f) {
        boolean z;
        boolean z2;
        float f2 = this.m0 - f;
        this.m0 = f;
        float scrollX = ((float) getScrollX()) + f2;
        float i = (float) i();
        float f3 = this.a0 * i;
        float f4 = this.b0 * i;
        boolean z3 = false;
        Au1 au1 = (Au1) this.K.get(0);
        ArrayList arrayList = this.K;
        Au1 au12 = (Au1) arrayList.get(arrayList.size() - 1);
        if (au1.b != 0) {
            f3 = au1.e * i;
            z = false;
        } else {
            z = true;
        }
        if (au12.b != this.N.e() - 1) {
            f4 = au12.e * i;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f3) {
            if (z) {
                this.w0.onPull(Math.abs(f3 - scrollX) / i);
                z3 = true;
            }
            scrollX = f3;
        } else if (scrollX > f4) {
            if (z2) {
                this.x0.onPull(Math.abs(scrollX - f4) / i);
                z3 = true;
            }
            scrollX = f4;
        }
        int i2 = (int) scrollX;
        this.m0 = (scrollX - ((float) i2)) + this.m0;
        scrollTo(i2, getScrollY());
        q(i2);
        return z3;
    }

    public void removeView(View view) {
        if (this.c0) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        if (r6 == r7) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void s(int r15) {
        /*
        // Method dump skipped, instructions count: 905
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.s(int):void");
    }

    public final void t(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public final boolean u() {
        this.q0 = -1;
        this.g0 = false;
        this.h0 = false;
        VelocityTracker velocityTracker = this.r0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.r0 = null;
        }
        this.w0.onRelease();
        this.x0.onRelease();
        if (this.w0.isFinished() || this.x0.isFinished()) {
            return true;
        }
        return false;
    }

    public final void v(int i, boolean z, int i2, boolean z2) {
        int i3;
        int i4;
        Au1 l = l(i);
        int max = l != null ? (int) (Math.max(this.a0, Math.min(l.e, this.b0)) * ((float) i())) : 0;
        if (z) {
            if (getChildCount() == 0) {
                B(false);
            } else {
                Scroller scroller = this.S;
                if (scroller != null && !scroller.isFinished()) {
                    i3 = this.T ? this.S.getCurrX() : this.S.getStartX();
                    this.S.abortAnimation();
                    B(false);
                } else {
                    i3 = getScrollX();
                }
                int scrollY = getScrollY();
                int i5 = max - i3;
                int i6 = 0 - scrollY;
                if (i5 == 0 && i6 == 0) {
                    e(false);
                    s(this.O);
                    A(0);
                } else {
                    B(true);
                    A(2);
                    int i7 = i();
                    int i8 = i7 / 2;
                    float f = (float) i7;
                    float f2 = (float) i8;
                    float sin = (((float) Math.sin((double) ((Math.min(1.0f, (((float) Math.abs(i5)) * 1.0f) / f) - 0.5f) * 0.47123894f))) * f2) + f2;
                    int abs = Math.abs(i2);
                    if (abs > 0) {
                        i4 = Math.round(Math.abs(sin / ((float) abs)) * 1000.0f) * 4;
                    } else {
                        Objects.requireNonNull(this.N);
                        i4 = (int) (((((float) Math.abs(i5)) / ((f * 1.0f) + ((float) 0))) + 1.0f) * 100.0f);
                    }
                    int min = Math.min(i4, 600);
                    this.T = false;
                    this.S.startScroll(i3, scrollY, i5, i6, min);
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    postInvalidateOnAnimation();
                }
            }
            if (z2) {
                g(i);
                return;
            }
            return;
        }
        if (z2) {
            g(i);
        }
        e(false);
        scrollTo(max, 0);
        q(max);
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == null;
    }

    public void w(AbstractC0966Pv0 pv0) {
        AbstractC0966Pv0 pv02 = this.N;
        if (pv02 != null) {
            synchronized (pv02) {
                pv02.b = null;
            }
            Objects.requireNonNull(this.N);
            for (int i = 0; i < this.K.size(); i++) {
                Au1 au1 = (Au1) this.K.get(i);
                this.N.d(this, au1.b, au1.f7705a);
            }
            Objects.requireNonNull(this.N);
            this.K.clear();
            int i2 = 0;
            while (i2 < getChildCount()) {
                if (!((Bu1) getChildAt(i2).getLayoutParams()).f7770a) {
                    removeViewAt(i2);
                    i2--;
                }
                i2++;
            }
            this.O = 0;
            scrollTo(0, 0);
        }
        this.N = pv0;
        this.f9486J = 0;
        if (pv0 != null) {
            if (this.U == null) {
                this.U = new Eu1(this);
            }
            AbstractC0966Pv0 pv03 = this.N;
            Eu1 eu1 = this.U;
            synchronized (pv03) {
                pv03.b = eu1;
            }
            this.e0 = false;
            boolean z = this.y0;
            this.y0 = true;
            this.f9486J = this.N.e();
            if (this.P >= 0) {
                Objects.requireNonNull(this.N);
                z(this.P, false, true, 0);
                this.P = -1;
                this.Q = null;
                this.R = null;
            } else if (!z) {
                s(this.O);
            } else {
                requestLayout();
            }
        }
        List list = this.C0;
        if (!(list == null || list.isEmpty())) {
            int size = this.C0.size();
            for (int i3 = 0; i3 < size; i3++) {
                C5376w81 w81 = (C5376w81) this.C0.get(i3);
                TabLayout tabLayout = w81.b;
                if (tabLayout.p0 == this) {
                    tabLayout.p(pv0, w81.f11522a);
                }
            }
        }
    }

    public void x(int i) {
        this.e0 = false;
        z(i, !this.y0, false, 0);
    }

    public void y(int i, boolean z) {
        this.e0 = false;
        z(i, z, false, 0);
    }

    public void z(int i, boolean z, boolean z2, int i2) {
        AbstractC0966Pv0 pv0 = this.N;
        boolean z3 = false;
        if (pv0 == null || pv0.e() <= 0) {
            B(false);
        } else if (z2 || this.O != i || this.K.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.N.e()) {
                i = this.N.e() - 1;
            }
            int i3 = this.f0;
            int i4 = this.O;
            if (i > i4 + i3 || i < i4 - i3) {
                for (int i5 = 0; i5 < this.K.size(); i5++) {
                    ((Au1) this.K.get(i5)).c = true;
                }
            }
            if (this.O != i) {
                z3 = true;
            }
            if (this.y0) {
                this.O = i;
                if (z3) {
                    g(i);
                }
                requestLayout();
                return;
            }
            s(i);
            v(i, z, i2, z3);
        } else {
            B(false);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new Bu1(getContext(), attributeSet);
    }
}
