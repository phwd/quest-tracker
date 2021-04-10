package java.util;

public abstract class AbstractSequentialList extends AbstractList {
    protected AbstractSequentialList() {
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator iterator() {
        return listIterator();
    }
}
