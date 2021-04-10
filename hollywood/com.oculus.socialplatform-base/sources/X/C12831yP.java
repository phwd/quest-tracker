package X;

import java.util.concurrent.TimeUnit;

/* renamed from: X.1yP  reason: invalid class name and case insensitive filesystem */
public final class C12831yP<T> extends AbstractRunnableC12821yO<T> {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.observable.ObservableSampleTimed$SampleTimedNoLast";
    public static final long serialVersionUID = -7139995637533111443L;

    public final void run() {
        Object andSet = getAndSet(null);
        if (andSet != null) {
            this.downstream.onNext(andSet);
        }
    }

    public C12831yP(AnonymousClass1yM<? super T> r1, long j, TimeUnit timeUnit, AbstractC12361xL r5) {
        super(r1, j, timeUnit, r5);
    }
}
