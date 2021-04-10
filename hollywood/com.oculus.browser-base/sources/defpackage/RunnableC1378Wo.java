package defpackage;

import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* renamed from: Wo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1378Wo implements Runnable {
    public final C1732ap F;
    public final String G;

    public RunnableC1378Wo(C1732ap apVar, String str) {
        this.F = apVar;
        this.G = str;
    }

    public void run() {
        Objects.requireNonNull((ContentChildProcessServiceDelegate) this.F.d);
        C2474f80 f80 = C2474f80.f9900a;
        synchronized (f80.j) {
            if (!f80.f) {
                f80.d = true;
                f80.e = false;
                f80.f = true;
            }
            if (!f80.d) {
                TraceEvent j0 = TraceEvent.j0("LibraryLoader.preloadAlreadyLocked");
                if (j0 != null) {
                    j0.close();
                }
            }
        }
    }
}
