package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xW  reason: invalid class name */
public final class AnonymousClass1xW extends AtomicReference<AbstractC12271xB> implements AbstractC12271xB {
    public static final long serialVersionUID = -754898800686245608L;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    public AnonymousClass1xW() {
    }

    public AnonymousClass1xW(AbstractC12271xB r1) {
        lazySet(r1);
    }
}
