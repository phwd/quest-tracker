package java.lang.ref;

public abstract class Reference {
    Reference pendingNext;
    final ReferenceQueue queue;
    Reference queueNext;
    volatile Object referent;

    private static class SinkHolder {
        private static volatile int finalize_count;
        static volatile Object sink;
        private static Object sinkUser = new Object() {
            /* class java.lang.ref.Reference.SinkHolder.AnonymousClass1 */
        };
    }

    private final native Object getReferent();

    /* access modifiers changed from: package-private */
    public native void clearReferent();

    public Object get() {
        return getReferent();
    }

    public void clear() {
        clearReferent();
    }

    Reference(Object obj) {
        this(obj, null);
    }

    Reference(Object obj, ReferenceQueue referenceQueue) {
        this.referent = obj;
        this.queue = referenceQueue;
    }

    public static void reachabilityFence(Object obj) {
        SinkHolder.sink = obj;
        if (SinkHolder.finalize_count == 0) {
            SinkHolder.sink = null;
        }
    }
}
