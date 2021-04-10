package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: GP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GP0 {

    /* renamed from: a  reason: collision with root package name */
    public final Q31 f8089a;
    public final LP0 b;
    public NP0 c;
    public ZH0 d;

    public GP0(Context context, EP0 ep0, ViewGroup viewGroup, int i) {
        this.b = new LP0(new BP0(this), ep0);
        this.f8089a = new CP0(this, context, viewGroup, i);
    }

    public void a(UH0 uh0) {
        LP0 lp0 = this.b;
        if (lp0.f != null) {
            lp0.a(false);
        }
        ZH0 zh0 = this.d;
        if (zh0 != null) {
            zh0.b();
        }
        NP0 np0 = (NP0) this.f8089a.get();
        this.c = np0;
        this.d = ZH0.a(uh0, np0, new DP0());
        LP0 lp02 = this.b;
        lp02.f = uh0;
        lp02.i = false;
        lp02.c(0.0f);
        if (lp02.c == null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            lp02.c = ofFloat;
            ofFloat.setDuration((long) 300);
            lp02.c.setInterpolator(animation.InterpolatorC5286vf.g);
            lp02.c.addListener(new JP0(lp02));
            lp02.c.addUpdateListener(new HP0(lp02));
        }
        Collection a2 = uh0.a();
        TH0 th0 = MP0.j;
        if (((ArrayList) a2).contains(th0)) {
            lp02.h = uh0.g(th0) != null;
        }
        lp02.c.setFloatValues(lp02.f.e(MP0.g), 1.0f);
        lp02.b(lp02.c);
    }
}
