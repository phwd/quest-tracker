package java.util;

import java.util.Spliterators;

public interface SortedSet<E> extends Set<E> {
    Comparator<? super E> comparator();

    E first();

    SortedSet<E> headSet(E e);

    E last();

    SortedSet<E> subSet(E e, E e2);

    SortedSet<E> tailSet(E e);

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    default Spliterator<E> spliterator() {
        return new Spliterators.IteratorSpliterator<E>(this, 21) {
            /* class java.util.SortedSet.AnonymousClass1 */

            @Override // java.util.Spliterators.IteratorSpliterator, java.util.Spliterator
            public Comparator<? super E> getComparator() {
                return SortedSet.this.comparator();
            }
        };
    }
}
