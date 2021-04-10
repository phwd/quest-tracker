package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LinearLayoutManager extends IK0 implements UK0 {
    public int A;
    public SavedState B;
    public final C5885z80 C;
    public final A80 D;
    public int E;
    public int[] F;
    public int r;
    public B80 s;
    public AbstractC4308pt0 t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public int z;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C80();
        public int F;
        public int G;
        public boolean H;

        public SavedState() {
        }

        public boolean b() {
            return this.F >= 0;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.F);
            parcel.writeInt(this.G);
            parcel.writeInt(this.H ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.F = parcel.readInt();
            this.G = parcel.readInt();
            this.H = parcel.readInt() != 1 ? false : true;
        }

        public SavedState(SavedState savedState) {
            this.F = savedState.F;
            this.G = savedState.G;
            this.H = savedState.H;
        }
    }

    public LinearLayoutManager(Context context) {
        this(1, false);
    }

    @Override // defpackage.IK0
    public void A0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.B = savedState;
            if (this.z != -1) {
                savedState.F = -1;
            }
            L0();
        }
    }

    public final void A1() {
        if (this.r == 1 || !t1()) {
            this.w = this.v;
        } else {
            this.w = !this.v;
        }
    }

    @Override // defpackage.IK0
    public Parcelable B0() {
        SavedState savedState = this.B;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (z() > 0) {
            g1();
            boolean z2 = this.u ^ this.w;
            savedState2.H = z2;
            if (z2) {
                View r1 = r1();
                savedState2.G = this.t.g() - this.t.b(r1);
                savedState2.F = R(r1);
            } else {
                View s1 = s1();
                savedState2.F = R(s1);
                savedState2.G = this.t.e(s1) - this.t.k();
            }
        } else {
            savedState2.F = -1;
        }
        return savedState2;
    }

    public int B1(int i, PK0 pk0, VK0 vk0) {
        if (z() == 0 || i == 0) {
            return 0;
        }
        g1();
        this.s.f7718a = true;
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        F1(i2, abs, true, vk0);
        B80 b80 = this.s;
        int h1 = h1(pk0, b80, vk0, false) + b80.g;
        if (h1 < 0) {
            return 0;
        }
        if (abs > h1) {
            i = i2 * h1;
        }
        this.t.p(-i);
        this.s.j = i;
        return i;
    }

    public void C1(int i, int i2) {
        this.z = i;
        this.A = i2;
        SavedState savedState = this.B;
        if (savedState != null) {
            savedState.F = -1;
        }
        L0();
    }

    public void D1(int i) {
        if (i == 0 || i == 1) {
            d(null);
            if (i != this.r || this.t == null) {
                AbstractC4308pt0 a2 = AbstractC4308pt0.a(this, i);
                this.t = a2;
                this.C.f11725a = a2;
                this.r = i;
                L0();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(AbstractC2531fV.w("invalid orientation:", i));
    }

    public void E1(boolean z2) {
        d(null);
        if (this.x != z2) {
            this.x = z2;
            L0();
        }
    }

    public final void F1(int i, int i2, boolean z2, VK0 vk0) {
        int i3;
        this.s.l = z1();
        this.s.f = i;
        int[] iArr = this.F;
        boolean z3 = false;
        iArr[0] = 0;
        int i4 = 1;
        iArr[1] = 0;
        a1(vk0, iArr);
        int max = Math.max(0, this.F[0]);
        int max2 = Math.max(0, this.F[1]);
        if (i == 1) {
            z3 = true;
        }
        B80 b80 = this.s;
        int i5 = z3 ? max2 : max;
        b80.h = i5;
        if (!z3) {
            max = max2;
        }
        b80.i = max;
        if (z3) {
            b80.h = this.t.h() + i5;
            View r1 = r1();
            B80 b802 = this.s;
            if (this.w) {
                i4 = -1;
            }
            b802.e = i4;
            int R = R(r1);
            B80 b803 = this.s;
            b802.d = R + b803.e;
            b803.b = this.t.b(r1);
            i3 = this.t.b(r1) - this.t.g();
        } else {
            View s1 = s1();
            B80 b804 = this.s;
            b804.h = this.t.k() + b804.h;
            B80 b805 = this.s;
            if (!this.w) {
                i4 = -1;
            }
            b805.e = i4;
            int R2 = R(s1);
            B80 b806 = this.s;
            b805.d = R2 + b806.e;
            b806.b = this.t.e(s1);
            i3 = (-this.t.e(s1)) + this.t.k();
        }
        B80 b807 = this.s;
        b807.c = i2;
        if (z2) {
            b807.c = i2 - i3;
        }
        b807.g = i3;
    }

    public final void G1(int i, int i2) {
        this.s.c = this.t.g() - i2;
        B80 b80 = this.s;
        b80.e = this.w ? -1 : 1;
        b80.d = i;
        b80.f = 1;
        b80.b = i2;
        b80.g = Integer.MIN_VALUE;
    }

    public final void H1(int i, int i2) {
        this.s.c = i2 - this.t.k();
        B80 b80 = this.s;
        b80.d = i;
        b80.e = this.w ? 1 : -1;
        b80.f = -1;
        b80.b = i2;
        b80.g = Integer.MIN_VALUE;
    }

    @Override // defpackage.IK0
    public int M0(int i, PK0 pk0, VK0 vk0) {
        if (this.r == 1) {
            return 0;
        }
        return B1(i, pk0, vk0);
    }

    @Override // defpackage.IK0
    public void N0(int i) {
        this.z = i;
        this.A = Integer.MIN_VALUE;
        SavedState savedState = this.B;
        if (savedState != null) {
            savedState.F = -1;
        }
        L0();
    }

    @Override // defpackage.IK0
    public int O0(int i, PK0 pk0, VK0 vk0) {
        if (this.r == 0) {
            return 0;
        }
        return B1(i, pk0, vk0);
    }

    @Override // defpackage.IK0
    public boolean V0() {
        boolean z2;
        if (this.o == 1073741824 || this.n == 1073741824) {
            return false;
        }
        int z3 = z();
        int i = 0;
        while (true) {
            if (i >= z3) {
                z2 = false;
                break;
            }
            ViewGroup.LayoutParams layoutParams = y(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                z2 = true;
                break;
            }
            i++;
        }
        return z2;
    }

    @Override // defpackage.IK0
    public boolean W() {
        return true;
    }

    @Override // defpackage.IK0
    public void X0(RecyclerView recyclerView, VK0 vk0, int i) {
        E80 e80 = new E80(recyclerView.getContext());
        e80.f7937a = i;
        Y0(e80);
    }

    @Override // defpackage.IK0
    public boolean Z0() {
        return this.B == null && this.u == this.x;
    }

    @Override // defpackage.UK0
    public PointF a(int i) {
        if (z() == 0) {
            return null;
        }
        boolean z2 = false;
        int i2 = 1;
        if (i < R(y(0))) {
            z2 = true;
        }
        if (z2 != this.w) {
            i2 = -1;
        }
        if (this.r == 0) {
            return new PointF((float) i2, 0.0f);
        }
        return new PointF(0.0f, (float) i2);
    }

    public void a1(VK0 vk0, int[] iArr) {
        int i;
        int l = vk0.f9076a != -1 ? this.t.l() : 0;
        if (this.s.f == -1) {
            i = 0;
        } else {
            i = l;
            l = 0;
        }
        iArr[0] = l;
        iArr[1] = i;
    }

    public void b1(VK0 vk0, B80 b80, PU pu) {
        int i = b80.d;
        if (i >= 0 && i < vk0.b()) {
            pu.a(i, Math.max(0, b80.g));
        }
    }

    public final int c1(VK0 vk0) {
        if (z() == 0) {
            return 0;
        }
        g1();
        return TP0.a(vk0, this.t, j1(!this.y, true), i1(!this.y, true), this, this.y);
    }

    @Override // defpackage.IK0
    public void d(String str) {
        RecyclerView recyclerView;
        if (this.B == null && (recyclerView = this.b) != null) {
            recyclerView.j(str);
        }
    }

    public final int d1(VK0 vk0) {
        if (z() == 0) {
            return 0;
        }
        g1();
        return TP0.b(vk0, this.t, j1(!this.y, true), i1(!this.y, true), this, this.y, this.w);
    }

    public final int e1(VK0 vk0) {
        if (z() == 0) {
            return 0;
        }
        g1();
        return TP0.c(vk0, this.t, j1(!this.y, true), i1(!this.y, true), this, this.y);
    }

    @Override // defpackage.IK0
    public boolean f() {
        return this.r == 0;
    }

    public int f1(int i) {
        if (i == 1) {
            return (this.r != 1 && t1()) ? 1 : -1;
        }
        if (i == 2) {
            return (this.r != 1 && t1()) ? -1 : 1;
        }
        if (i == 17) {
            return this.r == 0 ? -1 : Integer.MIN_VALUE;
        }
        if (i == 33) {
            return this.r == 1 ? -1 : Integer.MIN_VALUE;
        }
        if (i == 66) {
            return this.r == 0 ? 1 : Integer.MIN_VALUE;
        }
        if (i != 130) {
            return Integer.MIN_VALUE;
        }
        return this.r == 1 ? 1 : Integer.MIN_VALUE;
    }

    @Override // defpackage.IK0
    public boolean g() {
        return this.r == 1;
    }

    public void g1() {
        if (this.s == null) {
            this.s = new B80();
        }
    }

    @Override // defpackage.IK0
    public void h0(RecyclerView recyclerView, PK0 pk0) {
        g0();
    }

    public int h1(PK0 pk0, B80 b80, VK0 vk0, boolean z2) {
        int i = b80.c;
        int i2 = b80.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                b80.g = i2 + i;
            }
            x1(pk0, b80);
        }
        int i3 = b80.c + b80.h;
        A80 a80 = this.D;
        while (true) {
            if ((!b80.l && i3 <= 0) || !b80.b(vk0)) {
                break;
            }
            a80.f7654a = 0;
            a80.b = false;
            a80.c = false;
            a80.d = false;
            u1(pk0, vk0, b80, a80);
            if (!a80.b) {
                int i4 = b80.b;
                int i5 = a80.f7654a;
                b80.b = (b80.f * i5) + i4;
                if (!a80.c || b80.k != null || !vk0.g) {
                    b80.c -= i5;
                    i3 -= i5;
                }
                int i6 = b80.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + i5;
                    b80.g = i7;
                    int i8 = b80.c;
                    if (i8 < 0) {
                        b80.g = i7 + i8;
                    }
                    x1(pk0, b80);
                }
                if (z2 && a80.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - b80.c;
    }

    @Override // defpackage.IK0
    public View i0(View view, int i, PK0 pk0, VK0 vk0) {
        int f1;
        View view2;
        View view3;
        A1();
        if (z() == 0 || (f1 = f1(i)) == Integer.MIN_VALUE) {
            return null;
        }
        g1();
        F1(f1, (int) (((float) this.t.l()) * 0.33333334f), false, vk0);
        B80 b80 = this.s;
        b80.g = Integer.MIN_VALUE;
        b80.f7718a = false;
        h1(pk0, b80, vk0, true);
        if (f1 == -1) {
            if (this.w) {
                view2 = m1(z() - 1, -1);
            } else {
                view2 = m1(0, z());
            }
        } else if (this.w) {
            view2 = m1(0, z());
        } else {
            view2 = m1(z() - 1, -1);
        }
        if (f1 == -1) {
            view3 = s1();
        } else {
            view3 = r1();
        }
        if (!view3.hasFocusable()) {
            return view2;
        }
        if (view2 == null) {
            return null;
        }
        return view3;
    }

    public View i1(boolean z2, boolean z3) {
        if (this.w) {
            return n1(0, z(), z2, z3);
        }
        return n1(z() - 1, -1, z2, z3);
    }

    @Override // defpackage.IK0
    public void j(int i, int i2, VK0 vk0, PU pu) {
        if (this.r != 0) {
            i = i2;
        }
        if (z() != 0 && i != 0) {
            g1();
            F1(i > 0 ? 1 : -1, Math.abs(i), true, vk0);
            b1(vk0, this.s, pu);
        }
    }

    @Override // defpackage.IK0
    public void j0(AccessibilityEvent accessibilityEvent) {
        PK0 pk0 = this.b.f9482J;
        k0(accessibilityEvent);
        if (z() > 0) {
            accessibilityEvent.setFromIndex(k1());
            accessibilityEvent.setToIndex(l1());
        }
    }

    public View j1(boolean z2, boolean z3) {
        if (this.w) {
            return n1(z() - 1, -1, z2, z3);
        }
        return n1(0, z(), z2, z3);
    }

    @Override // defpackage.IK0
    public void k(int i, PU pu) {
        boolean z2;
        int i2;
        SavedState savedState = this.B;
        int i3 = -1;
        if (savedState == null || !savedState.b()) {
            A1();
            z2 = this.w;
            i2 = this.z;
            if (i2 == -1) {
                i2 = z2 ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.B;
            z2 = savedState2.H;
            i2 = savedState2.F;
        }
        if (!z2) {
            i3 = 1;
        }
        for (int i4 = 0; i4 < this.E && i2 >= 0 && i2 < i; i4++) {
            pu.a(i2, 0);
            i2 += i3;
        }
    }

    public int k1() {
        View n1 = n1(0, z(), false, true);
        if (n1 == null) {
            return -1;
        }
        return R(n1);
    }

    @Override // defpackage.IK0
    public int l(VK0 vk0) {
        return c1(vk0);
    }

    public int l1() {
        View n1 = n1(z() - 1, -1, false, true);
        if (n1 == null) {
            return -1;
        }
        return R(n1);
    }

    @Override // defpackage.IK0
    public int m(VK0 vk0) {
        return d1(vk0);
    }

    public View m1(int i, int i2) {
        int i3;
        int i4;
        g1();
        if ((i2 > i ? 1 : i2 < i ? (char) 65535 : 0) == 0) {
            return y(i);
        }
        if (this.t.e(y(i)) < this.t.k()) {
            i4 = 16644;
            i3 = 16388;
        } else {
            i4 = 4161;
            i3 = 4097;
        }
        if (this.r == 0) {
            return this.e.a(i, i2, i4, i3);
        }
        return this.f.a(i, i2, i4, i3);
    }

    @Override // defpackage.IK0
    public int n(VK0 vk0) {
        return e1(vk0);
    }

    public View n1(int i, int i2, boolean z2, boolean z3) {
        g1();
        int i3 = 320;
        int i4 = z2 ? 24579 : 320;
        if (!z3) {
            i3 = 0;
        }
        if (this.r == 0) {
            return this.e.a(i, i2, i4, i3);
        }
        return this.f.a(i, i2, i4, i3);
    }

    @Override // defpackage.IK0
    public int o(VK0 vk0) {
        return c1(vk0);
    }

    public View o1(PK0 pk0, VK0 vk0, boolean z2, boolean z3) {
        int i;
        int i2;
        g1();
        int z4 = z();
        int i3 = -1;
        if (z3) {
            i2 = z() - 1;
            i = -1;
        } else {
            i3 = z4;
            i2 = 0;
            i = 1;
        }
        int b = vk0.b();
        int k = this.t.k();
        int g = this.t.g();
        View view = null;
        View view2 = null;
        View view3 = null;
        while (i2 != i3) {
            View y2 = y(i2);
            int R = R(y2);
            int e = this.t.e(y2);
            int b2 = this.t.b(y2);
            if (R >= 0 && R < b) {
                if (!((JK0) y2.getLayoutParams()).c()) {
                    boolean z5 = b2 <= k && e < k;
                    boolean z6 = e >= g && b2 > g;
                    if (!z5 && !z6) {
                        return y2;
                    }
                    if (z2) {
                        if (!z6) {
                            if (view != null) {
                            }
                            view = y2;
                        }
                    } else if (!z5) {
                        if (view != null) {
                        }
                        view = y2;
                    }
                    view2 = y2;
                } else if (view3 == null) {
                    view3 = y2;
                }
            }
            i2 += i;
        }
        if (view != null) {
            return view;
        }
        return view2 != null ? view2 : view3;
    }

    @Override // defpackage.IK0
    public int p(VK0 vk0) {
        return d1(vk0);
    }

    public final int p1(int i, PK0 pk0, VK0 vk0, boolean z2) {
        int g;
        int g2 = this.t.g() - i;
        if (g2 <= 0) {
            return 0;
        }
        int i2 = -B1(-g2, pk0, vk0);
        int i3 = i + i2;
        if (!z2 || (g = this.t.g() - i3) <= 0) {
            return i2;
        }
        this.t.p(g);
        return g + i2;
    }

    @Override // defpackage.IK0
    public int q(VK0 vk0) {
        return e1(vk0);
    }

    public final int q1(int i, PK0 pk0, VK0 vk0, boolean z2) {
        int k;
        int k2 = i - this.t.k();
        if (k2 <= 0) {
            return 0;
        }
        int i2 = -B1(k2, pk0, vk0);
        int i3 = i + i2;
        if (!z2 || (k = i3 - this.t.k()) <= 0) {
            return i2;
        }
        this.t.p(-k);
        return i2 - k;
    }

    public final View r1() {
        return y(this.w ? 0 : z() - 1);
    }

    public final View s1() {
        return y(this.w ? z() - 1 : 0);
    }

    public boolean t1() {
        return K() == 1;
    }

    @Override // defpackage.IK0
    public View u(int i) {
        int z2 = z();
        if (z2 == 0) {
            return null;
        }
        int R = i - R(y(0));
        if (R >= 0 && R < z2) {
            View y2 = y(R);
            if (R(y2) == i) {
                return y2;
            }
        }
        return super.u(i);
    }

    public void u1(PK0 pk0, VK0 vk0, B80 b80, A80 a80) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        View c = b80.c(pk0);
        if (c == null) {
            a80.b = true;
            return;
        }
        JK0 jk0 = (JK0) c.getLayoutParams();
        if (b80.k == null) {
            if (this.w == (b80.f == -1)) {
                c(c, -1, false);
            } else {
                c(c, 0, false);
            }
        } else {
            if (this.w == (b80.f == -1)) {
                c(c, -1, true);
            } else {
                c(c, 0, true);
            }
        }
        JK0 jk02 = (JK0) c.getLayoutParams();
        Rect O = this.b.O(c);
        int A2 = IK0.A(this.p, this.n, P() + O() + ((ViewGroup.MarginLayoutParams) jk02).leftMargin + ((ViewGroup.MarginLayoutParams) jk02).rightMargin + O.left + O.right + 0, ((ViewGroup.MarginLayoutParams) jk02).width, f());
        int A3 = IK0.A(this.q, this.o, N() + Q() + ((ViewGroup.MarginLayoutParams) jk02).topMargin + ((ViewGroup.MarginLayoutParams) jk02).bottomMargin + O.top + O.bottom + 0, ((ViewGroup.MarginLayoutParams) jk02).height, g());
        if (U0(c, A2, A3, jk02)) {
            c.measure(A2, A3);
        }
        a80.f7654a = this.t.c(c);
        if (this.r == 1) {
            if (t1()) {
                i5 = this.p - P();
                i4 = i5 - this.t.d(c);
            } else {
                i4 = O();
                i5 = this.t.d(c) + i4;
            }
            if (b80.f == -1) {
                int i6 = b80.b;
                i = i6;
                i2 = i5;
                i3 = i6 - a80.f7654a;
            } else {
                int i7 = b80.b;
                i3 = i7;
                i2 = i5;
                i = a80.f7654a + i7;
            }
        } else {
            int Q = Q();
            int d = this.t.d(c) + Q;
            if (b80.f == -1) {
                int i8 = b80.b;
                i2 = i8;
                i3 = Q;
                i = d;
                i4 = i8 - a80.f7654a;
            } else {
                int i9 = b80.b;
                i3 = Q;
                i2 = a80.f7654a + i9;
                i = d;
                i4 = i9;
            }
        }
        a0(c, i4, i3, i2, i);
        if (jk0.c() || jk0.b()) {
            a80.c = true;
        }
        a80.d = c.hasFocusable();
    }

    @Override // defpackage.IK0
    public JK0 v() {
        return new JK0(-2, -2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0180  */
    @Override // defpackage.IK0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void v0(defpackage.PK0 r17, defpackage.VK0 r18) {
        /*
        // Method dump skipped, instructions count: 1060
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.v0(PK0, VK0):void");
    }

    public void v1(PK0 pk0, VK0 vk0, C5885z80 z80, int i) {
    }

    @Override // defpackage.IK0
    public void w0(VK0 vk0) {
        this.B = null;
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.C.d();
    }

    public void w1(View view, View view2) {
        RecyclerView recyclerView;
        if (this.B == null && (recyclerView = this.b) != null) {
            recyclerView.j("Cannot drop a view during a scroll or layout calculation");
        }
        g1();
        A1();
        int R = R(view);
        int R2 = R(view2);
        boolean z2 = R < R2 ? true : true;
        if (this.w) {
            if (z2) {
                C1(R2, this.t.g() - (this.t.c(view) + this.t.e(view2)));
            } else {
                C1(R2, this.t.g() - this.t.b(view2));
            }
        } else if (z2) {
            C1(R2, this.t.e(view2));
        } else {
            C1(R2, this.t.b(view2) - this.t.c(view));
        }
    }

    public final void x1(PK0 pk0, B80 b80) {
        if (b80.f7718a && !b80.l) {
            int i = b80.g;
            int i2 = b80.i;
            if (b80.f == -1) {
                int z2 = z();
                if (i >= 0) {
                    int f = (this.t.f() - i) + i2;
                    if (this.w) {
                        for (int i3 = 0; i3 < z2; i3++) {
                            View y2 = y(i3);
                            if (this.t.e(y2) < f || this.t.o(y2) < f) {
                                y1(pk0, 0, i3);
                                return;
                            }
                        }
                        return;
                    }
                    int i4 = z2 - 1;
                    for (int i5 = i4; i5 >= 0; i5--) {
                        View y3 = y(i5);
                        if (this.t.e(y3) < f || this.t.o(y3) < f) {
                            y1(pk0, i4, i5);
                            return;
                        }
                    }
                }
            } else if (i >= 0) {
                int i6 = i - i2;
                int z3 = z();
                if (this.w) {
                    int i7 = z3 - 1;
                    for (int i8 = i7; i8 >= 0; i8--) {
                        View y4 = y(i8);
                        if (this.t.b(y4) > i6 || this.t.n(y4) > i6) {
                            y1(pk0, i7, i8);
                            return;
                        }
                    }
                    return;
                }
                for (int i9 = 0; i9 < z3; i9++) {
                    View y5 = y(i9);
                    if (this.t.b(y5) > i6 || this.t.n(y5) > i6) {
                        y1(pk0, 0, i9);
                        return;
                    }
                }
            }
        }
    }

    public final void y1(PK0 pk0, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    H0(i3, pk0);
                }
                return;
            }
            while (i > i2) {
                H0(i, pk0);
                i--;
            }
        }
    }

    public boolean z1() {
        return this.t.i() == 0 && this.t.f() == 0;
    }

    public LinearLayoutManager(int i, boolean z2) {
        this.r = 1;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = true;
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.B = null;
        this.C = new C5885z80();
        this.D = new A80();
        this.E = 2;
        this.F = new int[2];
        D1(i);
        d(null);
        if (z2 != this.v) {
            this.v = z2;
            L0();
        }
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.r = 1;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = true;
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.B = null;
        this.C = new C5885z80();
        this.D = new A80();
        this.E = 2;
        this.F = new int[2];
        HK0 S = IK0.S(context, attributeSet, i, i2);
        D1(S.f8152a);
        boolean z2 = S.c;
        d(null);
        if (z2 != this.v) {
            this.v = z2;
            L0();
        }
        E1(S.d);
    }
}
