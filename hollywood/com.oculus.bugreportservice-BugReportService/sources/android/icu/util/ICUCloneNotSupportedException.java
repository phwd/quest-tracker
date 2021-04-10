package android.icu.util;

public class ICUCloneNotSupportedException extends ICUException {
    private static final long serialVersionUID = -4824446458488194964L;

    public ICUCloneNotSupportedException() {
    }

    public ICUCloneNotSupportedException(String str) {
        super(str);
    }

    public ICUCloneNotSupportedException(Throwable th) {
        super(th);
    }
}
