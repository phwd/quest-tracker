package java.lang;

public class IllegalStateException extends RuntimeException {
    static final long serialVersionUID = -1848914673093119416L;

    public IllegalStateException() {
    }

    public IllegalStateException(String str) {
        super(str);
    }

    public IllegalStateException(String str, Throwable th) {
        super(str, th);
    }
}
