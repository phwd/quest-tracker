package defpackage;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* renamed from: yK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5750yK0 {
    public final C5920zK0 F = new C5920zK0();
    public boolean G = false;
    public int H = 1;

    public abstract int b();

    public long c(int i) {
        return -1;
    }

    public int d(int i) {
        return 0;
    }

    public final void g() {
        this.F.b();
    }

    public final void h(int i, int i2) {
        this.F.e(i, i2);
    }

    public void i(RecyclerView recyclerView) {
    }

    public abstract void j(XK0 xk0, int i);

    public void k(XK0 xk0, int i, List list) {
        j(xk0, i);
    }

    public abstract XK0 m(ViewGroup viewGroup, int i);

    public void n(RecyclerView recyclerView) {
    }

    public boolean o(XK0 xk0) {
        return false;
    }

    public void p(XK0 xk0) {
    }

    public void q(XK0 xk0) {
    }

    public void r(boolean z) {
        if (!this.F.a()) {
            this.G = z;
            return;
        }
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }
}
