package java.util;

import java.util.function.UnaryOperator;

public interface List<E> extends Collection<E> {
    void add(int i, E e);

    @Override // java.util.Collection
    boolean add(E e);

    boolean addAll(int i, Collection<? extends E> collection);

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

    E get(int i);

    @Override // java.util.Collection
    int hashCode();

    int indexOf(Object obj);

    @Override // java.util.Collection
    boolean isEmpty();

    @Override // java.util.Collection, java.lang.Iterable
    Iterator<E> iterator();

    int lastIndexOf(Object obj);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int i);

    E remove(int i);

    @Override // java.util.Collection
    boolean remove(Object obj);

    @Override // java.util.Collection
    boolean removeAll(Collection<?> collection);

    @Override // java.util.Collection
    boolean retainAll(Collection<?> collection);

    E set(int i, E e);

    @Override // java.util.Collection
    int size();

    List<E> subList(int i, int i2);

    @Override // java.util.Collection
    Object[] toArray();

    @Override // java.util.Collection
    <T> T[] toArray(T[] tArr);

    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        ListIterator<E> li = listIterator();
        while (li.hasNext()) {
            li.set((E) operator.apply(li.next()));
        }
    }

    default void sort(Comparator<? super E> c) {
        Object[] a = toArray();
        Arrays.sort(a, c);
        ListIterator<E> i = listIterator();
        for (Object obj : a) {
            i.next();
            i.set((E) obj);
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 16);
    }
}
