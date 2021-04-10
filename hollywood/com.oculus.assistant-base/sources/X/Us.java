package X;

public final class Us {
    public static final Us A00 = new Us(false);
    public volatile Us next;
    public volatile Thread thread;

    public Us() {
        SH.ATOMIC_HELPER.A01(this, Thread.currentThread());
    }

    public Us(boolean z) {
    }
}
