package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20X  reason: invalid class name */
public final class AnonymousClass20X<T, R> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<R> {
    public static final long serialVersionUID = 3837284832786408377L;
    public final int bufferSize;
    public volatile boolean done;
    public final long index;
    public final AnonymousClass20Y<T, R> parent;
    public volatile AbstractC13481zf<R> queue;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (this.index == this.parent.unique) {
            this.done = true;
            this.parent.A01();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        AnonymousClass20Y<T, R> r5 = this.parent;
        if (this.index != r5.unique || !r5.errors.A01(th)) {
            AnonymousClass1y3.A01(th);
            return;
        }
        if (!r5.delayErrors) {
            r5.upstream.dispose();
        }
        this.done = true;
        r5.A01();
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(R r) {
        if (this.index == this.parent.unique) {
            if (r != null) {
                this.queue.offer(r);
            }
            this.parent.A01();
        }
    }

    public AnonymousClass20X(AnonymousClass20Y<T, R> r1, long j, int i) {
        this.parent = r1;
        this.index = j;
        this.bufferSize = i;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        if (EnumC12511xi.setOnce(this, r3)) {
            if (r3 instanceof AnonymousClass12n) {
                AbstractC13491zg r32 = (AbstractC13491zg) r3;
                int requestFusion = r32.requestFusion(7);
                if (requestFusion == 1) {
                    this.queue = r32;
                    this.done = true;
                    this.parent.A01();
                    return;
                } else if (requestFusion == 2) {
                    this.queue = r32;
                    return;
                }
            }
            this.queue = new C12451xc(this.bufferSize);
        }
    }
}
