package X;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public abstract class Qe<E> {
    @CanIgnoreReturnValue
    public abstract Qe<E> add(E e);

    public abstract ImmutableCollection<E> build();

    @CanIgnoreReturnValue
    public Qe<E> add(E... eArr) {
        for (E e : eArr) {
            add(e);
        }
        return this;
    }
}
