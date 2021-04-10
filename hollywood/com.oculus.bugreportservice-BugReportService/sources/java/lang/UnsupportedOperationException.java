package java.lang;

public class UnsupportedOperationException extends RuntimeException {
    static final long serialVersionUID = -1242599979055084673L;

    public UnsupportedOperationException() {
    }

    public UnsupportedOperationException(String str) {
        super(str);
    }

    public UnsupportedOperationException(Throwable th) {
        super(th);
    }
}
