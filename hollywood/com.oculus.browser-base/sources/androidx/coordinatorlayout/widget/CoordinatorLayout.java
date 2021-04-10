package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.appbar.AppBarLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CoordinatorLayout extends ViewGroup implements AbstractC4801sn0, AbstractC4971tn0 {
    public static final String F;
    public static final Class[] G = {Context.class, AttributeSet.class};
    public static final ThreadLocal H = new ThreadLocal();
    public static final Comparator I = new QA();

    /* renamed from: J  reason: collision with root package name */
    public static final C5903zE0 f9466J = new AE0(12);
    public final List K = new ArrayList();
    public final KF L = new KF();
    public final List M = new ArrayList();
    public final List N = new ArrayList();
    public final int[] O = new int[2];
    public final int[] P = new int[2];
    public boolean Q;
    public boolean R;
    public int[] S;
    public View T;
    public View U;
    public OA V;
    public boolean W;
    public C3985nz1 a0;
    public boolean b0;
    public Drawable c0;
    public ViewGroup.OnHierarchyChangeListener d0;
    public AbstractC0290Es0 e0;
    public final C5141un0 f0 = new C5141un0();

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        F = r0 != null ? r0.getName() : null;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f3440_resource_name_obfuscated_RES_2130968790);
        int[] iArr = FJ0.G;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.f3440_resource_name_obfuscated_RES_2130968790, 0);
        AbstractC1920bu1.m(this, context, iArr, attributeSet, obtainStyledAttributes, R.attr.f3440_resource_name_obfuscated_RES_2130968790, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.S = resources.getIntArray(resourceId);
            float f = resources.getDisplayMetrics().density;
            int length = this.S.length;
            for (int i = 0; i < length; i++) {
                int[] iArr2 = this.S;
                iArr2[i] = (int) (((float) iArr2[i]) * f);
            }
        }
        this.c0 = obtainStyledAttributes.getDrawable(1);
        obtainStyledAttributes.recycle();
        x();
        super.setOnHierarchyChangeListener(new MA(this));
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public static Rect g() {
        Rect rect = (Rect) f9466J.a();
        return rect == null ? new Rect() : rect;
    }

    @Override // defpackage.AbstractC4801sn0
    public void a(View view, View view2, int i, int i2) {
        C5141un0 un0 = this.f0;
        if (i2 == 1) {
            un0.b = i;
        } else {
            un0.f11435a = i;
        }
        this.U = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            NA na = (NA) getChildAt(i3).getLayoutParams();
            if (na.a(i2)) {
                AbstractC4993tu1 tu1 = na.f8531a;
            }
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public void b(View view, int i) {
        C5141un0 un0 = this.f0;
        if (i == 1) {
            un0.b = 0;
        } else {
            un0.f11435a = 0;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            NA na = (NA) childAt.getLayoutParams();
            if (na.a(i)) {
                AbstractC4993tu1 tu1 = na.f8531a;
                if (tu1 != null) {
                    tu1.o(this, childAt, view, i);
                }
                na.b(i, false);
                na.p = false;
            }
        }
        this.U = null;
    }

    @Override // defpackage.AbstractC4801sn0
    public void c(View view, int i, int i2, int[] iArr, int i3) {
        AbstractC4993tu1 tu1;
        int i4;
        int i5;
        int childCount = getChildCount();
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                NA na = (NA) childAt.getLayoutParams();
                if (na.a(i3) && (tu1 = na.f8531a) != null) {
                    int[] iArr2 = this.O;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    tu1.i(this, childAt, view, i, i2, iArr2, i3);
                    if (i > 0) {
                        i4 = Math.max(i6, this.O[0]);
                    } else {
                        i4 = Math.min(i6, this.O[0]);
                    }
                    i6 = i4;
                    if (i2 > 0) {
                        i5 = Math.max(i7, this.O[1]);
                    } else {
                        i5 = Math.min(i7, this.O[1]);
                    }
                    i7 = i5;
                    z = true;
                }
            }
        }
        iArr[0] = i6;
        iArr[1] = i7;
        if (z) {
            p(1);
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof NA) && super.checkLayoutParams(layoutParams);
    }

    @Override // defpackage.AbstractC4971tn0
    public void d(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        AbstractC4993tu1 tu1;
        int i6;
        boolean z;
        int i7;
        int childCount = getChildCount();
        boolean z2 = false;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                NA na = (NA) childAt.getLayoutParams();
                if (na.a(i5) && (tu1 = na.f8531a) != null) {
                    int[] iArr2 = this.O;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    tu1.j(this, childAt, view, i, i2, i3, i4, i5, iArr2);
                    if (i3 > 0) {
                        i6 = Math.max(i8, this.O[0]);
                    } else {
                        i6 = Math.min(i8, this.O[0]);
                    }
                    i8 = i6;
                    if (i4 > 0) {
                        z = true;
                        i7 = Math.max(i9, this.O[1]);
                    } else {
                        z = true;
                        i7 = Math.min(i9, this.O[1]);
                    }
                    i9 = i7;
                    z2 = z;
                }
            }
        }
        iArr[0] = iArr[0] + i8;
        iArr[1] = iArr[1] + i9;
        if (z2) {
            p(1);
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        AbstractC4993tu1 tu1 = ((NA) view.getLayoutParams()).f8531a;
        if (tu1 != null) {
            Objects.requireNonNull(tu1);
        }
        return super.drawChild(canvas, view, j);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.c0;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    @Override // defpackage.AbstractC4801sn0
    public void e(View view, int i, int i2, int i3, int i4, int i5) {
        d(view, i, i2, i3, i4, 0, this.P);
    }

    @Override // defpackage.AbstractC4801sn0
    public boolean f(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                NA na = (NA) childAt.getLayoutParams();
                AbstractC4993tu1 tu1 = na.f8531a;
                if (tu1 != null) {
                    boolean n = tu1.n(this, childAt, view, view2, i, i2);
                    z |= n;
                    na.b(i2, n);
                } else {
                    na.b(i2, false);
                }
            }
        }
        return z;
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new NA(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new NA(getContext(), attributeSet);
    }

    public int getNestedScrollAxes() {
        return this.f0.a();
    }

    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public final void h(NA na, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) na).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) na).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) na).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) na).bottomMargin));
        rect.set(max, max2, i + max, i2 + max2);
    }

    public void i(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            k(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List j(View view) {
        KF kf = this.L;
        int i = kf.b.L;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList2 = (ArrayList) kf.b.k(i2);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(kf.b.h(i2));
            }
        }
        this.N.clear();
        if (arrayList != null) {
            this.N.addAll(arrayList);
        }
        return this.N;
    }

    public void k(View view, Rect rect) {
        ThreadLocal threadLocal = AbstractC3457ku1.f10312a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal threadLocal2 = AbstractC3457ku1.f10312a;
        Matrix matrix = (Matrix) threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        AbstractC3457ku1.a(this, view, matrix);
        ThreadLocal threadLocal3 = AbstractC3457ku1.b;
        RectF rectF = (RectF) threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public final void l(int i, Rect rect, Rect rect2, NA na, int i2, int i3) {
        int i4;
        int i5;
        int i6 = na.c;
        if (i6 == 0) {
            i6 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i6, i);
        int i7 = na.d;
        if ((i7 & 7) == 0) {
            i7 |= 8388611;
        }
        if ((i7 & 112) == 0) {
            i7 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i7, i);
        int i8 = absoluteGravity & 7;
        int i9 = absoluteGravity & 112;
        int i10 = absoluteGravity2 & 7;
        int i11 = absoluteGravity2 & 112;
        if (i10 == 1) {
            i4 = rect.left + (rect.width() / 2);
        } else if (i10 != 5) {
            i4 = rect.left;
        } else {
            i4 = rect.right;
        }
        if (i11 == 16) {
            i5 = rect.top + (rect.height() / 2);
        } else if (i11 != 80) {
            i5 = rect.top;
        } else {
            i5 = rect.bottom;
        }
        if (i8 == 1) {
            i4 -= i2 / 2;
        } else if (i8 != 5) {
            i4 -= i2;
        }
        if (i9 == 16) {
            i5 -= i3 / 2;
        } else if (i9 != 80) {
            i5 -= i3;
        }
        rect2.set(i4, i5, i2 + i4, i3 + i5);
    }

    public final int m(int i) {
        int[] iArr = this.S;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i);
            return 0;
        } else if (i >= 0 && i < iArr.length) {
            return iArr[i];
        } else {
            Log.e("CoordinatorLayout", "Keyline index " + i + " out of range for " + this);
            return 0;
        }
    }

    public NA n(View view) {
        NA na = (NA) view.getLayoutParams();
        if (!na.b) {
            if (view instanceof KA) {
                KA ka = (KA) view;
                AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
                if (na.f8531a != behavior) {
                    na.f8531a = behavior;
                    na.b = true;
                }
                na.b = true;
            } else {
                LA la = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    la = (LA) cls.getAnnotation(LA.class);
                    if (la != null) {
                        break;
                    }
                }
                if (la != null) {
                    try {
                        AbstractC4993tu1 tu1 = (AbstractC4993tu1) la.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        if (na.f8531a != tu1) {
                            na.f8531a = tu1;
                            na.b = true;
                        }
                    } catch (Exception e) {
                        StringBuilder i = AbstractC2531fV.i("Default behavior class ");
                        i.append(la.value().getName());
                        i.append(" could not be instantiated. Did you forget a default constructor?");
                        Log.e("CoordinatorLayout", i.toString(), e);
                    }
                }
                na.b = true;
            }
        }
        return na;
    }

    public final MotionEvent o(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        return obtain;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        u();
        if (this.W) {
            if (this.V == null) {
                this.V = new OA(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.V);
        }
        if (this.a0 == null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (getFitsSystemWindows()) {
                requestApplyInsets();
            }
        }
        this.R = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
        if (this.W && this.V != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.V);
        }
        View view = this.U;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.R = false;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b0 && this.c0 != null) {
            C3985nz1 nz1 = this.a0;
            int d = nz1 != null ? nz1.d() : 0;
            if (d > 0) {
                this.c0.setBounds(0, 0, getWidth(), d);
                this.c0.draw(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            u();
        }
        boolean t = t(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            this.T = null;
            u();
        }
        return t;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AbstractC4993tu1 tu1;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        int layoutDirection = getLayoutDirection();
        int size = this.K.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) this.K.get(i5);
            if (view.getVisibility() != 8 && ((tu1 = ((NA) view.getLayoutParams()).f8531a) == null || !tu1.g(this, view, layoutDirection))) {
                q(view, layoutDirection);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0347, code lost:
        if (r0.h(r30, r19, r23, r0, r24, 0) == false) goto L_0x0357;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0076, code lost:
        if (r6 != false) goto L_0x00cd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x016d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0113  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r31, int r32) {
        /*
        // Method dump skipped, instructions count: 950
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                NA na = (NA) childAt.getLayoutParams();
                if (na.a(0)) {
                    AbstractC4993tu1 tu1 = na.f8531a;
                }
            }
        }
        return false;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                NA na = (NA) childAt.getLayoutParams();
                if (na.a(0)) {
                    AbstractC4993tu1 tu1 = na.f8531a;
                }
            }
        }
        return false;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        c(view, i, i2, iArr, 0);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        d(view, i, i2, i3, i4, 0, this.P);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        SparseArray sparseArray = savedState.H;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            AbstractC4993tu1 tu1 = n(childAt).f8531a;
            if (!(id == -1 || tu1 == null || (parcelable2 = (Parcelable) sparseArray.get(id)) == null)) {
                tu1.l(this, childAt, parcelable2);
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable m;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            AbstractC4993tu1 tu1 = ((NA) childAt.getLayoutParams()).f8531a;
            if (!(id == -1 || tu1 == null || (m = tu1.m(this, childAt)) == null)) {
                sparseArray.append(id, m);
            }
        }
        savedState.H = sparseArray;
        return savedState;
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return f(view, view2, i, 0);
    }

    public void onStopNestedScroll(View view) {
        b(view, 0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        View view = this.T;
        boolean z2 = false;
        if (view != null) {
            AbstractC4993tu1 tu1 = ((NA) view.getLayoutParams()).f8531a;
            z = tu1 != null ? tu1.p(this, this.T, motionEvent) : false;
        } else {
            z = t(motionEvent, 1);
            if (actionMasked != 0 && z) {
                z2 = true;
            }
        }
        if (this.T == null || actionMasked == 3) {
            z |= super.onTouchEvent(motionEvent);
        } else if (z2) {
            MotionEvent o = o(motionEvent);
            super.onTouchEvent(o);
            o.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.T = null;
            u();
        }
        return z;
    }

    public final void p(int i) {
        int i2;
        Rect rect;
        int i3;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int width;
        int i4;
        int i5;
        int i6;
        int height;
        int i7;
        int i8;
        int i9;
        NA na;
        int i10;
        Rect rect2;
        int i11;
        int i12;
        View view;
        boolean z5;
        int i13;
        NA na2;
        AbstractC4993tu1 tu1;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        int layoutDirection = getLayoutDirection();
        int size = this.K.size();
        Rect g = g();
        Rect g2 = g();
        Rect g3 = g();
        int i14 = 0;
        while (i14 < size) {
            View view2 = (View) this.K.get(i14);
            NA na3 = (NA) view2.getLayoutParams();
            if (i == 0 && view2.getVisibility() == 8) {
                i3 = size;
                rect = g3;
                i2 = i14;
            } else {
                int i15 = 0;
                while (i15 < i14) {
                    if (na3.l == ((View) this.K.get(i15))) {
                        NA na4 = (NA) view2.getLayoutParams();
                        if (na4.k != null) {
                            Rect g4 = g();
                            Rect g5 = g();
                            Rect g6 = g();
                            k(na4.k, g4);
                            i(view2, false, g5);
                            int measuredWidth = view2.getMeasuredWidth();
                            i12 = size;
                            int measuredHeight = view2.getMeasuredHeight();
                            i11 = i14;
                            rect2 = g3;
                            i10 = i15;
                            na = na3;
                            view = view2;
                            l(layoutDirection, g4, g6, na4, measuredWidth, measuredHeight);
                            if (g6.left == g5.left && g6.top == g5.top) {
                                i13 = measuredWidth;
                                na2 = na4;
                                z5 = false;
                            } else {
                                i13 = measuredWidth;
                                na2 = na4;
                                z5 = true;
                            }
                            h(na2, g6, i13, measuredHeight);
                            int i16 = g6.left - g5.left;
                            int i17 = g6.top - g5.top;
                            if (i16 != 0) {
                                AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                                view.offsetLeftAndRight(i16);
                            }
                            if (i17 != 0) {
                                AtomicInteger atomicInteger3 = AbstractC1920bu1.f9571a;
                                view.offsetTopAndBottom(i17);
                            }
                            if (z5 && (tu1 = na2.f8531a) != null) {
                                tu1.d(this, view, na2.k);
                            }
                            g4.setEmpty();
                            C5903zE0 ze0 = f9466J;
                            ze0.b(g4);
                            g5.setEmpty();
                            ze0.b(g5);
                            g6.setEmpty();
                            ze0.b(g6);
                            i15 = i10 + 1;
                            view2 = view;
                            size = i12;
                            i14 = i11;
                            g3 = rect2;
                            na3 = na;
                        }
                    }
                    i10 = i15;
                    na = na3;
                    i12 = size;
                    rect2 = g3;
                    i11 = i14;
                    view = view2;
                    i15 = i10 + 1;
                    view2 = view;
                    size = i12;
                    i14 = i11;
                    g3 = rect2;
                    na3 = na;
                }
                i2 = i14;
                i(view2, true, g2);
                if (na3.g != 0 && !g2.isEmpty()) {
                    int absoluteGravity = Gravity.getAbsoluteGravity(na3.g, layoutDirection);
                    int i18 = absoluteGravity & 112;
                    if (i18 == 48) {
                        g.top = Math.max(g.top, g2.bottom);
                    } else if (i18 == 80) {
                        g.bottom = Math.max(g.bottom, getHeight() - g2.top);
                    }
                    int i19 = absoluteGravity & 7;
                    if (i19 == 3) {
                        g.left = Math.max(g.left, g2.right);
                    } else if (i19 == 5) {
                        g.right = Math.max(g.right, getWidth() - g2.left);
                    }
                }
                if (na3.h != 0 && view2.getVisibility() == 0) {
                    AtomicInteger atomicInteger4 = AbstractC1920bu1.f9571a;
                    if (view2.isLaidOut() && view2.getWidth() > 0 && view2.getHeight() > 0) {
                        NA na5 = (NA) view2.getLayoutParams();
                        AbstractC4993tu1 tu12 = na5.f8531a;
                        Rect g7 = g();
                        Rect g8 = g();
                        g8.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                        g7.set(g8);
                        g8.setEmpty();
                        C5903zE0 ze02 = f9466J;
                        ze02.b(g8);
                        if (g7.isEmpty()) {
                            g7.setEmpty();
                            ze02.b(g7);
                        } else {
                            int absoluteGravity2 = Gravity.getAbsoluteGravity(na5.h, layoutDirection);
                            if ((absoluteGravity2 & 48) != 48 || (i8 = (g7.top - ((ViewGroup.MarginLayoutParams) na5).topMargin) - na5.j) >= (i9 = g.top)) {
                                z2 = false;
                            } else {
                                w(view2, i9 - i8);
                                z2 = true;
                            }
                            if ((absoluteGravity2 & 80) != 80 || (height = ((getHeight() - g7.bottom) - ((ViewGroup.MarginLayoutParams) na5).bottomMargin) + na5.j) >= (i7 = g.bottom)) {
                                z3 = z2;
                            } else {
                                w(view2, height - i7);
                                z3 = true;
                            }
                            if (!z3) {
                                w(view2, 0);
                            }
                            if ((absoluteGravity2 & 3) != 3 || (i5 = (g7.left - ((ViewGroup.MarginLayoutParams) na5).leftMargin) - na5.i) >= (i6 = g.left)) {
                                z4 = false;
                            } else {
                                v(view2, i6 - i5);
                                z4 = true;
                            }
                            if ((absoluteGravity2 & 5) == 5 && (width = ((getWidth() - g7.right) - ((ViewGroup.MarginLayoutParams) na5).rightMargin) + na5.i) < (i4 = g.right)) {
                                v(view2, width - i4);
                                z4 = true;
                            }
                            if (!z4) {
                                v(view2, 0);
                            }
                            g7.setEmpty();
                            ze02.b(g7);
                        }
                    }
                }
                if (i != 2) {
                    rect = g3;
                    rect.set(((NA) view2.getLayoutParams()).q);
                    if (rect.equals(g2)) {
                        i3 = size;
                    } else {
                        ((NA) view2.getLayoutParams()).q.set(g2);
                    }
                } else {
                    rect = g3;
                }
                i3 = size;
                for (int i20 = i2 + 1; i20 < i3; i20++) {
                    View view3 = (View) this.K.get(i20);
                    NA na6 = (NA) view3.getLayoutParams();
                    AbstractC4993tu1 tu13 = na6.f8531a;
                    if (tu13 != null && tu13.c(this, view3, view2)) {
                        if (i != 0 || !na6.p) {
                            if (i != 2) {
                                z = tu13.d(this, view3, view2);
                            } else {
                                tu13.e(this, view3, view2);
                                z = true;
                            }
                            if (i == 1) {
                                na6.p = z;
                            }
                        } else {
                            na6.p = false;
                        }
                    }
                }
            }
            i14 = i2 + 1;
            size = i3;
            g3 = rect;
        }
        g.setEmpty();
        C5903zE0 ze03 = f9466J;
        ze03.b(g);
        g2.setEmpty();
        ze03.b(g2);
        g3.setEmpty();
        ze03.b(g3);
    }

    public void q(View view, int i) {
        NA na = (NA) view.getLayoutParams();
        View view2 = na.k;
        int i2 = 0;
        if (view2 == null && na.f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (view2 != null) {
            Rect g = g();
            Rect g2 = g();
            try {
                k(view2, g);
                NA na2 = (NA) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                l(i, g, g2, na2, measuredWidth, measuredHeight);
                h(na2, g2, measuredWidth, measuredHeight);
                view.layout(g2.left, g2.top, g2.right, g2.bottom);
            } finally {
                g.setEmpty();
                C5903zE0 ze0 = f9466J;
                ze0.b(g);
                g2.setEmpty();
                ze0.b(g2);
            }
        } else {
            int i3 = na.e;
            if (i3 >= 0) {
                NA na3 = (NA) view.getLayoutParams();
                int i4 = na3.c;
                if (i4 == 0) {
                    i4 = 8388661;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
                int i5 = absoluteGravity & 7;
                int i6 = absoluteGravity & 112;
                int width = getWidth();
                int height = getHeight();
                int measuredWidth2 = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                if (i == 1) {
                    i3 = width - i3;
                }
                int m = m(i3) - measuredWidth2;
                if (i5 == 1) {
                    m += measuredWidth2 / 2;
                } else if (i5 == 5) {
                    m += measuredWidth2;
                }
                if (i6 == 16) {
                    i2 = 0 + (measuredHeight2 / 2);
                } else if (i6 == 80) {
                    i2 = measuredHeight2 + 0;
                }
                int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) na3).leftMargin, Math.min(m, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) na3).rightMargin));
                int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) na3).topMargin, Math.min(i2, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) na3).bottomMargin));
                view.layout(max, max2, measuredWidth2 + max, measuredHeight2 + max2);
                return;
            }
            NA na4 = (NA) view.getLayoutParams();
            Rect g3 = g();
            g3.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) na4).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) na4).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) na4).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) na4).bottomMargin);
            if (this.a0 != null) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                if (getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                    g3.left = this.a0.b() + g3.left;
                    g3.top = this.a0.d() + g3.top;
                    g3.right -= this.a0.c();
                    g3.bottom -= this.a0.a();
                }
            }
            Rect g4 = g();
            int i7 = na4.c;
            if ((i7 & 7) == 0) {
                i7 |= 8388611;
            }
            if ((i7 & 112) == 0) {
                i7 |= 48;
            }
            Gravity.apply(i7, view.getMeasuredWidth(), view.getMeasuredHeight(), g3, g4, i);
            view.layout(g4.left, g4.top, g4.right, g4.bottom);
            g3.setEmpty();
            C5903zE0 ze02 = f9466J;
            ze02.b(g3);
            g4.setEmpty();
            ze02.b(g4);
        }
    }

    public void r(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        AbstractC4993tu1 tu1 = ((NA) view.getLayoutParams()).f8531a;
        if (tu1 == null || !tu1.k(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.Q) {
            if (this.T == null) {
                int childCount = getChildCount();
                MotionEvent motionEvent = null;
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    AbstractC4993tu1 tu1 = ((NA) childAt.getLayoutParams()).f8531a;
                    if (tu1 != null) {
                        if (motionEvent == null) {
                            long uptimeMillis = SystemClock.uptimeMillis();
                            motionEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                        }
                        tu1.f(this, childAt, motionEvent);
                    }
                }
                if (motionEvent != null) {
                    motionEvent.recycle();
                }
            }
            u();
            this.Q = true;
        }
    }

    public final boolean s(AbstractC4993tu1 tu1, View view, MotionEvent motionEvent, int i) {
        if (i == 0) {
            return tu1.f(this, view, motionEvent);
        }
        if (i == 1) {
            return tu1.p(this, view, motionEvent);
        }
        throw new IllegalArgumentException();
    }

    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        x();
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.d0 = onHierarchyChangeListener;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.c0;
        if (drawable != null && drawable.isVisible() != z) {
            this.c0.setVisible(z, false);
        }
    }

    public final boolean t(MotionEvent motionEvent, int i) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        List list = this.M;
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator comparator = I;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) list.get(i3);
            NA na = (NA) view.getLayoutParams();
            AbstractC4993tu1 tu1 = na.f8531a;
            if (!(z2 || z3) || actionMasked == 0) {
                if (!z3 && !z2 && tu1 != null && (z2 = s(tu1, view, motionEvent, i))) {
                    this.T = view;
                    if (!(actionMasked == 3 || actionMasked == 1)) {
                        for (int i4 = 0; i4 < i3; i4++) {
                            View view2 = (View) list.get(i4);
                            AbstractC4993tu1 tu12 = ((NA) view2.getLayoutParams()).f8531a;
                            if (tu12 != null) {
                                if (motionEvent2 == null) {
                                    motionEvent2 = o(motionEvent);
                                }
                                s(tu12, view2, motionEvent2, i);
                            }
                        }
                    }
                }
                if (na.f8531a == null) {
                    na.m = false;
                }
                boolean z4 = na.m;
                if (z4) {
                    z = true;
                } else {
                    z = z4 | false;
                    na.m = z;
                }
                z3 = z && !z4;
                if (z && !z3) {
                    break;
                }
            } else if (tu1 != null) {
                if (motionEvent2 == null) {
                    motionEvent2 = o(motionEvent);
                }
                s(tu1, view, motionEvent2, i);
            }
        }
        list.clear();
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        return z2;
    }

    public final void u() {
        View view = this.T;
        if (view != null) {
            AbstractC4993tu1 tu1 = ((NA) view.getLayoutParams()).f8531a;
            if (tu1 != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                tu1.p(this, this.T, obtain);
                obtain.recycle();
            }
            this.T = null;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((NA) getChildAt(i).getLayoutParams()).m = false;
        }
        this.Q = false;
    }

    public final void v(View view, int i) {
        NA na = (NA) view.getLayoutParams();
        int i2 = na.i;
        if (i2 != i) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.offsetLeftAndRight(i - i2);
            na.i = i;
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.c0;
    }

    public final void w(View view, int i) {
        NA na = (NA) view.getLayoutParams();
        int i2 = na.j;
        if (i2 != i) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.offsetTopAndBottom(i - i2);
            na.j = i;
        }
    }

    public final void x() {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (getFitsSystemWindows()) {
            if (this.e0 == null) {
                this.e0 = new JA(this);
            }
            AbstractC1920bu1.o(this, this.e0);
            setSystemUiVisibility(1280);
            return;
        }
        AbstractC1920bu1.o(this, null);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof NA) {
            return new NA((NA) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new NA((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new NA(layoutParams);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new PA();
        public SparseArray H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.H = new SparseArray(readInt);
            for (int i = 0; i < readInt; i++) {
                this.H.append(iArr[i], readParcelableArray[i]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            SparseArray sparseArray = this.H;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.H.keyAt(i2);
                parcelableArr[i2] = (Parcelable) this.H.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
