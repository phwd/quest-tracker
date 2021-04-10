package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: Re  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1048Re implements Runnable {
    public final C1109Se F;
    public final boolean G;

    public RunnableC1048Re(C1109Se se, boolean z) {
        this.F = se;
        this.G = z;
    }

    public void run() {
        C1109Se se = this.F;
        boolean z = this.G;
        Objects.requireNonNull(se);
        Object obj = ThreadUtils.f10596a;
        if (!se.d) {
            se.d = true;
            if (z) {
                C5116uf.f().h();
                se.c.c(se.f8903a);
            }
        }
    }
}
