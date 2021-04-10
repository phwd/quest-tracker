package com.facebook.acra.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class BoundedLinkedList<E> extends LinkedList<E> {
    public int maxSize = -1;

    @Override // java.util.LinkedList, java.util.Deque
    public void addFirst(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    public BoundedLinkedList(int i) {
        this.maxSize = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.LinkedList, java.util.Deque
    public void addLast(Object obj) {
        add(obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.AbstractList, java.util.LinkedList, java.util.AbstractSequentialList
    public void add(int i, Object obj) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        super.add(i, obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Queue, java.util.LinkedList, java.util.Deque
    public boolean add(Object obj) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        return super.add(obj);
    }

    @Override // java.util.List, java.util.AbstractList, java.util.LinkedList, java.util.AbstractSequentialList
    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.LinkedList
    public boolean addAll(Collection collection) {
        int size = (size() + collection.size()) - this.maxSize;
        if (size > 0) {
            removeRange(0, size);
        }
        return super.addAll(collection);
    }
}
