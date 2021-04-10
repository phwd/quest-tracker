package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yT  reason: invalid class name and case insensitive filesystem */
public final class C12861yT extends AtomicReference<AbstractC12271xB> implements AbstractC12941yc, AbstractC12271xB, AbstractC12851yS<Throwable> {
    public static final long serialVersionUID = -4361286194466301354L;
    public final AbstractC12881yV onComplete;
    public final AbstractC12851yS<? super Throwable> onError;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC12851yS
    public final void accept(Throwable th) throws Exception {
        AnonymousClass1y3.A01(new AnonymousClass1YA(th));
    }

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        try {
            this.onComplete.run();
        } catch (Throwable th) {
            C12261xA.A00(th);
            AnonymousClass1y3.A01(th);
        }
        lazySet(EnumC12511xi.DISPOSED);
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            AnonymousClass1y3.A01(th2);
        }
        lazySet(EnumC12511xi.DISPOSED);
    }

    public C12861yT(AbstractC12851yS<? super Throwable> r1, AbstractC12881yV r2) {
        this.onError = r1;
        this.onComplete = r2;
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
