package X;

import java.util.concurrent.CountDownLatch;

/* renamed from: X.1yh  reason: invalid class name and case insensitive filesystem */
public final class C12991yh<T> extends CountDownLatch implements AbstractC12721yD<T>, AbstractC12941yc, AbstractC12501xh<T> {
    public AbstractC12271xB A00;
    public T A01;
    public Throwable A02;
    public volatile boolean A03;

    public C12991yh() {
        super(1);
    }

    @Override // X.AbstractC12501xh, X.AbstractC12941yc, X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r2) {
        this.A00 = r2;
        if (this.A03) {
            r2.dispose();
        }
    }

    @Override // X.AbstractC12501xh, X.AbstractC12941yc, X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.A02 = th;
        countDown();
    }

    @Override // X.AbstractC12501xh, X.AbstractC12721yD
    public final void onSuccess(T t) {
        this.A01 = t;
        countDown();
    }

    @Override // X.AbstractC12501xh, X.AbstractC12941yc
    public final void onComplete() {
        countDown();
    }
}
