package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.horizon.api.rating.ReviewsRequest;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0fp  reason: invalid class name */
public abstract class AnonymousClass0fp<E> extends AbstractCollection<E> implements AbstractC05490vp<E> {
    @MonotonicNonNullDecl
    public transient Set<E> A00;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A01;

    public abstract int A04();

    public abstract Iterator<E> A05();

    public abstract Iterator<Multiset.Entry<E>> A06();

    @Override // java.util.AbstractCollection, java.util.Collection, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        A1A(e, 1);
        return true;
    }

    public abstract void clear();

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return A92(obj, 1) > 0;
    }

    public Set<E> A03() {
        return new AnonymousClass0Nb(this);
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public int A1A(@NullableDecl E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC05490vp
    public Set<E> A2k() {
        Set<E> set = this.A00;
        if (set != null) {
            return set;
        }
        Set<E> A03 = A03();
        this.A00 = A03;
        return A03;
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public int A92(@NullableDecl Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public int A9n(@NullableDecl E e, int i) {
        AnonymousClass0th.A00(i, ReviewsRequest.KEY_COUNT);
        int A2J = A2J(e);
        int i2 = i - A2J;
        if (i2 > 0) {
            A1A(e, i2);
        } else if (i2 < 0) {
            A92(e, -i2);
            return A2J;
        }
        return A2J;
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public boolean A9o(@NullableDecl E e, int i, int i2) {
        AnonymousClass0th.A00(i, "oldCount");
        AnonymousClass0th.A00(i2, "newCount");
        if (A2J(e) != i) {
            return false;
        }
        A9n(e, i2);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: X.0fp<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw null;
        } else if (collection instanceof AbstractC05490vp) {
            AbstractC05490vp r6 = (AbstractC05490vp) collection;
            if (r6 instanceof AbstractMapBasedMultiset) {
                AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) r6;
                if (abstractMapBasedMultiset.isEmpty()) {
                    return false;
                }
                AnonymousClass0vu<E> r1 = abstractMapBasedMultiset.A01;
                int A03 = r1.A03();
                while (A03 >= 0) {
                    Preconditions.checkElementIndex(A03, r1.A01);
                    Object obj = r1.A06[A03];
                    AnonymousClass0vu<E> r12 = abstractMapBasedMultiset.A01;
                    Preconditions.checkElementIndex(A03, r12.A01);
                    A1A(obj, r12.A04[A03]);
                    r1 = abstractMapBasedMultiset.A01;
                    A03 = r1.A04(A03);
                }
                return true;
            } else if (r6.isEmpty()) {
                return false;
            } else {
                for (AnonymousClass0f2 r0 : r6.entrySet()) {
                    A1A(r0.A01(), r0.A00());
                }
                return true;
            }
        } else if (!collection.isEmpty()) {
            return C05250uz.A00(this, collection.iterator());
        } else {
            return false;
        }
    }

    @Override // X.AbstractC05490vp
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A01;
        if (set != null) {
            return set;
        }
        AnonymousClass0Na r0 = new AnonymousClass0Na(this);
        this.A01 = r0;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        if (collection instanceof AbstractC05490vp) {
            collection = ((AbstractC05490vp) collection).A2k();
        }
        return A2k().removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof AbstractC05490vp) {
                collection = ((AbstractC05490vp) collection).A2k();
            }
            return A2k().retainAll(collection);
        }
        throw null;
    }

    @Override // X.AbstractC05490vp
    public final boolean contains(@NullableDecl Object obj) {
        if (A2J(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC05490vp
    public final boolean equals(@NullableDecl Object obj) {
        return C05510vr.A00(this, obj);
    }

    @Override // X.AbstractC05490vp
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
