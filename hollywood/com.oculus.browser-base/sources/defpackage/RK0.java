package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: RK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RK0 extends AK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RecyclerView f8827a;

    public RK0(RecyclerView recyclerView) {
        this.f8827a = recyclerView;
    }

    @Override // defpackage.AK0
    public void a() {
        this.f8827a.j(null);
        RecyclerView recyclerView = this.f8827a;
        recyclerView.Q0.f = true;
        recyclerView.g0(true);
        if (!this.f8827a.L.g()) {
            this.f8827a.requestLayout();
        }
    }

    @Override // defpackage.AK0
    public void c(int i, int i2, Object obj) {
        this.f8827a.j(null);
        C3312k3 k3Var = this.f8827a.L;
        Objects.requireNonNull(k3Var);
        boolean z = false;
        if (i2 >= 1) {
            k3Var.b.add(k3Var.h(4, i, i2, obj));
            k3Var.f |= 4;
            if (k3Var.b.size() == 1) {
                z = true;
            }
        }
        if (z) {
            g();
        }
    }

    @Override // defpackage.AK0
    public void d(int i, int i2) {
        this.f8827a.j(null);
        C3312k3 k3Var = this.f8827a.L;
        Objects.requireNonNull(k3Var);
        boolean z = false;
        if (i2 >= 1) {
            k3Var.b.add(k3Var.h(1, i, i2, null));
            k3Var.f |= 1;
            if (k3Var.b.size() == 1) {
                z = true;
            }
        }
        if (z) {
            g();
        }
    }

    @Override // defpackage.AK0
    public void e(int i, int i2, int i3) {
        this.f8827a.j(null);
        C3312k3 k3Var = this.f8827a.L;
        Objects.requireNonNull(k3Var);
        boolean z = false;
        if (i != i2) {
            if (i3 == 1) {
                k3Var.b.add(k3Var.h(8, i, i2, null));
                k3Var.f |= 8;
                if (k3Var.b.size() == 1) {
                    z = true;
                }
            } else {
                throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
            }
        }
        if (z) {
            g();
        }
    }

    @Override // defpackage.AK0
    public void f(int i, int i2) {
        this.f8827a.j(null);
        C3312k3 k3Var = this.f8827a.L;
        Objects.requireNonNull(k3Var);
        boolean z = false;
        if (i2 >= 1) {
            k3Var.b.add(k3Var.h(2, i, i2, null));
            k3Var.f |= 2;
            if (k3Var.b.size() == 1) {
                z = true;
            }
        }
        if (z) {
            g();
        }
    }

    public void g() {
        int[] iArr = RecyclerView.F;
        RecyclerView recyclerView = this.f8827a;
        if (!recyclerView.e0 || !recyclerView.d0) {
            recyclerView.m0 = true;
            recyclerView.requestLayout();
            return;
        }
        Runnable runnable = recyclerView.P;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        recyclerView.postOnAnimation(runnable);
    }
}
