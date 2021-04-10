package java.util.concurrent.atomic;

import java.io.Serializable;
import sun.misc.Unsafe;

public class AtomicLong extends Number implements Serializable {
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long VALUE;
    static final boolean VM_SUPPORTS_LONG_CAS = VMSupportsCS8();
    private static final long serialVersionUID = 1927816293512124184L;
    private volatile long value;

    private static native boolean VMSupportsCS8();

    static {
        try {
            VALUE = U.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public AtomicLong(long j) {
        this.value = j;
    }

    public AtomicLong() {
    }

    public final long get() {
        return this.value;
    }

    public final void set(long j) {
        U.putLongVolatile(this, VALUE, j);
    }

    public final boolean compareAndSet(long j, long j2) {
        return U.compareAndSwapLong(this, VALUE, j, j2);
    }

    public final long getAndIncrement() {
        return U.getAndAddLong(this, VALUE, 1);
    }

    public final long getAndAdd(long j) {
        return U.getAndAddLong(this, VALUE, j);
    }

    public String toString() {
        return Long.toString(get());
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) get();
    }

    @Override // java.lang.Number
    public long longValue() {
        return get();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) get();
    }
}
