package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> comparator;
    @MonotonicNonNullDecl
    private transient SortedMultiset<E> descendingMultiset;

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> descendingEntryIterator();

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    AbstractSortedMultiset(Comparator<? super E> comparator2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedMultisetBridge, com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public NavigableSet<E> createElementSet() {
        return new SortedMultisets.NavigableElementSet(this);
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            return entryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = descendingEntryIterator();
        if (entryIterator.hasNext()) {
            return entryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry<E> result = entryIterator.next();
        Multiset.Entry<E> result2 = Multisets.immutableEntry(result.getElement(), result.getCount());
        entryIterator.remove();
        return result2;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = descendingEntryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry<E> result = entryIterator.next();
        Multiset.Entry<E> result2 = Multisets.immutableEntry(result.getElement(), result.getCount());
        entryIterator.remove();
        return result2;
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> subMultiset(@NullableDecl E fromElement, BoundType fromBoundType, @NullableDecl E toElement, BoundType toBoundType) {
        Preconditions.checkNotNull(fromBoundType);
        Preconditions.checkNotNull(toBoundType);
        return tailMultiset(fromElement, fromBoundType).headMultiset(toElement, toBoundType);
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(descendingMultiset());
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> result = this.descendingMultiset;
        if (result != null) {
            return result;
        }
        SortedMultiset<E> result2 = createDescendingMultiset();
        this.descendingMultiset = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public SortedMultiset<E> createDescendingMultiset() {
        return new DescendingMultiset<E>() {
            /* class com.google.common.collect.AbstractSortedMultiset.AnonymousClass1DescendingMultisetImpl */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.DescendingMultiset
            public SortedMultiset<E> forwardMultiset() {
                return AbstractSortedMultiset.this;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.DescendingMultiset
            public Iterator<Multiset.Entry<E>> entryIterator() {
                return AbstractSortedMultiset.this.descendingEntryIterator();
            }

            @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.ForwardingCollection, com.google.common.collect.DescendingMultiset, java.lang.Iterable
            public Iterator<E> iterator() {
                return AbstractSortedMultiset.this.descendingIterator();
            }
        };
    }
}
