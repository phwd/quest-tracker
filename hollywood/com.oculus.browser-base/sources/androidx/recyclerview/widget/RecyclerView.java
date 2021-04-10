package androidx.recyclerview.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
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
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RecyclerView extends ViewGroup implements AbstractC4461qn0 {
    public static final int[] F = {16843830};
    public static final Class[] G;
    public static final Interpolator H = new animation.InterpolatorC4900tK0();
    public int A0;
    public VelocityTracker B0;
    public int C0;
    public int D0;
    public int E0;
    public int F0;
    public int G0;
    public CY0 H0;
    public final RK0 I;
    public final int I0;

    /* renamed from: J  reason: collision with root package name */
    public final PK0 f9482J;
    public final int J0;
    public SavedState K;
    public float K0;
    public C3312k3 L;
    public float L0;
    public C2754go M;
    public boolean M0;
    public final C3970nu1 N;
    public final WK0 N0;
    public boolean O;
    public RU O0;
    public final Runnable P;
    public PU P0;
    public final Rect Q;
    public final VK0 Q0;
    public final Rect R;
    public MK0 R0;
    public final RectF S;
    public List S0;
    public AbstractC5750yK0 T;
    public boolean T0;
    public IK0 U;
    public boolean U0;
    public QK0 V;
    public DK0 V0;
    public final List W;
    public boolean W0;
    public ZK0 X0;
    public AbstractC4342q40 Y0;
    public final int[] Z0;
    public final ArrayList a0;
    public C4631rn0 a1;
    public final ArrayList b0;
    public final int[] b1;
    public LK0 c0;
    public final int[] c1;
    public boolean d0;
    public final int[] d1;
    public boolean e0;
    public final List e1;
    public boolean f0;
    public Runnable f1;
    public boolean g0;
    public boolean g1;
    public int h0;
    public int h1;
    public boolean i0;
    public int i1;
    public boolean j0;
    public final C5070uK0 j1;
    public boolean k0;
    public int l0;
    public boolean m0;
    public final AccessibilityManager n0;
    public List o0;
    public boolean p0;
    public boolean q0;
    public int r0;
    public int s0;
    public BK0 t0;
    public EdgeEffect u0;
    public EdgeEffect v0;
    public EdgeEffect w0;
    public EdgeEffect x0;
    public EW0 y0;
    public int z0;

    static {
        Class cls = Integer.TYPE;
        G = new Class[]{Context.class, AttributeSet.class, cls, cls};
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.f7260_resource_name_obfuscated_RES_2130969172);
    }

    public static RecyclerView G(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView G2 = G(viewGroup.getChildAt(i));
            if (G2 != null) {
                return G2;
            }
        }
        return null;
    }

    public static XK0 M(View view) {
        if (view == null) {
            return null;
        }
        return ((JK0) view.getLayoutParams()).f8284a;
    }

    public static void N(View view, Rect rect) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        Rect rect2 = jk0.b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) jk0).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) jk0).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) jk0).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) jk0).bottomMargin);
    }

    public static void l(XK0 xk0) {
        WeakReference weakReference = xk0.H;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view != xk0.G) {
                    ViewParent parent = view.getParent();
                    view = parent instanceof View ? (View) parent : null;
                } else {
                    return;
                }
            }
            xk0.H = null;
        }
    }

    public void A() {
        if (this.v0 == null) {
            EdgeEffect a2 = this.t0.a(this);
            this.v0 = a2;
            if (this.O) {
                a2.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                a2.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public void A0(int i) {
        Q().j(i);
    }

    public String B() {
        StringBuilder i = AbstractC2531fV.i(" ");
        i.append(super.toString());
        i.append(", adapter:");
        i.append(this.T);
        i.append(", layout:");
        i.append(this.U);
        i.append(", context:");
        i.append(getContext());
        return i.toString();
    }

    public void B0() {
        E80 e80;
        u0(0);
        this.N0.c();
        IK0 ik0 = this.U;
        if (ik0 != null && (e80 = ik0.g) != null) {
            e80.h();
        }
    }

    public final void C(VK0 vk0) {
        if (this.z0 == 2) {
            OverScroller overScroller = this.N0.H;
            overScroller.getFinalX();
            overScroller.getCurrX();
            Objects.requireNonNull(vk0);
            overScroller.getFinalY();
            overScroller.getCurrY();
            return;
        }
        Objects.requireNonNull(vk0);
    }

    public View D(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = (View) parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public final boolean E(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.b0.size();
        for (int i = 0; i < size; i++) {
            LK0 lk0 = (LK0) this.b0.get(i);
            if (lk0.a(this, motionEvent) && action != 3) {
                this.c0 = lk0;
                return true;
            }
        }
        return false;
    }

    public final void F(int[] iArr) {
        int e = this.M.e();
        if (e == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < e; i3++) {
            XK0 M2 = M(this.M.d(i3));
            if (!M2.v()) {
                int g = M2.g();
                if (g < i) {
                    i = g;
                }
                if (g > i2) {
                    i2 = g;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public XK0 H(int i) {
        XK0 xk0 = null;
        if (this.p0) {
            return null;
        }
        int h = this.M.h();
        for (int i2 = 0; i2 < h; i2++) {
            XK0 M2 = M(this.M.g(i2));
            if (M2 != null && !M2.n() && I(M2) == i) {
                if (!this.M.k(M2.G)) {
                    return M2;
                }
                xk0 = M2;
            }
        }
        return xk0;
    }

    public int I(XK0 xk0) {
        if (!xk0.i(524) && xk0.k()) {
            C3312k3 k3Var = this.L;
            int i = xk0.I;
            int size = k3Var.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                C3141j3 j3Var = (C3141j3) k3Var.b.get(i2);
                int i3 = j3Var.f10183a;
                if (i3 != 1) {
                    if (i3 == 2) {
                        int i4 = j3Var.b;
                        if (i4 <= i) {
                            int i5 = j3Var.d;
                            if (i4 + i5 <= i) {
                                i -= i5;
                            }
                        } else {
                            continue;
                        }
                    } else if (i3 == 8) {
                        int i6 = j3Var.b;
                        if (i6 == i) {
                            i = j3Var.d;
                        } else {
                            if (i6 < i) {
                                i--;
                            }
                            if (j3Var.d <= i) {
                                i++;
                            }
                        }
                    }
                } else if (j3Var.b <= i) {
                    i += j3Var.d;
                }
            }
            return i;
        }
        return -1;
    }

    public long J(XK0 xk0) {
        if (this.T.G) {
            return xk0.K;
        }
        return (long) xk0.I;
    }

    public int K(View view) {
        XK0 M2 = M(view);
        if (M2 != null) {
            return M2.e();
        }
        return -1;
    }

    public XK0 L(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return M(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public Rect O(View view) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        if (!jk0.c) {
            return jk0.b;
        }
        if (this.Q0.g && (jk0.b() || jk0.f8284a.l())) {
            return jk0.b;
        }
        Rect rect = jk0.b;
        rect.set(0, 0, 0, 0);
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            this.Q.set(0, 0, 0, 0);
            ((EK0) this.a0.get(i)).g(this.Q, view, this, this.Q0);
            int i2 = rect.left;
            Rect rect2 = this.Q;
            rect.left = i2 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        jk0.c = false;
        return rect;
    }

    public OK0 P() {
        return this.f9482J.d();
    }

    public final C4631rn0 Q() {
        if (this.a1 == null) {
            this.a1 = new C4631rn0(this);
        }
        return this.a1;
    }

    public boolean R() {
        return !this.g0 || this.p0 || this.L.g();
    }

    public void S() {
        if (this.a0.size() != 0) {
            IK0 ik0 = this.U;
            if (ik0 != null) {
                ik0.d("Cannot invalidate item decorations during a scroll or layout");
            }
            V();
            requestLayout();
        }
    }

    public boolean T() {
        return this.r0 > 0;
    }

    public void U(int i) {
        if (this.U != null) {
            u0(2);
            this.U.N0(i);
            awakenScrollBars();
        }
    }

    public void V() {
        int h = this.M.h();
        for (int i = 0; i < h; i++) {
            ((JK0) this.M.g(i).getLayoutParams()).c = true;
        }
        PK0 pk0 = this.f9482J;
        int size = pk0.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            JK0 jk0 = (JK0) ((XK0) pk0.c.get(i2)).G.getLayoutParams();
            if (jk0 != null) {
                jk0.c = true;
            }
        }
    }

    public void W(int i, int i2, boolean z) {
        int i3 = i + i2;
        int h = this.M.h();
        for (int i4 = 0; i4 < h; i4++) {
            XK0 M2 = M(this.M.g(i4));
            if (M2 != null && !M2.v()) {
                int i5 = M2.I;
                if (i5 >= i3) {
                    M2.r(-i2, z);
                    this.Q0.f = true;
                } else if (i5 >= i) {
                    M2.b(8);
                    M2.r(-i2, z);
                    M2.I = i - 1;
                    this.Q0.f = true;
                }
            }
        }
        PK0 pk0 = this.f9482J;
        int size = pk0.c.size();
        while (true) {
            size--;
            if (size >= 0) {
                XK0 xk0 = (XK0) pk0.c.get(size);
                if (xk0 != null) {
                    int i6 = xk0.I;
                    if (i6 >= i3) {
                        xk0.r(-i2, z);
                    } else if (i6 >= i) {
                        xk0.b(8);
                        pk0.f(size);
                    }
                }
            } else {
                requestLayout();
                return;
            }
        }
    }

    public void X() {
    }

    public void Y() {
    }

    public void Z() {
        this.r0++;
    }

    public void a0(boolean z) {
        int i;
        boolean z2 = true;
        int i2 = this.r0 - 1;
        this.r0 = i2;
        if (i2 < 1) {
            this.r0 = 0;
            if (z) {
                int i3 = this.l0;
                this.l0 = 0;
                if (i3 != 0) {
                    AccessibilityManager accessibilityManager = this.n0;
                    if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
                        z2 = false;
                    }
                    if (z2) {
                        AccessibilityEvent obtain = AccessibilityEvent.obtain();
                        obtain.setEventType(2048);
                        obtain.setContentChangeTypes(i3);
                        sendAccessibilityEventUnchecked(obtain);
                    }
                }
                for (int size = this.e1.size() - 1; size >= 0; size--) {
                    XK0 xk0 = (XK0) this.e1.get(size);
                    if (xk0.G.getParent() == this && !xk0.v() && (i = xk0.W) != -1) {
                        View view = xk0.G;
                        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                        view.setImportantForAccessibility(i);
                        xk0.W = -1;
                    }
                }
                this.e1.clear();
            }
        }
    }

    @Override // android.view.View, android.view.ViewGroup
    public void addFocusables(ArrayList arrayList, int i, int i2) {
        IK0 ik0 = this.U;
        if (ik0 == null || !ik0.e0()) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    public final void b0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.A0) {
            int i = actionIndex == 0 ? 1 : 0;
            this.A0 = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.E0 = x;
            this.C0 = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.F0 = y;
            this.D0 = y;
        }
    }

    public void c0() {
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof JK0) && this.U.h((JK0) layoutParams);
    }

    public int computeHorizontalScrollExtent() {
        IK0 ik0 = this.U;
        if (ik0 != null && ik0.f()) {
            return this.U.l(this.Q0);
        }
        return 0;
    }

    public int computeHorizontalScrollOffset() {
        IK0 ik0 = this.U;
        if (ik0 != null && ik0.f()) {
            return this.U.m(this.Q0);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        IK0 ik0 = this.U;
        if (ik0 != null && ik0.f()) {
            return this.U.n(this.Q0);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        IK0 ik0 = this.U;
        if (ik0 != null && ik0.g()) {
            return this.U.o(this.Q0);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        IK0 ik0 = this.U;
        if (ik0 != null && ik0.g()) {
            return this.U.p(this.Q0);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        IK0 ik0 = this.U;
        if (ik0 != null && ik0.g()) {
            return this.U.q(this.Q0);
        }
        return 0;
    }

    public void d0() {
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return Q().a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return Q().b(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return Q().c(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return Q().e(i, i2, i3, i4, iArr);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        int size = this.a0.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ((EK0) this.a0.get(i)).i(canvas, this, this.Q0);
        }
        EdgeEffect edgeEffect = this.u0;
        boolean z3 = true;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.O ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) ((-getHeight()) + paddingBottom), 0.0f);
            EdgeEffect edgeEffect2 = this.u0;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.v0;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.O) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.v0;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.w0;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.O ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate((float) (-paddingTop), (float) (-width));
            EdgeEffect edgeEffect6 = this.w0;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.x0;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.O) {
                canvas.translate((float) (getPaddingRight() + (-getWidth())), (float) (getPaddingBottom() + (-getHeight())));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            EdgeEffect edgeEffect8 = this.x0;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(save4);
        }
        if (z || this.y0 == null || this.a0.size() <= 0 || !this.y0.h()) {
            z3 = z;
        }
        if (z3) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void e0() {
        if (!this.W0 && this.d0) {
            Runnable runnable = this.f1;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postOnAnimation(runnable);
            this.W0 = true;
        }
    }

    public final void f(XK0 xk0) {
        View view = xk0.G;
        boolean z = view.getParent() == this;
        this.f9482J.k(L(view));
        if (xk0.p()) {
            this.M.b(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.M.a(view, -1, true);
        } else {
            C2754go goVar = this.M;
            int indexOfChild = goVar.f10022a.f11475a.indexOfChild(view);
            if (indexOfChild >= 0) {
                goVar.b.h(indexOfChild);
                goVar.i(view);
                return;
            }
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void f0() {
        boolean z;
        boolean z2 = false;
        if (this.p0) {
            C3312k3 k3Var = this.L;
            k3Var.l(k3Var.b);
            k3Var.l(k3Var.c);
            k3Var.f = 0;
            if (this.q0) {
                this.U.q0(this);
            }
        }
        if (this.y0 != null && this.U.Z0()) {
            this.L.j();
        } else {
            this.L.c();
        }
        boolean z3 = this.T0 || this.U0;
        VK0 vk0 = this.Q0;
        boolean z4 = this.g0 && this.y0 != null && ((z = this.p0) || z3 || this.U.h) && (!z || this.T.G);
        vk0.j = z4;
        if (z4 && z3 && !this.p0) {
            if (this.y0 != null && this.U.Z0()) {
                z2 = true;
            }
        }
        vk0.k = z2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:114:0x016a, code lost:
        if (r3 > 0) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0184, code lost:
        if (r6 > 0) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0187, code lost:
        if (r3 < 0) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x018a, code lost:
        if (r6 < 0) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0192, code lost:
        if ((r6 * r2) <= 0) goto L_0x019d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x019a, code lost:
        if ((r6 * r2) >= 0) goto L_0x019d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:132:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View focusSearch(android.view.View r14, int r15) {
        /*
        // Method dump skipped, instructions count: 422
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public void g(EK0 ek0) {
        IK0 ik0 = this.U;
        if (ik0 != null) {
            ik0.d("Cannot add item decoration during a scroll  or layout");
        }
        if (this.a0.isEmpty()) {
            setWillNotDraw(false);
        }
        this.a0.add(ek0);
        V();
        requestLayout();
    }

    public void g0(boolean z) {
        this.q0 = z | this.q0;
        this.p0 = true;
        int h = this.M.h();
        for (int i = 0; i < h; i++) {
            XK0 M2 = M(this.M.g(i));
            if (M2 != null && !M2.v()) {
                M2.b(6);
            }
        }
        V();
        PK0 pk0 = this.f9482J;
        int size = pk0.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            XK0 xk0 = (XK0) pk0.c.get(i2);
            if (xk0 != null) {
                xk0.b(6);
                xk0.a(null);
            }
        }
        AbstractC5750yK0 yk0 = pk0.h.T;
        if (yk0 == null || !yk0.G) {
            pk0.e();
        }
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        IK0 ik0 = this.U;
        if (ik0 != null) {
            return ik0.v();
        }
        throw new IllegalStateException(AbstractC2531fV.v(this, AbstractC2531fV.i("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        IK0 ik0 = this.U;
        if (ik0 != null) {
            return ik0.w(getContext(), attributeSet);
        }
        throw new IllegalStateException(AbstractC2531fV.v(this, AbstractC2531fV.i("RecyclerView has no LayoutManager")));
    }

    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public int getBaseline() {
        IK0 ik0 = this.U;
        if (ik0 == null) {
            return super.getBaseline();
        }
        Objects.requireNonNull(ik0);
        return -1;
    }

    public int getChildDrawingOrder(int i, int i2) {
        AbstractC4342q40 q40 = this.Y0;
        if (q40 == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        Objects.requireNonNull(q40);
        throw null;
    }

    public boolean getClipToPadding() {
        return this.O;
    }

    public void h(KK0 kk0) {
        if (this.o0 == null) {
            this.o0 = new ArrayList();
        }
        this.o0.add(kk0);
    }

    public void h0(XK0 xk0, CK0 ck0) {
        xk0.t(0, 8192);
        if (this.Q0.h && xk0.q() && !xk0.n() && !xk0.v()) {
            this.N.b.i(J(xk0), xk0);
        }
        this.N.c(xk0, ck0);
    }

    public boolean hasNestedScrollingParent() {
        return Q().h(0);
    }

    public void i(MK0 mk0) {
        if (this.S0 == null) {
            this.S0 = new ArrayList();
        }
        this.S0.add(mk0);
    }

    public void i0() {
        EW0 ew0 = this.y0;
        if (ew0 != null) {
            ew0.g();
        }
        IK0 ik0 = this.U;
        if (ik0 != null) {
            ik0.F0(this.f9482J);
            this.U.G0(this.f9482J);
        }
        this.f9482J.b();
    }

    public boolean isAttachedToWindow() {
        return this.d0;
    }

    public final boolean isLayoutSuppressed() {
        return this.j0;
    }

    public boolean isNestedScrollingEnabled() {
        return Q().d;
    }

    public void j(String str) {
        if (T()) {
            if (str == null) {
                throw new IllegalStateException(AbstractC2531fV.v(this, AbstractC2531fV.i("Cannot call this method while RecyclerView is computing a layout or scrolling")));
            }
            throw new IllegalStateException(str);
        } else if (this.s0 > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(AbstractC2531fV.v(this, AbstractC2531fV.i(""))));
        }
    }

    public void j0(EK0 ek0) {
        IK0 ik0 = this.U;
        if (ik0 != null) {
            ik0.d("Cannot remove item decoration during a scroll  or layout");
        }
        this.a0.remove(ek0);
        if (this.a0.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        V();
        requestLayout();
    }

    public final void k() {
        m0();
        u0(0);
    }

    public void k0(MK0 mk0) {
        List list = this.S0;
        if (list != null) {
            list.remove(mk0);
        }
    }

    public final void l0(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.Q.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof JK0) {
            JK0 jk0 = (JK0) layoutParams;
            if (!jk0.c) {
                Rect rect = jk0.b;
                Rect rect2 = this.Q;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.Q);
            offsetRectIntoDescendantCoords(view, this.Q);
        }
        this.U.K0(this, view, this.Q, !this.g0, view2 == null);
    }

    public void m() {
        int h = this.M.h();
        for (int i = 0; i < h; i++) {
            XK0 M2 = M(this.M.g(i));
            if (!M2.v()) {
                M2.c();
            }
        }
        PK0 pk0 = this.f9482J;
        int size = pk0.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((XK0) pk0.c.get(i2)).c();
        }
        int size2 = pk0.f8685a.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ((XK0) pk0.f8685a.get(i3)).c();
        }
        ArrayList arrayList = pk0.b;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                ((XK0) pk0.b.get(i4)).c();
            }
        }
    }

    public final void m0() {
        VelocityTracker velocityTracker = this.B0;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean z = false;
        A0(0);
        EdgeEffect edgeEffect = this.u0;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.u0.isFinished();
        }
        EdgeEffect edgeEffect2 = this.v0;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.v0.isFinished();
        }
        EdgeEffect edgeEffect3 = this.w0;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.w0.isFinished();
        }
        EdgeEffect edgeEffect4 = this.x0;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.x0.isFinished();
        }
        if (z) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
    }

    public void n(int i, int i2) {
        boolean z;
        EdgeEffect edgeEffect = this.u0;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            z = false;
        } else {
            this.u0.onRelease();
            z = this.u0.isFinished();
        }
        EdgeEffect edgeEffect2 = this.w0;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.w0.onRelease();
            z |= this.w0.isFinished();
        }
        EdgeEffect edgeEffect3 = this.v0;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.v0.onRelease();
            z |= this.v0.isFinished();
        }
        EdgeEffect edgeEffect4 = this.x0;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.x0.onRelease();
            z |= this.x0.isFinished();
        }
        if (z) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean n0(int r18, int r19, android.view.MotionEvent r20, int r21) {
        /*
        // Method dump skipped, instructions count: 319
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.n0(int, int, android.view.MotionEvent, int):boolean");
    }

    public void o() {
        if (!this.g0 || this.p0) {
            int i = AbstractC4969tm1.f11370a;
            Trace.beginSection("RV FullInvalidate");
            r();
            Trace.endSection();
        } else if (this.L.g()) {
            C3312k3 k3Var = this.L;
            int i2 = k3Var.f;
            boolean z = false;
            if ((i2 & 4) != 0) {
                if (!((i2 & 11) != 0)) {
                    int i3 = AbstractC4969tm1.f11370a;
                    Trace.beginSection("RV PartialInvalidate");
                    x0();
                    Z();
                    this.L.j();
                    if (!this.i0) {
                        int e = this.M.e();
                        int i4 = 0;
                        while (true) {
                            if (i4 < e) {
                                XK0 M2 = M(this.M.d(i4));
                                if (M2 != null && !M2.v() && M2.q()) {
                                    z = true;
                                    break;
                                }
                                i4++;
                            } else {
                                break;
                            }
                        }
                        if (z) {
                            r();
                        } else {
                            this.L.b();
                        }
                    }
                    z0(true);
                    a0(true);
                    Trace.endSection();
                    return;
                }
            }
            if (k3Var.g()) {
                int i5 = AbstractC4969tm1.f11370a;
                Trace.beginSection("RV FullInvalidate");
                r();
                Trace.endSection();
            }
        }
    }

    public void o0(int i, int i2, int[] iArr) {
        XK0 xk0;
        x0();
        Z();
        int i3 = AbstractC4969tm1.f11370a;
        Trace.beginSection("RV Scroll");
        C(this.Q0);
        int M02 = i != 0 ? this.U.M0(i, this.f9482J, this.Q0) : 0;
        int O02 = i2 != 0 ? this.U.O0(i2, this.f9482J, this.Q0) : 0;
        Trace.endSection();
        int e = this.M.e();
        for (int i4 = 0; i4 < e; i4++) {
            View d = this.M.d(i4);
            XK0 L2 = L(d);
            if (!(L2 == null || (xk0 = L2.O) == null)) {
                View view = xk0.G;
                int left = d.getLeft();
                int top = d.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        a0(true);
        z0(false);
        if (iArr != null) {
            iArr[0] = M02;
            iArr[1] = O02;
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r0 = 0;
        this.d0 = true;
        this.g0 = this.g0 && !isLayoutRequested();
        IK0 ik0 = this.U;
        if (ik0 != null) {
            ik0.i = true;
            ik0.f0();
        }
        this.W0 = false;
        ThreadLocal threadLocal = RU.F;
        RU ru = (RU) threadLocal.get();
        this.O0 = ru;
        if (ru == null) {
            this.O0 = new RU();
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            Display display = getDisplay();
            float f = 60.0f;
            if (!isInEditMode() && display != null) {
                float refreshRate = display.getRefreshRate();
                if (refreshRate >= 30.0f) {
                    f = refreshRate;
                }
            }
            RU ru2 = this.O0;
            ru2.f8835J = (long) (1.0E9f / f);
            threadLocal.set(ru2);
        }
        this.O0.H.add(this);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EW0 ew0 = this.y0;
        if (ew0 != null) {
            ew0.g();
        }
        B0();
        this.d0 = false;
        IK0 ik0 = this.U;
        if (ik0 != null) {
            PK0 pk0 = this.f9482J;
            ik0.i = false;
            ik0.h0(this, pk0);
        }
        this.e1.clear();
        removeCallbacks(this.f1);
        Objects.requireNonNull(this.N);
        do {
        } while (C3799mu1.f10458a.a() != null);
        RU ru = this.O0;
        if (ru != null) {
            ru.H.remove(this);
            this.O0 = null;
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            ((EK0) this.a0.get(i)).h(canvas, this, this.Q0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onGenericMotionEvent(android.view.MotionEvent r14) {
        /*
        // Method dump skipped, instructions count: 223
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x00bd */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Object[] objArr;
        if (this.j0) {
            return false;
        }
        this.c0 = null;
        if (E(motionEvent)) {
            k();
            return true;
        }
        IK0 ik0 = this.U;
        if (ik0 == null) {
            return false;
        }
        boolean f = ik0.f();
        boolean g = this.U.g();
        if (this.B0 == null) {
            this.B0 = VelocityTracker.obtain();
        }
        this.B0.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.k0) {
                this.k0 = false;
            }
            this.A0 = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.E0 = x;
            this.C0 = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.F0 = y;
            this.D0 = y;
            if (this.z0 == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                u0(1);
                A0(1);
            }
            int[] iArr = this.c1;
            iArr[1] = 0;
            iArr[0] = 0;
            if (g) {
                boolean z = f ? 1 : 0;
                char c = f ? 1 : 0;
                f = z | true;
            }
            int i = f ? 1 : 0;
            int i2 = f ? 1 : 0;
            int i3 = f ? 1 : 0;
            y0(i, 0);
        } else if (actionMasked == 1) {
            this.B0.clear();
            A0(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.A0);
            if (findPointerIndex < 0) {
                StringBuilder i4 = AbstractC2531fV.i("Error processing scroll; pointer index for id ");
                i4.append(this.A0);
                i4.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", i4.toString());
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.z0 != 1) {
                int i5 = x2 - this.C0;
                int i6 = y2 - this.D0;
                if (!f || Math.abs(i5) <= this.G0) {
                    objArr = null;
                } else {
                    this.E0 = x2;
                    objArr = 1;
                }
                if (g && Math.abs(i6) > this.G0) {
                    this.F0 = y2;
                    objArr = 1;
                }
                if (objArr != null) {
                    u0(1);
                }
            }
        } else if (actionMasked == 3) {
            k();
        } else if (actionMasked == 5) {
            this.A0 = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.E0 = x3;
            this.C0 = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.F0 = y3;
            this.D0 = y3;
        } else if (actionMasked == 6) {
            b0(motionEvent);
        }
        if (this.z0 == 1) {
            return true;
        }
        return false;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = AbstractC4969tm1.f11370a;
        Trace.beginSection("RV OnLayout");
        r();
        Trace.endSection();
        this.g0 = true;
    }

    public void onMeasure(int i, int i2) {
        IK0 ik0 = this.U;
        if (ik0 == null) {
            p(i, i2);
            return;
        }
        boolean z = false;
        if (ik0.W()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.U.x0(i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.g1 = z;
            if (!z && this.T != null) {
                if (this.Q0.d == 1) {
                    s();
                }
                this.U.Q0(i, i2);
                this.Q0.i = true;
                t();
                this.U.S0(i, i2);
                if (this.U.V0()) {
                    this.U.Q0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.Q0.i = true;
                    t();
                    this.U.S0(i, i2);
                }
                this.h1 = getMeasuredWidth();
                this.i1 = getMeasuredHeight();
            }
        } else if (this.e0) {
            this.U.x0(i, i2);
        } else {
            if (this.m0) {
                x0();
                Z();
                f0();
                a0(true);
                VK0 vk0 = this.Q0;
                if (vk0.k) {
                    vk0.g = true;
                } else {
                    this.L.c();
                    this.Q0.g = false;
                }
                this.m0 = false;
                z0(false);
            } else if (this.Q0.k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            AbstractC5750yK0 yk0 = this.T;
            if (yk0 != null) {
                this.Q0.e = yk0.b();
            } else {
                this.Q0.e = 0;
            }
            x0();
            this.U.x0(i, i2);
            z0(false);
            this.Q0.g = false;
        }
    }

    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (T()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.K = savedState;
        super.onRestoreInstanceState(savedState.G);
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.K;
        if (savedState2 != null) {
            savedState.H = savedState2.H;
        } else {
            IK0 ik0 = this.U;
            if (ik0 != null) {
                savedState.H = ik0.B0();
            } else {
                savedState.H = null;
            }
        }
        return savedState;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            this.x0 = null;
            this.v0 = null;
            this.w0 = null;
            this.u0 = null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:53:0x00f8 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0246, code lost:
        if (r4 == false) goto L_0x0249;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x029f, code lost:
        if (r0 != false) goto L_0x02a4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0110  */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r23) {
        /*
        // Method dump skipped, instructions count: 726
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setMeasuredDimension(IK0.i(i, paddingRight, getMinimumWidth()), IK0.i(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public void p0(int i) {
        if (!this.j0) {
            B0();
            IK0 ik0 = this.U;
            if (ik0 == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            ik0.N0(i);
            awakenScrollBars();
        }
    }

    public void q(View view) {
        XK0 M2 = M(view);
        Y();
        AbstractC5750yK0 yk0 = this.T;
        if (!(yk0 == null || M2 == null)) {
            Objects.requireNonNull(yk0);
        }
        List list = this.o0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((KK0) this.o0.get(size)).d(view);
            }
        }
    }

    public void q0(AbstractC5750yK0 yk0) {
        suppressLayout(false);
        AbstractC5750yK0 yk02 = this.T;
        if (yk02 != null) {
            yk02.F.unregisterObserver(this.I);
            this.T.n(this);
        }
        i0();
        C3312k3 k3Var = this.L;
        k3Var.l(k3Var.b);
        k3Var.l(k3Var.c);
        k3Var.f = 0;
        AbstractC5750yK0 yk03 = this.T;
        this.T = yk0;
        if (yk0 != null) {
            yk0.F.registerObserver(this.I);
            yk0.i(this);
        }
        IK0 ik0 = this.U;
        if (ik0 != null) {
            ik0.d0(yk03, this.T);
        }
        PK0 pk0 = this.f9482J;
        AbstractC5750yK0 yk04 = this.T;
        pk0.b();
        OK0 d = pk0.d();
        Objects.requireNonNull(d);
        if (yk03 != null) {
            d.b--;
        }
        if (d.b == 0) {
            d.a();
        }
        if (yk04 != null) {
            d.b++;
        }
        this.Q0.f = true;
        g0(false);
        requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:142:0x033c, code lost:
        if (r15.M.k(getFocusedChild()) == false) goto L_0x03ff;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void r() {
        /*
        // Method dump skipped, instructions count: 1033
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.r():void");
    }

    public boolean r0(XK0 xk0, int i) {
        if (T()) {
            xk0.W = i;
            this.e1.add(xk0);
            return false;
        }
        View view = xk0.G;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.setImportantForAccessibility(i);
        return true;
    }

    public void removeDetachedView(View view, boolean z) {
        XK0 M2 = M(view);
        if (M2 != null) {
            if (M2.p()) {
                M2.P &= -257;
            } else if (!M2.v()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(M2);
                throw new IllegalArgumentException(AbstractC2531fV.v(this, sb));
            }
        }
        view.clearAnimation();
        q(view);
        super.removeDetachedView(view, z);
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.U.z0(this, view, view2) && view2 != null) {
            l0(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.U.K0(this, view, rect, z, false);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.b0.size();
        for (int i = 0; i < size; i++) {
            ((LK0) this.b0.get(i)).e(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (this.h0 != 0 || this.j0) {
            this.i0 = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s() {
        int i;
        View D;
        this.Q0.a(1);
        C(this.Q0);
        this.Q0.i = false;
        x0();
        C3970nu1 nu1 = this.N;
        nu1.f10519a.clear();
        nu1.b.a();
        Z();
        f0();
        View focusedChild = (!this.M0 || !hasFocus() || this.T == null) ? null : getFocusedChild();
        XK0 L2 = (focusedChild == null || (D = D(focusedChild)) == null) ? null : L(D);
        long j = -1;
        if (L2 == null) {
            VK0 vk0 = this.Q0;
            vk0.m = -1;
            vk0.l = -1;
            vk0.n = -1;
        } else {
            VK0 vk02 = this.Q0;
            if (this.T.G) {
                j = L2.K;
            }
            vk02.m = j;
            if (this.p0) {
                i = -1;
            } else if (L2.n()) {
                i = L2.f9202J;
            } else {
                i = L2.e();
            }
            vk02.l = i;
            VK0 vk03 = this.Q0;
            View view = L2.G;
            int id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    id = view.getId();
                }
            }
            vk03.n = id;
        }
        VK0 vk04 = this.Q0;
        vk04.h = vk04.j && this.U0;
        this.U0 = false;
        this.T0 = false;
        vk04.g = vk04.k;
        vk04.e = this.T.b();
        F(this.Z0);
        if (this.Q0.j) {
            int e = this.M.e();
            for (int i2 = 0; i2 < e; i2++) {
                XK0 M2 = M(this.M.d(i2));
                if (!M2.v() && (!M2.l() || this.T.G)) {
                    this.N.c(M2, this.y0.j(this.Q0, M2, EW0.c(M2), M2.h()));
                    if (this.Q0.h && M2.q() && !M2.n() && !M2.v() && !M2.l()) {
                        this.N.b.i(J(M2), M2);
                    }
                }
            }
        }
        if (this.Q0.k) {
            int h = this.M.h();
            for (int i3 = 0; i3 < h; i3++) {
                XK0 M3 = M(this.M.g(i3));
                if (!M3.v() && M3.f9202J == -1) {
                    M3.f9202J = M3.I;
                }
            }
            VK0 vk05 = this.Q0;
            boolean z = vk05.f;
            vk05.f = false;
            this.U.v0(this.f9482J, vk05);
            this.Q0.f = z;
            for (int i4 = 0; i4 < this.M.e(); i4++) {
                XK0 M4 = M(this.M.d(i4));
                if (!M4.v()) {
                    C3799mu1 mu1 = (C3799mu1) this.N.f10519a.getOrDefault(M4, null);
                    if (!((mu1 == null || (mu1.b & 4) == 0) ? false : true)) {
                        int c = EW0.c(M4);
                        boolean i5 = M4.i(8192);
                        if (!i5) {
                            c |= 4096;
                        }
                        CK0 j2 = this.y0.j(this.Q0, M4, c, M4.h());
                        if (i5) {
                            h0(M4, j2);
                        } else {
                            C3970nu1 nu12 = this.N;
                            C3799mu1 mu12 = (C3799mu1) nu12.f10519a.getOrDefault(M4, null);
                            if (mu12 == null) {
                                mu12 = C3799mu1.a();
                                nu12.f10519a.put(M4, mu12);
                            }
                            mu12.b |= 2;
                            mu12.c = j2;
                        }
                    }
                }
            }
            m();
        } else {
            m();
        }
        a0(true);
        z0(false);
        this.Q0.d = 2;
    }

    public void s0(EW0 ew0) {
        EW0 ew02 = this.y0;
        if (ew02 != null) {
            ew02.g();
            this.y0.f7966a = null;
        }
        this.y0 = ew0;
        if (ew0 != null) {
            ew0.f7966a = this.V0;
        }
    }

    public void scrollBy(int i, int i2) {
        IK0 ik0 = this.U;
        if (ik0 == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.j0) {
            boolean f = ik0.f();
            boolean g = this.U.g();
            if (f || g) {
                if (!f) {
                    i = 0;
                }
                if (!g) {
                    i2 = 0;
                }
                n0(i, i2, null, 0);
            }
        }
    }

    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        int i = 0;
        if (T()) {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            if (contentChangeTypes != 0) {
                i = contentChangeTypes;
            }
            this.l0 |= i;
            i = 1;
        }
        if (i == 0) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public void setClipToPadding(boolean z) {
        if (z != this.O) {
            this.x0 = null;
            this.v0 = null;
            this.w0 = null;
            this.u0 = null;
        }
        this.O = z;
        super.setClipToPadding(z);
        if (this.g0) {
            requestLayout();
        }
    }

    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    public void setNestedScrollingEnabled(boolean z) {
        C4631rn0 Q2 = Q();
        if (Q2.d) {
            View view = Q2.c;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.stopNestedScroll();
        }
        Q2.d = z;
    }

    public boolean startNestedScroll(int i) {
        return Q().i(i, 0);
    }

    public void stopNestedScroll() {
        Q().j(0);
    }

    public final void suppressLayout(boolean z) {
        if (z != this.j0) {
            j("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.j0 = false;
                if (!(!this.i0 || this.U == null || this.T == null)) {
                    requestLayout();
                }
                this.i0 = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.j0 = true;
            this.k0 = true;
            B0();
        }
    }

    public final void t() {
        x0();
        Z();
        this.Q0.a(6);
        this.L.c();
        this.Q0.e = this.T.b();
        this.Q0.c = 0;
        if (this.K != null) {
            AbstractC5750yK0 yk0 = this.T;
            int a2 = AbstractC5580xK0.a(yk0.H);
            if (a2 == 1 ? yk0.b() > 0 : a2 != 2) {
                Parcelable parcelable = this.K.H;
                if (parcelable != null) {
                    this.U.A0(parcelable);
                }
                this.K = null;
            }
        }
        VK0 vk0 = this.Q0;
        vk0.g = false;
        this.U.v0(this.f9482J, vk0);
        VK0 vk02 = this.Q0;
        vk02.f = false;
        vk02.j = vk02.j && this.y0 != null;
        vk02.d = 4;
        a0(true);
        z0(false);
    }

    public void t0(IK0 ik0) {
        if (ik0 != this.U) {
            B0();
            if (this.U != null) {
                EW0 ew0 = this.y0;
                if (ew0 != null) {
                    ew0.g();
                }
                this.U.F0(this.f9482J);
                this.U.G0(this.f9482J);
                this.f9482J.b();
                if (this.d0) {
                    IK0 ik02 = this.U;
                    PK0 pk0 = this.f9482J;
                    ik02.i = false;
                    ik02.h0(this, pk0);
                }
                this.U.T0(null);
                this.U = null;
            } else {
                this.f9482J.b();
            }
            C2754go goVar = this.M;
            C2583fo foVar = goVar.b;
            foVar.f9953a = 0;
            C2583fo foVar2 = foVar.b;
            if (foVar2 != null) {
                foVar2.f9953a = 0;
                C2583fo foVar3 = foVar2.b;
                if (foVar3 != null) {
                    foVar3.g();
                }
            }
            int size = goVar.c.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                C5240vK0 vk0 = goVar.f10022a;
                Objects.requireNonNull(vk0);
                XK0 M2 = M((View) goVar.c.get(size));
                if (M2 != null) {
                    vk0.f11475a.r0(M2, M2.V);
                    M2.V = 0;
                }
                goVar.c.remove(size);
            }
            C5240vK0 vk02 = goVar.f10022a;
            int b = vk02.b();
            for (int i = 0; i < b; i++) {
                View a2 = vk02.a(i);
                vk02.f11475a.q(a2);
                a2.clearAnimation();
            }
            vk02.f11475a.removeAllViews();
            this.U = ik0;
            if (ik0 != null) {
                if (ik0.b == null) {
                    ik0.T0(this);
                    if (this.d0) {
                        IK0 ik03 = this.U;
                        ik03.i = true;
                        ik03.f0();
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("LayoutManager ");
                    sb.append(ik0);
                    sb.append(" is already attached to a RecyclerView:");
                    throw new IllegalArgumentException(AbstractC2531fV.v(ik0.b, sb));
                }
            }
            this.f9482J.l();
            requestLayout();
        }
    }

    public boolean u(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return Q().c(i, i2, iArr, iArr2, i3);
    }

    public void u0(int i) {
        E80 e80;
        if (i != this.z0) {
            this.z0 = i;
            if (i != 2) {
                this.N0.c();
                IK0 ik0 = this.U;
                if (!(ik0 == null || (e80 = ik0.g) == null)) {
                    e80.h();
                }
            }
            IK0 ik02 = this.U;
            if (ik02 != null) {
                ik02.C0(i);
            }
            c0();
            MK0 mk0 = this.R0;
            if (mk0 != null) {
                mk0.a(this, i);
            }
            List list = this.S0;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        ((MK0) this.S0.get(size)).a(this, i);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final void v(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        Q().f(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public void v0(int i, int i2, Interpolator interpolator, int i3, boolean z) {
        IK0 ik0 = this.U;
        if (ik0 == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.j0) {
            int i4 = 0;
            if (!ik0.f()) {
                i = 0;
            }
            if (!this.U.g()) {
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
                        y0(i4, 1);
                    }
                    this.N0.b(i, i2, i3, interpolator);
                    return;
                }
                scrollBy(i, i2);
            }
        }
    }

    public void w(int i, int i2) {
        this.s0++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        d0();
        MK0 mk0 = this.R0;
        if (mk0 != null) {
            mk0.b(this, i, i2);
        }
        List list = this.S0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((MK0) this.S0.get(size)).b(this, i, i2);
            }
        }
        this.s0--;
    }

    public void w0(int i) {
        if (!this.j0) {
            IK0 ik0 = this.U;
            if (ik0 == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                ik0.X0(this, this.Q0, i);
            }
        }
    }

    public void x() {
        if (this.x0 == null) {
            EdgeEffect a2 = this.t0.a(this);
            this.x0 = a2;
            if (this.O) {
                a2.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                a2.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public void x0() {
        int i = this.h0 + 1;
        this.h0 = i;
        if (i == 1 && !this.j0) {
            this.i0 = false;
        }
    }

    public void y() {
        if (this.u0 == null) {
            EdgeEffect a2 = this.t0.a(this);
            this.u0 = a2;
            if (this.O) {
                a2.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                a2.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public boolean y0(int i, int i2) {
        return Q().i(i, i2);
    }

    public void z() {
        if (this.w0 == null) {
            EdgeEffect a2 = this.t0.a(this);
            this.w0 = a2;
            if (this.O) {
                a2.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                a2.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void z0(boolean z) {
        if (this.h0 < 1) {
            this.h0 = 1;
        }
        if (!z && !this.j0) {
            this.i0 = false;
        }
        if (this.h0 == 1) {
            if (z && this.i0 && !this.j0 && this.U != null && this.T != null) {
                r();
            }
            if (!this.j0) {
                this.i0 = false;
            }
        }
        this.h0--;
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        float f;
        float f2;
        ClassLoader classLoader;
        Constructor<? extends U> constructor;
        this.I = new RK0(this);
        this.f9482J = new PK0(this);
        this.N = new C3970nu1();
        this.P = new RunnableC4560rK0(this);
        this.Q = new Rect();
        this.R = new Rect();
        this.S = new RectF();
        this.W = new ArrayList();
        this.a0 = new ArrayList();
        this.b0 = new ArrayList();
        this.h0 = 0;
        this.p0 = false;
        this.q0 = false;
        this.r0 = 0;
        this.s0 = 0;
        this.t0 = new BK0();
        this.y0 = new VD();
        this.z0 = 0;
        this.A0 = -1;
        this.K0 = Float.MIN_VALUE;
        this.L0 = Float.MIN_VALUE;
        this.M0 = true;
        this.N0 = new WK0(this);
        this.P0 = new PU();
        this.Q0 = new VK0();
        this.T0 = false;
        this.U0 = false;
        this.V0 = new DK0(this);
        this.W0 = false;
        char c = 2;
        this.Z0 = new int[2];
        this.b1 = new int[2];
        this.c1 = new int[2];
        this.d1 = new int[2];
        this.e1 = new ArrayList();
        this.f1 = new RunnableC4730sK0(this);
        this.h1 = 0;
        this.i1 = 0;
        this.j1 = new C5070uK0(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.G0 = viewConfiguration.getScaledTouchSlop();
        Method method = AbstractC2091cu1.f9729a;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            f = viewConfiguration.getScaledHorizontalScrollFactor();
        } else {
            f = AbstractC2091cu1.a(viewConfiguration, context);
        }
        this.K0 = f;
        if (i2 >= 26) {
            f2 = viewConfiguration.getScaledVerticalScrollFactor();
        } else {
            f2 = AbstractC2091cu1.a(viewConfiguration, context);
        }
        this.L0 = f2;
        this.I0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.J0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.y0.f7966a = this.V0;
        this.L = new C3312k3(new C5410wK0(this));
        this.M = new C2754go(new C5240vK0(this));
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if ((i2 >= 26 ? getImportantForAutofill() : 0) == 0 && i2 >= 26) {
            setImportantForAutofill(8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.n0 = (AccessibilityManager) getContext().getSystemService("accessibility");
        ZK0 zk0 = new ZK0(this);
        this.X0 = zk0;
        AbstractC1920bu1.n(this, zk0);
        int[] iArr = FJ0.v0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        AbstractC1920bu1.m(this, context, iArr, attributeSet, obtainStyledAttributes, i, 0);
        String string = obtainStyledAttributes.getString(8);
        if (obtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.O = obtainStyledAttributes.getBoolean(1, true);
        boolean z = obtainStyledAttributes.getBoolean(3, false);
        this.f0 = z;
        int i3 = 4;
        if (z) {
            StateListDrawable stateListDrawable = (StateListDrawable) obtainStyledAttributes.getDrawable(6);
            Drawable drawable = obtainStyledAttributes.getDrawable(7);
            StateListDrawable stateListDrawable2 = (StateListDrawable) obtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(5);
            if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                throw new IllegalArgumentException(AbstractC2531fV.v(this, AbstractC2531fV.i("Trying to set fast scroller without both required drawables.")));
            }
            Resources resources = getContext().getResources();
            new C3200jO(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.f19360_resource_name_obfuscated_RES_2131165555), resources.getDimensionPixelSize(R.dimen.f19380_resource_name_obfuscated_RES_2131165557), resources.getDimensionPixelOffset(R.dimen.f19370_resource_name_obfuscated_RES_2131165556));
            i3 = 4;
            c = 2;
        }
        obtainStyledAttributes.recycle();
        if (string != null) {
            String trim = string.trim();
            if (!trim.isEmpty()) {
                if (trim.charAt(0) == '.') {
                    trim = context.getPackageName() + trim;
                } else if (!trim.contains(".")) {
                    trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                }
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(trim, false, classLoader).asSubclass(IK0.class);
                    Object[] objArr = null;
                    try {
                        constructor = asSubclass.getConstructor(G);
                        Object[] objArr2 = new Object[i3];
                        objArr2[0] = context;
                        objArr2[1] = attributeSet;
                        objArr2[c] = Integer.valueOf(i);
                        objArr2[3] = 0;
                        objArr = objArr2;
                    } catch (NoSuchMethodException e) {
                        try {
                            constructor = asSubclass.getConstructor(new Class[0]);
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + trim, e2);
                        }
                    }
                    constructor.setAccessible(true);
                    t0((IK0) constructor.newInstance(objArr));
                } catch (ClassNotFoundException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + trim, e3);
                } catch (InvocationTargetException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + trim, e4);
                } catch (InstantiationException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + trim, e5);
                } catch (IllegalAccessException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + trim, e6);
                } catch (ClassCastException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + trim, e7);
                }
            }
        }
        int[] iArr2 = F;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i, 0);
        AbstractC1920bu1.m(this, context, iArr2, attributeSet, obtainStyledAttributes2, i, 0);
        boolean z2 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z2);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new SK0();
        public Parcelable H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.H = parcel.readParcelable(classLoader == null ? IK0.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            parcel.writeParcelable(this.H, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        IK0 ik0 = this.U;
        if (ik0 != null) {
            return ik0.x(layoutParams);
        }
        throw new IllegalStateException(AbstractC2531fV.v(this, AbstractC2531fV.i("RecyclerView has no LayoutManager")));
    }
}
