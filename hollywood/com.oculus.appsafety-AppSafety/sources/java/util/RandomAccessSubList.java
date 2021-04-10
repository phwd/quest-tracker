package java.util;

/* access modifiers changed from: package-private */
/* compiled from: AbstractList */
public class RandomAccessSubList<E> extends SubList<E> implements RandomAccess {
    RandomAccessSubList(AbstractList<E> list, int fromIndex, int toIndex) {
        super(list, fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.SubList, java.util.AbstractList
    public List<E> subList(int fromIndex, int toIndex) {
        return new RandomAccessSubList(this, fromIndex, toIndex);
    }
}
