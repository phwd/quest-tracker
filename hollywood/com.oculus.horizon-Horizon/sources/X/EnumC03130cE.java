package X;

/* renamed from: X.0cE  reason: invalid class name and case insensitive filesystem */
public enum EnumC03130cE {
    NOT_SO("not_so"),
    X86("x86"),
    ARM("armeabi-v7a"),
    X86_64("x86_64"),
    AARCH64("arm64-v8a"),
    OTHERS("others");
    
    public final String value;

    /* access modifiers changed from: public */
    EnumC03130cE(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
