package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1z8  reason: invalid class name */
public final class AnonymousClass1z8<T> extends AtomicReference<AbstractC12271xB> implements AbstractC12721yD<T>, AbstractC12941yc, AbstractC12271xB {
    public static final long serialVersionUID = -2177128922851101253L;
    public final AbstractC12941yc downstream;
    public final AbstractC13031yl<? super T, ? extends AbstractC12981yg> mapper;

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // X.AbstractC12941yc, X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        try {
            Object apply = this.mapper.apply(t);
            AnonymousClass219.A01(apply, "The mapper returned a null CompletableSource");
            AbstractC12981yg r1 = (AbstractC12981yg) apply;
            if (!EnumC12511xi.isDisposed((AbstractC12271xB) get())) {
                r1.AAZ(this);
            }
        } catch (Throwable th) {
            C12261xA.A00(th);
            onError(th);
        }
    }

    public AnonymousClass1z8(AbstractC12941yc r1, AbstractC13031yl<? super T, ? extends AbstractC12981yg> r2) {
        this.downstream = r1;
        this.mapper = r2;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AbstractC12941yc, X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.replace(this, r1);
    }
}
