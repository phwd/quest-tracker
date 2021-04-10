package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD, serializable = BuildConfig.IS_INTERNAL_BUILD)
public final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    static final RegularImmutableSortedSet<Comparable> NATURAL_EMPTY_SET = new RegularImmutableSortedSet<>(ImmutableList.of(), Ordering.natural());
    @VisibleForTesting
    final transient ImmutableList<E> elements;

    RegularImmutableSortedSet(ImmutableList<E> elements2, Comparator<? super E> comparator) {
        super(comparator);
        this.elements = elements2;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.util.NavigableSet, java.lang.Iterable, java.util.AbstractCollection, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    public int size() {
        return this.elements.size();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@NullableDecl Object o) {
        if (o == null) {
            return false;
        }
        try {
            return unsafeBinarySearch(o) >= 0;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set, java.util.AbstractCollection
    public boolean containsAll(Collection<?> targets) {
        if (targets instanceof Multiset) {
            targets = ((Multiset) targets).elementSet();
        }
        if (!SortedIterables.hasSameComparator(comparator(), targets) || targets.size() <= 1) {
            return super.containsAll(targets);
        }
        Iterator<E> thisIterator = iterator();
        Iterator<?> thatIterator = targets.iterator();
        if (!thisIterator.hasNext()) {
            return false;
        }
        Object target = thatIterator.next();
        E current = thisIterator.next();
        while (true) {
            try {
                int cmp = unsafeCompare(current, target);
                if (cmp < 0) {
                    if (!thisIterator.hasNext()) {
                        return false;
                    }
                    current = thisIterator.next();
                } else if (cmp == 0) {
                    if (!thatIterator.hasNext()) {
                        return true;
                    }
                    target = thatIterator.next();
                } else if (cmp > 0) {
                    return false;
                }
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
        }
    }

    private int unsafeBinarySearch(Object key) throws ClassCastException {
        return Collections.binarySearch(this.elements, key, unsafeComparator());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.elements.isPartialView();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        return this.elements.copyIntoArray(dst, offset);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036 A[Catch:{ ClassCastException -> 0x0048, NoSuchElementException -> 0x004b }] */
    @Override // com.google.common.collect.ImmutableSet
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r11) {
        /*
            r10 = this;
            r6 = 1
            r7 = 0
            if (r11 != r10) goto L_0x0005
        L_0x0004:
            return r6
        L_0x0005:
            boolean r8 = r11 instanceof java.util.Set
            if (r8 != 0) goto L_0x000b
            r6 = r7
            goto L_0x0004
        L_0x000b:
            r5 = r11
            java.util.Set r5 = (java.util.Set) r5
            int r8 = r10.size()
            int r9 = r5.size()
            if (r8 == r9) goto L_0x001a
            r6 = r7
            goto L_0x0004
        L_0x001a:
            boolean r8 = r10.isEmpty()
            if (r8 != 0) goto L_0x0004
            java.util.Comparator r8 = r10.comparator
            boolean r8 = com.google.common.collect.SortedIterables.hasSameComparator(r8, r5)
            if (r8 == 0) goto L_0x004e
            java.util.Iterator r4 = r5.iterator()
            com.google.common.collect.UnmodifiableIterator r2 = r10.iterator()     // Catch:{ ClassCastException -> 0x0048, NoSuchElementException -> 0x004b }
        L_0x0030:
            boolean r8 = r2.hasNext()     // Catch:{ ClassCastException -> 0x0048, NoSuchElementException -> 0x004b }
            if (r8 == 0) goto L_0x0004
            java.lang.Object r1 = r2.next()     // Catch:{ ClassCastException -> 0x0048, NoSuchElementException -> 0x004b }
            java.lang.Object r3 = r4.next()     // Catch:{ ClassCastException -> 0x0048, NoSuchElementException -> 0x004b }
            if (r3 == 0) goto L_0x0046
            int r8 = r10.unsafeCompare(r1, r3)     // Catch:{ ClassCastException -> 0x0048, NoSuchElementException -> 0x004b }
            if (r8 == 0) goto L_0x0030
        L_0x0046:
            r6 = r7
            goto L_0x0004
        L_0x0048:
            r0 = move-exception
            r6 = r7
            goto L_0x0004
        L_0x004b:
            r0 = move-exception
            r6 = r7
            goto L_0x0004
        L_0x004e:
            boolean r6 = r10.containsAll(r5)
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableSortedSet.equals(java.lang.Object):boolean");
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E first() {
        if (!isEmpty()) {
            return this.elements.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E last() {
        if (!isEmpty()) {
            return this.elements.get(size() - 1);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E lower(E element) {
        int index = headIndex(element, false) - 1;
        if (index == -1) {
            return null;
        }
        return this.elements.get(index);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E floor(E element) {
        int index = headIndex(element, true) - 1;
        if (index == -1) {
            return null;
        }
        return this.elements.get(index);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E ceiling(E element) {
        int index = tailIndex(element, true);
        if (index == size()) {
            return null;
        }
        return this.elements.get(index);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E higher(E element) {
        int index = tailIndex(element, false);
        if (index == size()) {
            return null;
        }
        return this.elements.get(index);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> headSetImpl(E toElement, boolean inclusive) {
        return getSubSet(0, headIndex(toElement, inclusive));
    }

    /* access modifiers changed from: package-private */
    public int headIndex(E toElement, boolean inclusive) {
        int index = Collections.binarySearch(this.elements, Preconditions.checkNotNull(toElement), comparator());
        if (index < 0) {
            return index ^ -1;
        }
        if (inclusive) {
            return index + 1;
        }
        return index;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> subSetImpl(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return tailSetImpl(fromElement, fromInclusive).headSetImpl(toElement, toInclusive);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> tailSetImpl(E fromElement, boolean inclusive) {
        return getSubSet(tailIndex(fromElement, inclusive), size());
    }

    /* access modifiers changed from: package-private */
    public int tailIndex(E fromElement, boolean inclusive) {
        int index = Collections.binarySearch(this.elements, Preconditions.checkNotNull(fromElement), comparator());
        if (index < 0) {
            return index ^ -1;
        }
        if (inclusive) {
            return index;
        }
        return index + 1;
    }

    /* access modifiers changed from: package-private */
    public Comparator<Object> unsafeComparator() {
        return this.comparator;
    }

    /* access modifiers changed from: package-private */
    public RegularImmutableSortedSet<E> getSubSet(int newFromIndex, int newToIndex) {
        if (newFromIndex == 0 && newToIndex == size()) {
            return this;
        }
        if (newFromIndex < newToIndex) {
            return new RegularImmutableSortedSet<>(this.elements.subList(newFromIndex, newToIndex), this.comparator);
        }
        return emptySet(this.comparator);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    public int indexOf(@NullableDecl Object target) {
        if (target == null) {
            return -1;
        }
        try {
            int position = Collections.binarySearch(this.elements, target, unsafeComparator());
            if (position < 0) {
                position = -1;
            }
            return position;
        } catch (ClassCastException e) {
            return -1;
        }
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        return this.elements;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> createDescendingSet() {
        Comparator<? super E> reversedOrder = Collections.reverseOrder(this.comparator);
        if (isEmpty()) {
            return emptySet(reversedOrder);
        }
        return new RegularImmutableSortedSet(this.elements.reverse(), reversedOrder);
    }
}
