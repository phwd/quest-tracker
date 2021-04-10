package android.icu.impl;

public class IllegalIcuArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = 3789261542830211225L;

    public IllegalIcuArgumentException(String str) {
        super(str);
    }

    public IllegalIcuArgumentException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public synchronized IllegalIcuArgumentException initCause(Throwable th) {
        super.initCause(th);
        return this;
    }
}
