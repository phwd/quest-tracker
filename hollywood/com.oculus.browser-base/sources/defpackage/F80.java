package defpackage;

import android.graphics.PointF;
import android.view.View;

/* renamed from: F80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F80 extends CY0 {
    public AbstractC4308pt0 d;
    public AbstractC4308pt0 e;

    @Override // defpackage.CY0
    public int[] b(IK0 ik0, View view) {
        int[] iArr = new int[2];
        if (ik0.f()) {
            iArr[0] = g(view, j(ik0));
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
    public View d(IK0 ik0) {
        if (ik0.g()) {
            return i(ik0, k(ik0));
        }
        if (ik0.f()) {
            return i(ik0, j(ik0));
        }
        return null;
    }

    @Override // defpackage.CY0
    public int e(IK0 ik0, int i, int i2) {
        int J2;
        View d2;
        int R;
        int i3;
        PointF a2;
        int i4;
        int i5;
        if (!(ik0 instanceof UK0) || (J2 = ik0.J()) == 0 || (d2 = d(ik0)) == null || (R = ik0.R(d2)) == -1 || (a2 = ((UK0) ik0).a(J2 - 1)) == null) {
            return -1;
        }
        int i6 = 0;
        if (ik0.f()) {
            i4 = h(ik0, j(ik0), i, 0);
            if (a2.x < 0.0f) {
                i4 = -i4;
            }
        } else {
            i4 = 0;
        }
        if (ik0.g()) {
            i5 = h(ik0, k(ik0), 0, i2);
            if (a2.y < 0.0f) {
                i5 = -i5;
            }
        } else {
            i5 = 0;
        }
        if (ik0.g()) {
            i4 = i5;
        }
        if (i4 == 0) {
            return -1;
        }
        int i7 = R + i4;
        if (i7 >= 0) {
            i6 = i7;
        }
        return i6 >= J2 ? i3 : i6;
    }

    public final int g(View view, AbstractC4308pt0 pt0) {
        return ((pt0.c(view) / 2) + pt0.e(view)) - ((pt0.l() / 2) + pt0.k());
    }

    public final int h(IK0 ik0, AbstractC4308pt0 pt0, int i, int i2) {
        int max;
        this.b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int[] iArr = {this.b.getFinalX(), this.b.getFinalY()};
        int z = ik0.z();
        float f = 1.0f;
        if (z != 0) {
            View view = null;
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            View view2 = null;
            for (int i5 = 0; i5 < z; i5++) {
                View y = ik0.y(i5);
                int R = ik0.R(y);
                if (R != -1) {
                    if (R < i4) {
                        view = y;
                        i4 = R;
                    }
                    if (R > i3) {
                        view2 = y;
                        i3 = R;
                    }
                }
            }
            if (!(view == null || view2 == null || (max = Math.max(pt0.b(view), pt0.b(view2)) - Math.min(pt0.e(view), pt0.e(view2))) == 0)) {
                f = (((float) max) * 1.0f) / ((float) ((i3 - i4) + 1));
            }
        }
        if (f <= 0.0f) {
            return 0;
        }
        return Math.round(((float) (Math.abs(iArr[0]) > Math.abs(iArr[1]) ? iArr[0] : iArr[1])) / f);
    }

    public final View i(IK0 ik0, AbstractC4308pt0 pt0) {
        int z = ik0.z();
        View view = null;
        if (z == 0) {
            return null;
        }
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
        return view;
    }

    public final AbstractC4308pt0 j(IK0 ik0) {
        AbstractC4308pt0 pt0 = this.e;
        if (pt0 == null || pt0.f11095a != ik0) {
            this.e = new C3966nt0(ik0);
        }
        return this.e;
    }

    public final AbstractC4308pt0 k(IK0 ik0) {
        AbstractC4308pt0 pt0 = this.d;
        if (pt0 == null || pt0.f11095a != ik0) {
            this.d = new C4137ot0(ik0);
        }
        return this.d;
    }
}
