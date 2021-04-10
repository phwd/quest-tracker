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
/* renamed from: X.0eM  reason: invalid class name */
public abstract class AnonymousClass0eM<E> extends AbstractCollection<E> implements AnonymousClass0r9<E> {
    @MonotonicNonNullDecl
    public transient Set<E> A00;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A01;

    public abstract int A04();

    public abstract Iterator<E> A05();

    public abstract Iterator<Multiset.Entry<E>> A06();

    @Override // java.util.AbstractCollection, java.util.Collection, X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        A11(e, 1);
        return true;
    }

    public abstract void clear();

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return A87(obj, 1) > 0;
    }

    public Set<E> A03() {
        return new AnonymousClass0CM(this);
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public int A11(@NullableDecl E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0r9
    public Set<E> A2N() {
        Set<E> set = this.A00;
        if (set != null) {
            return set;
        }
        Set<E> A03 = A03();
        this.A00 = A03;
        return A03;
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public int A87(@NullableDecl Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public int A8d(@NullableDecl E e, int i) {
        AnonymousClass0p6.A00(i, "count");
        int A1v = A1v(e);
        int i2 = i - A1v;
        if (i2 > 0) {
            A11(e, i2);
        } else if (i2 < 0) {
            A87(e, -i2);
            return A1v;
        }
        return A1v;
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public boolean A8e(@NullableDecl E e, int i, int i2) {
        AnonymousClass0p6.A00(i, "oldCount");
        AnonymousClass0p6.A00(i2, "newCount");
        if (A1v(e) != i) {
            return false;
        }
        A8d(e, i2);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: X.0eM<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw null;
        } else if (collection instanceof AnonymousClass0r9) {
            AnonymousClass0r9 r6 = (AnonymousClass0r9) collection;
            if (r6 instanceof AbstractMapBasedMultiset) {
                AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) r6;
                if (abstractMapBasedMultiset.isEmpty()) {
                    return false;
                }
                AnonymousClass0rF<E> r1 = abstractMapBasedMultiset.A01;
                int A03 = r1.A03();
                while (A03 >= 0) {
                    Preconditions.checkElementIndex(A03, r1.A01);
                    Object obj = r1.A06[A03];
                    AnonymousClass0rF<E> r12 = abstractMapBasedMultiset.A01;
                    Preconditions.checkElementIndex(A03, r12.A01);
                    A11(obj, r12.A04[A03]);
                    r1 = abstractMapBasedMultiset.A01;
                    A03 = r1.A04(A03);
                }
                return true;
            } else if (r6.isEmpty()) {
                return false;
            } else {
                for (AnonymousClass0dN r0 : r6.entrySet()) {
                    A11(r0.A01(), r0.A00());
                }
                return true;
            }
        } else if (!collection.isEmpty()) {
            return AnonymousClass0qL.A01(this, collection.iterator());
        } else {
            return false;
        }
    }

    @Override // X.AnonymousClass0r9
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A01;
        if (set != null) {
            return set;
        }
        AnonymousClass0CL r0 = new AnonymousClass0CL(this);
        this.A01 = r0;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        if (collection instanceof AnonymousClass0r9) {
            collection = ((AnonymousClass0r9) collection).A2N();
        }
        return A2N().removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof AnonymousClass0r9) {
                collection = ((AnonymousClass0r9) collection).A2N();
            }
            return A2N().retainAll(collection);
        }
        throw null;
    }

    @Override // X.AnonymousClass0r9
    public final boolean contains(@NullableDecl Object obj) {
        if (A1v(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0r9
    public final boolean equals(@NullableDecl Object obj) {
        return AnonymousClass0rC.A00(this, obj);
    }

    @Override // X.AnonymousClass0r9
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
