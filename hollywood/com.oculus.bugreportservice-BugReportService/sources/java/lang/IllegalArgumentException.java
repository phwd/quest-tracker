package java.lang;

public class IllegalArgumentException extends RuntimeException {
    private static final long serialVersionUID = -5365630128856068164L;

    public IllegalArgumentException() {
    }

    public IllegalArgumentException(String str) {
        super(str);
    }

    public IllegalArgumentException(String str, Throwable th) {
        super(str, th);
    }

    public IllegalArgumentException(Throwable th) {
        super(th);
    }
}
