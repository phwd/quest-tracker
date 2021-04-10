package X;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yO  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractRunnableC12821yO<T> extends AtomicReference<T> implements AnonymousClass1yM<T>, AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.observable.ObservableSampleTimed$SampleTimedObserver";
    public static final long serialVersionUID = -3517602651313910099L;
    public final AnonymousClass1yM<? super T> downstream;
    public final long period;
    public final AbstractC12361xL scheduler;
    public final AtomicReference<AbstractC12271xB> timer = new AtomicReference<>();
    public final TimeUnit unit;
    public AbstractC12271xB upstream;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r9) {
        if (EnumC12511xi.validate(this.upstream, r9)) {
            this.upstream = r9;
            this.downstream.A8A(this);
            AbstractC12361xL r1 = this.scheduler;
            long j = this.period;
            EnumC12511xi.replace(this.timer, r1.A02(this, j, j, this.unit));
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this.timer);
        this.upstream.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        EnumC12511xi.dispose(this.timer);
        this.downstream.onComplete();
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        EnumC12511xi.dispose(this.timer);
        this.downstream.onError(th);
    }

    public AbstractRunnableC12821yO(AnonymousClass1yM<? super T> r2, long j, TimeUnit timeUnit, AbstractC12361xL r6) {
        this.downstream = r2;
        this.period = j;
        this.unit = timeUnit;
        this.scheduler = r6;
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        lazySet(t);
    }
}
