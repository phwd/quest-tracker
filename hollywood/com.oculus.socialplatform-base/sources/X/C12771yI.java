package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yI  reason: invalid class name and case insensitive filesystem */
public final class C12771yI extends AtomicReference<AbstractC12271xB> implements AnonymousClass292, AbstractC12271xB {
    public static final long serialVersionUID = -2467358622224974244L;
    public final AbstractC12941yc downstream;

    @Override // X.AnonymousClass292
    public final void A9i(AbstractC06511aN r2) {
        EnumC12511xi.set(this, new C12471xe(r2));
    }

    public C12771yI(AbstractC12941yc r1) {
        this.downstream = r1;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AnonymousClass292
    public final void onComplete() {
        AbstractC12271xB r1;
        Object obj = get();
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (obj != r0 && (r1 = (AbstractC12271xB) getAndSet(r0)) != r0) {
            try {
                this.downstream.onComplete();
            } finally {
                if (r1 != null) {
                    r1.dispose();
                }
            }
        }
    }

    public final String toString() {
        return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
    }
}
