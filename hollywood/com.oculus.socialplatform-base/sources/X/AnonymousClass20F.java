package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20F  reason: invalid class name */
public final class AnonymousClass20F<R> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<R> {
    public static final long serialVersionUID = 2620149119579502636L;
    public final AnonymousClass1yM<? super R> downstream;
    public final AnonymousClass20E<?, R> parent;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        AnonymousClass20E<?, R> r1 = this.parent;
        r1.active = false;
        r1.A00();
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        AnonymousClass20E<?, R> r1 = this.parent;
        if (r1.error.A01(th)) {
            if (!r1.tillTheEnd) {
                r1.upstream.dispose();
            }
            r1.active = false;
            r1.A00();
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(R r) {
        this.downstream.onNext(r);
    }

    public AnonymousClass20F(AnonymousClass1yM<? super R> r1, AnonymousClass20E<?, R> r2) {
        this.downstream = r1;
        this.parent = r2;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.replace(this, r1);
    }
}
