package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20B  reason: invalid class name */
public final class AnonymousClass20B<U> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<U> {
    public static final long serialVersionUID = -7449079488798789337L;
    public final AnonymousClass1yM<? super U> downstream;
    public final AnonymousClass20C<?, ?> parent;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        AnonymousClass20C<?, ?> r1 = this.parent;
        r1.active = false;
        AnonymousClass20C.A00(r1);
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        this.parent.dispose();
        this.downstream.onError(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(U u) {
        this.downstream.onNext(u);
    }

    public AnonymousClass20B(AnonymousClass1yM<? super U> r1, AnonymousClass20C<?, ?> r2) {
        this.downstream = r1;
        this.parent = r2;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.replace(this, r1);
    }
}
