package java.util.concurrent.atomic;

import java.io.Serializable;
import sun.misc.Unsafe;

public class AtomicReference implements Serializable {
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long VALUE;
    private static final long serialVersionUID = -1848883965231344442L;
    private volatile Object value;

    static {
        try {
            VALUE = U.objectFieldOffset(AtomicReference.class.getDeclaredField("value"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public AtomicReference(Object obj) {
        this.value = obj;
    }

    public AtomicReference() {
    }

    public final Object get() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(get());
    }
}
