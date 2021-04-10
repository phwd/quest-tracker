package X;

/* renamed from: X.1zR  reason: invalid class name and case insensitive filesystem */
public final class C13351zR<T, R> implements AbstractC12721yD<T> {
    public final AbstractC12721yD<? super R> A00;
    public final AbstractC13031yl<? super T, ? extends R> A01;

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
        try {
            Object apply = this.A01.apply(t);
            AnonymousClass219.A01(apply, "The mapper function returned a null value.");
            this.A00.onSuccess(apply);
        } catch (Throwable th) {
            C12261xA.A00(th);
            onError(th);
        }
    }

    public C13351zR(AbstractC12721yD<? super R> r1, AbstractC13031yl<? super T, ? extends R> r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
