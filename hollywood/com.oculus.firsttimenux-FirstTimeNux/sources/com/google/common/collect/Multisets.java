package com.google.common.collect;

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
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Multisets {
    private Multisets() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.Multiset<? extends E> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        return ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new UnmodifiableMultiset((Multiset) Preconditions.checkNotNull(multiset));
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> multiset) {
        return (Multiset) Preconditions.checkNotNull(multiset);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multiset<? extends E> delegate;
        @MonotonicNonNullDecl
        transient Set<E> elementSet;
        @MonotonicNonNullDecl
        transient Set<Multiset.Entry<E>> entrySet;

        UnmodifiableMultiset(Multiset<? extends E> delegate2) {
            this.delegate = delegate2;
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
            Set<E> es = this.elementSet;
            if (es != null) {
                return es;
            }
            Set<E> es2 = createElementSet();
            this.elementSet = es2;
            return es2;
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> es = this.entrySet;
            if (es != null) {
                return es;
            }
            Set<Multiset.Entry<E>> es2 = Collections.unmodifiableSet(this.delegate.entrySet());
            this.entrySet = es2;
            return es2;
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
        public int add(E e, int occurences) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingCollection
        public boolean remove(Object element) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public int remove(Object element, int occurrences) {
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
        public int setCount(E e, int count) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.ForwardingMultiset
        public boolean setCount(E e, int oldCount, int newCount) {
            throw new UnsupportedOperationException();
        }
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        return new UnmodifiableSortedMultiset((SortedMultiset) Preconditions.checkNotNull(sortedMultiset));
    }

    public static <E> Multiset.Entry<E> immutableEntry(@NullableDecl E e, int n) {
        return new ImmutableEntry(e, n);
    }

    /* access modifiers changed from: package-private */
    public static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        private static final long serialVersionUID = 0;
        private final int count;
        @NullableDecl
        private final E element;

        ImmutableEntry(@NullableDecl E element2, int count2) {
            this.element = element2;
            this.count = count2;
            CollectPreconditions.checkNonnegative(count2, "count");
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

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredMultiset)) {
            return new FilteredMultiset(unfiltered, predicate);
        }
        FilteredMultiset<E> filtered = (FilteredMultiset) unfiltered;
        return new FilteredMultiset(filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    /* access modifiers changed from: private */
    public static final class FilteredMultiset<E> extends ViewMultiset<E> {
        final Predicate<? super E> predicate;
        final Multiset<E> unfiltered;

        FilteredMultiset(Multiset<E> unfiltered2, Predicate<? super E> predicate2) {
            super();
            this.unfiltered = (Multiset) Preconditions.checkNotNull(unfiltered2);
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
        public int count(@NullableDecl Object element) {
            int count = this.unfiltered.count(element);
            if (count <= 0) {
                return 0;
            }
            if (this.predicate.apply(element)) {
                return count;
            }
            return 0;
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
        public int add(@NullableDecl E element, int occurrences) {
            Preconditions.checkArgument(this.predicate.apply(element), "Element %s does not match predicate %s", element, this.predicate);
            return this.unfiltered.add(element, occurrences);
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
        public int remove(@NullableDecl Object element, int occurrences) {
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return count(element);
            }
            if (contains(element)) {
                return this.unfiltered.remove(element, occurrences);
            }
            return 0;
        }
    }

    static int inferDistinctElements(Iterable<?> elements) {
        if (elements instanceof Multiset) {
            return ((Multiset) elements).elementSet().size();
        }
        return 11;
    }

    @Beta
    public static <E> Multiset<E> union(final Multiset<? extends E> multiset1, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass1 */

            @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
            public boolean contains(@NullableDecl Object element) {
                return Multiset.this.contains(element) || multiset2.contains(element);
            }

            @Override // com.google.common.collect.AbstractMultiset
            public boolean isEmpty() {
                return Multiset.this.isEmpty() && multiset2.isEmpty();
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                return Math.max(Multiset.this.count(element), multiset2.count(element));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.union(Multiset.this.elementSet(), multiset2.elementSet());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<? extends Multiset.Entry<? extends E>> iterator1 = Multiset.this.entrySet().iterator();
                final Iterator<? extends Multiset.Entry<? extends E>> iterator2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass1.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        if (iterator1.hasNext()) {
                            Multiset.Entry<? extends E> entry1 = (Multiset.Entry) iterator1.next();
                            Object element = entry1.getElement();
                            return Multisets.immutableEntry(element, Math.max(entry1.getCount(), multiset2.count(element)));
                        }
                        while (iterator2.hasNext()) {
                            Multiset.Entry<? extends E> entry2 = (Multiset.Entry) iterator2.next();
                            Object element2 = entry2.getElement();
                            if (!Multiset.this.contains(element2)) {
                                return Multisets.immutableEntry(element2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    public static <E> Multiset<E> intersection(final Multiset<E> multiset1, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass2 */

            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                int count1 = Multiset.this.count(element);
                if (count1 == 0) {
                    return 0;
                }
                return Math.min(count1, multiset2.count(element));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.intersection(Multiset.this.elementSet(), multiset2.elementSet());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<Multiset.Entry<E>> iterator1 = Multiset.this.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass2.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        while (iterator1.hasNext()) {
                            Multiset.Entry<E> entry1 = (Multiset.Entry) iterator1.next();
                            E element = entry1.getElement();
                            int count = Math.min(entry1.getCount(), multiset2.count(element));
                            if (count > 0) {
                                return Multisets.immutableEntry(element, count);
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    @Beta
    public static <E> Multiset<E> sum(final Multiset<? extends E> multiset1, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass3 */

            @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
            public boolean contains(@NullableDecl Object element) {
                return Multiset.this.contains(element) || multiset2.contains(element);
            }

            @Override // com.google.common.collect.AbstractMultiset
            public boolean isEmpty() {
                return Multiset.this.isEmpty() && multiset2.isEmpty();
            }

            @Override // com.google.common.collect.Multiset, com.google.common.collect.Multisets.ViewMultiset
            public int size() {
                return IntMath.saturatedAdd(Multiset.this.size(), multiset2.size());
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object element) {
                return Multiset.this.count(element) + multiset2.count(element);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Set<E> createElementSet() {
                return Sets.union(Multiset.this.elementSet(), multiset2.elementSet());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<? extends Multiset.Entry<? extends E>> iterator1 = Multiset.this.entrySet().iterator();
                final Iterator<? extends Multiset.Entry<? extends E>> iterator2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass3.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        if (iterator1.hasNext()) {
                            Multiset.Entry<? extends E> entry1 = (Multiset.Entry) iterator1.next();
                            Object element = entry1.getElement();
                            return Multisets.immutableEntry(element, entry1.getCount() + multiset2.count(element));
                        }
                        while (iterator2.hasNext()) {
                            Multiset.Entry<? extends E> entry2 = (Multiset.Entry) iterator2.next();
                            Object element2 = entry2.getElement();
                            if (!Multiset.this.contains(element2)) {
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
    public static <E> Multiset<E> difference(final Multiset<E> multiset1, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() {
            /* class com.google.common.collect.Multisets.AnonymousClass4 */

            @Override // com.google.common.collect.Multiset
            public int count(@NullableDecl Object element) {
                int count1 = Multiset.this.count(element);
                if (count1 == 0) {
                    return 0;
                }
                return Math.max(0, count1 - multiset2.count(element));
            }

            @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multisets.ViewMultiset
            public void clear() {
                throw new UnsupportedOperationException();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<E> elementIterator() {
                final Iterator<Multiset.Entry<E>> iterator1 = Multiset.this.entrySet().iterator();
                return new AbstractIterator<E>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass4.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (iterator1.hasNext()) {
                            Multiset.Entry<E> entry1 = (Multiset.Entry) iterator1.next();
                            E element = (E) entry1.getElement();
                            if (entry1.getCount() > multiset2.count(element)) {
                                return element;
                            }
                        }
                        return (E) endOfData();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator<Multiset.Entry<E>> iterator1 = Multiset.this.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* class com.google.common.collect.Multisets.AnonymousClass4.AnonymousClass2 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Multiset.Entry<E> computeNext() {
                        while (iterator1.hasNext()) {
                            Multiset.Entry<E> entry1 = (Multiset.Entry) iterator1.next();
                            E element = entry1.getElement();
                            int count = entry1.getCount() - multiset2.count(element);
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
    public static boolean containsOccurrences(Multiset<?> superMultiset, Multiset<?> subMultiset) {
        Preconditions.checkNotNull(superMultiset);
        Preconditions.checkNotNull(subMultiset);
        for (Multiset.Entry<?> entry : subMultiset.entrySet()) {
            if (superMultiset.count(entry.getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multisetToModify, Multiset<?> multisetToRetain) {
        return retainOccurrencesImpl(multisetToModify, multisetToRetain);
    }

    private static <E> boolean retainOccurrencesImpl(Multiset<E> multisetToModify, Multiset<?> occurrencesToRetain) {
        Preconditions.checkNotNull(multisetToModify);
        Preconditions.checkNotNull(occurrencesToRetain);
        Iterator<Multiset.Entry<E>> entryIterator = multisetToModify.entrySet().iterator();
        boolean changed = false;
        while (entryIterator.hasNext()) {
            Multiset.Entry<E> entry = entryIterator.next();
            int retainCount = occurrencesToRetain.count(entry.getElement());
            if (retainCount == 0) {
                entryIterator.remove();
                changed = true;
            } else if (retainCount < entry.getCount()) {
                multisetToModify.setCount(entry.getElement(), retainCount);
                changed = true;
            }
        }
        return changed;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multisetToModify, Iterable<?> occurrencesToRemove) {
        if (occurrencesToRemove instanceof Multiset) {
            return removeOccurrences(multisetToModify, (Multiset<?>) ((Multiset) occurrencesToRemove));
        }
        Preconditions.checkNotNull(multisetToModify);
        Preconditions.checkNotNull(occurrencesToRemove);
        boolean changed = false;
        Iterator<?> it = occurrencesToRemove.iterator();
        while (it.hasNext()) {
            changed |= multisetToModify.remove(it.next());
        }
        return changed;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multisetToModify, Multiset<?> occurrencesToRemove) {
        Preconditions.checkNotNull(multisetToModify);
        Preconditions.checkNotNull(occurrencesToRemove);
        boolean changed = false;
        Iterator<? extends Multiset.Entry<?>> entryIterator = multisetToModify.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Multiset.Entry<?> entry = entryIterator.next();
            int removeCount = occurrencesToRemove.count(entry.getElement());
            if (removeCount >= entry.getCount()) {
                entryIterator.remove();
                changed = true;
            } else if (removeCount > 0) {
                multisetToModify.remove(entry.getElement(), removeCount);
                changed = true;
            }
        }
        return changed;
    }

    /* access modifiers changed from: package-private */
    public static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        AbstractEntry() {
        }

        @Override // com.google.common.collect.Multiset.Entry
        public boolean equals(@NullableDecl Object object) {
            if (!(object instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry<?> that = (Multiset.Entry) object;
            if (getCount() != that.getCount() || !Objects.equal(getElement(), that.getElement())) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int hashCode() {
            E e = getElement();
            return (e == null ? 0 : e.hashCode()) ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String text = String.valueOf(getElement());
            int n = getCount();
            return n == 1 ? text : text + " x " + n;
        }
    }

    static boolean equalsImpl(Multiset<?> multiset, @NullableDecl Object object) {
        if (object == multiset) {
            return true;
        }
        if (!(object instanceof Multiset)) {
            return false;
        }
        Multiset<?> that = (Multiset) object;
        if (!(multiset.size() == that.size() && multiset.entrySet().size() == that.entrySet().size())) {
            return false;
        }
        for (Multiset.Entry<?> entry : that.entrySet()) {
            if (multiset.count(entry.getElement()) != entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    static <E> boolean addAllImpl(Multiset<E> self, Collection<? extends E> elements) {
        Preconditions.checkNotNull(self);
        Preconditions.checkNotNull(elements);
        if (elements instanceof Multiset) {
            return addAllImpl((Multiset) self, cast(elements));
        }
        if (elements.isEmpty()) {
            return false;
        }
        return Iterators.addAll(self, elements.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.Multiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private static <E> boolean addAllImpl(Multiset<E> self, Multiset<? extends E> elements) {
        if (elements instanceof AbstractMapBasedMultiset) {
            return addAllImpl((Multiset) self, (AbstractMapBasedMultiset) elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        for (Multiset.Entry<? extends E> entry : elements.entrySet()) {
            self.add(entry.getElement(), entry.getCount());
        }
        return true;
    }

    private static <E> boolean addAllImpl(Multiset<E> self, AbstractMapBasedMultiset<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        elements.addTo(self);
        return true;
    }

    static boolean removeAllImpl(Multiset<?> self, Collection<?> elementsToRemove) {
        Collection<?> collection;
        if (elementsToRemove instanceof Multiset) {
            collection = ((Multiset) elementsToRemove).elementSet();
        } else {
            collection = elementsToRemove;
        }
        return self.elementSet().removeAll(collection);
    }

    static boolean retainAllImpl(Multiset<?> self, Collection<?> elementsToRetain) {
        Collection<?> collection;
        Preconditions.checkNotNull(elementsToRetain);
        if (elementsToRetain instanceof Multiset) {
            collection = ((Multiset) elementsToRetain).elementSet();
        } else {
            collection = elementsToRetain;
        }
        return self.elementSet().retainAll(collection);
    }

    static <E> int setCountImpl(Multiset<E> self, E element, int count) {
        CollectPreconditions.checkNonnegative(count, "count");
        int oldCount = self.count(element);
        int delta = count - oldCount;
        if (delta > 0) {
            self.add(element, delta);
        } else if (delta < 0) {
            self.remove(element, -delta);
        }
        return oldCount;
    }

    static <E> boolean setCountImpl(Multiset<E> self, E element, int oldCount, int newCount) {
        CollectPreconditions.checkNonnegative(oldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        if (self.count(element) != oldCount) {
            return false;
        }
        self.setCount(element, newCount);
        return true;
    }

    static <E> Iterator<E> elementIterator(Iterator<Multiset.Entry<E>> entryIterator) {
        return new TransformedIterator<Multiset.Entry<E>, E>(entryIterator) {
            /* class com.google.common.collect.Multisets.AnonymousClass5 */

            /* access modifiers changed from: package-private */
            public E transform(Multiset.Entry<E> entry) {
                return entry.getElement();
            }
        };
    }

    /* access modifiers changed from: package-private */
    public static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public abstract Iterator<E> iterator();

        /* access modifiers changed from: package-private */
        public abstract Multiset<E> multiset();

        ElementSet() {
        }

        public void clear() {
            multiset().clear();
        }

        public boolean contains(Object o) {
            return multiset().contains(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            return multiset().containsAll(c);
        }

        public boolean isEmpty() {
            return multiset().isEmpty();
        }

        public boolean remove(Object o) {
            return multiset().remove(o, Integer.MAX_VALUE) > 0;
        }

        public int size() {
            return multiset().entrySet().size();
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        /* access modifiers changed from: package-private */
        public abstract Multiset<E> multiset();

        EntrySet() {
        }

        public boolean contains(@NullableDecl Object o) {
            if (!(o instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry<?> entry = (Multiset.Entry) o;
            if (entry.getCount() > 0 && multiset().count(entry.getElement()) == entry.getCount()) {
                return true;
            }
            return false;
        }

        public boolean remove(Object object) {
            if (!(object instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry<?> entry = (Multiset.Entry) object;
            Object obj = (E) entry.getElement();
            int entryCount = entry.getCount();
            if (entryCount != 0) {
                return multiset().setCount(obj, entryCount, 0);
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

        MultisetIteratorImpl(Multiset<E> multiset2, Iterator<Multiset.Entry<E>> entryIterator2) {
            this.multiset = multiset2;
            this.entryIterator = entryIterator2;
        }

        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
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

    static int linearTimeSizeImpl(Multiset<?> multiset) {
        long size = 0;
        for (Multiset.Entry<?> entry : multiset.entrySet()) {
            size += (long) entry.getCount();
        }
        return Ints.saturatedCast(size);
    }

    static <T> Multiset<T> cast(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        Multiset.Entry<E>[] entries = (Multiset.Entry[]) multiset.entrySet().toArray(new Multiset.Entry[0]);
        Arrays.sort(entries, DecreasingCount.INSTANCE);
        return ImmutableMultiset.copyFromEntries(Arrays.asList(entries));
    }

    private static final class DecreasingCount implements Comparator<Multiset.Entry<?>> {
        static final DecreasingCount INSTANCE = new DecreasingCount();

        private DecreasingCount() {
        }

        public int compare(Multiset.Entry<?> entry1, Multiset.Entry<?> entry2) {
            return entry2.getCount() - entry1.getCount();
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
