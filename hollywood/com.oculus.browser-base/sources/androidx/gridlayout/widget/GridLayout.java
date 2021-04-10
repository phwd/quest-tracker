package androidx.gridlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.LogPrinter;
import android.util.Printer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GridLayout extends ViewGroup {
    public static final Printer F = new LogPrinter(3, GridLayout.class.getName());
    public static final int G = 3;
    public static final int H = 4;
    public static final int I = 1;

    /* renamed from: J  reason: collision with root package name */
    public static final int f9473J = 6;
    public static final int K = 5;
    public static final int L = 2;
    public static final BW M = new C4923tW();
    public static final BW N;
    public static final BW O;
    public static final BW P;
    public static final BW Q;
    public static final BW R;
    public static final BW S;
    public static final BW T;
    public static final BW U;
    public static final BW V = new C5603xW();
    public static final BW W = new C5943zW();
    public static final BW a0 = new AW();
    public final FW b0;
    public final FW c0;
    public int d0 = 0;
    public boolean e0 = false;
    public int f0 = 1;
    public int g0;
    public int h0 = 0;
    public Printer i0 = F;

    static {
        C5093uW uWVar = new C5093uW();
        N = uWVar;
        C5263vW vWVar = new C5263vW();
        O = vWVar;
        P = uWVar;
        Q = vWVar;
        R = uWVar;
        S = vWVar;
        T = new C5433wW(uWVar, vWVar);
        U = new C5433wW(vWVar, uWVar);
    }

    public GridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        FW fw = new FW(this, true);
        this.b0 = fw;
        FW fw2 = new FW(this, false);
        this.c0 = fw2;
        this.g0 = context.getResources().getDimensionPixelOffset(R.dimen.f18070_resource_name_obfuscated_RES_2131165426);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.U);
        try {
            fw2.s(obtainStyledAttributes.getInt(H, Integer.MIN_VALUE));
            m();
            requestLayout();
            q(obtainStyledAttributes.getInt(I, Integer.MIN_VALUE));
            int i = obtainStyledAttributes.getInt(G, 0);
            if (this.d0 != i) {
                this.d0 = i;
                m();
                requestLayout();
            }
            this.e0 = obtainStyledAttributes.getBoolean(f9473J, false);
            requestLayout();
            this.f0 = obtainStyledAttributes.getInt(0, 1);
            requestLayout();
            fw2.u = obtainStyledAttributes.getBoolean(K, true);
            fw2.p();
            m();
            requestLayout();
            fw.u = obtainStyledAttributes.getBoolean(L, true);
            fw.p();
            m();
            requestLayout();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static BW d(int i, boolean z) {
        int i2 = (i & (z ? 7 : 112)) >> (z ? 0 : 4);
        if (i2 == 1) {
            return V;
        }
        if (i2 == 3) {
            return z ? T : P;
        }
        if (i2 == 5) {
            return z ? U : Q;
        }
        if (i2 == 7) {
            return a0;
        }
        if (i2 == 8388611) {
            return R;
        }
        if (i2 != 8388613) {
            return M;
        }
        return S;
    }

    public static void l(String str) {
        throw new IllegalArgumentException(AbstractC2531fV.f(str, ". "));
    }

    public static void p(IW iw, int i, int i2, int i3, int i4) {
        HW hw = new HW(i, i2 + i);
        LW lw = iw.o;
        iw.o = new LW(lw.b, hw, lw.d, lw.e);
        HW hw2 = new HW(i3, i4 + i3);
        LW lw2 = iw.p;
        iw.p = new LW(lw2.b, hw2, lw2.d, lw2.e);
    }

    public static LW r(int i, int i2, BW bw) {
        return s(i, i2, bw, 0.0f);
    }

    public static LW s(int i, int i2, BW bw, float f) {
        return new LW(i != Integer.MIN_VALUE, i, i2, bw, f);
    }

    public final void a(IW iw, boolean z) {
        String str = z ? "column" : "row";
        HW hw = (z ? iw.p : iw.o).c;
        int i = hw.f8160a;
        if (i == Integer.MIN_VALUE || i >= 0) {
            int i2 = (z ? this.b0 : this.c0).b;
            if (i2 == Integer.MIN_VALUE) {
                return;
            }
            if (hw.b > i2) {
                l(str + " indices (start + span) mustn't exceed the " + str + " count");
                throw null;
            } else if (hw.a() > i2) {
                l(str + " span mustn't exceed the " + str + " count");
                throw null;
            }
        } else {
            l(str + " indices must be positive");
            throw null;
        }
    }

    public final int b() {
        int childCount = getChildCount();
        int i = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                i = ((IW) childAt.getLayoutParams()).hashCode() + (i * 31);
            }
        }
        return i;
    }

    public final void c() {
        boolean z;
        int i = this.h0;
        if (i == 0) {
            boolean z2 = this.d0 == 0;
            int i2 = (z2 ? this.b0 : this.c0).b;
            if (i2 == Integer.MIN_VALUE) {
                i2 = 0;
            }
            int[] iArr = new int[i2];
            int childCount = getChildCount();
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < childCount; i5++) {
                IW iw = (IW) getChildAt(i5).getLayoutParams();
                LW lw = z2 ? iw.o : iw.p;
                HW hw = lw.c;
                boolean z3 = lw.b;
                int a2 = hw.a();
                if (z3) {
                    i3 = hw.f8160a;
                }
                LW lw2 = z2 ? iw.p : iw.o;
                HW hw2 = lw2.c;
                boolean z4 = lw2.b;
                int a3 = hw2.a();
                if (i2 != 0) {
                    a3 = Math.min(a3, i2 - (z4 ? Math.min(hw2.f8160a, i2) : 0));
                }
                if (z4) {
                    i4 = hw2.f8160a;
                }
                if (i2 != 0) {
                    if (!z3 || !z4) {
                        while (true) {
                            int i6 = i4 + a3;
                            if (i6 <= i2) {
                                int i7 = i4;
                                while (true) {
                                    if (i7 >= i6) {
                                        z = true;
                                        break;
                                    } else if (iArr[i7] > i3) {
                                        break;
                                    } else {
                                        i7++;
                                    }
                                }
                            }
                            z = false;
                            if (z) {
                                break;
                            } else if (z4) {
                                i3++;
                            } else if (i6 <= i2) {
                                i4++;
                            } else {
                                i3++;
                                i4 = 0;
                            }
                        }
                    }
                    Arrays.fill(iArr, Math.min(i4, i2), Math.min(i4 + a3, i2), i3 + a2);
                }
                if (z2) {
                    p(iw, i3, a2, i4, a3);
                } else {
                    p(iw, i4, a3, i3, a2);
                }
                i4 += a3;
            }
            this.h0 = b();
        } else if (i != b()) {
            this.i0.println("The fields of some layout parameters were modified in between layout operations. Check the javadoc for GridLayout.LayoutParams#rowSpec.");
            m();
            c();
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof IW)) {
            return false;
        }
        IW iw = (IW) layoutParams;
        a(iw, true);
        a(iw, false);
        return true;
    }

    public final int e(View view) {
        if (view.getClass() == DY0.class || view.getClass() == Space.class) {
            return 0;
        }
        return this.g0 / 2;
    }

    public final int f(View view, boolean z, boolean z2) {
        return e(view);
    }

    public final IW g(View view) {
        return (IW) view.getLayoutParams();
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LW lw = LW.f8420a;
        return new IW(lw, lw);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new IW(getContext(), attributeSet);
    }

    public final int h(View view, boolean z, boolean z2) {
        int[] iArr;
        if (this.f0 == 1) {
            return i(view, z, z2);
        }
        FW fw = z ? this.b0 : this.c0;
        if (z2) {
            if (fw.j == null) {
                fw.j = new int[(fw.h() + 1)];
            }
            if (!fw.k) {
                fw.d(true);
                fw.k = true;
            }
            iArr = fw.j;
        } else {
            if (fw.l == null) {
                fw.l = new int[(fw.h() + 1)];
            }
            if (!fw.m) {
                fw.d(false);
                fw.m = true;
            }
            iArr = fw.l;
        }
        IW g = g(view);
        HW hw = (z ? g.p : g.o).c;
        return iArr[z2 ? hw.f8160a : hw.b];
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int i(android.view.View r6, boolean r7, boolean r8) {
        /*
            r5 = this;
            IW r0 = r5.g(r6)
            if (r7 == 0) goto L_0x000e
            if (r8 == 0) goto L_0x000b
            int r1 = r0.leftMargin
            goto L_0x0015
        L_0x000b:
            int r1 = r0.rightMargin
            goto L_0x0015
        L_0x000e:
            if (r8 == 0) goto L_0x0013
            int r1 = r0.topMargin
            goto L_0x0015
        L_0x0013:
            int r1 = r0.bottomMargin
        L_0x0015:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != r2) goto L_0x0054
            boolean r1 = r5.e0
            r2 = 0
            if (r1 != 0) goto L_0x0020
            r1 = r2
            goto L_0x0054
        L_0x0020:
            if (r7 == 0) goto L_0x0025
            LW r0 = r0.p
            goto L_0x0027
        L_0x0025:
            LW r0 = r0.o
        L_0x0027:
            if (r7 == 0) goto L_0x002c
            FW r1 = r5.b0
            goto L_0x002e
        L_0x002c:
            FW r1 = r5.c0
        L_0x002e:
            HW r0 = r0.c
            r3 = 1
            if (r7 == 0) goto L_0x0044
            java.util.concurrent.atomic.AtomicInteger r4 = defpackage.AbstractC1920bu1.f9571a
            int r4 = r5.getLayoutDirection()
            if (r4 != r3) goto L_0x003d
            r4 = r3
            goto L_0x003e
        L_0x003d:
            r4 = r2
        L_0x003e:
            if (r4 == 0) goto L_0x0044
            if (r8 != 0) goto L_0x0045
            r2 = r3
            goto L_0x0045
        L_0x0044:
            r2 = r8
        L_0x0045:
            if (r2 == 0) goto L_0x004a
            int r0 = r0.f8160a
            goto L_0x004f
        L_0x004a:
            int r0 = r0.b
            r1.h()
        L_0x004f:
            int r6 = r5.f(r6, r7, r8)
            r1 = r6
        L_0x0054:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.gridlayout.widget.GridLayout.i(android.view.View, boolean, boolean):int");
    }

    public final int j(View view, boolean z) {
        return z ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    public final int k(View view, boolean z) {
        return h(view, z, false) + h(view, z, true);
    }

    public final void m() {
        this.h0 = 0;
        FW fw = this.b0;
        if (fw != null) {
            fw.p();
        }
        FW fw2 = this.c0;
        if (fw2 != null) {
            fw2.p();
        }
        FW fw3 = this.b0;
        if (fw3 != null && this.c0 != null) {
            fw3.q();
            this.c0.q();
        }
    }

    public final void n(View view, int i, int i2, int i3, int i4) {
        view.measure(ViewGroup.getChildMeasureSpec(i, k(view, true), i3), ViewGroup.getChildMeasureSpec(i2, k(view, false), i4));
    }

    public final void o(int i, int i2, boolean z) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                IW g = g(childAt);
                if (z) {
                    n(childAt, i, i2, ((ViewGroup.MarginLayoutParams) g).width, ((ViewGroup.MarginLayoutParams) g).height);
                } else {
                    boolean z2 = this.d0 == 0;
                    LW lw = z2 ? g.p : g.o;
                    if (lw.a(z2) == a0) {
                        HW hw = lw.c;
                        int[] k = (z2 ? this.b0 : this.c0).k();
                        int k2 = (k[hw.b] - k[hw.f8160a]) - k(childAt, z2);
                        if (z2) {
                            n(childAt, i, i2, k2, ((ViewGroup.MarginLayoutParams) g).height);
                        } else {
                            n(childAt, i, i2, ((ViewGroup.MarginLayoutParams) g).width, k2);
                        }
                    }
                }
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int[] iArr;
        boolean z2;
        GridLayout gridLayout = this;
        c();
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        FW fw = gridLayout.b0;
        int i6 = (i5 - paddingLeft) - paddingRight;
        fw.v.f8292a = i6;
        fw.w.f8292a = -i6;
        boolean z3 = false;
        fw.q = false;
        fw.k();
        FW fw2 = gridLayout.c0;
        int i7 = ((i4 - i2) - paddingTop) - paddingBottom;
        fw2.v.f8292a = i7;
        fw2.w.f8292a = -i7;
        fw2.q = false;
        fw2.k();
        int[] k = gridLayout.b0.k();
        int[] k2 = gridLayout.c0.k();
        int childCount = getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = gridLayout.getChildAt(i8);
            if (childAt.getVisibility() == 8) {
                z2 = z3;
                iArr = k;
            } else {
                IW g = gridLayout.g(childAt);
                LW lw = g.p;
                LW lw2 = g.o;
                HW hw = lw.c;
                HW hw2 = lw2.c;
                int i9 = k[hw.f8160a];
                int i10 = k2[hw2.f8160a];
                int i11 = k[hw.b] - i9;
                int i12 = k2[hw2.b] - i10;
                int j = gridLayout.j(childAt, true);
                int j2 = gridLayout.j(childAt, z3);
                BW a2 = lw.a(true);
                BW a3 = lw2.a(z3);
                GW gw = (GW) gridLayout.b0.j().b(i8);
                GW gw2 = (GW) gridLayout.c0.j().b(i8);
                iArr = k;
                int d = a2.d(childAt, i11 - gw.d(true));
                int d2 = a3.d(childAt, i12 - gw2.d(true));
                int h = gridLayout.h(childAt, true, true);
                int h2 = gridLayout.h(childAt, false, true);
                int h3 = gridLayout.h(childAt, true, false);
                int i13 = h + h3;
                int h4 = h2 + gridLayout.h(childAt, false, false);
                z2 = false;
                int a4 = gw.a(this, childAt, a2, j + i13, true);
                int a5 = gw2.a(this, childAt, a3, j2 + h4, false);
                int e = a2.e(childAt, j, i11 - i13);
                int e2 = a3.e(childAt, j2, i12 - h4);
                int i14 = i9 + d + a4;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                int i15 = !(getLayoutDirection() == 1) ? paddingLeft + h + i14 : (((i5 - e) - paddingRight) - h3) - i14;
                int i16 = paddingTop + i10 + d2 + a5 + h2;
                if (!(e == childAt.getMeasuredWidth() && e2 == childAt.getMeasuredHeight())) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(e, 1073741824), View.MeasureSpec.makeMeasureSpec(e2, 1073741824));
                }
                childAt.layout(i15, i16, e + i15, e2 + i16);
            }
            i8++;
            gridLayout = this;
            k = iArr;
            z3 = z2;
        }
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        c();
        FW fw = this.b0;
        if (!(fw == null || this.c0 == null)) {
            fw.q();
            this.c0.q();
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize((-paddingRight) + i), View.MeasureSpec.getMode(i));
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize((-paddingBottom) + i2), View.MeasureSpec.getMode(i2));
        o(makeMeasureSpec, makeMeasureSpec2, true);
        if (this.d0 == 0) {
            i3 = this.b0.m(makeMeasureSpec);
            o(makeMeasureSpec, makeMeasureSpec2, false);
            i4 = this.c0.m(makeMeasureSpec2);
        } else {
            int m = this.c0.m(makeMeasureSpec2);
            o(makeMeasureSpec, makeMeasureSpec2, false);
            i3 = this.b0.m(makeMeasureSpec);
            i4 = m;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i3 + paddingRight, getSuggestedMinimumWidth()), i, 0), View.resolveSizeAndState(Math.max(i4 + paddingBottom, getSuggestedMinimumHeight()), i2, 0));
    }

    public void q(int i) {
        this.b0.s(i);
        m();
        requestLayout();
    }

    public void requestLayout() {
        super.requestLayout();
        m();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof IW) {
            return new IW((IW) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new IW((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new IW(layoutParams);
    }
}
