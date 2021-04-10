package X;

/* renamed from: X.Fq  reason: case insensitive filesystem */
public enum EnumC0165Fq {
    AUTO_EXPOSURE("auto"),
    MANUAL_EXPOSURE("man");
    
    public String mValue;

    /* access modifiers changed from: public */
    EnumC0165Fq(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public String toString() {
        return getValue();
    }
}
