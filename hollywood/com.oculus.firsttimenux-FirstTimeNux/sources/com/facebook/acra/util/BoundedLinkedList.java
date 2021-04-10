package com.facebook.acra.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class BoundedLinkedList<E> extends LinkedList<E> {
    private int maxSize = -1;

    public BoundedLinkedList(int maxSize2) {
        this.maxSize = maxSize2;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Queue, java.util.LinkedList, java.util.Deque
    public boolean add(E object) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        return super.add(object);
    }

    @Override // java.util.List, java.util.AbstractList, java.util.LinkedList, java.util.AbstractSequentialList
    public void add(int location, E object) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        super.add(location, object);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.LinkedList
    public boolean addAll(Collection<? extends E> collection) {
        int overhead = (size() + collection.size()) - this.maxSize;
        if (overhead > 0) {
            removeRange(0, overhead);
        }
        return super.addAll(collection);
    }

    @Override // java.util.List, java.util.AbstractList, java.util.LinkedList, java.util.AbstractSequentialList
    public boolean addAll(int location, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.LinkedList, java.util.Deque
    public void addFirst(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.LinkedList, java.util.Deque
    public void addLast(E object) {
        add(object);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            result.append(it.next().toString());
        }
        return result.toString();
    }
}
