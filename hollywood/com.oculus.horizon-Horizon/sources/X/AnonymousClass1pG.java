package X;

/* renamed from: X.1pG  reason: invalid class name */
public enum AnonymousClass1pG {
    FULL_FETCH(1),
    DISK_CACHE(2),
    ENCODED_MEMORY_CACHE(3),
    BITMAP_MEMORY_CACHE(4);
    
    public int mValue;

    /* access modifiers changed from: public */
    AnonymousClass1pG(int i) {
        this.mValue = i;
    }

    public static AnonymousClass1pG getMax(AnonymousClass1pG r2, AnonymousClass1pG r3) {
        if (r2.getValue() <= r3.getValue()) {
            return r3;
        }
        return r2;
    }

    public int getValue() {
        return this.mValue;
    }
}
