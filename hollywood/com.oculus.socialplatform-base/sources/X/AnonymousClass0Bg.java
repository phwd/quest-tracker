package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.ComparatorOrdering;
import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtCompatible(emulated = true)
/* renamed from: X.0Bg  reason: invalid class name */
public abstract class AnonymousClass0Bg<E> extends AnonymousClass0NP<E> implements AnonymousClass0MC<E> {
    @MonotonicNonNullDecl
    public transient Comparator<? super E> A00;
    @MonotonicNonNullDecl
    public transient NavigableSet<E> A01;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A02;

    public abstract AnonymousClass0MC<E> A06();

    public abstract Iterator<Multiset.Entry<E>> A07();

    @Override // X.AnonymousClass0MC
    /* renamed from: A2j */
    public final NavigableSet<E> A2k() {
        NavigableSet<E> navigableSet = this.A01;
        if (navigableSet != null) {
            return navigableSet;
        }
        AnonymousClass0BS r0 = new AnonymousClass0BS(this);
        this.A01 = r0;
        return r0;
    }

    @Override // X.AnonymousClass0wF, X.AnonymousClass0MC
    public final Comparator<? super E> comparator() {
        AnonymousClass0vx comparatorOrdering;
        Comparator<? super E> comparator = this.A00;
        if (comparator != null) {
            return comparator;
        }
        Comparator<? super E> comparator2 = A06().comparator();
        if (comparator2 instanceof AnonymousClass0vx) {
            comparatorOrdering = (AnonymousClass0vx) comparator2;
        } else {
            comparatorOrdering = new ComparatorOrdering(comparator2);
        }
        AnonymousClass0vx A002 = comparatorOrdering.A00();
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass0NP, X.AbstractC05490vp, X.AnonymousClass0MC
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A02;
        if (set != null) {
            return set;
        }
        AnonymousClass0NY r0 = new AnonymousClass0NY(this);
        this.A02 = r0;
        return r0;
    }

    @Override // X.AnonymousClass0NP
    /* renamed from: A05 */
    public final AbstractC05490vp<E> A02() {
        return A06();
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> A2a() {
        return A06();
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A3E() {
        return A06().A6F();
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> A5T(E e, BoundType boundType) {
        return A06().AAg(e, boundType).A2a();
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A6F() {
        return A06().A3E();
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A8P() {
        return A06().A8Q();
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A8Q() {
        return A06().A8P();
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> AAW(E e, BoundType boundType, E e2, BoundType boundType2) {
        return A06().AAW(e2, boundType2, e, boundType).A2a();
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> AAg(E e, BoundType boundType) {
        return A06().A5T(e, boundType).A2a();
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new C05500vq(this, entrySet().iterator());
    }

    @Override // X.AbstractC05120uD
    public final String toString() {
        return entrySet().toString();
    }

    @Override // X.AbstractC01640ff
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // X.AbstractC01640ff, java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) A04(tArr);
    }
}
