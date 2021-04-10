package com.google.common.collect;

import X.AbstractC0370Ug;
import X.SX;
import X.UY;
import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements UY<E>, NavigableSet<E> {
    public transient ImmutableSortedSet A00;
    public final transient Comparator A01;

    public class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Comparator comparator;
        public final Object[] elements;

        public Object readResolve() {
            SX sx = new SX(this.comparator);
            sx.A04(this.elements);
            return sx.build();
        }

        public SerializedForm(Comparator comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }
    }

    private final ImmutableSortedSet A0G(Object obj, boolean z) {
        RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
        return regularImmutableSortedSet.A0J(regularImmutableSortedSet.A0I(obj, z), regularImmutableSortedSet.size());
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final AbstractC0370Ug A0E() {
        return ((RegularImmutableSortedSet) this).A00.iterator();
    }

    public final ImmutableSortedSet A0F() {
        RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
        Comparator reverseOrder = Collections.reverseOrder(regularImmutableSortedSet.A01);
        if (regularImmutableSortedSet.isEmpty()) {
            return A01(reverseOrder);
        }
        return new RegularImmutableSortedSet(regularImmutableSortedSet.A00.A0F(), reverseOrder);
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ Iterator descendingIterator() {
        return ((RegularImmutableSortedSet) this).A00.A0F().iterator();
    }

    public static RegularImmutableSortedSet A01(Comparator comparator) {
        if (NaturalOrdering.A00.equals(comparator)) {
            return RegularImmutableSortedSet.A01;
        }
        return new RegularImmutableSortedSet(ImmutableList.of(), comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.NavigableSet
    public final Object ceiling(Object obj) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A0I = regularImmutableSortedSet.A0I(obj, true);
            if (A0I == regularImmutableSortedSet.size()) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A0I);
        } else if (obj != null) {
            Iterator it = A0G(obj, true).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet descendingSet() {
        ImmutableSortedSet immutableSortedSet = this.A00;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet A0F = A0F();
        this.A00 = A0F;
        A0F.A00 = this;
        return A0F;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        if (!(this instanceof RegularImmutableSortedSet)) {
            return iterator().next();
        }
        RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
        if (!regularImmutableSortedSet.isEmpty()) {
            return regularImmutableSortedSet.A00.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableSet
    public final Object floor(Object obj) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A0H = regularImmutableSortedSet.A0H(obj, true) - 1;
            if (A0H == -1) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A0H);
        } else if (obj != null) {
            RegularImmutableSortedSet regularImmutableSortedSet2 = (RegularImmutableSortedSet) this;
            AbstractC0370Ug A0E = regularImmutableSortedSet2.A0J(0, regularImmutableSortedSet2.A0H(obj, true)).A00.A0F().iterator();
            if (A0E.hasNext()) {
                return A0E.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.NavigableSet
    public final Object higher(Object obj) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A0I = regularImmutableSortedSet.A0I(obj, false);
            if (A0I == regularImmutableSortedSet.size()) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A0I);
        } else if (obj != null) {
            Iterator it = A0G(obj, false).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.SortedSet
    public final Object last() {
        if (!(this instanceof RegularImmutableSortedSet)) {
            return ((RegularImmutableSortedSet) this).A00.A0F().iterator().next();
        }
        RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
        if (!regularImmutableSortedSet.isEmpty()) {
            return regularImmutableSortedSet.A00.get(regularImmutableSortedSet.size() - 1);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableSet
    public final Object lower(Object obj) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A0H = regularImmutableSortedSet.A0H(obj, false) - 1;
            if (A0H == -1) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A0H);
        } else if (obj != null) {
            RegularImmutableSortedSet regularImmutableSortedSet2 = (RegularImmutableSortedSet) this;
            AbstractC0370Ug A0E = regularImmutableSortedSet2.A0J(0, regularImmutableSortedSet2.A0H(obj, false)).A00.A0F().iterator();
            if (A0E.hasNext()) {
                return A0E.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.NavigableSet
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this.A01, toArray());
    }

    public ImmutableSortedSet(Comparator comparator) {
        this.A01 = comparator;
    }

    @Override // X.UY, java.util.SortedSet
    public final Comparator comparator() {
        return this.A01;
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet headSet(Object obj, boolean z) {
        if (obj != null) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            return regularImmutableSortedSet.A0J(0, regularImmutableSortedSet.A0H(obj, z));
        }
        throw null;
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet headSet(Object obj) {
        if (obj != null) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            return regularImmutableSortedSet.A0J(0, regularImmutableSortedSet.A0H(obj, false));
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == null) {
            throw null;
        } else if (obj2 != null) {
            boolean z3 = false;
            if (this.A01.compare(obj, obj2) <= 0) {
                z3 = true;
            }
            Preconditions.checkArgument(z3);
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) A0G(obj, z);
            return regularImmutableSortedSet.A0J(0, regularImmutableSortedSet.A0H(obj2, z2));
        } else {
            throw null;
        }
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet subSet(Object obj, Object obj2) {
        if (obj == null) {
            throw null;
        } else if (obj2 != null) {
            boolean z = false;
            if (this.A01.compare(obj, obj2) <= 0) {
                z = true;
            }
            Preconditions.checkArgument(z);
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) A0G(obj, true);
            return regularImmutableSortedSet.A0J(0, regularImmutableSortedSet.A0H(obj2, false));
        } else {
            throw null;
        }
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet tailSet(Object obj, boolean z) {
        if (obj != null) {
            return A0G(obj, z);
        }
        throw null;
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet tailSet(Object obj) {
        if (obj != null) {
            return A0G(obj, true);
        }
        throw null;
    }
}
