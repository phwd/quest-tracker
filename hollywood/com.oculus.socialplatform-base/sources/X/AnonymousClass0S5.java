package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.0S5  reason: invalid class name */
public abstract class AnonymousClass0S5 extends PhantomReference<Object> {
    public AnonymousClass0S5 A00;
    public AnonymousClass0S5 A01;

    public abstract void destruct();

    public AnonymousClass0S5() {
        super(null, AnonymousClass0S8.A02);
    }

    public AnonymousClass0S5(Object obj) {
        super(obj, AnonymousClass0S8.A02);
        AtomicReference<AnonymousClass0S5> atomicReference;
        AnonymousClass0S5 r0;
        AnonymousClass0S7 r2 = AnonymousClass0S8.A01;
        do {
            atomicReference = r2.A00;
            r0 = atomicReference.get();
            this.A00 = r0;
        } while (!atomicReference.compareAndSet(r0, this));
    }
}
