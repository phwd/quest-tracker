package java.util.zip;

/* access modifiers changed from: package-private */
public class ZStreamRef {
    private volatile long address;

    ZStreamRef(long j) {
        this.address = j;
    }

    /* access modifiers changed from: package-private */
    public long address() {
        return this.address;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.address = 0;
    }
}
