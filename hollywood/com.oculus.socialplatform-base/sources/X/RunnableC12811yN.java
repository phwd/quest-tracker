package X;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yN  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12811yN<T> extends AtomicInteger implements AnonymousClass1yM<T>, AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.observable.ObservableThrottleLatest$ThrottleLatestObserver";
    public static final long serialVersionUID = -8296689127439125014L;
    public volatile boolean cancelled;
    public volatile boolean done;
    public final AnonymousClass1yM<? super T> downstream;
    public final boolean emitLast = false;
    public Throwable error;
    public final AtomicReference<T> latest = new AtomicReference<>();
    public final long timeout;
    public volatile boolean timerFired;
    public boolean timerRunning;
    public final TimeUnit unit;
    public AbstractC12271xB upstream;
    public final AbstractC12411xQ worker;

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TT;>;JLjava/util/concurrent/TimeUnit;LX/1xQ;Z)V */
    public RunnableC12811yN(AnonymousClass1yM r2, long j, TimeUnit timeUnit, AbstractC12411xQ r6) {
        this.downstream = r2;
        this.timeout = j;
        this.unit = timeUnit;
        this.worker = r6;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        this.worker.dispose();
        if (getAndIncrement() == 0) {
            this.latest.lazySet(null);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.done = true;
        A00();
    }

    public final void run() {
        this.timerFired = true;
        A00();
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.upstream, r2)) {
            this.upstream = r2;
            this.downstream.A8A(this);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        this.error = th;
        this.done = true;
        A00();
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        this.latest.set(t);
        A00();
    }

    private final void A00() {
        if (getAndIncrement() == 0) {
            AtomicReference<T> atomicReference = this.latest;
            AnonymousClass1yM<? super T> r4 = this.downstream;
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                if (!z || this.error == null) {
                    boolean z2 = false;
                    if (atomicReference.get() == null) {
                        z2 = true;
                    }
                    if (z) {
                        T andSet = atomicReference.getAndSet(null);
                        if (!z2 && this.emitLast) {
                            r4.onNext(andSet);
                        }
                        r4.onComplete();
                    } else {
                        if (z2) {
                            if (this.timerFired) {
                                this.timerRunning = false;
                                this.timerFired = false;
                            }
                        } else if (!this.timerRunning || this.timerFired) {
                            r4.onNext(atomicReference.getAndSet(null));
                            this.timerFired = false;
                            this.timerRunning = true;
                            this.worker.A02(this, this.timeout, this.unit);
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                } else {
                    atomicReference.lazySet(null);
                    r4.onError(this.error);
                }
                this.worker.dispose();
                return;
            }
            atomicReference.lazySet(null);
        }
    }
}
