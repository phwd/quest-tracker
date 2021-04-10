package defpackage;

import android.util.Pair;
import android.util.Printer;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* renamed from: FW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FW {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8019a;
    public int b = Integer.MIN_VALUE;
    public int c = Integer.MIN_VALUE;
    public KW d;
    public boolean e = false;
    public KW f;
    public boolean g = false;
    public KW h;
    public boolean i = false;
    public int[] j;
    public boolean k = false;
    public int[] l;
    public boolean m = false;
    public CW[] n;
    public boolean o = false;
    public int[] p;
    public boolean q = false;
    public boolean r;
    public boolean s = false;
    public int[] t;
    public boolean u = true;
    public JW v = new JW(0);
    public JW w = new JW(-100000);
    public final /* synthetic */ GridLayout x;

    public FW(GridLayout gridLayout, boolean z) {
        this.x = gridLayout;
        this.f8019a = z;
    }

    public final void a(List list, KW kw) {
        int i2 = 0;
        while (true) {
            HW[] hwArr = (HW[]) kw.b;
            if (i2 < hwArr.length) {
                o(list, hwArr[i2], ((JW[]) kw.c)[i2], false);
                i2++;
            } else {
                return;
            }
        }
    }

    public final String b(List list) {
        String str = this.f8019a ? "x" : "y";
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CW cw = (CW) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            HW hw = cw.f7816a;
            int i2 = hw.f8160a;
            int i3 = hw.b;
            int i4 = cw.b.f8292a;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            if (i2 < i3) {
                sb2.append(i3);
                sb2.append("-");
                sb2.append(str);
                sb2.append(i2);
                sb2.append(">=");
            } else {
                sb2.append(i2);
                sb2.append("-");
                sb2.append(str);
                sb2.append(i3);
                sb2.append("<=");
                i4 = -i4;
            }
            sb2.append(i4);
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    public final void c(KW kw, boolean z) {
        JW[] jwArr;
        for (JW jw : (JW[]) kw.c) {
            jw.f8292a = Integer.MIN_VALUE;
        }
        GW[] gwArr = (GW[]) j().c;
        for (int i2 = 0; i2 < gwArr.length; i2++) {
            int d2 = gwArr[i2].d(z);
            JW jw2 = (JW) kw.b(i2);
            int i3 = jw2.f8292a;
            if (!z) {
                d2 = -d2;
            }
            jw2.f8292a = Math.max(i3, d2);
        }
    }

    public final void d(boolean z) {
        int[] iArr = z ? this.j : this.l;
        int childCount = this.x.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.x.getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                IW g2 = this.x.g(childAt);
                boolean z2 = this.f8019a;
                HW hw = (z2 ? g2.p : g2.o).c;
                int i3 = z ? hw.f8160a : hw.b;
                iArr[i3] = Math.max(iArr[i3], this.x.i(childAt, z2, z));
            }
        }
    }

    public final KW e(boolean z) {
        HW hw;
        DW dw = new DW(HW.class, JW.class);
        LW[] lwArr = (LW[]) j().b;
        int length = lwArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (z) {
                hw = lwArr[i2].c;
            } else {
                HW hw2 = lwArr[i2].c;
                hw = new HW(hw2.b, hw2.f8160a);
            }
            dw.add(Pair.create(hw, new JW()));
        }
        return dw.a();
    }

    public CW[] f() {
        if (this.n == null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            a(arrayList, i());
            a(arrayList2, g());
            if (this.u) {
                int i2 = 0;
                while (i2 < h()) {
                    int i3 = i2 + 1;
                    o(arrayList, new HW(i2, i3), new JW(0), true);
                    i2 = i3;
                }
            }
            int h2 = h();
            o(arrayList, new HW(0, h2), this.v, false);
            o(arrayList2, new HW(h2, 0), this.w, false);
            CW[] v2 = v(arrayList);
            CW[] v3 = v(arrayList2);
            Printer printer = GridLayout.F;
            Object[] objArr = (Object[]) Array.newInstance(v2.getClass().getComponentType(), v2.length + v3.length);
            System.arraycopy(v2, 0, objArr, 0, v2.length);
            System.arraycopy(v3, 0, objArr, v2.length, v3.length);
            this.n = (CW[]) objArr;
        }
        if (!this.o) {
            i();
            g();
            this.o = true;
        }
        return this.n;
    }

    public final KW g() {
        if (this.h == null) {
            this.h = e(false);
        }
        if (!this.i) {
            c(this.h, false);
            this.i = true;
        }
        return this.h;
    }

    public int h() {
        return Math.max(this.b, l());
    }

    public final KW i() {
        if (this.f == null) {
            this.f = e(true);
        }
        if (!this.g) {
            c(this.f, true);
            this.g = true;
        }
        return this.f;
    }

    public KW j() {
        GW[] gwArr;
        int i2;
        int i3;
        if (this.d == null) {
            DW dw = new DW(LW.class, GW.class);
            int childCount = this.x.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                IW g2 = this.x.g(this.x.getChildAt(i4));
                boolean z = this.f8019a;
                LW lw = z ? g2.p : g2.o;
                dw.add(Pair.create(lw, lw.a(z).b()));
            }
            this.d = dw.a();
        }
        if (!this.e) {
            for (GW gw : (GW[]) this.d.c) {
                gw.c();
            }
            int childCount2 = this.x.getChildCount();
            for (int i5 = 0; i5 < childCount2; i5++) {
                View childAt = this.x.getChildAt(i5);
                IW g3 = this.x.g(childAt);
                boolean z2 = this.f8019a;
                LW lw2 = z2 ? g3.p : g3.o;
                GridLayout gridLayout = this.x;
                Objects.requireNonNull(gridLayout);
                if (childAt.getVisibility() == 8) {
                    i2 = 0;
                } else {
                    i2 = gridLayout.k(childAt, z2) + (z2 ? childAt.getMeasuredWidth() : childAt.getMeasuredHeight());
                }
                if (lw2.e == 0.0f) {
                    i3 = 0;
                } else {
                    if (this.t == null) {
                        this.t = new int[this.x.getChildCount()];
                    }
                    i3 = this.t[i5];
                }
                int i6 = i2 + i3;
                GW gw2 = (GW) this.d.b(i5);
                GridLayout gridLayout2 = this.x;
                gw2.c = ((lw2.d == GridLayout.M && lw2.e == 0.0f) ? 0 : 2) & gw2.c;
                int a2 = lw2.a(this.f8019a).a(childAt, i6, gridLayout2.getLayoutMode());
                gw2.b(a2, i6 - a2);
            }
            this.e = true;
        }
        return this.d;
    }

    public int[] k() {
        boolean z;
        if (this.p == null) {
            this.p = new int[(h() + 1)];
        }
        if (!this.q) {
            int[] iArr = this.p;
            float f2 = 0.0f;
            if (!this.s) {
                int childCount = this.x.getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        z = false;
                        break;
                    }
                    View childAt = this.x.getChildAt(i2);
                    if (childAt.getVisibility() != 8) {
                        IW g2 = this.x.g(childAt);
                        if ((this.f8019a ? g2.p : g2.o).e != 0.0f) {
                            z = true;
                            break;
                        }
                    }
                    i2++;
                }
                this.r = z;
                this.s = true;
            }
            if (!this.r) {
                u(f(), iArr, true);
            } else {
                if (this.t == null) {
                    this.t = new int[this.x.getChildCount()];
                }
                Arrays.fill(this.t, 0);
                u(f(), iArr, true);
                int childCount2 = (this.x.getChildCount() * this.v.f8292a) + 1;
                if (childCount2 >= 2) {
                    int childCount3 = this.x.getChildCount();
                    for (int i3 = 0; i3 < childCount3; i3++) {
                        View childAt2 = this.x.getChildAt(i3);
                        if (childAt2.getVisibility() != 8) {
                            IW g3 = this.x.g(childAt2);
                            f2 += (this.f8019a ? g3.p : g3.o).e;
                        }
                    }
                    int i4 = -1;
                    boolean z2 = true;
                    int i5 = 0;
                    while (i5 < childCount2) {
                        int i6 = (int) ((((long) i5) + ((long) childCount2)) / 2);
                        q();
                        t(i6, f2);
                        boolean u2 = u(f(), iArr, false);
                        if (u2) {
                            i5 = i6 + 1;
                            i4 = i6;
                        } else {
                            childCount2 = i6;
                        }
                        z2 = u2;
                    }
                    if (i4 > 0 && !z2) {
                        q();
                        t(i4, f2);
                        u(f(), iArr, true);
                    }
                }
            }
            if (!this.u) {
                int i7 = iArr[0];
                int length = iArr.length;
                for (int i8 = 0; i8 < length; i8++) {
                    iArr[i8] = iArr[i8] - i7;
                }
            }
            this.q = true;
        }
        return this.p;
    }

    public final int l() {
        int i2 = Integer.MIN_VALUE;
        if (this.c == Integer.MIN_VALUE) {
            int childCount = this.x.getChildCount();
            int i3 = -1;
            for (int i4 = 0; i4 < childCount; i4++) {
                IW g2 = this.x.g(this.x.getChildAt(i4));
                HW hw = (this.f8019a ? g2.p : g2.o).c;
                i3 = Math.max(Math.max(Math.max(i3, hw.f8160a), hw.b), hw.a());
            }
            if (i3 != -1) {
                i2 = i3;
            }
            this.c = Math.max(0, i2);
        }
        return this.c;
    }

    public int m(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            return n(0, size);
        }
        if (mode == 0) {
            return n(0, 100000);
        }
        if (mode != 1073741824) {
            return 0;
        }
        return n(size, size);
    }

    public final int n(int i2, int i3) {
        this.v.f8292a = i2;
        this.w.f8292a = -i3;
        this.q = false;
        return k()[h()];
    }

    public final void o(List list, HW hw, JW jw, boolean z) {
        if (hw.a() != 0) {
            if (z) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((CW) it.next()).f7816a.equals(hw)) {
                        return;
                    }
                }
            }
            list.add(new CW(hw, jw));
        }
    }

    public void p() {
        this.c = Integer.MIN_VALUE;
        this.d = null;
        this.f = null;
        this.h = null;
        this.j = null;
        this.l = null;
        this.n = null;
        this.p = null;
        this.t = null;
        this.s = false;
        q();
    }

    public void q() {
        this.e = false;
        this.g = false;
        this.i = false;
        this.k = false;
        this.m = false;
        this.o = false;
        this.q = false;
    }

    public final boolean r(int[] iArr, CW cw) {
        if (!cw.c) {
            return false;
        }
        HW hw = cw.f7816a;
        int i2 = hw.f8160a;
        int i3 = hw.b;
        int i4 = iArr[i2] + cw.b.f8292a;
        if (i4 <= iArr[i3]) {
            return false;
        }
        iArr[i3] = i4;
        return true;
    }

    public void s(int i2) {
        if (i2 == Integer.MIN_VALUE || i2 >= l()) {
            this.b = i2;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8019a ? "column" : "row");
        sb.append("Count must be greater than or equal to the maximum of all grid indices ");
        sb.append("(and spans) defined in the LayoutParams of each child");
        GridLayout.l(sb.toString());
        throw null;
    }

    public final void t(int i2, float f2) {
        Arrays.fill(this.t, 0);
        int childCount = this.x.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.x.getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                IW g2 = this.x.g(childAt);
                float f3 = (this.f8019a ? g2.p : g2.o).e;
                if (f3 != 0.0f) {
                    int round = Math.round((((float) i2) * f3) / f2);
                    this.t[i3] = round;
                    i2 -= round;
                    f2 -= f3;
                }
            }
        }
    }

    public final boolean u(CW[] cwArr, int[] iArr, boolean z) {
        String str = this.f8019a ? "horizontal" : "vertical";
        int h2 = h() + 1;
        boolean[] zArr = null;
        for (int i2 = 0; i2 < cwArr.length; i2++) {
            Arrays.fill(iArr, 0);
            for (int i3 = 0; i3 < h2; i3++) {
                boolean z2 = false;
                for (CW cw : cwArr) {
                    z2 |= r(iArr, cw);
                }
                if (!z2) {
                    if (zArr != null) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (int i4 = 0; i4 < cwArr.length; i4++) {
                            CW cw2 = cwArr[i4];
                            if (zArr[i4]) {
                                arrayList.add(cw2);
                            }
                            if (!cw2.c) {
                                arrayList2.add(cw2);
                            }
                        }
                        Printer printer = this.x.i0;
                        StringBuilder j2 = AbstractC2531fV.j(str, " constraints: ");
                        j2.append(b(arrayList));
                        j2.append(" are inconsistent; permanently removing: ");
                        j2.append(b(arrayList2));
                        j2.append(". ");
                        printer.println(j2.toString());
                    }
                    return true;
                }
            }
            if (!z) {
                return false;
            }
            boolean[] zArr2 = new boolean[cwArr.length];
            for (int i5 = 0; i5 < h2; i5++) {
                int length = cwArr.length;
                for (int i6 = 0; i6 < length; i6++) {
                    zArr2[i6] = zArr2[i6] | r(iArr, cwArr[i6]);
                }
            }
            if (i2 == 0) {
                zArr = zArr2;
            }
            int i7 = 0;
            while (true) {
                if (i7 >= cwArr.length) {
                    break;
                }
                if (zArr2[i7]) {
                    CW cw3 = cwArr[i7];
                    HW hw = cw3.f7816a;
                    if (hw.f8160a >= hw.b) {
                        cw3.c = false;
                        break;
                    }
                }
                i7++;
            }
        }
        return true;
    }

    public final CW[] v(List list) {
        EW ew = new EW(this, (CW[]) list.toArray(new CW[list.size()]));
        int length = ew.c.length;
        for (int i2 = 0; i2 < length; i2++) {
            ew.a(i2);
        }
        return ew.f7965a;
    }
}
