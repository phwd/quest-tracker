package java.util.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingQueue extends AbstractQueue implements BlockingQueue, Serializable {
    private static final long serialVersionUID = -6903933977591709194L;
    private final int capacity;
    private final AtomicInteger count;
    transient Node head;
    private transient Node last;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ReentrantLock putLock;
    private final ReentrantLock takeLock;

    /* access modifiers changed from: package-private */
    public static class Node {
        Object item;
        Node next;

        Node(Object obj) {
            this.item = obj;
        }
    }

    private void signalNotEmpty() {
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            this.notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void signalNotFull() {
        ReentrantLock reentrantLock = this.putLock;
        reentrantLock.lock();
        try {
            this.notFull.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void enqueue(Node node) {
        this.last.next = node;
        this.last = node;
    }

    private Object dequeue() {
        Node node = this.head;
        Node node2 = node.next;
        node.next = node;
        this.head = node2;
        Object obj = node2.item;
        node2.item = null;
        return obj;
    }

    /* access modifiers changed from: package-private */
    public void fullyLock() {
        this.putLock.lock();
        this.takeLock.lock();
    }

    /* access modifiers changed from: package-private */
    public void fullyUnlock() {
        this.takeLock.unlock();
        this.putLock.unlock();
    }

    public LinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingQueue(int i) {
        this.count = new AtomicInteger();
        this.takeLock = new ReentrantLock();
        this.notEmpty = this.takeLock.newCondition();
        this.putLock = new ReentrantLock();
        this.notFull = this.putLock.newCondition();
        if (i > 0) {
            this.capacity = i;
            Node node = new Node(null);
            this.head = node;
            this.last = node;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(Object obj) {
        if (obj != null) {
            AtomicInteger atomicInteger = this.count;
            if (atomicInteger.get() == this.capacity) {
                return false;
            }
            int i = -1;
            Node node = new Node(obj);
            ReentrantLock reentrantLock = this.putLock;
            reentrantLock.lock();
            try {
                if (atomicInteger.get() < this.capacity) {
                    enqueue(node);
                    i = atomicInteger.getAndIncrement();
                    if (i + 1 < this.capacity) {
                        this.notFull.signal();
                    }
                }
                if (i == 0) {
                    signalNotEmpty();
                }
                if (i >= 0) {
                    return true;
                }
                return false;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.BlockingQueue
    public Object take() {
        AtomicInteger atomicInteger = this.count;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                this.notEmpty.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        Object dequeue = dequeue();
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.notEmpty.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.capacity) {
            signalNotFull();
        }
        return dequeue;
    }

    @Override // java.util.concurrent.BlockingQueue
    public Object poll(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        AtomicInteger atomicInteger = this.count;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        Object dequeue = dequeue();
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.notEmpty.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.capacity) {
            signalNotFull();
        }
        return dequeue;
    }

    /* access modifiers changed from: package-private */
    public void unlink(Node node, Node node2) {
        node.item = null;
        node2.next = node.next;
        if (this.last == node) {
            this.last = node2;
        }
        if (this.count.getAndDecrement() == this.capacity) {
            this.notFull.signal();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        fullyLock();
        try {
            Node node = this.head;
            Node node2 = node.next;
            while (true) {
                node = node2;
                if (node == null) {
                    fullyUnlock();
                    return false;
                } else if (obj.equals(node.item)) {
                    unlink(node, node);
                    return true;
                } else {
                    node2 = node.next;
                }
            }
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        fullyLock();
        try {
            Node node = this.head;
            do {
                node = node.next;
                if (node == null) {
                    fullyUnlock();
                    return false;
                }
            } while (!obj.equals(node.item));
            return true;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        fullyLock();
        try {
            Object[] objArr = new Object[this.count.get()];
            int i = 0;
            Node node = this.head.next;
            while (node != null) {
                int i2 = i + 1;
                objArr[i] = node.item;
                node = node.next;
                i = i2;
            }
            return objArr;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        fullyLock();
        try {
            int i = this.count.get();
            if (objArr.length < i) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
            }
            int i2 = 0;
            Node node = this.head.next;
            while (node != null) {
                objArr[i2] = node.item;
                node = node.next;
                i2++;
            }
            if (objArr.length > i2) {
                objArr[i2] = null;
            }
            return objArr;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return Helpers.collectionToString(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        fullyLock();
        try {
            Node node = this.head;
            while (true) {
                Node node2 = node.next;
                if (node2 == null) {
                    break;
                }
                node.next = node;
                node2.item = null;
                node = node2;
            }
            this.head = this.last;
            if (this.count.getAndSet(0) == this.capacity) {
                this.notFull.signal();
            }
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {
        private Node current;
        private Object currentElement;
        private Node lastRet;

        Itr() {
            LinkedBlockingQueue.this.fullyLock();
            try {
                this.current = LinkedBlockingQueue.this.head.next;
                if (this.current != null) {
                    this.currentElement = this.current.item;
                }
            } finally {
                LinkedBlockingQueue.this.fullyUnlock();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current != null;
        }

        @Override // java.util.Iterator
        public Object next() {
            LinkedBlockingQueue.this.fullyLock();
            try {
                if (this.current != null) {
                    this.lastRet = this.current;
                    Object obj = null;
                    Node node = this.current;
                    do {
                        Node node2 = node.next;
                        node = node2 == node ? LinkedBlockingQueue.this.head.next : node2;
                        if (node == null) {
                            break;
                        }
                        obj = node.item;
                    } while (obj == null);
                    this.current = node;
                    Object obj2 = this.currentElement;
                    this.currentElement = obj;
                    return obj2;
                }
                throw new NoSuchElementException();
            } finally {
                LinkedBlockingQueue.this.fullyUnlock();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastRet != null) {
                LinkedBlockingQueue.this.fullyLock();
                try {
                    Node node = this.lastRet;
                    this.lastRet = null;
                    Node node2 = LinkedBlockingQueue.this.head;
                    Node node3 = node2.next;
                    while (true) {
                        node2 = node3;
                        if (node2 == null) {
                            break;
                        } else if (node2 == node) {
                            LinkedBlockingQueue.this.unlink(node2, node2);
                            break;
                        } else {
                            node3 = node2.next;
                        }
                    }
                } finally {
                    LinkedBlockingQueue.this.fullyUnlock();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        fullyLock();
        try {
            objectOutputStream.defaultWriteObject();
            throw null;
        } catch (Throwable th) {
            fullyUnlock();
            throw th;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
