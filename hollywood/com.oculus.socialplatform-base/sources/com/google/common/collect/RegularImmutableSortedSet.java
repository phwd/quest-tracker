package com.google.common.collect;

import X.AbstractC05490vp;
import X.AbstractC05710wh;
import X.AnonymousClass0wF;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    public static final RegularImmutableSortedSet<Comparable> A01 = new RegularImmutableSortedSet<>(ImmutableList.of(), NaturalOrdering.A00);
    @VisibleForTesting
    public final transient ImmutableList<E> A00;

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public final E ceiling(E e) {
        int A0R = A0R(e, true);
        if (A0R == size()) {
            return null;
        }
        return this.A00.get(A0R);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(@NullableDecl Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return Collections.binarySearch(this.A00, obj, this.A01) >= 0;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A[Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }] */
    @Override // com.google.common.collect.ImmutableSet
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r8) {
        /*
            r7 = this;
            r6 = 1
            if (r8 == r7) goto L_0x0048
            boolean r0 = r8 instanceof java.util.Set
            r5 = 0
            if (r0 == 0) goto L_0x0047
            java.util.Set r8 = (java.util.Set) r8
            int r1 = r7.size()
            int r0 = r8.size()
            if (r1 != r0) goto L_0x0047
            boolean r0 = r7.isEmpty()
            if (r0 != 0) goto L_0x0048
            java.util.Comparator<? super E> r4 = r7.A01
            boolean r0 = A00(r4, r8)
            if (r0 == 0) goto L_0x0042
            java.util.Iterator r3 = r8.iterator()
            X.0wh r2 = r7.iterator()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
        L_0x002a:
            boolean r0 = r2.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
            if (r0 == 0) goto L_0x0048
            java.lang.Object r1 = r2.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
            java.lang.Object r0 = r3.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
            if (r0 == 0) goto L_0x0047
            int r0 = r4.compare(r1, r0)     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
            if (r0 == 0) goto L_0x002a
            return r5
        L_0x0041:
            return r5
        L_0x0042:
            boolean r0 = r7.containsAll(r8)
            return r0
        L_0x0047:
            return r5
        L_0x0048:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableSortedSet.equals(java.lang.Object):boolean");
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public final E floor(E e) {
        int A0Q = A0Q(e, true) - 1;
        if (A0Q == -1) {
            return null;
        }
        return this.A00.get(A0Q);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public final E higher(E e) {
        int A0R = A0R(e, false);
        if (A0R == size()) {
            return null;
        }
        return this.A00.get(A0R);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public final E lower(E e) {
        int A0Q = A0Q(e, false) - 1;
        if (A0Q == -1) {
            return null;
        }
        return this.A00.get(A0Q);
    }

    public static boolean A00(Comparator<?> comparator, Iterable<?> iterable) {
        Comparator<? super E> comparator2;
        if (comparator == null) {
            throw null;
        } else if (iterable != null) {
            if (iterable instanceof SortedSet) {
                comparator2 = ((SortedSet) iterable).comparator();
                if (comparator2 == null) {
                    comparator2 = NaturalOrdering.A00;
                }
            } else if (!(iterable instanceof AnonymousClass0wF)) {
                return false;
            } else {
                comparator2 = ((AnonymousClass0wF) iterable).comparator();
            }
            return comparator.equals(comparator2);
        } else {
            throw null;
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A0F() {
        return this.A00.A0F();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int A0G(Object[] objArr, int i) {
        return this.A00.A0G(objArr, i);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final AbstractC05710wh<E> A0I() {
        return this.A00.iterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0L() {
        Comparator reverseOrder = Collections.reverseOrder(this.A01);
        if (isEmpty()) {
            return ImmutableSortedSet.A01(reverseOrder);
        }
        return new RegularImmutableSortedSet(this.A00.A0J(), reverseOrder);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    public final AbstractC05710wh<E> A0P() {
        return this.A00.A0J().iterator();
    }

    public final int A0Q(E e, boolean z) {
        ImmutableList<E> immutableList = this.A00;
        if (e != null) {
            int binarySearch = Collections.binarySearch(immutableList, e, comparator());
            if (binarySearch < 0) {
                return binarySearch ^ -1;
            }
            if (z) {
                return binarySearch + 1;
            }
            return binarySearch;
        }
        throw null;
    }

    public final int A0R(E e, boolean z) {
        ImmutableList<E> immutableList = this.A00;
        if (e != null) {
            int binarySearch = Collections.binarySearch(immutableList, e, comparator());
            if (binarySearch < 0) {
                return binarySearch ^ -1;
            }
            if (!z) {
                return binarySearch + 1;
            }
            return binarySearch;
        }
        throw null;
    }

    public final RegularImmutableSortedSet<E> A0S(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i < i2) {
            return new RegularImmutableSortedSet<>(this.A00.subList(i, i2), this.A01);
        }
        return ImmutableSortedSet.A01(this.A01);
    }

    @Override // java.util.Collection, java.util.Set, java.util.AbstractCollection
    public final boolean containsAll(Collection<?> collection) {
        if (collection instanceof AbstractC05490vp) {
            collection = ((AbstractC05490vp) collection).A2k();
        }
        if (!A00(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        AbstractC05710wh<E> A0I = iterator();
        Iterator<?> it = collection.iterator();
        if (A0I.hasNext()) {
            Object next = it.next();
            E next2 = A0I.next();
            while (true) {
                try {
                    int compare = this.A01.compare(next2, next);
                    if (compare >= 0) {
                        if (compare != 0) {
                            break;
                        } else if (!it.hasNext()) {
                            return true;
                        } else {
                            next = it.next();
                        }
                    } else if (!A0I.hasNext()) {
                        break;
                    } else {
                        next2 = A0I.next();
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
        }
        return false;
        return false;
    }

    public final int size() {
        return this.A00.size();
    }

    public RegularImmutableSortedSet(ImmutableList<E> immutableList, Comparator<? super E> comparator) {
        super(comparator);
        this.A00 = immutableList;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> A0H() {
        return this.A00;
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0M(E e, boolean z) {
        return A0S(0, A0Q(e, z));
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0N(E e, boolean z) {
        return A0S(A0R(e, z), size());
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0O(E e, boolean z, E e2, boolean z2) {
        return A0N(e, z).A0M(e2, z2);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public final E first() {
        if (!isEmpty()) {
            return this.A00.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public final E last() {
        if (!isEmpty()) {
            return this.A00.get(size() - 1);
        }
        throw new NoSuchElementException();
    }
}
