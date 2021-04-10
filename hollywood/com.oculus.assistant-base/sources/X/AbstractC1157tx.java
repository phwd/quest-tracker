package X;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.TreeMultiset;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.tx  reason: case insensitive filesystem */
public abstract class AbstractC1157tx<E> extends AbstractCollection<E> implements UM<E> {
    public transient Set A00;
    public transient Set A01;

    @Override // java.util.AbstractCollection, X.UM, java.util.Collection
    public final boolean add(Object obj) {
        A19(obj, 1);
        return true;
    }

    @Override // X.UM
    public final boolean remove(Object obj) {
        return A4m(obj, 1) > 0;
    }

    public final Iterator A03() {
        if (!(this instanceof TreeMultiset)) {
            return new C1154tu((AbstractMapBasedMultiset) this);
        }
        return new Ua((TreeMultiset) this);
    }

    @Override // X.UM
    public int A19(Object obj, int i) {
        if (!(this instanceof AbstractMapBasedMultiset)) {
            throw new UnsupportedOperationException();
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        if (i == 0) {
            return abstractMapBasedMultiset.A1V(obj);
        }
        boolean z = true;
        boolean z2 = false;
        if (i > 0) {
            z2 = true;
        }
        if (z2) {
            int A04 = abstractMapBasedMultiset.A01.A04(obj);
            if (A04 == -1) {
                abstractMapBasedMultiset.A01.A05(obj, i);
                abstractMapBasedMultiset.A00 += (long) i;
                return 0;
            }
            UQ uq = abstractMapBasedMultiset.A01;
            Preconditions.checkElementIndex(A04, uq.A02);
            int i2 = uq.A05[A04];
            long j = (long) i;
            long j2 = ((long) i2) + j;
            if (j2 > 2147483647L) {
                z = false;
            }
            if (z) {
                UQ uq2 = abstractMapBasedMultiset.A01;
                Preconditions.checkElementIndex(A04, uq2.A02);
                uq2.A05[A04] = (int) j2;
                abstractMapBasedMultiset.A00 += j;
                return i2;
            }
            throw new IllegalArgumentException(Strings.lenientFormat("too many occurrences: %s", Long.valueOf(j2)));
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.UM
    public Set A1m() {
        Set set = this.A00;
        if (set == null) {
            if (!(this instanceof Z2)) {
                set = new C0145Dk(this);
            } else {
                set = new SI((Z2) this);
            }
            this.A00 = set;
        }
        return set;
    }

    @Override // X.UM
    public int A4m(Object obj, int i) {
        if (!(this instanceof AbstractMapBasedMultiset)) {
            throw new UnsupportedOperationException();
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        if (i == 0) {
            return abstractMapBasedMultiset.A1V(obj);
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
            UQ uq = abstractMapBasedMultiset.A01;
            Preconditions.checkElementIndex(A04, uq.A02);
            int i2 = uq.A05[A04];
            if (i2 > i) {
                UQ uq2 = abstractMapBasedMultiset.A01;
                Preconditions.checkElementIndex(A04, uq2.A02);
                uq2.A05[A04] = i2 - i;
            } else {
                UQ uq3 = abstractMapBasedMultiset.A01;
                UQ.A00(uq3, uq3.A07[A04], (int) (uq3.A06[A04] >>> 32));
                i = i2;
            }
            abstractMapBasedMultiset.A00 -= (long) i;
            return i2;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.UM
    public int A4z(Object obj, int i) {
        int A05;
        if (!(this instanceof AbstractMapBasedMultiset)) {
            C0361Tm.A00(i, "count");
            int A1V = A1V(obj);
            int i2 = i - A1V;
            if (i2 > 0) {
                A19(obj, i2);
            } else if (i2 < 0) {
                A4m(obj, -i2);
                return A1V;
            }
            return A1V;
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        C0361Tm.A00(i, "count");
        UQ uq = abstractMapBasedMultiset.A01;
        if (i == 0) {
            A05 = UQ.A00(uq, obj, Tw.A02(obj));
        } else {
            A05 = uq.A05(obj, i);
        }
        abstractMapBasedMultiset.A00 += (long) (i - A05);
        return A05;
    }

    @Override // X.UM
    public boolean A50(Object obj, int i, int i2) {
        long j;
        long j2;
        if (!(this instanceof AbstractMapBasedMultiset)) {
            C0361Tm.A00(i, "oldCount");
            C0361Tm.A00(i2, "newCount");
            if (A1V(obj) != i) {
                return false;
            }
            A4z(obj, i2);
            return true;
        }
        AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) this;
        C0361Tm.A00(i, "oldCount");
        C0361Tm.A00(i2, "newCount");
        int A04 = abstractMapBasedMultiset.A01.A04(obj);
        if (A04 != -1) {
            UQ uq = abstractMapBasedMultiset.A01;
            Preconditions.checkElementIndex(A04, uq.A02);
            if (uq.A05[A04] != i) {
                return false;
            }
            UQ uq2 = abstractMapBasedMultiset.A01;
            if (i2 == 0) {
                UQ.A00(uq2, uq2.A07[A04], (int) (uq2.A06[A04] >>> 32));
                j2 = abstractMapBasedMultiset.A00 - ((long) i);
                abstractMapBasedMultiset.A00 = j2;
                return true;
            }
            Preconditions.checkElementIndex(A04, uq2.A02);
            uq2.A05[A04] = i2;
            j = abstractMapBasedMultiset.A00;
            i2 -= i;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 <= 0) {
                return true;
            }
            abstractMapBasedMultiset.A01.A05(obj, i2);
            j = abstractMapBasedMultiset.A00;
        }
        j2 = j + ((long) i2);
        abstractMapBasedMultiset.A00 = j2;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection == null) {
            throw null;
        } else if (collection instanceof UM) {
            UM um = (UM) collection;
            if (um instanceof AbstractMapBasedMultiset) {
                AbstractMapBasedMultiset abstractMapBasedMultiset = (AbstractMapBasedMultiset) um;
                if (abstractMapBasedMultiset.isEmpty()) {
                    return false;
                }
                UQ uq = abstractMapBasedMultiset.A01;
                int A02 = uq.A02();
                while (A02 >= 0) {
                    Preconditions.checkElementIndex(A02, uq.A02);
                    Object obj = uq.A07[A02];
                    UQ uq2 = abstractMapBasedMultiset.A01;
                    Preconditions.checkElementIndex(A02, uq2.A02);
                    A19(obj, uq2.A05[A02]);
                    uq = abstractMapBasedMultiset.A01;
                    A02 = uq.A03(A02);
                }
                return true;
            } else if (um.isEmpty()) {
                return false;
            } else {
                for (E e : um.entrySet()) {
                    A19(e.A01(), e.A00());
                }
                return true;
            }
        } else if (!collection.isEmpty()) {
            return UB.A01(this, collection.iterator());
        } else {
            return false;
        }
    }

    @Override // X.UM
    public final Set entrySet() {
        Set set = this.A01;
        if (set != null) {
            return set;
        }
        Dj dj = new Dj(this);
        this.A01 = dj;
        return dj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.Set */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection instanceof UM) {
            collection = ((UM) collection).A1m();
        }
        return A1m().removeAll(collection);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Set */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        if (collection != null) {
            if (collection instanceof UM) {
                collection = ((UM) collection).A1m();
            }
            return A1m().retainAll(collection);
        }
        throw null;
    }

    @Override // X.UM
    public final boolean contains(Object obj) {
        if (A1V(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.UM
    public final boolean equals(Object obj) {
        return UO.A00(this, obj);
    }

    @Override // X.UM
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
