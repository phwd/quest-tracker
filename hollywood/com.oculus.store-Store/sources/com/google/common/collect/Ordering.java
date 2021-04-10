package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class Ordering<T> implements Comparator<T> {
    static final int LEFT_IS_GREATER = 1;
    static final int RIGHT_IS_GREATER = -1;

    @Override // java.util.Comparator
    @CanIgnoreReturnValue
    public abstract int compare(@NullableDecl T t, @NullableDecl T t2);

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <T> Ordering<T> from(Comparator<T> comparator) {
        if (comparator instanceof Ordering) {
            return (Ordering) comparator;
        }
        return new ComparatorOrdering(comparator);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    @Deprecated
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        return (Ordering) Preconditions.checkNotNull(ordering);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <T> Ordering<T> explicit(List<T> valuesInOrder) {
        return new ExplicitOrdering(valuesInOrder);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <T> Ordering<T> explicit(T leastValue, T... remainingValuesInOrder) {
        return explicit(Lists.asList(leastValue, remainingValuesInOrder));
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
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
        private final AtomicInteger counter = new AtomicInteger(0);
        private final ConcurrentMap<Object, Integer> uids = Platform.tryWeakKeys(new MapMaker()).makeMap();

        ArbitraryOrdering() {
        }

        private Integer getUid(Object obj) {
            Integer uid = this.uids.get(obj);
            if (uid != null) {
                return uid;
            }
            Integer uid2 = Integer.valueOf(this.counter.getAndIncrement());
            Integer alreadySet = this.uids.putIfAbsent(obj, uid2);
            return alreadySet != null ? alreadySet : uid2;
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
            if (leftCode != rightCode) {
                return leftCode >= rightCode ? 1 : -1;
            }
            int result = getUid(left).compareTo(getUid(right));
            if (result != 0) {
                return result;
            }
            throw new AssertionError();
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

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    /* access modifiers changed from: package-private */
    public <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return onResultOf(Maps.keyFunction());
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public <U extends T> Ordering<U> compound(Comparator<? super U> secondaryComparator) {
        return new CompoundOrdering(this, (Comparator) Preconditions.checkNotNull(secondaryComparator));
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> comparators) {
        return new CompoundOrdering(comparators);
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    @CanIgnoreReturnValue
    public <E extends T> E min(Iterator<E> iterator) {
        E minSoFar = iterator.next();
        while (iterator.hasNext()) {
            minSoFar = (E) min(minSoFar, iterator.next());
        }
        return minSoFar;
    }

    @CanIgnoreReturnValue
    public <E extends T> E min(Iterable<E> iterable) {
        return (E) min(iterable.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: E extends T */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: E extends T */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public <E extends T> E min(@NullableDecl E a, @NullableDecl E b) {
        return compare(a, b) <= 0 ? a : b;
    }

    @CanIgnoreReturnValue
    public <E extends T> E min(@NullableDecl E a, @NullableDecl E b, @NullableDecl E c, E... rest) {
        E minSoFar = (E) min(min(a, b), c);
        for (E r : rest) {
            minSoFar = (E) min(minSoFar, r);
        }
        return minSoFar;
    }

    @CanIgnoreReturnValue
    public <E extends T> E max(Iterator<E> iterator) {
        E maxSoFar = iterator.next();
        while (iterator.hasNext()) {
            maxSoFar = (E) max(maxSoFar, iterator.next());
        }
        return maxSoFar;
    }

    @CanIgnoreReturnValue
    public <E extends T> E max(Iterable<E> iterable) {
        return (E) max(iterable.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: E extends T */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: E extends T */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public <E extends T> E max(@NullableDecl E a, @NullableDecl E b) {
        return compare(a, b) >= 0 ? a : b;
    }

    @CanIgnoreReturnValue
    public <E extends T> E max(@NullableDecl E a, @NullableDecl E b, @NullableDecl E c, E... rest) {
        E maxSoFar = (E) max(max(a, b), c);
        for (E r : rest) {
            maxSoFar = (E) max(maxSoFar, r);
        }
        return maxSoFar;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int k) {
        if (iterable instanceof Collection) {
            Collection<E> collection = (Collection) iterable;
            if (((long) collection.size()) <= 2 * ((long) k)) {
                Object[] array = collection.toArray();
                Arrays.sort(array, this);
                if (array.length > k) {
                    array = Arrays.copyOf(array, k);
                }
                return Collections.unmodifiableList(Arrays.asList(array));
            }
        }
        return leastOf(iterable.iterator(), k);
    }

    public <E extends T> List<E> leastOf(Iterator<E> iterator, int k) {
        Preconditions.checkNotNull(iterator);
        CollectPreconditions.checkNonnegative(k, "k");
        if (k == 0 || !iterator.hasNext()) {
            return Collections.emptyList();
        }
        if (k >= 1073741823) {
            ArrayList<E> list = Lists.newArrayList(iterator);
            Collections.sort(list, this);
            if (list.size() > k) {
                list.subList(k, list.size()).clear();
            }
            list.trimToSize();
            return Collections.unmodifiableList(list);
        }
        TopKSelector<E> selector = TopKSelector.least(k, this);
        selector.offerAll(iterator);
        return selector.topK();
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int k) {
        return reverse().leastOf(iterable, k);
    }

    public <E extends T> List<E> greatestOf(Iterator<E> iterator, int k) {
        return reverse().leastOf(iterator, k);
    }

    @CanIgnoreReturnValue
    public <E extends T> List<E> sortedCopy(Iterable<E> elements) {
        Object[] array = Iterables.toArray(elements);
        Arrays.sort(array, this);
        return Lists.newArrayList(Arrays.asList(array));
    }

    @CanIgnoreReturnValue
    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> elements) {
        return ImmutableList.sortedCopyOf(this, elements);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.Ordering<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (compare(next, next2) > 0) {
                    return false;
                }
                next = next2;
            }
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.Ordering<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (compare(next, next2) >= 0) {
                    return false;
                }
                next = next2;
            }
        }
        return true;
    }

    @Deprecated
    public int binarySearch(List<? extends T> sortedList, @NullableDecl T key) {
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
