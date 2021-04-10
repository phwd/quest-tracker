package X;

/* renamed from: X.0m0  reason: invalid class name and case insensitive filesystem */
public enum EnumC03300m0 {
    THREAD('t'),
    PROCESS('p'),
    GLOBAL('g');
    
    public final char mCode;

    /* access modifiers changed from: public */
    EnumC03300m0(char c) {
        this.mCode = c;
    }

    public char getCode() {
        return this.mCode;
    }
}
