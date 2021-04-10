package X;

/* renamed from: X.1zK  reason: invalid class name and case insensitive filesystem */
public final class C13291zK implements AbstractC12721yD<T> {
    public final AbstractC12721yD<? super T> A00;
    public final /* synthetic */ AnonymousClass1zJ A01;

    public C13291zK(AnonymousClass1zJ r1, AbstractC12721yD<? super T> r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

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
            this.A01.A00.accept(t);
            this.A00.onSuccess(t);
        } catch (Throwable th) {
            C12261xA.A00(th);
            this.A00.onError(th);
        }
    }
}
