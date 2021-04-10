package defpackage;

import android.os.Handler;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: EW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class EW0 {

    /* renamed from: a  reason: collision with root package name */
    public DK0 f7966a = null;
    public ArrayList b = new ArrayList();
    public long c = 120;
    public long d = 120;
    public long e = 250;
    public long f = 250;
    public boolean g = true;

    public static int c(XK0 xk0) {
        int i = xk0.P & 14;
        if (xk0.l()) {
            return 4;
        }
        if ((i & 4) != 0) {
            return i;
        }
        int i2 = xk0.f9202J;
        int e2 = xk0.e();
        return (i2 == -1 || e2 == -1 || i2 == e2) ? i : i | 2048;
    }

    public boolean a(XK0 xk0, XK0 xk02, CK0 ck0, CK0 ck02) {
        int i;
        int i2;
        int i3 = ck0.f7802a;
        int i4 = ck0.b;
        if (xk02.v()) {
            int i5 = ck0.f7802a;
            i = ck0.b;
            i2 = i5;
        } else {
            i2 = ck02.f7802a;
            i = ck02.b;
        }
        VD vd = (VD) this;
        if (xk0 == xk02) {
            return vd.b(xk0, i3, i4, i2, i);
        }
        float translationX = xk0.G.getTranslationX();
        float translationY = xk0.G.getTranslationY();
        float alpha = xk0.G.getAlpha();
        vd.o(xk0);
        xk0.G.setTranslationX(translationX);
        xk0.G.setTranslationY(translationY);
        xk0.G.setAlpha(alpha);
        vd.o(xk02);
        xk02.G.setTranslationX((float) (-((int) (((float) (i2 - i3)) - translationX))));
        xk02.G.setTranslationY((float) (-((int) (((float) (i - i4)) - translationY))));
        xk02.G.setAlpha(0.0f);
        vd.l.add(new TD(xk0, xk02, i3, i4, i2, i));
        return true;
    }

    public abstract boolean b(XK0 xk0, int i, int i2, int i3, int i4);

    public final void d(XK0 xk0) {
        DK0 dk0 = this.f7966a;
        if (dk0 != null) {
            boolean z = true;
            xk0.u(true);
            if (xk0.N != null && xk0.O == null) {
                xk0.N = null;
            }
            xk0.O = null;
            if (!((xk0.P & 16) != 0)) {
                RecyclerView recyclerView = dk0.f7882a;
                View view = xk0.G;
                recyclerView.x0();
                C2754go goVar = recyclerView.M;
                int indexOfChild = goVar.f10022a.f11475a.indexOfChild(view);
                if (indexOfChild == -1) {
                    goVar.l(view);
                } else if (goVar.b.d(indexOfChild)) {
                    goVar.b.f(indexOfChild);
                    goVar.l(view);
                    goVar.f10022a.c(indexOfChild);
                } else {
                    z = false;
                }
                if (z) {
                    XK0 M = RecyclerView.M(view);
                    recyclerView.f9482J.k(M);
                    recyclerView.f9482J.h(M);
                }
                recyclerView.z0(!z);
                if (!z && xk0.p()) {
                    dk0.f7882a.removeDetachedView(xk0.G, false);
                }
            }
        }
    }

    public final void e() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            MO mo = (MO) this.b.get(i);
            Objects.requireNonNull(mo);
            new Handler().post(new LO(mo));
        }
        this.b.clear();
    }

    public abstract void f(XK0 xk0);

    public abstract void g();

    public abstract boolean h();

    public final boolean i(MO mo) {
        boolean h = h();
        if (mo != null) {
            if (!h) {
                new Handler().post(new LO(mo));
            } else {
                this.b.add(mo);
            }
        }
        return h;
    }

    public CK0 j(VK0 vk0, XK0 xk0, int i, List list) {
        CK0 ck0 = new CK0();
        View view = xk0.G;
        ck0.f7802a = view.getLeft();
        ck0.b = view.getTop();
        view.getRight();
        view.getBottom();
        return ck0;
    }
}
