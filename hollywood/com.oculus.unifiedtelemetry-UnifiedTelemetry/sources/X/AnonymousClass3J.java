package X;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.3J  reason: invalid class name */
public abstract class AnonymousClass3J<E> {
    @CanIgnoreReturnValue
    public abstract AnonymousClass3J<E> add(E e);

    public abstract ImmutableCollection<E> build();

    @CanIgnoreReturnValue
    public AnonymousClass3J<E> add(E... eArr) {
        for (E e : eArr) {
            add(e);
        }
        return this;
    }
}
