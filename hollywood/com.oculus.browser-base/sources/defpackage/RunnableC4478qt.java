package defpackage;

import java.util.Objects;
import org.chromium.base.TraceEvent;

/* renamed from: qt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4478qt implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC4478qt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        Objects.requireNonNull(fuVar);
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.maybeCreateIncognitoTabSnapshotController");
        try {
            if (!AbstractC1575Zv.e().g("enable-incognito-snapshots-in-android-recents")) {
                new R00(fuVar.getWindow(), fuVar.i1, fuVar.l1);
            }
            fuVar.p1 = true;
            fuVar.G1();
            fuVar.l1.s();
            if (!Z00.a()) {
                AbstractC4841t00.a();
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
