package com.google.common.collect;

import X.AbstractC05710wh;
import X.AnonymousClass0BY;
import X.AnonymousClass0wF;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, AnonymousClass0wF<E> {
    @GwtIncompatible
    @LazyInit
    public transient ImmutableSortedSet<E> A00;
    public final transient Comparator<? super E> A01;

    public static class SerializedForm<E> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Comparator<? super E> comparator;
        public final Object[] elements;

        public Object readResolve() {
            AnonymousClass0BY r1 = new AnonymousClass0BY(this.comparator);
            r1.A08(this.elements);
            return r1.build();
        }

        public SerializedForm(Comparator<? super E> comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public abstract AbstractC05710wh<E> A0I();

    @GwtIncompatible
    public abstract ImmutableSortedSet<E> A0L();

    public abstract ImmutableSortedSet<E> A0M(E e, boolean z);

    public abstract ImmutableSortedSet<E> A0N(E e, boolean z);

    public abstract ImmutableSortedSet<E> A0O(E e, boolean z, E e2, boolean z2);

    @GwtIncompatible
    /* renamed from: A0P */
    public abstract AbstractC05710wh<E> descendingIterator();

    /* JADX WARN: Type inference failed for: r2v1, types: [E, java.lang.Object] */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E ceiling(E e) {
        if (e != null) {
            Iterator<T> it = A0N(e, true).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E floor(E e) {
        if (e != null) {
            AbstractC05710wh<E> A0P = A0M(e, true).descendingIterator();
            if (A0P.hasNext()) {
                return A0P.next();
            }
            return null;
        }
        throw null;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [E, java.lang.Object] */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E higher(E e) {
        if (e != null) {
            Iterator<T> it = A0N(e, false).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E lower(E e) {
        if (e != null) {
            AbstractC05710wh<E> A0P = A0M(e, false).descendingIterator();
            if (A0P.hasNext()) {
                return A0P.next();
            }
            return null;
        }
        throw null;
    }

    public static <E> RegularImmutableSortedSet<E> A01(Comparator<? super E> comparator) {
        return NaturalOrdering.A00.equals(comparator) ? (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.A01 : new RegularImmutableSortedSet<>(ImmutableList.of(), comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.A00;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> A0L = A0L();
        this.A00 = A0L;
        A0L.A00 = this;
        return A0L;
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

    @Override // java.util.SortedSet, X.AnonymousClass0wF
    public final Comparator<? super E> comparator() {
        return this.A01;
    }

    @Override // java.util.SortedSet
    public E first() {
        return iterator().next();
    }

    @Override // java.util.SortedSet
    public E last() {
        return descendingIterator().next();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet headSet(Object obj, boolean z) {
        if (obj != 0) {
            return A0M(obj, z);
        }
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet headSet(Object obj) {
        if (obj != 0) {
            return A0M(obj, false);
        }
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == 0) {
            throw null;
        } else if (obj2 != 0) {
            boolean z3 = false;
            if (this.A01.compare(obj, obj2) <= 0) {
                z3 = true;
            }
            Preconditions.checkArgument(z3);
            return A0O(obj, z, obj2, z2);
        } else {
            throw null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet subSet(Object obj, Object obj2) {
        if (obj == 0) {
            throw null;
        } else if (obj2 != 0) {
            boolean z = false;
            if (this.A01.compare(obj, obj2) <= 0) {
                z = true;
            }
            Preconditions.checkArgument(z);
            return A0O(obj, true, obj2, false);
        } else {
            throw null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public final /* bridge */ /* synthetic */ NavigableSet tailSet(Object obj, boolean z) {
        if (obj != 0) {
            return A0N(obj, z);
        }
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.SortedSet, java.util.NavigableSet
    public final SortedSet tailSet(Object obj) {
        if (obj != 0) {
            return A0N(obj, true);
        }
        throw null;
    }
}
