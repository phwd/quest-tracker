package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: sK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4730sK0 implements Runnable {
    public final /* synthetic */ RecyclerView F;

    public RunnableC4730sK0(RecyclerView recyclerView) {
        this.F = recyclerView;
    }

    public void run() {
        EW0 ew0 = this.F.y0;
        if (ew0 != null) {
            VD vd = (VD) ew0;
            boolean z = !vd.i.isEmpty();
            boolean z2 = !vd.k.isEmpty();
            boolean z3 = !vd.l.isEmpty();
            boolean z4 = !vd.j.isEmpty();
            if (z || z2 || z4 || z3) {
                Iterator it = vd.i.iterator();
                while (it.hasNext()) {
                    XK0 xk0 = (XK0) it.next();
                    View view = xk0.G;
                    ViewPropertyAnimator animate = view.animate();
                    vd.r.add(xk0);
                    animate.setDuration(vd.d).alpha(0.0f).setListener(new OD(vd, xk0, animate, view)).start();
                }
                vd.i.clear();
                if (z2) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(vd.k);
                    vd.n.add(arrayList);
                    vd.k.clear();
                    LD ld = new LD(vd, arrayList);
                    if (z) {
                        View view2 = ((UD) arrayList.get(0)).f9012a.G;
                        long j = vd.d;
                        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                        view2.postOnAnimationDelayed(ld, j);
                    } else {
                        ld.run();
                    }
                }
                if (z3) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.addAll(vd.l);
                    vd.o.add(arrayList2);
                    vd.l.clear();
                    MD md = new MD(vd, arrayList2);
                    if (z) {
                        View view3 = ((TD) arrayList2.get(0)).f8946a.G;
                        long j2 = vd.d;
                        AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                        view3.postOnAnimationDelayed(md, j2);
                    } else {
                        md.run();
                    }
                }
                if (z4) {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(vd.j);
                    vd.m.add(arrayList3);
                    vd.j.clear();
                    ND nd = new ND(vd, arrayList3);
                    if (z || z2 || z3) {
                        long j3 = 0;
                        long j4 = z ? vd.d : 0;
                        long j5 = z2 ? vd.e : 0;
                        if (z3) {
                            j3 = vd.f;
                        }
                        View view4 = ((XK0) arrayList3.get(0)).G;
                        AtomicInteger atomicInteger3 = AbstractC1920bu1.f9571a;
                        view4.postOnAnimationDelayed(nd, Math.max(j5, j3) + j4);
                    } else {
                        nd.run();
                    }
                }
            }
        }
        this.F.W0 = false;
    }
}
