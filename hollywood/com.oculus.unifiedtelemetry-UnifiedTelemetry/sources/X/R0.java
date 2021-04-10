package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

public abstract class R0 extends PhantomReference<Object> {
    public R0 A00;
    public R0 A01;

    public abstract void destruct();

    public R0() {
        super(null, R3.A02);
    }

    public R0(Object obj) {
        super(obj, R3.A02);
        AtomicReference<R0> atomicReference;
        R0 r0;
        R2 r2 = R3.A01;
        do {
            atomicReference = r2.A00;
            r0 = atomicReference.get();
            this.A00 = r0;
        } while (!atomicReference.compareAndSet(r0, this));
    }
}
