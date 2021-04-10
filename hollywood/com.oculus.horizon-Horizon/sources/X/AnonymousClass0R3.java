package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.0R3  reason: invalid class name */
public abstract class AnonymousClass0R3 extends PhantomReference<Object> {
    public AnonymousClass0R3 A00;
    public AnonymousClass0R3 A01;

    public abstract void destruct();

    public AnonymousClass0R3() {
        super(null, AnonymousClass0R6.A02);
    }

    public AnonymousClass0R3(Object obj) {
        super(obj, AnonymousClass0R6.A02);
        AtomicReference<AnonymousClass0R3> atomicReference;
        AnonymousClass0R3 r0;
        AnonymousClass0R5 r2 = AnonymousClass0R6.A01;
        do {
            atomicReference = r2.A00;
            r0 = atomicReference.get();
            this.A00 = r0;
        } while (!atomicReference.compareAndSet(r0, this));
    }
}
