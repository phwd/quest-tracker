package java.lang.ref;

public class SoftReference<T> extends Reference<T> {
    private static long clock;
    private long timestamp = clock;

    public SoftReference(T referent) {
        super(referent);
    }

    public SoftReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    @Override // java.lang.ref.Reference
    public T get() {
        T o = (T) super.get();
        if (o != null) {
            long j = this.timestamp;
            long j2 = clock;
            if (j != j2) {
                this.timestamp = j2;
            }
        }
        return o;
    }
}
