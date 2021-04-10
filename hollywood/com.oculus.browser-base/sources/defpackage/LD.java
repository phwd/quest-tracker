package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* renamed from: LD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LD implements Runnable {
    public final /* synthetic */ ArrayList F;
    public final /* synthetic */ VD G;

    public LD(VD vd, ArrayList arrayList) {
        this.G = vd;
        this.F = arrayList;
    }

    public void run() {
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            UD ud = (UD) it.next();
            VD vd = this.G;
            XK0 xk0 = ud.f9012a;
            int i = ud.b;
            int i2 = ud.c;
            int i3 = ud.d;
            int i4 = ud.e;
            Objects.requireNonNull(vd);
            View view = xk0.G;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (i5 != 0) {
                view.animate().translationX(0.0f);
            }
            if (i6 != 0) {
                view.animate().translationY(0.0f);
            }
            ViewPropertyAnimator animate = view.animate();
            vd.q.add(xk0);
            animate.setDuration(vd.e).setListener(new QD(vd, xk0, i5, view, i6, animate)).start();
        }
        this.F.clear();
        this.G.n.remove(this.F);
    }
}
