package com.facebook.common.collectlite;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RingBuffer<T> {
    private final T[] elems;
    private int end = 0;
    private int size = 0;
    private int start = 0;

    public RingBuffer(int i) {
        this.elems = (T[]) new Object[i];
    }

    @Nullable
    public synchronized T enqueue(T t) {
        T t2 = null;
        if (this.elems.length == 0) {
            return null;
        }
        if (isAtCapacity() && !isEmpty()) {
            t2 = dequeue();
        }
        if (this.size < this.elems.length) {
            this.elems[this.end] = t;
            this.end = (this.end + 1) % this.elems.length;
            this.size++;
            return t2;
        }
        throw new IllegalStateException("Overflow");
    }

    public synchronized T dequeue() {
        T t;
        if (this.size > 0) {
            t = this.elems[this.start];
            this.elems[this.start] = null;
            this.start = (this.start + 1) % this.elems.length;
            this.size--;
        } else {
            throw new IllegalStateException("Underflow");
        }
        return t;
    }

    public synchronized T get(int i) {
        if (this.size > i) {
        } else {
            throw new IndexOutOfBoundsException();
        }
        return this.elems[(i + this.start) % this.elems.length];
    }

    public synchronized T remove(int i) {
        T t;
        if (this.size > i) {
            t = this.elems[(this.start + i) % this.elems.length];
            while (true) {
                int i2 = i + 1;
                if (i2 < this.size) {
                    this.elems[(i + this.start) % this.elems.length] = this.elems[(this.start + i2) % this.elems.length];
                    i = i2;
                } else {
                    this.end = (i + this.start) % this.elems.length;
                    this.elems[this.end] = null;
                    this.size--;
                }
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return t;
    }

    public synchronized ArrayList<T> asList() {
        ArrayList<T> arrayList;
        arrayList = new ArrayList<>(size());
        for (int i = 0; i < this.size; i++) {
            arrayList.add(get(i));
        }
        return arrayList;
    }

    public synchronized boolean isEmpty() {
        return this.size == 0;
    }

    public synchronized boolean isAtCapacity() {
        return this.size == this.elems.length;
    }

    public synchronized int size() {
        return this.size;
    }

    public int capacity() {
        return this.elems.length;
    }

    public synchronized void clear() {
        this.size = 0;
        this.start = 0;
        this.end = 0;
        for (int i = 0; i < this.elems.length; i++) {
            this.elems[i] = null;
        }
    }

    public synchronized ArrayList<T> removeAll() {
        ArrayList<T> arrayList;
        arrayList = new ArrayList<>(this.size);
        while (this.size > 0) {
            arrayList.add(dequeue());
        }
        return arrayList;
    }

    public synchronized void addAllToFront(ArrayList<T> arrayList) {
        if (arrayList != null) {
            ArrayList<T> removeAll = removeAll();
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                enqueue(it.next());
            }
            Iterator<T> it2 = removeAll.iterator();
            while (it2.hasNext()) {
                enqueue(it2.next());
            }
        }
    }
}
