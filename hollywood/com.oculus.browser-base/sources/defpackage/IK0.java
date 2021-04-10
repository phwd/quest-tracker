package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: IK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IK0 {

    /* renamed from: a  reason: collision with root package name */
    public C2754go f8218a;
    public RecyclerView b;
    public final Rt1 c;
    public final Rt1 d;
    public St1 e;
    public St1 f;
    public E80 g;
    public boolean h = false;
    public boolean i = false;
    public boolean j = true;
    public boolean k = true;
    public int l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public int q;

    public IK0() {
        FK0 fk0 = new FK0(this);
        this.c = fk0;
        GK0 gk0 = new GK0(this);
        this.d = gk0;
        this.e = new St1(fk0);
        this.f = new St1(gk0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if (r5 == 1073741824) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            int r4 = r4 - r6
            r6 = 0
            int r4 = java.lang.Math.max(r6, r4)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L_0x001a
            if (r7 < 0) goto L_0x0011
            goto L_0x001c
        L_0x0011:
            if (r7 != r1) goto L_0x002f
            if (r5 == r2) goto L_0x0020
            if (r5 == 0) goto L_0x002f
            if (r5 == r3) goto L_0x0020
            goto L_0x002f
        L_0x001a:
            if (r7 < 0) goto L_0x001e
        L_0x001c:
            r5 = r3
            goto L_0x0031
        L_0x001e:
            if (r7 != r1) goto L_0x0022
        L_0x0020:
            r7 = r4
            goto L_0x0031
        L_0x0022:
            if (r7 != r0) goto L_0x002f
            if (r5 == r2) goto L_0x002c
            if (r5 != r3) goto L_0x0029
            goto L_0x002c
        L_0x0029:
            r7 = r4
            r5 = r6
            goto L_0x0031
        L_0x002c:
            r7 = r4
            r5 = r2
            goto L_0x0031
        L_0x002f:
            r5 = r6
            r7 = r5
        L_0x0031:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.IK0.A(int, int, int, int, boolean):int");
    }

    public static HK0 S(Context context, AttributeSet attributeSet, int i2, int i3) {
        HK0 hk0 = new HK0();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.v0, i2, i3);
        hk0.f8152a = obtainStyledAttributes.getInt(0, 1);
        hk0.b = obtainStyledAttributes.getInt(10, 1);
        hk0.c = obtainStyledAttributes.getBoolean(9, false);
        hk0.d = obtainStyledAttributes.getBoolean(11, false);
        obtainStyledAttributes.recycle();
        return hk0;
    }

    public static boolean Y(int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (i4 > 0 && i2 != i4) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i2;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i2;
        }
        return true;
    }

    public static int i(int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? Math.max(i3, i4) : size;
        }
        return Math.min(size, Math.max(i3, i4));
    }

    public abstract void A0(Parcelable parcelable);

    public int B(PK0 pk0, VK0 vk0) {
        return -1;
    }

    public abstract Parcelable B0();

    public int C(View view) {
        return view.getBottom() + ((JK0) view.getLayoutParams()).b.bottom;
    }

    public void C0(int i2) {
    }

    public int D(View view) {
        return view.getLeft() - ((JK0) view.getLayoutParams()).b.left;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean D0(defpackage.PK0 r8, defpackage.VK0 r9, int r10, android.os.Bundle r11) {
        /*
        // Method dump skipped, instructions count: 122
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.IK0.D0(PK0, VK0, int, android.os.Bundle):boolean");
    }

    public int E(View view) {
        Rect rect = ((JK0) view.getLayoutParams()).b;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public boolean E0() {
        return false;
    }

    public int F(View view) {
        Rect rect = ((JK0) view.getLayoutParams()).b;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    public void F0(PK0 pk0) {
        for (int z = z() - 1; z >= 0; z--) {
            if (!RecyclerView.M(y(z)).v()) {
                H0(z, pk0);
            }
        }
    }

    public int G(View view) {
        return view.getRight() + ((JK0) view.getLayoutParams()).b.right;
    }

    public void G0(PK0 pk0) {
        int size = pk0.f8685a.size();
        for (int i2 = size - 1; i2 >= 0; i2--) {
            View view = ((XK0) pk0.f8685a.get(i2)).G;
            XK0 M = RecyclerView.M(view);
            if (!M.v()) {
                M.u(false);
                if (M.p()) {
                    this.b.removeDetachedView(view, false);
                }
                EW0 ew0 = this.b.y0;
                if (ew0 != null) {
                    ew0.f(M);
                }
                M.u(true);
                XK0 M2 = RecyclerView.M(view);
                M2.T = null;
                M2.U = false;
                M2.d();
                pk0.h(M2);
            }
        }
        pk0.f8685a.clear();
        ArrayList arrayList = pk0.b;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (size > 0) {
            this.b.invalidate();
        }
    }

    public int H(View view) {
        return view.getTop() - ((JK0) view.getLayoutParams()).b.top;
    }

    public void H0(int i2, PK0 pk0) {
        View y = y(i2);
        J0(i2);
        pk0.g(y);
    }

    public View I() {
        View focusedChild;
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f8218a.c.contains(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    public void I0(View view) {
        C2754go goVar = this.f8218a;
        int indexOfChild = goVar.f10022a.f11475a.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (goVar.b.f(indexOfChild)) {
                goVar.l(view);
            }
            goVar.f10022a.c(indexOfChild);
        }
    }

    public int J() {
        RecyclerView recyclerView = this.b;
        AbstractC5750yK0 yk0 = recyclerView != null ? recyclerView.T : null;
        if (yk0 != null) {
            return yk0.b();
        }
        return 0;
    }

    public void J0(int i2) {
        C2754go goVar;
        int f2;
        View a2;
        if (y(i2) != null && (a2 = goVar.f10022a.a((f2 = (goVar = this.f8218a).f(i2)))) != null) {
            if (goVar.b.f(f2)) {
                goVar.l(a2);
            }
            goVar.f10022a.c(f2);
        }
    }

    public int K() {
        RecyclerView recyclerView = this.b;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return recyclerView.getLayoutDirection();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b8, code lost:
        if (r1 == false) goto L_0x00bf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean K0(androidx.recyclerview.widget.RecyclerView r19, android.view.View r20, android.graphics.Rect r21, boolean r22, boolean r23) {
        /*
        // Method dump skipped, instructions count: 213
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.IK0.K0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public int L() {
        RecyclerView recyclerView = this.b;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return recyclerView.getMinimumHeight();
    }

    public void L0() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public int M() {
        RecyclerView recyclerView = this.b;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return recyclerView.getMinimumWidth();
    }

    public abstract int M0(int i2, PK0 pk0, VK0 vk0);

    public int N() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public abstract void N0(int i2);

    public int O() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public abstract int O0(int i2, PK0 pk0, VK0 vk0);

    public int P() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public void P0(RecyclerView recyclerView) {
        Q0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public int Q() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public void Q0(int i2, int i3) {
        this.p = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        this.n = mode;
        if (mode == 0) {
            int[] iArr = RecyclerView.F;
        }
        this.q = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i3);
        this.o = mode2;
        if (mode2 == 0) {
            int[] iArr2 = RecyclerView.F;
        }
    }

    public int R(View view) {
        return ((JK0) view.getLayoutParams()).a();
    }

    public void R0(Rect rect, int i2, int i3) {
        int P = P() + O() + rect.width();
        int N = N() + Q() + rect.height();
        this.b.setMeasuredDimension(i(i2, P, M()), i(i3, N, L()));
    }

    public void S0(int i2, int i3) {
        int z = z();
        if (z == 0) {
            this.b.p(i2, i3);
            return;
        }
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MAX_VALUE;
        int i6 = Integer.MAX_VALUE;
        int i7 = Integer.MIN_VALUE;
        for (int i8 = 0; i8 < z; i8++) {
            View y = y(i8);
            Rect rect = this.b.Q;
            RecyclerView.N(y, rect);
            int i9 = rect.left;
            if (i9 < i5) {
                i5 = i9;
            }
            int i10 = rect.right;
            if (i10 > i4) {
                i4 = i10;
            }
            int i11 = rect.top;
            if (i11 < i6) {
                i6 = i11;
            }
            int i12 = rect.bottom;
            if (i12 > i7) {
                i7 = i12;
            }
        }
        this.b.Q.set(i5, i6, i4, i7);
        R0(this.b.Q, i2, i3);
    }

    public int T(PK0 pk0, VK0 vk0) {
        return -1;
    }

    public void T0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.b = null;
            this.f8218a = null;
            this.p = 0;
            this.q = 0;
        } else {
            this.b = recyclerView;
            this.f8218a = recyclerView.M;
            this.p = recyclerView.getWidth();
            this.q = recyclerView.getHeight();
        }
        this.n = 1073741824;
        this.o = 1073741824;
    }

    public int U() {
        return 0;
    }

    public boolean U0(View view, int i2, int i3, JK0 jk0) {
        return view.isLayoutRequested() || !this.j || !Y(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) jk0).width) || !Y(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) jk0).height);
    }

    public void V(View view, boolean z, Rect rect) {
        Matrix matrix;
        if (z) {
            Rect rect2 = ((JK0) view.getLayoutParams()).b;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        } else {
            rect.set(0, 0, view.getWidth(), view.getHeight());
        }
        if (!(this.b == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
            RectF rectF = this.b.S;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public boolean V0() {
        return false;
    }

    public abstract boolean W();

    public boolean W0(View view, int i2, int i3, JK0 jk0) {
        return !this.j || !Y(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) jk0).width) || !Y(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) jk0).height);
    }

    public boolean X() {
        return false;
    }

    public abstract void X0(RecyclerView recyclerView, VK0 vk0, int i2);

    public void Y0(E80 e80) {
        E80 e802 = this.g;
        if (!(e802 == null || e80 == e802 || !e802.e)) {
            e802.h();
        }
        this.g = e80;
        RecyclerView recyclerView = this.b;
        recyclerView.N0.c();
        if (e80.h) {
            StringBuilder i2 = AbstractC2531fV.i("An instance of ");
            i2.append(e80.getClass().getSimpleName());
            i2.append(" was started more than once. Each instance of");
            i2.append(e80.getClass().getSimpleName());
            i2.append(" is intended to only be used once. You should create a new instance for each use.");
            Log.w("RecyclerView", i2.toString());
        }
        e80.b = recyclerView;
        e80.c = this;
        int i3 = e80.f7937a;
        if (i3 != -1) {
            recyclerView.Q0.f9076a = i3;
            e80.e = true;
            e80.d = true;
            e80.f = recyclerView.U.u(i3);
            e80.b.N0.a();
            e80.h = true;
            return;
        }
        throw new IllegalArgumentException("Invalid target position");
    }

    public boolean Z(View view, boolean z) {
        boolean z2 = this.e.b(view, 24579) && this.f.b(view, 24579);
        return z ? z2 : !z2;
    }

    public abstract boolean Z0();

    public void a0(View view, int i2, int i3, int i4, int i5) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        Rect rect = jk0.b;
        view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) jk0).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) jk0).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) jk0).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) jk0).bottomMargin);
    }

    public void b(View view) {
        c(view, -1, false);
    }

    public void b0(int i2) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int e2 = recyclerView.M.e();
            for (int i3 = 0; i3 < e2; i3++) {
                recyclerView.M.d(i3).offsetLeftAndRight(i2);
            }
        }
    }

    public final void c(View view, int i2, boolean z) {
        XK0 M = RecyclerView.M(view);
        if (z || M.n()) {
            this.b.N.a(M);
        } else {
            this.b.N.f(M);
        }
        JK0 jk0 = (JK0) view.getLayoutParams();
        if (M.w() || M.o()) {
            if (M.o()) {
                M.T.k(M);
            } else {
                M.d();
            }
            this.f8218a.b(view, i2, view.getLayoutParams(), false);
        } else {
            int i3 = -1;
            if (view.getParent() == this.b) {
                int j2 = this.f8218a.j(view);
                if (i2 == -1) {
                    i2 = this.f8218a.e();
                }
                if (j2 == -1) {
                    StringBuilder i4 = AbstractC2531fV.i("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                    i4.append(this.b.indexOfChild(view));
                    throw new IllegalStateException(AbstractC2531fV.v(this.b, i4));
                } else if (j2 != i2) {
                    IK0 ik0 = this.b.U;
                    View y = ik0.y(j2);
                    if (y != null) {
                        ik0.y(j2);
                        ik0.s(j2);
                        JK0 jk02 = (JK0) y.getLayoutParams();
                        XK0 M2 = RecyclerView.M(y);
                        if (M2.n()) {
                            ik0.b.N.a(M2);
                        } else {
                            ik0.b.N.f(M2);
                        }
                        ik0.f8218a.b(y, i2, jk02, M2.n());
                    } else {
                        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + j2 + ik0.b.toString());
                    }
                }
            } else {
                this.f8218a.a(view, i2, false);
                jk0.c = true;
                E80 e80 = this.g;
                if (e80 != null && e80.e) {
                    Objects.requireNonNull(e80.b);
                    XK0 M3 = RecyclerView.M(view);
                    if (M3 != null) {
                        i3 = M3.g();
                    }
                    if (i3 == e80.f7937a) {
                        e80.f = view;
                    }
                }
            }
        }
        if (jk0.d) {
            M.G.invalidate();
            jk0.d = false;
        }
    }

    public void c0(int i2) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int e2 = recyclerView.M.e();
            for (int i3 = 0; i3 < e2; i3++) {
                recyclerView.M.d(i3).offsetTopAndBottom(i2);
            }
        }
    }

    public abstract void d(String str);

    public void d0(AbstractC5750yK0 yk0, AbstractC5750yK0 yk02) {
    }

    public void e(View view, Rect rect) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.O(view));
        }
    }

    public boolean e0() {
        return false;
    }

    public abstract boolean f();

    public void f0() {
    }

    public abstract boolean g();

    @Deprecated
    public void g0() {
    }

    public boolean h(JK0 jk0) {
        return jk0 != null;
    }

    public abstract void h0(RecyclerView recyclerView, PK0 pk0);

    public abstract View i0(View view, int i2, PK0 pk0, VK0 vk0);

    public abstract void j(int i2, int i3, VK0 vk0, PU pu);

    public abstract void j0(AccessibilityEvent accessibilityEvent);

    public void k(int i2, PU pu) {
    }

    public void k0(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && accessibilityEvent != null) {
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            AbstractC5750yK0 yk0 = this.b.T;
            if (yk0 != null) {
                accessibilityEvent.setItemCount(yk0.b());
            }
        }
    }

    public abstract int l(VK0 vk0);

    public void l0(PK0 pk0, VK0 vk0, D d2) {
        if (this.b.canScrollVertically(-1) || this.b.canScrollHorizontally(-1)) {
            d2.b.addAction(8192);
            d2.b.setScrollable(true);
        }
        if (this.b.canScrollVertically(1) || this.b.canScrollHorizontally(1)) {
            d2.b.addAction(4096);
            d2.b.setScrollable(true);
        }
        d2.i(B.a(T(pk0, vk0), B(pk0, vk0), X(), U()));
    }

    public abstract int m(VK0 vk0);

    public void m0(PK0 pk0, VK0 vk0, View view, D d2) {
    }

    public abstract int n(VK0 vk0);

    public void n0(View view, D d2) {
        XK0 M = RecyclerView.M(view);
        if (M != null && !M.n() && !this.f8218a.k(M.G)) {
            RecyclerView recyclerView = this.b;
            m0(recyclerView.f9482J, recyclerView.Q0, view, d2);
        }
    }

    public abstract int o(VK0 vk0);

    public View o0() {
        return null;
    }

    public abstract int p(VK0 vk0);

    public void p0(RecyclerView recyclerView, int i2, int i3) {
    }

    public abstract int q(VK0 vk0);

    public void q0(RecyclerView recyclerView) {
    }

    public void r(PK0 pk0) {
        int z = z();
        while (true) {
            z--;
            if (z >= 0) {
                View y = y(z);
                XK0 M = RecyclerView.M(y);
                if (!M.v()) {
                    if (!M.l() || M.n() || this.b.T.G) {
                        y(z);
                        s(z);
                        pk0.i(y);
                        this.b.N.f(M);
                    } else {
                        J0(z);
                        pk0.h(M);
                    }
                }
            } else {
                return;
            }
        }
    }

    public void r0(RecyclerView recyclerView, int i2, int i3, int i4) {
    }

    public final void s(int i2) {
        this.f8218a.c(i2);
    }

    public void s0(RecyclerView recyclerView, int i2, int i3) {
    }

    public View t(View view) {
        View D;
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || (D = recyclerView.D(view)) == null || this.f8218a.c.contains(D)) {
            return null;
        }
        return D;
    }

    public void t0() {
    }

    public View u(int i2) {
        int z = z();
        for (int i3 = 0; i3 < z; i3++) {
            View y = y(i3);
            XK0 M = RecyclerView.M(y);
            if (M != null && M.g() == i2 && !M.v() && (this.b.Q0.g || !M.n())) {
                return y;
            }
        }
        return null;
    }

    public void u0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        t0();
    }

    public abstract JK0 v();

    public abstract void v0(PK0 pk0, VK0 vk0);

    public JK0 w(Context context, AttributeSet attributeSet) {
        return new JK0(context, attributeSet);
    }

    public abstract void w0(VK0 vk0);

    public JK0 x(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof JK0) {
            return new JK0((JK0) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new JK0((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new JK0(layoutParams);
    }

    public void x0(int i2, int i3) {
        this.b.p(i2, i3);
    }

    public View y(int i2) {
        C2754go goVar = this.f8218a;
        if (goVar == null) {
            return null;
        }
        return goVar.f10022a.a(goVar.f(i2));
    }

    @Deprecated
    public boolean y0(RecyclerView recyclerView) {
        E80 e80 = this.g;
        return (e80 != null && e80.e) || recyclerView.T();
    }

    public int z() {
        C2754go goVar = this.f8218a;
        if (goVar != null) {
            return goVar.e();
        }
        return 0;
    }

    public boolean z0(RecyclerView recyclerView, View view, View view2) {
        return y0(recyclerView);
    }
}
