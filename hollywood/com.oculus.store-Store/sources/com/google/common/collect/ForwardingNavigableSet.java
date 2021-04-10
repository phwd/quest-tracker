package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

@GwtIncompatible
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
    public abstract NavigableSet<E> delegate();

    protected ForwardingNavigableSet() {
    }

    @Override // java.util.NavigableSet
    public E lower(E e) {
        return delegate().lower(e);
    }

    /* access modifiers changed from: protected */
    public E standardLower(E e) {
        return (E) Iterators.getNext(headSet(e, false).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    public E floor(E e) {
        return delegate().floor(e);
    }

    /* access modifiers changed from: protected */
    public E standardFloor(E e) {
        return (E) Iterators.getNext(headSet(e, true).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e) {
        return delegate().ceiling(e);
    }

    /* access modifiers changed from: protected */
    public E standardCeiling(E e) {
        return (E) Iterators.getNext(tailSet(e, true).iterator(), null);
    }

    @Override // java.util.NavigableSet
    public E higher(E e) {
        return delegate().higher(e);
    }

    /* access modifiers changed from: protected */
    public E standardHigher(E e) {
        return (E) Iterators.getNext(tailSet(e, false).iterator(), null);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return delegate().pollFirst();
    }

    /* access modifiers changed from: protected */
    public E standardPollFirst() {
        return (E) Iterators.pollNext(iterator());
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return delegate().pollLast();
    }

    /* access modifiers changed from: protected */
    public E standardPollLast() {
        return (E) Iterators.pollNext(descendingIterator());
    }

    /* access modifiers changed from: protected */
    public E standardFirst() {
        return iterator().next();
    }

    /* access modifiers changed from: protected */
    public E standardLast() {
        return descendingIterator().next();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return delegate().descendingSet();
    }

    @Beta
    protected class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet() {
            super(ForwardingNavigableSet.this);
        }
    }

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return delegate().subSet(fromElement, fromInclusive, toElement, toInclusive);
    }

    /* access modifiers changed from: protected */
    @Beta
    public NavigableSet<E> standardSubSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return tailSet(fromElement, fromInclusive).headSet(toElement, toInclusive);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet
    public SortedSet<E> standardSubSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return delegate().headSet(toElement, inclusive);
    }

    /* access modifiers changed from: protected */
    public SortedSet<E> standardHeadSet(E toElement) {
        return headSet(toElement, false);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return delegate().tailSet(fromElement, inclusive);
    }

    /* access modifiers changed from: protected */
    public SortedSet<E> standardTailSet(E fromElement) {
        return tailSet(fromElement, true);
    }
}
