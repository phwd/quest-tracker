package X;

/* renamed from: X.20l  reason: invalid class name and case insensitive filesystem */
public final class C137920l<T, U> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public boolean A01;
    public final AbstractC12721yD<? super U> A02;
    public final AbstractC140121h<? super U, ? super T> A03;
    public final U A04;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            this.A02.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A01) {
            this.A01 = true;
            this.A02.onSuccess(this.A04);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.A01) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.A01 = true;
        this.A02.onError(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.A01) {
            try {
                this.A03.accept(this.A04, t);
            } catch (Throwable th) {
                this.A00.dispose();
                onError(th);
            }
        }
    }

    public C137920l(AbstractC12721yD<? super U> r1, U u, AbstractC140121h<? super U, ? super T> r3) {
        this.A02 = r1;
        this.A03 = r3;
        this.A04 = u;
    }
}
