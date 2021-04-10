package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public abstract class FluentIterable<E> implements Iterable<E> {
    private final Iterable<E> iterable;

    protected FluentIterable() {
        this.iterable = this;
    }

    FluentIterable(Iterable<E> iterable2) {
        this.iterable = (Iterable) Preconditions.checkNotNull(iterable2);
    }

    public static <E> FluentIterable<E> from(final Iterable<E> iterable2) {
        if (iterable2 instanceof FluentIterable) {
            return (FluentIterable) iterable2;
        }
        return new FluentIterable<E>(iterable2) {
            /* class com.google.common.collect.FluentIterable.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<E> iterator() {
                return iterable2.iterator();
            }
        };
    }

    @Deprecated
    public static <E> FluentIterable<E> from(FluentIterable<E> iterable2) {
        return (FluentIterable) Preconditions.checkNotNull(iterable2);
    }

    @Beta
    public static <E> FluentIterable<E> of(E[] elements) {
        return from(Lists.newArrayList(elements));
    }

    public String toString() {
        return Iterables.toString(this.iterable);
    }

    public final int size() {
        return Iterables.size(this.iterable);
    }

    public final boolean contains(@Nullable Object element) {
        return Iterables.contains(this.iterable, element);
    }

    @CheckReturnValue
    public final FluentIterable<E> cycle() {
        return from(Iterables.cycle(this.iterable));
    }

    @Beta
    @CheckReturnValue
    public final FluentIterable<E> append(Iterable<? extends E> other) {
        return from(Iterables.concat(this.iterable, other));
    }

    @Beta
    @CheckReturnValue
    public final FluentIterable<E> append(E... elements) {
        return from(Iterables.concat(this.iterable, Arrays.asList(elements)));
    }

    @CheckReturnValue
    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        return from(Iterables.filter(this.iterable, predicate));
    }

    @GwtIncompatible("Class.isInstance")
    @CheckReturnValue
    public final <T> FluentIterable<T> filter(Class<T> type) {
        return from(Iterables.filter((Iterable<?>) this.iterable, (Class) type));
    }

    public final boolean anyMatch(Predicate<? super E> predicate) {
        return Iterables.any(this.iterable, predicate);
    }

    public final boolean allMatch(Predicate<? super E> predicate) {
        return Iterables.all(this.iterable, predicate);
    }

    public final Optional<E> firstMatch(Predicate<? super E> predicate) {
        return Iterables.tryFind(this.iterable, predicate);
    }

    public final <T> FluentIterable<T> transform(Function<? super E, T> function) {
        return from(Iterables.transform(this.iterable, function));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.base.Function<? super E, ? extends java.lang.Iterable<? extends T>> */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> FluentIterable<T> transformAndConcat(Function<? super E, ? extends Iterable<? extends T>> function) {
        return from(Iterables.concat(transform(function)));
    }

    public final Optional<E> first() {
        Iterator<E> iterator = this.iterable.iterator();
        if (iterator.hasNext()) {
            return Optional.of(iterator.next());
        }
        return Optional.absent();
    }

    public final Optional<E> last() {
        E current;
        Iterable<E> iterable2 = this.iterable;
        if (iterable2 instanceof List) {
            List<E> list = (List) iterable2;
            if (list.isEmpty()) {
                return Optional.absent();
            }
            return Optional.of(list.get(list.size() - 1));
        }
        Iterator<E> iterator = iterable2.iterator();
        if (!iterator.hasNext()) {
            return Optional.absent();
        }
        Iterable<E> iterable3 = this.iterable;
        if (iterable3 instanceof SortedSet) {
            return Optional.of(((SortedSet) iterable3).last());
        }
        do {
            current = iterator.next();
        } while (iterator.hasNext());
        return Optional.of(current);
    }

    @CheckReturnValue
    public final FluentIterable<E> skip(int numberToSkip) {
        return from(Iterables.skip(this.iterable, numberToSkip));
    }

    @CheckReturnValue
    public final FluentIterable<E> limit(int size) {
        return from(Iterables.limit(this.iterable, size));
    }

    public final boolean isEmpty() {
        return !this.iterable.iterator().hasNext();
    }

    public final ImmutableList<E> toList() {
        return ImmutableList.copyOf(this.iterable);
    }

    public final ImmutableList<E> toSortedList(Comparator<? super E> comparator) {
        return Ordering.from(comparator).immutableSortedCopy(this.iterable);
    }

    public final ImmutableSet<E> toSet() {
        return ImmutableSet.copyOf(this.iterable);
    }

    public final ImmutableSortedSet<E> toSortedSet(Comparator<? super E> comparator) {
        return ImmutableSortedSet.copyOf(comparator, this.iterable);
    }

    public final <V> ImmutableMap<E, V> toMap(Function<? super E, V> valueFunction) {
        return Maps.toMap(this.iterable, valueFunction);
    }

    public final <K> ImmutableListMultimap<K, E> index(Function<? super E, K> keyFunction) {
        return Multimaps.index(this.iterable, keyFunction);
    }

    public final <K> ImmutableMap<K, E> uniqueIndex(Function<? super E, K> keyFunction) {
        return Maps.uniqueIndex(this.iterable, keyFunction);
    }

    @GwtIncompatible("Array.newArray(Class, int)")
    public final E[] toArray(Class<E> type) {
        return (E[]) Iterables.toArray(this.iterable, type);
    }

    public final <C extends Collection<? super E>> C copyInto(C collection) {
        Preconditions.checkNotNull(collection);
        Iterable<E> iterable2 = this.iterable;
        if (iterable2 instanceof Collection) {
            collection.addAll(Collections2.cast(iterable2));
        } else {
            for (E item : iterable2) {
                collection.add(item);
            }
        }
        return collection;
    }

    @Beta
    public final String join(Joiner joiner) {
        return joiner.join(this);
    }

    public final E get(int position) {
        return (E) Iterables.get(this.iterable, position);
    }

    private static class FromIterableFunction<E> implements Function<Iterable<E>, FluentIterable<E>> {
        private FromIterableFunction() {
        }

        @Override // com.google.common.base.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) {
            return apply((Iterable) ((Iterable) obj));
        }

        public FluentIterable<E> apply(Iterable<E> fromObject) {
            return FluentIterable.from(fromObject);
        }
    }
}
