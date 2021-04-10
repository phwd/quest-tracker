package X;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1zH  reason: invalid class name and case insensitive filesystem */
public final class C13281zH<T> extends AtomicBoolean implements AbstractC12271xB {
    public static final long serialVersionUID = 7514387411091976596L;
    public final AbstractC12721yD<? super T> downstream;
    public final C13261zF<T> parent;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (compareAndSet(false, true)) {
            this.parent.A07(this);
        }
    }

    public C13281zH(AbstractC12721yD<? super T> r1, C13261zF<T> r2) {
        this.downstream = r1;
        this.parent = r2;
    }
}
