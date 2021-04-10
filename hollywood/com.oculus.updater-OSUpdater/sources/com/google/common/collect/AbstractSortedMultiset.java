package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
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

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
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
        Iterator<Multiset.Entry<E>> descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            return descendingEntryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = entryIterator.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.getElement(), next.getCount());
        entryIterator.remove();
        return immutableEntry;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        Iterator<Multiset.Entry<E>> descendingEntryIterator = descendingEntryIterator();
        if (!descendingEntryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = descendingEntryIterator.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.getElement(), next.getCount());
        descendingEntryIterator.remove();
        return immutableEntry;
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> subMultiset(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(descendingMultiset());
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset != null) {
            return sortedMultiset;
        }
        SortedMultiset<E> createDescendingMultiset = createDescendingMultiset();
        this.descendingMultiset = createDescendingMultiset;
        return createDescendingMultiset;
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

            @Override // java.util.Collection, com.google.common.collect.ForwardingCollection, com.google.common.collect.DescendingMultiset, java.lang.Iterable
            public Iterator<E> iterator() {
                return AbstractSortedMultiset.this.descendingIterator();
            }
        };
    }
}
