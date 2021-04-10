package java.lang.ref;

public class ReferenceQueue {
    private static final Reference sQueueNextUnenqueued = new PhantomReference(null, null);
    public static Reference unenqueued = null;
    private Reference head = null;
    private final Object lock = new Object();
    private Reference tail = null;

    private Reference reallyPollLocked() {
        Reference reference = this.head;
        if (reference == null) {
            return null;
        }
        if (reference == this.tail) {
            this.tail = null;
            this.head = null;
        } else {
            this.head = reference.queueNext;
        }
        reference.queueNext = sQueueNextUnenqueued;
        return reference;
    }

    public Reference poll() {
        synchronized (this.lock) {
            if (this.head == null) {
                return null;
            }
            return reallyPollLocked();
        }
    }

    static void add(Reference reference) {
        synchronized (ReferenceQueue.class) {
            if (unenqueued == null) {
                unenqueued = reference;
            } else {
                Reference reference2 = unenqueued;
                while (reference2.pendingNext != unenqueued) {
                    reference2 = reference2.pendingNext;
                }
                reference2.pendingNext = reference;
                Reference reference3 = reference;
                while (reference3.pendingNext != reference) {
                    reference3 = reference3.pendingNext;
                }
                reference3.pendingNext = unenqueued;
            }
            ReferenceQueue.class.notifyAll();
        }
    }
}
