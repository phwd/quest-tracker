package defpackage;

import java.util.concurrent.Executor;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.TtsPlatformImpl;

/* renamed from: vo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5315vo1 implements Runnable {
    public final TtsPlatformImpl F;

    public RunnableC5315vo1(TtsPlatformImpl ttsPlatformImpl) {
        this.F = ttsPlatformImpl;
    }

    public void run() {
        TtsPlatformImpl ttsPlatformImpl = this.F;
        TraceEvent.l0("TtsPlatformImpl:initialize", (long) ttsPlatformImpl.hashCode());
        C5655xo1 xo1 = new C5655xo1(ttsPlatformImpl);
        Executor executor = AbstractC2032cb.f9616a;
        xo1.f();
        ((ExecutorC1463Ya) executor).execute(xo1.e);
    }
}
