package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: wK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5410wK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RecyclerView f11540a;

    public C5410wK0(RecyclerView recyclerView) {
        this.f11540a = recyclerView;
    }

    public void a(C3141j3 j3Var) {
        int i = j3Var.f10183a;
        if (i == 1) {
            RecyclerView recyclerView = this.f11540a;
            recyclerView.U.p0(recyclerView, j3Var.b, j3Var.d);
        } else if (i == 2) {
            RecyclerView recyclerView2 = this.f11540a;
            recyclerView2.U.s0(recyclerView2, j3Var.b, j3Var.d);
        } else if (i == 4) {
            RecyclerView recyclerView3 = this.f11540a;
            recyclerView3.U.u0(recyclerView3, j3Var.b, j3Var.d, j3Var.c);
        } else if (i == 8) {
            RecyclerView recyclerView4 = this.f11540a;
            recyclerView4.U.r0(recyclerView4, j3Var.b, j3Var.d, 1);
        }
    }

    public XK0 b(int i) {
        RecyclerView recyclerView = this.f11540a;
        int h = recyclerView.M.h();
        int i2 = 0;
        XK0 xk0 = null;
        while (true) {
            if (i2 >= h) {
                break;
            }
            XK0 M = RecyclerView.M(recyclerView.M.g(i2));
            if (M != null && !M.n() && M.I == i) {
                if (!recyclerView.M.k(M.G)) {
                    xk0 = M;
                    break;
                }
                xk0 = M;
            }
            i2++;
        }
        if (xk0 != null && !this.f11540a.M.k(xk0.G)) {
            return xk0;
        }
        return null;
    }

    public void c(int i, int i2, Object obj) {
        int i3;
        int i4;
        RecyclerView recyclerView = this.f11540a;
        int h = recyclerView.M.h();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < h; i6++) {
            View g = recyclerView.M.g(i6);
            XK0 M = RecyclerView.M(g);
            if (M != null && !M.v() && (i4 = M.I) >= i && i4 < i5) {
                M.b(2);
                M.a(obj);
                ((JK0) g.getLayoutParams()).c = true;
            }
        }
        PK0 pk0 = recyclerView.f9482J;
        int size = pk0.c.size();
        while (true) {
            size--;
            if (size >= 0) {
                XK0 xk0 = (XK0) pk0.c.get(size);
                if (xk0 != null && (i3 = xk0.I) >= i && i3 < i5) {
                    xk0.b(2);
                    pk0.f(size);
                }
            } else {
                this.f11540a.U0 = true;
                return;
            }
        }
    }

    public void d(int i, int i2) {
        RecyclerView recyclerView = this.f11540a;
        int h = recyclerView.M.h();
        for (int i3 = 0; i3 < h; i3++) {
            XK0 M = RecyclerView.M(recyclerView.M.g(i3));
            if (M != null && !M.v() && M.I >= i) {
                M.r(i2, false);
                recyclerView.Q0.f = true;
            }
        }
        PK0 pk0 = recyclerView.f9482J;
        int size = pk0.c.size();
        for (int i4 = 0; i4 < size; i4++) {
            XK0 xk0 = (XK0) pk0.c.get(i4);
            if (xk0 != null && xk0.I >= i) {
                xk0.r(i2, true);
            }
        }
        recyclerView.requestLayout();
        this.f11540a.T0 = true;
    }

    public void e(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        RecyclerView recyclerView = this.f11540a;
        int h = recyclerView.M.h();
        int i10 = -1;
        if (i < i2) {
            i5 = i;
            i4 = i2;
            i3 = -1;
        } else {
            i4 = i;
            i5 = i2;
            i3 = 1;
        }
        for (int i11 = 0; i11 < h; i11++) {
            XK0 M = RecyclerView.M(recyclerView.M.g(i11));
            if (M != null && (i9 = M.I) >= i5 && i9 <= i4) {
                if (i9 == i) {
                    M.r(i2 - i, false);
                } else {
                    M.r(i3, false);
                }
                recyclerView.Q0.f = true;
            }
        }
        PK0 pk0 = recyclerView.f9482J;
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i10 = 1;
        }
        int size = pk0.c.size();
        for (int i12 = 0; i12 < size; i12++) {
            XK0 xk0 = (XK0) pk0.c.get(i12);
            if (xk0 != null && (i8 = xk0.I) >= i7 && i8 <= i6) {
                if (i8 == i) {
                    xk0.r(i2 - i, false);
                } else {
                    xk0.r(i10, false);
                }
            }
        }
        recyclerView.requestLayout();
        this.f11540a.T0 = true;
    }
}
