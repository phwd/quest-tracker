package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* renamed from: PK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PK0 {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f8685a;
    public ArrayList b = null;
    public final ArrayList c = new ArrayList();
    public final List d;
    public int e;
    public int f;
    public OK0 g;
    public final /* synthetic */ RecyclerView h;

    public PK0(RecyclerView recyclerView) {
        this.h = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.f8685a = arrayList;
        this.d = Collections.unmodifiableList(arrayList);
        this.e = 2;
        this.f = 2;
    }

    public void a(XK0 xk0, boolean z) {
        RecyclerView.l(xk0);
        View view = xk0.G;
        ZK0 zk0 = this.h.X0;
        if (zk0 != null) {
            C5349w j = zk0.j();
            AbstractC1920bu1.n(view, j instanceof YK0 ? (C5349w) ((YK0) j).e.remove(view) : null);
        }
        if (z) {
            QK0 qk0 = this.h.V;
            if (qk0 != null) {
                qk0.a(xk0);
            }
            int size = this.h.W.size();
            for (int i = 0; i < size; i++) {
                ((QK0) this.h.W.get(i)).a(xk0);
            }
            AbstractC5750yK0 yk0 = this.h.T;
            if (yk0 != null) {
                yk0.q(xk0);
            }
            RecyclerView recyclerView = this.h;
            if (recyclerView.Q0 != null) {
                recyclerView.N.g(xk0);
            }
        }
        xk0.Y = null;
        xk0.X = null;
        OK0 d2 = d();
        Objects.requireNonNull(d2);
        int i2 = xk0.L;
        ArrayList arrayList = d2.c(i2).f8541a;
        if (((NK0) d2.f8620a.get(i2)).b > arrayList.size()) {
            xk0.s();
            arrayList.add(xk0);
        }
    }

    public void b() {
        this.f8685a.clear();
        e();
    }

    public int c(int i) {
        if (i < 0 || i >= this.h.Q0.b()) {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid position ");
            sb.append(i);
            sb.append(". State item count is ");
            sb.append(this.h.Q0.b());
            throw new IndexOutOfBoundsException(AbstractC2531fV.v(this.h, sb));
        }
        RecyclerView recyclerView = this.h;
        if (!recyclerView.Q0.g) {
            return i;
        }
        return recyclerView.L.f(i, 0);
    }

    public OK0 d() {
        if (this.g == null) {
            this.g = new OK0();
        }
        return this.g;
    }

    public void e() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            f(size);
        }
        this.c.clear();
        int[] iArr = RecyclerView.F;
        PU pu = this.h.P0;
        int[] iArr2 = pu.c;
        if (iArr2 != null) {
            Arrays.fill(iArr2, -1);
        }
        pu.d = 0;
    }

    public void f(int i) {
        a((XK0) this.c.get(i), true);
        this.c.remove(i);
    }

    public void g(View view) {
        XK0 M = RecyclerView.M(view);
        if (M.p()) {
            this.h.removeDetachedView(view, false);
        }
        if (M.o()) {
            M.T.k(M);
        } else if (M.w()) {
            M.d();
        }
        h(M);
        if (this.h.y0 != null && !M.m()) {
            this.h.y0.f(M);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h(defpackage.XK0 r7) {
        /*
        // Method dump skipped, instructions count: 267
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.PK0.h(XK0):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i(android.view.View r5) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.PK0.i(android.view.View):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:161:0x02fc, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0427, code lost:
        if (r7.l() == false) goto L_0x0462;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0460, code lost:
        if ((r10 == 0 || r10 + r8 < r19) == false) goto L_0x0462;
     */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x03e9  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0419  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x0449  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0471  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0473  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0476  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x04e6  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x051b  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0524  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x052f  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x053d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public defpackage.XK0 j(int r17, boolean r18, long r19) {
        /*
        // Method dump skipped, instructions count: 1424
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.PK0.j(int, boolean, long):XK0");
    }

    public void k(XK0 xk0) {
        if (xk0.U) {
            this.b.remove(xk0);
        } else {
            this.f8685a.remove(xk0);
        }
        xk0.T = null;
        xk0.U = false;
        xk0.d();
    }

    public void l() {
        IK0 ik0 = this.h.U;
        this.f = this.e + (ik0 != null ? ik0.l : 0);
        for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.f; size--) {
            f(size);
        }
    }
}
