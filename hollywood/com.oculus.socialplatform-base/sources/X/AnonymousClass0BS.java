package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.NavigableSet;

@GwtIncompatible
/* renamed from: X.0BS  reason: invalid class name */
public class AnonymousClass0BS<E> extends AnonymousClass0MA<E> implements NavigableSet<E> {
    @Override // java.util.NavigableSet
    public final E ceiling(E e) {
        Multiset.Entry<E> A3E = this.A00.AAg(e, BoundType.CLOSED).A3E();
        if (A3E == null) {
            return null;
        }
        return (E) A3E.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> descendingSet() {
        return new AnonymousClass0BS(this.A00.A2a());
    }

    @Override // java.util.NavigableSet
    public final E floor(E e) {
        Multiset.Entry<E> A6F = this.A00.A5T(e, BoundType.CLOSED).A6F();
        if (A6F == null) {
            return null;
        }
        return (E) A6F.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> headSet(E e, boolean z) {
        return new AnonymousClass0BS(this.A00.A5T(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final E higher(E e) {
        Multiset.Entry<E> A3E = this.A00.AAg(e, BoundType.OPEN).A3E();
        if (A3E == null) {
            return null;
        }
        return (E) A3E.A01();
    }

    @Override // java.util.NavigableSet
    public final E lower(E e) {
        Multiset.Entry<E> A6F = this.A00.A5T(e, BoundType.OPEN).A6F();
        if (A6F == null) {
            return null;
        }
        return (E) A6F.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollFirst() {
        Multiset.Entry<E> A8P = this.A00.A8P();
        if (A8P == null) {
            return null;
        }
        return (E) A8P.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollLast() {
        Multiset.Entry<E> A8Q = this.A00.A8Q();
        if (A8Q == null) {
            return null;
        }
        return (E) A8Q.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return new AnonymousClass0BS(this.A00.AAW(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> tailSet(E e, boolean z) {
        return new AnonymousClass0BS(this.A00.AAg(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    public AnonymousClass0BS(AnonymousClass0MC<E> r1) {
        super(r1);
    }
}
