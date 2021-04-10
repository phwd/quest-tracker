package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1o9  reason: invalid class name */
public final class AnonymousClass1o9 {
    public final Executor A00;
    public final Executor A01;
    public final Executor A02 = Executors.newFixedThreadPool(2, new AnonymousClass1o7("FrescoIoBoundExecutor"));
    public final Executor A03;
    public final ScheduledExecutorService A04;

    public AnonymousClass1o9(int i) {
        this.A01 = Executors.newFixedThreadPool(i, new AnonymousClass1o7("FrescoDecodeExecutor"));
        this.A00 = Executors.newFixedThreadPool(i, new AnonymousClass1o7("FrescoBackgroundExecutor"));
        this.A04 = Executors.newScheduledThreadPool(i, new AnonymousClass1o7("FrescoBackgroundExecutor"));
        this.A03 = Executors.newFixedThreadPool(1, new AnonymousClass1o7("FrescoLightWeightBackgroundExecutor"));
    }
}
