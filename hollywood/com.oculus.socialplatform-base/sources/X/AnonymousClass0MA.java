package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* renamed from: X.0MA  reason: invalid class name */
public class AnonymousClass0MA<E> extends AnonymousClass0f1<E> implements SortedSet<E> {
    @Weak
    public final AnonymousClass0MC<E> A00;

    @Override // java.util.SortedSet
    public final Comparator<? super E> comparator() {
        return this.A00.comparator();
    }

    @Override // java.util.SortedSet
    public final E first() {
        Multiset.Entry<E> A3E = this.A00.A3E();
        if (A3E != null) {
            return (E) A3E.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> headSet(E e) {
        return this.A00.A5T(e, BoundType.OPEN).A2j();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, X.AnonymousClass0f1, java.util.Set, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new AnonymousClass0f3(this.A00.entrySet().iterator());
    }

    @Override // java.util.SortedSet
    public final E last() {
        Multiset.Entry<E> A6F = this.A00.A6F();
        if (A6F != null) {
            return (E) A6F.A01();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> subSet(E e, E e2) {
        return this.A00.AAW(e, BoundType.CLOSED, e2, BoundType.OPEN).A2j();
    }

    @Override // java.util.SortedSet
    public final SortedSet<E> tailSet(E e) {
        return this.A00.AAg(e, BoundType.CLOSED).A2j();
    }

    public AnonymousClass0MA(AnonymousClass0MC<E> r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0f1
    public final /* bridge */ /* synthetic */ AbstractC05490vp A00() {
        return this.A00;
    }
}
