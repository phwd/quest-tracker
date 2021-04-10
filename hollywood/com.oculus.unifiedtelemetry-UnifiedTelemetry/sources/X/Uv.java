package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class Uv<E> extends AbstractCollection<E> implements AnonymousClass34<E> {
    @MonotonicNonNullDecl
    public transient Set<E> A00;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A01;

    public abstract int A05();

    public abstract Iterator<E> A06();

    public abstract Iterator<Multiset.Entry<E>> A07();

    @Override // java.util.AbstractCollection, java.util.Collection, X.AnonymousClass34
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        A11(e, 1);
        return true;
    }

    public abstract void clear();

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return A4f(obj, 1) > 0;
    }

    public Set<E> A04() {
        return new BY(this);
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public int A11(@NullableDecl E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass34
    public Set<E> A1t() {
        Set<E> set = this.A00;
        if (set != null) {
            return set;
        }
        Set<E> A04 = A04();
        this.A00 = A04;
        return A04;
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public int A4f(@NullableDecl Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public int A4x(@NullableDecl E e, int i) {
        AnonymousClass9M.A00(i, "count");
        int A1c = A1c(e);
        int i2 = i - A1c;
        if (i2 > 0) {
            A11(e, i2);
        } else if (i2 < 0) {
            A4f(e, -i2);
            return A1c;
        }
        return A1c;
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public boolean A4y(@NullableDecl E e, int i, int i2) {
        AnonymousClass9M.A00(i, "oldCount");
        AnonymousClass9M.A00(i2, "newCount");
        if (A1c(e) != i) {
            return false;
        }
        A4x(e, i2);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: X.Uv<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        if (collection != null) {
            if (collection instanceof AnonymousClass34) {
                AnonymousClass34 r6 = (AnonymousClass34) collection;
                if (r6 instanceof AbstractMapBasedMultiset) {
                    AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) r6;
                    if (!abstractMapBasedMultiset.isEmpty()) {
                        AnonymousClass3s<E> r1 = abstractMapBasedMultiset.A01;
                        int A04 = r1.A04();
                        while (A04 >= 0) {
                            Preconditions.checkElementIndex(A04, r1.A02);
                            Object obj = r1.A07[A04];
                            AnonymousClass3s<E> r12 = abstractMapBasedMultiset.A01;
                            Preconditions.checkElementIndex(A04, r12.A02);
                            A11(obj, r12.A05[A04]);
                            r1 = abstractMapBasedMultiset.A01;
                            A04 = r1.A05(A04);
                        }
                        return true;
                    }
                } else if (!r6.isEmpty()) {
                    for (AbstractC0181Ug ug : r6.entrySet()) {
                        A11(ug.A01(), ug.A00());
                    }
                    return true;
                }
            } else if (!collection.isEmpty()) {
                Iterator<? extends E> it = collection.iterator();
                if (it != null) {
                    boolean z = false;
                    while (it.hasNext()) {
                        z |= add(it.next());
                    }
                    return z;
                }
                throw null;
            }
            return false;
        }
        throw null;
    }

    @Override // X.AnonymousClass34
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A01;
        if (set != null) {
            return set;
        }
        BX bx = new BX(this);
        this.A01 = bx;
        return bx;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        if (collection instanceof AnonymousClass34) {
            collection = ((AnonymousClass34) collection).A1t();
        }
        return A1t().removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof AnonymousClass34) {
                collection = ((AnonymousClass34) collection).A1t();
            }
            return A1t().retainAll(collection);
        }
        throw null;
    }

    @Override // X.AnonymousClass34
    public final boolean contains(@NullableDecl Object obj) {
        if (A1c(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass34
    public final boolean equals(@NullableDecl Object obj) {
        return AnonymousClass3g.A00(this, obj);
    }

    @Override // X.AnonymousClass34
    public final int hashCode() {
        return entrySet().hashCode();
    }

    public final boolean isEmpty() {
        return entrySet().isEmpty();
    }

    public final String toString() {
        return entrySet().toString();
    }
}
