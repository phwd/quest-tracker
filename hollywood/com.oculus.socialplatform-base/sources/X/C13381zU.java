package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zU  reason: invalid class name and case insensitive filesystem */
public final class C13381zU<T, R> extends AtomicReference<AbstractC12271xB> implements AbstractC12721yD<T>, AbstractC12271xB {
    public static final long serialVersionUID = 3258103020495908596L;
    public final AbstractC12721yD<? super R> downstream;
    public final AbstractC13031yl<? super T, ? extends AbstractC12761yH<? extends R>> mapper;

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        try {
            Object apply = this.mapper.apply(t);
            AnonymousClass219.A01(apply, "The single returned by the mapper is null");
            AbstractC12761yH r2 = (AbstractC12761yH) apply;
            if (!EnumC12511xi.isDisposed((AbstractC12271xB) get())) {
                r2.AAb(new C13371zT(this, this.downstream));
            }
        } catch (Throwable th) {
            C12261xA.A00(th);
            this.downstream.onError(th);
        }
    }

    public C13381zU(AbstractC12721yD<? super R> r1, AbstractC13031yl<? super T, ? extends AbstractC12761yH<? extends R>> r2) {
        this.downstream = r1;
        this.mapper = r2;
    }

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.setOnce(this, r2)) {
            this.downstream.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }
}
