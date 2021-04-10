package X;

public enum Q0 {
    THREAD('t'),
    PROCESS('p'),
    GLOBAL('g');
    
    public final char mCode;

    /* access modifiers changed from: public */
    Q0(char c) {
        this.mCode = c;
    }

    public char getCode() {
        return this.mCode;
    }
}
