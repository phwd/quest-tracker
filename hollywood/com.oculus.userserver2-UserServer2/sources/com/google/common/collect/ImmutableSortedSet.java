package com.google.common.collect;

import X.AnonymousClass6l;
import X.S1;
import X.TW;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.oculus.common.build.BuildConfig;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, S1<E> {
    @GwtIncompatible
    @LazyInit
    public transient ImmutableSortedSet<E> A00;
    public final transient Comparator<? super E> A01;

    public static class SerializedForm<E> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Comparator<? super E> comparator;
        public final Object[] elements;

        public Object readResolve() {
            AnonymousClass6l r1 = new AnonymousClass6l(this.comparator);
            r1.A04(this.elements);
            return r1.build();
        }

        public SerializedForm(Comparator<? super E> comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }
    }

    private final ImmutableSortedSet<E> A0I(E e, boolean z) {
        RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
        return RegularImmutableSortedSet.A02(regularImmutableSortedSet, RegularImmutableSortedSet.A01(regularImmutableSortedSet, e, z), regularImmutableSortedSet.size());
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final TW<E> A0H() {
        return ((RegularImmutableSortedSet) this).A00.iterator();
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ Iterator descendingIterator() {
        return ((RegularImmutableSortedSet) this).A00.A0I().iterator();
    }

    public static <E> RegularImmutableSortedSet<E> A04(Comparator<? super E> comparator) {
        return NaturalOrdering.A00.equals(comparator) ? (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.A01 : new RegularImmutableSortedSet<>(ImmutableList.of(), comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [E, java.lang.Object] */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final E ceiling(E e) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A012 = RegularImmutableSortedSet.A01(regularImmutableSortedSet, e, true);
            if (A012 == regularImmutableSortedSet.size()) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A012);
        } else if (e != null) {
            Iterator<T> it = A0I(e, true).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final NavigableSet descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.A00;
        if (immutableSortedSet == null) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            Comparator reverseOrder = Collections.reverseOrder(regularImmutableSortedSet.A01);
            if (regularImmutableSortedSet.isEmpty()) {
                immutableSortedSet = A04(reverseOrder);
            } else {
                immutableSortedSet = new RegularImmutableSortedSet<>(regularImmutableSortedSet.A00.A0I(), reverseOrder);
            }
            this.A00 = immutableSortedSet;
            immutableSortedSet.A00 = this;
        }
        return immutableSortedSet;
    }

    @Override // java.util.SortedSet
    public final E first() {
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
    @GwtIncompatible
    public final E floor(E e) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A002 = RegularImmutableSortedSet.A00(regularImmutableSortedSet, e, true) - 1;
            if (A002 == -1) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A002);
        } else if (e != null) {
            RegularImmutableSortedSet regularImmutableSortedSet2 = (RegularImmutableSortedSet) this;
            TW<E> A0H = RegularImmutableSortedSet.A02(regularImmutableSortedSet2, 0, RegularImmutableSortedSet.A00(regularImmutableSortedSet2, e, true)).A00.A0I().iterator();
            if (A0H.hasNext()) {
                return A0H.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [E, java.lang.Object] */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final E higher(E e) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A012 = RegularImmutableSortedSet.A01(regularImmutableSortedSet, e, false);
            if (A012 == regularImmutableSortedSet.size()) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A012);
        } else if (e != null) {
            Iterator<T> it = A0I(e, false).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.SortedSet
    public final E last() {
        if (!(this instanceof RegularImmutableSortedSet)) {
            return ((RegularImmutableSortedSet) this).A00.A0I().iterator().next();
        }
        RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
        if (!regularImmutableSortedSet.isEmpty()) {
            return regularImmutableSortedSet.A00.get(regularImmutableSortedSet.size() - 1);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final E lower(E e) {
        if (this instanceof RegularImmutableSortedSet) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            int A002 = RegularImmutableSortedSet.A00(regularImmutableSortedSet, e, false) - 1;
            if (A002 == -1) {
                return null;
            }
            return regularImmutableSortedSet.A00.get(A002);
        } else if (e != null) {
            RegularImmutableSortedSet regularImmutableSortedSet2 = (RegularImmutableSortedSet) this;
            TW<E> A0H = RegularImmutableSortedSet.A02(regularImmutableSortedSet2, 0, RegularImmutableSortedSet.A00(regularImmutableSortedSet2, e, false)).A00.A0I().iterator();
            if (A0H.hasNext()) {
                return A0H.next();
            }
            return null;
        } else {
            throw null;
        }
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    @Deprecated
    @CanIgnoreReturnValue
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    @Deprecated
    @CanIgnoreReturnValue
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this.A01, toArray());
    }

    public ImmutableSortedSet(Comparator<? super E> comparator) {
        this.A01 = comparator;
    }

    @Override // java.util.SortedSet, X.S1
    public final Comparator<? super E> comparator() {
        return this.A01;
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet headSet(Object obj, boolean z) {
        if (obj != null) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            return RegularImmutableSortedSet.A02(regularImmutableSortedSet, 0, RegularImmutableSortedSet.A00(regularImmutableSortedSet, obj, z));
        }
        throw null;
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet headSet(Object obj) {
        if (obj != null) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) this;
            return RegularImmutableSortedSet.A02(regularImmutableSortedSet, 0, RegularImmutableSortedSet.A00(regularImmutableSortedSet, obj, false));
        }
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == 0) {
            throw null;
        } else if (obj2 == null) {
            throw null;
        } else if (this.A01.compare(obj, obj2) <= 0) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) A0I(obj, z);
            return RegularImmutableSortedSet.A02(regularImmutableSortedSet, 0, RegularImmutableSortedSet.A00(regularImmutableSortedSet, obj2, z2));
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet subSet(Object obj, Object obj2) {
        if (obj == 0) {
            throw null;
        } else if (obj2 == null) {
            throw null;
        } else if (this.A01.compare(obj, obj2) <= 0) {
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) A0I(obj, true);
            return RegularImmutableSortedSet.A02(regularImmutableSortedSet, 0, RegularImmutableSortedSet.A00(regularImmutableSortedSet, obj2, false));
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet tailSet(Object obj, boolean z) {
        if (obj != 0) {
            return A0I(obj, z);
        }
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet tailSet(Object obj) {
        if (obj != 0) {
            return A0I(obj, true);
        }
        throw null;
    }
}
