package android.icu.impl;

import java.util.Comparator;
import java.util.Iterator;

public class IterableComparator<T> implements Comparator<Iterable<T>> {
    private static final IterableComparator NOCOMPARATOR = new IterableComparator();
    private final Comparator<T> comparator;
    private final int shorterFirst;

    @Override // java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return compare((Iterable) ((Iterable) obj), (Iterable) ((Iterable) obj2));
    }

    public IterableComparator() {
        this(null, true);
    }

    public IterableComparator(Comparator<T> comparator2) {
        this(comparator2, true);
    }

    public IterableComparator(Comparator<T> comparator2, boolean shorterFirst2) {
        this.comparator = comparator2;
        this.shorterFirst = shorterFirst2 ? 1 : -1;
    }

    public int compare(Iterable<T> a, Iterable<T> b) {
        int result;
        if (a == null) {
            if (b == null) {
                return 0;
            }
            return -this.shorterFirst;
        } else if (b == null) {
            return this.shorterFirst;
        } else {
            Iterator<T> bi = b.iterator();
            for (T aItem : a) {
                if (!bi.hasNext()) {
                    return this.shorterFirst;
                }
                T bItem = bi.next();
                Comparator<T> comparator2 = this.comparator;
                if (comparator2 != null) {
                    result = comparator2.compare(aItem, bItem);
                    continue;
                } else {
                    result = aItem.compareTo(bItem);
                    continue;
                }
                if (result != 0) {
                    return result;
                }
            }
            if (bi.hasNext()) {
                return -this.shorterFirst;
            }
            return 0;
        }
    }

    public static <T> int compareIterables(Iterable<T> a, Iterable<T> b) {
        return NOCOMPARATOR.compare((Iterable) a, (Iterable) b);
    }
}
