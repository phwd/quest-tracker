package java.util.concurrent;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DelayQueue<E extends Delayed> extends AbstractQueue<E> implements BlockingQueue<E> {
    private final Condition available = this.lock.newCondition();
    private Thread leader;
    private final transient ReentrantLock lock = new ReentrantLock();
    private final PriorityQueue<E> q = new PriorityQueue<>();

    public DelayQueue() {
    }

    public DelayQueue(Collection<? extends E> c) {
        addAll(c);
    }

    public boolean add(E e) {
        return offer((Delayed) e);
    }

    public boolean offer(E e) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            this.q.offer(e);
            if (this.q.peek() == e) {
                this.leader = null;
                this.available.signal();
            }
            return true;
        } finally {
            lock2.unlock();
        }
    }

    public void put(E e) {
        offer((Delayed) e);
    }

    public boolean offer(E e, long timeout, TimeUnit unit) {
        return offer((Delayed) e);
    }

    @Override // java.util.Queue
    public E poll() {
        E e;
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            E first = this.q.peek();
            if (first != null) {
                if (first.getDelay(TimeUnit.NANOSECONDS) <= 0) {
                    e = this.q.poll();
                    return e;
                }
            }
            e = null;
            return e;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (true) {
            try {
                E first = this.q.peek();
                if (first == null) {
                    this.available.await();
                } else {
                    long delay = first.getDelay(TimeUnit.NANOSECONDS);
                    if (delay <= 0) {
                        break;
                    } else if (this.leader != null) {
                        this.available.await();
                    } else {
                        Thread thisThread = Thread.currentThread();
                        this.leader = thisThread;
                        try {
                            this.available.awaitNanos(delay);
                        } finally {
                            if (this.leader == thisThread) {
                                this.leader = null;
                            }
                        }
                    }
                }
            } finally {
                if (this.leader == null && this.q.peek() != null) {
                    this.available.signal();
                }
                lock2.unlock();
            }
        }
        return this.q.poll();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (true) {
            try {
                E first = this.q.peek();
                if (first != null) {
                    long delay = first.getDelay(TimeUnit.NANOSECONDS);
                    if (delay <= 0) {
                        E poll = this.q.poll();
                        if (this.leader == null && this.q.peek() != null) {
                            this.available.signal();
                        }
                        lock2.unlock();
                        return poll;
                    } else if (nanos <= 0) {
                        if (this.leader == null && this.q.peek() != null) {
                            this.available.signal();
                        }
                        lock2.unlock();
                        return null;
                    } else if (nanos < delay || this.leader != null) {
                        nanos = this.available.awaitNanos(nanos);
                    } else {
                        Thread thisThread = Thread.currentThread();
                        this.leader = thisThread;
                        try {
                            nanos -= delay - this.available.awaitNanos(delay);
                        } finally {
                            if (this.leader == thisThread) {
                                this.leader = null;
                            }
                        }
                    }
                } else if (nanos <= 0) {
                    return null;
                } else {
                    nanos = this.available.awaitNanos(nanos);
                }
            } finally {
                if (this.leader == null && this.q.peek() != null) {
                    this.available.signal();
                }
                lock2.unlock();
            }
        }
    }

    @Override // java.util.Queue
    public E peek() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.q.peek();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.q.size();
        } finally {
            lock2.unlock();
        }
    }

    private E peekExpired() {
        E first = this.q.peek();
        if (first == null || first.getDelay(TimeUnit.NANOSECONDS) > 0) {
            return null;
        }
        return first;
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c != this) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            int n = 0;
            while (true) {
                try {
                    E e = peekExpired();
                    if (e == null) {
                        return n;
                    }
                    c.add(e);
                    this.q.poll();
                    n++;
                } finally {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c == this) {
            throw new IllegalArgumentException();
        } else if (maxElements <= 0) {
            return 0;
        } else {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            int n = 0;
            while (n < maxElements) {
                try {
                    E e = peekExpired();
                    if (e == null) {
                        break;
                    }
                    c.add(e);
                    this.q.poll();
                    n++;
                } catch (Throwable th) {
                    lock2.unlock();
                    throw th;
                }
            }
            lock2.unlock();
            return n;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public void clear() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            this.q.clear();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.q.toArray();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return (T[]) this.q.toArray(a);
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.q.remove(o);
        } finally {
            lock2.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeEQ(Object o) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            Iterator<E> it = this.q.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (o == it.next()) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr(toArray());
    }

    private class Itr implements Iterator<E> {
        final Object[] array;
        int cursor;
        int lastRet = -1;

        Itr(Object[] array2) {
            this.array = array2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor < this.array.length;
        }

        @Override // java.util.Iterator
        public E next() {
            int i = this.cursor;
            Object[] objArr = this.array;
            if (i < objArr.length) {
                this.lastRet = i;
                this.cursor = i + 1;
                return (E) ((Delayed) objArr[i]);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i >= 0) {
                DelayQueue.this.removeEQ(this.array[i]);
                this.lastRet = -1;
                return;
            }
            throw new IllegalStateException();
        }
    }
}
