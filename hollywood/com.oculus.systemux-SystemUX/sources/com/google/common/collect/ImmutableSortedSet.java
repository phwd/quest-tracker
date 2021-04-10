package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, SortedIterable<E> {
    static final int SPLITERATOR_CHARACTERISTICS = 1301;
    final transient Comparator<? super E> comparator;
    @GwtIncompatible
    @LazyInit
    transient ImmutableSortedSet<E> descendingSet;

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public abstract ImmutableSortedSet<E> createDescendingSet();

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public abstract UnmodifiableIterator<E> descendingIterator();

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> headSetImpl(E e, boolean z);

    /* access modifiers changed from: package-private */
    public abstract int indexOf(@NullableDecl Object obj);

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, com.google.common.collect.SortedIterable, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.util.NavigableSet, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> subSetImpl(E e, boolean z, E e2, boolean z2);

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> tailSetImpl(E e, boolean z);

    @Beta
    public static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(Comparator<? super E> comparator2) {
        return CollectCollectors.toImmutableSortedSet(comparator2);
    }

    static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator2) {
        return Ordering.natural().equals(comparator2) ? (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.NATURAL_EMPTY_SET : new RegularImmutableSortedSet<>(ImmutableList.of(), comparator2);
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e) {
        return new RegularImmutableSortedSet(ImmutableList.of(e), Ordering.natural());
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2) {
        return construct(Ordering.natural(), 2, e, e2);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3) {
        return construct(Ordering.natural(), 3, e, e2, e3);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3, E e4) {
        return construct(Ordering.natural(), 4, e, e2, e3, e4);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3, E e4, E e5) {
        return construct(Ordering.natural(), 5, e, e2, e3, e4, e5);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Comparable[] comparableArr = new Comparable[(eArr.length + 6)];
        comparableArr[0] = e;
        comparableArr[1] = e2;
        comparableArr[2] = e3;
        comparableArr[3] = e4;
        comparableArr[4] = e5;
        comparableArr[5] = e6;
        System.arraycopy(eArr, 0, comparableArr, 6, eArr.length);
        return construct(Ordering.natural(), comparableArr.length, comparableArr);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(E[] eArr) {
        return construct(Ordering.natural(), eArr.length, (Object[]) eArr.clone());
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Collection<? extends E> collection) {
        return copyOf((Comparator) Ordering.natural(), (Collection) collection);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterator<? extends E> it) {
        return copyOf(Ordering.natural(), it);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator2, Iterator<? extends E> it) {
        return new Builder(comparator2).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator2, Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(comparator2);
        if (SortedIterables.hasSameComparator(comparator2, iterable) && (iterable instanceof ImmutableSortedSet)) {
            ImmutableSortedSet<E> immutableSortedSet = (ImmutableSortedSet) iterable;
            if (!immutableSortedSet.isPartialView()) {
                return immutableSortedSet;
            }
        }
        Object[] array = Iterables.toArray(iterable);
        return construct(comparator2, array.length, array);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator2, Collection<? extends E> collection) {
        return copyOf((Comparator) comparator2, (Iterable) collection);
    }

    public static <E> ImmutableSortedSet<E> copyOfSorted(SortedSet<E> sortedSet) {
        Comparator comparator2 = SortedIterables.comparator(sortedSet);
        ImmutableList copyOf = ImmutableList.copyOf((Collection) sortedSet);
        if (copyOf.isEmpty()) {
            return emptySet(comparator2);
        }
        return new RegularImmutableSortedSet(copyOf, comparator2);
    }

    static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator2, int i, E... eArr) {
        if (i == 0) {
            return emptySet(comparator2);
        }
        ObjectArrays.checkElementsNotNull(eArr, i);
        Arrays.sort(eArr, 0, i, comparator2);
        int i2 = 1;
        for (int i3 = 1; i3 < i; i3++) {
            E e = eArr[i3];
            if (comparator2.compare(e, eArr[i2 - 1]) != 0) {
                eArr[i2] = e;
                i2++;
            }
        }
        Arrays.fill(eArr, i2, i, (Object) null);
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(eArr, i2), comparator2);
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator2) {
        return new Builder<>(comparator2);
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Collections.reverseOrder());
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;
        private E[] elements = ((E[]) new Object[4]);
        private int n = 0;

        public Builder(Comparator<? super E> comparator2) {
            super(true);
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.Builder
        public void copy() {
            E[] eArr = this.elements;
            this.elements = (E[]) Arrays.copyOf(eArr, eArr.length);
        }

        private void sortAndDedup() {
            int i = this.n;
            if (i != 0) {
                Arrays.sort(this.elements, 0, i, this.comparator);
                int i2 = 1;
                int i3 = 1;
                while (true) {
                    int i4 = this.n;
                    if (i2 < i4) {
                        Comparator<? super E> comparator2 = this.comparator;
                        E[] eArr = this.elements;
                        int compare = comparator2.compare(eArr[i3 - 1], eArr[i2]);
                        if (compare < 0) {
                            E[] eArr2 = this.elements;
                            eArr2[i3] = eArr2[i2];
                            i3++;
                        } else if (compare > 0) {
                            throw new AssertionError("Comparator " + this.comparator + " compare method violates its contract");
                        }
                        i2++;
                    } else {
                        Arrays.fill(this.elements, i3, i4, (Object) null);
                        this.n = i3;
                        return;
                    }
                }
            }
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            Preconditions.checkNotNull(e);
            copyIfNecessary();
            if (this.n == this.elements.length) {
                sortAndDedup();
                int i = this.n;
                int expandedCapacity = ImmutableCollection.Builder.expandedCapacity(i, i + 1);
                E[] eArr = this.elements;
                if (expandedCapacity > eArr.length) {
                    this.elements = (E[]) Arrays.copyOf(eArr, expandedCapacity);
                }
            }
            E[] eArr2 = this.elements;
            int i2 = this.n;
            this.n = i2 + 1;
            eArr2[i2] = e;
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            ObjectArrays.checkElementsNotNull(eArr);
            for (E e : eArr) {
                add((Object) e);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.Builder
        @CanIgnoreReturnValue
        public Builder<E> combine(ImmutableSet.Builder<E> builder) {
            copyIfNecessary();
            Builder builder2 = (Builder) builder;
            for (int i = 0; i < builder2.n; i++) {
                add((Object) builder2.elements[i]);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSortedSet<E> build() {
            sortAndDedup();
            int i = this.n;
            if (i == 0) {
                return ImmutableSortedSet.emptySet(this.comparator);
            }
            this.forceCopy = true;
            return new RegularImmutableSortedSet(ImmutableList.asImmutableList(this.elements, i), this.comparator);
        }
    }

    /* access modifiers changed from: package-private */
    public int unsafeCompare(Object obj, Object obj2) {
        return unsafeCompare(this.comparator, obj, obj2);
    }

    static int unsafeCompare(Comparator<?> comparator2, Object obj, Object obj2) {
        return comparator2.compare(obj, obj2);
    }

    ImmutableSortedSet(Comparator<? super E> comparator2) {
        this.comparator = comparator2;
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public ImmutableSortedSet<E> headSet(E e) {
        return headSet((Object) e, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableSortedSet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public ImmutableSortedSet<E> headSet(E e, boolean z) {
        return headSetImpl(Preconditions.checkNotNull(e), z);
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public ImmutableSortedSet<E> subSet(E e, E e2) {
        return subSet((Object) e, true, (Object) e2, false);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public ImmutableSortedSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(e2);
        Preconditions.checkArgument(this.comparator.compare(e, e2) <= 0);
        return subSetImpl(e, z, e2, z2);
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public ImmutableSortedSet<E> tailSet(E e) {
        return tailSet((Object) e, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableSortedSet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    @GwtIncompatible
    public ImmutableSortedSet<E> tailSet(E e, boolean z) {
        return tailSetImpl(Preconditions.checkNotNull(e), z);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E lower(E e) {
        return (E) Iterators.getNext(headSet((Object) e, false).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E floor(E e) {
        return (E) Iterators.getNext(headSet((Object) e, true).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E ceiling(E e) {
        return (E) Iterables.getFirst(tailSet((Object) e, true), null);
    }

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public E higher(E e) {
        return (E) Iterables.getFirst(tailSet((Object) e, false), null);
    }

    @Override // java.util.SortedSet
    public E first() {
        return iterator().next();
    }

    @Override // java.util.SortedSet
    public E last() {
        return descendingIterator().next();
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

    @Override // java.util.NavigableSet
    @GwtIncompatible
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.descendingSet;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> createDescendingSet = createDescendingSet();
        this.descendingSet = createDescendingSet;
        createDescendingSet.descendingSet = this;
        return createDescendingSet;
    }

    @Override // java.util.SortedSet, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new Spliterators.AbstractSpliterator<E>((long) size(), 1365) {
            /* class com.google.common.collect.ImmutableSortedSet.AnonymousClass1 */
            final UnmodifiableIterator<E> iterator = ImmutableSortedSet.this.iterator();

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super E> consumer) {
                if (!this.iterator.hasNext()) {
                    return false;
                }
                consumer.accept(this.iterator.next());
                return true;
            }

            @Override // java.util.Spliterator
            public Comparator<? super E> getComparator() {
                return ImmutableSortedSet.this.comparator;
            }
        };
    }

    private static class SerializedForm<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super E> comparator;
        final Object[] elements;

        public SerializedForm(Comparator<? super E> comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableSortedSet$Builder */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new Builder(this.comparator).add(this.elements).build();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }
}
