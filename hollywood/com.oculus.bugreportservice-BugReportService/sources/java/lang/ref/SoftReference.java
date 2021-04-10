package java.lang.ref;

public class SoftReference extends Reference {
    private static long clock;
    private long timestamp = clock;

    public SoftReference(Object obj) {
        super(obj);
    }

    public SoftReference(Object obj, ReferenceQueue referenceQueue) {
        super(obj, referenceQueue);
    }

    @Override // java.lang.ref.Reference
    public Object get() {
        Object obj = super.get();
        if (obj != null) {
            long j = this.timestamp;
            long j2 = clock;
            if (j != j2) {
                this.timestamp = j2;
            }
        }
        return obj;
    }
}
