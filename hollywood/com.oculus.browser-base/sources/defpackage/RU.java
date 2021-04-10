package defpackage;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: RU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RU implements Runnable {
    public static final ThreadLocal F = new ThreadLocal();
    public static Comparator G = new OU();
    public ArrayList H = new ArrayList();
    public long I;

    /* renamed from: J  reason: collision with root package name */
    public long f8835J;
    public ArrayList K = new ArrayList();

    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.I == 0) {
            this.I = System.nanoTime();
            recyclerView.post(this);
        }
        PU pu = recyclerView.P0;
        pu.f8693a = i;
        pu.b = i2;
    }

    public void b(long j) {
        QU qu;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        QU qu2;
        int size = this.H.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView3 = (RecyclerView) this.H.get(i2);
            if (recyclerView3.getWindowVisibility() == 0) {
                recyclerView3.P0.b(recyclerView3, false);
                i += recyclerView3.P0.d;
            }
        }
        this.K.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView4 = (RecyclerView) this.H.get(i4);
            if (recyclerView4.getWindowVisibility() == 0) {
                PU pu = recyclerView4.P0;
                int abs = Math.abs(pu.b) + Math.abs(pu.f8693a);
                for (int i5 = 0; i5 < pu.d * 2; i5 += 2) {
                    if (i3 >= this.K.size()) {
                        qu2 = new QU();
                        this.K.add(qu2);
                    } else {
                        qu2 = (QU) this.K.get(i3);
                    }
                    int[] iArr = pu.c;
                    int i6 = iArr[i5 + 1];
                    qu2.f8764a = i6 <= abs;
                    qu2.b = abs;
                    qu2.c = i6;
                    qu2.d = recyclerView4;
                    qu2.e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.K, G);
        int i7 = 0;
        while (i7 < this.K.size() && (recyclerView = (qu = (QU) this.K.get(i7)).d) != null) {
            XK0 c = c(recyclerView, qu.e, qu.f8764a ? Long.MAX_VALUE : j);
            if (!(c == null || c.H == null || !c.k() || c.l() || (recyclerView2 = (RecyclerView) c.H.get()) == null)) {
                if (recyclerView2.p0 && recyclerView2.M.h() != 0) {
                    recyclerView2.i0();
                }
                PU pu2 = recyclerView2.P0;
                pu2.b(recyclerView2, true);
                if (pu2.d != 0) {
                    try {
                        int i8 = AbstractC4969tm1.f11370a;
                        Trace.beginSection("RV Nested Prefetch");
                        VK0 vk0 = recyclerView2.Q0;
                        AbstractC5750yK0 yk0 = recyclerView2.T;
                        vk0.d = 1;
                        vk0.e = yk0.b();
                        vk0.g = false;
                        vk0.h = false;
                        vk0.i = false;
                        for (int i9 = 0; i9 < pu2.d * 2; i9 += 2) {
                            c(recyclerView2, pu2.c[i9], j);
                        }
                        Trace.endSection();
                    } catch (Throwable th) {
                        int i10 = AbstractC4969tm1.f11370a;
                        Trace.endSection();
                        throw th;
                    }
                }
            }
            qu.f8764a = false;
            qu.b = 0;
            qu.c = 0;
            qu.d = null;
            qu.e = 0;
            i7++;
        }
    }

    public final XK0 c(RecyclerView recyclerView, int i, long j) {
        boolean z;
        int h = recyclerView.M.h();
        int i2 = 0;
        while (true) {
            if (i2 >= h) {
                z = false;
                break;
            }
            XK0 M = RecyclerView.M(recyclerView.M.g(i2));
            if (M.I == i && !M.l()) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            return null;
        }
        PK0 pk0 = recyclerView.f9482J;
        try {
            recyclerView.Z();
            XK0 j2 = pk0.j(i, false, j);
            if (j2 != null) {
                if (!j2.k() || j2.l()) {
                    pk0.a(j2, false);
                } else {
                    pk0.g(j2.G);
                }
            }
            return j2;
        } finally {
            recyclerView.a0(false);
        }
    }

    public void run() {
        try {
            int i = AbstractC4969tm1.f11370a;
            Trace.beginSection("RV Prefetch");
            if (this.H.isEmpty()) {
                this.I = 0;
                Trace.endSection();
                return;
            }
            int size = this.H.size();
            long j = 0;
            for (int i2 = 0; i2 < size; i2++) {
                RecyclerView recyclerView = (RecyclerView) this.H.get(i2);
                if (recyclerView.getWindowVisibility() == 0) {
                    j = Math.max(recyclerView.getDrawingTime(), j);
                }
            }
            if (j == 0) {
                this.I = 0;
                Trace.endSection();
                return;
            }
            b(TimeUnit.MILLISECONDS.toNanos(j) + this.f8835J);
            this.I = 0;
            Trace.endSection();
        } catch (Throwable th) {
            this.I = 0;
            int i3 = AbstractC4969tm1.f11370a;
            Trace.endSection();
            throw th;
        }
    }
}
