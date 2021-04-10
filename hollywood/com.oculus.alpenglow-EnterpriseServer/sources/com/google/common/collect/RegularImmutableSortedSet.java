package com.google.common.collect;

import X.AbstractC07460te;
import X.AnonymousClass0tC;
import X.AnonymousClass0u6;
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
        int A012 = A01(e, true);
        if (A012 == size()) {
            return null;
        }
        return this.A00.get(A012);
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
            boolean r0 = A03(r4, r8)
            if (r0 == 0) goto L_0x0042
            java.util.Iterator r3 = r8.iterator()
            X.0u6 r2 = r7.iterator()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
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
        int A002 = A00(e, true) - 1;
        if (A002 == -1) {
            return null;
        }
        return this.A00.get(A002);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public final E higher(E e) {
        int A012 = A01(e, false);
        if (A012 == size()) {
            return null;
        }
        return this.A00.get(A012);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public final E lower(E e) {
        int A002 = A00(e, false) - 1;
        if (A002 == -1) {
            return null;
        }
        return this.A00.get(A002);
    }

    private final int A00(E e, boolean z) {
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

    private final int A01(E e, boolean z) {
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

    private final RegularImmutableSortedSet<E> A02(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i < i2) {
            return new RegularImmutableSortedSet<>(this.A00.subList(i, i2), this.A01);
        }
        return ImmutableSortedSet.A04(this.A01);
    }

    public static boolean A03(Comparator<?> comparator, Iterable<?> iterable) {
        Comparator<? super E> comparator2;
        if (comparator == null) {
            throw null;
        } else if (iterable != null) {
            if (iterable instanceof SortedSet) {
                comparator2 = ((SortedSet) iterable).comparator();
                if (comparator2 == null) {
                    comparator2 = NaturalOrdering.A00;
                }
            } else if (!(iterable instanceof AbstractC07460te)) {
                return false;
            } else {
                comparator2 = ((AbstractC07460te) iterable).comparator();
            }
            return comparator.equals(comparator2);
        } else {
            throw null;
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A08() {
        return this.A00.A08();
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0B() {
        Comparator reverseOrder = Collections.reverseOrder(this.A01);
        if (isEmpty()) {
            return ImmutableSortedSet.A04(reverseOrder);
        }
        return new RegularImmutableSortedSet(this.A00.reverse(), reverseOrder);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    /* renamed from: A0F */
    public final AnonymousClass0u6<E> descendingIterator() {
        return this.A00.reverse().iterator();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> asList() {
        return this.A00;
    }

    @Override // java.util.Collection, java.util.Set, java.util.AbstractCollection
    public final boolean containsAll(Collection<?> collection) {
        if (collection instanceof AnonymousClass0tC) {
            collection = ((AnonymousClass0tC) collection).A2G();
        }
        if (!A03(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        AnonymousClass0u6<E> it = iterator();
        Iterator<?> it2 = collection.iterator();
        if (it.hasNext()) {
            Object next = it2.next();
            E next2 = it.next();
            while (true) {
                try {
                    int compare = this.A01.compare(next2, next);
                    if (compare >= 0) {
                        if (compare != 0) {
                            break;
                        } else if (!it2.hasNext()) {
                            return true;
                        } else {
                            next = it2.next();
                        }
                    } else if (!it.hasNext()) {
                        break;
                    } else {
                        next2 = it.next();
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
        }
        return false;
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int copyIntoArray(Object[] objArr, int i) {
        return this.A00.copyIntoArray(objArr, i);
    }

    public final int size() {
        return this.A00.size();
    }

    public RegularImmutableSortedSet(ImmutableList<E> immutableList, Comparator<? super E> comparator) {
        super(comparator);
        this.A00 = immutableList;
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0C(E e, boolean z) {
        return A02(0, A00(e, z));
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0D(E e, boolean z) {
        return A02(A01(e, z), size());
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public final ImmutableSortedSet<E> A0E(E e, boolean z, E e2, boolean z2) {
        return A0D(e, z).A0C(e2, z2);
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

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.util.NavigableSet, java.lang.Iterable, java.util.AbstractCollection
    public final AnonymousClass0u6<E> iterator() {
        return this.A00.iterator();
    }
}
