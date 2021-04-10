package X;

import java.util.Collection;
import java.util.Set;

public interface UM<E> extends Collection<E> {
    int A19(Object obj, int i);

    int A1V(Object obj);

    Set A1m();

    int A4m(Object obj, int i);

    int A4z(Object obj, int i);

    boolean A50(Object obj, int i, int i2);

    @Override // java.util.Collection
    boolean add(Object obj);

    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection collection);

    Set entrySet();

    boolean equals(Object obj);

    int hashCode();

    boolean remove(Object obj);

    int size();
}
