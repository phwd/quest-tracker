package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zT  reason: invalid class name and case insensitive filesystem */
public final class C13371zT<R> implements AbstractC12721yD<R> {
    public final AbstractC12721yD<? super R> A00;
    public final AtomicReference<AbstractC12271xB> A01;

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r2) {
        EnumC12511xi.replace(this.A01, r2);
    }

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.A00.onError(th);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(R r) {
        this.A00.onSuccess(r);
    }

    public C13371zT(AtomicReference<AbstractC12271xB> atomicReference, AbstractC12721yD<? super R> r2) {
        this.A01 = atomicReference;
        this.A00 = r2;
    }
}
