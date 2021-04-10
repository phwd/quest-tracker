package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.NavigableSet;

@GwtIncompatible
/* renamed from: X.6g  reason: invalid class name */
public class AnonymousClass6g<E> extends AnonymousClass9J<E> implements NavigableSet<E> {
    @Override // java.util.NavigableSet
    public final E ceiling(E e) {
        Multiset.Entry<E> A1Y = this.A00.A3p(e, BoundType.CLOSED).A1Y();
        if (A1Y == null) {
            return null;
        }
        return (E) A1Y.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> descendingSet() {
        return new AnonymousClass6g(this.A00.A1M());
    }

    @Override // java.util.NavigableSet
    public final E floor(E e) {
        Multiset.Entry<E> A2G = this.A00.A26(e, BoundType.CLOSED).A2G();
        if (A2G == null) {
            return null;
        }
        return (E) A2G.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> headSet(E e, boolean z) {
        return new AnonymousClass6g(this.A00.A26(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final E higher(E e) {
        Multiset.Entry<E> A1Y = this.A00.A3p(e, BoundType.OPEN).A1Y();
        if (A1Y == null) {
            return null;
        }
        return (E) A1Y.A01();
    }

    @Override // java.util.NavigableSet
    public final E lower(E e) {
        Multiset.Entry<E> A2G = this.A00.A26(e, BoundType.OPEN).A2G();
        if (A2G == null) {
            return null;
        }
        return (E) A2G.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollFirst() {
        Multiset.Entry<E> A2r = this.A00.A2r();
        if (A2r == null) {
            return null;
        }
        return (E) A2r.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollLast() {
        Multiset.Entry<E> A2s = this.A00.A2s();
        if (A2s == null) {
            return null;
        }
        return (E) A2s.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return new AnonymousClass6g(this.A00.A3n(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> tailSet(E e, boolean z) {
        return new AnonymousClass6g(this.A00.A3p(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    public AnonymousClass6g(AnonymousClass9K<E> r1) {
        super(r1);
    }
}
