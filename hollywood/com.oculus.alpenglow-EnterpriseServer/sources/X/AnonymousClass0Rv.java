package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.0Rv  reason: invalid class name */
public abstract class AnonymousClass0Rv extends PhantomReference<Object> {
    public AnonymousClass0Rv A00;
    public AnonymousClass0Rv A01;

    public abstract void destruct();

    public AnonymousClass0Rv() {
        super(null, C02050Ry.A02);
    }

    public AnonymousClass0Rv(Object obj) {
        super(obj, C02050Ry.A02);
        AtomicReference<AnonymousClass0Rv> atomicReference;
        AnonymousClass0Rv r0;
        C02040Rx r2 = C02050Ry.A01;
        do {
            atomicReference = r2.A00;
            r0 = atomicReference.get();
            this.A00 = r0;
        } while (!atomicReference.compareAndSet(r0, this));
    }
}
