package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class Iterables {
    private Iterables() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Iterable<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Iterable<T> unmodifiableIterable(Iterable<? extends T> iterable) {
        Preconditions.checkNotNull(iterable);
        return ((iterable instanceof UnmodifiableIterable) || (iterable instanceof ImmutableCollection)) ? iterable : new UnmodifiableIterable(iterable);
    }

    @Deprecated
    public static <E> Iterable<E> unmodifiableIterable(ImmutableCollection<E> iterable) {
        return (Iterable) Preconditions.checkNotNull(iterable);
    }

    private static final class UnmodifiableIterable<T> extends FluentIterable<T> {
        private final Iterable<? extends T> iterable;

        private UnmodifiableIterable(Iterable<? extends T> iterable2) {
            this.iterable = iterable2;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.unmodifiableIterator(this.iterable.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return this.iterable.toString();
        }
    }

    public static int size(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return Iterators.size(iterable.iterator());
    }

    public static boolean contains(Iterable<?> iterable, @NullableDecl Object element) {
        if (iterable instanceof Collection) {
            return Collections2.safeContains((Collection) iterable, element);
        }
        return Iterators.contains(iterable.iterator(), element);
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterable<?> removeFrom, Collection<?> elementsToRemove) {
        if (removeFrom instanceof Collection) {
            return ((Collection) removeFrom).removeAll((Collection) Preconditions.checkNotNull(elementsToRemove));
        }
        return Iterators.removeAll(removeFrom.iterator(), elementsToRemove);
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterable<?> removeFrom, Collection<?> elementsToRetain) {
        if (removeFrom instanceof Collection) {
            return ((Collection) removeFrom).retainAll((Collection) Preconditions.checkNotNull(elementsToRetain));
        }
        return Iterators.retainAll(removeFrom.iterator(), elementsToRetain);
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterable<T> removeFrom, Predicate<? super T> predicate) {
        if (!(removeFrom instanceof RandomAccess) || !(removeFrom instanceof List)) {
            return Iterators.removeIf(removeFrom.iterator(), predicate);
        }
        return removeIfFromRandomAccessList((List) removeFrom, (Predicate) Preconditions.checkNotNull(predicate));
    }

    private static <T> boolean removeIfFromRandomAccessList(List<T> list, Predicate<? super T> predicate) {
        int from = 0;
        int to = 0;
        while (from < list.size()) {
            T element = list.get(from);
            if (!predicate.apply(element)) {
                if (from > to) {
                    try {
                        list.set(to, element);
                    } catch (UnsupportedOperationException e) {
                        slowRemoveIfForRemainingElements(list, predicate, to, from);
                        return true;
                    } catch (IllegalArgumentException e2) {
                        slowRemoveIfForRemainingElements(list, predicate, to, from);
                        return true;
                    }
                }
                to++;
            }
            from++;
        }
        list.subList(to, list.size()).clear();
        if (from == to) {
            return false;
        }
        return true;
    }

    private static <T> void slowRemoveIfForRemainingElements(List<T> list, Predicate<? super T> predicate, int to, int from) {
        for (int n = list.size() - 1; n > from; n--) {
            if (predicate.apply(list.get(n))) {
                list.remove(n);
            }
        }
        for (int n2 = from - 1; n2 >= to; n2--) {
            list.remove(n2);
        }
    }

    @NullableDecl
    static <T> T removeFirstMatching(Iterable<T> removeFrom, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        Iterator<T> iterator = removeFrom.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.apply(next)) {
                iterator.remove();
                return next;
            }
        }
        return null;
    }

    public static boolean elementsEqual(Iterable<?> iterable1, Iterable<?> iterable2) {
        if (!(iterable1 instanceof Collection) || !(iterable2 instanceof Collection) || ((Collection) iterable1).size() == ((Collection) iterable2).size()) {
            return Iterators.elementsEqual(iterable1.iterator(), iterable2.iterator());
        }
        return false;
    }

    public static String toString(Iterable<?> iterable) {
        return Iterators.toString(iterable.iterator());
    }

    public static <T> T getOnlyElement(Iterable<T> iterable) {
        return (T) Iterators.getOnlyElement(iterable.iterator());
    }

    @NullableDecl
    public static <T> T getOnlyElement(Iterable<? extends T> iterable, @NullableDecl T defaultValue) {
        return (T) Iterators.getOnlyElement(iterable.iterator(), defaultValue);
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
        return (T[]) toArray(iterable, ObjectArrays.newArray(type, 0));
    }

    static <T> T[] toArray(Iterable<? extends T> iterable, T[] array) {
        return (T[]) castOrCopyToCollection(iterable).toArray(array);
    }

    static Object[] toArray(Iterable<?> iterable) {
        return castOrCopyToCollection(iterable).toArray();
    }

    private static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        return Lists.newArrayList(iterable.iterator());
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> addTo, Iterable<? extends T> elementsToAdd) {
        if (elementsToAdd instanceof Collection) {
            return addTo.addAll(Collections2.cast(elementsToAdd));
        }
        return Iterators.addAll(addTo, ((Iterable) Preconditions.checkNotNull(elementsToAdd)).iterator());
    }

    public static int frequency(Iterable<?> iterable, @NullableDecl Object element) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).count(element);
        }
        if (iterable instanceof Set) {
            return ((Set) iterable).contains(element) ? 1 : 0;
        }
        return Iterators.frequency(iterable.iterator(), element);
    }

    public static <T> Iterable<T> cycle(final Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.cycle(iterable);
            }

            @Override // com.google.common.collect.FluentIterable
            public String toString() {
                return iterable.toString() + " (cycled)";
            }
        };
    }

    @SafeVarargs
    public static <T> Iterable<T> cycle(T... elements) {
        return cycle(Lists.newArrayList(elements));
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return FluentIterable.concat(a, b);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c) {
        return FluentIterable.concat(a, b, c);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c, Iterable<? extends T> d) {
        return FluentIterable.concat(a, b, c, d);
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(Iterable<? extends T>... inputs) {
        return FluentIterable.concat(inputs);
    }

    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> inputs) {
        return FluentIterable.concat(inputs);
    }

    public static <T> Iterable<List<T>> partition(final Iterable<T> iterable, final int size) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(size > 0);
        return new FluentIterable<List<T>>() {
            /* class com.google.common.collect.Iterables.AnonymousClass2 */

            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.partition(iterable.iterator(), size);
            }
        };
    }

    public static <T> Iterable<List<T>> paddedPartition(final Iterable<T> iterable, final int size) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(size > 0);
        return new FluentIterable<List<T>>() {
            /* class com.google.common.collect.Iterables.AnonymousClass3 */

            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.paddedPartition(iterable.iterator(), size);
            }
        };
    }

    public static <T> Iterable<T> filter(final Iterable<T> unfiltered, final Predicate<? super T> retainIfTrue) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(retainIfTrue);
        return new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass4 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.filter(unfiltered.iterator(), retainIfTrue);
            }
        };
    }

    @GwtIncompatible
    public static <T> Iterable<T> filter(Iterable<?> unfiltered, Class<T> desiredType) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(desiredType);
        return filter(unfiltered, Predicates.instanceOf(desiredType));
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.any(iterable.iterator(), predicate);
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.all(iterable.iterator(), predicate);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        return (T) Iterators.find(iterable.iterator(), predicate);
    }

    @NullableDecl
    public static <T> T find(Iterable<? extends T> iterable, Predicate<? super T> predicate, @NullableDecl T defaultValue) {
        return (T) Iterators.find(iterable.iterator(), predicate, defaultValue);
    }

    public static <T> Optional<T> tryFind(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.tryFind(iterable.iterator(), predicate);
    }

    public static <T> int indexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterable.iterator(), predicate);
    }

    public static <F, T> Iterable<T> transform(final Iterable<F> fromIterable, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(fromIterable);
        Preconditions.checkNotNull(function);
        return new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass5 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.transform(fromIterable.iterator(), function);
            }
        };
    }

    public static <T> T get(Iterable<T> iterable, int position) {
        Preconditions.checkNotNull(iterable);
        return iterable instanceof List ? (T) ((List) iterable).get(position) : (T) Iterators.get(iterable.iterator(), position);
    }

    @NullableDecl
    public static <T> T get(Iterable<? extends T> iterable, int position, @NullableDecl T defaultValue) {
        Preconditions.checkNotNull(iterable);
        Iterators.checkNonnegative(position);
        if (iterable instanceof List) {
            List<? extends T> list = Lists.cast(iterable);
            return position < list.size() ? (T) list.get(position) : defaultValue;
        }
        Iterator<? extends T> iterator = iterable.iterator();
        Iterators.advance(iterator, position);
        return (T) Iterators.getNext(iterator, defaultValue);
    }

    @NullableDecl
    public static <T> T getFirst(Iterable<? extends T> iterable, @NullableDecl T defaultValue) {
        return (T) Iterators.getNext(iterable.iterator(), defaultValue);
    }

    public static <T> T getLast(Iterable<T> iterable) {
        if (!(iterable instanceof List)) {
            return (T) Iterators.getLast(iterable.iterator());
        }
        List<T> list = (List) iterable;
        if (!list.isEmpty()) {
            return (T) getLastInNonemptyList(list);
        }
        throw new NoSuchElementException();
    }

    @NullableDecl
    public static <T> T getLast(Iterable<? extends T> iterable, @NullableDecl T defaultValue) {
        if (iterable instanceof Collection) {
            if (Collections2.cast(iterable).isEmpty()) {
                return defaultValue;
            }
            if (iterable instanceof List) {
                return (T) getLastInNonemptyList(Lists.cast(iterable));
            }
        }
        return (T) Iterators.getLast(iterable.iterator(), defaultValue);
    }

    private static <T> T getLastInNonemptyList(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> Iterable<T> skip(final Iterable<T> iterable, final int numberToSkip) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(numberToSkip >= 0, "number to skip cannot be negative");
        return new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass6 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                if (iterable instanceof List) {
                    List<T> list = (List) iterable;
                    return list.subList(Math.min(list.size(), numberToSkip), list.size()).iterator();
                }
                final Iterator<T> iterator = iterable.iterator();
                Iterators.advance(iterator, numberToSkip);
                return new Iterator<T>() {
                    /* class com.google.common.collect.Iterables.AnonymousClass6.AnonymousClass1 */
                    boolean atStart = true;

                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public T next() {
                        T result = (T) iterator.next();
                        this.atStart = false;
                        return result;
                    }

                    public void remove() {
                        CollectPreconditions.checkRemove(!this.atStart);
                        iterator.remove();
                    }
                };
            }
        };
    }

    public static <T> Iterable<T> limit(final Iterable<T> iterable, final int limitSize) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(limitSize >= 0, "limit is negative");
        return new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass7 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.limit(iterable.iterator(), limitSize);
            }
        };
    }

    public static <T> Iterable<T> consumingIterable(final Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass8 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                if (iterable instanceof Queue) {
                    return new ConsumingQueueIterator((Queue) iterable);
                }
                return Iterators.consumingIterator(iterable.iterator());
            }

            @Override // com.google.common.collect.FluentIterable
            public String toString() {
                return "Iterables.consumingIterable(...)";
            }
        };
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    @Beta
    public static <T> Iterable<T> mergeSorted(final Iterable<? extends Iterable<? extends T>> iterables, final Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterables, "iterables");
        Preconditions.checkNotNull(comparator, "comparator");
        return new UnmodifiableIterable(new FluentIterable<T>() {
            /* class com.google.common.collect.Iterables.AnonymousClass9 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.mergeSorted(Iterables.transform(iterables, Iterables.toIterator()), comparator);
            }
        });
    }

    static <T> Function<Iterable<? extends T>, Iterator<? extends T>> toIterator() {
        return new Function<Iterable<? extends T>, Iterator<? extends T>>() {
            /* class com.google.common.collect.Iterables.AnonymousClass10 */

            public Iterator<? extends T> apply(Iterable<? extends T> iterable) {
                return iterable.iterator();
            }
        };
    }
}
