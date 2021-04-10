package X;

/* renamed from: X.1l4  reason: invalid class name */
public enum AnonymousClass1l4 {
    FULL_FETCH(1),
    DISK_CACHE(2),
    ENCODED_MEMORY_CACHE(3),
    BITMAP_MEMORY_CACHE(4);
    
    public int mValue;

    public int getValue() {
        return this.mValue;
    }

    /* access modifiers changed from: public */
    AnonymousClass1l4(int i) {
        this.mValue = i;
    }

    public static AnonymousClass1l4 getMax(AnonymousClass1l4 r2, AnonymousClass1l4 r3) {
        if (r2.getValue() <= r3.getValue()) {
            return r3;
        }
        return r2;
    }
}
