package java.lang;

public class AssertionError extends Error {
    private static final long serialVersionUID = -5013299493970297370L;

    public AssertionError() {
    }

    private AssertionError(String str) {
        super(str);
    }

    public AssertionError(Object obj) {
        this(String.valueOf(obj));
        if (obj instanceof Throwable) {
            initCause((Throwable) obj);
        }
    }

    public AssertionError(String str, Throwable th) {
        super(str, th);
    }
}
