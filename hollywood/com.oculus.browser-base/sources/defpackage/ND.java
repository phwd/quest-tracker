package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* renamed from: ND  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ND implements Runnable {
    public final /* synthetic */ ArrayList F;
    public final /* synthetic */ VD G;

    public ND(VD vd, ArrayList arrayList) {
        this.G = vd;
        this.F = arrayList;
    }

    public void run() {
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            XK0 xk0 = (XK0) it.next();
            VD vd = this.G;
            Objects.requireNonNull(vd);
            View view = xk0.G;
            ViewPropertyAnimator animate = view.animate();
            vd.p.add(xk0);
            animate.alpha(1.0f).setDuration(vd.c).setListener(new PD(vd, xk0, view, animate)).start();
        }
        this.F.clear();
        this.G.m.remove(this.F);
    }
}
