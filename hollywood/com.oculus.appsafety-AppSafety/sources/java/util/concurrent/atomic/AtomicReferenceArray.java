package java.util.concurrent.atomic;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import sun.misc.Unsafe;

public class AtomicReferenceArray<E> implements Serializable {
    private static final int ABASE;
    private static final long ARRAY;
    private static final int ASHIFT;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = -6209656149925076980L;
    private final Object[] array;

    static {
        try {
            ARRAY = U.objectFieldOffset(AtomicReferenceArray.class.getDeclaredField("array"));
            ABASE = U.arrayBaseOffset(Object[].class);
            int scale = U.arrayIndexScale(Object[].class);
            if (((scale - 1) & scale) == 0) {
                ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
                return;
            }
            throw new Error("array index scale not a power of two");
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private long checkedByteOffset(int i) {
        if (i >= 0 && i < this.array.length) {
            return byteOffset(i);
        }
        throw new IndexOutOfBoundsException("index " + i);
    }

    private static long byteOffset(int i) {
        return (((long) i) << ASHIFT) + ((long) ABASE);
    }

    public AtomicReferenceArray(int length) {
        this.array = new Object[length];
    }

    public AtomicReferenceArray(E[] array2) {
        this.array = Arrays.copyOf(array2, array2.length, Object[].class);
    }

    public final int length() {
        return this.array.length;
    }

    public final E get(int i) {
        return getRaw(checkedByteOffset(i));
    }

    private E getRaw(long offset) {
        return (E) U.getObjectVolatile(this.array, offset);
    }

    public final void set(int i, E newValue) {
        U.putObjectVolatile(this.array, checkedByteOffset(i), newValue);
    }

    public final void lazySet(int i, E newValue) {
        U.putOrderedObject(this.array, checkedByteOffset(i), newValue);
    }

    public final E getAndSet(int i, E newValue) {
        return (E) U.getAndSetObject(this.array, checkedByteOffset(i), newValue);
    }

    public final boolean compareAndSet(int i, E expect, E update) {
        return compareAndSetRaw(checkedByteOffset(i), expect, update);
    }

    private boolean compareAndSetRaw(long offset, E expect, E update) {
        return U.compareAndSwapObject(this.array, offset, expect, update);
    }

    public final boolean weakCompareAndSet(int i, E expect, E update) {
        return compareAndSet(i, expect, update);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E> */
    /* JADX WARN: Multi-variable type inference failed */
    public final E getAndUpdate(int i, UnaryOperator<E> updateFunction) {
        E prev;
        long offset = checkedByteOffset(i);
        do {
            prev = (E) getRaw(offset);
        } while (!compareAndSetRaw(offset, prev, updateFunction.apply(prev)));
        return prev;
    }

    public final E updateAndGet(int i, UnaryOperator<E> updateFunction) {
        E prev;
        E next;
        long offset = checkedByteOffset(i);
        do {
            prev = getRaw(offset);
            next = (E) updateFunction.apply(prev);
        } while (!compareAndSetRaw(offset, prev, next));
        return next;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E> */
    /* JADX WARN: Multi-variable type inference failed */
    public final E getAndAccumulate(int i, E x, BinaryOperator<E> accumulatorFunction) {
        E prev;
        long offset = checkedByteOffset(i);
        do {
            prev = (E) getRaw(offset);
        } while (!compareAndSetRaw(offset, prev, accumulatorFunction.apply(prev, x)));
        return prev;
    }

    public final E accumulateAndGet(int i, E x, BinaryOperator<E> accumulatorFunction) {
        E prev;
        E next;
        long offset = checkedByteOffset(i);
        do {
            prev = getRaw(offset);
            next = (E) accumulatorFunction.apply(prev, x);
        } while (!compareAndSetRaw(offset, prev, next));
        return next;
    }

    public String toString() {
        int iMax = this.array.length - 1;
        if (iMax == -1) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        while (true) {
            b.append((Object) getRaw(byteOffset(i)));
            if (i == iMax) {
                b.append(']');
                return b.toString();
            }
            b.append(',');
            b.append(' ');
            i++;
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        Object a = s.readFields().get("array", (Object) null);
        if (a == null || !a.getClass().isArray()) {
            throw new InvalidObjectException("Not array type");
        }
        if (a.getClass() != Object[].class) {
            a = Arrays.copyOf((Object[]) a, Array.getLength(a), Object[].class);
        }
        U.putObjectVolatile(this, ARRAY, a);
    }
}
