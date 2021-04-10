package X;

public enum BN {
    RAW("raw"),
    OPUS_DEMO("opus_demo");
    
    public final String value;

    /* access modifiers changed from: public */
    BN(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
