package com.google.common.collect;

import java.util.Deque;
import java.util.Iterator;

public abstract class ForwardingDeque<E> extends ForwardingQueue<E> implements Deque<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
    public abstract Deque<E> delegate();

    protected ForwardingDeque() {
    }

    @Override // java.util.Deque
    public void addFirst(E e) {
        delegate().addFirst(e);
    }

    @Override // java.util.Deque
    public void addLast(E e) {
        delegate().addLast(e);
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    @Override // java.util.Deque
    public E getFirst() {
        return delegate().getFirst();
    }

    @Override // java.util.Deque
    public E getLast() {
        return delegate().getLast();
    }

    @Override // java.util.Deque
    public boolean offerFirst(E e) {
        return delegate().offerFirst(e);
    }

    @Override // java.util.Deque
    public boolean offerLast(E e) {
        return delegate().offerLast(e);
    }

    @Override // java.util.Deque
    public E peekFirst() {
        return delegate().peekFirst();
    }

    @Override // java.util.Deque
    public E peekLast() {
        return delegate().peekLast();
    }

    @Override // java.util.Deque
    public E pollFirst() {
        return delegate().pollFirst();
    }

    @Override // java.util.Deque
    public E pollLast() {
        return delegate().pollLast();
    }

    @Override // java.util.Deque
    public E pop() {
        return delegate().pop();
    }

    @Override // java.util.Deque
    public void push(E e) {
        delegate().push(e);
    }

    @Override // java.util.Deque
    public E removeFirst() {
        return delegate().removeFirst();
    }

    @Override // java.util.Deque
    public E removeLast() {
        return delegate().removeLast();
    }

    public boolean removeFirstOccurrence(Object o) {
        return delegate().removeFirstOccurrence(o);
    }

    public boolean removeLastOccurrence(Object o) {
        return delegate().removeLastOccurrence(o);
    }
}
