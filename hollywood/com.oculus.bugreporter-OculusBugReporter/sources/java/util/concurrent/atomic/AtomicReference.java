package java.util.concurrent.atomic;

import java.io.Serializable;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import sun.misc.Unsafe;

public class AtomicReference<V> implements Serializable {
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long VALUE;
    private static final long serialVersionUID = -1848883965231344442L;
    private volatile V value;

    static {
        try {
            VALUE = U.objectFieldOffset(AtomicReference.class.getDeclaredField("value"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public AtomicReference(V initialValue) {
        this.value = initialValue;
    }

    public AtomicReference() {
    }

    public final V get() {
        return this.value;
    }

    public final void set(V newValue) {
        this.value = newValue;
    }

    public final void lazySet(V newValue) {
        U.putOrderedObject(this, VALUE, newValue);
    }

    public final boolean compareAndSet(V expect, V update) {
        return U.compareAndSwapObject(this, VALUE, expect, update);
    }

    public final boolean weakCompareAndSet(V expect, V update) {
        return U.compareAndSwapObject(this, VALUE, expect, update);
    }

    public final V getAndSet(V newValue) {
        return (V) U.getAndSetObject(this, VALUE, newValue);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.atomic.AtomicReference<V> */
    /* JADX WARN: Multi-variable type inference failed */
    public final V getAndUpdate(UnaryOperator<V> updateFunction) {
        V prev;
        do {
            prev = (V) get();
        } while (!compareAndSet(prev, updateFunction.apply(prev)));
        return prev;
    }

    public final V updateAndGet(UnaryOperator<V> updateFunction) {
        V prev;
        V next;
        do {
            prev = get();
            next = (V) updateFunction.apply(prev);
        } while (!compareAndSet(prev, next));
        return next;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.atomic.AtomicReference<V> */
    /* JADX WARN: Multi-variable type inference failed */
    public final V getAndAccumulate(V x, BinaryOperator<V> accumulatorFunction) {
        V prev;
        do {
            prev = (V) get();
        } while (!compareAndSet(prev, accumulatorFunction.apply(prev, x)));
        return prev;
    }

    public final V accumulateAndGet(V x, BinaryOperator<V> accumulatorFunction) {
        V prev;
        V next;
        do {
            prev = get();
            next = (V) accumulatorFunction.apply(prev, x);
        } while (!compareAndSet(prev, next));
        return next;
    }

    public String toString() {
        return String.valueOf(get());
    }
}
