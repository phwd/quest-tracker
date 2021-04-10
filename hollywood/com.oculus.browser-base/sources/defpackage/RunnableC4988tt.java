package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.TraceEvent;

/* renamed from: tt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4988tt implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC4988tt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        Objects.requireNonNull(fuVar);
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.initJourneyManager");
        try {
            if (N.M09VlOh_("TabEngagementReportingAndroid")) {
                new I40(fuVar.P(), fuVar.Y, fuVar.G1, new C2511fL());
            }
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
