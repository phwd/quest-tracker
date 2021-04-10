package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.NavigableSet;

@GwtIncompatible
/* renamed from: X.6l  reason: invalid class name */
public class AnonymousClass6l<E> extends B3<E> implements NavigableSet<E> {
    @Override // java.util.NavigableSet
    public final E ceiling(E e) {
        Multiset.Entry<E> A2C = this.A00.A5T(e, BoundType.CLOSED).A2C();
        if (A2C == null) {
            return null;
        }
        return (E) A2C.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> descendingSet() {
        return new AnonymousClass6l(this.A00.A1o());
    }

    @Override // java.util.NavigableSet
    public final E floor(E e) {
        Multiset.Entry<E> A3M = this.A00.A30(e, BoundType.CLOSED).A3M();
        if (A3M == null) {
            return null;
        }
        return (E) A3M.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> headSet(E e, boolean z) {
        return new AnonymousClass6l(this.A00.A30(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final E higher(E e) {
        Multiset.Entry<E> A2C = this.A00.A5T(e, BoundType.OPEN).A2C();
        if (A2C == null) {
            return null;
        }
        return (E) A2C.A01();
    }

    @Override // java.util.NavigableSet
    public final E lower(E e) {
        Multiset.Entry<E> A3M = this.A00.A30(e, BoundType.OPEN).A3M();
        if (A3M == null) {
            return null;
        }
        return (E) A3M.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollFirst() {
        Multiset.Entry<E> A4E = this.A00.A4E();
        if (A4E == null) {
            return null;
        }
        return (E) A4E.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollLast() {
        Multiset.Entry<E> A4F = this.A00.A4F();
        if (A4F == null) {
            return null;
        }
        return (E) A4F.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return new AnonymousClass6l(this.A00.A5Q(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> tailSet(E e, boolean z) {
        return new AnonymousClass6l(this.A00.A5T(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    public AnonymousClass6l(BA<E> ba) {
        super(ba);
    }
}
