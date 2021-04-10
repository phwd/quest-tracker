package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.NavigableSet;

@GwtIncompatible
/* renamed from: X.0BY  reason: invalid class name */
public class AnonymousClass0BY<E> extends AnonymousClass0Id<E> implements NavigableSet<E> {
    @Override // java.util.NavigableSet
    public final E ceiling(E e) {
        Multiset.Entry<E> A2o = this.A00.A8Y(e, BoundType.CLOSED).A2o();
        if (A2o == null) {
            return null;
        }
        return (E) A2o.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> descendingSet() {
        return new AnonymousClass0BY(this.A00.A26());
    }

    @Override // java.util.NavigableSet
    public final E floor(E e) {
        Multiset.Entry<E> A5d = this.A00.A52(e, BoundType.CLOSED).A5d();
        if (A5d == null) {
            return null;
        }
        return (E) A5d.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> headSet(E e, boolean z) {
        return new AnonymousClass0BY(this.A00.A52(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final E higher(E e) {
        Multiset.Entry<E> A2o = this.A00.A8Y(e, BoundType.OPEN).A2o();
        if (A2o == null) {
            return null;
        }
        return (E) A2o.A01();
    }

    @Override // java.util.NavigableSet
    public final E lower(E e) {
        Multiset.Entry<E> A5d = this.A00.A52(e, BoundType.OPEN).A5d();
        if (A5d == null) {
            return null;
        }
        return (E) A5d.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollFirst() {
        Multiset.Entry<E> A6q = this.A00.A6q();
        if (A6q == null) {
            return null;
        }
        return (E) A6q.A01();
    }

    @Override // java.util.NavigableSet
    public final E pollLast() {
        Multiset.Entry<E> A6r = this.A00.A6r();
        if (A6r == null) {
            return null;
        }
        return (E) A6r.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return new AnonymousClass0BY(this.A00.A8V(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet<E> tailSet(E e, boolean z) {
        return new AnonymousClass0BY(this.A00.A8Y(e, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    public AnonymousClass0BY(AnonymousClass0Ie<E> r1) {
        super(r1);
    }
}
