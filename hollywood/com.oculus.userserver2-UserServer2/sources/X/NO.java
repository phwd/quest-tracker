package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class NO<E> extends AbstractCollection<E> implements AbstractC0120Qz<E> {
    @MonotonicNonNullDecl
    public transient Set<E> A00;
    @MonotonicNonNullDecl
    public transient Set<Multiset.Entry<E>> A01;

    @Override // java.util.AbstractCollection, java.util.Collection, X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        A0k(e, 1);
        return true;
    }

    public abstract void clear();

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return A3F(obj, 1) > 0;
    }

    public final Iterator<Multiset.Entry<E>> A03() {
        if (!(this instanceof TreeMultiset)) {
            return new Nd((AbstractMapBasedMultiset) this);
        }
        return new SW((TreeMultiset) this);
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public int A0k(@NullableDecl E e, int i) {
        String str;
        if (!(this instanceof AbstractMapBasedMultiset)) {
            throw new UnsupportedOperationException();
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        if (i == 0) {
            return abstractMapBasedMultiset.A1D(e);
        }
        boolean z = true;
        boolean z2 = false;
        if (i > 0) {
            z2 = true;
        }
        if (z2) {
            int A04 = abstractMapBasedMultiset.A01.A04(e);
            if (A04 == -1) {
                abstractMapBasedMultiset.A01.A05(e, i);
                abstractMapBasedMultiset.A00 += (long) i;
                return 0;
            }
            RB<E> rb = abstractMapBasedMultiset.A01;
            Preconditions.checkElementIndex(A04, rb.A02);
            int i2 = rb.A05[A04];
            long j = (long) i;
            long j2 = ((long) i2) + j;
            if (j2 > 2147483647L) {
                z = false;
            }
            if (z) {
                RB<E> rb2 = abstractMapBasedMultiset.A01;
                Preconditions.checkElementIndex(A04, rb2.A02);
                rb2.A05[A04] = (int) j2;
                abstractMapBasedMultiset.A00 += j;
                return i2;
            }
            str = Strings.lenientFormat("too many occurrences: %s", Long.valueOf(j2));
        } else {
            str = Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i));
        }
        throw new IllegalArgumentException(str);
    }

    @Override // X.AbstractC0120Qz
    public Set<E> A1R() {
        Set<E> set = this.A00;
        if (set == null) {
            if (!(this instanceof AnonymousClass6s)) {
                set = new AnonymousClass9l(this);
            } else {
                set = new AnonymousClass6g<>((AnonymousClass6s) this);
            }
            this.A00 = set;
        }
        return set;
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public int A3F(@NullableDecl Object obj, int i) {
        if (!(this instanceof AbstractMapBasedMultiset)) {
            throw new UnsupportedOperationException();
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        if (i == 0) {
            return abstractMapBasedMultiset.A1D(obj);
        }
        boolean z = false;
        if (i > 0) {
            z = true;
        }
        if (z) {
            int A04 = abstractMapBasedMultiset.A01.A04(obj);
            if (A04 == -1) {
                return 0;
            }
            RB<E> rb = abstractMapBasedMultiset.A01;
            Preconditions.checkElementIndex(A04, rb.A02);
            int i2 = rb.A05[A04];
            if (i2 > i) {
                RB<E> rb2 = abstractMapBasedMultiset.A01;
                Preconditions.checkElementIndex(A04, rb2.A02);
                rb2.A05[A04] = i2 - i;
            } else {
                RB<E> rb3 = abstractMapBasedMultiset.A01;
                RB.A01(rb3, rb3.A07[A04], (int) (rb3.A06[A04] >>> 32));
                i = i2;
            }
            abstractMapBasedMultiset.A00 -= (long) i;
            return i2;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public int A3X(@NullableDecl E e, int i) {
        int A05;
        if (!(this instanceof AbstractMapBasedMultiset)) {
            QN.A00(i, "count");
            int A1D = A1D(e);
            int i2 = i - A1D;
            if (i2 > 0) {
                A0k(e, i2);
            } else if (i2 < 0) {
                A3F(e, -i2);
                return A1D;
            }
            return A1D;
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        QN.A00(i, "count");
        RB<E> rb = abstractMapBasedMultiset.A01;
        if (i == 0) {
            A05 = RB.A01(rb, e, Qc.A02(e));
        } else {
            A05 = rb.A05(e, i);
        }
        abstractMapBasedMultiset.A00 += (long) (i - A05);
        return A05;
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public boolean A3Y(@NullableDecl E e, int i, int i2) {
        long j;
        long j2;
        if (!(this instanceof AbstractMapBasedMultiset)) {
            QN.A00(i, "oldCount");
            QN.A00(i2, "newCount");
            if (A1D(e) != i) {
                return false;
            }
            A3X(e, i2);
            return true;
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        QN.A00(i, "oldCount");
        QN.A00(i2, "newCount");
        int A04 = abstractMapBasedMultiset.A01.A04(e);
        if (A04 != -1) {
            RB<E> rb = abstractMapBasedMultiset.A01;
            Preconditions.checkElementIndex(A04, rb.A02);
            if (rb.A05[A04] != i) {
                return false;
            }
            RB<E> rb2 = abstractMapBasedMultiset.A01;
            if (i2 == 0) {
                RB.A01(rb2, rb2.A07[A04], (int) (rb2.A06[A04] >>> 32));
                j2 = abstractMapBasedMultiset.A00 - ((long) i);
                abstractMapBasedMultiset.A00 = j2;
                return true;
            }
            Preconditions.checkElementIndex(A04, rb2.A02);
            rb2.A05[A04] = i2;
            j = abstractMapBasedMultiset.A00;
            i2 -= i;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 <= 0) {
                return true;
            }
            abstractMapBasedMultiset.A01.A05(e, i2);
            j = abstractMapBasedMultiset.A00;
        }
        j2 = j + ((long) i2);
        abstractMapBasedMultiset.A00 = j2;
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: X.NO<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        if (collection != null) {
            if (collection instanceof AbstractC0120Qz) {
                AbstractC0120Qz qz = (AbstractC0120Qz) collection;
                if (qz instanceof AbstractMapBasedMultiset) {
                    AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) qz;
                    if (!abstractMapBasedMultiset.isEmpty()) {
                        RB<E> rb = abstractMapBasedMultiset.A01;
                        int A02 = rb.A02();
                        while (A02 >= 0) {
                            Preconditions.checkElementIndex(A02, rb.A02);
                            Object obj = rb.A07[A02];
                            RB<E> rb2 = abstractMapBasedMultiset.A01;
                            Preconditions.checkElementIndex(A02, rb2.A02);
                            A0k(obj, rb2.A05[A02]);
                            rb = abstractMapBasedMultiset.A01;
                            A02 = rb.A03(A02);
                        }
                        return true;
                    }
                } else if (!qz.isEmpty()) {
                    for (Mh mh : qz.entrySet()) {
                        A0k(mh.A01(), mh.A00());
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

    @Override // X.AbstractC0120Qz
    public final Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.A01;
        if (set != null) {
            return set;
        }
        C00219k r0 = new C00219k(this);
        this.A01 = r0;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        if (collection instanceof AbstractC0120Qz) {
            collection = ((AbstractC0120Qz) collection).A1R();
        }
        return A1R().removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof AbstractC0120Qz) {
                collection = ((AbstractC0120Qz) collection).A1R();
            }
            return A1R().retainAll(collection);
        }
        throw null;
    }

    @Override // X.AbstractC0120Qz
    public final boolean contains(@NullableDecl Object obj) {
        if (A1D(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0120Qz
    public final boolean equals(@NullableDecl Object obj) {
        return R7.A00(this, obj);
    }

    @Override // X.AbstractC0120Qz
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
