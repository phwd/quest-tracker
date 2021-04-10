package X;

/* renamed from: X.214  reason: invalid class name */
public final class AnonymousClass214<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public long A00;
    public AbstractC12271xB A01;
    public boolean A02;
    public final AnonymousClass1yM<? super T> A03;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r6) {
        if (EnumC12511xi.validate(this.A01, r6)) {
            this.A01 = r6;
            if (this.A00 == 0) {
                this.A02 = true;
                r6.dispose();
                AnonymousClass1z1.complete(this.A03);
                return;
            }
            this.A03.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A01.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A02) {
            this.A02 = true;
            this.A01.dispose();
            this.A03.onComplete();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.A02) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.A02 = true;
        this.A01.dispose();
        this.A03.onError(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.A02) {
            long j = this.A00;
            long j2 = j - 1;
            this.A00 = j2;
            if (j > 0) {
                boolean z = false;
                if (j2 == 0) {
                    z = true;
                }
                this.A03.onNext(t);
                if (z) {
                    onComplete();
                }
            }
        }
    }

    public AnonymousClass214(AnonymousClass1yM<? super T> r1, long j) {
        this.A03 = r1;
        this.A00 = j;
    }
}
