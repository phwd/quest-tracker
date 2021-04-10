package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0md  reason: invalid class name and case insensitive filesystem */
public final class C03440md {
    public final Executor A00;
    public final Executor A01;
    public final Executor A02 = Executors.newFixedThreadPool(2, new AnonymousClass0PU("FrescoIoBoundExecutor"));
    public final Executor A03;
    public final ScheduledExecutorService A04;

    public C03440md(int i) {
        this.A01 = Executors.newFixedThreadPool(i, new AnonymousClass0PU("FrescoDecodeExecutor"));
        this.A00 = Executors.newFixedThreadPool(i, new AnonymousClass0PU("FrescoBackgroundExecutor"));
        this.A04 = Executors.newScheduledThreadPool(i, new AnonymousClass0PU("FrescoBackgroundExecutor"));
        this.A03 = Executors.newFixedThreadPool(1, new AnonymousClass0PU("FrescoLightWeightBackgroundExecutor"));
    }
}
