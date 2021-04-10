package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: Qe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0987Qe implements Runnable {
    public final C1109Se F;

    public RunnableC0987Qe(C1109Se se) {
        this.F = se;
    }

    public void run() {
        C1109Se se = this.F;
        Objects.requireNonNull(se);
        Object obj = ThreadUtils.f10596a;
        if (!se.d) {
            se.d = true;
            AbstractC1220Ua0.f("BkgrdTaskBR", "Task execution failed. Task timed out.", new Object[0]);
            C5116uf.f().j(se.b.f9623a);
            if (se.c.a(se.f8903a, se.b)) {
                C5116uf.f().h();
                se.c.c(se.f8903a);
            }
        }
    }
}
