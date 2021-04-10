package java.lang.ref;

public final class FinalizerReference<T> extends Reference<T> {
    private static final Object LIST_LOCK = new Object();
    private static FinalizerReference<?> head = null;
    public static final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    private FinalizerReference<?> next;
    private FinalizerReference<?> prev;
    private T zombie;

    private final native T getReferent();

    private native boolean makeCircularListIfUnenqueued();

    public FinalizerReference(T r, ReferenceQueue<? super T> q) {
        super(r, q);
    }

    @Override // java.lang.ref.Reference
    public T get() {
        return this.zombie;
    }

    @Override // java.lang.ref.Reference
    public void clear() {
        this.zombie = null;
    }

    public static void add(Object referent) {
        FinalizerReference<?> reference = new FinalizerReference<>(referent, queue);
        synchronized (LIST_LOCK) {
            ((FinalizerReference) reference).prev = null;
            ((FinalizerReference) reference).next = head;
            if (head != null) {
                ((FinalizerReference) head).prev = reference;
            }
            head = reference;
        }
    }

    public static void remove(FinalizerReference<?> reference) {
        synchronized (LIST_LOCK) {
            FinalizerReference<?> next2 = ((FinalizerReference) reference).next;
            FinalizerReference<?> prev2 = ((FinalizerReference) reference).prev;
            ((FinalizerReference) reference).next = null;
            ((FinalizerReference) reference).prev = null;
            if (prev2 != null) {
                ((FinalizerReference) prev2).next = next2;
            } else {
                head = next2;
            }
            if (next2 != null) {
                ((FinalizerReference) next2).prev = prev2;
            }
        }
    }

    public static void finalizeAllEnqueued(long timeout) throws InterruptedException {
        Sentinel sentinel;
        do {
            sentinel = new Sentinel();
        } while (!enqueueSentinelReference(sentinel));
        sentinel.awaitFinalization(timeout);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.ref.FinalizerReference$Sentinel */
    /* JADX WARN: Multi-variable type inference failed */
    private static boolean enqueueSentinelReference(Sentinel sentinel) {
        synchronized (LIST_LOCK) {
            for (FinalizerReference<?> r = head; r != null; r = ((FinalizerReference) r).next) {
                if (r.getReferent() == sentinel) {
                    r.clearReferent();
                    ((FinalizerReference) r).zombie = sentinel;
                    if (!r.makeCircularListIfUnenqueued()) {
                        return false;
                    }
                    ReferenceQueue.add(r);
                    return true;
                }
            }
            throw new AssertionError((Object) "newly-created live Sentinel not on list!");
        }
    }

    private static class Sentinel {
        boolean finalized;

        private Sentinel() {
            this.finalized = false;
        }

        /* access modifiers changed from: protected */
        public synchronized void finalize() throws Throwable {
            if (!this.finalized) {
                this.finalized = true;
                notifyAll();
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void awaitFinalization(long timeout) throws InterruptedException {
            long endTime = System.nanoTime() + timeout;
            while (true) {
                if (this.finalized) {
                    break;
                } else if (timeout != 0) {
                    long currentTime = System.nanoTime();
                    if (currentTime >= endTime) {
                        break;
                    }
                    long deltaTime = endTime - currentTime;
                    wait(deltaTime / 1000000, (int) (deltaTime % 1000000));
                } else {
                    wait();
                }
            }
        }
    }
}
