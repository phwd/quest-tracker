package X;

/* renamed from: X.1zu  reason: invalid class name and case insensitive filesystem */
public final class C13631zu<T> implements AbstractC12721yD<T> {
    public final AbstractC12941yc A00;

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r2) {
        this.A00.A8A(r2);
    }

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.A00.onError(th);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        this.A00.onComplete();
    }

    public C13631zu(AbstractC12941yc r1) {
        this.A00 = r1;
    }
}
