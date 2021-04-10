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
/* renamed from: X.0C0  reason: invalid class name */
public abstract class AnonymousClass0C0<E> extends AnonymousClass0JK<E> implements AnonymousClass0Ie<E> {
    @MonotonicNonNullDecl
    public transient Comparator<? super E> A00;
    @MonotonicNonNullDecl
    public transient NavigableSet<E> A01;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A02;

    public abstract AnonymousClass0Ie<E> A03();

    public abstract Iterator<Multiset.Entry<E>> A04();

    @Override // X.AnonymousClass0Ie
    /* renamed from: A2F */
    public final NavigableSet<E> A2G() {
        NavigableSet<E> navigableSet = this.A01;
        if (navigableSet != null) {
            return navigableSet;
        }
        AnonymousClass0BY r0 = new AnonymousClass0BY(this);
        this.A01 = r0;
        return r0;
    }

    @Override // X.AbstractC07460te, X.AnonymousClass0Ie
    public final Comparator<? super E> comparator() {
        AnonymousClass0tL comparatorOrdering;
        Comparator<? super E> comparator = this.A00;
        if (comparator != null) {
            return comparator;
        }
        Comparator<? super E> comparator2 = A03().comparator();
        if (comparator2 instanceof AnonymousClass0tL) {
            comparatorOrdering = (AnonymousClass0tL) comparator2;
        } else {
            comparatorOrdering = new ComparatorOrdering(comparator2);
        }
        AnonymousClass0tL A002 = comparatorOrdering.A00();
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass0tC, X.AnonymousClass0JK, X.AnonymousClass0Ie
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A02;
        if (set != null) {
            return set;
        }
        AnonymousClass0JL r0 = new AnonymousClass0JL(this);
        this.A02 = r0;
        return r0;
    }

    @Override // X.AnonymousClass0JK
    /* renamed from: A02 */
    public final AnonymousClass0tC<E> A01() {
        return A03();
    }

    @Override // X.AnonymousClass0Ie
    public final AnonymousClass0Ie<E> A26() {
        return A03();
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A2o() {
        return A03().A5d();
    }

    @Override // X.AnonymousClass0Ie
    public final AnonymousClass0Ie<E> A52(E e, BoundType boundType) {
        return A03().A8Y(e, boundType).A26();
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A5d() {
        return A03().A2o();
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A6q() {
        return A03().A6r();
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A6r() {
        return A03().A6q();
    }

    @Override // X.AnonymousClass0Ie
    public final AnonymousClass0Ie<E> A8V(E e, BoundType boundType, E e2, BoundType boundType2) {
        return A03().A8V(e2, boundType2, e, boundType).A26();
    }

    @Override // X.AnonymousClass0Ie
    public final AnonymousClass0Ie<E> A8Y(E e, BoundType boundType) {
        return A03().A52(e, boundType).A26();
    }

    @Override // java.util.Collection, X.AnonymousClass0YC, java.lang.Iterable
    public Iterator<E> iterator() {
        return new AnonymousClass0tE(this, entrySet().iterator());
    }

    @Override // X.AbstractC07400rb
    public final String toString() {
        return entrySet().toString();
    }

    @Override // X.AnonymousClass0YC
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // java.util.Collection, X.AnonymousClass0YC
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
