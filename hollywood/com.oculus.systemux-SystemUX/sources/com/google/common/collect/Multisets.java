package com.google.common.collect;

import com.facebook.imagepipeline.common.BytesRange;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Multisets {
    private Multisets() {
    }

    public static <T, E, M extends Multiset<E>> Collector<T, ?, M> toMultiset(Function<? super T, E> function, ToIntFunction<? super T> toIntFunction, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(toIntFunction);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new BiConsumer(function, toIntFunction) {
            /* class com.google.common.collect.$$Lambda$Multisets$YoneUvFxwXMGW05uIggbsPvRBo8 */
            private final /* synthetic */ Function f$0;
            private final /* synthetic */ ToIntFunction f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Multiset) obj).add(this.f$0.apply(obj2), this.f$1.applyAsInt(obj2));
            }
        }, $$Lambda$Multisets$nSg8KnV2rWlaLPt0DUhOLaV0hIY.INSTANCE, new Collector.Characteristics[0]);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.Multiset<? extends E> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        return ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new UnmodifiableMultiset((Multiset) Preconditions.checkNotNull(multiset));
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> immutableMultiset) {
        return (Multiset) Preconditions.checkNotNull(immutableMultiset);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multiset<? extends E> delegate;
        @MonotonicNonNullDecl
        transient Set<E> elementSet;
        @MonotonicNonNullDecl
        transient Set<Multiset.Entry<E>> entrySet;

        UnmodifiableMultiset(Multiset<? extends E> multiset) {
            this.delegate = multiset;
        }

        /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: com.google.common.collect.Multiset<? extends E>, com.google.common.collect.Multiset<E> */
        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingMultiset
        public Multiset<E> delegate() {
            return (Multiset<? extends E>) this.delegate;
        }

        /* access modifiers changed from: package-private */
        public Set<E> createElementSet() {
            return Collections.unmodifiableSet(this.delegate.elementSet());
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public Set<E> elementSet() {
            Set<E> set = this.elementSet;
            if (set != null) {
                return set;
            }
            Set<E> createElementSet = createElementSet();
            this.elementSet = createElementSet;
            return createElementSet;
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Multiset.Entry<E>> unmodifiableSet = Collections.unmodifiableSet(this.delegate.entrySet());
            this.entrySet = unmodifiableSet;
            return unmodifiableSet;
        }

        @Override // com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.unmodifiableIterator(this.delegate.iterator());
        }

        @Override // com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public int add(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingCollection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public int remove(Object obj, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public int setCount(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public boolean setCount(E e, int i, int i2) {
            throw new UnsupportedOperationException();
        }
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        return new UnmodifiableSortedMultiset((SortedMultiset) Preconditions.checkNotNull(sortedMultiset));
    }

    public static <E> Multiset.Entry<E> immutableEntry(@NullableDecl E e, int i) {
        return new ImmutableEntry(e, i);
    }

    /* access modifiers changed from: package-private */
    public static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        private static final long serialVersionUID = 0;
        private final int count;
        @NullableDecl
        private final E element;

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }

        ImmutableEntry(@NullableDecl E e, int i) {
            this.element = e;
            this.count = i;
            CollectPreconditions.checkNonnegative(i, "count");
        }

        @Override // com.google.common.collect.Multiset.Entry
        @NullableDecl
        public final E getElement() {
            return this.element;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final int getCount() {
            return this.count;
        }
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> multiset, Predicate<? super E> predicate) {
        if (!(multiset instanceof FilteredMultiset)) {
            return new FilteredMultiset(multiset, predicate);
        }
        FilteredMultiset filteredMultiset = (FilteredMultiset) multiset;
        return new FilteredMultiset(filteredMultiset.unfiltered, Predicates.and(filteredMultiset.predicate, predicate));
    }

    /* access modifiers changed from: private */
    public static final class FilteredMultiset<E> extends ViewMultiset<E> {
        final Predicate<? super E> predicate;
        final Multiset<E> unfiltered;

        FilteredMultiset(Multiset<E> multiset, Predicate<? super E> predicate2) {
            super();
            this.unfiltered = (Multiset) Preconditions.checkNotNull(multiset);
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate2);
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.Multisets.ViewMultiset, java.lang.Iterable
        public UnmodifiableIterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMultiset
        public Set<E> createElementSet() {
            return Sets.filter(this.unfiltered.elementSet(), this.predicate);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMultiset
        public Set<Multiset.Entry<E>> createEntrySet() {
            return Sets.filter(this.unfiltered.entrySet(), new Predicate<Multiset.Entry<E>>() {
                /* class com.google.common.collect.Multisets.FilteredMultiset.AnonymousClass1 */

                @Override // com.google.common.base.Predicate
                public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                    return apply((Multiset.Entry) ((Multiset.Entry) obj));
                }

                public boolean apply(Multiset.Entry<E> entry) {
                    return FilteredMultiset.this.predicate.apply(entry.getElement());
                }
            });
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<Multiset.Entry<E>> entryIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.Multiset
        public int count(@NullableDecl Object obj) {
            int count = this.unfiltered.count(obj);
            if (count <= 0) {
                return 0;
            }
            if (this.predicate.apply(obj)) {
                return count;
            }
            return 0;
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
        public int add(@NullableDecl E e, int i) {
            Preconditions.checkArgument(this.predicate.apply(e), "Element %s does not match predicate %s", e, this.predicate);
            return this.unfiltered.add(e, i);
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
        public int remove(@NullableDecl Object obj, int i) {
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            if (contains(obj)) {
                return this.unfiltered.remove(obj, i);
            }
            return 0;
        }
    }

    static int inferDistinctElements(Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).elementSet().size();
        }
        return 11;
    }

    @Beta
    public static <E> Multiset<E> union(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass1 */

            @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
            public boolean contains(@NullableDecl Object obj) {
                return multiset.contains(obj) || multiset2.contains(obj);
            }

            @Override // com.google.common.collect.AbstractMultiset
            public boolean isEmpty() {
                return multiset.isEmpty() && multiset2.isEmpty();
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object obj) {
                return Math.max(multiset.count(obj), multiset2.count(obj));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.union(multiset.elementSet(), multiset2.elementSet());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it = multiset.entrySet().iterator();
                final Iterator it2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass1.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        if (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            return Multisets.immutableEntry(element, Math.max(entry.getCount(), multiset2.count(element)));
                        }
                        while (it2.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                            Object element2 = entry2.getElement();
                            if (!multiset.contains(element2)) {
                                return Multisets.immutableEntry(element2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    public static <E> Multiset<E> intersection(final Multiset<E> multiset, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass2 */

            @Override // com.google.common.collect.Multiset
            public int count(Object obj) {
                int count = multiset.count(obj);
                if (count == 0) {
                    return 0;
                }
                return Math.min(count, multiset2.count(obj));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.intersection(multiset.elementSet(), multiset2.elementSet());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it = multiset.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass2.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            int min = Math.min(entry.getCount(), multiset2.count(element));
                            if (min > 0) {
                                return Multisets.immutableEntry(element, min);
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    @Beta
    public static <E> Multiset<E> sum(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass3 */

            @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
            public boolean contains(@NullableDecl Object obj) {
                return multiset.contains(obj) || multiset2.contains(obj);
            }

            @Override // com.google.common.collect.AbstractMultiset
            public boolean isEmpty() {
                return multiset.isEmpty() && multiset2.isEmpty();
            }

            @Override // com.google.common.collect.Multiset, com.google.common.collect.Multisets.ViewMultiset
            public int size() {
                return IntMath.saturatedAdd(multiset.size(), multiset2.size());
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object obj) {
                return multiset.count(obj) + multiset2.count(obj);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.union(multiset.elementSet(), multiset2.elementSet());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it = multiset.entrySet().iterator();
                final Iterator it2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass3.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        if (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            return Multisets.immutableEntry(element, entry.getCount() + multiset2.count(element));
                        }
                        while (it2.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                            Object element2 = entry2.getElement();
                            if (!multiset.contains(element2)) {
                                return Multisets.immutableEntry(element2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    @Beta
    public static <E> Multiset<E> difference(final Multiset<E> multiset, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass4 */

            @Override // com.google.common.collect.Multiset
            public int count(@NullableDecl Object obj) {
                int count = multiset.count(obj);
                if (count == 0) {
                    return 0;
                }
                return Math.max(0, count - multiset2.count(obj));
            }

            @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multisets.ViewMultiset
            public void clear() {
                throw new UnsupportedOperationException();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                final Iterator it = multiset.entrySet().iterator();
                return new AbstractIterator<E>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass4.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            E e = (E) entry.getElement();
                            if (entry.getCount() > multiset2.count(e)) {
                                return e;
                            }
                        }
                        return (E) endOfData();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it = multiset.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass4.AnonymousClass2 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            int count = entry.getCount() - multiset2.count(element);
                            if (count > 0) {
                                return Multisets.immutableEntry(element, count);
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multisets.ViewMultiset
            public int distinctElements() {
                return Iterators.size(entryIterator());
            }
        };
    }

    @CanIgnoreReturnValue
    public static boolean containsOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        for (Multiset.Entry<?> entry : multiset2.entrySet()) {
            if (multiset.count(entry.getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        return retainOccurrencesImpl(multiset, multiset2);
    }

    private static <E> boolean retainOccurrencesImpl(Multiset<E> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            int count = multiset2.count(next.getElement());
            if (count == 0) {
                it.remove();
            } else if (count < next.getCount()) {
                multiset.setCount(next.getElement(), count);
            }
            z = true;
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return removeOccurrences(multiset, (Multiset<?>) ((Multiset) iterable));
        }
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(iterable);
        boolean z = false;
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            z |= multiset.remove(it.next());
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<?>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<?> next = it.next();
            int count = multiset2.count(next.getElement());
            if (count >= next.getCount()) {
                it.remove();
            } else if (count > 0) {
                multiset.remove(next.getElement(), count);
            }
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        AbstractEntry() {
        }

        @Override // com.google.common.collect.Multiset.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            if (getCount() != entry.getCount() || !Objects.equal(getElement(), entry.getElement())) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int hashCode() {
            int i;
            E element = getElement();
            if (element == null) {
                i = 0;
            } else {
                i = element.hashCode();
            }
            return i ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String valueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return valueOf;
            }
            return valueOf + " x " + count;
        }
    }

    static boolean equalsImpl(Multiset<?> multiset, @NullableDecl Object obj) {
        if (obj == multiset) {
            return true;
        }
        if (obj instanceof Multiset) {
            Multiset multiset2 = (Multiset) obj;
            if (multiset.size() == multiset2.size() && multiset.entrySet().size() == multiset2.entrySet().size()) {
                for (Multiset.Entry entry : multiset2.entrySet()) {
                    if (multiset.count(entry.getElement()) != entry.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static <E> boolean addAllImpl(Multiset<E> multiset, Collection<? extends E> collection) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            return addAllImpl((Multiset) multiset, cast(collection));
        }
        if (collection.isEmpty()) {
            return false;
        }
        return Iterators.addAll(multiset, collection.iterator());
    }

    private static <E> boolean addAllImpl(Multiset<E> multiset, Multiset<? extends E> multiset2) {
        if (multiset2.isEmpty()) {
            return false;
        }
        multiset.getClass();
        multiset2.forEachEntry(new ObjIntConsumer() {
            /* class com.google.common.collect.$$Lambda$na6YZNSQK4ODR7fs5f6M1p3g */

            @Override // java.util.function.ObjIntConsumer
            public final void accept(Object obj, int i) {
                Multiset.this.add(obj, i);
            }
        });
        return true;
    }

    static boolean removeAllImpl(Multiset<?> multiset, Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().removeAll(collection);
    }

    static boolean retainAllImpl(Multiset<?> multiset, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().retainAll(collection);
    }

    static <E> int setCountImpl(Multiset<E> multiset, E e, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        int count = multiset.count(e);
        int i2 = i - count;
        if (i2 > 0) {
            multiset.add(e, i2);
        } else if (i2 < 0) {
            multiset.remove(e, -i2);
        }
        return count;
    }

    static <E> boolean setCountImpl(Multiset<E> multiset, E e, int i, int i2) {
        CollectPreconditions.checkNonnegative(i, "oldCount");
        CollectPreconditions.checkNonnegative(i2, "newCount");
        if (multiset.count(e) != i) {
            return false;
        }
        multiset.setCount(e, i2);
        return true;
    }

    static <E> Iterator<E> elementIterator(Iterator<Multiset.Entry<E>> it) {
        return new TransformedIterator<Multiset.Entry<E>, E>(it) {
            /* class com.google.common.collect.Multisets.AnonymousClass5 */

            /* access modifiers changed from: package-private */
            public E transform(Multiset.Entry<E> entry) {
                return entry.getElement();
            }
        };
    }

    static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public abstract Iterator<E> iterator();

        /* access modifiers changed from: package-private */
        public abstract Multiset<E> multiset();

        ElementSet() {
        }

        public void clear() {
            multiset().clear();
        }

        public boolean contains(Object obj) {
            return multiset().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return multiset().containsAll(collection);
        }

        public boolean isEmpty() {
            return multiset().isEmpty();
        }

        public boolean remove(Object obj) {
            return multiset().remove(obj, BytesRange.TO_END_OF_CONTENT) > 0;
        }

        public int size() {
            return multiset().entrySet().size();
        }
    }

    static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        /* access modifiers changed from: package-private */
        public abstract Multiset<E> multiset();

        EntrySet() {
        }

        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            if (entry.getCount() > 0 && multiset().count(entry.getElement()) == entry.getCount()) {
                return true;
            }
            return false;
        }

        public boolean remove(Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                E e = (E) entry.getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return multiset().setCount(e, count, 0);
                }
            }
            return false;
        }

        public void clear() {
            multiset().clear();
        }
    }

    static <E> Iterator<E> iteratorImpl(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
    }

    /* access modifiers changed from: package-private */
    public static final class MultisetIteratorImpl<E> implements Iterator<E> {
        private boolean canRemove;
        @MonotonicNonNullDecl
        private Multiset.Entry<E> currentEntry;
        private final Iterator<Multiset.Entry<E>> entryIterator;
        private int laterCount;
        private final Multiset<E> multiset;
        private int totalCount;

        MultisetIteratorImpl(Multiset<E> multiset2, Iterator<Multiset.Entry<E>> it) {
            this.multiset = multiset2;
            this.entryIterator = it;
        }

        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                if (this.laterCount == 0) {
                    this.currentEntry = this.entryIterator.next();
                    int count = this.currentEntry.getCount();
                    this.laterCount = count;
                    this.totalCount = count;
                }
                this.laterCount--;
                this.canRemove = true;
                return this.currentEntry.getElement();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            if (this.totalCount == 1) {
                this.entryIterator.remove();
            } else {
                this.multiset.remove(this.currentEntry.getElement());
            }
            this.totalCount--;
            this.canRemove = false;
        }
    }

    static <E> Spliterator<E> spliteratorImpl(Multiset<E> multiset) {
        Spliterator<Multiset.Entry<E>> spliterator = multiset.entrySet().spliterator();
        return CollectSpliterators.flatMap(spliterator, $$Lambda$Multisets$cvAUryqBQ5ACjGtci4h38iRqYCM.INSTANCE, (spliterator.characteristics() & 1296) | 64, (long) multiset.size());
    }

    static int linearTimeSizeImpl(Multiset<?> multiset) {
        long j = 0;
        for (Multiset.Entry<?> entry : multiset.entrySet()) {
            j += (long) entry.getCount();
        }
        return Ints.saturatedCast(j);
    }

    static <T> Multiset<T> cast(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        Multiset.Entry[] entryArr = (Multiset.Entry[]) multiset.entrySet().toArray(new Multiset.Entry[0]);
        Arrays.sort(entryArr, DecreasingCount.INSTANCE);
        return ImmutableMultiset.copyFromEntries(Arrays.asList(entryArr));
    }

    private static final class DecreasingCount implements Comparator<Multiset.Entry<?>> {
        static final DecreasingCount INSTANCE = new DecreasingCount();

        private DecreasingCount() {
        }

        public int compare(Multiset.Entry<?> entry, Multiset.Entry<?> entry2) {
            return entry2.getCount() - entry.getCount();
        }
    }

    private static abstract class ViewMultiset<E> extends AbstractMultiset<E> {
        private ViewMultiset() {
        }

        @Override // com.google.common.collect.Multiset
        public int size() {
            return Multisets.linearTimeSizeImpl(this);
        }

        @Override // com.google.common.collect.AbstractMultiset
        public void clear() {
            elementSet().clear();
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Multisets.iteratorImpl(this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMultiset
        public int distinctElements() {
            return elementSet().size();
        }
    }
}
