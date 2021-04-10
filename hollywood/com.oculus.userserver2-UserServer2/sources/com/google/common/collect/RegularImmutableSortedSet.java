package com.google.common.collect;

import X.AbstractC0120Qz;
import X.S1;
import X.TW;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.oculus.common.build.BuildConfig;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    public static final RegularImmutableSortedSet<Comparable> A01 = new RegularImmutableSortedSet<>(ImmutableList.of(), NaturalOrdering.A00);
    @VisibleForTesting
    public final transient ImmutableList<E> A00;

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
            X.TW r2 = r7.iterator()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0041 }
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

    /* JADX WARN: Incorrect args count in method signature: (TE;Z)I */
    public static final int A00(RegularImmutableSortedSet regularImmutableSortedSet, Object obj, boolean z) {
        ImmutableList<E> immutableList = regularImmutableSortedSet.A00;
        if (obj != null) {
            int binarySearch = Collections.binarySearch(immutableList, obj, regularImmutableSortedSet.comparator());
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

    /* JADX WARN: Incorrect args count in method signature: (TE;Z)I */
    public static final int A01(RegularImmutableSortedSet regularImmutableSortedSet, Object obj, boolean z) {
        ImmutableList<E> immutableList = regularImmutableSortedSet.A00;
        if (obj != null) {
            int binarySearch = Collections.binarySearch(immutableList, obj, regularImmutableSortedSet.comparator());
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

    /* JADX WARN: Incorrect args count in method signature: (II)Lcom/google/common/collect/RegularImmutableSortedSet<TE;>; */
    public static final RegularImmutableSortedSet A02(RegularImmutableSortedSet regularImmutableSortedSet, int i, int i2) {
        if (i == 0 && i2 == regularImmutableSortedSet.size()) {
            return regularImmutableSortedSet;
        }
        if (i < i2) {
            return new RegularImmutableSortedSet(regularImmutableSortedSet.A00.subList(i, i2), regularImmutableSortedSet.A01);
        }
        return ImmutableSortedSet.A04(regularImmutableSortedSet.A01);
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
            } else if (!(iterable instanceof S1)) {
                return false;
            } else {
                comparator2 = ((S1) iterable).comparator();
            }
            return comparator.equals(comparator2);
        } else {
            throw null;
        }
    }

    @Override // java.util.Collection, java.util.Set, java.util.AbstractCollection
    public final boolean containsAll(Collection<?> collection) {
        if (collection instanceof AbstractC0120Qz) {
            collection = ((AbstractC0120Qz) collection).A1R();
        }
        if (!A03(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        TW<E> A0H = iterator();
        Iterator<?> it = collection.iterator();
        if (A0H.hasNext()) {
            Object next = it.next();
            E next2 = A0H.next();
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
                    } else if (!A0H.hasNext()) {
                        break;
                    } else {
                        next2 = A0H.next();
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
    public final ImmutableList<E> A0G() {
        return this.A00;
    }
}
