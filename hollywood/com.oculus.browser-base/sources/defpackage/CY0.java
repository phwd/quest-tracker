package defpackage;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: CY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class CY0 {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView f7818a;
    public Scroller b;
    public final MK0 c = new AY0(this);

    public void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f7818a;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.k0(this.c);
                this.f7818a.H0 = null;
            }
            this.f7818a = recyclerView;
            if (recyclerView == null) {
                return;
            }
            if (recyclerView.H0 == null) {
                recyclerView.i(this.c);
                this.f7818a.H0 = this;
                this.b = new Scroller(this.f7818a.getContext(), new DecelerateInterpolator());
                f();
                return;
            }
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
    }

    public abstract int[] b(IK0 ik0, View view);

    public E80 c(IK0 ik0) {
        if (!(ik0 instanceof UK0)) {
            return null;
        }
        return new BY0(this, this.f7818a.getContext());
    }

    public abstract View d(IK0 ik0);

    public abstract int e(IK0 ik0, int i, int i2);

    public void f() {
        IK0 ik0;
        View d;
        RecyclerView recyclerView = this.f7818a;
        if (recyclerView != null && (ik0 = recyclerView.U) != null && (d = d(ik0)) != null) {
            int[] b2 = b(ik0, d);
            if (b2[0] != 0 || b2[1] != 0) {
                this.f7818a.v0(b2[0], b2[1], null, Integer.MIN_VALUE, false);
            }
        }
    }
}
