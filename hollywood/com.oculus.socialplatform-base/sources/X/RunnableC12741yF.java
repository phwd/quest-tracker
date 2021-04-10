package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yF  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12741yF<T> extends AtomicReference<AbstractC12271xB> implements AbstractC12721yD<T>, AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.single.SingleObserveOn$ObserveOnSingleObserver";
    public static final long serialVersionUID = 3528003840217436037L;
    public final AbstractC12721yD<? super T> downstream;
    public Throwable error;
    public final AbstractC12361xL scheduler;
    public T value;

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.error = th;
        EnumC12511xi.replace(this, this.scheduler.A01(this));
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        this.value = t;
        EnumC12511xi.replace(this, this.scheduler.A01(this));
    }

    public final void run() {
        Throwable th = this.error;
        if (th != null) {
            this.downstream.onError(th);
        } else {
            this.downstream.onSuccess(this.value);
        }
    }

    public RunnableC12741yF(AbstractC12721yD<? super T> r1, AbstractC12361xL r2) {
        this.downstream = r1;
        this.scheduler = r2;
    }

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.setOnce(this, r2)) {
            this.downstream.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }
}
