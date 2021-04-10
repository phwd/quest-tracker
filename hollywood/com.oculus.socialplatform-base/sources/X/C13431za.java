package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1za  reason: invalid class name and case insensitive filesystem */
public final class C13431za<T> extends AtomicReference<AbstractC12271xB> implements AbstractC12721yD<T> {
    public static final long serialVersionUID = 3323743579927613702L;
    public final int index;
    public final C13421zZ<T, ?> parent;

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.parent.A00(th, this.index);
    }

    /* JADX DEBUG: Type inference failed for r0v3. Raw type applied. Possible types: ? super java.lang.Object[] */
    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        C13421zZ<T, ?> r2 = this.parent;
        r2.values[this.index] = t;
        if (r2.decrementAndGet() == 0) {
            try {
                Object apply = r2.zipper.apply(r2.values);
                AnonymousClass219.A01(apply, "The zipper returned a null value");
                r2.downstream.onSuccess(apply);
            } catch (Throwable th) {
                C12261xA.A00(th);
                r2.downstream.onError(th);
            }
        }
    }

    public C13431za(C13421zZ<T, ?> r1, int i) {
        this.parent = r1;
        this.index = i;
    }

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.setOnce(this, r1);
    }
}
