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
/* renamed from: X.0YK  reason: invalid class name */
public abstract class AnonymousClass0YK<E> extends AbstractCollection<E> implements AnonymousClass0tC<E> {
    @MonotonicNonNullDecl
    public transient Set<E> A00;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A01;

    public abstract int A04();

    public abstract Iterator<E> A05();

    public abstract Iterator<Multiset.Entry<E>> A06();

    @Override // java.util.AbstractCollection, X.AnonymousClass0tC, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        A0x(e, 1);
        return true;
    }

    public abstract void clear();

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return A7L(obj, 1) > 0;
    }

    public Set<E> A03() {
        return new AnonymousClass0JO(this);
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public int A0x(@NullableDecl E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0tC
    public Set<E> A2G() {
        Set<E> set = this.A00;
        if (set != null) {
            return set;
        }
        Set<E> A03 = A03();
        this.A00 = A03;
        return A03;
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public int A7L(@NullableDecl Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public int A7q(@NullableDecl E e, int i) {
        C07340r5.A00(i, "count");
        int A1s = A1s(e);
        int i2 = i - A1s;
        if (i2 > 0) {
            A0x(e, i2);
        } else if (i2 < 0) {
            A7L(e, -i2);
            return A1s;
        }
        return A1s;
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public boolean A7r(@NullableDecl E e, int i, int i2) {
        C07340r5.A00(i, "oldCount");
        C07340r5.A00(i2, "newCount");
        if (A1s(e) != i) {
            return false;
        }
        A7q(e, i2);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: X.0YK<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        if (collection != null) {
            if (collection instanceof AnonymousClass0tC) {
                AnonymousClass0tC r6 = (AnonymousClass0tC) collection;
                if (r6 instanceof AbstractMapBasedMultiset) {
                    AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) r6;
                    if (!abstractMapBasedMultiset.isEmpty()) {
                        AnonymousClass0tI<E> r1 = abstractMapBasedMultiset.A01;
                        int A02 = r1.A02();
                        while (A02 >= 0) {
                            Preconditions.checkElementIndex(A02, r1.A02);
                            Object obj = r1.A07[A02];
                            AnonymousClass0tI<E> r12 = abstractMapBasedMultiset.A01;
                            Preconditions.checkElementIndex(A02, r12.A02);
                            A0x(obj, r12.A05[A02]);
                            r1 = abstractMapBasedMultiset.A01;
                            A02 = r1.A03(A02);
                        }
                        return true;
                    }
                } else if (!r6.isEmpty()) {
                    for (AnonymousClass0Y0 r0 : r6.entrySet()) {
                        A0x(r0.A01(), r0.A00());
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

    @Override // X.AnonymousClass0tC
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A01;
        if (set != null) {
            return set;
        }
        AnonymousClass0JN r0 = new AnonymousClass0JN(this);
        this.A01 = r0;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        if (collection instanceof AnonymousClass0tC) {
            collection = ((AnonymousClass0tC) collection).A2G();
        }
        return A2G().removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof AnonymousClass0tC) {
                collection = ((AnonymousClass0tC) collection).A2G();
            }
            return A2G().retainAll(collection);
        }
        throw null;
    }

    @Override // X.AnonymousClass0tC
    public final boolean contains(@NullableDecl Object obj) {
        if (A1s(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0tC
    public final boolean equals(@NullableDecl Object obj) {
        return AnonymousClass0tF.A00(this, obj);
    }

    @Override // X.AnonymousClass0tC
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
