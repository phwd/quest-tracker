package X;

public enum mb {
    THREAD('t'),
    PROCESS('p'),
    GLOBAL('g');
    
    public final char mCode;

    /* access modifiers changed from: public */
    mb(char c) {
        this.mCode = c;
    }

    public char getCode() {
        return this.mCode;
    }
}
