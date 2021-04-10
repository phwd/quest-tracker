package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.1zd  reason: invalid class name and case insensitive filesystem */
public final class C13461zd<T> extends AbstractC13361zS<T> implements AnonymousClass1yM<T> {
    public static final long serialVersionUID = 4109457741734051389L;
    public final AnonymousClass1yM<? super T> downstream;
    public final AbstractC12881yV onFinally;
    public AnonymousClass12n<T> qd;
    public boolean syncFused;
    public AbstractC12271xB upstream;

    private final void A00() {
        if (compareAndSet(0, 1)) {
            try {
                this.onFinally.run();
            } catch (Throwable th) {
                C12261xA.A00(th);
                AnonymousClass1y3.A01(th);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.upstream, r2)) {
            this.upstream = r2;
            if (r2 instanceof AnonymousClass12n) {
                this.qd = (AnonymousClass12n) r2;
            }
            this.downstream.A8A(this);
        }
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        this.qd.clear();
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.upstream.dispose();
        A00();
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        return this.qd.isEmpty();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.downstream.onComplete();
        A00();
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        this.downstream.onError(th);
        A00();
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        this.downstream.onNext(t);
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        T poll = this.qd.poll();
        if (poll == null && this.syncFused) {
            A00();
        }
        return poll;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        AnonymousClass12n<T> r2 = this.qd;
        int i2 = 0;
        if (!(r2 == null || (i & 4) != 0 || (i2 = r2.requestFusion(i)) == 0)) {
            boolean z = true;
            if (i2 != 1) {
                z = false;
            }
            this.syncFused = z;
        }
        return i2;
    }

    public C13461zd(AnonymousClass1yM<? super T> r1, AbstractC12881yV r2) {
        this.downstream = r1;
        this.onFinally = r2;
    }
}
