package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.ComparatorOrdering;
import com.google.common.collect.Multiset;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.ReverseNaturalOrdering;
import com.google.common.collect.ReverseOrdering;
import com.oculus.common.build.BuildConfig;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
/* renamed from: X.6q  reason: invalid class name */
public abstract class AnonymousClass6q<E> extends AbstractC00189h<E> implements AnonymousClass9K<E> {
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A00;
    @MonotonicNonNullDecl
    public transient Comparator<? super E> A01;
    @MonotonicNonNullDecl
    public transient NavigableSet<E> A02;

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A1M() {
        return ((C00050s) this).A00;
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A1Y() {
        return ((C00050s) this).A00.A2G();
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A26(E e, BoundType boundType) {
        return ((C00050s) this).A00.A3p(e, boundType).A1M();
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A2G() {
        return ((C00050s) this).A00.A1Y();
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A2r() {
        return ((C00050s) this).A00.A2s();
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A2s() {
        return ((C00050s) this).A00.A2r();
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A3n(E e, BoundType boundType, E e2, BoundType boundType2) {
        return ((C00050s) this).A00.A3n(e2, boundType2, e, boundType).A1M();
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A3p(E e, BoundType boundType) {
        return ((C00050s) this).A00.A26(e, boundType).A1M();
    }

    @Override // X.AnonymousClass9K
    public final NavigableSet<E> A1Q() {
        NavigableSet<E> navigableSet = this.A02;
        if (navigableSet != null) {
            return navigableSet;
        }
        AnonymousClass6g r0 = new AnonymousClass6g(this);
        this.A02 = r0;
        return r0;
    }

    @Override // X.AnonymousClass9K, X.S1
    public final Comparator<? super E> comparator() {
        RF comparatorOrdering;
        Comparator<? super E> comparator = this.A01;
        if (comparator == null) {
            Comparator<? super E> comparator2 = ((C00050s) this).A00.comparator();
            if (comparator2 instanceof RF) {
                comparatorOrdering = (RF) comparator2;
            } else {
                comparatorOrdering = new ComparatorOrdering(comparator2);
            }
            if (comparatorOrdering instanceof ReverseOrdering) {
                comparator = ((ReverseOrdering) comparatorOrdering).forwardOrder;
            } else if (comparatorOrdering instanceof ReverseNaturalOrdering) {
                comparator = NaturalOrdering.A00;
            } else if (!(comparatorOrdering instanceof NaturalOrdering)) {
                comparator = new ReverseOrdering<>(comparatorOrdering);
            } else {
                comparator = ReverseNaturalOrdering.A00;
            }
            this.A01 = comparator;
        }
        return comparator;
    }

    @Override // X.NA, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        if (!(this instanceof C00050s)) {
            return new R0(this, entrySet().iterator());
        }
        AnonymousClass9K<E> A1M = ((C00050s) this).A00.A1M();
        return new R0(A1M, A1M.entrySet().iterator());
    }

    @Override // X.QZ
    public final String toString() {
        return entrySet().toString();
    }

    @Override // X.NA
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // X.NA, java.util.Collection
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
