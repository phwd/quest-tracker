package X;

/* renamed from: X.21H  reason: invalid class name */
public final class AnonymousClass21H<T> extends AbstractC136820a<T> {
    public AnonymousClass21N A00;
    public final int A01 = 1;
    public final AbstractC12361xL A02;
    public final AnonymousClass21O<T> A03;

    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.util.concurrent.atomic.AtomicReference<X.21J<T>> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A0K(AnonymousClass21N r6) {
        synchronized (this) {
            AnonymousClass21N r0 = this.A00;
            if (r0 != null && r0 == r6) {
                this.A00 = null;
                AbstractC12271xB r02 = r6.timer;
                if (r02 != null) {
                    r02.dispose();
                }
            }
            long j = r6.subscriberCount - 1;
            r6.subscriberCount = j;
            if (j == 0) {
                AnonymousClass21O<T> r1 = this.A03;
                if (r1 instanceof AbstractC12271xB) {
                    ((AbstractC12271xB) r1).dispose();
                } else if (r1 instanceof AnonymousClass21I) {
                    ((AnonymousClass21I) r1).A03.compareAndSet((AbstractC12271xB) r6.get(), null);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.util.concurrent.atomic.AtomicReference<X.21J<T>> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A0L(AnonymousClass21N r6) {
        synchronized (this) {
            if (r6.subscriberCount == 0 && r6 == this.A00) {
                this.A00 = null;
                AbstractC12271xB r2 = (AbstractC12271xB) r6.get();
                EnumC12511xi.dispose(r6);
                AnonymousClass21O<T> r1 = this.A03;
                if (r1 instanceof AbstractC12271xB) {
                    ((AbstractC12271xB) r1).dispose();
                } else if (r1 instanceof AnonymousClass21I) {
                    if (r2 == null) {
                        r6.disconnectedEarly = true;
                    } else {
                        ((AnonymousClass21I) r1).A03.compareAndSet(r2, null);
                    }
                }
            }
        }
    }

    public AnonymousClass21H(AnonymousClass21O<T> r3) {
        AbstractC12361xL r1 = C12581xp.A04;
        this.A03 = r3;
        this.A02 = r1;
    }
}
