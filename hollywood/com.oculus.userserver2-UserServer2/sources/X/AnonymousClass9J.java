package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* renamed from: X.9J  reason: invalid class name */
public class AnonymousClass9J<E> extends AbstractC0105Mg<E> implements SortedSet<E> {
    @Weak
    public final AnonymousClass9K<E> A00;

    @Override // java.util.SortedSet
    public final Comparator<? super E> comparator() {
        return this.A00.comparator();
    }

    @Override // java.util.SortedSet
    public final E first() {
        Multiset.Entry<E> A1Y = this.A00.A1Y();
        if (A1Y != null) {
            return (E) A1Y.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> headSet(E e) {
        return this.A00.A26(e, BoundType.OPEN).A1Q();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, X.AbstractC0105Mg, java.util.Set, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new C0106Mk(this.A00.entrySet().iterator());
    }

    @Override // java.util.SortedSet
    public final E last() {
        Multiset.Entry<E> A2G = this.A00.A2G();
        if (A2G != null) {
            return (E) A2G.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> subSet(E e, E e2) {
        return this.A00.A3n(e, BoundType.CLOSED, e2, BoundType.OPEN).A1Q();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> tailSet(E e) {
        return this.A00.A3p(e, BoundType.CLOSED).A1Q();
    }

    public AnonymousClass9J(AnonymousClass9K<E> r1) {
        this.A00 = r1;
    }
}
