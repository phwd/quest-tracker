package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21N  reason: invalid class name */
public final class AnonymousClass21N extends AtomicReference<AbstractC12271xB> implements Runnable, AbstractC12851yS<AbstractC12271xB> {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.observable.ObservableRefCount$RefConnection";
    public static final long serialVersionUID = -4552101107598366241L;
    public boolean connected;
    public boolean disconnectedEarly;
    public final AnonymousClass21H<?> parent;
    public long subscriberCount;
    public final AbstractC12271xB timer;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC12851yS
    public final void accept(AbstractC12271xB r4) throws Exception {
        AbstractC12271xB r42 = r4;
        EnumC12511xi.replace(this, r42);
        synchronized (this.parent) {
            if (this.disconnectedEarly) {
                ((AnonymousClass21I) this.parent.A03).A03.compareAndSet(r42, null);
            }
        }
    }

    public final void run() {
        this.parent.A0L(this);
    }

    public AnonymousClass21N(AnonymousClass21H<?> r1) {
        this.parent = r1;
    }
}
