package com.facebook.acra.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class BoundedLinkedList<E> extends LinkedList<E> {
    private int maxSize = -1;

    public BoundedLinkedList(int i) {
        this.maxSize = i;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Queue, java.util.LinkedList, java.util.Deque
    public boolean add(E e) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        return super.add(e);
    }

    @Override // java.util.List, java.util.AbstractList, java.util.LinkedList, java.util.AbstractSequentialList
    public void add(int i, E e) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        super.add(i, e);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.LinkedList
    public boolean addAll(Collection<? extends E> collection) {
        int size = (size() + collection.size()) - this.maxSize;
        if (size > 0) {
            removeRange(0, size);
        }
        return super.addAll(collection);
    }

    @Override // java.util.List, java.util.AbstractList, java.util.LinkedList, java.util.AbstractSequentialList
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.LinkedList, java.util.Deque
    public void addFirst(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.LinkedList, java.util.Deque
    public void addLast(E e) {
        add(e);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }
}
