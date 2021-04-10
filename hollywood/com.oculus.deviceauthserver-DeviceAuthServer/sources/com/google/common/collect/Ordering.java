package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class Ordering<T> implements Comparator<T> {
    static final int LEFT_IS_GREATER = 1;
    static final int RIGHT_IS_GREATER = -1;

    @Override // java.util.Comparator
    public abstract int compare(@Nullable T t, @Nullable T t2);

    @GwtCompatible(serializable = true)
    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> from(Comparator<T> comparator) {
        if (comparator instanceof Ordering) {
            return (Ordering) comparator;
        }
        return new ComparatorOrdering(comparator);
    }

    @GwtCompatible(serializable = true)
    @Deprecated
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        return (Ordering) Preconditions.checkNotNull(ordering);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(List<T> valuesInOrder) {
        return new ExplicitOrdering(valuesInOrder);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(T leastValue, T... remainingValuesInOrder) {
        return explicit(Lists.asList(leastValue, remainingValuesInOrder));
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    public static Ordering<Object> arbitrary() {
        return ArbitraryOrderingHolder.ARBITRARY_ORDERING;
    }

    private static class ArbitraryOrderingHolder {
        static final Ordering<Object> ARBITRARY_ORDERING = new ArbitraryOrdering();

        private ArbitraryOrderingHolder() {
        }
    }

    @VisibleForTesting
    static class ArbitraryOrdering extends Ordering<Object> {
        private Map<Object, Integer> uids = Platform.tryWeakKeys(new MapMaker()).makeComputingMap(new Function<Object, Integer>() {
            /* class com.google.common.collect.Ordering.ArbitraryOrdering.AnonymousClass1 */
            final AtomicInteger counter = new AtomicInteger(0);

            @Override // com.google.common.base.Function
            public Integer apply(Object from) {
                return Integer.valueOf(this.counter.getAndIncrement());
            }
        });

        ArbitraryOrdering() {
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Object left, Object right) {
            if (left == right) {
                return 0;
            }
            if (left == null) {
                return -1;
            }
            if (right == null) {
                return 1;
            }
            int leftCode = identityHashCode(left);
            int rightCode = identityHashCode(right);
            if (leftCode == rightCode) {
                int result = this.uids.get(left).compareTo(this.uids.get(right));
                if (result != 0) {
                    return result;
                }
                throw new AssertionError();
            } else if (leftCode < rightCode) {
                return -1;
            } else {
                return 1;
            }
        }

        public String toString() {
            return "Ordering.arbitrary()";
        }

        /* access modifiers changed from: package-private */
        public int identityHashCode(Object object) {
            return System.identityHashCode(object);
        }
    }

    protected Ordering() {
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    /* access modifiers changed from: package-private */
    public <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return onResultOf(Maps.keyFunction());
    }

    @GwtCompatible(serializable = true)
    public <U extends T> Ordering<U> compound(Comparator<? super U> secondaryComparator) {
        return new CompoundOrdering(this, (Comparator) Preconditions.checkNotNull(secondaryComparator));
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> comparators) {
        return new CompoundOrdering(comparators);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    public <E extends T> E min(Iterator<E> iterator) {
        E minSoFar = iterator.next();
        while (iterator.hasNext()) {
            minSoFar = (E) min(minSoFar, iterator.next());
        }
        return minSoFar;
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return (E) min(iterable.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: E extends T */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: E extends T */
    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E min(@Nullable E a, @Nullable E b) {
        return compare(a, b) <= 0 ? a : b;
    }

    public <E extends T> E min(@Nullable E a, @Nullable E b, @Nullable E c, E... rest) {
        E minSoFar = (E) min(min(a, b), c);
        for (E r : rest) {
            minSoFar = (E) min(minSoFar, r);
        }
        return minSoFar;
    }

    public <E extends T> E max(Iterator<E> iterator) {
        E maxSoFar = iterator.next();
        while (iterator.hasNext()) {
            maxSoFar = (E) max(maxSoFar, iterator.next());
        }
        return maxSoFar;
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return (E) max(iterable.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: E extends T */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: E extends T */
    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E max(@Nullable E a, @Nullable E b) {
        return compare(a, b) >= 0 ? a : b;
    }

    public <E extends T> E max(@Nullable E a, @Nullable E b, @Nullable E c, E... rest) {
        E maxSoFar = (E) max(max(a, b), c);
        for (E r : rest) {
            maxSoFar = (E) max(maxSoFar, r);
        }
        return maxSoFar;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int k) {
        if (iterable instanceof Collection) {
            Collection<E> collection = (Collection) iterable;
            if (((long) collection.size()) <= ((long) k) * 2) {
                Object[] array = collection.toArray();
                Arrays.sort(array, this);
                if (array.length > k) {
                    array = ObjectArrays.arraysCopyOf(array, k);
                }
                return Collections.unmodifiableList(Arrays.asList(array));
            }
        }
        return leastOf(iterable.iterator(), k);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r12v0, resolved type: com.google.common.collect.Ordering<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r0v4 int: [D('bufferCap' int), D('list' java.util.ArrayList<E extends T>)] */
    public <E extends T> List<E> leastOf(Iterator<E> elements, int k) {
        Preconditions.checkNotNull(elements);
        CollectPreconditions.checkNonnegative(k, "k");
        if (k == 0 || !elements.hasNext()) {
            return ImmutableList.of();
        }
        if (k >= 1073741823) {
            ArrayList<E> list = Lists.newArrayList(elements);
            Collections.sort(list, this);
            if (list.size() > k) {
                list.subList(k, list.size()).clear();
            }
            list.trimToSize();
            return Collections.unmodifiableList(list);
        }
        int bufferCap = k * 2;
        Object[] objArr = new Object[bufferCap];
        Object next = elements.next();
        objArr[0] = next;
        int bufferSize = 1;
        while (bufferSize < k && elements.hasNext()) {
            E e = elements.next();
            objArr[bufferSize] = e;
            next = max(next, e);
            bufferSize++;
        }
        while (elements.hasNext()) {
            E e2 = elements.next();
            if (compare(e2, next) < 0) {
                int bufferSize2 = bufferSize + 1;
                objArr[bufferSize] = e2;
                if (bufferSize2 == bufferCap) {
                    int left = 0;
                    int right = bufferCap - 1;
                    int minThresholdPosition = 0;
                    while (left < right) {
                        int pivotNewIndex = partition(objArr, left, right, ((left + right) + 1) >>> 1);
                        if (pivotNewIndex <= k) {
                            if (pivotNewIndex >= k) {
                                break;
                            }
                            left = Math.max(pivotNewIndex, left + 1);
                            minThresholdPosition = pivotNewIndex;
                        } else {
                            right = pivotNewIndex - 1;
                        }
                    }
                    next = objArr[minThresholdPosition];
                    for (int i = minThresholdPosition + 1; i < k; i++) {
                        next = max(next, objArr[i]);
                    }
                    bufferSize = k;
                } else {
                    bufferSize = bufferSize2;
                }
            }
        }
        Arrays.sort(objArr, 0, bufferSize, this);
        return Collections.unmodifiableList(Arrays.asList(ObjectArrays.arraysCopyOf(objArr, Math.min(bufferSize, k))));
    }

    private <E extends T> int partition(E[] values, int left, int right, int pivotIndex) {
        E pivotValue = values[pivotIndex];
        values[pivotIndex] = values[right];
        values[right] = pivotValue;
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (compare(values[i], pivotValue) < 0) {
                ObjectArrays.swap(values, storeIndex, i);
                storeIndex++;
            }
        }
        ObjectArrays.swap(values, right, storeIndex);
        return storeIndex;
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int k) {
        return reverse().leastOf(iterable, k);
    }

    public <E extends T> List<E> greatestOf(Iterator<E> iterator, int k) {
        return reverse().leastOf(iterator, k);
    }

    public <E extends T> List<E> sortedCopy(Iterable<E> elements) {
        Object[] array = Iterables.toArray(elements);
        Arrays.sort(array, this);
        return Lists.newArrayList(Arrays.asList(array));
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> elements) {
        Object[] array = Iterables.toArray(elements);
        for (Object obj : array) {
            Preconditions.checkNotNull(obj);
        }
        Arrays.sort(array, this);
        return ImmutableList.asImmutableList(array);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.Ordering<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.Ordering<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public int binarySearch(List<? extends T> sortedList, @Nullable T key) {
        return Collections.binarySearch(sortedList, key, this);
    }

    @VisibleForTesting
    static class IncomparableValueException extends ClassCastException {
        private static final long serialVersionUID = 0;
        final Object value;

        IncomparableValueException(Object value2) {
            super("Cannot compare value: " + value2);
            this.value = value2;
        }
    }
}
