package X;

import android.view.View;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;

/* renamed from: X.1Bs  reason: invalid class name and case insensitive filesystem */
public class C05891Bs {
    public int A00 = Integer.MIN_VALUE;
    public int A01 = Integer.MIN_VALUE;
    public int A02 = 0;
    public ArrayList<View> A03 = new ArrayList<>();
    public final int A04;
    public final /* synthetic */ StaggeredGridLayoutManager A05;

    public final View A07(int i, int i2) {
        View view = null;
        if (i2 != -1) {
            int size = this.A03.size() - 1;
            while (size >= 0) {
                View view2 = this.A03.get(size);
                StaggeredGridLayoutManager staggeredGridLayoutManager = this.A05;
                if (staggeredGridLayoutManager.A0C && staggeredGridLayoutManager.getPosition(view2) >= i) {
                    break;
                } else if (staggeredGridLayoutManager.A0C || staggeredGridLayoutManager.getPosition(view2) > i) {
                    if (!view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                } else {
                    return view;
                }
            }
        } else {
            int size2 = this.A03.size();
            int i3 = 0;
            while (i3 < size2) {
                View view3 = this.A03.get(i3);
                StaggeredGridLayoutManager staggeredGridLayoutManager2 = this.A05;
                if ((staggeredGridLayoutManager2.A0C && staggeredGridLayoutManager2.getPosition(view3) <= i) || ((!staggeredGridLayoutManager2.A0C && staggeredGridLayoutManager2.getPosition(view3) >= i) || !view3.hasFocusable())) {
                    break;
                }
                i3++;
                view = view3;
            }
        }
        return view;
    }

    public C05891Bs(StaggeredGridLayoutManager staggeredGridLayoutManager, int i) {
        this.A05 = staggeredGridLayoutManager;
        this.A04 = i;
    }

    private final int A00(int i, int i2) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.A05;
        int A052 = staggeredGridLayoutManager.A05.A05();
        int A022 = staggeredGridLayoutManager.A05.A02();
        int i3 = -1;
        if (i2 > i) {
            i3 = 1;
        }
        while (i != i2) {
            View view = this.A03.get(i);
            int A0B = staggeredGridLayoutManager.A05.A0B(view);
            int A08 = staggeredGridLayoutManager.A05.A08(view);
            boolean z = false;
            boolean z2 = false;
            if (A0B <= A022) {
                z2 = true;
            }
            if (A08 >= A052) {
                z = true;
            }
            if (z2 && z && (A0B < A052 || A08 > A022)) {
                return staggeredGridLayoutManager.getPosition(view);
            }
            i += i3;
        }
        return -1;
    }

    public static final void A01(C05891Bs r3) {
        ArrayList<View> arrayList = r3.A03;
        View view = arrayList.get(arrayList.size() - 1);
        view.getLayoutParams();
        r3.A00 = r3.A05.A05.A08(view);
    }

    public static final void A02(C05891Bs r2) {
        View view = r2.A03.get(0);
        view.getLayoutParams();
        r2.A01 = r2.A05.A05.A0B(view);
    }

    public final int A03() {
        if (this.A05.A0C) {
            return A00(this.A03.size() - 1, -1);
        }
        return A00(0, this.A03.size());
    }

    public final int A04() {
        if (this.A05.A0C) {
            return A00(0, this.A03.size());
        }
        return A00(this.A03.size() - 1, -1);
    }

    public final int A05(int i) {
        int i2 = this.A00;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.A03.size() == 0) {
            return i;
        }
        A01(this);
        return this.A00;
    }

    public final int A06(int i) {
        int i2 = this.A01;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.A03.size() == 0) {
            return i;
        }
        A02(this);
        return this.A01;
    }

    public final void A08() {
        this.A03.clear();
        this.A01 = Integer.MIN_VALUE;
        this.A00 = Integer.MIN_VALUE;
        this.A02 = 0;
    }
}
