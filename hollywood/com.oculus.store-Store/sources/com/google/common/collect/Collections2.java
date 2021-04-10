package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Collections2 {
    private Collections2() {
    }

    public static <E> Collection<E> filter(Collection<E> unfiltered, Predicate<? super E> predicate) {
        if (unfiltered instanceof FilteredCollection) {
            return ((FilteredCollection) unfiltered).createCombined(predicate);
        }
        return new FilteredCollection((Collection) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
    }

    static boolean safeContains(Collection<?> collection, @NullableDecl Object object) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(object);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    static boolean safeRemove(Collection<?> collection, @NullableDecl Object object) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(object);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        FilteredCollection(Collection<E> unfiltered2, Predicate<? super E> predicate2) {
            this.unfiltered = unfiltered2;
            this.predicate = predicate2;
        }

        /* access modifiers changed from: package-private */
        public FilteredCollection<E> createCombined(Predicate<? super E> newPredicate) {
            return new FilteredCollection<>(this.unfiltered, Predicates.and(this.predicate, newPredicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E element) {
            Preconditions.checkArgument(this.predicate.apply(element));
            return this.unfiltered.add(element);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            Iterator<? extends E> it = collection.iterator();
            while (it.hasNext()) {
                Preconditions.checkArgument(this.predicate.apply(it.next()));
            }
            return this.unfiltered.addAll(collection);
        }

        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        public boolean contains(@NullableDecl Object element) {
            if (Collections2.safeContains(this.unfiltered, element)) {
                return this.predicate.apply(element);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.containsAllImpl(this, collection);
        }

        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        public boolean remove(Object element) {
            return contains(element) && this.unfiltered.remove(element);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            boolean changed = false;
            Iterator<E> itr = this.unfiltered.iterator();
            while (itr.hasNext()) {
                E e = itr.next();
                if (this.predicate.apply(e) && collection.contains(e)) {
                    itr.remove();
                    changed = true;
                }
            }
            return changed;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            boolean changed = false;
            Iterator<E> itr = this.unfiltered.iterator();
            while (itr.hasNext()) {
                E e = itr.next();
                if (this.predicate.apply(e) && !collection.contains(e)) {
                    itr.remove();
                    changed = true;
                }
            }
            return changed;
        }

        public int size() {
            int size = 0;
            for (E e : this.unfiltered) {
                if (this.predicate.apply(e)) {
                    size++;
                }
            }
            return size;
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] array) {
            return (T[]) Lists.newArrayList(iterator()).toArray(array);
        }
    }

    public static <F, T> Collection<T> transform(Collection<F> fromCollection, Function<? super F, T> function) {
        return new TransformedCollection(fromCollection, function);
    }

    /* access modifiers changed from: package-private */
    public static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> fromCollection;
        final Function<? super F, ? extends T> function;

        TransformedCollection(Collection<F> fromCollection2, Function<? super F, ? extends T> function2) {
            this.fromCollection = (Collection) Preconditions.checkNotNull(fromCollection2);
            this.function = (Function) Preconditions.checkNotNull(function2);
        }

        public void clear() {
            this.fromCollection.clear();
        }

        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.fromCollection.iterator(), this.function);
        }

        public int size() {
            return this.fromCollection.size();
        }
    }

    static boolean containsAllImpl(Collection<?> self, Collection<?> c) {
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            if (!self.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    static String toStringImpl(Collection<?> collection) {
        StringBuilder sb = newStringBuilderForCollection(collection.size()).append('[');
        boolean first = true;
        for (Object o : collection) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            if (o == collection) {
                sb.append("(this Collection)");
            } else {
                sb.append(o);
            }
        }
        return sb.append(']').toString();
    }

    static StringBuilder newStringBuilderForCollection(int size) {
        CollectPreconditions.checkNonnegative(size, "size");
        return new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
    }

    static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection) iterable;
    }

    @Beta
    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> elements) {
        return orderedPermutations(elements, Ordering.natural());
    }

    @Beta
    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> elements, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(elements, comparator);
    }

    /* access modifiers changed from: private */
    public static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        final Comparator<? super E> comparator;
        final ImmutableList<E> inputList;
        final int size;

        OrderedPermutationCollection(Iterable<E> input, Comparator<? super E> comparator2) {
            this.inputList = ImmutableList.sortedCopyOf(comparator2, input);
            this.comparator = comparator2;
            this.size = calculateSize(this.inputList, comparator2);
        }

        private static <E> int calculateSize(List<E> sortedInputList, Comparator<? super E> comparator2) {
            int permutations = 1;
            int n = 1;
            int r = 1;
            while (n < sortedInputList.size()) {
                if (comparator2.compare(sortedInputList.get(n - 1), sortedInputList.get(n)) < 0) {
                    permutations = IntMath.saturatedMultiply(permutations, IntMath.binomial(n, r));
                    r = 0;
                    if (permutations == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
                n++;
                r++;
            }
            return IntMath.saturatedMultiply(permutations, IntMath.binomial(n, r));
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.inputList, this.comparator);
        }

        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        public String toString() {
            return "orderedPermutationCollection(" + this.inputList + ")";
        }
    }

    private static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        final Comparator<? super E> comparator;
        @NullableDecl
        List<E> nextPermutation;

        OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator2) {
            this.nextPermutation = Lists.newArrayList(list);
            this.comparator = comparator2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            if (this.nextPermutation == null) {
                return (List) endOfData();
            }
            ImmutableList<E> next = ImmutableList.copyOf((Collection) this.nextPermutation);
            calculateNextPermutation();
            return next;
        }

        /* access modifiers changed from: package-private */
        public void calculateNextPermutation() {
            int j = findNextJ();
            if (j == -1) {
                this.nextPermutation = null;
                return;
            }
            Collections.swap(this.nextPermutation, j, findNextL(j));
            Collections.reverse(this.nextPermutation.subList(j + 1, this.nextPermutation.size()));
        }

        /* access modifiers changed from: package-private */
        public int findNextJ() {
            for (int k = this.nextPermutation.size() - 2; k >= 0; k--) {
                if (this.comparator.compare(this.nextPermutation.get(k), this.nextPermutation.get(k + 1)) < 0) {
                    return k;
                }
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int findNextL(int j) {
            E ak = this.nextPermutation.get(j);
            for (int l = this.nextPermutation.size() - 1; l > j; l--) {
                if (this.comparator.compare(ak, this.nextPermutation.get(l)) < 0) {
                    return l;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }
    }

    @Beta
    public static <E> Collection<List<E>> permutations(Collection<E> elements) {
        return new PermutationCollection(ImmutableList.copyOf((Collection) elements));
    }

    private static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> inputList;

        PermutationCollection(ImmutableList<E> input) {
            this.inputList = input;
        }

        public int size() {
            return IntMath.factorial(this.inputList.size());
        }

        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.inputList);
        }

        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        public String toString() {
            return "permutations(" + this.inputList + ")";
        }
    }

    private static class PermutationIterator<E> extends AbstractIterator<List<E>> {
        final int[] c;
        int j = Integer.MAX_VALUE;
        final List<E> list;
        final int[] o;

        PermutationIterator(List<E> list2) {
            this.list = new ArrayList(list2);
            int n = list2.size();
            this.c = new int[n];
            this.o = new int[n];
            Arrays.fill(this.c, 0);
            Arrays.fill(this.o, 1);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            if (this.j <= 0) {
                return (List) endOfData();
            }
            ImmutableList<E> next = ImmutableList.copyOf((Collection) this.list);
            calculateNextPermutation();
            return next;
        }

        /* access modifiers changed from: package-private */
        public void calculateNextPermutation() {
            this.j = this.list.size() - 1;
            int s = 0;
            if (this.j != -1) {
                while (true) {
                    int q = this.c[this.j] + this.o[this.j];
                    if (q < 0) {
                        switchDirection();
                    } else if (q != this.j + 1) {
                        Collections.swap(this.list, (this.j - this.c[this.j]) + s, (this.j - q) + s);
                        this.c[this.j] = q;
                        return;
                    } else if (this.j != 0) {
                        s++;
                        switchDirection();
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void switchDirection() {
            this.o[this.j] = -this.o[this.j];
            this.j--;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isPermutation(List<?> first, List<?> second) {
        if (first.size() != second.size()) {
            return false;
        }
        ObjectCountHashMap<?> firstCounts = counts(first);
        ObjectCountHashMap<?> secondCounts = counts(second);
        if (first.size() != second.size()) {
            return false;
        }
        for (int i = 0; i < first.size(); i++) {
            if (firstCounts.getValue(i) != secondCounts.get(firstCounts.getKey(i))) {
                return false;
            }
        }
        return true;
    }

    private static <E> ObjectCountHashMap<E> counts(Collection<E> collection) {
        ObjectCountHashMap<E> map = new ObjectCountHashMap<>();
        for (E e : collection) {
            map.put(e, map.get(e) + 1);
        }
        return map;
    }
}
