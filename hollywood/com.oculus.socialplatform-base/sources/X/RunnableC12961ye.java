package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1ye  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12961ye extends AtomicReference<AbstractC12271xB> implements AbstractC12941yc, AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.completable.CompletableObserveOn$ObserveOnCompletableObserver";
    public static final long serialVersionUID = 8571289934935992137L;
    public final AbstractC12941yc downstream;
    public Throwable error;
    public final AbstractC12361xL scheduler;

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        EnumC12511xi.replace(this, this.scheduler.A01(this));
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        this.error = th;
        EnumC12511xi.replace(this, this.scheduler.A01(this));
    }

    public final void run() {
        Throwable th = this.error;
        if (th != null) {
            this.error = null;
            this.downstream.onError(th);
            return;
        }
        this.downstream.onComplete();
    }

    public RunnableC12961ye(AbstractC12941yc r1, AbstractC12361xL r2) {
        this.downstream = r1;
        this.scheduler = r2;
    }

    @Override // X.AbstractC12941yc
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
