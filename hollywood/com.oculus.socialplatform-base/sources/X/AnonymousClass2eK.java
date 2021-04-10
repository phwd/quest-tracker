package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.2eK  reason: invalid class name */
public abstract class AnonymousClass2eK extends PhantomReference<Object> {
    public AnonymousClass2eK A00;
    public AnonymousClass2eK A01;

    public abstract void destruct();

    public AnonymousClass2eK() {
        super(null, AnonymousClass2eM.A02);
    }

    public AnonymousClass2eK(Object obj) {
        super(obj, AnonymousClass2eM.A02);
        AtomicReference<AnonymousClass2eK> atomicReference;
        AnonymousClass2eK r0;
        AnonymousClass2eO r2 = AnonymousClass2eM.A01;
        do {
            atomicReference = r2.A00;
            r0 = atomicReference.get();
            this.A00 = r0;
        } while (!atomicReference.compareAndSet(r0, this));
    }
}
