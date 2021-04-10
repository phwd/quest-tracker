package X;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

/* renamed from: X.0pi  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06730pi<E> {
    @CanIgnoreReturnValue
    public abstract AbstractC06730pi<E> add(E e);

    public abstract ImmutableCollection<E> build();

    public static int A01(int i, int i2) {
        if (i2 >= 0) {
            int i3 = i + (i >> 1) + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            if (i3 < 0) {
                return Integer.MAX_VALUE;
            }
            return i3;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: X.0pi<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public AbstractC06730pi<E> A02(Iterable<? extends E> iterable) {
        Iterator<? extends E> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return this;
    }

    @CanIgnoreReturnValue
    public AbstractC06730pi<E> add(E... eArr) {
        for (E e : eArr) {
            add(e);
        }
        return this;
    }
}
