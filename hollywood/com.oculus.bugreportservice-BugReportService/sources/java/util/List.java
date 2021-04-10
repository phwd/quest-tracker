package java.util;

public interface List extends Collection {
    void add(int i, Object obj);

    @Override // java.util.Collection
    boolean add(Object obj);

    @Override // java.util.Collection
    boolean addAll(Collection collection);

    @Override // java.util.Collection
    void clear();

    @Override // java.util.Collection
    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean equals(Object obj);

    Object get(int i);

    @Override // java.util.Collection
    int hashCode();

    int indexOf(Object obj);

    @Override // java.util.Collection
    boolean isEmpty();

    @Override // java.util.Collection, java.lang.Iterable
    Iterator iterator();

    ListIterator listIterator();

    ListIterator listIterator(int i);

    Object remove(int i);

    @Override // java.util.Collection
    boolean remove(Object obj);

    @Override // java.util.Collection
    boolean removeAll(Collection collection);

    Object set(int i, Object obj);

    @Override // java.util.Collection
    int size();

    @Override // java.util.Collection
    Object[] toArray();

    @Override // java.util.Collection
    Object[] toArray(Object[] objArr);

    default void sort(Comparator comparator) {
        Object[] array = toArray();
        Arrays.sort(array, comparator);
        ListIterator listIterator = listIterator();
        for (Object obj : array) {
            listIterator.next();
            listIterator.set(obj);
        }
    }
}
