package X;

import com.google.common.collect.BoundType;
import java.util.Iterator;
import java.util.NavigableSet;

public final class SI<E> extends C0117Bk<E> implements NavigableSet<E> {
    @Override // java.util.NavigableSet
    public final Object ceiling(Object obj) {
        AbstractC1179ua A29 = this.A00.A5F(obj, BoundType.CLOSED).A29();
        if (A29 == null) {
            return null;
        }
        return A29.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet descendingSet() {
        return new SI(this.A00.A1g());
    }

    @Override // java.util.NavigableSet
    public final Object floor(Object obj) {
        AbstractC1179ua A3f = this.A00.A3B(obj, BoundType.CLOSED).A3f();
        if (A3f == null) {
            return null;
        }
        return A3f.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet headSet(Object obj, boolean z) {
        return new SI(this.A00.A3B(obj, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Object higher(Object obj) {
        AbstractC1179ua A29 = this.A00.A5F(obj, BoundType.OPEN).A29();
        if (A29 == null) {
            return null;
        }
        return A29.A01();
    }

    @Override // java.util.NavigableSet
    public final Object lower(Object obj) {
        AbstractC1179ua A3f = this.A00.A3B(obj, BoundType.OPEN).A3f();
        if (A3f == null) {
            return null;
        }
        return A3f.A01();
    }

    @Override // java.util.NavigableSet
    public final Object pollFirst() {
        AbstractC1179ua A4V = this.A00.A4V();
        if (A4V == null) {
            return null;
        }
        return A4V.A01();
    }

    @Override // java.util.NavigableSet
    public final Object pollLast() {
        AbstractC1179ua A4W = this.A00.A4W();
        if (A4W == null) {
            return null;
        }
        return A4W.A01();
    }

    @Override // java.util.NavigableSet
    public final NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        return new SI(this.A00.A5A(obj, BoundType.forBoolean(z), obj2, BoundType.forBoolean(z2)));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet tailSet(Object obj, boolean z) {
        return new SI(this.A00.A5F(obj, BoundType.forBoolean(z)));
    }

    @Override // java.util.NavigableSet
    public final Iterator descendingIterator() {
        return descendingSet().iterator();
    }

    public SI(AbstractC0118Bl bl) {
        super(bl);
    }
}
