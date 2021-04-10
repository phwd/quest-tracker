package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class Sets {
    private Sets() {
    }

    /* access modifiers changed from: package-private */
    public static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        ImprovedAbstractSet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection<?> c) {
            return Sets.removeAllImpl(this, c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            return super.retainAll((Collection) Preconditions.checkNotNull(c));
        }
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E anElement, E... otherElements) {
        return ImmutableEnumSet.asImmutable(EnumSet.of(anElement, otherElements));
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> elements) {
        if (elements instanceof ImmutableEnumSet) {
            return (ImmutableEnumSet) elements;
        }
        if (elements instanceof Collection) {
            Collection<E> collection = (Collection) elements;
            if (collection.isEmpty()) {
                return ImmutableSet.of();
            }
            return ImmutableEnumSet.asImmutable(EnumSet.copyOf(collection));
        }
        Iterator<E> itr = elements.iterator();
        if (!itr.hasNext()) {
            return ImmutableSet.of();
        }
        EnumSet<E> enumSet = EnumSet.of(itr.next());
        Iterators.addAll(enumSet, itr);
        return ImmutableEnumSet.asImmutable(enumSet);
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> elementType) {
        EnumSet<E> set = EnumSet.noneOf(elementType);
        Iterables.addAll(set, iterable);
        return set;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(E... elements) {
        HashSet<E> set = newHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new HashSet<>(Collections2.cast(elements));
        }
        return newHashSet(elements.iterator());
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> elements) {
        HashSet<E> set = newHashSet();
        Iterators.addAll(set, elements);
        return set;
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<>(Maps.capacity(expectedSize));
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> elements) {
        Set<E> set = newConcurrentHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new LinkedHashSet<>(Collections2.cast(elements));
        }
        LinkedHashSet<E> set = newLinkedHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return new LinkedHashSet<>(Maps.capacity(expectedSize));
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
        TreeSet<E> set = newTreeSet();
        Iterables.addAll(set, elements);
        return set;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        return new TreeSet<>((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(Maps.newIdentityHashMap());
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(Iterable<? extends E> elements) {
        Collection<? extends E> elementsCollection;
        if (elements instanceof Collection) {
            elementsCollection = Collections2.cast(elements);
        } else {
            elementsCollection = Lists.newArrayList(elements);
        }
        return new CopyOnWriteArraySet<>(elementsCollection);
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return makeComplementByHand(collection, collection.iterator().next().getDeclaringClass());
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> type) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        return makeComplementByHand(collection, type);
    }

    private static <E extends Enum<E>> EnumSet<E> makeComplementByHand(Collection<E> collection, Class<E> type) {
        EnumSet<E> result = EnumSet.allOf(type);
        result.removeAll(collection);
        return result;
    }

    @Deprecated
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public abstract UnmodifiableIterator<E> iterator();

        private SetView() {
        }

        public ImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf((Collection) this);
        }

        @CanIgnoreReturnValue
        public <S extends Set<E>> S copyInto(S set) {
            set.addAll(this);
            return set;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @CanIgnoreReturnValue
        @Deprecated
        public final boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final void clear() {
            throw new UnsupportedOperationException();
        }
    }

    public static <E> SetView<E> union(final Set<? extends E> set1, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            /* class com.google.common.collect.Sets.AnonymousClass1 */

            public int size() {
                int size = set1.size();
                for (Object obj : set2) {
                    if (!set1.contains(obj)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return set1.isEmpty() && set2.isEmpty();
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.SetView, com.google.common.collect.Sets.SetView, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() {
                    /* class com.google.common.collect.Sets.AnonymousClass1.AnonymousClass1 */
                    final Iterator<? extends E> itr1 = set1.iterator();
                    final Iterator<? extends E> itr2 = set2.iterator();

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        if (this.itr1.hasNext()) {
                            return (E) this.itr1.next();
                        }
                        while (this.itr2.hasNext()) {
                            E e = (E) this.itr2.next();
                            if (!set1.contains(e)) {
                                return e;
                            }
                        }
                        return (E) endOfData();
                    }
                };
            }

            public boolean contains(Object object) {
                return set1.contains(object) || set2.contains(object);
            }

            @Override // com.google.common.collect.Sets.SetView
            public <S extends Set<E>> S copyInto(S set) {
                set.addAll(set1);
                set.addAll(set2);
                return set;
            }

            @Override // com.google.common.collect.Sets.SetView
            public ImmutableSet<E> immutableCopy() {
                return new ImmutableSet.Builder().addAll((Iterable) set1).addAll((Iterable) set2).build();
            }
        };
    }

    public static <E> SetView<E> intersection(final Set<E> set1, final Set<?> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            /* class com.google.common.collect.Sets.AnonymousClass2 */

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.SetView, com.google.common.collect.Sets.SetView, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() {
                    /* class com.google.common.collect.Sets.AnonymousClass2.AnonymousClass1 */
                    final Iterator<E> itr = set1.iterator();

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (this.itr.hasNext()) {
                            E e = this.itr.next();
                            if (set2.contains(e)) {
                                return e;
                            }
                        }
                        return (E) endOfData();
                    }
                };
            }

            public int size() {
                int size = 0;
                for (Object obj : set1) {
                    if (set2.contains(obj)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return Collections.disjoint(set1, set2);
            }

            public boolean contains(Object object) {
                return set1.contains(object) && set2.contains(object);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> collection) {
                return set1.containsAll(collection) && set2.containsAll(collection);
            }
        };
    }

    public static <E> SetView<E> difference(final Set<E> set1, final Set<?> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            /* class com.google.common.collect.Sets.AnonymousClass3 */

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.SetView, com.google.common.collect.Sets.SetView, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() {
                    /* class com.google.common.collect.Sets.AnonymousClass3.AnonymousClass1 */
                    final Iterator<E> itr = set1.iterator();

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (this.itr.hasNext()) {
                            E e = this.itr.next();
                            if (!set2.contains(e)) {
                                return e;
                            }
                        }
                        return (E) endOfData();
                    }
                };
            }

            public int size() {
                int size = 0;
                for (Object obj : set1) {
                    if (!set2.contains(obj)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return set2.containsAll(set1);
            }

            public boolean contains(Object element) {
                return set1.contains(element) && !set2.contains(element);
            }
        };
    }

    public static <E> SetView<E> symmetricDifference(final Set<? extends E> set1, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            /* class com.google.common.collect.Sets.AnonymousClass4 */

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.SetView, com.google.common.collect.Sets.SetView, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<E> iterator() {
                final Iterator<? extends E> itr1 = set1.iterator();
                final Iterator<? extends E> itr2 = set2.iterator();
                return new AbstractIterator<E>() {
                    /* class com.google.common.collect.Sets.AnonymousClass4.AnonymousClass1 */

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (itr1.hasNext()) {
                            E elem1 = (E) itr1.next();
                            if (!set2.contains(elem1)) {
                                return elem1;
                            }
                        }
                        while (itr2.hasNext()) {
                            E elem2 = (E) itr2.next();
                            if (!set1.contains(elem2)) {
                                return elem2;
                            }
                        }
                        return (E) endOfData();
                    }
                };
            }

            public int size() {
                int size = 0;
                for (Object obj : set1) {
                    if (!set2.contains(obj)) {
                        size++;
                    }
                }
                for (Object obj2 : set2) {
                    if (!set1.contains(obj2)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return set1.equals(set2);
            }

            public boolean contains(Object element) {
                return set1.contains(element) ^ set2.contains(element);
            }
        };
    }

    public static <E> Set<E> filter(Set<E> unfiltered, Predicate<? super E> predicate) {
        if (unfiltered instanceof SortedSet) {
            return filter((SortedSet) unfiltered, (Predicate) predicate);
        }
        if (!(unfiltered instanceof FilteredSet)) {
            return new FilteredSet((Set) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet<E> filtered = (FilteredSet) unfiltered;
        return new FilteredSet((Set) filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    public static <E> SortedSet<E> filter(SortedSet<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredSet)) {
            return new FilteredSortedSet((SortedSet) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet<E> filtered = (FilteredSet) unfiltered;
        return new FilteredSortedSet((SortedSet) filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> filter(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredSet)) {
            return new FilteredNavigableSet((NavigableSet) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet<E> filtered = (FilteredSet) unfiltered;
        return new FilteredNavigableSet((NavigableSet) filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    /* access modifiers changed from: private */
    public static class FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
        FilteredSet(Set<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        public boolean equals(@NullableDecl Object object) {
            return Sets.equalsImpl(this, object);
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    /* access modifiers changed from: private */
    public static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        FilteredSortedSet(SortedSet<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return ((SortedSet) this.unfiltered).comparator();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).subSet(fromElement, toElement), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E toElement) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).headSet(toElement), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E fromElement) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).tailSet(fromElement), this.predicate);
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) Iterators.find(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.SortedSet
        public E last() {
            SortedSet<E> sortedUnfiltered = (SortedSet) this.unfiltered;
            while (true) {
                E element = sortedUnfiltered.last();
                if (this.predicate.apply(element)) {
                    return element;
                }
                sortedUnfiltered = sortedUnfiltered.headSet(element);
            }
        }
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static class FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
        FilteredNavigableSet(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        /* access modifiers changed from: package-private */
        public NavigableSet<E> unfiltered() {
            return (NavigableSet) this.unfiltered;
        }

        @Override // java.util.NavigableSet
        @NullableDecl
        public E lower(E e) {
            return (E) Iterators.find(unfiltered().headSet(e, false).descendingIterator(), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        @NullableDecl
        public E floor(E e) {
            return (E) Iterators.find(unfiltered().headSet(e, true).descendingIterator(), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return (E) Iterables.find(unfiltered().tailSet(e, true), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return (E) Iterables.find(unfiltered().tailSet(e, false), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) Iterables.removeFirstMatching(unfiltered(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) Iterables.removeFirstMatching(unfiltered().descendingSet(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return Sets.filter((NavigableSet) unfiltered().descendingSet(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.filter(unfiltered().descendingIterator(), this.predicate);
        }

        @Override // com.google.common.collect.Sets.FilteredSortedSet, java.util.SortedSet
        public E last() {
            return (E) Iterators.find(unfiltered().descendingIterator(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return Sets.filter((NavigableSet) unfiltered().subSet(fromElement, fromInclusive, toElement, toInclusive), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return Sets.filter((NavigableSet) unfiltered().headSet(toElement, inclusive), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return Sets.filter((NavigableSet) unfiltered().tailSet(fromElement, inclusive), this.predicate);
        }
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> sets) {
        return CartesianSet.create(sets);
    }

    @SafeVarargs
    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... sets) {
        return cartesianProduct(Arrays.asList(sets));
    }

    /* access modifiers changed from: private */
    public static final class CartesianSet<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        private final transient ImmutableList<ImmutableSet<E>> axes;
        private final transient CartesianList<E> delegate;

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.ImmutableList$Builder */
        /* JADX WARN: Multi-variable type inference failed */
        static <E> Set<List<E>> create(List<? extends Set<? extends E>> sets) {
            ImmutableList.Builder<ImmutableSet<E>> axesBuilder = new ImmutableList.Builder<>(sets.size());
            for (Set<? extends E> set : sets) {
                ImmutableSet<E> copy = ImmutableSet.copyOf((Collection) set);
                if (copy.isEmpty()) {
                    return ImmutableSet.of();
                }
                axesBuilder.add(copy);
            }
            final ImmutableList<ImmutableSet<E>> axes2 = axesBuilder.build();
            return new CartesianSet(axes2, new CartesianList(new ImmutableList<List<E>>() {
                /* class com.google.common.collect.Sets.CartesianSet.AnonymousClass1 */

                public int size() {
                    return ImmutableList.this.size();
                }

                @Override // java.util.List
                public List<E> get(int index) {
                    return ((ImmutableSet) ImmutableList.this.get(index)).asList();
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }
            }));
        }

        private CartesianSet(ImmutableList<ImmutableSet<E>> axes2, CartesianList<E> delegate2) {
            this.axes = axes2;
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Collection<List<E>> delegate() {
            return this.delegate;
        }

        public boolean equals(@NullableDecl Object object) {
            if (object instanceof CartesianSet) {
                return this.axes.equals(((CartesianSet) object).axes);
            }
            return super.equals(object);
        }

        public int hashCode() {
            int adjust = size() - 1;
            for (int i = 0; i < this.axes.size(); i++) {
                adjust = ((adjust * 31) ^ -1) ^ -1;
            }
            int hash = 1;
            UnmodifiableIterator<ImmutableSet<E>> it = this.axes.iterator();
            while (it.hasNext()) {
                Set<E> axis = it.next();
                hash = (((hash * 31) + ((size() / axis.size()) * axis.hashCode())) ^ -1) ^ -1;
            }
            return ((hash + adjust) ^ -1) ^ -1;
        }
    }

    @GwtCompatible(serializable = false)
    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new PowerSet(set);
    }

    /* access modifiers changed from: private */
    public static final class SubSet<E> extends AbstractSet<E> {
        private final ImmutableMap<E, Integer> inputSet;
        private final int mask;

        SubSet(ImmutableMap<E, Integer> inputSet2, int mask2) {
            this.inputSet = inputSet2;
            this.mask = mask2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return new UnmodifiableIterator<E>() {
                /* class com.google.common.collect.Sets.SubSet.AnonymousClass1 */
                final ImmutableList<E> elements = SubSet.this.inputSet.keySet().asList();
                int remainingSetBits = SubSet.this.mask;

                public boolean hasNext() {
                    return this.remainingSetBits != 0;
                }

                @Override // java.util.Iterator
                public E next() {
                    int index = Integer.numberOfTrailingZeros(this.remainingSetBits);
                    if (index == 32) {
                        throw new NoSuchElementException();
                    }
                    this.remainingSetBits &= (1 << index) ^ -1;
                    return this.elements.get(index);
                }
            };
        }

        public int size() {
            return Integer.bitCount(this.mask);
        }

        public boolean contains(@NullableDecl Object o) {
            Integer index = this.inputSet.get(o);
            if (index == null || (this.mask & (1 << index.intValue())) == 0) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static final class PowerSet<E> extends AbstractSet<Set<E>> {
        final ImmutableMap<E, Integer> inputSet;

        PowerSet(Set<E> input) {
            this.inputSet = Maps.indexMap(input);
            Preconditions.checkArgument(this.inputSet.size() <= 30, "Too many elements to create power set: %s > 30", this.inputSet.size());
        }

        public int size() {
            return 1 << this.inputSet.size();
        }

        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Set<E>> iterator() {
            return new AbstractIndexedListIterator<Set<E>>(size()) {
                /* class com.google.common.collect.Sets.PowerSet.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIndexedListIterator
                public Set<E> get(int setBits) {
                    return new SubSet(PowerSet.this.inputSet, setBits);
                }
            };
        }

        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            return this.inputSet.keySet().containsAll((Set) obj);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof PowerSet) {
                return this.inputSet.equals(((PowerSet) obj).inputSet);
            }
            return super.equals(obj);
        }

        public int hashCode() {
            return this.inputSet.keySet().hashCode() << (this.inputSet.size() - 1);
        }

        public String toString() {
            return "powerSet(" + this.inputSet + ")";
        }
    }

    @Beta
    public static <E> Set<Set<E>> combinations(Set<E> set, final int size) {
        final ImmutableMap<E, Integer> index = Maps.indexMap(set);
        CollectPreconditions.checkNonnegative(size, "size");
        Preconditions.checkArgument(size <= index.size(), "size (%s) must be <= set.size() (%s)", size, index.size());
        if (size == 0) {
            return ImmutableSet.of(ImmutableSet.of());
        }
        if (size == index.size()) {
            return ImmutableSet.of(index.keySet());
        }
        return new AbstractSet<Set<E>>() {
            /* class com.google.common.collect.Sets.AnonymousClass5 */

            public boolean contains(@NullableDecl Object o) {
                if (!(o instanceof Set)) {
                    return false;
                }
                Set<?> s = (Set) o;
                if (s.size() != size || !index.keySet().containsAll(s)) {
                    return false;
                }
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Set<E>> iterator() {
                return new AbstractIterator<Set<E>>() {
                    /* class com.google.common.collect.Sets.AnonymousClass5.AnonymousClass1 */
                    final BitSet bits = new BitSet(index.size());

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Set<E> computeNext() {
                        if (this.bits.isEmpty()) {
                            this.bits.set(0, size);
                        } else {
                            int firstSetBit = this.bits.nextSetBit(0);
                            int bitToFlip = this.bits.nextClearBit(firstSetBit);
                            if (bitToFlip == index.size()) {
                                return (Set) endOfData();
                            }
                            this.bits.set(0, (bitToFlip - firstSetBit) - 1);
                            this.bits.clear((bitToFlip - firstSetBit) - 1, bitToFlip);
                            this.bits.set(bitToFlip);
                        }
                        final BitSet copy = (BitSet) this.bits.clone();
                        return new AbstractSet<E>() {
                            /* class com.google.common.collect.Sets.AnonymousClass5.AnonymousClass1.AnonymousClass1 */

                            public boolean contains(@NullableDecl Object o) {
                                Integer i = (Integer) index.get(o);
                                return i != null && copy.get(i.intValue());
                            }

                            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                            public Iterator<E> iterator() {
                                return new AbstractIterator<E>() {
                                    /* class com.google.common.collect.Sets.AnonymousClass5.AnonymousClass1.AnonymousClass1.AnonymousClass1 */
                                    int i = -1;

                                    /* access modifiers changed from: protected */
                                    @Override // com.google.common.collect.AbstractIterator
                                    public E computeNext() {
                                        this.i = copy.nextSetBit(this.i + 1);
                                        return this.i == -1 ? (E) endOfData() : (E) index.keySet().asList().get(this.i);
                                    }
                                };
                            }

                            public int size() {
                                return size;
                            }
                        };
                    }
                };
            }

            public int size() {
                return IntMath.binomial(index.size(), size);
            }

            public String toString() {
                return "Sets.combinations(" + index.keySet() + ", " + size + ")";
            }
        };
    }

    static int hashCodeImpl(Set<?> s) {
        int hashCode = 0;
        Iterator<?> it = s.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            hashCode = ((hashCode + (o != null ? o.hashCode() : 0)) ^ -1) ^ -1;
        }
        return hashCode;
    }

    static boolean equalsImpl(Set<?> s, @NullableDecl Object object) {
        if (s == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> o = (Set) object;
        try {
            return s.size() == o.size() && s.containsAll(o);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> set) {
        return ((set instanceof ImmutableCollection) || (set instanceof UnmodifiableNavigableSet)) ? set : new UnmodifiableNavigableSet<>(set);
    }

    /* access modifiers changed from: package-private */
    public static final class UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E>, Serializable {
        private static final long serialVersionUID = 0;
        private final NavigableSet<E> delegate;
        @MonotonicNonNullDecl
        private transient UnmodifiableNavigableSet<E> descendingSet;
        private final SortedSet<E> unmodifiableDelegate;

        UnmodifiableNavigableSet(NavigableSet<E> delegate2) {
            this.delegate = (NavigableSet) Preconditions.checkNotNull(delegate2);
            this.unmodifiableDelegate = Collections.unmodifiableSortedSet(delegate2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public SortedSet<E> delegate() {
            return this.unmodifiableDelegate;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            return this.delegate.lower(e);
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            return this.delegate.floor(e);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return this.delegate.ceiling(e);
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return this.delegate.higher(e);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            UnmodifiableNavigableSet<E> result = this.descendingSet;
            if (result != null) {
                return result;
            }
            UnmodifiableNavigableSet<E> result2 = new UnmodifiableNavigableSet<>(this.delegate.descendingSet());
            this.descendingSet = result2;
            result2.descendingSet = this;
            return result2;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(toElement, inclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(fromElement, inclusive));
        }
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return Synchronized.navigableSet(navigableSet);
    }

    static boolean removeAllImpl(Set<?> set, Iterator<?> iterator) {
        boolean changed = false;
        while (iterator.hasNext()) {
            changed |= set.remove(iterator.next());
        }
        return changed;
    }

    static boolean removeAllImpl(Set<?> set, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return removeAllImpl(set, collection.iterator());
        }
        return Iterators.removeAll(set.iterator(), collection);
    }

    @GwtIncompatible
    static class DescendingSet<E> extends ForwardingNavigableSet<E> {
        private final NavigableSet<E> forward;

        DescendingSet(NavigableSet<E> forward2) {
            this.forward = forward2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public NavigableSet<E> delegate() {
            return this.forward;
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E lower(E e) {
            return this.forward.higher(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E floor(E e) {
            return this.forward.ceiling(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E ceiling(E e) {
            return this.forward.floor(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E higher(E e) {
            return this.forward.lower(e);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollFirst() {
            return this.forward.pollLast();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollLast() {
            return this.forward.pollFirst();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return this.forward;
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return this.forward.iterator();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return this.forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return standardSubSet(fromElement, toElement);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return this.forward.tailSet(toElement, inclusive).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> headSet(E toElement) {
            return standardHeadSet(toElement);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return this.forward.headSet(fromElement, inclusive).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> tailSet(E fromElement) {
            return standardTailSet(fromElement);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> forwardComparator = this.forward.comparator();
            if (forwardComparator == null) {
                return Ordering.natural().reverse();
            }
            return reverse(forwardComparator);
        }

        private static <T> Ordering<T> reverse(Comparator<T> forward2) {
            return Ordering.from(forward2).reverse();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public E first() {
            return this.forward.last();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public E last() {
            return this.forward.first();
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.util.NavigableSet, java.lang.Iterable
        public Iterator<E> iterator() {
            return this.forward.descendingIterator();
        }

        @Override // com.google.common.collect.ForwardingCollection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public <T> T[] toArray(T[] array) {
            return (T[]) standardToArray(array);
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }
    }

    @Beta
    @GwtIncompatible
    public static <K extends Comparable<? super K>> NavigableSet<K> subSet(NavigableSet<K> set, Range<K> range) {
        boolean z;
        boolean z2 = true;
        if (set.comparator() != null && set.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(set.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            if (range.lowerBoundType() == BoundType.CLOSED) {
                z = true;
            } else {
                z = false;
            }
            K upperEndpoint = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z2 = false;
            }
            return set.subSet(lowerEndpoint, z, upperEndpoint, z2);
        } else if (range.hasLowerBound()) {
            K lowerEndpoint2 = range.lowerEndpoint();
            if (range.lowerBoundType() != BoundType.CLOSED) {
                z2 = false;
            }
            return set.tailSet(lowerEndpoint2, z2);
        } else if (!range.hasUpperBound()) {
            return (NavigableSet) Preconditions.checkNotNull(set);
        } else {
            K upperEndpoint2 = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z2 = false;
            }
            return set.headSet(upperEndpoint2, z2);
        }
    }
}
