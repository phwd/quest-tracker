package X;

import java.util.Arrays;

/* renamed from: X.1z0  reason: invalid class name and case insensitive filesystem */
public final class C13181z0<T> implements AbstractC12501xh<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public final AbstractC12501xh<? super T> A01;
    public final AbstractC13191z2<? super Throwable> A02;

    @Override // X.AbstractC12501xh
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            this.A01.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AbstractC12501xh
    public final void onComplete() {
        this.A01.onComplete();
    }

    @Override // X.AbstractC12501xh
    public final void onError(Throwable th) {
        try {
            if (this.A02.test(th)) {
                this.A01.onComplete();
            } else {
                this.A01.onError(th);
            }
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            this.A01.onError(new AnonymousClass1Ox(Arrays.asList(th, th2)));
        }
    }

    @Override // X.AbstractC12501xh
    public final void onSuccess(T t) {
        this.A01.onSuccess(t);
    }

    public C13181z0(AbstractC12501xh<? super T> r1, AbstractC13191z2<? super Throwable> r2) {
        this.A01 = r1;
        this.A02 = r2;
    }
}
