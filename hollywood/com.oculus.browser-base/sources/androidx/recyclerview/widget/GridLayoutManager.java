package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean G = false;
    public int H = -1;
    public int[] I;

    /* renamed from: J  reason: collision with root package name */
    public View[] f9481J;
    public final SparseIntArray K = new SparseIntArray();
    public final SparseIntArray L = new SparseIntArray();
    public OW M = new MW();
    public final Rect N = new Rect();

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Q1(IK0.S(context, attributeSet, i, i2).b);
    }

    @Override // defpackage.IK0
    public int B(PK0 pk0, VK0 vk0) {
        if (this.r == 1) {
            return this.H;
        }
        if (vk0.b() < 1) {
            return 0;
        }
        return L1(pk0, vk0, vk0.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void E1(boolean z) {
        if (!z) {
            d(null);
            if (this.x) {
                this.x = false;
                L0();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    public final void I1(int i) {
        int i2;
        int[] iArr = this.I;
        int i3 = this.H;
        if (!(iArr != null && iArr.length == i3 + 1 && iArr[iArr.length - 1] == i)) {
            iArr = new int[(i3 + 1)];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.I = iArr;
    }

    public final void J1() {
        View[] viewArr = this.f9481J;
        if (viewArr == null || viewArr.length != this.H) {
            this.f9481J = new View[this.H];
        }
    }

    public int K1(int i, int i2) {
        if (this.r != 1 || !t1()) {
            int[] iArr = this.I;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.I;
        int i3 = this.H;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public final int L1(PK0 pk0, VK0 vk0, int i) {
        if (!vk0.g) {
            return this.M.a(i, this.H);
        }
        int c = pk0.c(i);
        if (c != -1) {
            return this.M.a(c, this.H);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int M0(int i, PK0 pk0, VK0 vk0) {
        R1();
        J1();
        if (this.r == 1) {
            return 0;
        }
        return B1(i, pk0, vk0);
    }

    public final int M1(PK0 pk0, VK0 vk0, int i) {
        if (!vk0.g) {
            return this.M.b(i, this.H);
        }
        int i2 = this.L.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int c = pk0.c(i);
        if (c != -1) {
            return this.M.b(c, this.H);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    public final int N1(PK0 pk0, VK0 vk0, int i) {
        if (!vk0.g) {
            return this.M.c(i);
        }
        int i2 = this.K.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int c = pk0.c(i);
        if (c != -1) {
            return this.M.c(c);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int O0(int i, PK0 pk0, VK0 vk0) {
        R1();
        J1();
        if (this.r == 0) {
            return 0;
        }
        return B1(i, pk0, vk0);
    }

    public final void O1(View view, int i, boolean z) {
        int i2;
        int i3;
        NW nw = (NW) view.getLayoutParams();
        Rect rect = nw.b;
        int i4 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) nw).topMargin + ((ViewGroup.MarginLayoutParams) nw).bottomMargin;
        int i5 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) nw).leftMargin + ((ViewGroup.MarginLayoutParams) nw).rightMargin;
        int K1 = K1(nw.e, nw.f);
        if (this.r == 1) {
            i2 = IK0.A(K1, i, i5, ((ViewGroup.MarginLayoutParams) nw).width, false);
            i3 = IK0.A(this.t.l(), this.o, i4, ((ViewGroup.MarginLayoutParams) nw).height, true);
        } else {
            int A = IK0.A(K1, i, i4, ((ViewGroup.MarginLayoutParams) nw).height, false);
            int A2 = IK0.A(this.t.l(), this.n, i5, ((ViewGroup.MarginLayoutParams) nw).width, true);
            i3 = A;
            i2 = A2;
        }
        P1(view, i2, i3, z);
    }

    public final void P1(View view, int i, int i2, boolean z) {
        boolean z2;
        JK0 jk0 = (JK0) view.getLayoutParams();
        if (z) {
            z2 = W0(view, i, i2, jk0);
        } else {
            z2 = U0(view, i, i2, jk0);
        }
        if (z2) {
            view.measure(i, i2);
        }
    }

    public void Q1(int i) {
        if (i != this.H) {
            this.G = true;
            if (i >= 1) {
                this.H = i;
                this.M.f8628a.clear();
                L0();
                return;
            }
            throw new IllegalArgumentException(AbstractC2531fV.w("Span count should be at least 1. Provided ", i));
        }
    }

    @Override // defpackage.IK0
    public void R0(Rect rect, int i, int i2) {
        int i3;
        int i4;
        if (this.I == null) {
            super.R0(rect, i, i2);
        }
        int P = P() + O();
        int N2 = N() + Q();
        if (this.r == 1) {
            i4 = IK0.i(i2, rect.height() + N2, L());
            int[] iArr = this.I;
            i3 = IK0.i(i, iArr[iArr.length - 1] + P, M());
        } else {
            i3 = IK0.i(i, rect.width() + P, M());
            int[] iArr2 = this.I;
            i4 = IK0.i(i2, iArr2[iArr2.length - 1] + N2, L());
        }
        this.b.setMeasuredDimension(i3, i4);
    }

    public final void R1() {
        int i;
        int i2;
        if (this.r == 1) {
            i2 = this.p - P();
            i = O();
        } else {
            i2 = this.q - N();
            i = Q();
        }
        I1(i2 - i);
    }

    @Override // defpackage.IK0
    public int T(PK0 pk0, VK0 vk0) {
        if (this.r == 0) {
            return this.H;
        }
        if (vk0.b() < 1) {
            return 0;
        }
        return L1(pk0, vk0, vk0.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public boolean Z0() {
        return this.B == null && !this.G;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void b1(VK0 vk0, B80 b80, PU pu) {
        int i = this.H;
        for (int i2 = 0; i2 < this.H && b80.b(vk0) && i > 0; i2++) {
            int i3 = b80.d;
            pu.a(i3, Math.max(0, b80.g));
            i -= this.M.c(i3);
            b80.d += b80.e;
        }
    }

    @Override // defpackage.IK0
    public boolean h(JK0 jk0) {
        return jk0 instanceof NW;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ca, code lost:
        if (r13 == (r2 > r15)) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e6, code lost:
        if (r13 == (r2 > r8)) goto L_0x00e8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f0  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View i0(android.view.View r23, int r24, defpackage.PK0 r25, defpackage.VK0 r26) {
        /*
        // Method dump skipped, instructions count: 304
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.i0(android.view.View, int, PK0, VK0):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int m(VK0 vk0) {
        return d1(vk0);
    }

    @Override // defpackage.IK0
    public void m0(PK0 pk0, VK0 vk0, View view, D d) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof NW)) {
            n0(view, d);
            return;
        }
        NW nw = (NW) layoutParams;
        int L1 = L1(pk0, vk0, nw.a());
        if (this.r == 0) {
            d.j(C.a(nw.e, nw.f, L1, 1, false, false));
        } else {
            d.j(C.a(L1, 1, nw.e, nw.f, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int n(VK0 vk0) {
        return e1(vk0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public View o1(PK0 pk0, VK0 vk0, boolean z, boolean z2) {
        int i;
        int z3 = z();
        int i2 = -1;
        int i3 = 1;
        if (z2) {
            i = z() - 1;
            i3 = -1;
        } else {
            i2 = z3;
            i = 0;
        }
        int b = vk0.b();
        g1();
        int k = this.t.k();
        int g = this.t.g();
        View view = null;
        View view2 = null;
        while (i != i2) {
            View y = y(i);
            int R = R(y);
            if (R >= 0 && R < b && M1(pk0, vk0, R) == 0) {
                if (((JK0) y.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = y;
                    }
                } else if (this.t.e(y) < g && this.t.b(y) >= k) {
                    return y;
                } else {
                    if (view == null) {
                        view = y;
                    }
                }
            }
            i += i3;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int p(VK0 vk0) {
        return d1(vk0);
    }

    @Override // defpackage.IK0
    public void p0(RecyclerView recyclerView, int i, int i2) {
        this.M.f8628a.clear();
        this.M.b.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public int q(VK0 vk0) {
        return e1(vk0);
    }

    @Override // defpackage.IK0
    public void q0(RecyclerView recyclerView) {
        this.M.f8628a.clear();
        this.M.b.clear();
    }

    @Override // defpackage.IK0
    public void r0(RecyclerView recyclerView, int i, int i2, int i3) {
        this.M.f8628a.clear();
        this.M.b.clear();
    }

    @Override // defpackage.IK0
    public void s0(RecyclerView recyclerView, int i, int i2) {
        this.M.f8628a.clear();
        this.M.b.clear();
    }

    @Override // defpackage.IK0
    public void u0(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.M.f8628a.clear();
        this.M.b.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void u1(PK0 pk0, VK0 vk0, B80 b80, A80 a80) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        boolean z;
        View c;
        int j = this.t.j();
        boolean z2 = j != 1073741824;
        int i19 = z() > 0 ? this.I[this.H] : 0;
        if (z2) {
            R1();
        }
        boolean z3 = b80.e == 1;
        int i20 = this.H;
        if (!z3) {
            i20 = M1(pk0, vk0, b80.d) + N1(pk0, vk0, b80.d);
        }
        int i21 = 0;
        while (i21 < this.H && b80.b(vk0) && i20 > 0) {
            int i22 = b80.d;
            int N1 = N1(pk0, vk0, i22);
            if (N1 <= this.H) {
                i20 -= N1;
                if (i20 < 0 || (c = b80.c(pk0)) == null) {
                    break;
                }
                this.f9481J[i21] = c;
                i21++;
            } else {
                throw new IllegalArgumentException("Item at position " + i22 + " requires " + N1 + " spans but GridLayoutManager has only " + this.H + " spans.");
            }
        }
        if (i21 == 0) {
            a80.b = true;
            return;
        }
        if (z3) {
            i = 1;
            i2 = i21;
            i3 = 0;
        } else {
            i3 = i21 - 1;
            i2 = -1;
            i = -1;
        }
        int i23 = 0;
        while (i3 != i2) {
            View view = this.f9481J[i3];
            NW nw = (NW) view.getLayoutParams();
            int N12 = N1(pk0, vk0, R(view));
            nw.f = N12;
            nw.e = i23;
            i23 += N12;
            i3 += i;
        }
        float f = 0.0f;
        int i24 = 0;
        for (int i25 = 0; i25 < i21; i25++) {
            View view2 = this.f9481J[i25];
            if (b80.k != null) {
                z = false;
                if (z3) {
                    c(view2, -1, true);
                } else {
                    c(view2, 0, true);
                }
            } else if (z3) {
                b(view2);
                z = false;
            } else {
                z = false;
                c(view2, 0, false);
            }
            e(view2, this.N);
            O1(view2, j, z);
            int c2 = this.t.c(view2);
            if (c2 > i24) {
                i24 = c2;
            }
            float d = (((float) this.t.d(view2)) * 1.0f) / ((float) ((NW) view2.getLayoutParams()).f);
            if (d > f) {
                f = d;
            }
        }
        if (z2) {
            I1(Math.max(Math.round(f * ((float) this.H)), i19));
            i24 = 0;
            for (int i26 = 0; i26 < i21; i26++) {
                View view3 = this.f9481J[i26];
                O1(view3, 1073741824, true);
                int c3 = this.t.c(view3);
                if (c3 > i24) {
                    i24 = c3;
                }
            }
        }
        for (int i27 = 0; i27 < i21; i27++) {
            View view4 = this.f9481J[i27];
            if (this.t.c(view4) != i24) {
                NW nw2 = (NW) view4.getLayoutParams();
                Rect rect = nw2.b;
                int i28 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) nw2).topMargin + ((ViewGroup.MarginLayoutParams) nw2).bottomMargin;
                int i29 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) nw2).leftMargin + ((ViewGroup.MarginLayoutParams) nw2).rightMargin;
                int K1 = K1(nw2.e, nw2.f);
                if (this.r == 1) {
                    i18 = IK0.A(K1, 1073741824, i29, ((ViewGroup.MarginLayoutParams) nw2).width, false);
                    i17 = View.MeasureSpec.makeMeasureSpec(i24 - i28, 1073741824);
                } else {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i24 - i29, 1073741824);
                    i17 = IK0.A(K1, 1073741824, i28, ((ViewGroup.MarginLayoutParams) nw2).height, false);
                    i18 = makeMeasureSpec;
                }
                P1(view4, i18, i17, true);
            }
        }
        a80.f7654a = i24;
        if (this.r == 1) {
            if (b80.f == -1) {
                i16 = b80.b;
                i15 = i16 - i24;
            } else {
                int i30 = b80.b;
                i15 = i30;
                i16 = i24 + i30;
            }
            i5 = i15;
            i6 = 0;
            i4 = i16;
            i7 = 0;
        } else {
            if (b80.f == -1) {
                i7 = b80.b;
                i14 = i7 - i24;
            } else {
                int i31 = b80.b;
                i14 = i31;
                i7 = i24 + i31;
            }
            i6 = i14;
            i5 = 0;
            i4 = 0;
        }
        int i32 = 0;
        while (i32 < i21) {
            View view5 = this.f9481J[i32];
            NW nw3 = (NW) view5.getLayoutParams();
            if (this.r == 1) {
                if (t1()) {
                    i13 = O() + this.I[this.H - nw3.e];
                    i12 = i13 - this.t.d(view5);
                } else {
                    i12 = this.I[nw3.e] + O();
                    i13 = this.t.d(view5) + i12;
                }
                i10 = i12;
                i9 = i5;
                i8 = i4;
                i11 = i13;
            } else {
                int Q = Q() + this.I[nw3.e];
                i11 = i7;
                i10 = i6;
                i9 = Q;
                i8 = this.t.d(view5) + Q;
            }
            a0(view5, i10, i9, i11, i8);
            if (nw3.c() || nw3.b()) {
                a80.c = true;
            }
            a80.d |= view5.hasFocusable();
            i32++;
            i7 = i11;
            i6 = i10;
            i5 = i9;
            i4 = i8;
        }
        Arrays.fill(this.f9481J, (Object) null);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public JK0 v() {
        if (this.r == 0) {
            return new NW(-2, -1);
        }
        return new NW(-1, -2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public void v0(PK0 pk0, VK0 vk0) {
        if (vk0.g) {
            int z = z();
            for (int i = 0; i < z; i++) {
                NW nw = (NW) y(i).getLayoutParams();
                int a2 = nw.a();
                this.K.put(a2, nw.f);
                this.L.put(a2, nw.e);
            }
        }
        super.v0(pk0, vk0);
        this.K.clear();
        this.L.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void v1(PK0 pk0, VK0 vk0, C5885z80 z80, int i) {
        R1();
        if (vk0.b() > 0 && !vk0.g) {
            boolean z = i == 1;
            int M1 = M1(pk0, vk0, z80.b);
            if (z) {
                while (M1 > 0) {
                    int i2 = z80.b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    z80.b = i3;
                    M1 = M1(pk0, vk0, i3);
                }
            } else {
                int b = vk0.b() - 1;
                int i4 = z80.b;
                while (i4 < b) {
                    int i5 = i4 + 1;
                    int M12 = M1(pk0, vk0, i5);
                    if (M12 <= M1) {
                        break;
                    }
                    i4 = i5;
                    M1 = M12;
                }
                z80.b = i4;
            }
        }
        J1();
    }

    @Override // defpackage.IK0
    public JK0 w(Context context, AttributeSet attributeSet) {
        return new NW(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public void w0(VK0 vk0) {
        this.B = null;
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.C.d();
        this.G = false;
    }

    @Override // defpackage.IK0
    public JK0 x(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new NW((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new NW(layoutParams);
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(i2, z);
        Q1(i);
    }

    public GridLayoutManager(Context context, int i) {
        super(1, false);
        Q1(i);
    }
}
