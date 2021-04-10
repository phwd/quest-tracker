package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public abstract Set<T> asSet();

    public abstract boolean equals(@Nullable Object obj);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> optional);

    @Beta
    public abstract T or(Supplier<? extends T> supplier);

    public abstract T or(T t);

    @Nullable
    public abstract T orNull();

    public abstract String toString();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T reference) {
        return new Present(Preconditions.checkNotNull(reference));
    }

    public static <T> Optional<T> fromNullable(@Nullable T nullableReference) {
        if (nullableReference == null) {
            return absent();
        }
        return new Present(nullableReference);
    }

    Optional() {
    }

    @Beta
    public static <T> Iterable<T> presentInstances(final Iterable<? extends Optional<? extends T>> optionals) {
        Preconditions.checkNotNull(optionals);
        return new Iterable<T>() {
            /* class com.google.common.base.Optional.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new AbstractIterator<T>() {
                    /* class com.google.common.base.Optional.AnonymousClass1.AnonymousClass1 */
                    private final Iterator<? extends Optional<? extends T>> iterator = ((Iterator) Preconditions.checkNotNull(optionals.iterator()));

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.base.AbstractIterator
                    public T computeNext() {
                        while (this.iterator.hasNext()) {
                            Optional<? extends T> optional = (Optional) this.iterator.next();
                            if (optional.isPresent()) {
                                return (T) optional.get();
                            }
                        }
                        return (T) endOfData();
                    }
                };
            }
        };
    }
}