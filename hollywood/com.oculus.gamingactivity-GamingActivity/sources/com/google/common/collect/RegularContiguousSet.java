package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class RegularContiguousSet<C extends Comparable> extends ContiguousSet<C> {
    private static final long serialVersionUID = 0;
    private final Range<C> range;

    RegularContiguousSet(Range<C> range2, DiscreteDomain<C> domain) {
        super(domain);
        this.range = range2;
    }

    private ContiguousSet<C> intersectionInCurrentDomain(Range<C> other) {
        if (this.range.isConnected(other)) {
            return ContiguousSet.create(this.range.intersection(other), this.domain);
        }
        return new EmptyContiguousSet(this.domain);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> headSetImpl(C toElement, boolean inclusive) {
        return intersectionInCurrentDomain(Range.upTo(toElement, BoundType.forBoolean(inclusive)));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        if (fromElement.compareTo(toElement) != 0 || fromInclusive || toInclusive) {
            return intersectionInCurrentDomain(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
        }
        return new EmptyContiguousSet(this.domain);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> tailSetImpl(C fromElement, boolean inclusive) {
        return intersectionInCurrentDomain(Range.downTo(fromElement, BoundType.forBoolean(inclusive)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.collect.DiscreteDomain */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    public int indexOf(Object target) {
        if (contains(target)) {
            return (int) this.domain.distance(first(), (Comparable) target);
        }
        return -1;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.util.NavigableSet, java.lang.Iterable, java.util.AbstractCollection, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<C> iterator() {
        return new AbstractSequentialIterator<C>(first()) {
            /* class com.google.common.collect.RegularContiguousSet.AnonymousClass1 */
            final C last = ((C) RegularContiguousSet.this.last());

            /* access modifiers changed from: protected */
            public C computeNext(C previous) {
                if (RegularContiguousSet.equalsOrThrow(previous, this.last)) {
                    return null;
                }
                return (C) RegularContiguousSet.this.domain.next(previous);
            }
        };
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return new AbstractSequentialIterator<C>(last()) {
            /* class com.google.common.collect.RegularContiguousSet.AnonymousClass2 */
            final C first = ((C) RegularContiguousSet.this.first());

            /* access modifiers changed from: protected */
            public C computeNext(C previous) {
                if (RegularContiguousSet.equalsOrThrow(previous, this.first)) {
                    return null;
                }
                return (C) RegularContiguousSet.this.domain.previous(previous);
            }
        };
    }

    /* access modifiers changed from: private */
    public static boolean equalsOrThrow(Comparable<?> left, @NullableDecl Comparable<?> right) {
        return right != null && Range.compareOrThrow(left, right) == 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public C first() {
        return this.range.lowerBound.leastValueAbove(this.domain);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public C last() {
        return this.range.upperBound.greatestValueBelow(this.domain);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<C> createAsList() {
        return this.domain.supportsFastOffset ? new ImmutableAsList<C>() {
            /* class com.google.common.collect.RegularContiguousSet.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableAsList
            public ImmutableSortedSet<C> delegateCollection() {
                return RegularContiguousSet.this;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.collect.DiscreteDomain */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.List
            public C get(int i) {
                Preconditions.checkElementIndex(i, size());
                return (C) RegularContiguousSet.this.domain.offset(RegularContiguousSet.this.first(), (long) i);
            }
        } : super.createAsList();
    }

    public int size() {
        long distance = this.domain.distance(first(), last());
        if (distance >= 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return ((int) distance) + 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.Range<C extends java.lang.Comparable> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@NullableDecl Object object) {
        if (object == null) {
            return false;
        }
        try {
            return this.range.contains((Comparable) object);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set, java.util.AbstractCollection
    public boolean containsAll(Collection<?> targets) {
        return Collections2.containsAllImpl(this, targets);
    }

    public boolean isEmpty() {
        return false;
    }

    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> intersection(ContiguousSet<C> other) {
        ContiguousSet<C> other2;
        Preconditions.checkNotNull(other);
        Preconditions.checkArgument(this.domain.equals(other.domain));
        if (other.isEmpty()) {
            return other;
        }
        Comparable comparable = (Comparable) Ordering.natural().max(first(), other.first());
        Comparable comparable2 = (Comparable) Ordering.natural().min(last(), other.last());
        if (comparable.compareTo(comparable2) <= 0) {
            other2 = ContiguousSet.create(Range.closed(comparable, comparable2), this.domain);
        } else {
            other2 = new EmptyContiguousSet<>(this.domain);
        }
        return other2;
    }

    @Override // com.google.common.collect.ContiguousSet
    public Range<C> range() {
        return range(BoundType.CLOSED, BoundType.CLOSED);
    }

    @Override // com.google.common.collect.ContiguousSet
    public Range<C> range(BoundType lowerBoundType, BoundType upperBoundType) {
        return Range.create(this.range.lowerBound.withLowerBoundType(lowerBoundType, this.domain), this.range.upperBound.withUpperBoundType(upperBoundType, this.domain));
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean equals(@NullableDecl Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof RegularContiguousSet) {
            RegularContiguousSet<?> that = (RegularContiguousSet) object;
            if (this.domain.equals(that.domain)) {
                if (!first().equals(that.first()) || !last().equals(that.last())) {
                    return false;
                }
                return true;
            }
        }
        return super.equals(object);
    }

    @Override // com.google.common.collect.ImmutableSet
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @GwtIncompatible
    private static final class SerializedForm<C extends Comparable> implements Serializable {
        final DiscreteDomain<C> domain;
        final Range<C> range;

        private SerializedForm(Range<C> range2, DiscreteDomain<C> domain2) {
            this.range = range2;
            this.domain = domain2;
        }

        private Object readResolve() {
            return new RegularContiguousSet(this.range, this.domain);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.range, this.domain);
    }
}
