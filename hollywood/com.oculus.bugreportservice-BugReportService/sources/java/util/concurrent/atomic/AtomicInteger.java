package java.util.concurrent.atomic;

import java.io.Serializable;
import sun.misc.Unsafe;

public class AtomicInteger extends Number implements Serializable {
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long VALUE;
    private static final long serialVersionUID = 6214790243416807050L;
    private volatile int value;

    static {
        try {
            VALUE = U.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public AtomicInteger(int i) {
        this.value = i;
    }

    public AtomicInteger() {
    }

    public final int get() {
        return this.value;
    }

    public final void set(int i) {
        this.value = i;
    }

    public final int getAndSet(int i) {
        return U.getAndSetInt(this, VALUE, i);
    }

    public final boolean compareAndSet(int i, int i2) {
        return U.compareAndSwapInt(this, VALUE, i, i2);
    }

    public final int getAndIncrement() {
        return U.getAndAddInt(this, VALUE, 1);
    }

    public final int getAndDecrement() {
        return U.getAndAddInt(this, VALUE, -1);
    }

    public final int getAndAdd(int i) {
        return U.getAndAddInt(this, VALUE, i);
    }

    public final int incrementAndGet() {
        return U.getAndAddInt(this, VALUE, 1) + 1;
    }

    public final int decrementAndGet() {
        return U.getAndAddInt(this, VALUE, -1) - 1;
    }

    public final int addAndGet(int i) {
        return U.getAndAddInt(this, VALUE, i) + i;
    }

    public String toString() {
        return Integer.toString(get());
    }

    @Override // java.lang.Number
    public int intValue() {
        return get();
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) get();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) get();
    }
}
