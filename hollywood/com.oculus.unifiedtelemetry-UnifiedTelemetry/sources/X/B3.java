package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

public class B3<E> extends AbstractC0180Uf<E> implements SortedSet<E> {
    @Weak
    public final BA<E> A00;

    @Override // java.util.SortedSet
    public final Comparator<? super E> comparator() {
        return this.A00.comparator();
    }

    @Override // java.util.SortedSet
    public final E first() {
        Multiset.Entry<E> A2C = this.A00.A2C();
        if (A2C != null) {
            return (E) A2C.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> headSet(E e) {
        return this.A00.A30(e, BoundType.OPEN).A1s();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable, X.AbstractC0180Uf
    public final Iterator<E> iterator() {
        return new C0182Uh(this.A00.entrySet().iterator());
    }

    @Override // java.util.SortedSet
    public final E last() {
        Multiset.Entry<E> A3M = this.A00.A3M();
        if (A3M != null) {
            return (E) A3M.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> subSet(E e, E e2) {
        return this.A00.A5Q(e, BoundType.CLOSED, e2, BoundType.OPEN).A1s();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> tailSet(E e) {
        return this.A00.A5T(e, BoundType.CLOSED).A1s();
    }

    public B3(BA<E> ba) {
        this.A00 = ba;
    }

    @Override // X.AbstractC0180Uf
    public final /* bridge */ /* synthetic */ AnonymousClass34 A00() {
        return this.A00;
    }
}
