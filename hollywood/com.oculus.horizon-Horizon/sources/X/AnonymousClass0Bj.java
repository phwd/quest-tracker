package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* renamed from: X.0Bj  reason: invalid class name */
public class AnonymousClass0Bj<E> extends AnonymousClass0dK<E> implements SortedSet<E> {
    @Weak
    public final AnonymousClass0Bk<E> A00;

    @Override // java.util.SortedSet
    public final Comparator<? super E> comparator() {
        return this.A00.comparator();
    }

    @Override // java.util.SortedSet
    public final E first() {
        Multiset.Entry<E> A2p = this.A00.A2p();
        if (A2p != null) {
            return (E) A2p.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> headSet(E e) {
        return this.A00.A4j(e, BoundType.OPEN).A2M();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, X.AnonymousClass0dK, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new AnonymousClass0dO(this.A00.entrySet().iterator());
    }

    @Override // java.util.SortedSet
    public final E last() {
        Multiset.Entry<E> A5C = this.A00.A5C();
        if (A5C != null) {
            return (E) A5C.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> subSet(E e, E e2) {
        return this.A00.A9W(e, BoundType.CLOSED, e2, BoundType.OPEN).A2M();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> tailSet(E e) {
        return this.A00.A9c(e, BoundType.CLOSED).A2M();
    }

    public AnonymousClass0Bj(AnonymousClass0Bk<E> r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0dK
    public final /* bridge */ /* synthetic */ AnonymousClass0r9 A00() {
        return this.A00;
    }
}
