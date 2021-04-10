package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yC  reason: invalid class name and case insensitive filesystem */
public final class C12711yC<T> extends AtomicReference<C12701yB<T>> implements AbstractC12271xB {
    public static final long serialVersionUID = -7650903191002190468L;
    public final AbstractC12721yD<? super T> downstream;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        C12701yB r0 = (C12701yB) getAndSet(null);
        if (r0 != null) {
            r0.A07(this);
        }
    }

    public C12711yC(AbstractC12721yD<? super T> r1, C12701yB<T> r2) {
        this.downstream = r1;
        lazySet(r2);
    }
}
