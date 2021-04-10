package android.icu.util;

public class ICUException extends RuntimeException {
    private static final long serialVersionUID = -3067399656455755650L;

    public ICUException() {
    }

    public ICUException(String str) {
        super(str);
    }

    public ICUException(Throwable th) {
        super(th);
    }
}
