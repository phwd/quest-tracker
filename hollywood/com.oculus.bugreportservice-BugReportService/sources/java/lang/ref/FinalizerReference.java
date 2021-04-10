package java.lang.ref;

public final class FinalizerReference extends Reference {
    private static final Object LIST_LOCK = new Object();
    private static FinalizerReference head = null;
    public static final ReferenceQueue queue = new ReferenceQueue();
    private FinalizerReference next;
    private Object zombie;

    private final native Object getReferent();

    private native boolean makeCircularListIfUnenqueued();

    public static void finalizeAllEnqueued(long j) {
        Sentinel sentinel;
        do {
            sentinel = new Sentinel();
        } while (!enqueueSentinelReference(sentinel));
        sentinel.awaitFinalization(j);
    }

    private static boolean enqueueSentinelReference(Sentinel sentinel) {
        synchronized (LIST_LOCK) {
            for (FinalizerReference finalizerReference = head; finalizerReference != null; finalizerReference = finalizerReference.next) {
                if (finalizerReference.getReferent() == sentinel) {
                    finalizerReference.clearReferent();
                    finalizerReference.zombie = sentinel;
                    if (!finalizerReference.makeCircularListIfUnenqueued()) {
                        return false;
                    }
                    ReferenceQueue.add(finalizerReference);
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

        /* access modifiers changed from: package-private */
        public synchronized void awaitFinalization(long j) {
            long nanoTime = System.nanoTime() + j;
            while (true) {
                if (this.finalized) {
                    break;
                } else if (j != 0) {
                    long nanoTime2 = System.nanoTime();
                    if (nanoTime2 >= nanoTime) {
                        break;
                    }
                    long j2 = nanoTime - nanoTime2;
                    wait(j2 / 1000000, (int) (j2 % 1000000));
                } else {
                    wait();
                }
            }
        }
    }
}
