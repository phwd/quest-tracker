package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.209  reason: invalid class name */
public final class AnonymousClass209<T, U> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<U> {
    public static final long serialVersionUID = -4606175640614850599L;
    public volatile boolean done;
    public int fusionMode;
    public final long id;
    public final AnonymousClass208<T, U> parent;
    public volatile AbstractC13481zf<U> queue;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.done = true;
        this.parent.A04();
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.parent.errors.A01(th)) {
            AnonymousClass208<T, U> r1 = this.parent;
            if (!r1.delayErrors) {
                r1.A05();
            }
            this.done = true;
            this.parent.A04();
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(U u) {
        if (this.fusionMode == 0) {
            AnonymousClass208<T, U> r2 = this.parent;
            if (r2.get() != 0 || !r2.compareAndSet(0, 1)) {
                AbstractC13481zf r1 = this.queue;
                if (r1 == null) {
                    r1 = new C12451xc(r2.bufferSize);
                    this.queue = r1;
                }
                r1.offer(u);
                if (r2.getAndIncrement() != 0) {
                    return;
                }
            } else {
                r2.downstream.onNext(u);
                if (r2.decrementAndGet() == 0) {
                    return;
                }
            }
            AnonymousClass208.A02(r2);
            return;
        }
        this.parent.A04();
    }

    public AnonymousClass209(AnonymousClass208<T, U> r1, long j) {
        this.id = j;
        this.parent = r1;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        if (EnumC12511xi.setOnce(this, r3) && (r3 instanceof AnonymousClass12n)) {
            AbstractC13491zg r32 = (AbstractC13491zg) r3;
            int requestFusion = r32.requestFusion(7);
            if (requestFusion == 1) {
                this.fusionMode = requestFusion;
                this.queue = r32;
                this.done = true;
                this.parent.A04();
            } else if (requestFusion == 2) {
                this.fusionMode = requestFusion;
                this.queue = r32;
            }
        }
    }
}
