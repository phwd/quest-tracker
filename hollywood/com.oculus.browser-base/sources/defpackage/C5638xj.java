package defpackage;

import android.view.Window;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: xj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5638xj extends Ep1 implements AbstractC4448qj, AbstractC5808yj {
    public BottomSheet F;
    public PriorityQueue G;
    public boolean H;
    public Runnable I;

    /* renamed from: J  reason: collision with root package name */
    public List f11628J = new ArrayList();
    public int K;
    public AbstractC4277pj L;
    public Q31 M;
    public final C2399ej1 N = new C2399ej1(new RunnableC4618rj(this));
    public X O;

    public C5638xj(Q31 q31, Callback callback, Window window, C3493l60 l60, Q31 q312) {
        this.M = q31;
        this.I = new RunnableC4788sj(this, callback, window, l60, q312);
    }

    public void j(AbstractC0576Jj jj) {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet == null) {
            this.f11628J.add(jj);
        } else {
            bottomSheet.H.b(jj);
        }
    }

    public void k() {
        if (this.F != null) {
            Iterator it = this.G.iterator();
            while (it.hasNext()) {
                Objects.requireNonNull((AbstractC4277pj) it.next());
                it.remove();
            }
            p(this.F.a0, true, 0);
            this.L = null;
            this.K = -1;
        }
    }

    public boolean l(boolean z) {
        if (this.F != null && !this.N.b()) {
            BottomSheet bottomSheet = this.F;
            if (bottomSheet.g0 && bottomSheet.q()) {
                this.F.v(1, z, 0);
                return true;
            }
        }
        return false;
    }

    public void m() {
        if (this.F != null && !this.N.b()) {
            BottomSheet bottomSheet = this.F;
            if (bottomSheet.a0 != null) {
                bottomSheet.v(2, true, 0);
            }
        }
    }

    public AbstractC4277pj n() {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet == null) {
            return null;
        }
        return bottomSheet.a0;
    }

    public int o() {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet == null) {
            return 0;
        }
        return bottomSheet.U;
    }

    public void p(AbstractC4277pj pjVar, boolean z, int i) {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet != null) {
            AbstractC4277pj pjVar2 = bottomSheet.a0;
            if (pjVar != pjVar2) {
                this.G.remove(pjVar);
            } else if (!this.H) {
                if (bottomSheet.U == 0) {
                    if (pjVar2 != null) {
                        pjVar2.f();
                    }
                    v(z);
                    return;
                }
                this.H = true;
                bottomSheet.v(0, z, i);
            }
        }
    }

    public boolean q() {
        BottomSheet bottomSheet = this.F;
        return bottomSheet != null && bottomSheet.g0;
    }

    public void r(AbstractC0576Jj jj) {
        BottomSheet bottomSheet = this.F;
        if (bottomSheet != null) {
            bottomSheet.H.c(jj);
        } else {
            this.f11628J.remove(jj);
        }
    }

    public boolean u(AbstractC4277pj pjVar, boolean z) {
        if (pjVar != null) {
            if (this.F == null) {
                this.I.run();
            }
            AbstractC4277pj pjVar2 = this.F.a0;
            if (pjVar == pjVar2) {
                return true;
            }
            boolean z2 = pjVar2 != null && pjVar.k() < this.F.a0.k() && (this.F.g0 ^ true);
            this.G.add(pjVar);
            if (this.F.a0 != null || this.N.b()) {
                if (z2) {
                    this.G.add(this.F.a0);
                    if (!this.N.b()) {
                        this.F.v(0, z, 0);
                        return true;
                    }
                    this.F.x(null);
                }
                return false;
            }
            v(z);
            return true;
        }
        throw new RuntimeException("Attempting to show null content in the sheet!");
    }

    public final void v(boolean z) {
        if (this.F.U != 0) {
            throw new RuntimeException("Showing next content before sheet is hidden!");
        } else if (this.G.isEmpty()) {
            this.F.x(null);
        } else {
            this.F.x((AbstractC4277pj) this.G.poll());
            BottomSheet bottomSheet = this.F;
            bottomSheet.v(bottomSheet.j(), z, 0);
        }
    }

    public int z(int i) {
        boolean b = this.N.b();
        int a2 = this.N.a();
        if (!b && this.F != null) {
            this.K = o();
            this.L = n();
            this.F.v(0, false, i);
        }
        return a2;
    }
}
