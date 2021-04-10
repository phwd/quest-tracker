package java.util;

public interface Collection extends Iterable {
    boolean add(Object obj);

    boolean addAll(Collection collection);

    void clear();

    boolean contains(Object obj);

    boolean containsAll(Collection collection);

    boolean equals(Object obj);

    int hashCode();

    boolean isEmpty();

    @Override // java.lang.Iterable
    Iterator iterator();

    boolean remove(Object obj);

    boolean removeAll(Collection collection);

    boolean retainAll(Collection collection);

    int size();

    Object[] toArray();

    Object[] toArray(Object[] objArr);
}
