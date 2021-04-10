package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.NavigableSet;

@GwtIncompatible
/* renamed from: X.06b  reason: invalid class name */
public class AnonymousClass06b<E> extends AnonymousClass0Bj<E> implements NavigableSet<E> {
    @Override // java.util.NavigableSet
    public final E ceiling(E e) {
        Multiset.Entry<E> A2p = this.A00.A9c(e, BoundType.CLOSED).A2p();
        if (A2p == null) {
            return null;
        }
        return (E) A2p.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> descendingSet() {
        return new AnonymousClass06b(this.A00.A2E());
    }

    @Override // java.util.NavigableSet
    public final E floor(E e) {
        Multiset.Entry<E> A5C = this.A00.A4j(e, BoundType.CLOSED).A5C();
        if (A5C == null) {
            return null;
        }
        return (E) A5C.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> headSet(E e, boolean z) {
        return new AnonymousClass06b(this.A00.A4j(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final E higher(E e) {
        Multiset.Entry<E> A2p = this.A00.A9c(e, BoundType.OPEN).A2p();
        if (A2p == null) {
            return null;
        }
        return (E) A2p.A01();
    }

    @Override // java.util.NavigableSet
    public final E lower(E e) {
        Multiset.Entry<E> A5C = this.A00.A4j(e, BoundType.OPEN).A5C();
        if (A5C == null) {
            return null;
        }
        return (E) A5C.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollFirst() {
        Multiset.Entry<E> A7M = this.A00.A7M();
        if (A7M == null) {
            return null;
        }
        return (E) A7M.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollLast() {
        Multiset.Entry<E> A7N = this.A00.A7N();
        if (A7N == null) {
            return null;
        }
        return (E) A7N.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return new AnonymousClass06b(this.A00.A9W(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> tailSet(E e, boolean z) {
        return new AnonymousClass06b(this.A00.A9c(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    public AnonymousClass06b(AnonymousClass0Bk<E> r1) {
        super(r1);
    }
}
