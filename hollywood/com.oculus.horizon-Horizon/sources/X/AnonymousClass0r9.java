package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0r9  reason: invalid class name */
public interface AnonymousClass0r9<E> extends Collection<E> {
    @CanIgnoreReturnValue
    int A11(@NullableDecl E e, int i);

    int A1v(@NullableDecl @CompatibleWith("E") Object obj);

    Set<E> A2N();

    @CanIgnoreReturnValue
    int A87(@NullableDecl @CompatibleWith("E") Object obj, int i);

    @CanIgnoreReturnValue
    int A8d(E e, int i);

    @CanIgnoreReturnValue
    boolean A8e(E e, int i, int i2);

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
