package defpackage;

import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* renamed from: cO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2006cO0 implements Runnable {
    public final C2347eO0 F;
    public final String G;

    public RunnableC2006cO0(C2347eO0 eo0, String str) {
        this.F = eo0;
        this.G = str;
    }

    public void run() {
        C2347eO0 eo0 = this.F;
        String str = this.G;
        Objects.requireNonNull(eo0);
        if (str == null) {
            PostTask.c(Zo1.f9374a, new RunnableC2177dO0(eo0));
            return;
        }
        C1655aO0 ao0 = new C1655aO0(eo0.b, eo0.f9851a, str, eo0.d);
        Executor executor = AbstractC2032cb.f9616a;
        ao0.f();
        ((ExecutorC1463Ya) executor).execute(ao0.e);
    }
}
