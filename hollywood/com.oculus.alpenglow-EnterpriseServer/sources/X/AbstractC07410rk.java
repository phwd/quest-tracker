package X;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.0rk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07410rk<E> {
    @CanIgnoreReturnValue
    public abstract AbstractC07410rk<E> add(E e);

    public abstract ImmutableCollection<E> build();

    @CanIgnoreReturnValue
    public AbstractC07410rk<E> add(E... eArr) {
        for (E e : eArr) {
            add(e);
        }
        return this;
    }
}
