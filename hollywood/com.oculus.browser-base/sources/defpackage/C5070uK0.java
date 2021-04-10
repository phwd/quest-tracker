package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

/* renamed from: uK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5070uK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RecyclerView f11406a;

    public C5070uK0(RecyclerView recyclerView) {
        this.f11406a = recyclerView;
    }

    public void a(XK0 xk0, CK0 ck0, CK0 ck02) {
        boolean z;
        int i;
        int i2;
        RecyclerView recyclerView = this.f11406a;
        Objects.requireNonNull(recyclerView);
        xk0.u(false);
        EW0 ew0 = recyclerView.y0;
        Objects.requireNonNull(ew0);
        if (ck0 == null || ((i = ck0.f7802a) == (i2 = ck02.f7802a) && ck0.b == ck02.b)) {
            VD vd = (VD) ew0;
            vd.o(xk0);
            xk0.G.setAlpha(0.0f);
            vd.j.add(xk0);
            z = true;
        } else {
            z = ew0.b(xk0, i, ck0.b, i2, ck02.b);
        }
        if (z) {
            recyclerView.e0();
        }
    }

    public void b(XK0 xk0, CK0 ck0, CK0 ck02) {
        boolean z;
        this.f11406a.f9482J.k(xk0);
        RecyclerView recyclerView = this.f11406a;
        recyclerView.f(xk0);
        xk0.u(false);
        EW0 ew0 = recyclerView.y0;
        Objects.requireNonNull(ew0);
        int i = ck0.f7802a;
        int i2 = ck0.b;
        View view = xk0.G;
        int left = ck02 == null ? view.getLeft() : ck02.f7802a;
        int top = ck02 == null ? view.getTop() : ck02.b;
        if (xk0.n() || (i == left && i2 == top)) {
            VD vd = (VD) ew0;
            vd.o(xk0);
            vd.i.add(xk0);
            z = true;
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            z = ew0.b(xk0, i, i2, left, top);
        }
        if (z) {
            recyclerView.e0();
        }
    }
}
