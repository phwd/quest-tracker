package java.util;

public interface Set<E> extends Collection<E> {
    @Override // java.util.Collection
    boolean add(E e);

    @Override // java.util.Collection
    boolean addAll(Collection<? extends E> collection);

    @Override // java.util.Collection
    void clear();

    @Override // java.util.Collection
    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    @Override // java.util.Collection
    boolean equals(Object obj);

    @Override // java.util.Collection
    int hashCode();

    @Override // java.util.Collection
    boolean isEmpty();

    @Override // java.util.Collection, java.lang.Iterable
    Iterator<E> iterator();

    @Override // java.util.Collection
    boolean remove(Object obj);

    @Override // java.util.Collection
    boolean removeAll(Collection<?> collection);

    @Override // java.util.Collection
    boolean retainAll(Collection<?> collection);

    @Override // java.util.Collection
    int size();

    @Override // java.util.Collection
    Object[] toArray();

    @Override // java.util.Collection
    <T> T[] toArray(T[] tArr);

    @Override // java.util.Collection, java.lang.Iterable
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 1);
    }
}
