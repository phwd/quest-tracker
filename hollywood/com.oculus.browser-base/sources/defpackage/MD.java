package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* renamed from: MD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MD implements Runnable {
    public final /* synthetic */ ArrayList F;
    public final /* synthetic */ VD G;

    public MD(VD vd, ArrayList arrayList) {
        this.G = vd;
        this.F = arrayList;
    }

    public void run() {
        View view;
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            TD td = (TD) it.next();
            VD vd = this.G;
            Objects.requireNonNull(vd);
            XK0 xk0 = td.f8946a;
            View view2 = null;
            if (xk0 == null) {
                view = null;
            } else {
                view = xk0.G;
            }
            XK0 xk02 = td.b;
            if (xk02 != null) {
                view2 = xk02.G;
            }
            if (view != null) {
                ViewPropertyAnimator duration = view.animate().setDuration(vd.f);
                vd.s.add(td.f8946a);
                duration.translationX((float) (td.e - td.c));
                duration.translationY((float) (td.f - td.d));
                duration.alpha(0.0f).setListener(new RD(vd, td, duration, view)).start();
            }
            if (view2 != null) {
                ViewPropertyAnimator animate = view2.animate();
                vd.s.add(td.b);
                animate.translationX(0.0f).translationY(0.0f).setDuration(vd.f).alpha(1.0f).setListener(new SD(vd, td, animate, view2)).start();
            }
        }
        this.F.clear();
        this.G.o.remove(this.F);
    }
}
