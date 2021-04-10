package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yJ  reason: invalid class name and case insensitive filesystem */
public final class C12781yJ extends AtomicReference<AbstractC12271xB> implements AbstractC12941yc, AbstractC12271xB {
    public static final long serialVersionUID = -7545121636549663526L;

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        lazySet(EnumC12511xi.DISPOSED);
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        lazySet(EnumC12511xi.DISPOSED);
        AnonymousClass1y3.A01(new AnonymousClass1YA(th));
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AbstractC12941yc
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.setOnce(this, r1);
    }
}
