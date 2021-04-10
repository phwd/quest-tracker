package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.ComparatorOrdering;
import com.google.common.collect.Multiset;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtCompatible(emulated = true)
/* renamed from: X.6u  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00156u<E> extends BV<E> implements BA<E> {
    @MonotonicNonNullDecl
    public transient Comparator<? super E> A00;
    @MonotonicNonNullDecl
    public transient NavigableSet<E> A01;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A02;

    public abstract BA<E> A03();

    public abstract Iterator<Multiset.Entry<E>> A04();

    @Override // X.BA
    /* renamed from: A1s */
    public final NavigableSet<E> A1t() {
        NavigableSet<E> navigableSet = this.A01;
        if (navigableSet != null) {
            return navigableSet;
        }
        AnonymousClass6l r0 = new AnonymousClass6l(this);
        this.A01 = r0;
        return r0;
    }

    @Override // X.AnonymousClass6z, X.BA
    public final Comparator<? super E> comparator() {
        AnonymousClass4J comparatorOrdering;
        Comparator<? super E> comparator = this.A00;
        if (comparator != null) {
            return comparator;
        }
        Comparator<? super E> comparator2 = A03().comparator();
        if (comparator2 instanceof AnonymousClass4J) {
            comparatorOrdering = (AnonymousClass4J) comparator2;
        } else {
            comparatorOrdering = new ComparatorOrdering(comparator2);
        }
        AnonymousClass4J A002 = comparatorOrdering.A00();
        this.A00 = A002;
        return A002;
    }

    @Override // X.BV, X.BA, X.AnonymousClass34
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A02;
        if (set != null) {
            return set;
        }
        BW bw = new BW(this);
        this.A02 = bw;
        return bw;
    }

    @Override // X.BV
    /* renamed from: A02 */
    public final AnonymousClass34<E> A01() {
        return A03();
    }

    @Override // X.BA
    public final BA<E> A1o() {
        return A03();
    }

    @Override // X.BA
    public final Multiset.Entry<E> A2C() {
        return A03().A3M();
    }

    @Override // X.BA
    public final BA<E> A30(E e, BoundType boundType) {
        return A03().A5T(e, boundType).A1o();
    }

    @Override // X.BA
    public final Multiset.Entry<E> A3M() {
        return A03().A2C();
    }

    @Override // X.BA
    public final Multiset.Entry<E> A4E() {
        return A03().A4F();
    }

    @Override // X.BA
    public final Multiset.Entry<E> A4F() {
        return A03().A4E();
    }

    @Override // X.BA
    public final BA<E> A5Q(E e, BoundType boundType, E e2, BoundType boundType2) {
        return A03().A5Q(e2, boundType2, e, boundType).A1o();
    }

    @Override // X.BA
    public final BA<E> A5T(E e, BoundType boundType) {
        return A03().A30(e, boundType).A1o();
    }

    @Override // java.util.Collection, X.AbstractC0189Ur, java.lang.Iterable
    public Iterator<E> iterator() {
        return new AnonymousClass3L(this, entrySet().iterator());
    }

    @Override // X.AnonymousClass6I
    public final String toString() {
        return entrySet().toString();
    }

    @Override // X.AbstractC0189Ur
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // java.util.Collection, X.AbstractC0189Ur
    public final <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        int i = 0;
        for (E e : this) {
            tArr[i] = e;
            i++;
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }
}
