package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.util.Objects;

/* renamed from: Sr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Sr1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Vr1 f8921a;
    public final Tm1 b;
    public final XY c;

    public Sr1(Vr1 vr1, Tm1 tm1, XY xy) {
        this.f8921a = vr1;
        this.b = tm1;
        this.c = xy;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Vr1 vr1 = this.f8921a;
        Tm1 tm1 = this.b;
        XY xy = this.c;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(vr1);
        View view = xy.g;
        if (!vr1.f9111a.isFinishing() && !vr1.f9111a.isDestroyed() && view != null && !vr1.f9111a.isFinishing() && !vr1.f9111a.isDestroyed()) {
            String str = xy.f9215a;
            if (str == null || tm1.shouldTriggerHelpUI(str)) {
                String str2 = xy.b;
                String str3 = xy.c;
                ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1 = xy.l;
                if (fv1 == null) {
                    fv1 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view);
                }
                Activity activity = vr1.f9111a;
                boolean d = C0283Ep.h().d();
                C1175Tf1 tf1 = new C1175Tf1((Context) activity, view, str2, str3, true, (C4390qK0) fv1, d);
                tf1.e(xy.f);
                tf1.I.P.b(new Tr1(vr1, str, tm1, xy, view));
                long j = xy.k;
                if (!d) {
                    tf1.M = j;
                    tf1.H.removeCallbacks(tf1.K);
                    if (tf1.I.c()) {
                        long j2 = tf1.M;
                        if (j2 != 0) {
                            tf1.H.postDelayed(tf1.K, j2);
                        }
                    }
                }
                if (xy.e) {
                    if (xy.d) {
                        AbstractC3628lu1.c(view);
                    } else {
                        AbstractC3628lu1.d(view);
                    }
                }
                fv1.I.set(xy.j);
                fv1.d();
                tf1.f();
                xy.i.run();
            }
        }
    }
}
