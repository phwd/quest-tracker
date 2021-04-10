package X;

/* renamed from: X.1zw  reason: invalid class name and case insensitive filesystem */
public final class C13651zw<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public final AbstractC12941yc A01;

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        this.A00 = r2;
        this.A01.A8A(this);
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.A01.onComplete();
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        this.A01.onError(th);
    }

    public C13651zw(AbstractC12941yc r1) {
        this.A01 = r1;
    }
}
