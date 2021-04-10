package defpackage;

import java.util.Objects;
import org.chromium.base.TraceEvent;

/* renamed from: Wt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1388Wt implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC1388Wt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        Objects.requireNonNull(fuVar);
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.initializeToolbarManager");
        try {
            C4126op1 op1 = fuVar.h1;
            ((AbstractC0246Ea1) op1.f10579a).l(false).n(op1.b);
            fuVar.b1.V.m(fuVar.i1, new View$OnClickListenerC1083Rt(fuVar), new View$OnClickListenerC1144St(fuVar), new View$OnClickListenerC1205Tt(fuVar), null, new C1266Ut(fuVar));
            AbstractC4772sd1.k(fuVar.h0);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
