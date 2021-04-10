package defpackage;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: bb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RejectedExecutionHandlerC1861bb implements RejectedExecutionHandler {
    public RejectedExecutionHandlerC1861bb(CallableC1524Za za) {
    }

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        ((ExecutorC1463Ya) AbstractC2032cb.f9616a).execute(runnable);
    }
}
