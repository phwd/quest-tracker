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
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StaggeredGridLayoutManager extends IK0 implements UK0 {
    public BitSet A;
    public int B;
    public int C;
    public RZ0 D;
    public int E;
    public boolean F;
    public boolean G;
    public SavedState H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public final Rect f9483J;
    public final OZ0 K;
    public boolean L;
    public int[] M;
    public final Runnable N;
    public int r = -1;
    public TZ0[] s;
    public AbstractC4308pt0 t;
    public AbstractC4308pt0 u;
    public int v;
    public int w;
    public final H70 x;
    public boolean y;
    public boolean z;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new SZ0();
        public int F;
        public int G;
        public int H;
        public int[] I;

        /* renamed from: J  reason: collision with root package name */
        public int f9484J;
        public int[] K;
        public List L;
        public boolean M;
        public boolean N;
        public boolean O;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.F);
            parcel.writeInt(this.G);
            parcel.writeInt(this.H);
            if (this.H > 0) {
                parcel.writeIntArray(this.I);
            }
            parcel.writeInt(this.f9484J);
            if (this.f9484J > 0) {
                parcel.writeIntArray(this.K);
            }
            parcel.writeInt(this.M ? 1 : 0);
            parcel.writeInt(this.N ? 1 : 0);
            parcel.writeInt(this.O ? 1 : 0);
            parcel.writeList(this.L);
        }

        public SavedState(Parcel parcel) {
            this.F = parcel.readInt();
            this.G = parcel.readInt();
            int readInt = parcel.readInt();
            this.H = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.I = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.f9484J = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.K = iArr2;
                parcel.readIntArray(iArr2);
            }
            boolean z = false;
            this.M = parcel.readInt() == 1;
            this.N = parcel.readInt() == 1;
            this.O = parcel.readInt() == 1 ? true : z;
            this.L = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.H = savedState.H;
            this.F = savedState.F;
            this.G = savedState.G;
            this.I = savedState.I;
            this.f9484J = savedState.f9484J;
            this.K = savedState.K;
            this.M = savedState.M;
            this.N = savedState.N;
            this.O = savedState.O;
            this.L = savedState.L;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.y = false;
        this.z = false;
        this.B = -1;
        this.C = Integer.MIN_VALUE;
        this.D = new RZ0();
        this.E = 2;
        this.f9483J = new Rect();
        this.K = new OZ0(this);
        this.L = true;
        this.N = new NZ0(this);
        HK0 S = IK0.S(context, attributeSet, i, i2);
        int i3 = S.f8152a;
        if (i3 == 0 || i3 == 1) {
            d(null);
            if (i3 != this.v) {
                this.v = i3;
                AbstractC4308pt0 pt0 = this.t;
                this.t = this.u;
                this.u = pt0;
                L0();
            }
            int i4 = S.b;
            d(null);
            if (i4 != this.r) {
                this.D.a();
                L0();
                this.r = i4;
                this.A = new BitSet(this.r);
                this.s = new TZ0[this.r];
                for (int i5 = 0; i5 < this.r; i5++) {
                    this.s[i5] = new TZ0(this, i5);
                }
                L0();
            }
            boolean z2 = S.c;
            d(null);
            SavedState savedState = this.H;
            if (!(savedState == null || savedState.M == z2)) {
                savedState.M = z2;
            }
            this.y = z2;
            L0();
            this.x = new H70();
            this.t = AbstractC4308pt0.a(this, this.v);
            this.u = AbstractC4308pt0.a(this, 1 - this.v);
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    @Override // defpackage.IK0
    public void A0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.H = savedState;
            if (this.B != -1) {
                savedState.I = null;
                savedState.H = 0;
                savedState.F = -1;
                savedState.G = -1;
                savedState.I = null;
                savedState.H = 0;
                savedState.f9484J = 0;
                savedState.K = null;
                savedState.L = null;
            }
            L0();
        }
    }

    public final void A1(int i) {
        H70 h70 = this.x;
        h70.e = i;
        int i2 = 1;
        if (this.z != (i == -1)) {
            i2 = -1;
        }
        h70.d = i2;
    }

    @Override // defpackage.IK0
    public Parcelable B0() {
        int i;
        View view;
        int i2;
        int i3;
        int[] iArr;
        SavedState savedState = this.H;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.M = this.y;
        savedState2.N = this.F;
        savedState2.O = this.G;
        RZ0 rz0 = this.D;
        if (rz0 == null || (iArr = rz0.f8839a) == null) {
            savedState2.f9484J = 0;
        } else {
            savedState2.K = iArr;
            savedState2.f9484J = iArr.length;
            savedState2.L = rz0.b;
        }
        int i4 = -1;
        if (z() > 0) {
            if (this.F) {
                i = l1();
            } else {
                i = k1();
            }
            savedState2.F = i;
            if (this.z) {
                view = g1(true);
            } else {
                view = h1(true);
            }
            if (view != null) {
                i4 = R(view);
            }
            savedState2.G = i4;
            int i5 = this.r;
            savedState2.H = i5;
            savedState2.I = new int[i5];
            for (int i6 = 0; i6 < this.r; i6++) {
                if (this.F) {
                    i2 = this.s[i6].h(Integer.MIN_VALUE);
                    if (i2 != Integer.MIN_VALUE) {
                        i3 = this.t.g();
                    } else {
                        savedState2.I[i6] = i2;
                    }
                } else {
                    i2 = this.s[i6].k(Integer.MIN_VALUE);
                    if (i2 != Integer.MIN_VALUE) {
                        i3 = this.t.k();
                    } else {
                        savedState2.I[i6] = i2;
                    }
                }
                i2 -= i3;
                savedState2.I[i6] = i2;
            }
        } else {
            savedState2.F = -1;
            savedState2.G = -1;
            savedState2.H = 0;
        }
        return savedState2;
    }

    public final void B1(int i, int i2) {
        for (int i3 = 0; i3 < this.r; i3++) {
            if (!this.s[i3].f8965a.isEmpty()) {
                D1(this.s[i3], i, i2);
            }
        }
    }

    @Override // defpackage.IK0
    public void C0(int i) {
        if (i == 0) {
            b1();
        }
    }

    public final void C1(int i, VK0 vk0) {
        int i2;
        int i3;
        int i4;
        H70 h70 = this.x;
        boolean z2 = false;
        h70.b = 0;
        h70.c = i;
        E80 e80 = this.g;
        if (!(e80 != null && e80.e) || (i4 = vk0.f9076a) == -1) {
            i3 = 0;
            i2 = 0;
        } else {
            if (this.z == (i4 < i)) {
                i3 = this.t.l();
                i2 = 0;
            } else {
                i2 = this.t.l();
                i3 = 0;
            }
        }
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && recyclerView.O) {
            this.x.f = this.t.k() - i2;
            this.x.g = this.t.g() + i3;
        } else {
            this.x.g = this.t.f() + i3;
            this.x.f = -i2;
        }
        H70 h702 = this.x;
        h702.h = false;
        h702.f8137a = true;
        if (this.t.i() == 0 && this.t.f() == 0) {
            z2 = true;
        }
        h702.i = z2;
    }

    public final void D1(TZ0 tz0, int i, int i2) {
        int i3 = tz0.d;
        if (i == -1) {
            int i4 = tz0.b;
            if (i4 == Integer.MIN_VALUE) {
                tz0.c();
                i4 = tz0.b;
            }
            if (i4 + i3 <= i2) {
                this.A.set(tz0.e, false);
                return;
            }
            return;
        }
        int i5 = tz0.c;
        if (i5 == Integer.MIN_VALUE) {
            tz0.b();
            i5 = tz0.c;
        }
        if (i5 - i3 >= i2) {
            this.A.set(tz0.e, false);
        }
    }

    public final int E1(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode);
        }
        return i;
    }

    @Override // defpackage.IK0
    public int M0(int i, PK0 pk0, VK0 vk0) {
        return z1(i, pk0, vk0);
    }

    @Override // defpackage.IK0
    public void N0(int i) {
        SavedState savedState = this.H;
        if (!(savedState == null || savedState.F == i)) {
            savedState.I = null;
            savedState.H = 0;
            savedState.F = -1;
            savedState.G = -1;
        }
        this.B = i;
        this.C = Integer.MIN_VALUE;
        L0();
    }

    @Override // defpackage.IK0
    public int O0(int i, PK0 pk0, VK0 vk0) {
        return z1(i, pk0, vk0);
    }

    @Override // defpackage.IK0
    public void R0(Rect rect, int i, int i2) {
        int i3;
        int i4;
        int P = P() + O();
        int N2 = N() + Q();
        if (this.v == 1) {
            i4 = IK0.i(i2, rect.height() + N2, L());
            i3 = IK0.i(i, (this.w * this.r) + P, M());
        } else {
            i3 = IK0.i(i, rect.width() + P, M());
            i4 = IK0.i(i2, (this.w * this.r) + N2, L());
        }
        this.b.setMeasuredDimension(i3, i4);
    }

    @Override // defpackage.IK0
    public boolean W() {
        return this.E != 0;
    }

    @Override // defpackage.IK0
    public void X0(RecyclerView recyclerView, VK0 vk0, int i) {
        E80 e80 = new E80(recyclerView.getContext());
        e80.f7937a = i;
        Y0(e80);
    }

    @Override // defpackage.IK0
    public boolean Z0() {
        return this.H == null;
    }

    @Override // defpackage.UK0
    public PointF a(int i) {
        int a1 = a1(i);
        PointF pointF = new PointF();
        if (a1 == 0) {
            return null;
        }
        if (this.v == 0) {
            pointF.x = (float) a1;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = (float) a1;
        }
        return pointF;
    }

    public final int a1(int i) {
        if (z() == 0) {
            return this.z ? 1 : -1;
        }
        return (i < k1()) != this.z ? -1 : 1;
    }

    @Override // defpackage.IK0
    public void b0(int i) {
        super.b0(i);
        for (int i2 = 0; i2 < this.r; i2++) {
            TZ0 tz0 = this.s[i2];
            int i3 = tz0.b;
            if (i3 != Integer.MIN_VALUE) {
                tz0.b = i3 + i;
            }
            int i4 = tz0.c;
            if (i4 != Integer.MIN_VALUE) {
                tz0.c = i4 + i;
            }
        }
    }

    public boolean b1() {
        int i;
        if (!(z() == 0 || this.E == 0 || !this.i)) {
            if (this.z) {
                i = l1();
                k1();
            } else {
                i = k1();
                l1();
            }
            if (i == 0 && p1() != null) {
                this.D.a();
                this.h = true;
                L0();
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.IK0
    public void c0(int i) {
        super.c0(i);
        for (int i2 = 0; i2 < this.r; i2++) {
            TZ0 tz0 = this.s[i2];
            int i3 = tz0.b;
            if (i3 != Integer.MIN_VALUE) {
                tz0.b = i3 + i;
            }
            int i4 = tz0.c;
            if (i4 != Integer.MIN_VALUE) {
                tz0.c = i4 + i;
            }
        }
    }

    public final int c1(VK0 vk0) {
        if (z() == 0) {
            return 0;
        }
        return TP0.a(vk0, this.t, h1(!this.L), g1(!this.L), this, this.L);
    }

    @Override // defpackage.IK0
    public void d(String str) {
        RecyclerView recyclerView;
        if (this.H == null && (recyclerView = this.b) != null) {
            recyclerView.j(str);
        }
    }

    @Override // defpackage.IK0
    public void d0(AbstractC5750yK0 yk0, AbstractC5750yK0 yk02) {
        this.D.a();
        for (int i = 0; i < this.r; i++) {
            this.s[i].d();
        }
    }

    public final int d1(VK0 vk0) {
        if (z() == 0) {
            return 0;
        }
        return TP0.b(vk0, this.t, h1(!this.L), g1(!this.L), this, this.L, this.z);
    }

    public final int e1(VK0 vk0) {
        if (z() == 0) {
            return 0;
        }
        return TP0.c(vk0, this.t, h1(!this.L), g1(!this.L), this, this.L);
    }

    @Override // defpackage.IK0
    public boolean f() {
        return this.v == 0;
    }

    public final int f1(PK0 pk0, H70 h70, VK0 vk0) {
        int i;
        int i2;
        int i3;
        TZ0 tz0;
        boolean z2;
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z3;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z4 = false;
        this.A.set(0, this.r, true);
        if (this.x.i) {
            i = h70.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            if (h70.e == 1) {
                i11 = h70.g + h70.b;
            } else {
                i11 = h70.f - h70.b;
            }
            i = i11;
        }
        B1(h70.e, i);
        if (this.z) {
            i2 = this.t.g();
        } else {
            i2 = this.t.k();
        }
        Object[] objArr = null;
        while (true) {
            int i12 = h70.c;
            if (((i12 < 0 || i12 >= vk0.b()) ? z4 : true) && (this.x.i || !this.A.isEmpty())) {
                View view = pk0.j(h70.c, z4, Long.MAX_VALUE).G;
                h70.c += h70.d;
                PZ0 pz0 = (PZ0) view.getLayoutParams();
                int a2 = pz0.a();
                int[] iArr = this.D.f8839a;
                int i13 = (iArr == null || a2 >= iArr.length) ? -1 : iArr[a2];
                if (i13 == -1 ? true : z4) {
                    if (t1(h70.e)) {
                        i10 = this.r - 1;
                        i9 = -1;
                        i8 = -1;
                    } else {
                        i9 = this.r;
                        int i14 = z4 ? 1 : 0;
                        Object[] objArr2 = z4 ? 1 : 0;
                        Object[] objArr3 = z4 ? 1 : 0;
                        i10 = i14;
                        i8 = 1;
                    }
                    TZ0 tz02 = null;
                    if (h70.e == 1) {
                        int k = this.t.k();
                        int i15 = Integer.MAX_VALUE;
                        while (i10 != i9) {
                            TZ0 tz03 = this.s[i10];
                            int h = tz03.h(k);
                            if (h < i15) {
                                tz02 = tz03;
                                i15 = h;
                            }
                            i10 += i8;
                        }
                    } else {
                        int g = this.t.g();
                        int i16 = Integer.MIN_VALUE;
                        while (i10 != i9) {
                            TZ0 tz04 = this.s[i10];
                            int k2 = tz04.k(g);
                            if (k2 > i16) {
                                tz02 = tz04;
                                i16 = k2;
                            }
                            i10 += i8;
                        }
                    }
                    tz0 = tz02;
                    RZ0 rz0 = this.D;
                    rz0.b(a2);
                    rz0.f8839a[a2] = tz0.e;
                } else {
                    tz0 = this.s[i13];
                }
                pz0.e = tz0;
                if (h70.e == 1) {
                    z2 = false;
                    c(view, -1, false);
                } else {
                    z2 = false;
                    c(view, 0, false);
                }
                if (this.v == 1) {
                    int i17 = this.w;
                    int i18 = this.n;
                    int i19 = ((ViewGroup.MarginLayoutParams) pz0).width;
                    int i20 = z2 ? 1 : 0;
                    int i21 = z2 ? 1 : 0;
                    int i22 = z2 ? 1 : 0;
                    r1(view, IK0.A(i17, i18, i20, i19, z2), IK0.A(this.q, this.o, N() + Q(), ((ViewGroup.MarginLayoutParams) pz0).height, true), z2);
                } else {
                    r1(view, IK0.A(this.p, this.n, P() + O(), ((ViewGroup.MarginLayoutParams) pz0).width, true), IK0.A(this.w, this.o, 0, ((ViewGroup.MarginLayoutParams) pz0).height, false), false);
                }
                if (h70.e == 1) {
                    int h2 = tz0.h(i2);
                    i5 = h2;
                    i4 = this.t.c(view) + h2;
                } else {
                    int k3 = tz0.k(i2);
                    i4 = k3;
                    i5 = k3 - this.t.c(view);
                }
                if (h70.e == 1) {
                    pz0.e.a(view);
                } else {
                    pz0.e.n(view);
                }
                if (!q1() || this.v != 1) {
                    i6 = this.u.k() + (tz0.e * this.w);
                    i7 = this.u.c(view) + i6;
                } else {
                    i7 = this.u.g() - (((this.r - 1) - tz0.e) * this.w);
                    i6 = i7 - this.u.c(view);
                }
                if (this.v == 1) {
                    a0(view, i6, i5, i7, i4);
                } else {
                    a0(view, i5, i6, i4, i7);
                }
                D1(tz0, this.x.e, i);
                v1(pk0, this.x);
                if (!this.x.h || !view.hasFocusable()) {
                    z3 = false;
                } else {
                    BitSet bitSet = this.A;
                    int i23 = tz0.e;
                    z3 = false;
                    bitSet.set(i23, false);
                }
                z4 = z3;
                objArr = 1;
            }
        }
        if (objArr == null) {
            v1(pk0, this.x);
        }
        if (this.x.e == -1) {
            i3 = this.t.k() - n1(this.t.k());
        } else {
            i3 = m1(this.t.g()) - this.t.g();
        }
        if (i3 > 0) {
            return Math.min(h70.b, i3);
        }
        int i24 = z4 ? 1 : 0;
        int i25 = z4 ? 1 : 0;
        int i26 = z4 ? 1 : 0;
        return i24;
    }

    @Override // defpackage.IK0
    public boolean g() {
        return this.v == 1;
    }

    public View g1(boolean z2) {
        int k = this.t.k();
        int g = this.t.g();
        View view = null;
        for (int z3 = z() - 1; z3 >= 0; z3--) {
            View y2 = y(z3);
            int e = this.t.e(y2);
            int b = this.t.b(y2);
            if (b > k && e < g) {
                if (b <= g || !z2) {
                    return y2;
                }
                if (view == null) {
                    view = y2;
                }
            }
        }
        return view;
    }

    @Override // defpackage.IK0
    public boolean h(JK0 jk0) {
        return jk0 instanceof PZ0;
    }

    @Override // defpackage.IK0
    public void h0(RecyclerView recyclerView, PK0 pk0) {
        g0();
        Runnable runnable = this.N;
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(runnable);
        }
        for (int i = 0; i < this.r; i++) {
            this.s[i].d();
        }
        recyclerView.requestLayout();
    }

    public View h1(boolean z2) {
        int k = this.t.k();
        int g = this.t.g();
        int z3 = z();
        View view = null;
        for (int i = 0; i < z3; i++) {
            View y2 = y(i);
            int e = this.t.e(y2);
            if (this.t.b(y2) > k && e < g) {
                if (e >= k || !z2) {
                    return y2;
                }
                if (view == null) {
                    view = y2;
                }
            }
        }
        return view;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0038, code lost:
        if (r8.v == 1) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003d, code lost:
        if (r8.v == 0) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004b, code lost:
        if (q1() == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0057, code lost:
        if (q1() == false) goto L_0x005b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005f  */
    @Override // defpackage.IK0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View i0(android.view.View r9, int r10, defpackage.PK0 r11, defpackage.VK0 r12) {
        /*
        // Method dump skipped, instructions count: 331
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.i0(android.view.View, int, PK0, VK0):android.view.View");
    }

    public final void i1(PK0 pk0, VK0 vk0, boolean z2) {
        int g;
        int m1 = m1(Integer.MIN_VALUE);
        if (m1 != Integer.MIN_VALUE && (g = this.t.g() - m1) > 0) {
            int i = g - (-z1(-g, pk0, vk0));
            if (z2 && i > 0) {
                this.t.p(i);
            }
        }
    }

    @Override // defpackage.IK0
    public void j(int i, int i2, VK0 vk0, PU pu) {
        int i3;
        int i4;
        if (this.v != 0) {
            i = i2;
        }
        if (!(z() == 0 || i == 0)) {
            u1(i, vk0);
            int[] iArr = this.M;
            if (iArr == null || iArr.length < this.r) {
                this.M = new int[this.r];
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.r; i6++) {
                H70 h70 = this.x;
                if (h70.d == -1) {
                    i4 = h70.f;
                    i3 = this.s[i6].k(i4);
                } else {
                    i4 = this.s[i6].h(h70.g);
                    i3 = this.x.g;
                }
                int i7 = i4 - i3;
                if (i7 >= 0) {
                    this.M[i5] = i7;
                    i5++;
                }
            }
            Arrays.sort(this.M, 0, i5);
            for (int i8 = 0; i8 < i5; i8++) {
                int i9 = this.x.c;
                if (i9 >= 0 && i9 < vk0.b()) {
                    pu.a(this.x.c, this.M[i8]);
                    H70 h702 = this.x;
                    h702.c += h702.d;
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.IK0
    public void j0(AccessibilityEvent accessibilityEvent) {
        PK0 pk0 = this.b.f9482J;
        k0(accessibilityEvent);
        if (z() > 0) {
            View h1 = h1(false);
            View g1 = g1(false);
            if (h1 != null && g1 != null) {
                int R = R(h1);
                int R2 = R(g1);
                if (R < R2) {
                    accessibilityEvent.setFromIndex(R);
                    accessibilityEvent.setToIndex(R2);
                    return;
                }
                accessibilityEvent.setFromIndex(R2);
                accessibilityEvent.setToIndex(R);
            }
        }
    }

    public final void j1(PK0 pk0, VK0 vk0, boolean z2) {
        int k;
        int n1 = n1(Integer.MAX_VALUE);
        if (n1 != Integer.MAX_VALUE && (k = n1 - this.t.k()) > 0) {
            int z1 = k - z1(k, pk0, vk0);
            if (z2 && z1 > 0) {
                this.t.p(-z1);
            }
        }
    }

    public int k1() {
        if (z() == 0) {
            return 0;
        }
        return R(y(0));
    }

    @Override // defpackage.IK0
    public int l(VK0 vk0) {
        return c1(vk0);
    }

    public int l1() {
        int z2 = z();
        if (z2 == 0) {
            return 0;
        }
        return R(y(z2 - 1));
    }

    @Override // defpackage.IK0
    public int m(VK0 vk0) {
        return d1(vk0);
    }

    public final int m1(int i) {
        int h = this.s[0].h(i);
        for (int i2 = 1; i2 < this.r; i2++) {
            int h2 = this.s[i2].h(i);
            if (h2 > h) {
                h = h2;
            }
        }
        return h;
    }

    @Override // defpackage.IK0
    public int n(VK0 vk0) {
        return e1(vk0);
    }

    public final int n1(int i) {
        int k = this.s[0].k(i);
        for (int i2 = 1; i2 < this.r; i2++) {
            int k2 = this.s[i2].k(i);
            if (k2 < k) {
                k = k2;
            }
        }
        return k;
    }

    @Override // defpackage.IK0
    public int o(VK0 vk0) {
        return c1(vk0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void o1(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.z
            if (r0 == 0) goto L_0x0009
            int r0 = r6.l1()
            goto L_0x000d
        L_0x0009:
            int r0 = r6.k1()
        L_0x000d:
            r1 = 8
            if (r9 != r1) goto L_0x001a
            if (r7 >= r8) goto L_0x0016
            int r2 = r8 + 1
            goto L_0x001c
        L_0x0016:
            int r2 = r7 + 1
            r3 = r8
            goto L_0x001d
        L_0x001a:
            int r2 = r7 + r8
        L_0x001c:
            r3 = r7
        L_0x001d:
            RZ0 r4 = r6.D
            r4.d(r3)
            r4 = 1
            if (r9 == r4) goto L_0x003c
            r5 = 2
            if (r9 == r5) goto L_0x0036
            if (r9 == r1) goto L_0x002b
            goto L_0x0041
        L_0x002b:
            RZ0 r9 = r6.D
            r9.f(r7, r4)
            RZ0 r7 = r6.D
            r7.e(r8, r4)
            goto L_0x0041
        L_0x0036:
            RZ0 r9 = r6.D
            r9.f(r7, r8)
            goto L_0x0041
        L_0x003c:
            RZ0 r9 = r6.D
            r9.e(r7, r8)
        L_0x0041:
            if (r2 > r0) goto L_0x0044
            return
        L_0x0044:
            boolean r7 = r6.z
            if (r7 == 0) goto L_0x004d
            int r7 = r6.k1()
            goto L_0x0051
        L_0x004d:
            int r7 = r6.l1()
        L_0x0051:
            if (r3 > r7) goto L_0x0056
            r6.L0()
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.o1(int, int, int):void");
    }

    @Override // defpackage.IK0
    public int p(VK0 vk0) {
        return d1(vk0);
    }

    @Override // defpackage.IK0
    public void p0(RecyclerView recyclerView, int i, int i2) {
        o1(i, i2, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bc, code lost:
        if (r10 == r11) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ce, code lost:
        if (r10 == r11) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d2, code lost:
        r10 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0099 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View p1() {
        /*
        // Method dump skipped, instructions count: 246
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.p1():android.view.View");
    }

    @Override // defpackage.IK0
    public int q(VK0 vk0) {
        return e1(vk0);
    }

    @Override // defpackage.IK0
    public void q0(RecyclerView recyclerView) {
        this.D.a();
        L0();
    }

    public boolean q1() {
        return K() == 1;
    }

    @Override // defpackage.IK0
    public void r0(RecyclerView recyclerView, int i, int i2, int i3) {
        o1(i, i2, 8);
    }

    public final void r1(View view, int i, int i2, boolean z2) {
        boolean z3;
        e(view, this.f9483J);
        PZ0 pz0 = (PZ0) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) pz0).leftMargin;
        Rect rect = this.f9483J;
        int E1 = E1(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) pz0).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) pz0).topMargin;
        Rect rect2 = this.f9483J;
        int E12 = E1(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) pz0).bottomMargin + rect2.bottom);
        if (z2) {
            z3 = W0(view, E1, E12, pz0);
        } else {
            z3 = U0(view, E1, E12, pz0);
        }
        if (z3) {
            view.measure(E1, E12);
        }
    }

    @Override // defpackage.IK0
    public void s0(RecyclerView recyclerView, int i, int i2) {
        o1(i, i2, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0412, code lost:
        if (b1() != false) goto L_0x0416;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void s1(defpackage.PK0 r12, defpackage.VK0 r13, boolean r14) {
        /*
        // Method dump skipped, instructions count: 1076
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.s1(PK0, VK0, boolean):void");
    }

    public final boolean t1(int i) {
        if (this.v == 0) {
            return (i == -1) != this.z;
        }
        return ((i == -1) == this.z) == q1();
    }

    @Override // defpackage.IK0
    public void u0(RecyclerView recyclerView, int i, int i2, Object obj) {
        o1(i, i2, 4);
    }

    public void u1(int i, VK0 vk0) {
        int i2;
        int i3;
        if (i > 0) {
            i3 = l1();
            i2 = 1;
        } else {
            i2 = -1;
            i3 = k1();
        }
        this.x.f8137a = true;
        C1(i3, vk0);
        A1(i2);
        H70 h70 = this.x;
        h70.c = i3 + h70.d;
        h70.b = Math.abs(i);
    }

    @Override // defpackage.IK0
    public JK0 v() {
        if (this.v == 0) {
            return new PZ0(-2, -1);
        }
        return new PZ0(-1, -2);
    }

    @Override // defpackage.IK0
    public void v0(PK0 pk0, VK0 vk0) {
        s1(pk0, vk0, true);
    }

    public final void v1(PK0 pk0, H70 h70) {
        int i;
        int i2;
        if (h70.f8137a && !h70.i) {
            if (h70.b != 0) {
                int i3 = 1;
                if (h70.e == -1) {
                    int i4 = h70.f;
                    int k = this.s[0].k(i4);
                    while (i3 < this.r) {
                        int k2 = this.s[i3].k(i4);
                        if (k2 > k) {
                            k = k2;
                        }
                        i3++;
                    }
                    int i5 = i4 - k;
                    if (i5 < 0) {
                        i2 = h70.g;
                    } else {
                        i2 = h70.g - Math.min(i5, h70.b);
                    }
                    w1(pk0, i2);
                    return;
                }
                int i6 = h70.g;
                int h = this.s[0].h(i6);
                while (i3 < this.r) {
                    int h2 = this.s[i3].h(i6);
                    if (h2 < h) {
                        h = h2;
                    }
                    i3++;
                }
                int i7 = h - h70.g;
                if (i7 < 0) {
                    i = h70.f;
                } else {
                    i = Math.min(i7, h70.b) + h70.f;
                }
                x1(pk0, i);
            } else if (h70.e == -1) {
                w1(pk0, h70.g);
            } else {
                x1(pk0, h70.f);
            }
        }
    }

    @Override // defpackage.IK0
    public JK0 w(Context context, AttributeSet attributeSet) {
        return new PZ0(context, attributeSet);
    }

    @Override // defpackage.IK0
    public void w0(VK0 vk0) {
        this.B = -1;
        this.C = Integer.MIN_VALUE;
        this.H = null;
        this.K.b();
    }

    public final void w1(PK0 pk0, int i) {
        for (int z2 = z() - 1; z2 >= 0; z2--) {
            View y2 = y(z2);
            if (this.t.e(y2) >= i && this.t.o(y2) >= i) {
                PZ0 pz0 = (PZ0) y2.getLayoutParams();
                Objects.requireNonNull(pz0);
                if (pz0.e.f8965a.size() != 1) {
                    pz0.e.l();
                    I0(y2);
                    pk0.g(y2);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Override // defpackage.IK0
    public JK0 x(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new PZ0((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new PZ0(layoutParams);
    }

    public final void x1(PK0 pk0, int i) {
        while (z() > 0) {
            View y2 = y(0);
            if (this.t.b(y2) <= i && this.t.n(y2) <= i) {
                PZ0 pz0 = (PZ0) y2.getLayoutParams();
                Objects.requireNonNull(pz0);
                if (pz0.e.f8965a.size() != 1) {
                    pz0.e.m();
                    I0(y2);
                    pk0.g(y2);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final void y1() {
        if (this.v == 1 || !q1()) {
            this.z = this.y;
        } else {
            this.z = !this.y;
        }
    }

    public int z1(int i, PK0 pk0, VK0 vk0) {
        if (z() == 0 || i == 0) {
            return 0;
        }
        u1(i, vk0);
        int f1 = f1(pk0, this.x, vk0);
        if (this.x.b >= f1) {
            i = i < 0 ? -f1 : f1;
        }
        this.t.p(-i);
        this.F = this.z;
        H70 h70 = this.x;
        h70.b = 0;
        v1(pk0, h70);
        return i;
    }
}
