package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zV  reason: invalid class name */
public final class AnonymousClass1zV<T, R> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<R>, AbstractC12721yD<T>, AbstractC12271xB {
    public static final long serialVersionUID = -8948264376121066672L;
    public final AnonymousClass1yM<? super R> downstream;
    public final AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> mapper;

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // X.AnonymousClass1yM, X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(R r) {
        this.downstream.onNext(r);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        try {
            Object apply = this.mapper.apply(t);
            AnonymousClass219.A01(apply, "The mapper returned a null Publisher");
            ((AbstractC13121yu) apply).AAa(this);
        } catch (Throwable th) {
            C12261xA.A00(th);
            this.downstream.onError(th);
        }
    }

    public AnonymousClass1zV(AnonymousClass1yM<? super R> r1, AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> r2) {
        this.downstream = r1;
        this.mapper = r2;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AnonymousClass1yM, X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.replace(this, r1);
    }
}
