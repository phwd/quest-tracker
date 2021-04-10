package X;

public enum ZQ {
    NotSet(0),
    FromCache(1),
    FromService(2);
    
    public final int mPriority;

    /* access modifiers changed from: public */
    ZQ(int i) {
        this.mPriority = i;
    }

    public int getPriority() {
        return this.mPriority;
    }
}
