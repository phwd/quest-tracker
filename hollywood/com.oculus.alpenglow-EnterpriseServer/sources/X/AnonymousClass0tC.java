package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0tC  reason: invalid class name */
public interface AnonymousClass0tC<E> extends Collection<E> {
    @CanIgnoreReturnValue
    int A0x(@NullableDecl E e, int i);

    int A1s(@NullableDecl @CompatibleWith("E") Object obj);

    Set<E> A2G();

    @CanIgnoreReturnValue
    int A7L(@NullableDecl @CompatibleWith("E") Object obj, int i);

    @CanIgnoreReturnValue
    int A7q(E e, int i);

    @CanIgnoreReturnValue
    boolean A7r(E e, int i, int i2);

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    boolean add(E e);

    boolean contains(@NullableDecl Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    Set<Multiset.Entry<E>> entrySet();

    boolean equals(@NullableDecl Object obj);

    int hashCode();

    @CanIgnoreReturnValue
    boolean remove(@NullableDecl Object obj);

    int size();
}
