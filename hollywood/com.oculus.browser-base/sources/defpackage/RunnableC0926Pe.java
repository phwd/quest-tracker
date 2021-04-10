package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: Pe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0926Pe implements Runnable {
    public final C1109Se F;

    public RunnableC0926Pe(C1109Se se) {
        this.F = se;
    }

    public void run() {
        C1109Se se = this.F;
        Objects.requireNonNull(se);
        Object obj = ThreadUtils.f10596a;
        boolean b = se.c.b(se.f8903a, se.b, se);
        C5116uf.f().i(se.b.f9623a);
        if (b) {
            PostTask.b(Zo1.b, new RunnableC0987Qe(se), 162000);
        }
    }
}
