package defpackage;

import android.graphics.PointF;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

/* renamed from: Tu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Tu1 extends CY0 {
    public AbstractC4308pt0 d;
    public AbstractC4308pt0 e;
    public final /* synthetic */ ViewPager2 f;

    public Tu1(ViewPager2 viewPager2) {
        this.f = viewPager2;
    }

    @Override // defpackage.CY0
    public int[] b(IK0 ik0, View view) {
        int[] iArr = new int[2];
        if (ik0.f()) {
            iArr[0] = g(view, i(ik0));
        } else {
            iArr[0] = 0;
        }
        if (ik0.g()) {
            iArr[1] = g(view, k(ik0));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // defpackage.CY0
    public E80 c(IK0 ik0) {
        if (!(ik0 instanceof UK0)) {
            return null;
        }
        return new C1027Qv0(this, this.f7818a.getContext());
    }

    @Override // defpackage.CY0
    public View d(IK0 ik0) {
        if (!this.f.T.f9428a.m) {
            if (ik0.g()) {
                return h(ik0, k(ik0));
            }
            if (ik0.f()) {
                return h(ik0, i(ik0));
            }
        }
        return null;
    }

    @Override // defpackage.CY0
    public int e(IK0 ik0, int i, int i2) {
        AbstractC4308pt0 j;
        int J2 = ik0.J();
        if (J2 == 0 || (j = j(ik0)) == null) {
            return -1;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int z = ik0.z();
        View view = null;
        View view2 = null;
        for (int i5 = 0; i5 < z; i5++) {
            View y = ik0.y(i5);
            if (y != null) {
                int g = g(y, j);
                if (g <= 0 && g > i3) {
                    view2 = y;
                    i3 = g;
                }
                if (g >= 0 && g < i4) {
                    view = y;
                    i4 = g;
                }
            }
        }
        boolean l = l(ik0, i, i2);
        if (l && view != null) {
            return ik0.R(view);
        }
        if (!(l || view2 == null)) {
            return ik0.R(view2);
        }
        if (l) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int R = ik0.R(view) + (m(ik0) == l ? -1 : 1);
        if (R < 0 || R >= J2) {
            return -1;
        }
        return R;
    }

    public final int g(View view, AbstractC4308pt0 pt0) {
        return ((pt0.c(view) / 2) + pt0.e(view)) - ((pt0.l() / 2) + pt0.k());
    }

    public final View h(IK0 ik0, AbstractC4308pt0 pt0) {
        int z = ik0.z();
        View view = null;
        if (z != 0) {
            int l = (pt0.l() / 2) + pt0.k();
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < z; i2++) {
                View y = ik0.y(i2);
                int abs = Math.abs(((pt0.c(y) / 2) + pt0.e(y)) - l);
                if (abs < i) {
                    view = y;
                    i = abs;
                }
            }
        }
        return view;
    }

    public final AbstractC4308pt0 i(IK0 ik0) {
        AbstractC4308pt0 pt0 = this.e;
        if (pt0 == null || pt0.f11095a != ik0) {
            this.e = new C3966nt0(ik0);
        }
        return this.e;
    }

    public final AbstractC4308pt0 j(IK0 ik0) {
        if (ik0.g()) {
            return k(ik0);
        }
        if (ik0.f()) {
            return i(ik0);
        }
        return null;
    }

    public final AbstractC4308pt0 k(IK0 ik0) {
        AbstractC4308pt0 pt0 = this.d;
        if (pt0 == null || pt0.f11095a != ik0) {
            this.d = new C4137ot0(ik0);
        }
        return this.d;
    }

    public final boolean l(IK0 ik0, int i, int i2) {
        return !ik0.f() ? i2 > 0 : i > 0;
    }

    public final boolean m(IK0 ik0) {
        PointF a2;
        int J2 = ik0.J();
        if (!(ik0 instanceof UK0) || (a2 = ((UK0) ik0).a(J2 - 1)) == null || (a2.x >= 0.0f && a2.y >= 0.0f)) {
            return false;
        }
        return true;
    }
}
