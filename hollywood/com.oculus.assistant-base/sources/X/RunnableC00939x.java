package X;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.9x  reason: invalid class name and case insensitive filesystem */
public final class RunnableC00939x implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.latency.ResponseInteractionLatencyLoggerAdapter$1";
    public final /* synthetic */ C0785hJ A00;

    public RunnableC00939x(C0785hJ hJVar) {
        this.A00 = hJVar;
    }

    public final void run() {
        AtomicLong atomicLong = this.A00.A01;
        if (atomicLong.get() != 0) {
            long andSet = atomicLong.getAndSet(0);
            hG hGVar = C00879r.A00;
            hGVar.A00.markerPoint(hGVar.A02, EnumC00909u.DELIVERING_RESPONSE.getPointName(), andSet, TimeUnit.MILLISECONDS);
            hGVar.A09(2, andSet);
        }
    }
}
