package X;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.21G  reason: invalid class name */
public final class AnonymousClass21G<T> extends AtomicBoolean implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final long serialVersionUID = -7419642935409022375L;
    public final AnonymousClass21N connection;
    public final AnonymousClass1yM<? super T> downstream;
    public final AnonymousClass21H<T> parent;
    public AbstractC12271xB upstream;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (compareAndSet(false, true)) {
            this.parent.A0K(this.connection);
            this.downstream.onComplete();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (compareAndSet(false, true)) {
            this.parent.A0K(this.connection);
            this.downstream.onError(th);
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.upstream, r2)) {
            this.upstream = r2;
            this.downstream.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.upstream.dispose();
        if (compareAndSet(false, true)) {
            AnonymousClass21H<T> r6 = this.parent;
            AnonymousClass21N r5 = this.connection;
            synchronized (r6) {
                AnonymousClass21N r0 = r6.A00;
                if (r0 != null && r0 == r5) {
                    long j = r5.subscriberCount - 1;
                    r5.subscriberCount = j;
                    if (j == 0 && r5.connected) {
                        r6.A0L(r5);
                    }
                }
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        this.downstream.onNext(t);
    }

    public AnonymousClass21G(AnonymousClass1yM<? super T> r1, AnonymousClass21H<T> r2, AnonymousClass21N r3) {
        this.downstream = r1;
        this.parent = r2;
        this.connection = r3;
    }
}
