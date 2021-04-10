package java.lang.ref;

import sun.misc.Cleaner;

public class ReferenceQueue<T> {
    private static final Reference sQueueNextUnenqueued = new PhantomReference(null, null);
    public static Reference<?> unenqueued = null;
    private Reference<? extends T> head = null;
    private final Object lock = new Object();
    private Reference<? extends T> tail = null;

    private boolean enqueueLocked(Reference<? extends T> r) {
        if (r.queueNext != null) {
            return false;
        }
        if (r instanceof Cleaner) {
            ((Cleaner) r).clean();
            r.queueNext = sQueueNextUnenqueued;
            return true;
        }
        Reference<? extends T> reference = this.tail;
        if (reference == null) {
            this.head = r;
        } else {
            reference.queueNext = r;
        }
        this.tail = r;
        this.tail.queueNext = r;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isEnqueued(Reference<? extends T> reference) {
        boolean z;
        synchronized (this.lock) {
            z = (reference.queueNext == null || reference.queueNext == sQueueNextUnenqueued) ? false : true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean enqueue(Reference<? extends T> reference) {
        synchronized (this.lock) {
            if (!enqueueLocked(reference)) {
                return false;
            }
            this.lock.notifyAll();
            return true;
        }
    }

    private Reference<? extends T> reallyPollLocked() {
        Reference<? extends T> reference = this.head;
        if (reference == null) {
            return null;
        }
        Reference<? extends T> r = this.head;
        if (reference == this.tail) {
            this.tail = null;
            this.head = null;
        } else {
            this.head = reference.queueNext;
        }
        r.queueNext = sQueueNextUnenqueued;
        return r;
    }

    public Reference<? extends T> poll() {
        synchronized (this.lock) {
            if (this.head == null) {
                return null;
            }
            return reallyPollLocked();
        }
    }

    public Reference<? extends T> remove(long timeout) throws IllegalArgumentException, InterruptedException {
        if (timeout >= 0) {
            synchronized (this.lock) {
                Reference<? extends T> r = reallyPollLocked();
                if (r != null) {
                    return r;
                }
                long start = timeout == 0 ? 0 : System.nanoTime();
                while (true) {
                    this.lock.wait(timeout);
                    Reference<? extends T> r2 = reallyPollLocked();
                    if (r2 != null) {
                        return r2;
                    }
                    if (timeout != 0) {
                        long end = System.nanoTime();
                        timeout -= (end - start) / 1000000;
                        if (timeout <= 0) {
                            return null;
                        }
                        start = end;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Negative timeout value");
        }
    }

    public Reference<? extends T> remove() throws InterruptedException {
        return remove(0);
    }

    public static void enqueuePending(Reference<?> list) {
        do {
            ReferenceQueue<? super T> referenceQueue = list.queue;
            if (referenceQueue == null) {
                Reference<?> next = list.pendingNext;
                list.pendingNext = list;
                list = next;
                continue;
            } else {
                synchronized (((ReferenceQueue) referenceQueue).lock) {
                    do {
                        Reference<?> next2 = list.pendingNext;
                        list.pendingNext = list;
                        referenceQueue.enqueueLocked(list);
                        list = next2;
                        if (list == list) {
                            break;
                        }
                    } while (list.queue == referenceQueue);
                    ((ReferenceQueue) referenceQueue).lock.notifyAll();
                }
                continue;
            }
        } while (list != list);
    }

    static void add(Reference<?> list) {
        synchronized (ReferenceQueue.class) {
            if (unenqueued == null) {
                unenqueued = list;
            } else {
                Reference<?> last = unenqueued;
                while (last.pendingNext != unenqueued) {
                    last = last.pendingNext;
                }
                last.pendingNext = list;
                Reference<?> last2 = list;
                while (last2.pendingNext != list) {
                    last2 = last2.pendingNext;
                }
                last2.pendingNext = unenqueued;
            }
            ReferenceQueue.class.notifyAll();
        }
    }
}
