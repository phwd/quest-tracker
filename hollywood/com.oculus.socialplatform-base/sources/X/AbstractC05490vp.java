package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0vp  reason: invalid class name and case insensitive filesystem */
public interface AbstractC05490vp<E> extends Collection<E> {
    @CanIgnoreReturnValue
    int A1A(@NullableDecl E e, int i);

    int A2J(@NullableDecl @CompatibleWith("E") Object obj);

    Set<E> A2k();

    @CanIgnoreReturnValue
    int A92(@NullableDecl @CompatibleWith("E") Object obj, int i);

    @CanIgnoreReturnValue
    int A9n(E e, int i);

    @CanIgnoreReturnValue
    boolean A9o(E e, int i, int i2);

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
