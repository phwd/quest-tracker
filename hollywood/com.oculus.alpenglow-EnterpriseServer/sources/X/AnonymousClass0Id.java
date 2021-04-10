package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* renamed from: X.0Id  reason: invalid class name */
public class AnonymousClass0Id<E> extends AbstractC02340Xz<E> implements SortedSet<E> {
    @Weak
    public final AnonymousClass0Ie<E> A00;

    @Override // X.AbstractC02340Xz
    public final /* bridge */ /* synthetic */ AnonymousClass0tC A00() {
        return this.A00;
    }

    @Override // java.util.SortedSet
    public final Comparator<? super E> comparator() {
        return this.A00.comparator();
    }

    @Override // java.util.SortedSet
    public final E first() {
        Multiset.Entry<E> A2o = this.A00.A2o();
        if (A2o != null) {
            return (E) A2o.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> headSet(E e) {
        return this.A00.A52(e, BoundType.OPEN).A2F();
    }

    @Override // X.AbstractC02340Xz, java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new AnonymousClass0Y1(this.A00.entrySet().iterator());
    }

    @Override // java.util.SortedSet
    public final E last() {
        Multiset.Entry<E> A5d = this.A00.A5d();
        if (A5d != null) {
            return (E) A5d.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> subSet(E e, E e2) {
        return this.A00.A8V(e, BoundType.CLOSED, e2, BoundType.OPEN).A2F();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> tailSet(E e) {
        return this.A00.A8Y(e, BoundType.CLOSED).A2F();
    }

    public AnonymousClass0Id(AnonymousClass0Ie<E> r1) {
        this.A00 = r1;
    }
}
