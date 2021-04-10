package X;

/* renamed from: X.20f  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC137320f<T, R> implements AnonymousClass1yM<T>, AnonymousClass12n<R> {
    public AbstractC12271xB A00;
    public int A01;
    public AnonymousClass12n<T> A02;
    public boolean A03;
    public final AnonymousClass1yM<? super R> A04;

    public final int A00(int i) {
        AnonymousClass12n<T> r1 = this.A02;
        if (r1 == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = r1.requestFusion(i);
        if (requestFusion != 0) {
            this.A01 = requestFusion;
        }
        return requestFusion;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            if (r2 instanceof AnonymousClass12n) {
                this.A02 = (AnonymousClass12n) r2;
            }
            this.A04.A8A(this);
        }
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        this.A02.clear();
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        return this.A02.isEmpty();
    }

    @Override // X.AbstractC13481zf
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A03) {
            this.A03 = true;
            this.A04.onComplete();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.A03) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.A03 = true;
        this.A04.onError(th);
    }

    public AbstractC137320f(AnonymousClass1yM<? super R> r1) {
        this.A04 = r1;
    }
}
