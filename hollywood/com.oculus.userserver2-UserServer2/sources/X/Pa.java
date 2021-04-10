package X;

import java.lang.ref.PhantomReference;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Pa extends PhantomReference<Object> {
    public Pa A00;
    public Pa A01;

    public abstract void destruct();

    public Pa() {
        super(null, C0114Pd.A02);
    }

    public Pa(Object obj) {
        super(obj, C0114Pd.A02);
        AtomicReference<Pa> atomicReference;
        Pa pa;
        C0113Pc pc = C0114Pd.A01;
        do {
            atomicReference = pc.A00;
            pa = atomicReference.get();
            this.A00 = pa;
        } while (!atomicReference.compareAndSet(pa, this));
    }
}
