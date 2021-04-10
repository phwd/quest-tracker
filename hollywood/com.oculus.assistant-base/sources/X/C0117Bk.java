package X;

import com.google.common.collect.BoundType;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* renamed from: X.Bk  reason: case insensitive filesystem */
public class C0117Bk<E> extends AbstractC1180ub<E> implements SortedSet<E> {
    public final AbstractC0118Bl A00;

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.A00.comparator();
    }

    @Override // java.util.SortedSet
    public final Object first() {
        AbstractC1179ua A29 = this.A00.A29();
        if (A29 != null) {
            return A29.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return this.A00.A3B(obj, BoundType.OPEN).A1l();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new C1178uZ(this.A00.entrySet().iterator());
    }

    @Override // java.util.SortedSet
    public final Object last() {
        AbstractC1179ua A3f = this.A00.A3f();
        if (A3f != null) {
            return A3f.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return this.A00.A5A(obj, BoundType.CLOSED, obj2, BoundType.OPEN).A1l();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return this.A00.A5F(obj, BoundType.CLOSED).A1l();
    }

    public C0117Bk(AbstractC0118Bl bl) {
        this.A00 = bl;
    }
}
