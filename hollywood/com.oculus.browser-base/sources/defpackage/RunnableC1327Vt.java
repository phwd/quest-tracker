package defpackage;

import java.util.Objects;
import org.chromium.base.TraceEvent;

/* renamed from: Vt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1327Vt implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC1327Vt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        Objects.requireNonNull(fuVar);
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.refreshSignIn");
        try {
            XQ.b(fuVar);
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
