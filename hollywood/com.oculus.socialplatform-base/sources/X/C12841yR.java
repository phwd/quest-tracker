package X;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yR  reason: invalid class name and case insensitive filesystem */
public final class C12841yR<T> extends AtomicReference<AbstractC12271xB> implements AbstractC12721yD<T>, AbstractC12271xB {
    public static final long serialVersionUID = -7012088219455310787L;
    public final AbstractC12851yS<? super Throwable> onError;
    public final AbstractC12851yS<? super T> onSuccess;

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        lazySet(EnumC12511xi.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            AnonymousClass1y3.A01(new AnonymousClass1Ox(Arrays.asList(th, th2)));
        }
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        lazySet(EnumC12511xi.DISPOSED);
        try {
            this.onSuccess.accept(t);
        } catch (Throwable th) {
            C12261xA.A00(th);
            AnonymousClass1y3.A01(th);
        }
    }

    public C12841yR(AbstractC12851yS<? super T> r1, AbstractC12851yS<? super Throwable> r2) {
        this.onSuccess = r1;
        this.onError = r2;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.setOnce(this, r1);
    }
}
