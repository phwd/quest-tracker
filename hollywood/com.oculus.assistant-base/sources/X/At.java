package X;

public enum At {
    RAW("raw"),
    OPUS_DEMO("opus_demo");
    
    public final String mValue;

    /* access modifiers changed from: public */
    At(String str) {
        this.mValue = str;
    }

    public String toString() {
        return this.mValue;
    }
}
