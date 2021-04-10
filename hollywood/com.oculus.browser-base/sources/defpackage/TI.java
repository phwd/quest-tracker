package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.Objects;

/* renamed from: TI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TI extends AbstractC4853t40 {
    public XK0 d;
    public final /* synthetic */ UI e;

    public TI(UI ui, PI pi) {
        this.e = ui;
    }

    @Override // defpackage.AbstractC4853t40
    public boolean a(RecyclerView recyclerView, XK0 xk0, XK0 xk02) {
        Objects.requireNonNull((H60) this.e);
        boolean z = xk0 instanceof G60;
        Objects.requireNonNull((H60) this.e);
        return z && (xk02 instanceof G60);
    }

    @Override // defpackage.AbstractC4853t40
    public void b(RecyclerView recyclerView, XK0 xk0) {
        super.b(recyclerView, xk0);
        if (xk0.f() != this.e.Q && recyclerView.isAttachedToWindow()) {
            recyclerView.post(new RI(this));
        }
        this.d = null;
        UI.s(this.e, false);
        o(false, xk0);
    }

    @Override // defpackage.AbstractC4853t40
    public int f(RecyclerView recyclerView, XK0 xk0) {
        XK0 xk02 = this.d;
        if (xk02 == xk0 || xk02 == null) {
            Objects.requireNonNull((H60) this.e);
            if (xk0 instanceof G60) {
                return AbstractC4853t40.j(3, 0);
            }
        }
        return AbstractC4853t40.j(0, 0);
    }

    @Override // defpackage.AbstractC4853t40
    public boolean i() {
        return !this.e.P.c;
    }

    @Override // defpackage.AbstractC4853t40
    public boolean l(RecyclerView recyclerView, XK0 xk0, XK0 xk02) {
        int f = xk0.f();
        int f2 = xk02.f();
        if (f == f2) {
            return false;
        }
        Collections.swap(this.e.L, f, f2);
        this.e.F.c(f, f2);
        return true;
    }

    @Override // defpackage.AbstractC4853t40
    public void m(XK0 xk0, int i) {
        if (i == 2 && this.d != xk0) {
            this.d = xk0;
            this.e.Q = xk0.f();
            UI.s(this.e, true);
            o(true, xk0);
        }
    }

    @Override // defpackage.AbstractC4853t40
    public void n(XK0 xk0, int i) {
    }

    public final void o(boolean z, XK0 xk0) {
        Zu1 a2 = AbstractC1920bu1.a(xk0.G);
        float f = z ? this.e.O : 0.0f;
        View view = (View) a2.f9382a.get();
        if (view != null) {
            view.animate().translationZ(f);
        }
        SI si = new SI(this, xk0, z);
        View view2 = (View) a2.f9382a.get();
        if (view2 != null) {
            view2.animate().withEndAction(si);
        }
        a2.c(100);
        View view3 = (View) a2.f9382a.get();
        if (view3 != null) {
            view3.animate().start();
        }
    }
}
