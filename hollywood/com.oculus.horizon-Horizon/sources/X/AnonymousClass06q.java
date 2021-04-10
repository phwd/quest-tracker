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
/* renamed from: X.06q  reason: invalid class name */
public abstract class AnonymousClass06q<E> extends AnonymousClass0CH<E> implements AnonymousClass0Bk<E> {
    @MonotonicNonNullDecl
    public transient Comparator<? super E> A00;
    @MonotonicNonNullDecl
    public transient NavigableSet<E> A01;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A02;

    public abstract AnonymousClass0Bk<E> A03();

    public abstract Iterator<Multiset.Entry<E>> A04();

    @Override // X.AnonymousClass0Bk
    /* renamed from: A2M */
    public final NavigableSet<E> A2N() {
        NavigableSet<E> navigableSet = this.A01;
        if (navigableSet != null) {
            return navigableSet;
        }
        AnonymousClass06b r0 = new AnonymousClass06b(this);
        this.A01 = r0;
        return r0;
    }

    @Override // X.AbstractC07200rb, X.AnonymousClass0Bk
    public final Comparator<? super E> comparator() {
        AbstractC07150rI comparatorOrdering;
        Comparator<? super E> comparator = this.A00;
        if (comparator != null) {
            return comparator;
        }
        Comparator<? super E> comparator2 = A03().comparator();
        if (comparator2 instanceof AbstractC07150rI) {
            comparatorOrdering = (AbstractC07150rI) comparator2;
        } else {
            comparatorOrdering = new ComparatorOrdering(comparator2);
        }
        AbstractC07150rI A002 = comparatorOrdering.A00();
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass0CH, X.AnonymousClass0r9, X.AnonymousClass0Bk
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A02;
        if (set != null) {
            return set;
        }
        AnonymousClass0CJ r0 = new AnonymousClass0CJ(this);
        this.A02 = r0;
        return r0;
    }

    @Override // X.AnonymousClass0CH
    /* renamed from: A02 */
    public final AnonymousClass0r9<E> A01() {
        return A03();
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A2E() {
        return A03();
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A2p() {
        return A03().A5C();
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A4j(E e, BoundType boundType) {
        return A03().A9c(e, boundType).A2E();
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A5C() {
        return A03().A2p();
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A7M() {
        return A03().A7N();
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A7N() {
        return A03().A7M();
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A9W(E e, BoundType boundType, E e2, BoundType boundType2) {
        return A03().A9W(e2, boundType2, e, boundType).A2E();
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A9c(E e, BoundType boundType) {
        return A03().A4j(e, boundType).A2E();
    }

    @Override // X.AnonymousClass0eD, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new C07130rB(this, entrySet().iterator());
    }

    @Override // X.AbstractC06680pa
    public final String toString() {
        return entrySet().toString();
    }

    @Override // X.AnonymousClass0eD
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // X.AnonymousClass0eD, java.util.Collection
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
