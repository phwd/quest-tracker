package X;

public enum KH {
    NOT_SO("not_so"),
    X86("x86"),
    ARM("armeabi-v7a"),
    X86_64("x86_64"),
    AARCH64("arm64-v8a"),
    OTHERS("others");
    
    public final String value;

    /* access modifiers changed from: public */
    KH(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
