package java.util;

public interface Set extends Collection {
    @Override // java.util.Collection
    boolean add(Object obj);

    @Override // java.util.Collection
    boolean addAll(Collection collection);

    @Override // java.util.Collection
    void clear();

    @Override // java.util.Collection
    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection collection);

    @Override // java.util.Collection
    boolean equals(Object obj);

    @Override // java.util.Collection
    int hashCode();

    @Override // java.util.Collection
    boolean isEmpty();

    @Override // java.util.Collection, java.lang.Iterable
    Iterator iterator();

    @Override // java.util.Collection
    boolean remove(Object obj);

    @Override // java.util.Collection
    boolean removeAll(Collection collection);

    @Override // java.util.Collection
    boolean retainAll(Collection collection);

    @Override // java.util.Collection
    int size();

    @Override // java.util.Collection
    Object[] toArray();

    @Override // java.util.Collection
    Object[] toArray(Object[] objArr);
}
