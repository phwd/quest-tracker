package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.34  reason: invalid class name */
public interface AnonymousClass34<E> extends Collection<E> {
    @CanIgnoreReturnValue
    int A11(@NullableDecl E e, int i);

    int A1c(@NullableDecl @CompatibleWith("E") Object obj);

    Set<E> A1t();

    @CanIgnoreReturnValue
    int A4f(@NullableDecl @CompatibleWith("E") Object obj, int i);

    @CanIgnoreReturnValue
    int A4x(E e, int i);

    @CanIgnoreReturnValue
    boolean A4y(E e, int i, int i2);

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
