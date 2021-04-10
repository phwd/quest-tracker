package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: kc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3402kc0 {

    /* renamed from: a  reason: collision with root package name */
    public final View$OnLayoutChangeListenerC4598rc0 f10290a = new View$OnLayoutChangeListenerC4598rc0();
    public C1322Vq0 b = new C1322Vq0();

    public void a() {
        View$OnLayoutChangeListenerC4598rc0 rc0 = this.f10290a;
        if (rc0.c0()) {
            rc0.f0();
            ViewGroup W = rc0.W();
            if (W != null) {
                rc0.X().c(W);
            }
        }
    }

    public void b() {
        View$OnLayoutChangeListenerC4598rc0 rc0 = this.f10290a;
        rc0.F.j(AbstractC4938tc0.f11352a, false);
        if (rc0.c0()) {
            rc0.F.l(AbstractC4938tc0.c, 4);
        }
    }

    public boolean c(View view) {
        View$OnLayoutChangeListenerC4598rc0 rc0 = this.f10290a;
        if (rc0.c0() && !rc0.d0(view)) {
            C4686s50 s50 = rc0.L.f9829a;
            if (s50.F.h(I50.b) && ((R50) s50.H).e() != null) {
                return true;
            }
        }
        return false;
    }
}
