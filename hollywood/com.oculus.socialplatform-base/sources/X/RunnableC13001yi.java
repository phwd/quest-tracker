package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yi  reason: invalid class name and case insensitive filesystem */
public final class RunnableC13001yi<T> extends AtomicReference<AbstractC12271xB> implements AbstractC12721yD<T>, AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.single.SingleSubscribeOn$SubscribeOnObserver";
    public static final long serialVersionUID = 7000911171163930287L;
    public final AbstractC12721yD<? super T> downstream;
    public final AbstractC12761yH<? extends T> source;
    public final AnonymousClass1xW task = new AnonymousClass1xW();

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        this.downstream.onSuccess(t);
    }

    public final void run() {
        this.source.AAb(this);
    }

    public RunnableC13001yi(AbstractC12721yD<? super T> r2, AbstractC12761yH<? extends T> r3) {
        this.downstream = r2;
        this.source = r3;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
        this.task.dispose();
    }

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.setOnce(this, r1);
    }
}
