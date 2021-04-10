package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20J  reason: invalid class name */
public final class AnonymousClass20J<T> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final long serialVersionUID = -5417183359794346637L;
    public volatile boolean done;
    public int fusionMode;
    public final AnonymousClass21A<T> parent;
    public final int prefetch;
    public AbstractC13481zf<T> queue;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.parent.A5j(this);
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        this.parent.A5k(this, th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.A5l(this, t);
        } else {
            this.parent.A2g();
        }
    }

    public AnonymousClass20J(AnonymousClass21A<T> r1, int i) {
        this.parent = r1;
        this.prefetch = i;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        AbstractC13481zf<T> r0;
        if (EnumC12511xi.setOnce(this, r3)) {
            if (r3 instanceof AnonymousClass12n) {
                AbstractC13491zg r32 = (AbstractC13491zg) r3;
                int requestFusion = r32.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = r32;
                    this.done = true;
                    this.parent.A5j(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = r32;
                    return;
                }
            }
            int i = -this.prefetch;
            if (i < 0) {
                r0 = new C12451xc<>(-i);
            } else {
                r0 = new C12441xb<>(i);
            }
            this.queue = r0;
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }
}
