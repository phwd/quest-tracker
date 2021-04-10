package java.util;

public interface ListIterator extends Iterator {
    void add(Object obj);

    @Override // java.util.Iterator
    boolean hasNext();

    @Override // java.util.Iterator
    Object next();

    Object previous();

    int previousIndex();

    @Override // java.util.Iterator
    void remove();

    void set(Object obj);
}
