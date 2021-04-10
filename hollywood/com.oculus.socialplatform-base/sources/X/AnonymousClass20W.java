package X;

/* renamed from: X.20W  reason: invalid class name */
public final class AnonymousClass20W<T> extends AnonymousClass20O<T> implements AbstractC12721yD<T> {
    public static final long serialVersionUID = 3786543492451018833L;
    public AbstractC12271xB upstream;

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.upstream, r2)) {
            this.upstream = r2;
            this.downstream.A8A(this);
        }
    }

    @Override // X.AnonymousClass20O, X.AbstractC12271xB
    public final void dispose() {
        super.dispose();
        this.upstream.dispose();
    }

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        if ((get() & 54) != 0) {
            AnonymousClass1y3.A01(th);
            return;
        }
        lazySet(2);
        this.downstream.onError(th);
    }

    public AnonymousClass20W(AnonymousClass1yM<? super T> r1) {
        super(r1);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        A00(t);
    }
}
