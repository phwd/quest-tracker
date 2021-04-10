package X;

/* renamed from: X.1gZ  reason: invalid class name */
public enum AnonymousClass1gZ {
    RUNNING(false),
    PAUSED(false),
    CLEARED(false),
    SUCCESS(true),
    FAILED(true);
    
    public final boolean isComplete;

    public boolean isComplete() {
        return this.isComplete;
    }

    /* access modifiers changed from: public */
    AnonymousClass1gZ(boolean z) {
        this.isComplete = z;
    }
}
