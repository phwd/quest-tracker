package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

public abstract class F5 extends PhantomReference {
    public F5 A00;
    public F5 A01;

    public abstract void destruct();

    public F5() {
        super(null, F8.A02);
    }

    public F5(Object obj) {
        super(obj, F8.A02);
        AtomicReference atomicReference;
        F5 f5;
        F7 f7 = F8.A01;
        do {
            atomicReference = f7.A00;
            f5 = (F5) atomicReference.get();
            this.A00 = f5;
        } while (!atomicReference.compareAndSet(f5, this));
    }
}
