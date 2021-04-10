package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xe  reason: invalid class name and case insensitive filesystem */
public final class C12471xe extends AtomicReference<AbstractC06511aN> implements AbstractC12271xB {
    public static final long serialVersionUID = 5718521705281392066L;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        AbstractC06511aN r0;
        if (get() != null && (r0 = (AbstractC06511aN) getAndSet(null)) != null) {
            try {
                r0.cancel();
            } catch (Exception e) {
                C12261xA.A00(e);
                AnonymousClass1y3.A01(e);
            }
        }
    }

    public C12471xe(AbstractC06511aN r1) {
        super(r1);
    }
}
